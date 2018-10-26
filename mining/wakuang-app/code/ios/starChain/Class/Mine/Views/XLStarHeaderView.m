//
//  XLStarHeaderView.m
//  starChain
//
//  Created by rlx on 2018/6/25.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import "XLStarHeaderView.h"
#import "ZFChart.h"
#import "XLCompleteTaskModel.h"
#import "XLStarCurveView.h"

@interface XLStarContenView : UIView

@property (strong, nonatomic) NSDictionary *dict;
@property (weak, nonatomic) UILabel *starLable;
@property (weak, nonatomic) UIImageView *starImageView;
@property (weak, nonatomic) UILabel *starCountLable;

@property (strong, nonatomic) CAGradientLayer *gradientLayer;

@property (assign, nonatomic) NSInteger startValue;
@property (assign, nonatomic) NSInteger todayValue;




@end


@implementation XLStarContenView


- (instancetype)initWithFrame:(CGRect)frame {
    if (self = [super initWithFrame:frame]) {
        [self addSubView];
    }
    return self;
}

- (void)addSubView {
    
    UILabel *starLable = [UILabel lableWithSuperView:self fontSize:12 color:UIColor_Hex(0xe0deff) title:@"" textAlignment:NSTextAlignmentLeft];
    [starLable makeConstraints:^(MASConstraintMaker *make) {
        make.left.offset(10);
        make.top.offset(7);
    }];
    
    UIImageView *starImageView = [UIImageView tj_WithSuperView:self];
    UIImage *starImage = [UIImage imageNamed:@"icon-star"];
    [starImageView makeConstraints:^(MASConstraintMaker *make) {
        make.right.offset(-10);
        make.size.equalTo(starImage.size);
        make.top.offset(11);
    }];
    
    UILabel *starCountLable = [UILabel lableWithSuperView:self fontSize:ZOOM5(21) color:UIColor_Hex(0xffffff) title:@"0" textAlignment:NSTextAlignmentLeft];
    [starCountLable makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(starLable);
        make.top.equalTo(starLable.bottom).offset(12);
    }];
    
    self.starLable = starLable;
    self.starImageView = starImageView;
    self.starCountLable = starCountLable;
}

- (void)setDict:(NSDictionary *)dict {
    _dict = dict;
    
    self.starLable.text = dict[@"text"];
    self.starImageView.image = [UIImage imageNamed:dict[@"icon"]];
    self.starCountLable.text = dict[@"starCount"];
}


@end


@interface XLStarHeaderView()

@property (nonatomic, strong) XLStarCurveView *starCurveView;


@property (copy, nonatomic) NSString *nebula;
@property (copy, nonatomic) NSString *constellation;
@property (copy, nonatomic) NSString *stars;
@property (strong, nonatomic) NSArray *datas;

@property (weak, nonatomic) UIView *starDetailsView;
@property (strong, nonatomic) CAGradientLayer *gradientLayer;
@property (strong, nonatomic) NSMutableArray <XLStarContenView *>*starContentViews;

@property (strong, nonatomic) UIView *curveView;

@property (strong, nonatomic) NSMutableArray *starValues;

@property (weak, nonatomic) UILabel *startDateLable;
@property (weak, nonatomic) UILabel *todayDateLable;
@property (weak, nonatomic) UILabel *startVauleLable;
@property (weak, nonatomic) UILabel *todayVauleLable;



@end

@implementation XLStarHeaderView

- (instancetype)initWithFrame:(CGRect)frame {
    if (self = [super initWithFrame:frame]) {
        [self addSubView];
    }
    return self;
}

