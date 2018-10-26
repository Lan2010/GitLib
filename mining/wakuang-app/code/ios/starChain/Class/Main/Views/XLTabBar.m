//
//  XLTabBar.m
//  starChain
//
//  Created by rlx on 2018/6/8.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import "XLTabBar.h"

@implementation XLTabBar


- (void)layoutSubviews {
    [super layoutSubviews];
    

    
    CGSize iconSize = [UIImage imageNamed:@"home"].size; //除以分辨率
    
    NSLog(@"iconSize = %@", NSStringFromCGSize(iconSize));
    
    [self.subviews enumerateObjectsUsingBlock:^(__kindof UIView * _Nonnull view, NSUInteger idx, BOOL * _Nonnull stop) {
        if([NSStringFromClass([view class]) containsString:@"UITabBarButton"]) {
            
            NSLog(@"UITabBarButtonFrame = %@", NSStringFromCGRect(view.frame));
            
            
            
            [view.subviews enumerateObjectsUsingBlock:^(__kindof UIView * _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
                if([NSStringFromClass([obj class]) containsString:@"UITabBarSwappableImageView"]) { //图片
                    TJLog(@"tabObj%@", obj);
                    [obj makeConstraints:^(MASConstraintMaker *make) {
                        make.size.equalTo(iconSize);
                        make.top.offset(5);
                        make.centerY.offset(0);
                    }];                    
                } else {//lable
                    [obj removeFromSuperview];
                }
            }];
        }
    }];
    
}

@end
