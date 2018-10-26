//
//  UIColor+category.h
//  categary
//
//  Created by 夏铁军 on 15/11/6.
//  Copyright © 2015年 tiaowang. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface UIColor (category)

#pragma mark - 颜色函数

+ (nonnull UIColor *)tj_randomColor;
+ (nonnull UIColor *)tj_backgroundColor;
+ (nonnull UIColor *)tj_mainColor;
+ (nonnull UIColor *)tj_separatorColor;
+ (nonnull UIColor *)tj_maskColor;
+ (nonnull CGColorRef)tj_borderColor;
+ (nonnull UIColor *)colorwithHexString:(nonnull NSString *)color;
+ (nonnull UIColor *)tj_colorWithHex:(u_int32_t)hex;
+ (nonnull UIColor *)tj_colorWithRed:(u_int8_t)red green:(u_int8_t)green blue:(u_int8_t)blue;

@end
