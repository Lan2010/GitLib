//
//  XLStarDetailsController.m
//  starChain
//
//  Created by rlx on 2018/7/5.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import "XLStarDetailsController.h"
#import "ShoppingCartTool.h"
#import "UIView+Boom.h"


@interface XLStarDetailsController ()

@property (weak, nonatomic) UIImageView *adbackgroundImageView;
@property (weak, nonatomic) UIImageView *boxImageView;
@property (weak, nonatomic) UIImageView *taskImageView;
@property (weak, nonatomic) UIImageView *linkStarImageView;
@property (weak, nonatomic) UIImageView *taskStarBackgroundImageView;
@property (weak, nonatomic) UIImageView *linkStarBackgroundImageView;
@property (weak, nonatomic) UIButton *openButton;
@property (weak, nonatomic) UIImageView *starImageView;
@property (weak, nonatomic) UIView *starContentView;
@property (weak, nonatomic) UILabel *starCountLable;
@property (weak, nonatomic) UILabel *starTitleLable;
@property (weak, nonatomic) UIView *moveAnimationView;


@property (strong, nonatomic) CAKeyframeAnimation *scaleAnim;
@property (strong, nonatomic) UIImage *paopaoImage;
@property (strong, nonatomic) NSTimer *timer;

@property (assign, nonatomic) NSInteger topMargin;
@property (assign, nonatomic) NSInteger seconds;

@property (strong, nonatomic) CAShapeLayer *moveAnimationLayer;
@property (assign, nonatomic) int adStarPointType;//0浏览, 1点击


@end

@implementation XLStarDetailsController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    self.fd_interactivePopDisabled = YES;
    self.tj_navigationBar.tintColor = [UIColor whiteColor];
    self.titleColor = [UIColor whiteColor];
    self.navBarAlpha = YES;
    self.tj_navigationItem.leftBarButtonItems = [UIBarButtonItem backBarItemImage:[UIImage imageNamed:@""] target:nil action:nil];
    
    [self addSubView];
}

