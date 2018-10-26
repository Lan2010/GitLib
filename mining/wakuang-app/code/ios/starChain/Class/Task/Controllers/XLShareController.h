//
//  XLShareController.h
//  starChain
//
//  Created by rlx on 2018/6/13.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface XLShareController : UIViewController

@property (copy, nonatomic) void (^shareSuccessBlock)(NSInteger);

- (void)shareWithImage:(UIImage *)image;

@end
