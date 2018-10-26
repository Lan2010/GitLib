//
//  XLVersionCheck.h
//  starChain
//
//  Created by rlx on 2018/6/13.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface XLVersionCheck : NSObject

+ (void)versionCheckWithResponseData:(void(^)(BOOL updata, BOOL isForced, NSString *title, NSString *message))responseBlock noDataBlock:(void (^)(void))noDataBlock;


@end
