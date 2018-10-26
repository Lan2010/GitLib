//
//  XLAddressModel.h
//  starChain
//
//  Created by rlx on 2018/6/14.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface XLAddressModel : NSObject


+ (void)loadAddressDataWithParameters:(NSDictionary *)parameters
                              success:(void (^)(id))success
                              failure:(void (^)(id))failure;

+ (void)modifyAddAddressDataWithParameters:(NSDictionary *)parameters
                              success:(void (^)(id))success
                              failure:(void (^)(id))failure;

@end
