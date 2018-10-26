//
//  XLShareView.h
//  starChain
//
//  Created by rlx on 2018/6/13.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface XLShareView : UIView

@property (copy, nonatomic) void (^didClickCanCalButtonBlock)();
@property (copy, nonatomic) void (^didClickItemBlock)(NSInteger index);


@end