- (void)addSubView {
    
    TJLog(@"annotation = %@", self.annotation);
    
    UIImageView *contentImageView = [[UIImageView alloc] init];
    contentImageView.userInteractionEnabled = YES;
    [self.view insertSubview:contentImageView atIndex:0];
    contentImageView.image = [UIImage imageNamed:@"bg-xb"];
    [contentImageView makeConstraints:^(MASConstraintMaker *make) {make.edges.offset(0);}];
    
    UIImage *boxImage = [UIImage imageNamed:@"box"];
    UIImageView *boxImageView = [[UIImageView alloc] initWithImage:boxImage];
    self.boxImageView = boxImageView;
    boxImageView.clipsToBounds = YES;
    boxImageView.userInteractionEnabled = YES;
    [contentImageView addSubview:boxImageView];
    [boxImageView makeConstraints:^(MASConstraintMaker *make) {
        make.width.offset(H(boxImage.size.width));
        make.height.offset(H(boxImage.size.height));
        make.top.offset(H(61));
        make.centerX.offset(0);
    }];
    
    UIButton *openButton = [UIButton tj_WithSuperView:boxImageView];
    [openButton setTitleColor:[UIColor orangeColor] forState:UIControlStateNormal];
    [openButton makeConstraints:^(MASConstraintMaker *make) {
        make.centerX.offset(0);
        make.centerY.offset(H(-19));
        make.width.height.offset(30);
    }];
    
    UIImageView *adbackgroundImageView = [[UIImageView alloc] init];;
    [contentImageView addSubview:adbackgroundImageView];
    adbackgroundImageView.userInteractionEnabled = YES;
    
    UILabel *adTitleLable = [UILabel lableWithSuperView:adbackgroundImageView fontSize:18 color:[UIColor whiteColor] title:self.annotation.advertName  textAlignment:NSTextAlignmentLeft];
    [adTitleLable makeConstraints:^(MASConstraintMaker *make) {
        make.top.offset(H(27));
        make.centerX.offset(0);
        make.height.offset(17);
    }];
    
    UIImageView *adImageView = [UIImageView tj_WithSuperView:adbackgroundImageView];
 
    if (self.annotation.adImage) {
        adImageView.image = self.annotation.adImage;
    } else {
        [adImageView sd_setImageWithURL:[NSURL URLWithString:self.annotation.adImageUrl]];
    }
    
    UIImage *adbackgroundImage = ([self.annotation.adLink replacingEmptyString].length) ? [UIImage imageNamed:@"bg-ad-btn"] : [UIImage imageNamed:@"bg-ad"];
    adbackgroundImageView.image = adbackgroundImage;
    [adbackgroundImageView makeConstraints:^(MASConstraintMaker *make) {
        make.top.equalTo(boxImageView.bottom).offset(H(15));
        make.centerX.offset(0);
        make.height.offset( H(adbackgroundImage.size.height));
        make.width.offset(H(adbackgroundImage.size.width));
    }];
    
    [adImageView makeConstraints:^(MASConstraintMaker *make) {
        make.top.offset(H(59));
        make.centerX.offset(0);
        make.height.offset(H(189));
        make.width.offset(H(310));
    }];
    
    UILabel *advertisingLable = [UILabel lableWithSuperView:adbackgroundImageView fontSize:14 color:[UIColor whiteColor] title:@"广告" textAlignment:NSTextAlignmentCenter];
    advertisingLable.layer.borderWidth = 0.5;
    advertisingLable.layer.borderColor = [UIColor whiteColor].CGColor;
    [advertisingLable makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(adImageView);
        make.top.equalTo(adImageView.bottom).offset(H(10));
        make.width.offset(37);
        make.height.offset(20);
    }];
    
    UILabel *advertisingTextLable = [UILabel lableWithSuperView:adbackgroundImageView fontSize:14 color:[UIColor whiteColor] title:self.annotation.advertRemark textAlignment:NSTextAlignmentLeft];
    [advertisingTextLable makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(advertisingLable.right).offset(6);
        make.right.equalTo(adImageView);
        make.centerY.equalTo(advertisingLable);
        make.height.offset(14);
    }];

    UIImageView *taskStarBackgroundImageView = [[UIImageView alloc] init];
    taskStarBackgroundImageView.hidden = YES;
    [boxImageView addSubview:taskStarBackgroundImageView];
    [taskStarBackgroundImageView addTapGesturesWithTarget:self action:@selector(tapTaskStarBackgroundImageView)];
    taskStarBackgroundImageView.userInteractionEnabled = YES;
    
    UILabel *starTitleLable = [UILabel lableWithSuperView:taskStarBackgroundImageView fontSize:11 color:UIColor_Hex(0x7e2d00) title:self.annotation.starCount textAlignment:NSTextAlignmentCenter];
    starTitleLable.backgroundColor = UIColor_RGB(255, 250, 180);
    [starTitleLable shearRoundedCornersWithRadiu:17 * 0.5];
    [starTitleLable makeConstraints:^(MASConstraintMaker *make) {
        make.top.left.right.offset(0);
        make.height.offset(17);
    }];
    
    UIImageView *taskImageView = [[UIImageView alloc] initWithImage:[UIImage imageNamed:@"star-big"]];
    [taskStarBackgroundImageView addSubview:taskImageView];
    [taskImageView makeConstraints:^(MASConstraintMaker *make) {
        make.centerX.offset(0);
        make.top.offset(17);
        make.height.width.offset(H(54));
    }];
    
    [taskStarBackgroundImageView makeConstraints:^(MASConstraintMaker *make) {
        make.centerX.offset(0);
        make.top.offset(H(40));
        make.width.offset(58);
        make.height.offset(H(54) + 17);
    }];

    self.starTitleLable = starTitleLable;
    self.taskStarBackgroundImageView = taskStarBackgroundImageView;
    self.seconds = 5;
    self.openButton = openButton;
    self.taskImageView = taskImageView;
    self.adbackgroundImageView = adbackgroundImageView;
    
    [openButton setTitle:@(self.seconds).description forState:UIControlStateNormal];
    
    [self addTimer];
    [self addStarCountContentView];
    [self addLinkStarContetView];
    
}

