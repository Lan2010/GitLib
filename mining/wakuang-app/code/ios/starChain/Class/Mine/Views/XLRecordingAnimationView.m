//
//  XLRecordingAnimationView.m
//  starChain
//
//  Created by rlx on 2018/6/20.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import "XLRecordingAnimationView.h"

@interface XLRecordingAnimationView()

@property (strong, nonatomic) CAShapeLayer *animationLayer;
@property (strong, nonatomic) CAReplicatorLayer *replicatorLayer;
@property (strong, nonatomic) CAAnimationGroup *animationGroup;

@end


@implementation XLRecordingAnimationView


- (instancetype)initWithFrame:(CGRect)frame {
    if ([super initWithFrame:frame]) {
        [self addSubView];
    }
    return self;
}

- (void)addSubView {
    
    self.animationLayer = [CAShapeLayer layer];
    self.animationLayer.frame = self.bounds;
    self.animationLayer.backgroundColor = [UIColor clearColor].CGColor;
    self.animationLayer.position = CGPointMake(0.5, 0.5);
    
    self.animationLayer.path = [UIBezierPath bezierPathWithOvalInRect:self.bounds].CGPath;
    self.animationLayer.fillColor = UIColor_Hex(0x6963C0).CGColor;
    self.animationLayer.opacity= 0.0;
    
    [self.layer addSublayer:self.replicatorLayer];
    [self.layer insertSublayer:self.replicatorLayer atIndex:0];
    [self.animationLayer addAnimation:self.animationGroup forKey:@"animationGroup"];
    
    UILabel *exploreLable = [UILabel lableWithSuperView:self fontSize:15 color: [UIColor whiteColor] title:@"录音" textAlignment:NSTextAlignmentCenter];
    exploreLable.layer.cornerRadius = 63 * 0.5;
    exploreLable.layer.masksToBounds = YES;
    exploreLable.backgroundColor = UIColor_Hex(0x6963C0);
    [exploreLable makeConstraints:^(MASConstraintMaker *make) {
        make.center.offset(0);
        make.width.height.offset(63);
    }];
    
}

- (CAAnimationGroup *)animationGroup {
    if (!_animationGroup) {
        CABasicAnimation *opacityAnimation = [CABasicAnimation animationWithKeyPath:@"opacity"];
        opacityAnimation.fromValue = @0.6;
        opacityAnimation.toValue = @0.1;
        
        CABasicAnimation *transformAnimation = [CABasicAnimation animationWithKeyPath:@"transform.scale"];
        transformAnimation.fromValue = @(83.0 / self.bounds.size.width);
        transformAnimation.toValue = @1;
        
        CAAnimationGroup *animationGroup = [CAAnimationGroup animation];
        animationGroup.animations = @[opacityAnimation, transformAnimation];
        animationGroup.duration = 3;
        animationGroup.autoreverses = false;
        animationGroup.timingFunction = [CAMediaTimingFunction functionWithName:kCAMediaTimingFunctionEaseOut];
        animationGroup.repeatCount = HUGE;
        animationGroup.removedOnCompletion = NO;
        animationGroup.fillMode = kCAFillModeForwards;
        
        _animationGroup = animationGroup;
    }
    return _animationGroup;
}

- (CAReplicatorLayer *)replicatorLayer {
    if (!_replicatorLayer) {
        CAReplicatorLayer *layer = [CAReplicatorLayer layer];
        layer.bounds = _animationLayer.bounds;
        layer.position = CGPointMake(self.bounds.size.width, self.bounds.size.height);
        layer.instanceCount = 3;
        layer.instanceDelay = 1;
        [layer addSublayer:self.animationLayer];
        _replicatorLayer = layer;
    }
    return _replicatorLayer;
}

- (void)endAnimation {
    [self.animationLayer removeAnimationForKey:@"animationGroup"];
}

- (void)starAnimation {
    [self.animationLayer removeAnimationForKey:@"animationGroup"];
    [self.animationLayer addAnimation:self.animationGroup forKey:@"animationGroup"];
}

 
@end
