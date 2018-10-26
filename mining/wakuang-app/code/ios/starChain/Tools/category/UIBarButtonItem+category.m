//
//  UIBarButtonItem+category.m
//  starChain
//
//  Created by rlx on 2018/6/6.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import "UIBarButtonItem+category.h"

@implementation UIBarButtonItem (category)

+ (nonnull UIBarButtonItem *)barButtonItemWithTitle:(nonnull NSString *)title target:(nullable id)target action:(nullable SEL)action {
    UIBarButtonItem *barButtonItem = [[UIBarButtonItem alloc] initWithTitle:title style:UIBarButtonItemStylePlain target:target action:action];
    [barButtonItem setTitleTextAttributes:@{NSFontAttributeName: [UIFont systemFontOfSize:15]} forState:UIControlStateNormal];
    return barButtonItem;
}

+ (nonnull UIBarButtonItem *)barButtonItemWithImageName:(nonnull NSString *)imageName size:(CGSize)size target:(nullable id)target action:(nullable SEL)action {
    UIButton *button = [[UIButton alloc] initWithFrame:CGRectMake(0, 0, size.width, size.height)];
    UIImage *normalImage = [UIImage imageNamed:imageName];
    [button setImage:normalImage forState:UIControlStateNormal];
    normalImage = [normalImage changerAlpha:0.3];
    [button setImage:normalImage forState: UIControlStateHighlighted];
    [button addTarget:target action:action  forControlEvents:UIControlEventTouchUpInside];
    UIBarButtonItem *barButtonItem = [[UIBarButtonItem alloc] initWithCustomView:button];
    return barButtonItem;
}

+ (nonnull UIBarButtonItem *)barButtonItemWithTitle:(nonnull NSString *)title color:(nonnull UIColor *)color target:(nullable id)target action:(nullable SEL)action {
    CGSize titleSize = [title getStringSizeWithWidth:200 fontSize:15];
    UIButton *button = [[UIButton alloc] initWithFrame:CGRectMake(0, 0, titleSize.width + 2, titleSize.height + 2)];
    button.titleLabel.font = [UIFont systemFontOfSize:15];
    [button setTitle:title forState:UIControlStateNormal];
    [button setTitleColor:color forState:UIControlStateNormal];
    [button setTitleColor:[color colorWithAlphaComponent:0.3] forState:UIControlStateHighlighted];
    [button addTarget:target action:action  forControlEvents:UIControlEventTouchUpInside];
    UIBarButtonItem *barButtonItem = [[UIBarButtonItem alloc] initWithCustomView:button];
    return barButtonItem;
}

+ (nonnull UIBarButtonItem *)spacerBarItem {
    UIBarButtonItem *negativeSpacer = [[UIBarButtonItem alloc] initWithBarButtonSystemItem:UIBarButtonSystemItemFixedSpace target:self action:nil];
    negativeSpacer.width = -5;
    return negativeSpacer;
}

+ (NSArray <UIBarButtonItem *>*)backBarItemWithTarget:(nullable id)target action:(nullable SEL)action {
    return [self backBarItemImage:[UIImage imageNamed:@"fh-icon"] target:target action:action];
}

+ (NSArray <UIBarButtonItem *>*)backBarItemImage:(UIImage *)image target:(nullable id)target action:(nullable SEL)action {
    UIBarButtonItem *backBarItem = [[UIBarButtonItem alloc] initWithImage:image style:UIBarButtonItemStylePlain target:target action:action];
    UIButton *button = [[UIButton alloc] initWithFrame:CGRectMake(0, 0, 50, 30)];
    UIBarButtonItem *placeholderButton = [[UIBarButtonItem alloc] initWithCustomView:button];
    if (iOS11) {
        [button addTarget:target action:action forControlEvents:UIControlEventTouchUpInside];
        return @[backBarItem, placeholderButton];
    } else {
        return  @[[UIBarButtonItem spacerBarItem], backBarItem];
    }
}

@end
