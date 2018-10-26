//
//  XLAddressModel.m
//  starChain
//
//  Created by rlx on 2018/6/14.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import "XLAddressModel.h"

@implementation XLAddressModel

+ (void)loadAddressDataWithParameters:(NSDictionary *)parameters
                              success:(void (^)(id))success
                              failure:(void (^)(id))failure {
    
    [TJNetworkTool requestWithUrl:@"Real.GetUserAddress" parameters:parameters success:^(id data) {
        if ([data[@"code"] intValue]) {
            if (failure) failure(data);
        } else {
            success([self mj_objectWithKeyValues:data[@"info"]]);
         }
    } failure:^(id error) {
        if (failure) failure(error);
    }];
}

+ (void)modifyAddAddressDataWithParameters:(NSDictionary *)parameters
                                success:(void (^)(id))success
                                failure:(void (^)(id))failure {
    [TJNetworkTool requestWithUrl:@"Real.SetUserAddress" parameters:parameters success:^(id data) {
        if ([data[@"code"] intValue]) {
            if (failure) failure(data);
        } else {
            if (success) success(data);
        }
    } failure:^(id error) {
        if (failure) failure(error);
    }];
}

@end
