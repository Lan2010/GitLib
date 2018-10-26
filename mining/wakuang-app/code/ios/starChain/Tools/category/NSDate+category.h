//
//  NSDate+category.h
//  starChain
//
//  Created by rlx on 2018/7/18.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface NSDate (category)

@property (readonly) NSInteger year;    // 年
@property (readonly) NSInteger month;   // 月
@property (readonly) NSInteger day;     // 日
@property (readonly) NSInteger hour;    // 时
@property (readonly) NSInteger minute;  // 分
@property (readonly) NSInteger second;  // 秒
@property (readonly) NSInteger weekday; // 星期

+ (NSUInteger)getDaysInYear:(NSInteger)year month:(NSInteger)month;
+ (NSUInteger)getDaysInYear2:(NSInteger)year month:(NSInteger)month;

@end
