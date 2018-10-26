//
//  UIView+category.m
//  categary
//
//  Created by 夏铁军 on 15/11/6.
//  Copyright © 2015年 tiaowang. All rights reserved.
//

#import "UIView+category.h"

@implementation UIView (category)


+ (_Nonnull instancetype)tj_WithSuperView:(nonnull UIView *)superView {
    UIView *view = [[self alloc] init];
    [superView addSubview:view];
    view.userInteractionEnabled = YES;
    return view;
}

+ (_Nonnull instancetype)imageViewWithImageName:(nullable NSString *)imageName superView:(nonnull UIView *)superView {
    UIImageView *imageView = [self tj_WithSuperView:superView];
    imageView.image = [UIImage imageNamed:imageName];
    return imageView;
}

+ (_Nonnull instancetype)lableWithSuperView:(UIView *)superView fontSize:(CGFloat)fontSize color:(nullable UIColor *)color title:(nullable NSString *)title textAlignment:(NSTextAlignment)textAlignment {
    UILabel *lable = [[UILabel alloc] init];
    lable.userInteractionEnabled = YES;
    if (superView) [superView addSubview:lable];
    lable.text = title;
    if (color) lable.textColor = color;
    lable.font = [UIFont systemFontOfSize:fontSize];
    lable.textAlignment = textAlignment;
    return lable;
}

+ (_Nonnull instancetype)buttonWithSuperView:(UIView *)superView fontSize:(CGFloat)fontSize color:(nullable UIColor *)color title:(nullable NSString *)title {
    UIButton *button = [[self alloc] init];
    [superView addSubview:button];
    [button setTitle:title forState:UIControlStateNormal];
    if (color) [button setTitleColor:color forState:UIControlStateNormal];
    button.titleLabel.font = [UIFont systemFontOfSize:fontSize];
    return button;
}

- (nonnull UIView *)shearRoundedCornersWithRadiu:(CGFloat)radiu {
    self.layer.cornerRadius = radiu;
    self.layer.masksToBounds = YES;
    return self;
}

- (void)addTapGesturesWithTarget:(nullable id)id action:(nonnull SEL)action {
    UITapGestureRecognizer *tap = [[UITapGestureRecognizer alloc] initWithTarget:id action:action];
    [self addGestureRecognizer:tap];
}

- (nonnull CAGradientLayer *)addGradientLayerWithColors:(nonnull NSArray <UIColor *>*)colors endPoint:(CGPoint)endPoint {
    
    if (self.frame.size.width) TJLog(@"seting frame");

    CAGradientLayer *gradientLayer = [CAGradientLayer layer];
    gradientLayer.frame = self.bounds;
    [self.layer insertSublayer:gradientLayer atIndex:0];
    gradientLayer.startPoint = CGPointMake(0, 0);
    gradientLayer.endPoint = endPoint;
    
    if (colors.count) {
        gradientLayer.colors = @[(__bridge id)colors.firstObject.CGColor,
                                 (__bridge id)colors.lastObject.CGColor];
    }
    return gradientLayer;
}

- (void)drawRoundedCornersWithRoundedRect:(CGRect)rect corner:(UIRectCorner)corners cornerRadii:(CGSize)cornerRadii {
    UIBezierPath *maskPath=  [UIBezierPath bezierPathWithRoundedRect:rect byRoundingCorners:corners cornerRadii:cornerRadii];
    CAShapeLayer *maskLayer = [[CAShapeLayer alloc] init];
    maskLayer.frame = self.bounds;
    maskLayer.path = maskPath.CGPath;
    self.layer.mask = maskLayer;
    self.layer.masksToBounds = YES;
}

- (void)removeAllSubView {
    [self.subviews enumerateObjectsUsingBlock:^(__kindof UIView * _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
        [obj removeFromSuperview];
    }];
}

- (void)setTj_x:(CGFloat)tj_x {
    CGRect frame = self.frame;
    frame.origin.x = tj_x;
    self.frame = frame;
}

- (CGFloat)tj_x {
    return self.frame.origin.x;
}

- (void)setTj_y:(CGFloat)tj_y {
    CGRect frame = self.frame;
    frame.origin.y = tj_y;
    self.frame = frame;
}

- (CGFloat)tj_y {
    return self.frame.origin.y;
}

- (void)setTj_width:(CGFloat)tj_width {
    CGRect frame = self.frame;
    frame.size.width = tj_width;
    self.frame = frame;
}

- (CGFloat)tj_width {
    return self.frame.size.width;
}

- (void)setTj_height:(CGFloat)tj_height {
    CGRect frame = self.frame;
    frame.size.height = tj_height;
    self.frame = frame;
}

- (CGFloat)tj_height {
    return self.frame.size.height;
}

- (void)setTj_size:(CGSize)tj_size {
    CGRect frame = self.frame;
    frame.size = tj_size;
    self.frame = frame;
}

- (CGSize)tj_size {
    return self.frame.size;
}

- (void)setTj_centerX:(CGFloat)tj_centerX {
    CGPoint center = self.center;
    center.x = tj_centerX;
    self.center = center;
}

- (CGFloat)tj_centerX {
    return self.center.x;
}

- (void)setTj_centerY:(CGFloat)tj_centerY {
    CGPoint center = self.center;
    center.x = tj_centerY;
    self.center = center;
}

- (CGFloat)tj_centerY {
    return self.center.x;
}


@end