- (void)addLinkStarContetView {
    
    self.linkStarBackgroundImageView = nil;
    
    if ([self.annotation.adLink replacingEmptyString].length) {
        
        UIImageView *linkStarBackgroundImageView = [[UIImageView alloc] init];
        linkStarBackgroundImageView.hidden = YES;
        [self.adbackgroundImageView addSubview:linkStarBackgroundImageView];
        [linkStarBackgroundImageView addTapGesturesWithTarget:self action:@selector(tapLinkStarBackgroundImageView)];
        linkStarBackgroundImageView.userInteractionEnabled = YES;
        CGFloat rightMagrin = (KScreenHeight < 667) ? H(-78) : H(-84);
        [linkStarBackgroundImageView makeConstraints:^(MASConstraintMaker *make) {
            make.right.offset(rightMagrin);
            make.top.offset(H(290 - 5));
            make.width.offset(58);
            make.height.offset(H(35) + 17);
        }];
        
        UILabel *linkStarTitleLable = [UILabel lableWithSuperView:linkStarBackgroundImageView fontSize:11 color:UIColor_Hex(0x7e2d00) title:self.annotation.linkStarPoint textAlignment:NSTextAlignmentCenter];
        linkStarTitleLable.backgroundColor = UIColor_RGB(255, 250, 180);
        [linkStarTitleLable shearRoundedCornersWithRadiu:17 * 0.5];
        [linkStarTitleLable makeConstraints:^(MASConstraintMaker *make) {
            make.top.offset(0);
            make.left.right.offset(0);
            make.height.offset(17);
        }];
        
        UIImageView *linkStarImageView = [[UIImageView alloc] initWithImage:[UIImage imageNamed:@"star-big"]];
        [linkStarBackgroundImageView addSubview:linkStarImageView];
        [linkStarImageView makeConstraints:^(MASConstraintMaker *make) {
            make.centerX.offset(0);
            make.top.offset(17);
            make.width.height.offset(H(35));
        }];
        
        UIButton *lookButton = [UIButton tj_WithSuperView:self.adbackgroundImageView];
        [lookButton addTarget:self action:@selector(didClickLookButton) forControlEvents:UIControlEventTouchUpInside];
        [lookButton makeConstraints:^(MASConstraintMaker *make) {
            make.left.offset(H(89));
            make.right.offset(H(-89));
            make.height.offset(H(50));
            make.top.offset(H(290));
        }];
        
        self.linkStarImageView = linkStarImageView;
        self.linkStarBackgroundImageView = linkStarBackgroundImageView;
    }
}

- (void)addStarCountContentView {
    
    UIImage *starImage = [UIImage imageNamed:@"icon-star"];
    self.availableStarPointString = (self.availableStarPointString.floatValue) ? self.availableStarPointString : @"0";
    CGFloat width = [self.availableStarPointString getStringSizeWithWidth:KScreenWidth fontSize:15].width;
    width = width + starImage.size.width + 13;
    if (!self.availableStarPointString.floatValue) [self loadTotalStarCountData];
    
    UIButton *starContentView = [UIButton tj_WithSuperView:self.view];
    starContentView.backgroundColor = [UIColor_Hex(0xb32f1959) colorWithAlphaComponent:0.8];
    [starContentView shearRoundedCornersWithRadiu:11];
    [starContentView makeConstraints:^(MASConstraintMaker *make) {
        make.right.offset(-14);
        make.top.offset(StatusBarHight + (44 - 22) * 0.5);
        make.width.offset(width);
        make.height.offset(22);
    }];
    
    UIImageView *starImageView = [UIImageView tj_WithSuperView:starContentView];
    starImageView.userInteractionEnabled = NO;
    starImageView.image = starImage;
    self.starImageView = starImageView;
    [starImageView makeConstraints:^(MASConstraintMaker *make) {
        make.centerY.offset(0);
        make.left.offset(6);
        make.size.equalTo(starImage.size);
    }];
    
    UILabel *starCountLable = [UILabel lableWithSuperView:starContentView fontSize:14 color:[UIColor whiteColor] title:self.availableStarPointString textAlignment:NSTextAlignmentLeft];
    [starCountLable makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(starImageView.right).offset(4);
        make.centerY.offset(0);
        make.right.offset(-2);
    }];
    
    self.starContentView = starContentView;
    self.starCountLable = starCountLable;
    
}


