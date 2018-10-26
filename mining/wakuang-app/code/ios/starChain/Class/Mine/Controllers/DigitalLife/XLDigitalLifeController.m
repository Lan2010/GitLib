//
//  XLDigitalLifeController.m
//  starChain
//
//  Created by rlx on 2018/7/26.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import "XLDigitalLifeController.h"

@interface XLDigitalLifeController ()

@property (strong, nonatomic) NSMutableArray <UIImageView *>*imageViews;

@end

@implementation XLDigitalLifeController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    self.tj_navigationItem.title = @"";
    self.tj_navigationBar.tintColor = [UIColor whiteColor];
    self.titleColor = [UIColor whiteColor];
    self.navBarAlpha = YES;
    self.imageViews = [NSMutableArray arrayWithCapacity:4];
    
    UIImageView *contentImageView = [[UIImageView alloc] init];
    contentImageView.userInteractionEnabled = YES;
    [self.view insertSubview:contentImageView atIndex:0];
    contentImageView.image = [UIImage imageNamed:@"digitalLifeBg"];
    [contentImageView makeConstraints:^(MASConstraintMaker *make) {make.edges.offset(0);}];
    
    UIImage *identityImage = [UIImage imageNamed:@"shenfen"];
    UIImageView *identityImageView = [UIImageView tj_WithSuperView:contentImageView];
    identityImageView.image = identityImage;
    [identityImageView makeConstraints:^(MASConstraintMaker *make) {
        make.top.offset(H(230));
        make.right.equalTo(contentImageView.centerX).offset(H(-20));
        make.height.offset(H(identityImage.size.height));
        make.width.offset(H(identityImage.size.width));
    }];
    
    UIImage *interestImage = [UIImage imageNamed:@"interest"];
    UIImageView *interestImageView = [UIImageView tj_WithSuperView:contentImageView];
    interestImageView.image = interestImage;
    [interestImageView makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(contentImageView.centerX).offset(H(20));
        make.top.equalTo(identityImageView);
        make.height.width.equalTo(identityImageView);
    }];
    
    UIImage *physiologicalImage = [UIImage imageNamed:@"life"];
    UIImageView *physiologicalImageView = [UIImageView tj_WithSuperView:contentImageView];
    physiologicalImageView.image = physiologicalImage;
    [physiologicalImageView makeConstraints:^(MASConstraintMaker *make) {
        make.left.offset(H(49));
        make.top.equalTo(identityImageView.bottom).offset(H(30));
        make.height.offset(H(physiologicalImage.size.height));
        make.width.offset(H(physiologicalImage.size.width));
    }];
    
    UIImage *publicImage = [UIImage imageNamed:@"gongzhonghao"];
    UIImageView *publicImageView = [UIImageView tj_WithSuperView:contentImageView];
    publicImageView.image = publicImage;
    [publicImageView makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(interestImageView.right);
        make.top.width.height.equalTo(physiologicalImageView);
    }];
    
    UIButton *jumpButton = [UIButton tj_WithSuperView:contentImageView];
    [jumpButton addTarget:self action:@selector(didClickJumpButton) forControlEvents:UIControlEventTouchUpInside];
    [jumpButton makeConstraints:^(MASConstraintMaker *make) {
        make.left.offset(H(40));
        make.right.offset(H(-40));
        make.height.offset(H(50));
        make.top.equalTo(physiologicalImageView.bottom).offset(H(200));
    }];
    
    [self.imageViews addObject:identityImageView];
    [self.imageViews addObject:interestImageView];
    [self.imageViews addObject:physiologicalImageView];
    [self.imageViews addObject:publicImageView];
    
    dispatch_after(dispatch_time(DISPATCH_TIME_NOW, (int64_t)(0.5 * NSEC_PER_SEC)), dispatch_get_main_queue(), ^{
        [self.imageViews enumerateObjectsUsingBlock:^(UIImageView *obj, NSUInteger idx, BOOL * _Nonnull stop) {
            [self addMoveAnimationWithView:obj delay:(idx + 1) / 1.5];
        }];
    });
}

- (void)addMoveAnimationWithView:(UIView *)view delay:(CFTimeInterval)delay {
    
    CABasicAnimation *positionAnimation = [CABasicAnimation animationWithKeyPath:@"position"];
    positionAnimation.toValue = [NSValue valueWithCGPoint:CGPointMake(view.layer.position.x + H(20), view.layer.position.y + H(30))];
    positionAnimation.beginTime = CACurrentMediaTime() + delay;
    positionAnimation.duration = 3;
    positionAnimation.autoreverses = YES;
    positionAnimation.removedOnCompletion = NO;
    positionAnimation.fillMode = kCAFillModeForwards;
    positionAnimation.repeatCount = CGFLOAT_MAX;
    positionAnimation.timingFunction = [CAMediaTimingFunction functionWithName:kCAMediaTimingFunctionLinear];
    [view.layer addAnimation:positionAnimation forKey:@"position"];
}

- (void)didClickJumpButton {
    NSString *urlString = [USERDEFAULTS objectForKey:@"DigitalUrl"] ? [USERDEFAULTS objectForKey:@"DigitalUrl"] : @"";
    [self loadInteractionWebPageWithUrlString:urlString title:@"数字生命"];
}

- (UIStatusBarStyle)preferredStatusBarStyle {
    return UIStatusBarStyleLightContent;
}

@end
