//
//  XLInvitationController.m
//  starChain
//
//  Created by rlx on 2018/6/14.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import "XLInvitationController.h"
#import "XLShareController.h"

@interface XLInvitationController ()
    
@property (weak, nonatomic) UILabel *invitationCodeLable;
@property (weak, nonatomic) UILabel *invitationCountLable;
@property (weak, nonatomic) UILabel *invitationLable;
@property (weak, nonatomic) UIImageView *backgroundImageView;
    
@end

@implementation XLInvitationController//这个界面要做缩放
    
- (void)viewDidLoad {
    [super viewDidLoad];
    
    self.tj_navigationItem.title = @"邀请好友";
    self.tj_navigationBar.tintColor = [UIColor whiteColor];
    self.titleColor = [UIColor whiteColor];
    self.navBarAlpha = YES;
    self.tj_navigationItem.rightBarButtonItem = [[UIBarButtonItem alloc] initWithImage:[UIImage imageNamed:@"icon_shara"] style:UIBarButtonItemStylePlain target:self action:@selector(didClickShareItem)];
    
    UIImageView *backgroundImageView = [[UIImageView alloc] init];
    backgroundImageView.userInteractionEnabled = YES;
    [self.view insertSubview:backgroundImageView atIndex:0];
    backgroundImageView.image = [UIImage imageNamed:@"fri_bg"];
    [backgroundImageView makeConstraints:^(MASConstraintMaker *make) {make.edges.offset(0);}];
    
    UIImageView *contentImageView = [UIImageView imageViewWithImageName:@"friend-bg" superView:backgroundImageView];
    contentImageView.userInteractionEnabled = YES;
    [backgroundImageView insertSubview:contentImageView atIndex:0];
    [contentImageView makeConstraints:^(MASConstraintMaker *make) {
        make.left.offset(H(18));
        make.right.offset(H(-18));
        make.top.offset(H(141));
        make.height.offset(H(505));
    }];
    
    NSString *appName = [NSBundle mainBundle].infoDictionary[@"CFBundleDisplayName"];
    UILabel *invitationLable = [UILabel lableWithSuperView:backgroundImageView fontSize:23 color:[UIColor whiteColor] title:[NSString stringWithFormat:@"%@邀请码", appName] textAlignment:NSTextAlignmentCenter];
    invitationLable.hidden = YES;
    [invitationLable makeConstraints:^(MASConstraintMaker *make) {
        make.centerX.offset(0);
        make.bottom.equalTo(contentImageView.top).offset(H(-45));
    }];
                                
    UIImageView *titleImageView = [UIImageView imageViewWithImageName:@"invitationTitle" superView:contentImageView];
    [titleImageView makeConstraints:^(MASConstraintMaker *make) {
        make.top.offset(H(31));
        make.centerX.offset(0);
    }];
    
    UILabel *invitationCodeLable = [UILabel lableWithSuperView:contentImageView fontSize:H(60) color:UIColor_Hex(0x6963C0) title:nil textAlignment:NSTextAlignmentLeft];
    invitationCodeLable.font = [UIFont boldSystemFontOfSize:H(60)];
    [invitationCodeLable makeConstraints:^(MASConstraintMaker *make) {
        make.top.equalTo(titleImageView.bottom).offset(H(25));
        make.centerX.offset(0);
        make.height.offset(H(45));
    }];
    
    UILabel *invitationCountLable = [UILabel lableWithSuperView:contentImageView fontSize:H(14) color:UIColor_Hex(0x777777) title:nil textAlignment:NSTextAlignmentLeft];
    [invitationCountLable makeConstraints:^(MASConstraintMaker *make) {
        make.top.equalTo(invitationCodeLable.bottom).offset(H(53));
        make.centerX.offset(0);
    }];
    
    NSString *instructionsString = [NSString stringWithFormat:@"每邀请一位好友下载并注册, 您和好友都将获得%d个星星", self.rewardCount];
    UILabel *instructionsLable = [UILabel lableWithSuperView:contentImageView fontSize:H(14) color:UIColor_Hex(0x303030) title:nil textAlignment:NSTextAlignmentCenter];
    instructionsLable.numberOfLines = 0;
    instructionsLable.attributedText = [instructionsString titleMargin:H(5) withAlignment:NSTextAlignmentCenter];
    [instructionsLable makeConstraints:^(MASConstraintMaker *make) {
        make.top.equalTo(invitationCountLable.bottom).offset(H(48));
        make.centerX.offset(0);
        make.left.offset(H(48));
        make.right.offset(H(-48));
    }];
//    
//    UIImageView *inviteCodeImageView = [UIImageView imageViewWithImageName:@"qianheziDownload.png" superView:contentImageView];
//    inviteCodeImageView.backgroundColor = [UIColor purpleColor];
//    [inviteCodeImageView makeConstraints:^(MASConstraintMaker *make) {
//        make.top.equalTo(instructionsLable.bottom).offset(H(27));
//        make.centerX.offset(0);
//        make.width.height.offset(H(120));
//    }];
//    
//    NSString *appName = [NSBundle mainBundle].infoDictionary[@"CFBundleDisplayName"];
//    UILabel *promptLable = [UILabel lableWithSuperView:contentImageView fontSize:H(13) color:UIColor_Hex(0xa0a0a0) title:[NSString stringWithFormat:@"扫码下载%@APP", appName] textAlignment:NSTextAlignmentLeft];
//    [promptLable makeConstraints:^(MASConstraintMaker *make) {
//        make.top.equalTo(inviteCodeImageView.bottom).offset(H(40));
//        make.centerX.offset(0);
//    }];
//    
//    UIView *leftLineView = [UIView tj_WithSuperView:contentImageView];
//    leftLineView.backgroundColor = UIColor_Hex(0xa0a0a0);
//    [leftLineView makeConstraints:^(MASConstraintMaker *make) {
//        make.right.equalTo(promptLable.left).offset(-12);
//        make.centerY.equalTo(promptLable);
//        make.height.offset(1);
//        make.width.offset(45);
//    }];
//    
//    UIView *rightLineView = [UIView tj_WithSuperView:contentImageView];
//    rightLineView.backgroundColor = UIColor_Hex(0xa0a0a0);
//    [rightLineView makeConstraints:^(MASConstraintMaker *make) {
//        make.left.equalTo(promptLable.right).offset(12);
//        make.height.width.centerY.equalTo(leftLineView);
//    }];
    
    self.invitationLable = invitationLable;
    self.backgroundImageView = backgroundImageView;
    self.invitationCodeLable = invitationCodeLable;
    self.invitationCountLable = invitationCountLable;
    self.invitationCodeLable.attributedText = [self.dataDict[@"code"] titleKernSpacing:7];
    self.invitationCountLable.text = [NSString stringWithFormat:@"已邀请%d个好友", [self.dataDict[@"count"] intValue]];
}
    
    
- (void)didClickShareItem {

    self.invitationLable.hidden = NO;
    self.invitationCountLable.hidden = YES;
    UIGraphicsBeginImageContextWithOptions(self.backgroundImageView.bounds.size , YES, 0);
    
    [self.backgroundImageView.layer renderInContext:UIGraphicsGetCurrentContext()];
    UIImage *image= UIGraphicsGetImageFromCurrentImageContext();
    UIGraphicsEndImageContext();
    self.invitationCountLable.hidden = NO;
    self.invitationLable.hidden = YES;
    
    XLShareController *shareController = [[XLShareController alloc] init];
    [shareController shareWithImage:image];
    [self presentViewController:shareController animated:NO completion:nil];
}
    
- (UIStatusBarStyle)preferredStatusBarStyle {
    return UIStatusBarStyleLightContent;
}
    
@end
