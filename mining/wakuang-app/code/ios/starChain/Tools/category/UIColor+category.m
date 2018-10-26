//
//  UIColor+category.m
//  categary
//
//  Created by 夏铁军 on 15/11/6.
//  Copyright © 2015年 tiaowang. All rights reserved.
//

#import "UIColor+category.h"

@implementation UIColor (category)

+ (nonnull UIColor *)tj_backgroundColor {
    return UIColor_Hex(0xf2f2f2);
}

+ (nonnull UIColor *)tj_mainColor {
    return UIColor_Hex(0x6963C0);
}

+ (nonnull UIColor *)tj_separatorColor {
    return [UIColor_Hex(0xdddddd) colorWithAlphaComponent:0.8];
}

+ (nonnull UIColor *)tj_maskColor {
    return [[UIColor blackColor] colorWithAlphaComponent:0.45];
}

+ (nonnull CGColorRef)tj_borderColor {
    return UIColor_Hex(0xdadada).CGColor;
}

#pragma mark - 颜色函数
+ (nonnull UIColor *)tj_colorWithHex:(u_int32_t)hex {
    u_int8_t red = (hex & 0xFF0000) >> 16;
    u_int8_t green = (hex & 0x00FF00) >> 8;
    u_int8_t blue = hex & 0x0000FF;
    
    return [UIColor tj_colorWithRed:red green:green blue:blue];
}

+ (nonnull UIColor *)tj_colorWithRed:(u_int8_t)red green:(u_int8_t)green blue:(u_int8_t)blue {
    return [UIColor colorWithRed:red / 255.0 green:green / 255.0 blue:blue / 255.0 alpha:1.0];
}

+ (nonnull UIColor *)tj_randomColor {
    u_int8_t red = arc4random_uniform(256);
    u_int8_t green = arc4random_uniform(256);
    u_int8_t blue = arc4random_uniform(256);
    
    return [UIColor tj_colorWithRed:red green:green blue:blue];
}

+ (nonnull UIColor *)colorwithHexString:(NSString *)color {
    NSString *cString = [[color stringByTrimmingCharactersInSet:[NSCharacterSet whitespaceAndNewlineCharacterSet]] uppercaseString];
    // String should be 6 or 8 characters
    if ([cString length] < 6) {
        return [UIColor clearColor];
    }
    // strip 0X if it appears
    if ([cString hasPrefix:@"0X"])
        cString = [cString substringFromIndex:2];
    if ([cString hasPrefix:@"#"])
        cString = [cString substringFromIndex:1];
    if ([cString length] != 6)
        return [UIColor clearColor];
    
    // Separate into r, g, b substrings
    NSRange range;
    range.location = 0;
    range.length = 2;
    
    //r
    NSString *rString = [cString substringWithRange:range];
    
    //g
    range.location = 2;
    NSString *gString = [cString substringWithRange:range];
    
    //b
    range.location = 4;
    NSString *bString = [cString substringWithRange:range];
    
    // Scan values
    unsigned int r, g, b;
    [[NSScanner scannerWithString:rString] scanHexInt:&r];
    [[NSScanner scannerWithString:gString] scanHexInt:&g];
    [[NSScanner scannerWithString:bString] scanHexInt:&b];
    
    return [UIColor colorWithRed:((float) r / 255.0f) green:((float) g / 255.0f) blue:((float) b / 255.0f) alpha:1.0f];
}

@end
