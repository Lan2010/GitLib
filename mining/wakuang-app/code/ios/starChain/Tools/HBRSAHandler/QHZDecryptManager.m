//
//  QHZDecryptManager.m
//  qhz
//
//  Created by 夏铁军 on 17/3/2.
//  Copyright © 2017年 qhz. All rights reserved.
//

#import "QHZDecryptManager.h"
#import "HBRSAHandler.h"
#import "JKEncrypt.h"

@implementation QHZDecryptManager

+ (NSString *)decryptSignValue:(NSString *)signValue {
    NSData *singStrData = [NSString convertHexStrToData:signValue];
    HBRSAHandler *handler = [HBRSAHandler shareRSAHandler];
    return  [handler decrypt_publicKey:singStrData];
}

+ (NSDictionary *)decryptWithString:(NSString *)dataStr {
    NSString *desStr = [JKEncrypt doDecEncryptStr:dataStr];//3des
    NSData *jsonData = [desStr dataUsingEncoding:NSUTF8StringEncoding];//转化为data
    return  [NSJSONSerialization JSONObjectWithData:jsonData options:NSJSONReadingMutableContainers error:nil];
}

+ (BOOL)validationSignWithResponseData:(NSDictionary *)responseData {
    NSMutableString *responseDataStr = [NSMutableString string];
    NSMutableArray *responseDataMarr = [NSMutableArray arrayWithCapacity:5];
    
    [responseData enumerateKeysAndObjectsUsingBlock:^(id  _Nonnull key, id  _Nonnull obj, BOOL * _Nonnull stop) {
        if (![key isEqualToString:@"sign"])
            [responseDataMarr addObject:key];
    }];
    
    NSSortDescriptor *descriptor = [NSSortDescriptor sortDescriptorWithKey:nil ascending:YES];
    NSArray *descriptors = [NSArray arrayWithObject:descriptor];
    NSArray *responseDataArray = [responseDataMarr sortedArrayUsingDescriptors:descriptors];//有序的数组
    [responseDataArray enumerateObjectsUsingBlock:^(id  _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
        [responseDataStr appendString: [NSString stringWithFormat:@"%@", responseData[obj]]];
    }];
    NSString *singStr = [self decryptSignValue:responseData[@"sign"]];
    NSString *appendMd5Str = [[responseDataStr md5String] uppercaseString];
    return [singStr isEqualToString:appendMd5Str];
}

@end
