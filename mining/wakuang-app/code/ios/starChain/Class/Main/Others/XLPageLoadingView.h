//
//  XLPageLoading.h
//  starChain
//
//  Created by rlx on 2018/6/25.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import <Foundation/Foundation.h>

typedef NS_ENUM(NSInteger, XLShowType) {
    XLShowTypeLoading,
    XLShowTypeEmpty,
    XLShowTypeLoadError,
    XLShowTypelocationError,
    XLShowTypeDefault,
};

@interface XLPageLoadingView : UIView

+ (instancetype)loadingAnimationWithSuperView:(UIView *)view;

- (void)stopAnimation;

@property (copy, nonatomic) void (^didClickReloadButtonBlock)(void);

@property (assign, nonatomic) XLShowType showType;


@end
