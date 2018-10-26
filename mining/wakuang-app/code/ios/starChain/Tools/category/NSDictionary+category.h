//
//  NSDictionary+category.h
//  qhz
//
//  Created by 夏铁军 on 16/12/14.
//  Copyright © 2016年 qhz. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface NSDictionary (category)


- (NSString *)jsonString;

- (NSString *)keyWithValueAscendingNoAddKey:(NSString *)AddKey;

@end
