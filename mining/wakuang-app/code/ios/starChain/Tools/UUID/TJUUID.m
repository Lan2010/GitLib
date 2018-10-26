//
//  TJUUID.m
//  钥匙串
//
//  Created by 夏铁军 on 16/12/3.
//  Copyright © 2016年 qianhezi. All rights reserved.
//

#import "TJUUID.h"
#import "KeychainItemWrapper.h"

@implementation TJUUID

+ (NSString *)getUUID {
    KeychainItemWrapper *wrapper = [[KeychainItemWrapper alloc] initWithIdentifier:@"deviceIdentifier" accessGroup:nil];
    NSString *uniqueIdentifier = [wrapper objectForKey:(id)kSecAttrAccount];
    
    if (uniqueIdentifier == nil || uniqueIdentifier.length == 0) {
        uniqueIdentifier = [[[UIDevice currentDevice] identifierForVendor] UUIDString];
        [wrapper setObject:uniqueIdentifier forKey:(__bridge id)kSecAttrAccount];
    }
    
    return uniqueIdentifier;
}

@end