- (void)addSubView {
    
    self.backgroundColor = UIColor_Hex(0xf2f2f2);
    
    self.starContentViews = [NSMutableArray array];
    self.starValues = [NSMutableArray array];
    
    UIView *contentView = [UIView tj_WithSuperView:self];
    contentView.backgroundColor = [UIColor whiteColor];
    contentView.frame = CGRectMake(0, 0, self.tj_width, self.tj_height - 10);
    self.contentView = contentView;
    [contentView makeConstraints:^(MASConstraintMaker *make) {
        make.left.right.top.offset(0);
        make.bottom.offset(-10);
    }];
    
    UILabel *starLable = [UILabel lableWithSuperView:contentView fontSize:14 color:UIColor_Hex(0xe0deff) title:@"星星" textAlignment:NSTextAlignmentLeft];
    [starLable makeConstraints:^(MASConstraintMaker *make) {
        make.centerX.offset(0);
        make.top.offset(85);
    }];
    
    UIImageView *starImageView = [UIImageView imageViewWithImageName:@"icon-star" superView:contentView];
    [starImageView makeConstraints:^(MASConstraintMaker *make) {
        make.right.equalTo(starLable.left).offset(-8);
        make.height.width.offset(15);
        make.centerY.equalTo(starLable);
    }];
    
    UIButton *arrowButton = [UIButton tj_WithSuperView:contentView];
    [arrowButton setImage:[UIImage imageNamed:@"icon-tridown"] forState:UIControlStateNormal];
    [arrowButton setImage:[UIImage imageNamed:@"icon-triup"] forState:UIControlStateSelected];
    [arrowButton addTarget:self action:@selector(didClickArrowButton:) forControlEvents:UIControlEventTouchUpInside];
    [arrowButton makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(starLable.right).offset(0);
        make.centerY.equalTo(starImageView);
        make.height.offset(30);
        make.width.offset(40);
    }];
    
    UILabel *starCountLable = [UILabel lableWithSuperView:contentView fontSize:24 color:UIColor_Hex(0xffffff) title:@"" textAlignment:NSTextAlignmentLeft];
    [starCountLable makeConstraints:^(MASConstraintMaker *make) {
        make.centerX.offset(0);
        make.top.equalTo(starImageView.bottom).offset(12);
        make.height.offset(20);
    }];
    
    UIView *starDetailsView = [UIView tj_WithSuperView:contentView];
    starDetailsView.backgroundColor = [UIColor clearColor];
    [starDetailsView makeConstraints:^(MASConstraintMaker *make) {
        make.top.equalTo(starCountLable.bottom).offset(7);
        make.left.offset(16);
        make.right.offset(-16);
        make.height.offset(0);
    }];
    
    CGFloat count = 3;
    CGFloat margin = 5;
    CGFloat width = (KScreenWidth - 32 - (count - 1) * margin) / count;
    for (int i = 0; i < 3; i++) {
        CGFloat left = i * (margin + width);
        XLStarContenView *starContentView = [XLStarContenView tj_WithSuperView:starDetailsView];
        starContentView.backgroundColor = UIColor_Hex(0x6132a1);
        [starContentView makeConstraints:^(MASConstraintMaker *make) {
            make.left.offset(left);
            make.width.offset(width);
            make.height.offset(61);
            make.top.offset(5);
        }];
        [self.starContentViews addObject:starContentView];
    }
    
    UIView *curveView = [UIView tj_WithSuperView:contentView];
    curveView.backgroundColor = [UIColor whiteColor];
    [curveView shearRoundedCornersWithRadiu:8];
    [curveView makeConstraints:^(MASConstraintMaker *make) {
        make.top.equalTo(starDetailsView.bottom).offset(10);
        make.left.offset(H(16));
        make.right.offset(H(-16));
        make.height.offset(123);
    }];
    
    UILabel *instructionsLable = [UILabel lableWithSuperView:starDetailsView fontSize:12 color:UIColor_Hex(0xffffff) title:@"1个星云=10000个星座，1个星座=10000个星星" textAlignment:NSTextAlignmentLeft];
    [instructionsLable makeConstraints:^(MASConstraintMaker *make) {
        make.centerX.offset(0);
        make.bottom.offset(-12);
    }];
    
    self.curveView = curveView;
    self.starDetailsView = starDetailsView;
    self.starCountLable = starCountLable;
    
    [self.starDetailsView.subviews enumerateObjectsUsingBlock:^(__kindof UIView * _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
        obj.hidden = YES;
    }];
 
    _gradientLayer = [self.contentView addGradientLayerWithColors:@[UIColor_Hex(0x2f1959), UIColor_Hex(0xac56fa)] endPoint:CGPointMake(0, 1)];
    
    self.starCurveView = [[XLStarCurveView alloc] initWithFrame:CGRectMake(0, 10, KScreenWidth - H(32), 90)];
    [curveView addSubview:self.starCurveView];
    
    UILabel *startDateLable = [UILabel lableWithSuperView:curveView fontSize:13 color:UIColor_Hex(0x847ed9) title:@"" textAlignment:NSTextAlignmentLeft];
    [startDateLable makeConstraints:^(MASConstraintMaker *make) {
        make.left.offset(6);
        make.bottom.offset(H(-4));
    }];
    
    UILabel *todayDateLable = [UILabel lableWithSuperView:curveView fontSize:13 color:UIColor_Hex(0x847ed9) title:@"" textAlignment:NSTextAlignmentLeft];
    [todayDateLable makeConstraints:^(MASConstraintMaker *make) {
        make.right.offset(-6);
        make.bottom.equalTo(startDateLable);
    }];
    
    UILabel *startVauleLable = [UILabel lableWithSuperView:curveView fontSize:13 color:UIColor_Hex(0x847ed9) title:@"" textAlignment:NSTextAlignmentLeft];
    [startVauleLable makeConstraints:^(MASConstraintMaker *make) {
        make.left.offset(0);
        make.bottom.offset(H(-23));
    }];
    
    UILabel *todayVauleLable = [UILabel lableWithSuperView:curveView fontSize:13 color:UIColor_Hex(0x847ed9) title:@"" textAlignment:NSTextAlignmentLeft];
    [todayVauleLable makeConstraints:^(MASConstraintMaker *make) {
        make.top.offset(3);
        make.right.offset(0);
    }];
    
    self.startDateLable = startDateLable;
    self.todayDateLable = todayDateLable;
    self.startVauleLable = startVauleLable;
    self.todayVauleLable = todayVauleLable;
 
}


