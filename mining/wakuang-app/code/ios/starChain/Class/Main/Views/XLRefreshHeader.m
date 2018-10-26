//
//  QHZRefreshHeader.m
//  qhz
//
//  Created by 夏铁军 on 17/2/9.
//  Copyright © 2017年 qhz. All rights reserved.
//

#import "XLRefreshHeader.h"

@interface XLRefreshHeader()

@property (weak, nonatomic) UILabel *label;

@property (strong, nonatomic) CAShapeLayer *circleLayer;
@property (strong, nonatomic) CAShapeLayer *ballLayer;
@property (strong, nonatomic) UIBezierPath *bezierPath;
@property (weak, nonatomic) UIView *circleView;

@property (strong, nonatomic) CABasicAnimation *transformAnimation;



@end


@implementation XLRefreshHeader

- (void)prepare
{
    [super prepare];
    
    // 设置控件的高度
    self.mj_h = 75;
    
    // 添加label
    UILabel *label = [[UILabel alloc] init];
    label.textColor = UIColor_Hex(0xa2a1b4);
    label.font = [UIFont systemFontOfSize:13];
    label.textAlignment = NSTextAlignmentCenter;
    [self addSubview:label];
    self.label = label;
    self.label.frame = CGRectMake(0, 32, KScreenWidth, 20);
    
    CGFloat KscreenWidth = [UIScreen mainScreen].bounds.size.width;
    
    CGFloat circleViewW = 32;
    UIView *circleView = [[UIView alloc] initWithFrame:CGRectMake((KscreenWidth - circleViewW) * 0.5, 12, circleViewW, circleViewW)];
    self.circleView = circleView;
    [self addSubview:self.circleView];
    
    [self.circleView.layer addSublayer:self.circleLayer];
    CGFloat W = 23;
    self.circleLayer.frame = CGRectMake((circleViewW - W) * 0.5, (circleView.tj_height - W) * 0.5, W, W);
    
    UIBezierPath *path = [UIBezierPath bezierPathWithRoundedRect:self.circleLayer.bounds cornerRadius:W];
    self.circleLayer.path = [path CGPath];
    self.bezierPath = path;
    
    [self.circleView.layer addSublayer:self.ballLayer];
    CGFloat ballW = 7;
    self.ballLayer.frame = CGRectMake((circleViewW - ballW) * 0.5, CGRectGetMinY(self.circleLayer.frame) - ballW * 0.5, ballW, ballW);
    UIBezierPath *ballLayerPath = [UIBezierPath bezierPathWithRoundedRect:self.ballLayer.bounds cornerRadius:ballW];
    self.ballLayer.path = [ballLayerPath CGPath];
    
    self.circleView.transform = CGAffineTransformMakeScale(0.35, 0.35);
    self.circleView.alpha = 0;
    self.label.alpha = 0;
}

- (CABasicAnimation *)transformAnimation {
    if (!_transformAnimation) {
        CABasicAnimation *animation = [CABasicAnimation animationWithKeyPath:@"transform.rotation.z"];
        animation.fromValue = @(0);
        animation.toValue = @(M_PI * 2);
        animation.repeatCount = MAXFLOAT;
        animation.duration = 1.5;
        animation.fillMode = kCAFillModeForwards;
        animation.removedOnCompletion = NO;
        _transformAnimation = animation;
    }
    return _transformAnimation;
}

- (CAShapeLayer *)ballLayer {
    if (!_ballLayer) {
        _ballLayer = [CAShapeLayer layer];
        _ballLayer.strokeColor = [UIColor clearColor].CGColor;
        _ballLayer.fillColor = [UIColor_Hex(0x8644de) CGColor];
    }
    return _ballLayer;
}


- (CAShapeLayer *)circleLayer {
    if (!_circleLayer) {
        _circleLayer = [CAShapeLayer layer];
        _circleLayer.lineWidth = 1.0f;
        _circleLayer.strokeColor = UIColor_Hex(0x6849df).CGColor;
        _circleLayer.fillColor = [[UIColor clearColor] CGColor];
        _circleLayer.lineCap = kCALineCapRound;
    }
    return _circleLayer;
}

#pragma mark 在这里设置子控件的位置和尺寸
- (void)placeSubviews {
    [super placeSubviews];
    
    TJLog(@"placeSubviews");
    
}


#pragma mark 监听控件的刷新状态
- (void)setState:(MJRefreshState)state {
    MJRefreshCheckState;
    
    switch (state) {
        case MJRefreshStateIdle:
            self.circleView.transform = CGAffineTransformMakeScale(0, 0);
            self.circleView.alpha = 0.2;
            self.circleLayer.strokeColor = UIColor_Hex(0x6849df).CGColor;
            self.label.text = @"下拉刷新";
            break;
        case MJRefreshStatePulling:
            self.label.text = @"松开刷新";
            self.circleLayer.strokeColor = UIColor_Hex(0x6849df).CGColor;
            break;
        case MJRefreshStateRefreshing:
            self.circleView.transform = CGAffineTransformIdentity;
            self.circleView.alpha = 1;
            self.label.alpha = 1;
            self.label.transform = CGAffineTransformMakeTranslation(0, 14);
            [self.circleView.layer addAnimation:self.transformAnimation forKey:@"rotationAnimation"];
            self.label.text = @"刷新中";
            self.circleLayer.strokeColor = UIColor_Hex(0x8644de).CGColor;
            break;
        default:
            break;
    }
}

#pragma mark 监听拖拽比例（控件被拖出来的比例）
- (void)setPullingPercent:(CGFloat)pullingPercent {
    [super setPullingPercent:pullingPercent];
    
    [self.circleView.layer removeAnimationForKey:@"rotationAnimation"];
    
    CGFloat scale = MIN(1, pullingPercent - 0.35);
    self.label.alpha = 1;
    self.circleView.alpha = (scale < 0.35) ? 0 : scale;
    self.circleView.transform = CGAffineTransformMakeScale(scale,scale);
    self.label.transform = CGAffineTransformMakeTranslation(0, scale * 14);
    
    NSLog(@"pullingPercent = %f", pullingPercent);
 
 
}

@end
