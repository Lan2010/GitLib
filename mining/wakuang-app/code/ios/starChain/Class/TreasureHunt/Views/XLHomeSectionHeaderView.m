//
//  XLHomeSectionHeaderView.m
//  starChain
//
//  Created by rlx on 2018/6/8.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import "XLHomeSectionHeaderView.h"

@implementation XLHomeSectionHeaderView


- (instancetype)initWithReuseIdentifier:(NSString *)reuseIdentifier {
    if (self = [super initWithReuseIdentifier:reuseIdentifier]) {
        [self addSubView];
    }
    return self;
}

- (void)addSubView {
    
    self.contentView.backgroundColor = UIColor_Hex(0xf4f3fc);
    
    UIView *rewardContenView = [UIView tj_WithSuperView:self.contentView];
    rewardContenView.backgroundColor = [UIColor whiteColor];
    [rewardContenView makeConstraints:^(MASConstraintMaker *make) {
        make.top.offset(8);
        make.left.right.offset(0);
        make.height.offset(50);
    }];
    
    UILabel *rewardLable = [UILabel lableWithSuperView:rewardContenView fontSize:16 color:UIColor_RGB(32, 26, 44) title:@"奖励排行榜" textAlignment:NSTextAlignmentLeft];
    rewardLable.font = [UIFont boldSystemFontOfSize:16];
    [rewardLable makeConstraints:^(MASConstraintMaker *make) {
        make.left.offset(18);
        make.centerY.offset(0);
    }];
    
    UIView *infoContenView = [UIView tj_WithSuperView:self.contentView];
    infoContenView.backgroundColor = UIColor_Hex(0xf4f3fc);
    [infoContenView makeConstraints:^(MASConstraintMaker *make) {
        make.top.equalTo(rewardContenView.bottom);
        make.left.right.bottom.offset(0);
    }];
 
    UILabel *rankingLable = [UILabel lableWithSuperView:infoContenView fontSize:13 color:UIColor_Hex(0x868496) title:@"名次" textAlignment:NSTextAlignmentLeft];
    [rankingLable makeConstraints:^(MASConstraintMaker *make) {
        make.left.offset(24);
        make.centerY.offset(0);
    }];
    
    UILabel *userLable = [UILabel lableWithSuperView:infoContenView fontSize:13 color:UIColor_Hex(0x868496) title:@"用户" textAlignment:NSTextAlignmentLeft];
    [userLable makeConstraints:^(MASConstraintMaker *make) {
        make.center.offset(0);
    }];
    
    UILabel *starLable = [UILabel lableWithSuperView:infoContenView fontSize:13 color:UIColor_Hex(0x868496) title:@"星星" textAlignment:NSTextAlignmentLeft];
    [starLable makeConstraints:^(MASConstraintMaker *make) {
        make.right.offset(-24);
        make.centerY.offset(0);
    }];

}

@end
