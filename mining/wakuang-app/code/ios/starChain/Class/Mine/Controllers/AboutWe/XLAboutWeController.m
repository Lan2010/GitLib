//
//  XLAboutWeController.m
//  starChain
//
//  Created by rlx on 2018/7/2.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import "XLAboutWeController.h"

@interface XLAboutWeController ()

@end

@implementation XLAboutWeController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    self.view.backgroundColor = [UIColor whiteColor];
    
    UIImage *logoImage = [UIImage imageNamed:@"applogo"];
    UIImageView *logoView = [[UIImageView alloc] initWithImage:logoImage];
    [logoView shearRoundedCornersWithRadiu:8];
    [self.view addSubview:logoView];

    [logoView makeConstraints:^(MASConstraintMaker *make) {
        make.centerX.offset(0);
        make.top.equalTo(self.tj_navigationBar.bottom).offset(36);
        make.size.equalTo(logoImage.size);
    }];

    NSString *version = [NSBundle mainBundle].infoDictionary[@"CFBundleShortVersionString"];
    NSString *appName = [NSBundle mainBundle].infoDictionary[@"CFBundleDisplayName"];

    UILabel *versionLable = [UILabel lableWithSuperView:self.view fontSize:15 color:UIColor_Hex(0x6963c0) title:[NSString stringWithFormat:@"%@V%@", appName,version] textAlignment:NSTextAlignmentLeft];
    [versionLable makeConstraints:^(MASConstraintMaker *make) {
        make.centerX.equalTo(logoView);
        make.top.equalTo(logoView.bottom).offset(8);
    }];
//
//    NSString *introduce = @"捡星星APP由天智星产品团队开发，用区块链的方式将用户的个人信息价值及注意力权益通证化，把广告营销中原本分成给中介媒体或渠道的收益部分，转化为一种“星星”激励，利用线上+线下营销和算法匹配，贡献了场景数据和注意力的用户能够获取“星星”，这些“星星”可持续增值，可通过兑换、抽奖、拍卖等方式获取奖励。";
//    UILabel *introduceLable = [UILabel lableWithSuperView:self.view fontSize:15 color:UIColor_Hex(0x3f3e45) title:nil textAlignment:NSTextAlignmentLeft];
//    introduceLable.numberOfLines = 0;
//    introduceLable.attributedText = [introduce titleMargin:5];
//    [introduceLable makeConstraints:^(MASConstraintMaker *make) {
//        make.left.offset(H(23));
//        make.right.offset(H(-23));
//        make.top.equalTo(versionLable.bottom).offset(50);
//    }];
    
}


@end
