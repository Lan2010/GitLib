//
//  XLBaseController.m
//  starChain
//
//  Created by rlx on 2018/6/6.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import "XLBaseController.h"

@interface XLBaseController ()

@property (strong, nonatomic) UIView *lineView;


@end

@implementation XLBaseController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    self.view.backgroundColor = [UIColor tj_backgroundColor];
    self.automaticallyAdjustsScrollViewInsets = NO;
    self.fd_prefersNavigationBarHidden = YES;
    self.fd_interactivePopMaxAllowedInitialDistanceToLeftEdge = 100;
    if (!self.hiddenNavigationBar) [self.view addSubview:self.tj_navigationBar];
}


- (XLNavigationBar *)tj_navigationBar {
    if (!_tj_navigationBar) {
        _tj_navigationBar = [[XLNavigationBar alloc] initWithFrame:CGRectMake(0, 0, KScreenWidth, TOPMAGRIN)];
        _tj_navigationBar.items = @[self.tj_navigationItem];
        _tj_navigationBar.tintColor = [UIColor blackColor];
        _tj_navigationBar.barTintColor = [UIColor whiteColor];
        _tj_navigationBar.translucent = NO;
        _tj_navigationBar.clipsToBounds = YES;
        _tj_navigationBar.titleTextAttributes = @{NSFontAttributeName: [UIFont systemFontOfSize:18]};
        [_tj_navigationBar addSubview:self.lineView];
    }
    return _tj_navigationBar;
}

- (UIView *)lineView {
    if (!_lineView) {
        _lineView = [[UIView alloc] initWithFrame:CGRectMake(0, TOPMAGRIN - 0.7, KScreenWidth, 0.5)];
        _lineView.backgroundColor = [UIColor_Hex(0xdddddd) colorWithAlphaComponent:0.9];
    }
    return _lineView;
}

- (UINavigationItem *)tj_navigationItem {
    if (!_tj_navigationItem) {
        _tj_navigationItem = [[UINavigationItem alloc] init];
        _tj_navigationItem.title = self.navigationItem.title;
    }
    return _tj_navigationItem;
}

- (void)setHiddenLineView:(BOOL)hiddenLineView {
    _hiddenLineView = hiddenLineView;
    _lineView.hidden = hiddenLineView;
}

- (void)setHiddenNavigationBar:(BOOL)hiddenNavigationBar {
    _hiddenNavigationBar = hiddenNavigationBar;
    if (hiddenNavigationBar) [_tj_navigationBar removeFromSuperview];
}

- (void)setTitleColor:(UIColor *)titleColor {
    _titleColor = titleColor;
    self.tj_navigationBar.titleTextAttributes = @{NSForegroundColorAttributeName: titleColor};
}

- (void)setNavBarAlpha:(BOOL)navBarAlpha {
    _navBarAlpha = navBarAlpha;
    if (navBarAlpha) {
        self.hiddenLineView = YES;
        [self.tj_navigationBar setBackgroundImage:[[UIImage alloc] init] forBarMetrics:UIBarMetricsDefault];
        self.tj_navigationBar.translucent = YES;
    }
}


@end
