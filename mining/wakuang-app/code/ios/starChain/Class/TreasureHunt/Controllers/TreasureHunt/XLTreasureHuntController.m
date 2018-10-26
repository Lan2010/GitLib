//
//  XLTreasureHuntController.m
//  starChain
//
//  Created by rlx on 2018/6/6.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import "XLTreasureHuntController.h"
#import "XLTreasureHuntHeader.h"
#import "XLHomeCell.h"
#import "XLHomeSectionHeaderView.h"
#import "XLVersionCheck.h"
#import "XLButton.h"
#import "XLAnnotationView.h"
#import "XLTaskStarModel.h"
#import "XLTaskModel.h"
#import "XLWalletController.h"
#import "XLAlertAdvertisingController.h"
#import "ShoppingCartTool.h"
#import "XLStarDetailsController.h"
#import <UIButton+WebCache.h>
#import "QHZGuideController.h"


#import <BaiduMapAPI_Map/BMKMapComponent.h>
#import <BaiduMapAPI_Location/BMKLocationComponent.h>
#import <BaiduMapAPI_Search/BMKSearchComponent.h>
#import <BaiduMapAPI_Utils/BMKGeometry.h>

#import <AVFoundation/AVCaptureDevice.h>
#import <AVFoundation/AVMediaFormat.h>

#import "XLPushTaskView.h"
#import "QHZSuspensionView.h"

@interface XLTreasureHuntController ()<BMKLocationServiceDelegate,
BMKMapViewDelegate,
BMKPoiSearchDelegate,
XLAnnotationViewDelegate,
GuideControllerDelegate,
CAAnimationDelegate>

@property (weak, nonatomic) XLTreasureHuntHeader *headerView;
@property (weak, nonatomic) UIView *starContentView;
@property (weak, nonatomic) UILabel *starCountLable;
@property (weak, nonatomic) UIView *locationView;
@property (weak, nonatomic) UIImageView *starImageView;
@property (weak, nonatomic) UIView *pushTaskView;
@property (weak, nonatomic) UIView *taskView;
@property (weak, nonatomic) UILabel *taskNameLable;
@property (weak, nonatomic) XLButton *refreshButton;

@property (strong, nonatomic) NSMutableArray *pushTaskDatas;
@property (strong, nonatomic) BMKLocationService *locService;
@property (strong, nonatomic) BMKMapView *mapView;
@property (strong, nonatomic) MBProgressHUD *hud;
@property (strong, nonatomic) NSArray *taskStarDatas;
@property (strong, nonatomic) NSArray *advertisingStarDatas;
@property (strong, nonatomic) NSArray *taskDatas;
@property (strong, nonatomic) QHZSuspensionView *suspensionView;
@property (strong, nonatomic) XLMeLocationAnnotation *meLocationAnnotation;
@property (strong, nonatomic) XLMelocationAnnotationView *meLocationAnnotationView;
@property (strong, nonatomic) NSMutableArray <XLStarAnnotation *>*advertisingAnnotationDatas;
@property (strong, nonatomic) NSMutableArray <XLStarAnnotation *>*starAnnotationDatas;
@property (strong, nonatomic) NSMutableArray <BMKPointAnnotation *>*taskLocationAnnotationDatas;
@property (strong, nonatomic) NSMutableArray <BMKCircle *>*taskLocationCircles;
@property (strong, nonatomic) CABasicAnimation *transformAnimation;
@property (strong, nonatomic) NSDictionary *ADStarRadiusDict;

@property (assign, nonatomic) NSInteger cityCode;
@property (assign, nonatomic) NSInteger ADStarRadius;
@property (assign, nonatomic) BMKCoordinateSpan span;
@property (assign, nonatomic) CLLocationCoordinate2D locationCoordinate2D;
@property (assign, nonatomic) CGRect starFrame;
@property (assign, nonatomic) BOOL refresh;
@property (assign, nonatomic) BOOL animationStop;
@property (assign, nonatomic) NSInteger enterTaskCount;

@property (copy, nonatomic) NSString *suspensionLink;
@property (copy, nonatomic) NSString *suspensionTitle;
@property (copy, nonatomic) NSString *availableStarPoint;

@end


@implementation XLTreasureHuntController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    [self listeningNotification];
    [self loadTotalStarCountData];
    [self addSubView];
    [self versionUpdate];
    [self loadInviteFriendsUrl];
}


