//
//  XLAlertAdvertisingController.m
//  starChain
//
//  Created by rlx on 2018/6/29.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import "XLAlertAdvertisingController.h"

@interface XLAlertAdvertisingController ()

@property (weak, nonatomic) UIView *contentView;
@property (weak, nonatomic) UIView *advertisingMaskView;
@property (weak, nonatomic) UIButton *dismissButton;
@property (weak, nonatomic) UIImageView *advertisingIcon;

@end

@implementation XLAlertAdvertisingController

ControllerPresentationCustom

- (void)viewDidLoad {
    [super viewDidLoad];
    
    
    self.view.backgroundColor = [UIColor clearColor];
    
    UIView *advertisingMaskView = [[UIView alloc] initWithFrame:self.view.bounds];
    advertisingMaskView.backgroundColor = [[UIColor blackColor] colorWithAlphaComponent:0.5];
    [self.view addSubview:advertisingMaskView];
    advertisingMaskView.alpha = 0;
    
    UIView *contentView = [UIView tj_WithSuperView:advertisingMaskView];
    contentView.backgroundColor = [UIColor whiteColor];
    [contentView shearRoundedCornersWithRadiu:5];
    contentView.alpha = 0;
    _contentView = contentView;

    CGFloat width = H(294);
    CGFloat hight = H(324);
    contentView.frame = CGRectMake((KScreenWidth - width) * 0.5, (KScreenHeight - hight) * 0.5 - 20, width, hight);
    contentView.transform = CGAffineTransformMakeScale(0.5, 0.5);
    
    UIImageView *advertisingIcon = [UIImageView tj_WithSuperView:contentView];
    [advertisingIcon shearRoundedCornersWithRadiu:H(35)];
    [advertisingIcon makeConstraints:^(MASConstraintMaker *make) {
        make.centerX.offset(0);
        make.height.width.offset(H(70));
        make.top.offset(H(12));
    }];
    
    UILabel *titleLable = [UILabel lableWithSuperView:contentView fontSize:14 color:UIColor_Hex(0x3e384b) title:@"有了肯德基，生活好滋味" textAlignment:NSTextAlignmentCenter];
    [titleLable makeConstraints:^(MASConstraintMaker *make) {
        make.top.equalTo(advertisingIcon.bottom).offset(H(5));
        make.centerX.offset(0);
    }];
    
    UILabel *rewardCountLable = [UILabel lableWithSuperView:contentView fontSize:18 color:UIColor_Hex(0x201a2c) title:@"奖励1个星星" textAlignment:NSTextAlignmentCenter];
    [rewardCountLable makeConstraints:^(MASConstraintMaker *make) {
        make.top.equalTo(titleLable.bottom).offset(H(10));
        make.centerX.offset(0);
    }];
    
    UILabel *instructionsLable = [UILabel lableWithSuperView:contentView fontSize:12 color:UIColor_Hex(0x868496) title:@"已存入账户" textAlignment:NSTextAlignmentCenter];
    [instructionsLable makeConstraints:^(MASConstraintMaker *make) {
        make.top.equalTo(rewardCountLable.bottom).offset(H(5));
        make.centerX.offset(0);
    }];
    
    
    UIImageView *advertisingImageView = [UIImageView tj_WithSuperView:contentView];
    advertisingImageView.backgroundColor = [UIColor colorWithRed:240.0f/255.0f green:239.0f/255.0f blue:248.0f/255.0f alpha:1.0f];
    [advertisingImageView makeConstraints:^(MASConstraintMaker *make) {
        make.top.equalTo(instructionsLable).offset(H(22));
        make.left.right.bottom.offset(0);
    }];

    UIButton *dismissButton = [UIButton tj_WithSuperView:advertisingMaskView];
    dismissButton.alpha = 0;
    [dismissButton setImage:[UIImage imageNamed:@"bannerPreviewX_icon"] forState:UIControlStateNormal];
    [dismissButton addTarget:self action:@selector(didClickDismissButton) forControlEvents:UIControlEventTouchUpInside];
    [dismissButton makeConstraints:^(MASConstraintMaker *make) {
        make.centerX.offset(0);
        make.top.equalTo(contentView.bottom).offset(H(30));
        make.width.height.offset(42);
    }];
    
    _advertisingMaskView = advertisingMaskView;
    _dismissButton = dismissButton;
    
    TJLog(@"advertisingDict = %@", self.dict);
    
    [advertisingIcon sd_setImageWithURL:[NSURL URLWithString:self.dict[@"iconUrl"]]];
    titleLable.text = self.dict[@"title"];
    rewardCountLable.text = [NSString stringWithFormat:@"奖励%.2f个星星", [self.dict[@"rewardCount"] floatValue]];
    NSArray *AdUrls = self.dict[@"AdImageUrls"];
    [advertisingImageView sd_setImageWithURL:[NSURL URLWithString:AdUrls.firstObject[@"url"]]];

}

- (void)viewWillAppear:(BOOL)animated {
    [super viewWillAppear:animated];
    
    [UIView animateWithDuration:0.25 delay:0 usingSpringWithDamping:1 initialSpringVelocity:0.5 options:UIViewAnimationOptionTransitionNone animations:^{
        self.advertisingMaskView.alpha = 1;
        self.contentView.alpha = 1;
        self.contentView.transform = CGAffineTransformMakeScale(1, 1);
        self.dismissButton.alpha = 1;
    } completion:nil];
}

- (void)didClickDismissButton {
    
    [UIView animateWithDuration:0.25 animations:^{
        self.advertisingMaskView.alpha = 0;
        self.contentView.alpha = 0;
        self.dismissButton.alpha = 0;
    } completion:^(BOOL finished) {
        if (self.didDissmissButtonBlock) self.didDissmissButtonBlock();
        [self dismissViewControllerAnimated:NO completion:nil];
    }];
}


@end
