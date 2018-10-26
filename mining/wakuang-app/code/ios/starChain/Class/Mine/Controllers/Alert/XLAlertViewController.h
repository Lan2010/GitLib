//
//  XLAlertView.h
//  starChain
//
//  Created by rlx on 2018/6/27.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface XLAlertViewController : UIViewController

+ (instancetype)alertViewWithMessage:(NSString *)message buttonTitles:(NSArray *)buttonTitles;

@property (copy, nonatomic) void (^didClickButtonBlock)(UIButton *);

@end
