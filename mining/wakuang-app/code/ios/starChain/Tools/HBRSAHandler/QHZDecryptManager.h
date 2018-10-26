//
//  QHZDecryptManager.h
//  qhz
//
//  Created by 夏铁军 on 17/3/2.
//  Copyright © 2017年 qhz. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface QHZDecryptManager : NSObject

+ (NSString *)decryptSignValue:(NSString *)signValue;

+ (NSDictionary *)decryptWithString:(NSString *)string;

+ (BOOL)validationSignWithResponseData:(NSDictionary *)responseData;

@end
