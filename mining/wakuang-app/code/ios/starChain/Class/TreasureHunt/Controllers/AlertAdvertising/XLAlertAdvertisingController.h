//
//  XLAlertAdvertisingController.h
//  starChain
//
//  Created by rlx on 2018/6/29.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface XLAlertAdvertisingController : UIViewController

@property (strong, nonatomic) NSDictionary *dict;
@property (copy, nonatomic) void (^didDissmissButtonBlock)();


@end
