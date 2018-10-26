//
//  MBProgressHUD+category.m
//  HUD使用
//
//  Created by 夏铁军 on 16/12/3.
//  Copyright © 2016年 qianhezi. All rights reserved.
//

#import "MBProgressHUD+TJ.h"

#define hudOpacity 0.7f
#define Font [UIFont systemFontOfSize:13]

@implementation MBProgressHUD (TJ)


+ (MBProgressHUD *)showLogining:(UIView *)view  message:(NSString *)message {
    MBProgressHUD *hud = [self showHUDAddedTo:view message:message mode:MBProgressHUDModeIndeterminate];
    return hud;
}

+ (MBProgressHUD *)showHUDAddedTo:(UIView *)view message:(NSString *)message mode:(MBProgressHUDMode)mode {
    MBProgressHUD *hud = [MBProgressHUD showHUDAddedTo:view animated:YES];
    hud.opacity = hudOpacity;
    hud.mode = mode;
    hud.opacity = 0.7;
    hud.detailsLabelText = message;
    hud.detailsLabelFont = Font;
    hud.removeFromSuperViewOnHide = YES;
    return hud;
}

+ (MBProgressHUD *)showHUDAddedTo:(UIView *)view messagqe:(NSString *)message {
    MBProgressHUD *hud = [self showHUDAddedTo:view message:message mode:MBProgressHUDModeText];
    hud.cornerRadius = 0;
    hud.opacity = 0.7;
    hud.margin = 12;
    [hud hideHUDAfter:1.5];
    return hud;
}

+ (MBProgressHUD *)showLogWithmessagqe:(NSString *)message {
    UIWindow *view = [UIApplication sharedApplication].keyWindow;
    MBProgressHUD *hud = [self showHUDAddedTo:view message:message mode:MBProgressHUDModeText];
    hud.cornerRadius = 0;
    hud.opacity = 0.7;
    hud.margin = 12;
    return hud;
}

- (void)hideHUDAfter:(NSTimeInterval)timeInterval {
    if (timeInterval) {
        dispatch_after(dispatch_time(DISPATCH_TIME_NOW, (int64_t)(timeInterval * NSEC_PER_SEC)), dispatch_get_main_queue(), ^{
            [self removeFromSuperview];
        });
    } else {
        [self removeFromSuperview];
    }
}


@end

