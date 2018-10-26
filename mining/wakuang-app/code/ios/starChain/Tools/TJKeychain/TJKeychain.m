//
//  TJKeychain.m
//  钥匙串
//
//  Created by 夏铁军 on 15/12/3.
//  Copyright © 2016年 tiaowang. All rights reserved.
//

#import "TJKeychain.h"
#import "SSKeychain.h"

@implementation TJKeychain

static NSString * const serviceName = @"cn.sischain.starChain";//应用的名字改了的话就取token不了

+ (void)tj_setObject:(NSString *)Object forKey:(NSString *)key {
    [SSKeychain setPassword:Object forService:serviceName account:key];
}

+ (id)tj_objectForKey:(NSString *)key {
    NSString *tokenStr = [SSKeychain passwordForService:serviceName account:key];
    tokenStr = (tokenStr) ? tokenStr : @"";
    return tokenStr;
}

+ (void)tj_deleteForKey:(NSString *)key {
    [SSKeychain deletePasswordForService:serviceName account:key];
}


@end