- (void)addSubView {
    
    self.advertisingAnnotationDatas = [NSMutableArray array];
    self.starAnnotationDatas = [NSMutableArray array];
    self.taskLocationAnnotationDatas = [NSMutableArray array];
    self.taskLocationCircles = [NSMutableArray array];
    self.taskStarDatas = [NSArray array];
    self.advertisingStarDatas = [NSArray array];
    self.taskDatas = [NSArray array];
    self.enterTaskCount = 0;
    
    self.hiddenNavigationBar = YES;
    self.animationStop = YES;
    
    self.mapView = [[BMKMapView alloc] initWithFrame:CGRectMake(0, 0, KScreenWidth, KScreenHeight - TabbarHight)];
    [self.view addSubview:self.mapView];
    self.mapView.rotateEnabled = NO;
    self.mapView.overlookEnabled = NO;
    
    BMKLocationViewDisplayParam *displayParam = [[BMKLocationViewDisplayParam alloc]init];
    displayParam.isAccuracyCircleShow = false;
    [self.mapView updateLocationViewWithParam:displayParam];
    
    self.locService = [[BMKLocationService alloc] init];
    [self.locService startUserLocationService];
    
    self.mapView.delegate = self;
    self.locService.delegate = self;
    
    self.mapView.showsUserLocation = NO;
    self.mapView.userTrackingMode = BMKUserTrackingModeFollow;
    self.mapView.showsUserLocation = YES;
    
    UIButton *starContentView = [UIButton tj_WithSuperView:self.view];
    self.starContentView = starContentView;
    [starContentView addTarget:self action:@selector(didClickStarContentView) forControlEvents:UIControlEventTouchUpInside];
    starContentView.backgroundColor = [UIColor_RGB(26, 14, 50) colorWithAlphaComponent:0.7];
    [starContentView shearRoundedCornersWithRadiu:11];
    [starContentView makeConstraints:^(MASConstraintMaker *make) {
        make.left.offset(20);
        make.top.offset(35 + (StatusBarHight - 20));
        make.width.offset(85);
        make.height.offset(22);
    }];
    
    UIImageView *starImageView = [UIImageView tj_WithSuperView:starContentView];
    starImageView.userInteractionEnabled = NO;
    UIImage *starImage = [UIImage imageNamed:@"icon-star"];
    starImageView.image = starImage;
    self.starImageView = starImageView;
    [starImageView makeConstraints:^(MASConstraintMaker *make) {
        make.centerY.offset(0);
        make.left.offset(6);
        make.size.equalTo(starImage.size);
    }];
    
    UILabel *starCountLable = [UILabel lableWithSuperView:starContentView fontSize:14 color:[UIColor whiteColor] title:@"0" textAlignment:NSTextAlignmentLeft];
    self.starCountLable = starCountLable;
    starCountLable.userInteractionEnabled = NO;
    [starCountLable makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(starImageView.right).offset(4);
        make.centerY.offset(0);
        make.right.offset(-2);
    }];
    
    XLButton *starRankingButton = [XLButton tj_WithSuperView:self.view];
    [starRankingButton setImage:[UIImage imageNamed:@"btn-rank"] forState:UIControlStateNormal];
    [starRankingButton addTarget:self action:@selector(didClickStarRankingButton) forControlEvents:UIControlEventTouchUpInside];
    [starRankingButton makeConstraints:^(MASConstraintMaker *make) {
        make.top.equalTo(starContentView.bottom).offset(8);
        make.left.equalTo(starImageView);
        make.height.offset(20);
        make.width.offset(75);
    }];
    
    XLButton *strategyButton = [XLButton tj_WithSuperView:self.view];
    UIImage *strategyImage = [UIImage imageNamed:@"Strategy"];
    [strategyButton addTarget:self action:@selector(didClickStrategyButton) forControlEvents:UIControlEventTouchUpInside];
    [strategyButton setImage:strategyImage forState:UIControlStateNormal];
    [strategyButton makeConstraints:^(MASConstraintMaker *make) {
        make.centerY.equalTo(starImageView);
        make.right.offset(-14);
        make.size.equalTo(strategyImage.size);
    }];
    
    XLButton *refreshButton = [XLButton tj_WithSuperView:self.view];
    UIImage *refreshImage = [UIImage imageNamed:@"btn-fresh"];
    self.refreshButton = refreshButton;
    [refreshButton addTarget:self action:@selector(didClickRefreshButton) forControlEvents:UIControlEventTouchUpInside];
    [refreshButton setImage:refreshImage forState:UIControlStateNormal];
    [refreshButton makeConstraints:^(MASConstraintMaker *make) {
        make.bottom.offset(-30);
        make.right.offset(-14);
        make.size.equalTo(refreshImage.size);
    }];
    
    [self addTaskPromptView];
    [self loadSuspensionData];
    
}

- (void)didClickStarContentView {
    if (![USERDEFAULTS boolForKey:login]) return;
    self.tabBarController.selectedIndex = 2;
}

- (void)loadSuspensionData {
    [TJNetworkTool requestWithUrl:@"Common.SuspensionFrame" parameters:nil success:^(id data) {
        if ([data[@"code"] intValue] == 0) {
            
            NSDictionary *info = data[@"info"];
            NSString *iconUrl = info[@"frame_icon"];
            if (![iconUrl isKindOfClass:[NSString class]]) return ;
            if (![iconUrl containsString:@"http"]) return;
            
            self.suspensionLink = info[@"link"];
            self.suspensionTitle = info[@"frame_word"];
            
            [[SDWebImageManager sharedManager] downloadImageWithURL:[NSURL URLWithString:iconUrl] options:0 progress:nil completed:^(UIImage *image, NSError *error, SDImageCacheType cacheType, BOOL finished, NSURL *imageURL) {
                if (image.size.width) {
                    [self.suspensionView setImage:image forState:UIControlStateNormal];
                    [self.view addSubview:self.suspensionView];
                }
            }];
        } else {
            [self.suspensionView removeFromSuperview];
        }
    } failure:^(id error) { }];
    
}

#pragma mark - 悬浮
- (QHZSuspensionView *)suspensionView {
    if (!_suspensionView) {
        QHZSuspensionView *suspensionView = [[QHZSuspensionView alloc] initWithFrame:CGRectMake(KScreenWidth - ZOOM5(90) - 10, KScreenHeight - ZOOM5(90) - TabbarSafeBottomMargin - 49 - H(150), ZOOM5(90), ZOOM5(90))];
        [suspensionView addTarget:self action:@selector(didClickSuspensionButton) forControlEvents:UIControlEventTouchUpInside];
        _suspensionView = suspensionView;
    }
    return _suspensionView;
}

- (void)didClickSuspensionButton {
    [self loadInteractionWebPageWithUrlString:self.suspensionLink title:self.suspensionTitle];
}

- (void)didClickStarRankingButton {
    [self.navigationController pushViewControllerWithName:@"XLStarRankingController" title:nil animated:YES];
}

- (void)addPushTaskView {
    
    if (!self.pushTaskDatas.count) return;
    
    XLPushTaskView *pushTaskView = [[XLPushTaskView alloc] initWithFrame:CGRectMake(17, KScreenHeight, KScreenWidth - 34, 150) model:self.pushTaskDatas.firstObject];
    [pushTaskView setDidClickButtonBlock:^(NSInteger tag) {
        (tag) ? [self didClickCancelButton] : [self didClickDetermineButton];
    }];
 
    self.pushTaskView = pushTaskView;
    [self.view addSubview:pushTaskView];
 
}

- (void)didClickCancelButton {
    [self hiddenPushTaskViewComplete:^{
        [self.pushTaskDatas removeObjectAtIndex:0];
        [self.pushTaskView removeFromSuperview];
        [self addPushTaskView];
        [self showPushTaskView];
    }];
}

