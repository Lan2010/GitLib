//
//  UIImage+draw.h
//  qhz
//
//  Created by 夏铁军 on 16/11/17.
//  Copyright © 2016年 qhz. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface UIImage (draw)

- (void)asynClipCircularSize:(CGSize)size color:(UIColor *)color
                 finishiBlock:(void (^)(UIImage *))finishiBlock;

- (void)asynDrawImageSize:(CGSize)size color:(UIColor *)color
             finishiBlock:(void (^)(UIImage *))finishiBlock;

- (void)asynImageSize:(CGSize)size color:(UIColor *)color
         finishiBlock:(void (^)(UIImage *))finishiBlock;

+ (UIImage *)imageWithColor:(UIColor *)color andSize:(CGSize)size;

+ (UIImage *)imageWithColor:(UIColor *)color;//是适合背景图片

- (UIImage *)imageWithChangeColor:(UIColor *)color;

- (UIImage *)changerAlpha:(CGFloat)alpha;

- (UIImage *)fixOrientation:(UIImage *)aImage;

- (UIImage *)rotatedByDegrees:(CGFloat)degrees;


@end
