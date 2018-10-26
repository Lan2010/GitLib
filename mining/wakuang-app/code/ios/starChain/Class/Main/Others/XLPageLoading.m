//
//  XLPageLoading.m
//  starChain
//
//  Created by rlx on 2018/6/25.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import "XLPageLoadingView.h"

@interface XLPageLoading ()

@property (weak, nonatomic) FLAnimatedImageView *animationImageView;
@property (weak, nonatomic) UILabel *loadingLable;

@end

@implementation XLPageLoadingView

+ (instancetype)loadingAnimationWithSuperView:(UIView *)view {
    
    XLPageLoadingView *pageLoadingview = [XLPageLoadingView tj_WithSuperView:view];
    pageLoadingview.frame = CGRectMake(0, TOPMAGRIN, KScreenWidth, KScreenHeight - TOPMAGRIN);
    pageLoadingview.backgroundColor = [UIColor tj_backgroundColor];
    
    FLAnimatedImageView *animationImageView = [FLAnimatedImageView tj_WithSuperView:pageLoadingview];
    pageLoadingview.animationImageView = animationImageView;
    
    FLAnimatedImage *animatedImage = [FLAnimatedImage animatedImageWithGIFData:[NSData dataWithContentsOfURL:[[NSBundle mainBundle] URLForResource:@"pageLoading" withExtension:@"gif"]]];
    animationImageView.animatedImage = animatedImage;
    
    CGFloat imageWidth = animatedImage.size.width * 0.5;
    CGFloat imageHight = animatedImage.size.height * 0.5;
    
    CGFloat offsetYMargin = 0;
    if (TJScreenHeight < 667) {
        offsetYMargin = -H(80);
        imageWidth = H(imageWidth);
        imageHight = H(imageHight);
    } else {
        offsetYMargin = -H(70);
    }
    
    [animationImageView makeConstraints:^(MASConstraintMaker *make) {
        make.centerX.offset(0);
        make.centerY.offset(offsetYMargin);
        make.width.offset(imageWidth);
        make.height.offset(imageHight);
    }];
    
    UILabel *loadingLable = [UILabel tj_LableWithSuperView:pageLoadingview fontSize:0 color:UIColor_Hex(0x303030) title:@"页面加载中, 请稍后.." textAlignment:NSTextAlignmentCenter];
    pageLoadingview.loadingLable = loadingLable;
    loadingLable.font = [UIFont systemFontOfSize:13];
    [loadingLable makeConstraints:^(MASConstraintMaker *make) {
        make.centerX.offset(0);
        make.top.equalTo(animationImageView.bottom).offset(5);
    }];
    
    return pageLoadingview;
}

- (void)setShowType:(XLShowType)showType {
    _sh
    
}

@end
