//
//  UIBarButtonItem+category.h
//  starChain
//
//  Created by rlx on 2018/6/6.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface UIBarButtonItem (category)

+ (nonnull UIBarButtonItem *)spacerBarItem;

+ (nonnull UIBarButtonItem *)barButtonItemWithTitle:(nonnull NSString *)title target:(nullable id)target action:(nullable SEL)action;

+ (nonnull UIBarButtonItem *)barButtonItemWithTitle:(nonnull NSString *)title color:(nonnull UIColor *)color target:(nullable id)target action:(nullable SEL)action;

+ (nonnull UIBarButtonItem *)barButtonItemWithImageName:(nonnull NSString *)imageName size:(CGSize)size target:(nullable id)target action:(nullable SEL)action;

+ (NSArray <UIBarButtonItem *>*)backBarItemWithTarget:(nullable id)target action:(nullable SEL)action;

+ (NSArray <UIBarButtonItem *>*)backBarItemImage:(UIImage *)image target:(nullable id)target action:(nullable SEL)action;

@end
