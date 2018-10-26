//
//  QHZEncryptManager.m
//  qhz
//
//  Created by 夏铁军 on 17/3/2.
//  Copyright © 2017年 qhz. All rights reserved.
//

#import "QHZEncryptManager.h"
#import "HBRSAHandler.h"

@implementation QHZEncryptManager

+ (NSDictionary *)encrptWithParameters:(NSDictionary *)parameters {
    NSMutableDictionary *MParameters = [NSMutableDictionary dictionaryWithDictionary:parameters];
    MParameters[@"s"] = [self signatureStringWithParameters:parameters];
    return MParameters.copy;
}

+ (NSString *)signatureStringWithParameters:(NSDictionary *)parameters {
    NSMutableString *dataString = [NSMutableString string];
    NSMutableArray *Marr = [NSMutableArray arrayWithCapacity:7];
    
    [parameters enumerateKeysAndObjectsUsingBlock:^(id  _Nonnull key, id  _Nonnull obj, BOOL * _Nonnull stop) {
        if (![key isEqualToString:@"s"])
            [Marr addObject:key];
    }];
    
    NSSortDescriptor *descriptor = [NSSortDescriptor sortDescriptorWithKey:nil ascending:YES];
    NSArray *resultArray = [Marr sortedArrayUsingDescriptors:@[descriptor]];//有序的数组
    [resultArray enumerateObjectsUsingBlock:^(id  _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
        //        NSLog(@" %@ = %@", obj, parameters[obj]);
        [dataString appendString:parameters[obj]];
    }];
    
    NSString *md5UppercaseString = [[dataString md5String] uppercaseString];
    HBRSAHandler *handler = [HBRSAHandler shareRSAHandler];
    NSData *privnewData = [handler encrypt_privatecKey:md5UppercaseString];
    NSString *privnewStr = [NSString convertDataToHexStr:privnewData];
    return [privnewStr uppercaseString];
}

@end