- (void)didClickDetermineButton {
    
    [self hiddenPushTaskViewComplete:^{
        
        XLTaskModel *model = self.pushTaskDatas.firstObject;
        
        if (![XLGlobalFunc globalFunc].bindingMill) {
            [self alertWithmessage:@"您还未绑定充电宝" leftButtonName:@"取消" rightButtonName:@"前往绑定" leftButtonBlock:^{
                [self.pushTaskDatas removeObjectAtIndex:0];
                [self.pushTaskView removeFromSuperview];
                [self addPushTaskView];
                [self showPushTaskView];
            } rightButtonBlock:^{
                [self didBindingMillItem];
            }];
            return;
        }
        
        [self alertWithmessage:[NSString stringWithFormat:@"是否接受%@任务", model.task_name] leftButtonName:@"取消" rightButtonName:@"接受任务" leftButtonBlock:nil rightButtonBlock:^{
            
            [self loadUserAcceptTaskDataWithOrderNO:model.order_no addressId: model.tl_id complete:^{
                [self.pushTaskDatas removeObjectAtIndex:0];
                [self addPushTaskView];
                [self showPushTaskView];
            }];
        }];
        
    }];
    
}

- (void)loadUserAcceptTaskDataWithOrderNO:(NSNumber *)orderNO addressId:(NSString *)addressId complete:(void(^)(void))complete {
    
    NSDictionary *parameters = @{@"orderNO": orderNO, @"tl_id": addressId};
    TJLog(@"Task.UserAcceptTask, parameters =  %@", parameters);
    [TJNetworkTool requestWithUrl:@"Task.UserAcceptTask" parameters:parameters success:^(id data) {
        
        [self showMessageAutoHide:data[@"msg"]];
        
        if ([data[@"code"] intValue] == 0) {
            [[NSNotificationCenter defaultCenter] postNotificationName:acceptTaskNotification object:nil userInfo:nil];
            complete();
        }
        
    } failure:^(id error) { }];
}

#pragma mark - 绑定矿机
- (void)didBindingMillItem {
    
    [AVCaptureDevice requestAccessForMediaType:AVMediaTypeVideo completionHandler:^(BOOL granted) {
        dispatch_async(dispatch_get_main_queue(), ^{
            if (granted) {
                [self.navigationController pushViewControllerWithName:@"XLBindingMillController" title:nil animated:YES];
            } else {
                [self alertWithTitle:@"" message:@"现在不能使用相机扫码绑定充电宝，前往开启？" leftButtonName:@"取消" rightButtonName:@"前往开启" leftButtonBlock:^{
                } rightButtonBlock:^{
                    [[UIApplication sharedApplication] openURL:[NSURL URLWithString:UIApplicationOpenSettingsURLString]];
                }];
                return ;
            }
        });
    }];
    
}

#pragma mark - 加载用户的总的星星数量
- (void)loadUserInfoData {
    
    if (![USERDEFAULTS boolForKey:login]) return;
    
    [TJNetworkTool requestWithUrl:@"User.Info" parameters:nil success:^(id data) {
        if ([data[@"code"] intValue] == 0) {
            NSDictionary *all_status = data[@"info"][@"all_status"];
            [XLGlobalFunc globalFunc].bindingMill = [all_status[@"Charge"] intValue];
            
            static dispatch_once_t onceTokenBindingMill;
            dispatch_once(&onceTokenBindingMill, ^{
                if (![XLGlobalFunc globalFunc].bindingMill) {
                    [self bindingMill];
                }
            });
        }
    } failure:^(id error) {}];
    
}

#pragma mark - 显示推送任务视图
- (void)showPushTaskView {

    if (!self.pushTaskDatas.count) return;
    if (self.pushTaskView.tj_y != KScreenHeight) return;
    
    [UIView animateWithDuration:0.75 delay:0.1 usingSpringWithDamping:0.6 initialSpringVelocity:1 options:UIViewAnimationOptionCurveEaseInOut animations:^{
        self.pushTaskView.alpha = 1;
        self.pushTaskView.tj_y = KScreenHeight - (150 + TabbarHight + 20);
    } completion:^(BOOL complete){}];
}

- (void)hiddenPushTaskViewComplete:(void (^)(void))complete {
    [UIView animateWithDuration:0.75 delay:0.1 usingSpringWithDamping:0.6 initialSpringVelocity:1 options:UIViewAnimationOptionCurveEaseInOut animations:^{
        self.pushTaskView.alpha = 0;
        self.pushTaskView.tj_y = KScreenHeight;
    } completion:^(BOOL finish){
        complete();
        TJLog(@"pushTaskDatas = %@", self.pushTaskDatas);
    }];
}

- (void)loadPushTaskData {
    
    if (![USERDEFAULTS boolForKey:login]) return;
    
    CLLocationCoordinate2D coordinate = [XLGlobalFunc globalFunc].coordinate;
    if (coordinate.latitude == 0 || coordinate.longitude == 0) {
        return;
    }
    
    self.taskDatas = [NSMutableArray array];
    
    NSDictionary *parameters = @{
                                 @"lat": @(coordinate.latitude),
                                 @"lng": @(coordinate.longitude),
                                 };
    [TJNetworkTool requestWithUrl:@"Task.GetRemindAd" parameters:parameters success:^(id data) {
        if ([data[@"code"] intValue] == 0) {
            self.pushTaskDatas = [XLTaskModel mj_objectArrayWithKeyValuesArray:data[@"info"]];
            [self addPushTaskView];
            [self showPushTaskView];
        }
    } failure:^(id error) { }];
}

- (CABasicAnimation *)transformAnimation {
    if (!_transformAnimation) {
        CABasicAnimation *animation = [CABasicAnimation animationWithKeyPath:@"transform.rotation.z"];
        animation.fromValue = @(0);
        animation.toValue = @(M_PI * 2);
        animation.repeatCount = MAXFLOAT;
        animation.duration = 1.5;
        animation.fillMode = kCAFillModeForwards;
        animation.removedOnCompletion = NO;
        _transformAnimation = animation;
    }
    return _transformAnimation;
}

