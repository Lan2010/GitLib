//
//  QHZSuspensionView.m
//  qhz
//
//  Created by lei wei on 2017/12/7.
//  Copyright © 2017年 qhz. All rights reserved.
//


#define kLeanProportion 0
#define topMargin 0
#define leftMargin 10
#define rightMargin 10
#define bottomMargin 10

#import "QHZSuspensionView.h"

@implementation QHZSuspensionView

- (instancetype)initWithFrame:(CGRect)frame {
    if(self = [super initWithFrame:frame]) {
        self.userInteractionEnabled = YES;
        UIPanGestureRecognizer *pan = [[UIPanGestureRecognizer alloc]initWithTarget:self action:@selector(handlePanGesture:)];
        pan.delaysTouchesBegan = YES;
        [self addGestureRecognizer:pan];
    }
    return self;
}

#pragma mark - event response
- (void)handlePanGesture:(UIPanGestureRecognizer*)p {
    CGPoint panPoint = [p locationInView:self.superview];
    
    if(p.state == UIGestureRecognizerStateBegan) {
     }else if(p.state == UIGestureRecognizerStateChanged) {
        
        self.center = CGPointMake(panPoint.x, panPoint.y);
        
    }else if(p.state == UIGestureRecognizerStateEnded
             || p.state == UIGestureRecognizerStateCancelled) {
 
        CGFloat ballWidth = self.frame.size.width;
        CGFloat ballHeight = self.frame.size.height;
        CGFloat screenWidth = [[UIScreen mainScreen] bounds].size.width;
        CGFloat screenHeight = [[UIScreen mainScreen] bounds].size.height;
        
        CGFloat left = fabs(panPoint.x);
        CGFloat right = fabs(screenWidth - left);
        CGFloat top = fabs(panPoint.y);
        CGFloat bottom = fabs(screenHeight - top);
        
        CGFloat minSpace = 0;
        if (self.leanType == QHZSuspensionViewLeanTypeHorizontal) {
            minSpace = MIN(left, right);
        }else{
            minSpace = MIN(MIN(MIN(top, left), bottom), right);
        }
        CGPoint newCenter = CGPointZero;
        CGFloat targetY = 0;
        CGFloat kVerticalMargin = TabbarSafeBottomMargin + 49;
        
        if (panPoint.y < kVerticalMargin + ballHeight / 2.0) {
            targetY = kVerticalMargin + ballHeight / 2.0 + topMargin;
 
        }else if (panPoint.y > (screenHeight - ballHeight / 2.0 - kVerticalMargin)) {
            targetY = screenHeight - ballHeight / 2.0 - kVerticalMargin - bottomMargin;
        }else{
            targetY = panPoint.y;
        }
        
        CGFloat centerXSpace = (0.5 - kLeanProportion) * ballWidth;
        CGFloat centerYSpace = (0.5 - kLeanProportion) * ballHeight;
        
        if (minSpace == left) {
            newCenter = CGPointMake(centerXSpace + leftMargin, targetY);
        }else if (minSpace == right) {
            newCenter = CGPointMake(screenWidth - centerXSpace - leftMargin, targetY);
        }else if (minSpace == top) {
            newCenter = CGPointMake(panPoint.x, centerYSpace);
        }else {
            newCenter = CGPointMake(panPoint.x, screenHeight - centerYSpace);
        }
        
        [UIView animateWithDuration:0.25 animations:^{
            self.center = newCenter;
            TJLog(@"newCenter = %@", NSStringFromCGPoint(newCenter));
        }];
    } else{
        NSLog(@"pan state : %zd", p.state);
    }
}


@end
