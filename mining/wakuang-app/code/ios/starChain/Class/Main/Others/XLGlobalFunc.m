//
//  XLGlobalFunc.m
//  starChain
//
//  Created by rlx on 2018/6/7.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import "XLGlobalFunc.h"

@implementation XLGlobalFunc

static XLGlobalFunc *instance = nil;

+ (XLGlobalFunc *)globalFunc {
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        instance = [[XLGlobalFunc alloc] init];
    });
    return instance;
}

- (BOOL)isFisrtOpen {
    NSString *currentlyVersions = [NSBundle mainBundle].infoDictionary[@"CFBundleVersion"];
    NSUserDefaults *userDefaults = [NSUserDefaults standardUserDefaults];
    NSString *oldVersions = [userDefaults objectForKey:@"old_Versions"];
    TJLog(@"currentlyVersions = %@, oldVersions = %@",currentlyVersions, oldVersions);
    if (![currentlyVersions isEqualToString:oldVersions]) {
        [userDefaults setObject:currentlyVersions forKey:@"old_Versions"];
        return YES;
    } else {
        [userDefaults setObject:currentlyVersions forKey:@"old_Versions"];
        return NO;
    }
}

- (BOOL)firstInstallat {
    if (![USERDEFAULTS boolForKey:@"firstInstallat"]) {
        [USERDEFAULTS setBool:YES forKey:@"firstInstallat"];
        return YES;
    } else {
        return NO;
    }
}

@end