- (void)addTaskPromptView {
    UIView *taskView = [[UIView alloc] initWithFrame:CGRectMake(17, KScreenHeight, KScreenWidth - 34, 38)];
    taskView.backgroundColor = [UIColor whiteColor];
    taskView.userInteractionEnabled = YES;
    taskView.layer.cornerRadius = 3;
    taskView.layer.masksToBounds = NO;
    taskView.layer.shadowColor = [UIColor colorWithRed:105.0f/255.0f green:99.0f/255.0f blue:192.0f/255.0f alpha:0.26f].CGColor;
    taskView.layer.shadowOpacity = 0.5;
    taskView.layer.shadowRadius = 3;
    self.taskView = taskView;
    [self.view addSubview:taskView];
    
    [taskView addTapGesturesWithTarget:self action:@selector(hiddenTaskPromptView)];
    
    UILabel *taskNameLable = [UILabel lableWithSuperView:taskView fontSize:15 color:UIColor_Hex(0x2f1959) title:@"" textAlignment:NSTextAlignmentLeft];
    self.taskNameLable = taskNameLable;
    [taskNameLable makeConstraints:^(MASConstraintMaker *make) {
        make.left.offset(20);
        make.centerY.offset(0);
    }];
    [taskView addSubview:taskNameLable];
    
}

#pragma mark - 刷新数据
- (void)didClickRefreshButton {
    //防止地图上面的标注没有移除完成又添加
    self.refreshButton.userInteractionEnabled = NO;
    dispatch_after(dispatch_time(DISPATCH_TIME_NOW, (int64_t)(1 * NSEC_PER_SEC)), dispatch_get_main_queue(), ^{
        self.refreshButton.userInteractionEnabled = YES;
    });
    
    if (self.animationStop) {
        [self.refreshButton.layer addAnimation:self.transformAnimation forKey:@"transform"];
    } else {
        return;
    }
    
    self.span = self.mapView.region.span;
    [self refreshData];
}

- (void)animationDidStop:(CAAnimation *)anim finished:(BOOL)flag{
    if (anim == [self.refreshButton.layer animationForKey:@"transform"]) {
        self.animationStop = flag;
    }
}

#pragma mark - 加载相关的url
- (void)loadInviteFriendsUrl {
    [TJNetworkTool requestWithUrl:@"Common.CommonUrl" parameters:nil success:^(id data) {
        if (![data[@"code"] intValue]) {
            NSArray *array = data[@"info"];
            NSUserDefaults *userDefault = [NSUserDefaults standardUserDefaults];
            [array enumerateObjectsUsingBlock:^(id  _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
                TJLog(@"url = %@, type = %@", obj[@"url"], obj[@"state"] );
                if (obj) [userDefault setObject:obj[@"url"] forKey:obj[@"state"]];
            }];
        }
    } failure:^(id error) {
        
    }];
}

#pragma mark - 星链攻略
- (void)didClickStrategyButton {
    [self.navigationController pushViewControllerWithName:@"XLStrategyController" title:@"攻略" animated:YES];
}

- (void)showTaskPromptView {
    
    NSLog(@"showTaskPromptView");
    
    NSLog(@"taskView = %@", NSStringFromCGRect(self.taskView.frame));
 
    if (self.taskView.tj_y != KScreenHeight) return;
    
    [UIView animateWithDuration:0.75 delay:0.1 usingSpringWithDamping:0.6 initialSpringVelocity:1 options:UIViewAnimationOptionCurveEaseInOut animations:^{
        self.taskView.alpha = 1;
        self.taskView.tj_y = KScreenHeight - (38 + TabbarHight + 20);
    } completion:^(BOOL complete){
        dispatch_after(dispatch_time(DISPATCH_TIME_NOW, (int64_t)(2 * NSEC_PER_SEC)), dispatch_get_main_queue(), ^{
            [self hiddenTaskPromptView];
        });
    }];
}

- (void)hiddenTaskPromptView {
    [UIView animateWithDuration:0.75 delay:0.1 usingSpringWithDamping:0.6 initialSpringVelocity:1 options:UIViewAnimationOptionCurveEaseInOut animations:^{
        self.taskView.alpha = 0;
        self.taskView.tj_y = KScreenHeight;
    } completion:nil];
}

#pragma mark - annotationView 点击事件
- (void)didSelectStarAnnotationView:(BMKAnnotationView *)annotationView {
    
    if (![USERDEFAULTS boolForKey:login]) {
        [self presentViewControllerWithName:@"XLLoginController" title:nil]; return;
    }
    
    if ([annotationView isKindOfClass:[XLStarAnnotationView class]]) {
        
        XLStarAnnotationView *starAnnotationView = (XLStarAnnotationView *)annotationView;
        XLStarAnnotation *annotation = starAnnotationView.starAnannotation;
        XLStarDetailsController *starDetailsController = [[XLStarDetailsController alloc] init];
        starDetailsController.annotation = annotation;
        starDetailsController.mapView = self.mapView;
        starDetailsController.availableStarPointString = self.availableStarPoint;
        
        [self.navigationController pushViewController:starDetailsController animated:YES];
        
    } else if ([annotationView isKindOfClass:[XLTasklocationAnnotationView class]]) {
        XLTasklocationAnnotationView *tasklocationAnnotationView = (XLTasklocationAnnotationView *)annotationView;
        self.taskNameLable.text = tasklocationAnnotationView.tasklocationAnnotation.name;
        [self showTaskPromptView];
        
    }
}

- (BMKOverlayView*)mapView:(BMKMapView *)map viewForOverlay:(id<BMKOverlay>)overlay {
    if ([overlay isKindOfClass:[BMKCircle class]]) {
        BMKCircle *circle = (BMKCircle *)overlay;
        BMKCircleView *circleView = [[BMKCircleView alloc] initWithCircle:circle];
        circleView.strokeColor = [UIColor colorWithRed:132.0f/255.0f green:86.0f/255.0f blue:250.0f/255.0f alpha:0.15f];
        circleView.fillColor = [UIColor colorWithRed:132.0f/255.0f green:86.0f/255.0f blue:250.0f/255.0f alpha:0.15f];
        return circleView;
    } else {
        return nil;
    }
}

#pragma mark - BMKMapViewDelegate