- (void)didClickLookButton {
    [self tapLinkStarBackgroundImageView];
}

- (void)tapLinkStarBackgroundImageView {
    
    if (self.seconds) return;
    
    if (!self.linkStarBackgroundImageView) {
        [self jumpLink];
        return;
    }
 
    self.adStarPointType = 1;
    self.linkStarBackgroundImageView.userInteractionEnabled = NO;
    self.linkStarImageView.userInteractionEnabled = NO;
    CGRect rect = [self.linkStarBackgroundImageView convertRect:self.linkStarImageView.frame toView:self.view];
    CGPoint startPoint = CGPointMake(rect.origin.x + rect.size.width * 0.5, rect.origin.y + rect.size.height * 0.5);
    
    [self loadChargelinkStarDataWithAnnotation: _annotation complete:^(NSString *availableStarPoint){
        
        [self.linkStarBackgroundImageView removeFromSuperview];
        [self.mapView removeAnnotation:self.annotation];
        
        [self chargeStarCompleteWithStartPoint:startPoint starPoint:availableStarPoint animationComplete:^{
            [self jumpLink];
        }];
    }];
}

- (void)tapTaskStarBackgroundImageView {
    
    self.taskStarBackgroundImageView.userInteractionEnabled = NO;
    self.adStarPointType = 0;
    CGRect rect = [self.taskStarBackgroundImageView convertRect:self.taskImageView.frame toView:self.view];
    CGPoint startPoint = CGPointMake(rect.origin.x + rect.size.width * 0.5, rect.origin.y + rect.size.height * 0.5);

    if (self.annotation.starType == XLStarTypeAD) {
        
        [self loadChargeAdvertisingStarDataWithAnnotation: _annotation complete:^(NSString *availableStarPoint){
            
            [self.taskStarBackgroundImageView removeFromSuperview];
            [self.mapView removeAnnotation:self.annotation];
            [self chargeStarCompleteWithStartPoint:startPoint starPoint:availableStarPoint animationComplete:nil];
        }];
        
    } else {
        [self loadChargeTaskStarDataWithAnnotation: _annotation complete:^(NSString *availableStarPoint){
            
            [self.taskStarBackgroundImageView removeFromSuperview];
            [self.mapView removeAnnotation:self.annotation];
            
            [self chargeStarCompleteWithStartPoint:startPoint starPoint:availableStarPoint animationComplete:nil];
        }];
    }
}

#pragma mark - 收取星星完成
- (void)chargeStarCompleteWithStartPoint:(CGPoint)startPoint starPoint:(NSString *)starPoint animationComplete:(void (^)(void))animationComplete {

    CGRect starImageViewRect = [self.starContentView convertRect:self.starImageView.frame toView:self.view];
    CGPoint endPoint = CGPointMake(starImageViewRect.origin.x + starImageViewRect.size.width * 0.5, starImageViewRect.origin.y + starImageViewRect.size.height * 0.5);
    
    [ShoppingCartTool addToShoppingCartWithGoodsImage:[UIImage imageNamed:@"star-big"] startPoint:startPoint endPoint:endPoint start:^(CAShapeLayer *moveAnimationLayer, UIView *moveAnimationView) {
        self.moveAnimationLayer = moveAnimationLayer;
        self.moveAnimationView = moveAnimationView;
    } completion:^(BOOL finish) {
        
        CGFloat width = 85;
        width = [self calculateStarCountLableWidthWithStarPoint:starPoint];
        self.starCountLable.text = starPoint;
        [self.starContentView updateConstraints:^(MASConstraintMaker *make) {
            make.width.offset(width);
        }];
        if (animationComplete) animationComplete();
    }];
 
}

