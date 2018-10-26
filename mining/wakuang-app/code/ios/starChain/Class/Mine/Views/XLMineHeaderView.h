//
//  XLMineHeaderView.h
//  starChain
//
//  Created by rlx on 2018/6/11.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface XLMineHeaderView : UIView

@property (weak, nonatomic) UILabel *phoneNumberLable;

@property (copy, nonatomic) void (^tapPhoneNumberLableBlock)(void);
@property (copy, nonatomic) void (^tapInvitationImageViewBlock)(void);

@end

