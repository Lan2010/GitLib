//
//  NSDictionary+category.m
//  qhz
//
//  Created by 夏铁军 on 16/12/14.
//  Copyright © 2016年 qhz. All rights reserved.
//

#import "NSDictionary+category.h"
#import "TJUUID.h"

@implementation NSDictionary (category)

- (NSString *)keyWithValueAscendingNoAddKey:(NSString *)AddKey {
    
    NSSortDescriptor *descriptor = [NSSortDescriptor sortDescriptorWithKey:nil ascending:YES];
    NSArray *descriptors = [NSArray arrayWithObject:descriptor];
    
    NSMutableString *responseDataStr = [NSMutableString string];
    NSMutableArray *responseDataMarr = [NSMutableArray arrayWithCapacity:5];
    
    [self enumerateKeysAndObjectsUsingBlock:^(id  _Nonnull key, id  _Nonnull obj, BOOL * _Nonnull stop) {
        if (![key isEqualToString:AddKey])
            [responseDataMarr addObject:key];
    }];
    
    NSArray *responseDataArray = [responseDataMarr sortedArrayUsingDescriptors:descriptors];//有序的数组
    [responseDataArray enumerateObjectsUsingBlock:^(id  _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
        TJLog(@"xit %@ = %@", obj, self[obj]);
        [responseDataStr appendString: [NSString stringWithFormat:@"%@", self[obj]]];
    }];
    
    return [[responseDataStr md5String] uppercaseString];
}

- (NSString *)jsonString {
    NSData *jsonData = [NSJSONSerialization dataWithJSONObject:self options:NSJSONWritingPrettyPrinted error:nil];
    return [[NSString alloc] initWithData:jsonData encoding:NSUTF8StringEncoding];
}

@end
