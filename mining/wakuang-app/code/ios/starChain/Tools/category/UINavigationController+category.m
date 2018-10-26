//
//  UINavigationController+category.m
//  categary
//
//  Created by 夏铁军 on 15/11/6.
//  Copyright © 2015年 tiaowang. All rights reserved.
//

#import "UINavigationController+category.h"


@implementation UINavigationController (category)

- (UIViewController *)pushViewControllerWithName:(NSString *)controllerName title:(NSString *)title animated:(BOOL)animated {
    Class clsss = NSClassFromString(controllerName);
    UIViewController *controller = [[clsss alloc] init];
    if (title) controller.navigationItem.title = title;
    controller.hidesBottomBarWhenPushed = YES;
    [self pushViewController:controller animated:animated];
    return controller;
}

@end

