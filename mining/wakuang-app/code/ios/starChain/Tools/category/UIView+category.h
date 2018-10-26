//
//  UIView+category.h
//  categary
//
//  Created by 夏铁军 on 15/11/6.
//  Copyright © 2015年 tiaowang. All rights reserved.
//
#import <UIKit/UIKit.h>

@interface UIView (category)

@property (assign, nonatomic) CGFloat tj_x;
@property (assign, nonatomic) CGFloat tj_y;
@property (assign, nonatomic) CGFloat tj_width;
@property (assign, nonatomic) CGFloat tj_height;
@property (assign, nonatomic) CGFloat tj_centerX;
@property (assign, nonatomic) CGFloat tj_centerY;
@property (assign, nonatomic) CGSize  tj_size;

- (void)removeAllSubView;

+ (_Nonnull instancetype)tj_WithSuperView:(nonnull UIView *)superView;

+ (_Nonnull instancetype)imageViewWithImageName:(nullable NSString *)imageName superView:(nonnull UIView *)superView;

+ (_Nonnull instancetype)buttonWithSuperView:(UIView *)superView fontSize:(CGFloat)fontSize color:(nullable UIColor *)color title:(nullable NSString *)title;

+ (_Nonnull instancetype)lableWithSuperView:(UIView *)superView fontSize:(CGFloat)fontSize color:(nullable UIColor *)color title:(nullable NSString *)title textAlignment:(NSTextAlignment)textAlignment;

 - (nonnull UIView *)shearRoundedCornersWithRadiu:(CGFloat)radiu;

- (void)addTapGesturesWithTarget:(nullable id)id action:(nonnull SEL)action;

- (nonnull CAGradientLayer *)addGradientLayerWithColors:(nonnull NSArray <UIColor *>*)colors endPoint:(CGPoint)endPoint;

- (void)drawRoundedCornersWithRoundedRect:(CGRect)rect corner:(UIRectCorner)corners cornerRadii:(CGSize)cornerRadii;




@end
