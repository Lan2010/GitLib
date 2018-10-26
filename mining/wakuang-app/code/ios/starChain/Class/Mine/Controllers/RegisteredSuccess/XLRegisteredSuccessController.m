//
//  XLRegisteredSuccessController.m
//  starChain
//
//  Created by rlx on 2018/6/11.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import "XLRegisteredSuccessController.h"
#import "XLButton.h"

@interface XLRegisteredSuccessController ()

@end

@implementation XLRegisteredSuccessController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    self.tj_navigationItem.title = @"注册成功";
    self.tj_navigationBar.tintColor = [UIColor whiteColor];
    self.titleColor = [UIColor whiteColor];
    self.navBarAlpha = YES;
    self.tj_navigationItem.leftBarButtonItems = nil;
    self.fd_interactivePopDisabled = YES;

    UIImageView *contentImageView = [[UIImageView alloc] init];
    [self.view insertSubview:contentImageView atIndex:0];
    contentImageView.userInteractionEnabled = YES;
    contentImageView.image = [UIImage imageNamed:@"loginBackground"];
    [contentImageView makeConstraints:^(MASConstraintMaker *make) {make.edges.offset(0);}];
    
    UIView *contentView = [UIView tj_WithSuperView:contentImageView];
    contentView.backgroundColor = [UIColor whiteColor];
    [contentView shearRoundedCornersWithRadiu:8];
    [contentView makeConstraints:^(MASConstraintMaker *make) {
        make.width.offset(ZOOM5(335));
        make.centerX.offset(0);
        make.top.offset(ZOOM5(167));
        make.height.offset(ZOOM5(390));
    }];
    
    UIImageView *iconImageView = [UIImageView tj_WithSuperView:contentView];
    iconImageView.image = [UIImage imageNamed:@"registerSucceed"];
    [iconImageView makeConstraints:^(MASConstraintMaker *make) {
        make.top.offset(ZOOM5(50));
        make.width.offset(ZOOM5(150));
        make.height.offset(ZOOM5(133));
        make.centerX.offset(0);
    }];
    
    NSLog(@"self.message = %@", self.message);
    
    UILabel *succeedLable = [UILabel lableWithSuperView:contentView fontSize:18 color:UIColor_Hex(0xAC56FA) title: (self.message.length) ? self.message : @"恭喜您，注册成功！" textAlignment:NSTextAlignmentLeft];
    [succeedLable makeConstraints:^(MASConstraintMaker *make) {
        make.centerX.offset(0);
        make.top.equalTo(iconImageView.bottom).offset(19);
    }];
    
    XLButton *experienceButton = [XLButton buttonWithSuperView:contentView fontSize:16 color:[UIColor whiteColor] title:@"立即体验"];
    [experienceButton setBackgroundImage:[UIImage imageNamed:@"gradientButtonBg"] forState:UIControlStateNormal];
    [experienceButton shearRoundedCornersWithRadiu:ZOOM5(45) * 0.5];
    [experienceButton addTarget:self action:@selector(didClickExperienceButton) forControlEvents:UIControlEventTouchUpInside];
    [experienceButton makeConstraints:^(MASConstraintMaker *make) {
        make.top.equalTo(succeedLable.bottom).offset(33);
        make.left.offset(18);
        make.right.offset(-18);
        make.height.offset(xl_bottomButtonH);
    }];
}


- (void)didClickExperienceButton {
    [self dismissToRootViewController];
}

- (UIStatusBarStyle)preferredStatusBarStyle {
    return UIStatusBarStyleLightContent;
}


@end
