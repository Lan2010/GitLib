//
//  XLGradientButton.h
//  starChain
//
//  Created by rlx on 2018/7/14.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface XLGradientButton : UIView

@property (weak, nonatomic) UIButton *button;

- (instancetype)initWithFrame:(CGRect)frame gradientColors:(NSArray *)colors point:(CGPoint)point;


@end
