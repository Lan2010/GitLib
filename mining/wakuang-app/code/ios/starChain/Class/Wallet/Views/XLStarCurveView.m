//
//  XLStarCurveView.m
//  starChain
//
//  Created by rlx on 2018/7/9.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import "XLStarCurveView.h"

@interface XLStarCurveView()

@property (assign, nonatomic) CGFloat maxValues;
@property (assign, nonatomic) CGFloat minValues;
@property (assign, nonatomic) CGFloat yMarginValue;
@property (assign, nonatomic) CGFloat xMarginValue;
@property (assign, nonatomic) CGFloat yMarginHight;
@property (assign, nonatomic) NSInteger maxY;



@end

@implementation XLStarCurveView


- (instancetype)initWithFrame:(CGRect)frame {
    if (self = [super initWithFrame:frame]) {
        self.backgroundColor = [UIColor whiteColor];
  
    }
    return self;
}


- (void)setVaules:(NSArray *)vaules {
    _vaules = vaules;
    
    self.maxValues = CGFLOAT_MIN;
    self.minValues = CGFLOAT_MAX;
    
    [vaules enumerateObjectsUsingBlock:^(id  _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
        CGFloat value = [obj floatValue];
        if (value > self.maxValues) self.maxValues = value;
        if (value < self.minValues) self.minValues = value;
    }];
    
    
    if (self.maxValues == self.minValues) {
        self.yMarginValue =  self.maxValues / 5.0;
    } else {
        self.yMarginValue =  (self.maxValues - self.minValues) / 5.0;
    }
    
    self.xMarginValue = (self.frame.size.width) / (self.vaules.count - 1);
    self.maxY = self.frame.size.height - 1;
    self.yMarginHight = (self.maxY) / 5.0;
    
    NSLog(@"maxValues = %f", self.maxValues);
    NSLog(@"minValues = %f", self.minValues);
    
    [self setNeedsDisplay];
    
}

- (BOOL)noGrowth {
    return (self.maxValues == self.minValues);
}

- (void)drawRect:(CGRect)rect {
    
    UIBezierPath *bezierPath = [UIBezierPath bezierPath];
    bezierPath.lineWidth = 1;
    [UIColor_Hex(0xac56fa) setStroke];
    
    
    if (self.showStraightline) {
        
        [bezierPath moveToPoint:CGPointMake(0, self.maxY * 0.5)];
        [bezierPath addLineToPoint:CGPointMake(self.bounds.size.width, self.maxY * 0.5)];
        
    } else {
        [self.vaules enumerateObjectsUsingBlock:^(id  _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
            
            CGFloat Y =  self.maxY - ((([obj floatValue] - self.minValues) / self.yMarginValue) * self.yMarginHight);
            
            CGFloat X = self.xMarginValue * idx;
            
            if (idx == 0) {
                [bezierPath moveToPoint:CGPointMake(X, MIN(self.maxY, Y))];
            } else {
                [bezierPath addLineToPoint:CGPointMake(X, MAX(2, Y))];
            }
            NSLog(@"*********(x, y) = (%f, %f)", X, Y);
        }];
     }
    
    [bezierPath stroke];
    
}

- (void)setShowStraightline:(BOOL)showStraightline {
    _showStraightline = showStraightline;
    [self setNeedsDisplay];    
}

//https://blog.csdn.net/Hierarch_Lee/article/details/48792145

@end
