//
//  QHZEncryptManager.h
//  qhz
//
//  Created by 夏铁军 on 17/3/2.
//  Copyright © 2017年 qhz. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface QHZEncryptManager : NSObject

+ (NSDictionary *)encrptWithParameters:(NSDictionary *)parameters;

+ (NSString *)signatureStringWithParameters:(NSDictionary *)parameters;

@end
