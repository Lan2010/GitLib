//
//  XLVersionCheck.m
//  starChain
//
//  Created by rlx on 2018/6/13.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import "XLVersionCheck.h"

@implementation XLVersionCheck

+ (void)versionCheckWithResponseData:(void(^)(BOOL updata, BOOL isForced, NSString *title, NSString *message))responseBlock noDataBlock:(void (^)(void))noDataBlock {
    
    [TJNetworkTool requestWithUrl:@"Common.VersionInfo" parameters:[NSDictionary dictionary] success:^(id data) {
            
        if (![data[@"code"] intValue]) {
            
            NSString * serviceVersion = data[@"info"][@"version_code"];
            serviceVersion = [serviceVersion stringByReplacingOccurrencesOfString:@"." withString:@""];
            serviceVersion = [serviceVersion stringByReplacingOccurrencesOfString:@"," withString:@""];
            
            NSDictionary *infoDic = [[NSBundle mainBundle] infoDictionary];
            NSString *currentlyVersions = [infoDic objectForKey:@"CFBundleShortVersionString"];
            currentlyVersions = [currentlyVersions stringByReplacingOccurrencesOfString:@"." withString:@""];
            currentlyVersions = [currentlyVersions stringByReplacingOccurrencesOfString:@"," withString:@""];
            
            NSString *titleStr = data[@"info"][@"version_code"];
            if (serviceVersion.intValue > currentlyVersions.intValue) {
                if ([data[@"info"][@"is_forced"] intValue]) {
                    responseBlock(YES, YES, titleStr, data[@"info"][@"update_desc"]);
                } else {
                    responseBlock(YES, NO, titleStr, data[@"info"][@"update_desc"]);
                }
            } else {
                responseBlock(NO, NO, @"", @"已经是最新版本了");
            }
        } else {
            noDataBlock();
        }
    } failure:^(id error) {
        noDataBlock();
    }];
}

@end
