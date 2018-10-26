//
//  XLLineView.h
//  starChain
//
//  Created by rlx on 2018/6/11.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface XLLineView : UIView

@property (strong, nonatomic) UIColor *lineColor;

- (instancetype)initWithFrame:(CGRect)frame topLine:(BOOL)topLine bottomLine:(BOOL)bottomLine;
- (instancetype)initWithTopLine:(BOOL)topLine bottomLine:(BOOL)bottomLine;


@end
