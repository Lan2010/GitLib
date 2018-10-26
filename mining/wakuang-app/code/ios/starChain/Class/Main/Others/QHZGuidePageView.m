//
//  QHZGuidePageView.m
//  qhz
//
//  Created by lei wei on 2017/5/24.
//  Copyright © 2017年 qhz. All rights reserved.
//

#import "QHZGuidePageView.h"
#import "XLTreasureHuntController.h"

@interface QHZGuidePageView ()<UIScrollViewDelegate>

@property (weak, nonatomic) UIScrollView *scrollView;
@property (weak, nonatomic) UIPageControl *pageControl;
@property (weak, nonatomic) UIButton *experienceButton;
@property (assign, nonatomic) NSInteger count;
@property (assign, nonatomic) NSInteger currentPage;

@end

@implementation QHZGuidePageView


+ (void)showGuidePage {
    [[[QHZGuidePageView alloc] init] addSubView];
}

- (void)addSubView {
    
    _count = 2;
    
    UIWindow *backgroundView = [UIApplication sharedApplication].keyWindow;
    self.frame = backgroundView.bounds;
    [backgroundView addSubview:self];
    UIScrollView *scrollView = [[UIScrollView alloc] initWithFrame:backgroundView.bounds];
    [self addSubview:scrollView];
    scrollView.backgroundColor = [UIColor whiteColor];

    scrollView.contentSize = CGSizeMake(KScreenWidth * _count, 0);
    scrollView.pagingEnabled = YES;
    scrollView.showsVerticalScrollIndicator = NO;
    scrollView.showsHorizontalScrollIndicator = NO;
    scrollView.delegate = self;
    scrollView.bounces = NO;

    UIPageControl *pageControl = [[UIPageControl alloc] init];
    pageControl.numberOfPages = _count;
    pageControl.pageIndicatorTintColor = UIColor_Hex(0xdddddd);
    pageControl.currentPageIndicatorTintColor = [UIColor colorWithRed:132.0f/255.0f green:86.0f/255.0f blue:250.0f/255.0f alpha:1.0f];
    [self addSubview:pageControl];
    [pageControl makeConstraints:^(MASConstraintMaker *make) {
        make.centerX.offset(0);
        make.bottom.offset(ZOOM(-12));
        make.width.offset(200);
        make.height.offset(10);
    }];

    self.scrollView = scrollView;
    self.pageControl = pageControl;
    
    for (int i = 0; i < _count; i++) {

        UIImageView *guideView = [[UIImageView alloc] initWithFrame:CGRectMake(KScreenWidth * i, 0, KScreenWidth, KScreenHeight)];
        [scrollView addSubview:guideView];
        guideView.userInteractionEnabled = YES;
        
        guideView.backgroundColor = [UIColor whiteColor];
        
        if (IPHONEX) {
            guideView.image = [UIImage imageNamed:[NSString stringWithFormat:@"guidepage%d_X",i + 1]];

        } else {
            guideView.image = [UIImage imageNamed:[NSString stringWithFormat:@"introducePage%d",i + 1]];

        }
    
        if (i == _count - 1) {
            UIButton *experienceButton = [UIButton tj_WithSuperView:guideView];
            [experienceButton addTarget:self action:@selector(didClickeExperienceButton) forControlEvents:UIControlEventTouchUpInside];
            self.experienceButton = experienceButton;
            [experienceButton makeConstraints:^(MASConstraintMaker *make) {
                make.centerX.offset(0);
                make.bottom.offset(ZOOM(-33));
                make.width.offset(ZOOM(170));
                make.height.offset(ZOOM(45));
            }];
        }
    };
}

- (void)didClickeExperienceButton {
    [self.scrollView removeAllSubView];
    [self.scrollView removeFromSuperview];
    [self removeFromSuperview];
    
//    UITabBarController *tabBarController = (UITabBarController *)[UIApplication sharedApplication].keyWindow.rootViewController;
//    QHZHomeViewController *homeViewController = tabBarController.viewControllers[0].childViewControllers.firstObject;
//    homeViewController.showUpDate = YES;
    
}

- (void)scrollViewDidEndDecelerating:(UIScrollView *)scrollView {
    NSInteger page = scrollView.contentOffset.x / KScreenWidth;
    _pageControl.currentPage = page;
    self.currentPage = page;
//    self.pageControl.hidden = self.currentPage;
}


@end