#pragma mark - 收集广告星星
- (void)loadChargeAdvertisingStarDataWithAnnotation:(XLStarAnnotation *)annotation complete:(void (^)(NSString *))complete {
    
    NSDictionary *parameters = @{
                                 @"advertId": annotation.advertId,
                                 @"starPoint": annotation.starCount,
                                 @"adStarPointType": @(self.adStarPointType)
                                 };
    [self loadChargeStarDataWithUrlString:@"Account.ClickAd" parameters:parameters annotation:annotation complete:complete];
}


#pragma mark - 收集链接广告星星
- (void)loadChargelinkStarDataWithAnnotation:(XLStarAnnotation *)annotation complete:(void (^)(NSString *))complete {
    
    NSLog(@" annotation.starPoint = %@", annotation.linkStarPoint);
    
    NSDictionary *parameters = @{
                                 @"advertId": annotation.advertId,
                                 @"starPoint": annotation.linkStarPoint,
                                 @"adStarPointType": @(self.adStarPointType)
                                 };
    [self loadChargeStarDataWithUrlString:@"Account.ClickAd" parameters:parameters annotation:annotation complete:complete];
}


#pragma mark - 收集任务星星和随机星星
- (void)loadChargeTaskStarDataWithAnnotation:(XLStarAnnotation *)annotation complete:(void (^)(NSString *))complete {
    NSString *taskId = annotation.taskId;
    
    TJLog(@"taskId = %@", taskId);
    
    taskId = (!taskId.intValue) ? @"" : taskId;
    NSDictionary *parameters = @{
                                 @"advertId": annotation.advertId,
                                 @"recordToken": annotation.recordToken,
                                 @"taskId": taskId
                                 };
    [self loadChargeStarDataWithUrlString:@"Account.Collection" parameters:parameters annotation:annotation complete:complete];
}

#pragma mark - 收取星星最终接口
- (void)loadChargeStarDataWithUrlString:(NSString *)urlString
                             parameters:(NSDictionary *)parameters
                             annotation:(XLStarAnnotation *)annotation
                               complete:(void (^)(NSString *))complete {
    [TJNetworkTool requestWithUrl:urlString parameters:parameters success:^(id data) {
        if ([data[@"code"] intValue] == 0) {
            NSString *availableStarPoint = data[@"info"][@"availableStarPoint"];
            complete(availableStarPoint);
        } else {
            self.taskStarBackgroundImageView.userInteractionEnabled = YES;
            [self showMessageAutoHide:data[@"msg"]];
        }
    } failure:^(id error) {
        [self showMessageAutoHide:@"服务器出错啦"];
        self.taskStarBackgroundImageView.userInteractionEnabled = YES;
    }];
}

- (void)jumpLink {
    if (![self.annotation.adLink replacingEmptyString].length) return;
    if (![self.annotation.adLink containsString:@"http"]) return;
    [[UIApplication sharedApplication] openURL:[NSURL URLWithString:self.annotation.adLink]];
}


- (void)changeButtonTitle {
    self.seconds--;
    if (self.seconds <= 0) {
        
        [_timer invalidate];
        _timer = nil;
        
        self.tj_navigationItem.leftBarButtonItems = [UIBarButtonItem backBarItemWithTarget:self action:@selector(tj_goBack)];

        [self.openButton removeFromSuperview];
        [self didClickOpenButton];
        return;
    }
    [self.openButton setTitle:@(self.seconds).description forState:UIControlStateNormal];
}

