//
//  UIViewController+category.m
//  qhz
//
//  Created by 夏铁军 on 16/11/20.
//  Copyright © 2016年 qhz. All rights reserved.
//

#import "UIViewController+category.h"
#import "XLWkWebController.h"
#import "XLWebViewController.h"
#import "XLBaseController.h"
#import "XLAlertViewController.h"

@implementation UIViewController (category)

- (UIViewController *)presentViewControllerWithName:(NSString *)controllerName title:(NSString *)title animated:(BOOL)animated {
    Class clsss = NSClassFromString(controllerName);
    UIViewController *controller = [[clsss alloc] init];
    if (title) {
        controller.title = title;
    }
    UINavigationController *navVC = [[UINavigationController alloc] initWithRootViewController:controller];
    [self presentViewController:navVC animated:animated completion:nil];
    return controller;
}

- (UIViewController *)presentViewControllerWithName:(NSString *)controllerName title:(NSString *)title {
    Class clsss = NSClassFromString(controllerName);
    UIViewController *controller = [[clsss alloc] init];
    if (title) {
        controller.title = title;
    }
    UINavigationController *navVC = [[UINavigationController alloc] initWithRootViewController:controller];
    [self presentViewController:navVC animated:YES completion:nil];
    return controller;
}

- (UINavigationController *)getCurrentNav{
    UINavigationController* nav = nil;
    if ([self isKindOfClass:[UINavigationController class]]) {
        nav = (id)self;
    } else {
        if ([self isKindOfClass:[UITabBarController class]]) {
            nav = [((UITabBarController*)self).selectedViewController getCurrentNav];
        } else {
            nav = self.navigationController;
        }
    }
    return nav;
}

//获取当前屏幕显示的viewcontroller
- (UIViewController *)currentViewController {
    return [self getCurrentViewControllerFrom:[UIApplication sharedApplication].keyWindow.rootViewController];
}

- (UIViewController *)getCurrentViewControllerFrom:(UIViewController *)rootVC {
    UIViewController *currentVC;
    if ([rootVC presentedViewController]) {
        rootVC = [rootVC presentedViewController];
    }
    if ([rootVC isKindOfClass:[UITabBarController class]]) {
        currentVC = [self getCurrentViewControllerFrom:[(UITabBarController *)rootVC selectedViewController]];
    } else if ([rootVC isKindOfClass:[UINavigationController class]]){
        currentVC = [self getCurrentViewControllerFrom:[(UINavigationController *)rootVC topViewController]];
    } else {
        currentVC = rootVC;
    }
    return currentVC;
}

- (void)alertWithTitle:(NSString *)title message:(NSString *)message leftButtonName:(NSString *)leftButtonName rightButtonName:(NSString *)rightButtonName leftButtonBlock:(void (^)(void))leftButtonBlock rightButtonBlock:(void (^)(void))rightButtonBlock {
    
    UIAlertController *alert = [UIAlertController alertControllerWithTitle:title message:message preferredStyle:UIAlertControllerStyleAlert];
    if (leftButtonName) {
        [alert addAction:[UIAlertAction actionWithTitle:leftButtonName style:UIAlertActionStyleDefault handler:^(UIAlertAction * _Nonnull action) {
            if (leftButtonBlock) leftButtonBlock();
        }]];
    }
    
    if (rightButtonName) {
        [alert addAction:[UIAlertAction actionWithTitle:rightButtonName style:UIAlertActionStyleDefault handler:^(UIAlertAction * _Nonnull action) {
            if (rightButtonBlock) rightButtonBlock();
        }]];
    }
    
    [self presentViewController:alert animated:YES completion:nil];
}

- (void)alertWithmessage:(NSString *)message leftButtonName:(NSString *)leftButtonName rightButtonName:(NSString *)rightButtonName leftButtonBlock:(void (^)(void))leftButtonBlock rightButtonBlock:(void (^)(void))rightButtonBlock {
    
    NSMutableArray *buttonTitles = [NSMutableArray array];
    if (leftButtonName) [buttonTitles addObject:leftButtonName];
    if (rightButtonName) [buttonTitles addObject:rightButtonName];
    
    XLAlertViewController *alertViewController = [XLAlertViewController alertViewWithMessage:message buttonTitles:buttonTitles];
    [self presentViewController:alertViewController animated:NO completion:nil];
    [alertViewController setDidClickButtonBlock:^(UIButton *button) {
        if (button.tag == 1000) {
            if (leftButtonBlock) leftButtonBlock();
            
        } else {
            if (rightButtonBlock) rightButtonBlock();
        }
    }];
    
}

- (void)dismissToRootViewController {
    UIViewController *vc = self;
    while (vc.presentingViewController) {
        vc = vc.presentingViewController;
    }
    [vc dismissViewControllerAnimated:YES completion:nil];
}

- (UINavigationController *)currentNavigationController {
    UITabBarController *tabBar = (UITabBarController *)[UIApplication sharedApplication].keyWindow.rootViewController;
    UINavigationController *navVC = tabBar.selectedViewController;
    return navVC;
}

- (void)loadUnableInteractionWebPageWithUrlString:(NSString *)urlString title:(NSString *)title {
    
    NSLog(@"dictUrl = %@", urlString);
    
    if ([XLGlobalFunc globalFunc].brokenNetwork) {
        urlString = @"";
    } else if (!urlString.length) {
        return;
    }
    XLWkWebController *wkWebController = [[XLWkWebController alloc] init];
    [wkWebController loadWebPageWithTitle:title urlString:urlString];
    wkWebController.hidesBottomBarWhenPushed = YES;
    [self.navigationController pushViewController:wkWebController animated:YES];
}

- (void)loadInteractionWebPageWithUrlString:(NSString *)urlString title:(NSString *)title {
    
    NSLog(@"dictUrl = %@", urlString);
    
    if ([XLGlobalFunc globalFunc].brokenNetwork) {
        urlString = @"";
    } else if (!urlString.length) {
        return;
    }
    
    XLWebViewController *webController = [[XLWebViewController alloc] init];
    [webController loadWebPageWithTitle:title urlString:urlString];
    webController.hidesBottomBarWhenPushed = YES;
    [self.navigationController pushViewController:webController animated:YES];
}

- (MBProgressHUD *)showLogMessage:(NSString *)message {
    MBProgressHUD *hud = [MBProgressHUD showHUDAddedTo:self.view message:message mode:MBProgressHUDModeText];
    hud.cornerRadius = 0;
    hud.opacity = 0.7;
    hud.margin = 12;
    return hud;
}

- (MBProgressHUD *)showMessage:(NSString *)message {
    return [MBProgressHUD showLogining:self.view message:message];
}

- (MBProgressHUD *)showMessageAutoHide:(NSString *)message {
    return [MBProgressHUD showHUDAddedTo:self.view messagqe:message];
}

@end
