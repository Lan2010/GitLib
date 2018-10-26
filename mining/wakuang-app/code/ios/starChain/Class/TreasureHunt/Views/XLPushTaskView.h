//
//  XLPushTaskView.h
//  starChain
//
//  Created by rlx on 2018/7/16.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import <UIKit/UIKit.h>

@class XLTaskModel;

@interface XLPushTaskView : UIView

@property (copy, nonatomic) void (^didClickButtonBlock)(NSInteger tag);

- (instancetype)initWithFrame:(CGRect)frame model:(XLTaskModel *)model;

@end
