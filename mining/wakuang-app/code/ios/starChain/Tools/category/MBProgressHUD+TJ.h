//
//  MBProgressHUD+category.h
//  HUD使用
//
//  Created by 夏铁军 on 16/12/3.
//  Copyright © 2016年 qianhezi. All rights reserved.
//

#import <MBProgressHUD/MBProgressHUD.h>

@interface MBProgressHUD (TJ)

- (void)hideHUDAfter:(NSTimeInterval)timeInterval;

+ (MBProgressHUD *)showLogining:(UIView *)view message:(NSString *)message;
+ (MBProgressHUD *)showLogWithmessagqe:(NSString *)message;
+ (MBProgressHUD *)showHUDAddedTo:(UIView *)view messagqe:(NSString *)message;
+ (MBProgressHUD *)showHUDAddedTo:(UIView *)view message:(NSString *)message mode:(MBProgressHUDMode)mode;


@end

