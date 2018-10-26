//
//  TJKeychain.h
//  钥匙串
//
//  Created by 夏铁军 on 15/12/3.
//  Copyright © 2016年 tiaowang. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface TJKeychain : NSObject

+ (void)tj_setObject:(NSString *)Object forKey:(NSString *)key;

+ (id)tj_objectForKey:(NSString *)key;

+ (void)tj_deleteForKey:(NSString *)key;

@end