- (BMKAnnotationView *)mapView:(BMKMapView *)mapView viewForAnnotation:(id <BMKAnnotation>)annotation {
    
    NSLog(@"viewForAnnotation class = %@", [annotation class]);
    
    if ([annotation isKindOfClass:[XLStarAnnotation class]]) {
        
        XLStarAnnotation *starAnnotation = (XLStarAnnotation *)annotation;
        NSString *starAnnotationID = @"starAnnotationID";
        XLStarAnnotationView *annotationView = (XLStarAnnotationView *)[mapView dequeueReusableAnnotationViewWithIdentifier:starAnnotationID];
        if (annotationView == nil) {
            annotationView = [[XLStarAnnotationView alloc] initWithAnnotation:starAnnotation reuseIdentifier:starAnnotationID];
        }
        annotationView.delegate = self;
        annotationView.starAnannotation = starAnnotation;
        return annotationView;
        
    } else if ([annotation isKindOfClass:[XLTasklocationAnnotation class]]) {
        
        XLTasklocationAnnotation *tasklocationAnnotation = (XLTasklocationAnnotation *)annotation;
        NSString *annotationViewID = @"annotationViewID";
        XLTasklocationAnnotationView *annotationView = (XLTasklocationAnnotationView *)[mapView dequeueReusableAnnotationViewWithIdentifier:annotationViewID];
        if (annotationView == nil) {
            annotationView = [[XLTasklocationAnnotationView alloc] initWithAnnotation:annotation reuseIdentifier:annotationViewID];
        }
        annotationView.delegate = self;
        annotationView.centerOffset = CGPointMake(0, -6);
        annotationView.tasklocationAnnotation = tasklocationAnnotation;
        return annotationView;
        
    } else if ([annotation isKindOfClass:[XLMeLocationAnnotation class]]) {
        
        XLMeLocationAnnotation *meLocationAnnotation = (XLMeLocationAnnotation *)annotation;
        NSString *meLocationAnnotationID = @"meLocationAnnotation";
        XLMelocationAnnotationView *annotationView = (XLMelocationAnnotationView *)[mapView dequeueReusableAnnotationViewWithIdentifier:meLocationAnnotationID];
        if (annotationView == nil) {
            annotationView = [[XLMelocationAnnotationView alloc] initWithAnnotation:annotation reuseIdentifier:meLocationAnnotationID];
        }
        
        annotationView.meLocationAnnotation = meLocationAnnotation;
        self.meLocationAnnotationView = annotationView;
        return annotationView;
        
    } else {
        return nil;
    }
    
}

#pragma mark - BMKLocationServiceDelegate

- (void)didFailToLocateUserWithError:(NSError *)error {
    CLAuthorizationStatus authorizationStatus = [CLLocationManager authorizationStatus];
    if (![CLLocationManager locationServicesEnabled] || authorizationStatus  == kCLAuthorizationStatusDenied || authorizationStatus == kCLAuthorizationStatusNotDetermined || authorizationStatus == kCLAuthorizationStatusRestricted) {
        [self alertWithTitle:@"" message:@"定位服务未开启" leftButtonName:@"取消" rightButtonName:@"开启" leftButtonBlock:nil rightButtonBlock:^{
            [[UIApplication sharedApplication] openURL:[NSURL URLWithString:UIApplicationOpenSettingsURLString]];
        }];
    }
    TJLog(@"error = %@", error);
}

- (XLMeLocationAnnotation *)meLocationAnnotation {
    if (!_meLocationAnnotation) {
        _meLocationAnnotation = [[XLMeLocationAnnotation alloc] init];
        [self.mapView addAnnotation:_meLocationAnnotation];
        [self.mapView showAnnotations:@[_meLocationAnnotation] animated:NO];
    }
    return _meLocationAnnotation;
}

- (void)didUpdateBMKUserLocation:(BMKUserLocation *)userLocation {
    
    self.locationCoordinate2D = userLocation.location.coordinate;
    [self currentCityCode:userLocation];
    self.meLocationAnnotation.coordinate = userLocation.location.coordinate;
    [XLGlobalFunc globalFunc].coordinate = userLocation.location.coordinate;
    [_mapView updateLocationData:userLocation];
    
    [self detectionEnterTaskLocation];
    
    TJLog(@"didUpdateUserLocation lat %f,long %f",userLocation.location.coordinate.latitude,userLocation.location.coordinate.longitude);
}


- (void)detectionEnterTaskLocation {
    
    [self.taskDatas enumerateObjectsUsingBlock:^(XLTaskModel *model, NSUInteger idx, BOOL * _Nonnull stop) {
        
        CLLocationCoordinate2D coordinate = (CLLocationCoordinate2D){model.lat.doubleValue, model.lng.doubleValue};
        
        BMKMapPoint point1 = BMKMapPointForCoordinate(coordinate);
        BMKMapPoint point2 = BMKMapPointForCoordinate(self.locationCoordinate2D);
        CLLocationDistance distance = BMKMetersBetweenMapPoints(point1,point2);
        if (distance < model.task_radius.doubleValue) {
            self.enterTaskCount++;
        }
    }];
    
    self.meLocationAnnotationView.showPrompt = self.enterTaskCount;
    
}

#pragma mark - 当前城市编码
- (void)currentCityCode:(BMKUserLocation *)userLocation {
    
    CLGeocoder *geocoder = [[CLGeocoder alloc] init];
    [geocoder reverseGeocodeLocation: userLocation.location completionHandler:^(NSArray *array, NSError *error) {
        
        if (array.count > 0) {
            CLPlacemark *placemark = array.firstObject;
            if (placemark) {
                NSString *city = placemark.locality;
                BMKOfflineMap *offlineMap = [[BMKOfflineMap alloc] init];
                NSArray *records = [offlineMap searchCity:city];
                BMKOLSearchRecord *oneRecord = [records objectAtIndex:0];
                self.cityCode  = oneRecord.cityID;
                
                [XLGlobalFunc globalFunc].cityCode = oneRecord.cityID;
                
                if (self.cityCode) {
                    static dispatch_once_t onceToken;
                    dispatch_once(&onceToken, ^{
                        
                        BMKCoordinateRegion region;
                        region.center = userLocation.location.coordinate;
                        region.span =  BMKCoordinateSpanMake(0.005f, 0.005f);
                        [self.mapView setRegion:region animated:NO];
                        self.span = region.span;
                        
                        [self showGuideView];
                        [self loadAnnotationData];
                    });
                }
            }
        }
    }];
}


