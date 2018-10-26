//
//  XLPageLoading.m
//  starChain
//
//  Created by rlx on 2018/6/25.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import "XLPageLoadingView.h"
#import "FLAnimatedImageView.h"
#import "FLAnimatedImage.h"
#import "XLGradientButton.h"

@interface XLPageLoadingView ()

@property (weak, nonatomic) FLAnimatedImageView *animationImageView;
@property (weak, nonatomic) UILabel *loadingLable;
@property (weak, nonatomic) XLGradientButton *reloadButton;

@end

@implementation XLPageLoadingView

+ (instancetype)loadingAnimationWithSuperView:(UIView *)view {
    
    XLPageLoadingView *pageLoadingview = [XLPageLoadingView tj_WithSuperView:view];
    pageLoadingview.frame = CGRectMake(0, TOPMAGRIN, KScreenWidth, KScreenHeight - TOPMAGRIN);
    pageLoadingview.backgroundColor = [UIColor whiteColor];
    
    FLAnimatedImageView *animationImageView = [FLAnimatedImageView tj_WithSuperView:pageLoadingview];
    pageLoadingview.animationImageView = animationImageView;
    
    UILabel *loadingLable = [UILabel lableWithSuperView:pageLoadingview fontSize:13 color:UIColor_Hex(0xa2a1b4) title:@"" textAlignment:NSTextAlignmentCenter];
    pageLoadingview.loadingLable = loadingLable;
    
    return pageLoadingview;
}


- (void)setShowType:(XLShowType)showType {
    _showType = showType;
    
    self.animationImageView.animatedImage = nil;
    [self.animationImageView stopAnimating];
    
    if (_showType == XLShowTypeLoading) {
 
        FLAnimatedImage *animatedImage = [FLAnimatedImage animatedImageWithGIFData:[NSData dataWithContentsOfURL:[[NSBundle mainBundle] URLForResource:@"pageLoading" withExtension:@"gif"]]];
        CGFloat imageWidth = animatedImage.size.width * 0.5;
        CGFloat imageHight = animatedImage.size.height * 0.5;
        
        CGFloat topMargin = 0;
        if (KScreenHeight < 667) {
            topMargin = H(80);
            imageWidth = H(imageWidth);
            imageHight = H(imageHight);
        } else {
            topMargin = H(70);
        }
        
        self.animationImageView.animatedImage = animatedImage;
        
        [self.animationImageView remakeConstraints:^(MASConstraintMaker *make) {
            make.centerX.offset(0);
            make.top.offset(topMargin);
            make.width.offset(imageWidth);
            make.height.offset(imageHight);
        }];
        
        [self.loadingLable remakeConstraints:^(MASConstraintMaker *make) {
            make.centerX.offset(0);
            make.top.equalTo(self.animationImageView.bottom).offset(5);
        }];
        
        self.loadingLable.text = @"加载中...";
        

    } else if (_showType == XLShowTypeEmpty) {
        
        UIImage *image = [UIImage imageNamed:@"pic-nodata"];
        CGFloat imageWidth = ZOOM5(image.size.width);
        CGFloat imageHight = ZOOM5(image.size.height);
        
        _animationImageView.image = image;
        TJLog(@"image = %@", image);
 
        [_animationImageView remakeConstraints:^(MASConstraintMaker *make) {
            make.centerX.offset(0);
            make.width.offset(imageWidth);
            make.height.offset(imageHight);
            make.top.offset(H(80));
        }];

        [_loadingLable remakeConstraints:^(MASConstraintMaker *make) {
            make.centerX.offset(0);
            make.top.equalTo(self.animationImageView.bottom).offset(10);
        }];

        _loadingLable.text = @"空空如也";
        
    }  else if (_showType == XLShowTypeLoadError) {
        
        UIImage *image = [UIImage imageNamed:@"pic-fault"];
        
        CGFloat imageWidth = ZOOM5(image.size.width);
        CGFloat imageHight = ZOOM5(image.size.height);
 
        _animationImageView.image = image;
        TJLog(@"image = %@", image);
        
        [_animationImageView remakeConstraints:^(MASConstraintMaker *make) {
            make.width.offset(imageWidth);
            make.height.offset(imageHight);
            make.top.offset(H(80));
            make.centerX.offset(0);
        }];
        
        [_loadingLable remakeConstraints:^(MASConstraintMaker *make) {
            make.centerX.offset(0);
            make.top.equalTo(self.animationImageView.bottom).offset(40);
        }];
        
        
        [self.reloadButton removeFromSuperview];
        XLGradientButton *reloadButton = [[XLGradientButton alloc] initWithFrame:CGRectMake(0, 0, 100, 40) gradientColors:@[[UIColor colorWithRed:105.0f/255.0f green:99.0f/255.0f blue:192.0f/255.0f alpha:1.0f], [UIColor colorWithRed:172.0f/255.0f green:86.0f/255.0f blue:250.0f/255.0f alpha:1.0f]] point:CGPointMake(1, 0)];
        [_loadingLable.superview addSubview:reloadButton];
        [reloadButton.button setTitle:@"重新加载" forState:UIControlStateNormal];
        reloadButton.button.titleLabel.font = UIFont_Px(14);
        self.reloadButton = reloadButton;
        [reloadButton.button addTapGesturesWithTarget:self action:@selector(didClickReloadButton)];
        [reloadButton shearRoundedCornersWithRadiu:20];
        [reloadButton makeConstraints:^(MASConstraintMaker *make) {
            make.top.equalTo(self.loadingLable.bottom).offset(30);
            make.centerX.offset(0);
            make.width.offset(100);
            make.height.offset(40);
        }];
        
        _loadingLable.text = @"页面加载出错, 请重试";
        
    }  else if (_showType == XLShowTypelocationError) {
        
        UIImage *image = [UIImage imageNamed:@"pic-nonet"];
        
        CGFloat imageWidth = ZOOM5(image.size.width);
        CGFloat imageHight = ZOOM5(image.size.height);
        _animationImageView.image = image;
        
        TJLog(@"image = %@", image);
        
        [_animationImageView remakeConstraints:^(MASConstraintMaker *make) {
            make.width.offset(imageWidth);
            make.height.offset(imageHight);
            make.top.offset(H(80));
            make.centerX.offset(0);
        }];
        
        [_loadingLable remakeConstraints:^(MASConstraintMaker *make) {
            make.centerX.offset(0);
            make.top.equalTo(self.animationImageView.bottom).offset(40);
        }];
        
        _loadingLable.text = @"无法获取地址，请开启定位";
        
    } else {
        
        UIImage *image = [UIImage imageNamed:@""];
        _animationImageView.image = image;
        _loadingLable.text = @"";
        
        TJLog(@"image = %@", image);
    }
 
 
}

- (void)didClickReloadButton {
    if (self.didClickReloadButtonBlock) self.didClickReloadButtonBlock();
}

- (void)stopAnimation {
    
    dispatch_after(dispatch_time(DISPATCH_TIME_NOW, (int64_t)(0.25 * NSEC_PER_SEC)), dispatch_get_main_queue(), ^{
        [UIView animateWithDuration:0.1 animations:^{
            self.animationImageView.alpha = 0;
            self.alpha = 0;
        } completion:^(BOOL finished) {
            self.animationImageView.animatedImage = nil;
            [self.animationImageView stopAnimating];
            [self.subviews enumerateObjectsUsingBlock:^(__kindof UIView * _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
                [obj removeFromSuperview];
            }];
            [self removeFromSuperview];
        }];
    });
}

@end
