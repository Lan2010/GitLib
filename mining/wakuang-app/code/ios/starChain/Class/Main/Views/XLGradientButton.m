//
//  XLGradientButton.m
//  starChain
//
//  Created by rlx on 2018/7/14.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import "XLGradientButton.h"

@implementation XLGradientButton 

- (instancetype)initWithFrame:(CGRect)frame gradientColors:(NSArray *)colors point:(CGPoint)point {
    if (self = [super initWithFrame:frame]) {
        [self addGradientLayerWithColors:colors endPoint:point];
        UIButton *button = [[UIButton alloc] init];
        button.frame = self.bounds;
        [self addSubview:button];
        _button = button;
    }
    return self;
}

@end