- (void)bindingMill {
    
//    if ([XLGlobalFunc globalFunc].bindingMill) return;
//    if (![USERDEFAULTS boolForKey:login]) return;
//
//    UIAlertController *alert = [UIAlertController alertControllerWithTitle:nil message:@"您还未绑定充电宝，是否绑定?" preferredStyle:UIAlertControllerStyleAlert];
//    [alert addAction:[UIAlertAction actionWithTitle:@"去绑定" style:UIAlertActionStyleDefault handler:^(UIAlertAction * _Nonnull action) {
//        [self didBindingMillItem];
//    }]];
//    [alert addAction:[UIAlertAction actionWithTitle:@"去购买" style:UIAlertActionStyleDefault handler:^(UIAlertAction * _Nonnull action) {
//    }]];
//    [alert addAction:[UIAlertAction actionWithTitle:@"取消" style:UIAlertActionStyleDefault handler:^(UIAlertAction * _Nonnull action) {
//    }]];
//
//    [self presentViewController:alert animated:YES completion:nil];
    
}


#pragma mark - 加载广告星星数据
- (void)loadADStarData {
    
    if (!self.cityCode) return;
    
    CLLocationCoordinate2D coordinate = self.locationCoordinate2D;
    NSDictionary *parameters = @{
                                 @"lat": @(coordinate.latitude),
                                 @"lng": @(coordinate.longitude),
                                 @"m": @(self.ADStarRadius),
                                 @"code": @(self.cityCode)
                                 };
    
    [self.mapView removeAnnotations:self.advertisingAnnotationDatas];
    [self.advertisingAnnotationDatas removeAllObjects];
    
    [TJNetworkTool requestWithUrl:@"Ad.GetLocationStar" parameters:parameters success:^(id data) {
        
        [self.mapView removeAnnotations:self.advertisingAnnotationDatas];
        [self.advertisingAnnotationDatas removeAllObjects];
        
        if ([data[@"code"] intValue] == 0) {
            
            self.advertisingStarDatas = [XLAdvertisingStarModel mj_objectArrayWithKeyValuesArray:data[@"info"]];
            [self.advertisingStarDatas enumerateObjectsUsingBlock:^(XLAdvertisingStarModel *model, NSUInteger idx, BOOL * _Nonnull stop) {
                XLStarAnnotation *item = [[XLStarAnnotation alloc] init];
                item.coordinate = (CLLocationCoordinate2D){model.Lat.doubleValue, model.Lng.doubleValue};
                item.starType = XLStarTypeAD;
                item.advertId = model.ad_id;
                item.advertisementType = model.advertisementType;
                item.text = model.onceStarPoint;
                item.starCount = model.onceStarPoint;
                item.linkStarPoint = model.onceClickStarPoint;
                item.adImageUrl = model.advertPic.firstObject[@"url"];
                item.adIcon = model.advertIcon;
                item.adLink = model.ad_url;
                item.advertRemark = model.advertRemark;
                item.advertName = model.advertName;
                [self.advertisingAnnotationDatas addObject:item];
                
            }];
            
            if (![USERDEFAULTS boolForKey:login]) {//如果没有登录的话就没有任务星星和随机掉落的星星, 就显示广告星星
                [self.mapView addAnnotations:self.advertisingAnnotationDatas];
                [self.mapView showAnnotations:self.advertisingAnnotationDatas animated:NO];
                [self adjustRegion];
            }
        } else {
            if (![USERDEFAULTS boolForKey:login]) {
                [self.refreshButton.layer removeAnimationForKey:@"transform"];
            }
        }
        
        (self.refresh) ? [self loadTaskStarData] : [self loadOngoingTaskData];
        
    } failure:^(id error) {
        (self.refresh) ? [self loadTaskStarData] : [self loadOngoingTaskData];
    }];
    
}

#pragma mark - 任务星星和随机星星数据
- (void)loadTaskStarData {
    
    if (![USERDEFAULTS boolForKey:login]) return;
    
    [self.mapView removeAnnotations:self.starAnnotationDatas];
    [self.starAnnotationDatas removeAllObjects];
    
    [TJNetworkTool requestWithUrl:@"Account.UnCollection" parameters:@{@"cityCode": @(self.cityCode)} success:^(id data) {
        
        NSInteger code = [data[@"code"] intValue];
        
        [self.mapView removeAnnotations:self.starAnnotationDatas];
        [self.starAnnotationDatas removeAllObjects];
        
        if ([data[@"code"] intValue] == 0) {
            
            self.taskStarDatas = [XLTaskStarModel mj_objectArrayWithKeyValuesArray:data[@"info"]];
            [self.taskStarDatas enumerateObjectsUsingBlock:^(XLTaskStarModel *model, NSUInteger idx, BOOL * _Nonnull stop) {
                
                XLStarAnnotation *item = [[XLStarAnnotation alloc] init];
                NSArray *longitudeAndLatitudes = [model.longitudeAndLatitude componentsSeparatedByString:@"|"];
                NSString *Latitudes = [longitudeAndLatitudes.firstObject substringFromIndex:4];
                NSString *longitude = [longitudeAndLatitudes.lastObject substringFromIndex:4];
                item.coordinate = (CLLocationCoordinate2D){Latitudes.doubleValue, longitude.doubleValue};
                item.recordsType = model.recordsType;
                item.starType = ([model.recordsType isEqualToString:@"TASK"]) ? XLStarTypeTask : XLStarTypeRandom;
                item.text = model.operStarPoint;
                item.advertId = model.ad_id;
                item.recordToken = model.recordToken;
                item.starCount = [NSString stringWithFormat:@"%.4f", model.operStarPoint.doubleValue + model.onceStarPoint.doubleValue];
                item.adImageUrl = model.advertPic.firstObject[@"url"];
                item.adIcon = model.advertIcon;
                item.adLink = model.ad_url;
                item.advertName = model.advertName;
                item.createTime = model.createTime;
                item.advertRemark = model.advertRemark;
                item.taskId = model.taskId;
                item.linkStarPoint = model.onceClickStarPoint;
                
                [self.starAnnotationDatas addObject:item];
            }];
            
        }
        
        if (code == 0) [self.mapView addAnnotations:self.starAnnotationDatas];
        if (!self.refresh) {
            [self.mapView addAnnotations:self.taskLocationAnnotationDatas];
            [self.mapView addOverlays:self.taskLocationCircles];
        }
        
        [self.mapView addAnnotations:self.advertisingAnnotationDatas];
        
        if (code == 0) [self.mapView showAnnotations:self.starAnnotationDatas animated:NO];
        if (!self.refresh) [self.mapView showAnnotations:self.taskLocationAnnotationDatas animated:NO];
        [self.mapView showAnnotations:self.advertisingAnnotationDatas animated:NO];
        
        [self adjustRegion];
        
    } failure:^(id error) {
        [self.mapView showAnnotations:self.advertisingAnnotationDatas animated:NO];
        [self.mapView addAnnotations:self.advertisingAnnotationDatas];
        [self adjustRegion];
        [self.refreshButton.layer removeAnimationForKey:@"transform"];
    }];
}


