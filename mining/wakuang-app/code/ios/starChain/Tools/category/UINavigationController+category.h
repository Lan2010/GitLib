//
//  UINavigationController+category.h
//  categary
//
//  Created by 夏铁军 on 15/11/6.
//  Copyright © 2015年 tiaowang. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface UINavigationController (category)


- (UIViewController *)pushViewControllerWithName:(NSString *)controllerName title:(NSString *)title animated:(BOOL)animated;

@end
