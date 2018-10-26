//
//  XLShareCell.h
//  starChain
//
//  Created by rlx on 2018/6/13.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface XLShareCell : UIView

@property (copy, nonatomic) void (^didClickItemBlock)(NSInteger index);

- (instancetype)initWithFrame:(CGRect)frame iconName:(NSString *)iconName title:(NSString *)title;


@end