#pragma mark - 正在进行中的任务的位置数据
- (void)loadOngoingTaskData {
    
    if (![USERDEFAULTS boolForKey:login]) return;
    
    NSDictionary *parameters = @{
                                 @"pageindex": @1,
                                 @"pagesize": @50,
                                 @"type": @2
                                 };
    
    [self.mapView removeAnnotations:self.taskLocationAnnotationDatas];
    [self.mapView removeOverlays:self.taskLocationCircles];
    [self.taskLocationAnnotationDatas removeAllObjects];
    [self.taskLocationCircles removeAllObjects];
    
    [TJNetworkTool requestWithUrl:@"Task.GetMyTask" parameters:parameters success:^(id data) {
        
        [self.mapView removeAnnotations:self.taskLocationAnnotationDatas];
        [self.mapView removeOverlays:self.taskLocationCircles];
        [self.taskLocationAnnotationDatas removeAllObjects];
        [self.taskLocationCircles removeAllObjects];
        
        if ([data[@"code"] intValue] == 0) {
            
            self.taskDatas = [XLTaskModel mj_objectArrayWithKeyValuesArray:data[@"info"]];
            [self.taskDatas enumerateObjectsUsingBlock:^(XLTaskModel *model, NSUInteger idx, BOOL * _Nonnull stop) {
                XLTasklocationAnnotation *item = [[XLTasklocationAnnotation alloc] init];
                item.coordinate = (CLLocationCoordinate2D){model.lat.doubleValue, model.lng.doubleValue};
                item.imageUrl = model.task_lcon;
                item.name = model.task_name;
                item.task_radius = model.task_radius.doubleValue;
                [self.taskLocationAnnotationDatas addObject:item];
                [self.taskLocationCircles addObject:[BMKCircle circleWithCenterCoordinate:item.coordinate radius:item.task_radius]];
            }];
            
            [self detectionEnterTaskLocation];
            
        } else {
            self.meLocationAnnotationView.showPrompt = NO;
        }
        
        [self loadTaskStarData];
        
    } failure:^(id error) {
        [self loadTaskStarData];
    }];
}


#pragma mark - 显示指引
- (void)showGuideView {
    
    if ([USERDEFAULTS boolForKey:homeGuideView]) {
        [self bindingMill];
        return;
    };
    
    if (![NSStringFromClass([self.navigationController.childViewControllers.lastObject class]) isEqualToString:NSStringFromClass(self.class)]){
        return;
    }
    
    if (self.presentedViewController) return;

    QHZGuideController *guideController = [[QHZGuideController alloc] init];
    guideController.controllerName = NSStringFromClass(self.class);
    guideController.delegate = self;
    [self presentViewController:guideController animated:NO completion:nil];
}

- (void)dismissGuideController:(QHZGuideController *)guideController maskView:(JYMaskView *)maskView {
    [USERDEFAULTS setBool:YES forKey:homeGuideView];
    
    [self bindingMill];
}

#pragma mark - 调整范围
- (void)adjustRegion {
    [self.refreshButton.layer removeAnimationForKey:@"transform"];
    BMKCoordinateRegion region;
    region.center = self.locationCoordinate2D;
    region.span = self.span;
    self.mapView.region = region;
}

- (void)viewWillDisappear:(BOOL)animated {
    [super viewWillDisappear:animated];
    self.hidesBottomBarWhenPushed = NO;
}

- (void)dealloc {
    _locService = nil;
    _mapView = nil;
    
    [[NSNotificationCenter defaultCenter] removeObserver:self name:AFNetworkingReachabilityDidChangeNotification object:nil];
    [[NSNotificationCenter defaultCenter] removeObserver:self name:loginSucceedNotification object:nil];
    [[NSNotificationCenter defaultCenter] removeObserver:self name:acceptTaskNotification object:nil];
    [[NSNotificationCenter defaultCenter] removeObserver:self name:tokenErrornNedLoginNotification object:nil];
    [[NSNotificationCenter defaultCenter] removeObserver:self name:cancellationNotification object:nil];
}

- (void)listeningNotification {
    
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(netWorkStatusChange:) name:AFNetworkingReachabilityDidChangeNotification object:nil];
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(loginSucceed) name:loginSucceedNotification object:nil];
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(acceptTask) name:acceptTaskNotification object:nil];
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(exitLogin) name:tokenErrornNedLoginNotification object:nil];
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(exitLogin) name:cancellationNotification object:nil];
}

- (void)exitLogin {
    self.starCountLable.text = @"0";
    [self.pushTaskView removeFromSuperview];
    [self removeTaskData];
    [self removeOngoingTaskData];
}

#pragma mark - 登录状态错误或者是退出登录
- (void)removeOngoingTaskData {
    [_mapView removeAnnotations:self.taskLocationAnnotationDatas];
    [_mapView removeOverlays:self.taskLocationCircles];
    
    [self.taskLocationAnnotationDatas removeAllObjects];
    [self.taskLocationCircles removeAllObjects];
    
    self.meLocationAnnotationView.showPrompt = NO;
    self.enterTaskCount = 0;
}

- (void)removeTaskData {
    [self.mapView removeAnnotations:self.starAnnotationDatas];
    [self.starAnnotationDatas removeAllObjects];
    self.taskDatas = [NSArray array];
}

- (void)acceptTask {
    self.refresh = NO;
    [self loadOngoingTaskData];
}

