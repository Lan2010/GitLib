//
//  TJADLaunch.m
//  qhz
//
//  Created by lei wei on 2017/4/11.
//  Copyright © 2017年 qhz. All rights reserved.
//

#import "XLADLaunch.h"
#import "XLMainController.h"
#import "XLBaseNavController.h"
#import "TJKeychain.h"
#import "XLWebViewController.h"
#import "QHZGuidePageView.h"

@interface XLADLaunch ()

@property (weak, nonatomic) UIButton *skipButton;
@property (weak, nonatomic) UIImageView *iconView;
@property (assign, nonatomic) NSInteger downCount;
@property (assign, nonatomic) BOOL isPushNotice;
@property (weak, nonatomic) NSTimer *timer;

@end


@implementation XLADLaunch

 
- (void)addAdvertisingView {
    
    UIImage *image = [self getADImage];
    if (!image) {
        [TJKeychain tj_deleteForKey:adVersion];
        return;
    }
 
    UIImageView *iconView = [[UIImageView alloc] initWithFrame:KeyWindow.bounds];
    [KeyWindow addSubview:iconView];
    iconView.userInteractionEnabled = YES;
    [iconView addTapGesturesWithTarget:self action:@selector(didClickAdImage)];
    iconView.image = image;
    self.iconView = iconView;
    
    //程序启动的时候就去沙盒中加载最新的数据, 没有的话就器请求, 有的话先判断是否是第一次打开,是第一次打开的话, 就不显示, 直接起下载
    self.downCount = 3;
    CGFloat topMagrin = (IPHONEX) ? 54 : 30;
    
    UIButton *skipButton = [UIButton buttonWithSuperView:iconView fontSize:13 color:[UIColor whiteColor] title:nil];
    skipButton.backgroundColor = [[UIColor blackColor] colorWithAlphaComponent:0.65];
    skipButton.frame = CGRectMake(KScreenWidth - 70, topMagrin, 60, 30);
    [skipButton shearRoundedCornersWithRadiu:skipButton.tj_height * 0.5];
    [skipButton addTarget:self action:@selector(removeAdvertisingView) forControlEvents:UIControlEventTouchUpInside];
    self.skipButton = skipButton;

    [self.skipButton setTitle:[NSString stringWithFormat:@"跳过: %ld",(long)self.downCount] forState:UIControlStateNormal];
    [self performSelector:@selector(addTimer) withObject:nil afterDelay:1];
    [XLADLaunch downloadADImage];
}

+ (void)showAD {
    [[[XLADLaunch alloc] init] addAdvertisingView];
}

- (void)didClickAdImage {
    [self removeAdvertisingView];
    XLMainController* rootVC = (XLMainController *)[UIApplication sharedApplication].keyWindow.rootViewController;
    XLBaseNavController *baseNav = rootVC.viewControllers[rootVC.selectedIndex];
    NSString *urlString = [USERDEFAULTS objectForKey:adActionUrl];
    if (adActionUrl.length) {
        XLWebViewController *webController = [[XLWebViewController alloc] init];
        [webController loadWebPageWithTitle:nil urlString:urlString];
        webController.hidesBottomBarWhenPushed = YES;
        [baseNav pushViewController:webController animated:YES];
    }
}

- (void)changeButtonTitle {
    if (self.downCount <= 1) {
        [self removeAdvertisingView];
    } else {
        self.downCount --;
        [self.skipButton setTitle:[NSString stringWithFormat:@"跳过: %ld",(long)self.downCount] forState:UIControlStateNormal];
    }
}

- (void)addTimer {
    NSTimer *timer = [NSTimer timerWithTimeInterval:1 target:self selector:@selector(changeButtonTitle) userInfo:nil repeats:YES];
    [[NSRunLoop currentRunLoop] addTimer:timer forMode:NSRunLoopCommonModes];
    self.timer = timer;
}

- (void)removeAdvertisingView {
    
    [self.timer invalidate];
    self.timer = nil;
    
    [USERDEFAULTS setBool:NO forKey:newAdImage];//如果有广告页,广告页显示完成后, 就没有新的广告页了
    
    [UIView transitionWithView:self.iconView duration:0.65 options:UIViewAnimationOptionCurveEaseOut animations:^{
        self.iconView.transform = CGAffineTransformMakeScale(1.6, 1.6);
        self.iconView.alpha = 0.0;
    } completion:^(BOOL finished) {
        //消失的时候和点击隐藏的时候都会调
        if (!self.isPushNotice) {
            [[NSNotificationCenter defaultCenter] postNotificationName:adHienCompleteNotification object:nil];
            self.isPushNotice = YES;
        }
        [self.iconView.subviews enumerateObjectsUsingBlock:^(__kindof UIView * _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
            [obj removeFromSuperview];
            obj = nil;
        }];
        
        [self.iconView removeFromSuperview];
     }];
    
}

- (UIImage *)getADImage {
    if ([XLGlobalFunc globalFunc].advertisingImage) {
        return [XLGlobalFunc globalFunc].advertisingImage;
    } else {
        return [UIImage imageWithContentsOfFile:ADIMAGEPATH];
    }
}

+ (void)checkNewAd {
    if ([USERDEFAULTS boolForKey:newAdImage]) {
        [self showAD];
    } else {
        [self downloadADImage];
    }
}

+ (void)downloadADImage {
    
    TJLog(@"开始下载新图片");

    [TJNetworkTool requestWithUrl:@"Banner.GetStartList" parameters:@{@"type": @167} success:^(id responseObject) {
        
        if (![responseObject[@"code"] intValue]) {
   
            NSUserDefaults *userDefaults = [NSUserDefaults standardUserDefaults];
            NSDictionary *info = responseObject[@"info"];
            NSString *imageUrl= info[@"banner_url"];
            NSString *actionUrl= info[@"link_url"];
            NSString *imageVersion = info[@"version"];
            
            //取出沙盒的imageVersion
            NSString *boxImageVersion = [userDefaults objectForKey:adVersion];
            
            TJLog(@"boxImageVersion = %@,imageVersion = %@", boxImageVersion, imageVersion);
            
            BOOL haveNewAD = (!boxImageVersion.length || ![boxImageVersion isEqualToString:imageVersion]);//括号一定不能少
            [userDefaults setBool:haveNewAD forKey:newAdImage];
            
            if (haveNewAD) {
                SDWebImageDownloader *downloader = [SDWebImageDownloader sharedDownloader];
                [downloader downloadImageWithURL:[NSURL URLWithString:imageUrl]  options:SDWebImageCacheMemoryOnly | SDWebImageContinueInBackground | SDWebImageRetryFailed progress:nil completed:^(UIImage *image, NSData *data, NSError *error, BOOL finished) {
                    if (image) {
                        [image asynDrawImageSize:CGSizeMake(KScreenWidth, KScreenHeight) color:[UIColor whiteColor] finishiBlock:^(UIImage * newImage) {
                            [UIImagePNGRepresentation(newImage) writeToFile:ADIMAGEPATH atomically:YES];
                            [XLGlobalFunc globalFunc].advertisingImage = newImage;//内存保存
                            [userDefaults setObject:imageVersion forKey:adVersion];
                            TJLog(@"保存图片完成: %@", newImage);
                        }];
                        if (actionUrl.length)  [userDefaults setObject:actionUrl forKey:adActionUrl];
                    }
              
                }];
            }
        }
    } failure:^(id error) {
        
    }];

}



@end
