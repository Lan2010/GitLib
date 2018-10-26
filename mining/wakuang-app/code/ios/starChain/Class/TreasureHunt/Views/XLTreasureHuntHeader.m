//
//  XLTreasureHuntHeader.m
//  starChain
//
//  Created by rlx on 2018/6/8.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import "XLTreasureHuntHeader.h"

@interface XLTreasureHuntHeader()

@property (weak, nonatomic) UIView *contentView;

@end

@implementation XLTreasureHuntHeader

- (instancetype)initWithFrame:(CGRect)frame {
    self = [super initWithFrame:frame];
    if (self) {
        
        UIImageView *contentView = [[UIImageView alloc] initWithFrame:self.bounds];
        contentView.userInteractionEnabled = YES;
        contentView.image = [UIImage imageNamed:@"home_bg"];
        [self addSubview:contentView];
        self.contentView = contentView;
        
        UIView *noteView = [UIView tj_WithSuperView:self];
        noteView.backgroundColor = UIColor_Hex(0x6a34a1);
        [noteView makeConstraints:^(MASConstraintMaker *make) {
            make.top.offset(30);
            make.left.right.offset(0);
            make.height.offset(30);
        }];
        
        UIImageView *noteImageView = [UIImageView tj_WithSuperView:noteView];
        UIImage *noteImage = [UIImage imageNamed:@"icon_horn"];
        noteImageView.image = noteImage;
 
        UILabel *noteLable = [UILabel lableWithSuperView:noteView fontSize:13 color:[UIColor whiteColor] title:@"车公庙广场任务进行中" textAlignment:NSTextAlignmentLeft];
        [noteLable makeConstraints:^(MASConstraintMaker *make) {
            make.centerY.offset(0);
            make.centerX.offset(-noteImage.size.width * 0.5 - 6);
        }];
        
        [noteImageView makeConstraints:^(MASConstraintMaker *make) {
            make.centerY.offset(0);
            make.right.equalTo(noteLable.left).offset(-12);
            make.size.equalTo(noteImage.size);
        }];
        
        UIImageView *starImageView = [UIImageView tj_WithSuperView:contentView];
        UIImage *starImage = [UIImage imageNamed:@"star1"];
        starImageView.image = starImage;
        [starImageView makeConstraints:^(MASConstraintMaker *make) {
            make.top.equalTo(noteView.bottom).offset(27);
            make.left.offset(14);
            make.size.equalTo(starImage.size);
        }];
        
        UILabel *starLale = [UILabel lableWithSuperView:contentView fontSize:14 color:UIColor_Hex(0xFFA05C) title:@"星星 1000" textAlignment:NSTextAlignmentLeft];
        [starLale makeConstraints:^(MASConstraintMaker *make) {
            make.left.equalTo(starImageView.right).offset(9);
            make.centerY.equalTo(starImageView);
        }];
        
        //星链攻略
        UIImageView *strategyImageView = [UIImageView tj_WithSuperView:contentView];
        UIImage *strategyImage = [UIImage imageNamed:@"Strategy"];
        strategyImageView.image = strategyImage;
        [strategyImageView makeConstraints:^(MASConstraintMaker *make) {
            make.centerY.equalTo(starImageView);
            make.right.offset(-14);
            make.size.equalTo(strategyImage.size);
        }];
        
        UILabel *strategyLale = [UILabel lableWithSuperView:strategyImageView fontSize:12 color:[UIColor whiteColor] title:@"星链攻略" textAlignment:NSTextAlignmentLeft];
        strategyLale.numberOfLines = 0;
        [strategyLale makeConstraints:^(MASConstraintMaker *make) {
            make.width.height.offset(38);
            make.centerY.offset(0);
            make.centerX.offset(7);
        }];
        
        UIImageView *satelliteImageView = [UIImageView tj_WithSuperView:contentView];
        UIImage *satelliteImage = [UIImage imageNamed:@"Satellite1"];
        satelliteImageView.image = satelliteImage;
        [satelliteImageView makeConstraints:^(MASConstraintMaker *make) {
            make.top.equalTo(strategyImageView.bottom);
            make.right.equalTo(strategyImageView);
            make.size.equalTo(satelliteImage.size);
        }];
        
        _exploreView = [[XLExploreView alloc] initWithFrame:CGRectMake((self.tj_width - 125) * 0.5, (self.tj_height - 125) * 0.5, 125, 125)];
        [contentView addSubview:_exploreView];
        [_exploreView makeConstraints:^(MASConstraintMaker *make) {
            make.center.offset(0);
            make.width.height.offset(125);
        }];
        
        UILabel *updateTimeLable = [UILabel lableWithSuperView:contentView fontSize:12 color:[UIColor tj_mainColor] title:@"2018.1.1 10点更新" textAlignment:NSTextAlignmentLeft];
        updateTimeLable.numberOfLines = 0;
        [updateTimeLable makeConstraints:^(MASConstraintMaker *make) {
            make.bottom.offset(-10);
            make.centerX.offset(0);
        }];
        
        UIImageView *assetRankingImageView = [UIImageView tj_WithSuperView:contentView];
        UIImage *assetRankingImage = [UIImage imageNamed:@"assetRanking"];
        assetRankingImageView.image = assetRankingImage;
        [assetRankingImageView makeConstraints:^(MASConstraintMaker *make) {
            make.bottom.equalTo(updateTimeLable.top).offset(-9);
            make.centerX.offset(0);
            make.size.equalTo(assetRankingImage.size);
        }];
 
    }
    return self;
}

 
@end