- (void)loginSucceed {
    self.enterTaskCount = 0;
    self.refresh = NO;
    [self loadTotalStarCountData];
    [self loadADStarData];
}

- (void)refreshData {
    self.refresh = NO; //NO 刷新任务位置
    [self loadSuspensionData];
    [self loadADStarData];
}

- (NSInteger)ADStarRadius {
    NSInteger zoomLevel = floor(_mapView.zoomLevel);
    NSDictionary *dict = self.ADStarRadiusDict[[NSString stringWithFormat:@"%ld", (long)zoomLevel]];
    CGFloat size = [dict[@"size"] floatValue];
    NSInteger distance = [dict[@"distance"] integerValue];
    return  (KScreenWidth * 0.5 - 100) / size * distance;
}

#pragma mark - 所有的标注数据
- (void)loadAnnotationData {
    self.refresh = NO;
    [self loadTotalStarCountData];
    [self loadADStarData];
}

- (void)viewWillAppear:(BOOL)animated {
    [super viewWillAppear:animated];
    [self loadTotalStarCountData];
    [self loadPushTaskData];
    [self loadUserInfoData];
    
}

#pragma mark - 网络状态改变
- (void)netWorkStatusChange:(NSNotification *)notification {
    
    if ([XLGlobalFunc globalFunc].firstInstallat) return;
    
    id status = notification.userInfo[@"AFNetworkingReachabilityNotificationStatusItem"];
    if ([status intValue] == 0 || [status intValue] == -1) {
        [XLGlobalFunc globalFunc].brokenNetwork = YES;
        
        _hud = [self showLogMessage:@"网络错误"];
        
    } else {
        [_hud hideHUDAfter:0];
        [XLGlobalFunc globalFunc].brokenNetwork = NO;
        if ([status intValue] == AFNetworkReachabilityStatusReachableViaWWAN) {
            [XLGlobalFunc globalFunc].networkStatus = [UIDevice telephonyNetwork];
        } else if ([status intValue] == AFNetworkReachabilityStatusReachableViaWiFi) {
            [XLGlobalFunc globalFunc].networkStatus = @"WiFi";
        }
    };
}

#pragma mark - 版本更新
- (void)versionUpdate {
    [XLVersionCheck versionCheckWithResponseData:^(BOOL updata, BOOL isForced, NSString *title, NSString *message) {
        if (updata && ![USERDEFAULTS boolForKey:firstOpen]) {
            title = @"发现新版本";
            UIAlertController *alert = [UIAlertController alertControllerWithTitle:title message:message preferredStyle:UIAlertControllerStyleAlert];
            if (!isForced) [alert addAction:[UIAlertAction actionWithTitle:@"取消" style:UIAlertActionStyleDefault handler:nil]];
            [alert addAction:[UIAlertAction actionWithTitle:@"立即升级" style:UIAlertActionStyleDefault handler:^(UIAlertAction * _Nonnull action) {
                [[UIApplication sharedApplication] openURL:[NSURL URLWithString:updataUrlStr]];
                [self presentViewController:alert animated:YES completion:nil];
            }]];
            [self presentViewController:alert animated:YES completion:nil];
        }
        
    } noDataBlock:^{}];
    
}


#pragma mark - 加载总共的星星数量
- (void)loadTotalStarCountData {
    
     __block CGFloat width = 85;
    
    if (![USERDEFAULTS boolForKey:login]) {
        [self.starContentView updateConstraints:^(MASConstraintMaker *make) {
            make.width.offset(width);
        }];
        return;
    };
    
    [TJNetworkTool requestWithUrl:@"Account.Info" parameters:nil success:^(id data) {
        
        if ([data[@"code"] intValue] == 0) {
            NSString *availableStarPointString = [NSString stringWithFormat:@"%@", data[@"info"][@"availableStarPoint"]];
            width = [availableStarPointString getStringSizeWithWidth:KScreenWidth fontSize:15].width;
            width = width + self.starImageView.bounds.size.width + 13;
            
            self.starCountLable.text = availableStarPointString;
            self.availableStarPoint = availableStarPointString;
        } else {
            self.starCountLable.text = @"0";
        }
        
        [self.starContentView updateConstraints:^(MASConstraintMaker *make) {
            make.width.offset(MAX(85, width));
        }];
        
    } failure:^(id error) {}];
}

- (NSDictionary *)ADStarRadiusDict {
    if (!_ADStarRadiusDict) {
        _ADStarRadiusDict = @{
                              @"4" : @{
                                      @"size": @41,
                                      @"distance": @1000000
                                      },
                              @"5" : @{
                                      @"size": @36,
                                      @"distance": @500000
                                      },
                              @"6" : @{
                                      @"size": @36,
                                      @"distance": @200000
                                      },
                              @"7" : @{
                                      @"size": @35,
                                      @"distance": @100000
                                      },
                              @"8" : @{
                                      @"size": @30,
                                      @"distance": @50000
                                      },
                              @"9" : @{
                                      @"size": @30,
                                      @"distance": @25000
                                      },
                              @"10" : @{
                                      @"size": @43.6,
                                      @"distance": @20000
                                      },
                              @"11" : @{
                                      @"size": @43.6,
                                      @"distance": @10000
                                      },
                              @"12" : @{
                                      @"size": @43.7,
                                      @"distance": @5000
                                      },
                              @"13" : @{
                                      @"size": @34.8,
                                      @"distance": @2000
                                      },
                              @"14" : @{
                                      @"size": @34.8,
                                      @"distance": @1000
                                      },
                              @"15" : @{
                                      @"size": @34.8,
                                      @"distance": @500
                                      },
                              @"16" : @{
                                      @"size": @30,
                                      @"distance": @200
                                      },
                              @"17" : @{
                                      @"size": @28,
                                      @"distance": @100
                                      },
                              @"18" : @{
                                      @"size": @28,
                                      @"distance": @50
                                      },
                              @"19" : @{
                                      @"size": @22.3,
                                      @"distance": @20
                                      },
                              @"20" : @{
                                      @"size": @22.3,
                                      @"distance": @10
                                      },
                              @"21" : @{
                                      @"size": @22.3,
                                      @"distance": @5
                                      },
                              };
    }
    return _ADStarRadiusDict;
}

@end
