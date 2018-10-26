//
//  XLTaskView.m
//  starChain
//
//  Created by rlx on 2018/7/6.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import "XLTaskView.h"
#import "XLTaskModel.h"

@interface XLTaskView()

@property (weak, nonatomic) UILabel *placeLable;
@property (weak, nonatomic) UILabel *distanceLable;
@property (weak, nonatomic) UILabel *dateLable;
@property (weak, nonatomic) UIImageView *iconView;
@property (weak, nonatomic) UILabel *starCountLable;

@property (strong, nonatomic) NSDateFormatter *dateFormatter;



@end

@implementation XLTaskView

- (instancetype)initWithFrame:(CGRect)frame {
    if (self = [super initWithFrame:frame]) {
        [self addSubView];
    }
    return self;
}

- (void)addSubView {
    
    UIImageView *iconView = [UIImageView imageViewWithImageName:@"" superView:self];
    [iconView shearRoundedCornersWithRadiu:11];
    [iconView makeConstraints:^(MASConstraintMaker *make) {
        make.width.height.offset(22);
        make.left.offset(ZOOM5(21));
        make.top.offset(14);
    }];
    
    UILabel *placeLable = [UILabel lableWithSuperView:self fontSize:14 color:UIColor_Hex(0x201A2C) title:@"" textAlignment: NSTextAlignmentLeft];
    placeLable.font = [UIFont boldSystemFontOfSize:16];
    [placeLable makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(iconView.right).offset(8);
        make.centerY.equalTo(iconView);
    }];
    
    UIImageView *locationIconView = [UIImageView imageViewWithImageName:@"icon-location" superView:self];
    [locationIconView makeConstraints:^(MASConstraintMaker *make) {
        make.top.equalTo(placeLable.bottom).offset(13);
        make.width.offset(12.5);
        make.height.offset(15);
        make.centerX.equalTo(iconView);
    }];
    
    UILabel *distanceLable = [UILabel lableWithSuperView:self fontSize:16 color:UIColor_Hex(0x2F1959) title:@"" textAlignment:NSTextAlignmentLeft];
    [distanceLable makeConstraints:^(MASConstraintMaker *make) {
        make.bottom.equalTo(locationIconView).offset(2);
        make.left.equalTo(locationIconView.right).offset(6);
    }];
    
    UIImageView *starImageView = [UIImageView tj_WithSuperView:self];
    UIImage *starImage = [UIImage imageNamed:@"icon-star"];
    starImageView.image = starImage;
    [starImageView makeConstraints:^(MASConstraintMaker *make) {
        make.centerY.equalTo(locationIconView);
        make.size.equalTo(starImage.size);
        make.centerX.offset(-(starImage.size.width * 0.5));
    }];
    
    UILabel *starCountLable = [UILabel lableWithSuperView:self fontSize:15 color:[UIColor blackColor]  title:@"" textAlignment:NSTextAlignmentLeft];
    [starCountLable makeConstraints:^(MASConstraintMaker *make) {
        make.centerY.equalTo(locationIconView).offset(2);
        make.left.equalTo(starImageView.right).offset(3);
    }];
    
    UILabel *dateLable = [UILabel lableWithSuperView:self fontSize:12 color:UIColor_Hex(0xa8a6de) title:@"" textAlignment:NSTextAlignmentLeft];
    [dateLable makeConstraints:^(MASConstraintMaker *make) {
        make.top.equalTo(distanceLable.bottom).offset(14);
        make.left.equalTo(locationIconView);
    }];
    
    self.iconView = iconView;
    self.placeLable = placeLable;
    self.dateLable = dateLable;
    self.distanceLable = distanceLable;
    self.starCountLable = starCountLable;
}

- (void)setModel:(XLTaskModel *)model {
    _model = model;
    
    self.dateLable.text = [NSString stringWithFormat:@"%@至%@", [model.begin_time substringFromIndex:6], [model.end_time substringFromIndex:6]];
    [self.iconView sd_setImageWithURL:[NSURL URLWithString:model.task_lcon] placeholderImage:[UIImage imageNamed:@"slogon"]];
    self.placeLable.text = model.task_name;
    
    CGFloat distance = model.distance.floatValue;
    self.distanceLable.text = [NSString stringWithFormat:@"%.2fkm", distance / 1000.0];
    self.starCountLable.text = [NSString stringWithFormat:@"x%@", model.task_award];
    
}

- (NSDateFormatter *)dateFormatter {
    if (!_dateFormatter) {
        _dateFormatter = [[NSDateFormatter alloc] init];
        [_dateFormatter setDateFormat:@"yyyy-MM-dd HH:mm"];
    }
    return _dateFormatter;
}

 
@end
