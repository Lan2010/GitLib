//
//  XLGlobalFunc.h
//  starChain
//
//  Created by rlx on 2018/6/7.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <CoreLocation/CLLocation.h>

@interface XLGlobalFunc : NSObject

+ (XLGlobalFunc *)globalFunc;

@property (strong, nonatomic) UIImage *advertisingImage;
@property (copy, nonatomic) NSString *registrationID;
@property (copy, nonatomic) NSString *networkStatus;

@property (assign, nonatomic) NSInteger cityCode;
@property (assign, nonatomic, readonly) BOOL isFisrtOpen;
@property (assign, nonatomic) BOOL brokenNetwork;
@property (assign, nonatomic) BOOL bindingMill;
@property (assign, nonatomic) BOOL updateAddressBook;
@property (assign, nonatomic) int rewardCount;
@property (strong, nonatomic) NSArray *bornDatas;

@property (assign, nonatomic) CLLocationCoordinate2D coordinate;

@property (assign, nonatomic, readonly) BOOL firstInstallat;



@end
