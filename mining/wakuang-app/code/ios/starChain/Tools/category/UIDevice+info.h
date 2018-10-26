//
//  UIDevice+info.h
//  starChain
//
//  Created by rlx on 2018/6/20.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface UIDevice (info)

+ (NSString *)deviceVersion;

+ (NSString *)IPAddresses;

+ (NSString *)telephonyNetwork;

+ (BOOL)isIphone;

+ (NSString *)getDeviceMAC;

@end
