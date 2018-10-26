//
//  AppDelegate.m
//  starChain
//
//  Created by rlx on 2018/6/6.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import "AppDelegate.h"
#import "XLMainController.h"
#import <WebKit/WebKit.h>
#import <Bugly/Bugly.h>
#import "QHZGuidePageView.h"
#import "XLADLaunch.h"
#import "QHZGuidePageView.h"

#import <BaiduMapAPI_Map/BMKMapComponent.h>

#import <UMShare/UMShare.h>
#import <UMCommonLog/UMCommonLogHeaders.h>
#import <UMCommon/UMCommon.h>


@interface AppDelegate ()

@property (strong, nonatomic) WKWebView *wKwebView;
@property (strong, nonatomic) NSDate *enterBackgroundTime;
@property (strong, nonatomic) BMKMapManager *mapManager;


@end


@implementation AppDelegate


- (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions {
    [self appLaunching];
    [self initWindow];
    [self initBaiduMap];
    [self bugCollect];
    [self ListeningNetworkStatu];
    [self registerShare];
    return YES;
}

- (void)initWindow {
    self.window = [[UIWindow alloc] initWithFrame:KScreenBounds];
    self.window.backgroundColor = [UIColor whiteColor];
    self.window.rootViewController = [[XLMainController alloc] init];
    [self.window makeKeyAndVisible];
    BOOL isFirstOpen = [XLGlobalFunc globalFunc].isFisrtOpen;
    [USERDEFAULTS setBool:isFirstOpen forKey:firstOpen];
    (isFirstOpen) ? [QHZGuidePageView showGuidePage] : [XLADLaunch showAD];
}

- (void)registerShare {
    [UMCommonLogManager setUpUMCommonLogManager];
    [UMConfigure setLogEnabled:YES];
    [UMConfigure initWithAppkey:@"5b31a5418f4a9d5d8c00001a" channel:nil];
    [[UMSocialManager defaultManager] setPlaform:UMSocialPlatformType_WechatSession appKey:@"wxa36622d358d67adf" appSecret:@"5f4c68ce92216f878ca078dd756426cd" redirectURL:nil];
    [[UMSocialManager defaultManager] setPlaform:UMSocialPlatformType_WechatTimeLine appKey:@"wxa36622d358d67adf" appSecret:@"5f4c68ce92216f878ca078dd756426cd" redirectURL:nil];
    [[UMSocialManager defaultManager] setPlaform:UMSocialPlatformType_Sina appKey:@"1498832838"  appSecret:@"5d7e81cf1d4d6b98296a6528c2d738ab" redirectURL:@"https://www.qianhezi.cn"];
}

- (void)initBaiduMap {
    _mapManager = [[BMKMapManager alloc]init];
    if ([BMKMapManager setCoordinateTypeUsedInBaiduMapSDK:BMK_COORDTYPE_BD09LL]) {
        NSLog(@"经纬度类型设置成功");
    } else {
        NSLog(@"经纬度类型设置失败");
    }
    BOOL ret = [_mapManager start:@"sDwqixdvGIxYNrvimvjcS55FPM3FCYcD" generalDelegate:nil];
    if (!ret) {
        NSLog(@"manager start failed!");
    }
    
}

- (void)setUsetAgent {
    
    _wKwebView = [[WKWebView alloc] initWithFrame:CGRectZero];
    [_wKwebView evaluateJavaScript:@"navigator.userAgent" completionHandler:^(id result, NSError *error) {
        NSString *newUA = [NSString stringWithFormat:@"Qianhezi%@",result];
        [[NSUserDefaults standardUserDefaults] registerDefaults: @{@"UserAgent":newUA}];
    }];
    
    UIWebView *webView = [[UIWebView alloc] initWithFrame:CGRectZero];
    NSString *originUA = [webView stringByEvaluatingJavaScriptFromString:@"navigator.userAgent"];
    NSString *newUA = [NSString stringWithFormat:@"Qianhezi%@",originUA];
    [[NSUserDefaults standardUserDefaults] registerDefaults: @{@"UserAgent":newUA}];
}

#pragma mark -  奔溃日志收集
- (void)bugCollect {
    [Bugly startWithAppId:@"ce00d91650"];
}

- (void)ListeningNetworkStatu {
    [[AFNetworkReachabilityManager sharedManager] startMonitoring];
}

- (void)applicationWillTerminate:(UIApplication *)application {
    NSLog(@"程序被杀死");
    [self appWillTerminate];
}

- (void)applicationWillEnterForeground:(UIApplication *)application {
    [XLADLaunch checkNewAd];    
}

- (void)appWillTerminate {
    [self appLaunchingOrTerminateWithType:@"0"];
}

- (void)appLaunching {
    [self appLaunchingOrTerminateWithType:@"1"];
}

- (void)appLaunchingOrTerminateWithType:(NSString *)type {
    NSMutableDictionary *parameters = [NSMutableDictionary dictionary];
    parameters[@"type"] = type;
    NSString *mobile = [USERDEFAULTS objectForKey:phone];
    parameters[@"mobile"] = (mobile.length) ? mobile : @"";
    [TJNetworkTool requestWithUrl:@"Common.AppStopStar" parameters:parameters success:^(id data) {} failure:^(id error) {}];
}

@end
