//
//  XLHomeCell.m
//  starChain
//
//  Created by rlx on 2018/6/8.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import "XLHomeCell.h"


@interface XLHomeCell()

@property (weak, nonatomic) UIImageView *starImageView;
@property (weak, nonatomic) UILabel *rankingLable;
@property (weak, nonatomic) UILabel *nameLable;
@property (weak, nonatomic) UILabel *starCountLable;
@property (strong, nonatomic) CAGradientLayer *gradientLayer;

@end

@implementation XLHomeCell


- (instancetype)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier {
    if (self = [super initWithStyle:style reuseIdentifier:reuseIdentifier]) {
        [self addSubView];
    }
    return self;
}

- (void)addSubView {
    
//    CAGradientLayer *gradientLayer = [CAGradientLayer layer];
//    gradientLayer.frame = CGRectMake(0, 0, KScreenWidth, 44);
//    [self.contentView.layer insertSublayer:gradientLayer atIndex:0];
//    gradientLayer.startPoint = CGPointMake(0, 0);
//    gradientLayer.endPoint = CGPointMake(1, 0);
 
//    UIImageView *starImageView = [UIImageView tj_WithSuperView:self.contentView];
//    UIImage *starImage = [UIImage imageNamed:@"star1"];
//    starImageView.image = starImage;
//    starImageView.hidden = YES;
//    [starImageView makeConstraints:^(MASConstraintMaker *make) {
//        make.centerY.offset(0);
//        make.left.offset(24);
//        make.size.equalTo(starImage.size);
//    }];
    
    CGFloat W = (KScreenWidth - 12 * 2) / 3;
    
    UILabel *rankingLable = [UILabel lableWithSuperView:self.contentView fontSize:15 color:[UIColor whiteColor] title:@"25" textAlignment:NSTextAlignmentCenter];
    [rankingLable makeConstraints:^(MASConstraintMaker *make) {
        make.centerY.offset(0);
        make.left.offset(12);
        make.width.offset(W - 40);
    }];
    
    UILabel *nameLable = [UILabel lableWithSuperView:self.contentView fontSize:15 color:[UIColor whiteColor] title:@"ELin" textAlignment:NSTextAlignmentRight];
    [nameLable makeConstraints:^(MASConstraintMaker *make) {
        make.centerY.offset(0);
        make.left.equalTo(rankingLable.right);
        make.width.offset(W + 20);
    }];
    
    UILabel *starCountLable = [UILabel lableWithSuperView:self.contentView fontSize:15 color:[UIColor whiteColor] title:@"1000.8" textAlignment:NSTextAlignmentRight];
    [starCountLable makeConstraints:^(MASConstraintMaker *make) {
        make.width.equalTo(nameLable);
        make.right.offset(-12);
        make.centerY.offset(0);
    }];
    
    self.selectionStyle = UITableViewCellSelectionStyleNone;
    
    self.starCountLable = starCountLable;
    self.nameLable = nameLable;
    self.rankingLable = rankingLable;
    
    self.contentView.backgroundColor = [UIColor whiteColor];
    self.rankingLable.textColor = UIColor_Hex(0x201a2c);
    self.nameLable.textColor = UIColor_Hex(0x201a2c);
    self.starCountLable.textColor = UIColor_Hex(0x201a2c);
    
}

- (void)setRow:(NSInteger)row {
    _row = row;
}

- (void)setDict:(NSDictionary *)dict {
    _dict = dict;
    self.rankingLable.text = dict[@"num"];
    self.nameLable.text = dict[@"phone"];
    self.starCountLable.text = dict[@"starPoint"];
}

@end
