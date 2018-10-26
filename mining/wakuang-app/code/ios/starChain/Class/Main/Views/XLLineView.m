//
//  XLLineView.m
//  starChain
//
//  Created by rlx on 2018/6/11.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import "XLLineView.h"

@implementation XLLineView{
    BOOL _topLine;
    BOOL _bottomLine;
    CGFloat _lineWidth;
}

- (instancetype)initWithFrame:(CGRect)frame {
    if (self = [super initWithFrame:frame]) {
        _topLine = _bottomLine = NO;
        _lineColor = UIColor_Hex(0xD8D8D8);// //0xdcdcdc
        _lineWidth = 0.5;
        self.backgroundColor = [UIColor whiteColor];
    }
    return self;
}


- (instancetype)initWithFrame:(CGRect)frame topLine:(BOOL)topLine bottomLine:(BOOL)bottomLine {
    if (self = [super initWithFrame:frame]) {
        _topLine = topLine;
        _bottomLine = bottomLine;
        _lineColor = UIColor_Hex(0xD8D8D8);// //0xdcdcdc
        _lineWidth = 0.5;
        self.backgroundColor = [UIColor whiteColor];
    }
    return self;
}

- (instancetype)initWithTopLine:(BOOL)topLine bottomLine:(BOOL)bottomLine {
    return [self initWithFrame:CGRectZero topLine:topLine bottomLine:bottomLine];
}

- (void)drawRect:(CGRect)rect {
    
    _lineColor = (_lineColor) ? _lineColor : [UIColor redColor];
    
    if (_topLine) {
        UIBezierPath *topLinePath = [UIBezierPath bezierPath];
        topLinePath.lineWidth = _lineWidth;
        [topLinePath moveToPoint:CGPointMake(0, 0)];
        [topLinePath addLineToPoint:CGPointMake(rect.size.width, 0)];
        [_lineColor set];
        [topLinePath stroke];
    }
    
    if (_bottomLine) {
        UIBezierPath *bottomLinePath = [UIBezierPath bezierPath];
        bottomLinePath.lineWidth = _lineWidth;
        [bottomLinePath moveToPoint:CGPointMake(0, rect.size.height)];
        [bottomLinePath addLineToPoint:CGPointMake(rect.size.width, rect.size.height)];
        [_lineColor set];
        [bottomLinePath stroke];
    }
}

- (void)setLineColor:(UIColor *)lineColor {
    _lineColor = lineColor;
    [self setNeedsDisplay];
}



@end
