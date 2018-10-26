//
//  UIViewController+category.h
//  qhz
//
//  Created by 夏铁军 on 16/11/20.
//  Copyright © 2016年 qhz. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "MBProgressHUD+TJ.h"

@interface UIViewController (category)

- (UIViewController *)presentViewControllerWithName:(NSString *)controllerName title:(NSString *)title animated:(BOOL)animated;

- (UIViewController *)presentViewControllerWithName:(NSString *)controllerName title:(NSString *)title;

- (void)dismissToRootViewController;

- (UINavigationController *)currentNavigationController;

- (UIViewController *)currentViewController;

- (void)loadUnableInteractionWebPageWithUrlString:(NSString *)urlString title:(NSString *)title;

- (void)loadInteractionWebPageWithUrlString:(NSString *)urlString title:(NSString *)title;

- (void)alertWithTitle:(NSString *)title message:(NSString *)message leftButtonName:(NSString *)leftButtonName rightButtonName:(NSString *)rightButtonName leftButtonBlock:(void (^)(void))leftButtonBlock rightButtonBlock:(void (^)(void))rightButtonBlock;

- (void)alertWithmessage:(NSString *)message leftButtonName:(NSString *)leftButtonName rightButtonName:(NSString *)rightButtonName leftButtonBlock:(void (^)(void))leftButtonBlock rightButtonBlock:(void (^)(void))rightButtonBlock;

- (MBProgressHUD *)showLogMessage:(NSString *)message;

- (MBProgressHUD *)showMessage:(NSString *)message;

- (MBProgressHUD *)showMessageAutoHide:(NSString *)message;

@end