- (void)addTimer {
    if (!_timer) {
        NSTimer *timer = [NSTimer timerWithTimeInterval:1 target:self selector:@selector(changeButtonTitle) userInfo:nil repeats:YES];
        [[NSRunLoop currentRunLoop] addTimer:timer forMode:NSRunLoopCommonModes];
        _timer = timer;
    }
}

- (void)didClickOpenButton {
    
    self.taskStarBackgroundImageView.layer.anchorPoint = CGPointMake(0.5, 0.75);
    self.boxImageView.image = [UIImage imageNamed:@"box-open"];
    self.taskStarBackgroundImageView.hidden = NO;
    [self scaleAnimation:self.taskStarBackgroundImageView];
    
    if ([self.annotation.adLink replacingEmptyString].length) {
        self.linkStarBackgroundImageView.hidden = NO;
        self.linkStarBackgroundImageView.layer.anchorPoint = CGPointMake(0.5, 0.85);
        [self scaleAnimation:self.linkStarBackgroundImageView];
    }
}

- (void)scaleAnimation:(UIView *)view {
    
    CABasicAnimation *scaleAnimation = [CABasicAnimation animation];
    scaleAnimation.keyPath = @"transform.scale";
    scaleAnimation.fromValue = @0;
    scaleAnimation.toValue = @1;
    scaleAnimation.duration = 0.5;
    
    [view.layer addAnimation:scaleAnimation forKey:@"scale"];
    [self performSelector:@selector(startMoveAnimation:) withObject:view afterDelay:scaleAnimation.duration];
}

- (void)startMoveAnimation:(UIView *)view {

    id toValue = (view == self.linkStarBackgroundImageView) ? @(view.layer.position.y - H(10)) : @(view.layer.position.y - H(15));
    
    CABasicAnimation *positionAnimation = [CABasicAnimation animationWithKeyPath:@"position.y"];
    positionAnimation.fromValue = @(view.layer.position.y);
    positionAnimation.toValue = toValue;
    positionAnimation.duration = 1.5;
    positionAnimation.autoreverses = YES;
    positionAnimation.removedOnCompletion = NO;
    positionAnimation.fillMode = kCAFillModeForwards;
    positionAnimation.repeatCount = HUGE_VALF;
    positionAnimation.timingFunction = [CAMediaTimingFunction functionWithName:kCAMediaTimingFunctionLinear];
    
    [view.layer addAnimation:positionAnimation forKey:@"position"];
}

- (CGFloat)calculateStarCountLableWidthWithStarPoint:(NSString *)starPoint {
    
    CGFloat width = 85;
    NSString *availableStarPointString = [NSString stringWithFormat:@"%@", starPoint];
    width = [availableStarPointString getStringSizeWithWidth:KScreenWidth fontSize:15].width;
    width = width + self.starImageView.bounds.size.width + 13;
    return MAX(85, width);
}

#pragma mark - 加载总共的星星数量
- (void)loadTotalStarCountData {
    [TJNetworkTool requestWithUrl:@"Account.Info" parameters:nil success:^(id data) {
 
        CGFloat width = 85;
 
        if ([data[@"code"] intValue] == 0) {
            NSString *availableStarPoint = data[@"info"][@"availableStarPoint"];
            width = [self calculateStarCountLableWidthWithStarPoint:availableStarPoint];
            self.starCountLable.text = availableStarPoint;
        } else {
            self.starCountLable.text = @"0";
        }
        
        [self.starContentView updateConstraints:^(MASConstraintMaker *make) {
            make.width.offset(width);
        }];
        
    } failure:^(id error) {}];
}

- (void)tj_goBack {
    self.moveAnimationLayer.hidden = YES;
    [self.moveAnimationView.layer removeAllAnimations];
    [self.moveAnimationLayer removeAllAnimations];
    [self.navigationController popViewControllerAnimated:YES];
}

- (UIStatusBarStyle)preferredStatusBarStyle {
    return UIStatusBarStyleLightContent;
}

@end
