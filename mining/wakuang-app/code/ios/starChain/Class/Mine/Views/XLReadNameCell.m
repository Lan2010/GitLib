//
//  XLReadNameCell.m
//  starChain
//
//  Created by rlx on 2018/6/15.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import "XLReadNameCell.h"
#import "XLRealNameModel.h"

@interface XLReadNameCell()

@property (weak, nonatomic) UIImageView *iconView;
@property (weak, nonatomic) UILabel *typeTextLable;
@property (weak, nonatomic) UILabel *starCountLable;
@property (weak, nonatomic) UILabel *stateLable;
@property (weak, nonatomic) UIImageView *stateImageView;

@end

@implementation XLReadNameCell

- (instancetype)initWithFrame:(CGRect)frame {
    if (self = [super initWithFrame:frame]) {
        [self addSubView];
    }
    return self;
}

- (void)addSubView {
    
    UIImageView *iconView = [UIImageView tj_WithSuperView:self.contentView];
    [iconView makeConstraints:^(MASConstraintMaker *make) {
        make.centerX.offset(0);
        make.width.height.offset(25);
        make.top.offset(8);
    }];
    
    UILabel *typeTextLable = [UILabel lableWithSuperView:self.contentView fontSize:16 color:UIColor_Hex(0x000000) title:@"姓名身份证" textAlignment:NSTextAlignmentLeft];
    [typeTextLable makeConstraints:^(MASConstraintMaker *make) {
        make.centerX.equalTo(iconView);
        make.top.equalTo(iconView.bottom).offset(7);
    }];
    
    UIView *gradientView = [[UIView alloc] initWithFrame:CGRectMake(0, 0, ZOOM5(80), 25)];
    [gradientView shearRoundedCornersWithRadiu:25 * 0.5];
    [self.contentView addSubview:gradientView];
    [gradientView addGradientLayerWithColors:@[UIColor_Hex(0xAC56F9), UIColor_Hex(0x6963C0)] endPoint:CGPointMake(1, 0)];
    [gradientView makeConstraints:^(MASConstraintMaker *make) {
        make.top.equalTo(typeTextLable.bottom).offset(8);
        make.centerX.offset(0);
        make.width.offset(ZOOM5(80));
        make.height.offset(25);
    }];
    
    UILabel *starCountLable = [UILabel lableWithSuperView:gradientView fontSize:14 color:[UIColor whiteColor] title:@"+10星星" textAlignment:NSTextAlignmentCenter];
    [starCountLable makeConstraints:^(MASConstraintMaker *make) {
        make.center.offset(0);
    }];
    
    UIImageView *stateImageView = [UIImageView tj_WithSuperView:self.contentView];
    [stateImageView makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(gradientView).offset(15);
        make.width.height.offset(10);
        make.top.equalTo(gradientView.bottom).offset(13);
    }];
    
    UILabel *stateLable = [UILabel lableWithSuperView:self.contentView fontSize:13 color:UIColor_Hex(0x000000) title:@"已完成" textAlignment:NSTextAlignmentLeft];
    [stateLable makeConstraints:^(MASConstraintMaker *make) {
        make.centerY.equalTo(stateImageView);
        make.left.equalTo(stateImageView.right).offset(3);
    }];
    
    self.contentView.backgroundColor = [UIColor whiteColor];
    self.iconView = iconView;
    self.stateImageView = stateImageView;
    self.typeTextLable = typeTextLable;
    self.stateLable = stateLable;
    self.starCountLable = starCountLable;
    
}

- (void)setDict:(NSDictionary *)dict {
    _dict = dict;
    
    [self.models enumerateObjectsUsingBlock:^(XLRealNameModel *model, NSUInteger idx, BOOL * _Nonnull stop) {
        if ([model.type_code isEqualToString:dict[@"type_code"]]) {
            self.starCountLable.text = [NSString stringWithFormat:@"+%d星星", model.type_value.intValue];
            self.stateImageView.image = [UIImage imageNamed:((model.status.intValue)) ? @"complete" : @"noComplete"];
            self.stateLable.textColor = (model.status.intValue) ? UIColor_Hex(0x10EB80) : UIColor_Hex(0xa0a0a0);
            self.stateLable.text = (model.status.intValue) ? @"已完成" : @"未完成";
            self.iconView.image = [UIImage imageNamed:dict[@"icon"]];
            self.typeTextLable.text = dict[@"text"];
            self.state = model.status.intValue;
            self.starCount = model.type_value.intValue;
            *stop = YES;
        }
    }];
 }


@end
