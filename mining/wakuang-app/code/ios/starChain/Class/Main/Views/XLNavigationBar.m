//
//  XLNavigationBar.m
//  starChain
//
//  Created by rlx on 2018/6/6.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import "XLNavigationBar.h"

@implementation XLNavigationBar

- (void)layoutSubviews {
    [super layoutSubviews];
    
    if (@available(iOS 11.0, *)) {
        self.frame = CGRectMake(0, 0, CGRectGetWidth(self.frame), TOPMAGRIN);
        for (UIView *view in self.subviews) {
            if([NSStringFromClass([view class]) containsString:@"Background"]) {
                view.frame = self.frame;
                [view.subviews enumerateObjectsUsingBlock:^(__kindof UIView * _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
                    if ([obj isKindOfClass:[UIImageView class]]) {
                        [obj removeFromSuperview];
                    }
                }];
            } else if ([NSStringFromClass([view class]) containsString:@"ContentView"]) {
                CGRect frame = view.frame;
                frame.origin.y = StatusBarHight;
                frame.size.height = self.bounds.size.height - frame.origin.y;
                view.frame = frame;
            }
        }
    }
}


- (void)setAlpha:(CGFloat)alpha {
 
    for (UIView *view in self.subviews) {
        if([NSStringFromClass([view class]) containsString:@"Background"]) {
            view.alpha = 0;
        } else if ([NSStringFromClass([view class]) containsString:@"ContentView"]) {
            view.alpha = 0;
        }
    }
}


@end