- (void)setStarRecordDatas:(NSArray *)starRecordDatas {
    _starRecordDatas = starRecordDatas;
    
    if (!starRecordDatas.count) return;
    
    [_starValues removeAllObjects];
    
    self.startDateLable.text = @"";
    self.todayDateLable.text = @"";
    self.startVauleLable.text = @"";
    self.todayVauleLable.text = @"";
    
    if (!starRecordDatas.count) return;
    
    //如果只有一条数据的话,就不显示日期, 画一根横线
    if (starRecordDatas.count > 1) {
        
        self.startDateLable.text = starRecordDatas.firstObject[@"day"];
        self.todayDateLable.text = starRecordDatas.lastObject[@"day"];
        self.startVauleLable.text = starRecordDatas.firstObject[@"starPoint"];
        self.todayVauleLable.text = starRecordDatas.lastObject[@"starPoint"];
        
        [self.startVauleLable remakeConstraints:^(MASConstraintMaker *make) {
            make.left.offset(0);
            make.bottom.offset(-25);
        }];
        
        [self.starRecordDatas enumerateObjectsUsingBlock:^(id  _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
            [self.starValues addObject:obj[@"starPoint"]];
        }];

        self.starCurveView.vaules = self.starValues;
        self.starCurveView.showStraightline = NO;
        
        //如果有2天的数据, 而且是一样的
        if (self.starCurveView.noGrowth) {
            
            [self.todayVauleLable remakeConstraints:^(MASConstraintMaker *make) {
                make.bottom.equalTo(self.startVauleLable);
                make.right.offset(0);
            }];
        } else {
            [self.todayVauleLable remakeConstraints:^(MASConstraintMaker *make) {
                make.top.offset(3);
                make.right.offset(0);
            }];
        }
        
    } else {
        
        self.startDateLable.text = starRecordDatas.firstObject[@"day"];
        self.startVauleLable.text = starRecordDatas.firstObject[@"starPoint"];
        
        self.starCurveView.vaules = [NSArray array];
        self.starCurveView.showStraightline = YES;

        [self.startVauleLable remakeConstraints:^(MASConstraintMaker *make) {
            make.centerX.offset(0);
            make.bottom.offset(-45);
        }];

     }
 
     NSLog(@"_starValues = %@", _starValues);
    
}

- (void)didClickArrowButton:(UIButton *)button {
    button.selected = !button.selected;
    
    [_gradientLayer removeFromSuperlayer];
        
    if (button.selected) {
 
        [self.starDetailsView updateConstraints:^(MASConstraintMaker *make) {
            make.height.offset(105);
        }];
        [self.starDetailsView.subviews enumerateObjectsUsingBlock:^(__kindof UIView * _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
            obj.hidden = NO;
        }];
        
        
    } else {
        [self.starDetailsView updateConstraints:^(MASConstraintMaker *make) {
            make.height.offset(0);
        }];
        [self.starDetailsView.subviews enumerateObjectsUsingBlock:^(__kindof UIView * _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
            obj.hidden = YES;
        }];
    }

    if ([self.delegate respondsToSelector:@selector(didCLickArrowButton:button:)]) {
        [self.delegate didCLickArrowButton:self button:button];
    }

    _gradientLayer = [self.contentView addGradientLayerWithColors:@[UIColor_Hex(0x2f1959), UIColor_Hex(0xac56fa)] endPoint:CGPointMake(0, 1)];
    
}

- (NSArray *)datas {
    _datas = @[
               @{
                   @"text": @"星云",
                   @"icon": @"icon-xy",
                   @"starCount": (_nebula.length) ? _nebula : @""
                   },
               @{
                   @"text": @"星座",
                   @"icon": @"icon-xz",
                   @"starCount": (_constellation.length) ? _constellation : @""
                   },
               @{
                   @"text": @"星星",
                   @"icon": @"icon-star",
                   @"starCount": (_stars.length) ? _stars : @""
                   }
               ];
    return _datas;
}

- (void)setStarCount:(NSString *)starCount {
    _starCount = starCount;
    
    NSInteger constellationCount = (starCount.integerValue % (10000 * 10000)) / 10000;
    CGFloat star_count = (starCount.integerValue % (10000 * 10000)) % 10000;
    NSInteger nebulaCount = starCount.integerValue / (10000 * 10000);
    
    _nebula = [NSString stringWithFormat:@"%ld", (long)nebulaCount]; //星云
    _constellation = [NSString stringWithFormat:@"%ld", (long)constellationCount];//星座
    _stars = [NSString stringWithFormat:@"%.4f",star_count + (starCount.doubleValue - starCount.integerValue)]; //星星
    self.starCountLable.text = starCount;

    [self.starContentViews enumerateObjectsUsingBlock:^(XLStarContenView * _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
        obj.dict = self.datas[idx];
    }];
}


@end
