//
//  QHZWkWebController.m
//  qhz
//
//  Created by 夏铁军 on 17/2/18.
//  Copyright © 2017年 qhz. All rights reserved.
//

#import "XLWkWebController.h"
#import <WebKit/WebKit.h>

@interface XLWkWebController ()<WKNavigationDelegate,WKUIDelegate>

@property (weak, nonatomic) WKWebView *wkWebView;

@property (strong, nonatomic) UIProgressView *progressView;
@property (strong, nonatomic) UIBarButtonItem *placeholderItem;
@property (strong, nonatomic) UIBarButtonItem *backItem;
@property (strong, nonatomic) UIBarButtonItem *closeItem;
@property (strong, nonatomic) UIBarButtonItem *placeholderButtonItem;

@property (copy, nonatomic) NSString *urlString;
@property (copy, nonatomic) NSString *shareText;
@property (copy, nonatomic) NSString *shareUrlString;
@property (copy, nonatomic) NSString *shareTitle;
@property (copy, nonatomic) NSString *shareImgaeUrl;

@end

@implementation XLWkWebController

- (void)viewDidLoad {
    [super viewDidLoad];
    [self setDefaultTitle];
}

- (void)loadWebPageWithTitle:(NSString *)title urlString:(NSString *)urlString {
    urlString = [urlString replacingEmptyString];
    _urlString = urlString;
    if (title.length) self.title = title;
    [self setDefaultTitle];
    [self loadWebPage];
}

- (void)setDefaultTitle {
    if (!self.tj_navigationItem.title.length)  self.tj_navigationItem.title = @"钱盒子";
}

- (void)jumpNativePageWithIndex:(NSInteger)index {
    UITabBarController *tabBarController = (UITabBarController *)[UIApplication sharedApplication].keyWindow.rootViewController;
    if (tabBarController.selectedIndex == index) {
        [self.navigationController popToRootViewControllerAnimated:YES];
    } else {
        tabBarController.selectedIndex = index;
    }
}

- (void)loadWebPage {
    [self.view addSubview:self.progressView];
    [self.view bringSubviewToFront:self.progressView];
    [self.wkWebView loadRequest:[NSMutableURLRequest requestWithURL:[NSURL URLWithString:_urlString]]];
}

- (WKWebView *)wkWebView {
    if (!_wkWebView) {
        WKWebView *wkWebView = [[WKWebView alloc] initWithFrame:CGRectZero configuration:[[WKWebViewConfiguration alloc] init]];
        [self.view addSubview:wkWebView];
        _wkWebView = wkWebView;
        wkWebView.allowsBackForwardNavigationGestures = YES;
        wkWebView.backgroundColor = [UIColor whiteColor];
        wkWebView.navigationDelegate = self;
        wkWebView.UIDelegate = self;
        if (self.hiddenNavigationBar) {
            [wkWebView makeConstraints:^(MASConstraintMaker *make) {
                make.top.left.right.bottom.offset(0);
            }];
        } else {
            [wkWebView makeConstraints:^(MASConstraintMaker *make) {
                make.top.equalTo(self.tj_navigationBar.bottom);
                make.left.right.bottom.offset(0);
            }];
        }
        [self.view insertSubview:wkWebView belowSubview:self.progressView];
        [_wkWebView addObserver:self forKeyPath:@"estimatedProgress" options:NSKeyValueObservingOptionNew| NSKeyValueObservingOptionOld context:nil];
    }
    return _wkWebView;
}

- (void)back {
    [self.navigationController popViewControllerAnimated:YES];
}

- (UIBarButtonItem *)backItem {
    if (!_backItem) {
        _backItem = [[UIBarButtonItem alloc] initWithImage:[UIImage imageNamed:@"fh－icon"] style:UIBarButtonItemStylePlain target:self action:@selector(popController)];
    }
    return _backItem;
}

- (UIBarButtonItem *)placeholderItem {
    if (!_placeholderItem) {
        _placeholderItem = [UIBarButtonItem spacerBarItem];
    }
    return _placeholderItem;
}

- (UIBarButtonItem *)closeItem {
    if (!_closeItem) {
        _closeItem = [[UIBarButtonItem alloc] initWithTitle:@"关闭" style:UIBarButtonItemStylePlain target:self action:@selector(back)];
        _closeItem.tintColor = [UIColor blackColor];
    }
    return _closeItem;
}

- (void)popController {
    if (self.wkWebView.canGoBack) {
        [self.wkWebView goBack];
        if (@available(iOS 11.0, *)) {
            self.tj_navigationItem.leftBarButtonItems = @[self.backItem, self.closeItem];
        } else {
            self.tj_navigationItem.leftBarButtonItems = @[self.placeholderItem, self.backItem, self.closeItem];
        }
    } else {
        [self back];
    }
}

- (UIProgressView *)progressView {
    if (!_progressView) {
        if (!self.hiddenNavigationBar) {
            _progressView = [[UIProgressView alloc] initWithFrame: CGRectMake(0, TOPMAGRIN - 0.5, KScreenWidth, 0.5)];
        } else {
            _progressView = [[UIProgressView alloc] initWithFrame: CGRectMake(0, 0, KScreenWidth, 0.5)];
        }
        _progressView.progressTintColor = UIColor_Hex(0x3092fd);
        _progressView.trackTintColor = [UIColor whiteColor];
    }
    return _progressView;
}

- (void)observeValueForKeyPath:(NSString *)keyPath ofObject:(id)object change:(NSDictionary<NSKeyValueChangeKey,id> *)change context:(void *)context {
    if ([keyPath isEqual: @"estimatedProgress"] && object == self.wkWebView) {
        [self.progressView setAlpha:1.0f];
        [self.progressView setProgress:self.wkWebView.estimatedProgress animated:YES];
        if(self.wkWebView.estimatedProgress >= 1.0f) {
            [UIView animateWithDuration:0.3f delay:0.3f options:UIViewAnimationOptionCurveEaseOut animations:^{
                [self.progressView setAlpha:0.0f];
                self.progressView.trackTintColor = [UIColor clearColor];
            } completion:^(BOOL finished) {
                [self.progressView setProgress:0.0f animated:NO];
            }];
        }
    } else {
        [super observeValueForKeyPath:keyPath ofObject:object change:change context:context];
    }
}

- (void)dealloc {
    [self.wkWebView removeObserver:self forKeyPath:@"estimatedProgress"];
}

- (void)viewDidAppear:(BOOL)animated {
    [super viewDidAppear:animated];
    if (@available(iOS 11.0, *)) {
        self.tj_navigationItem.leftBarButtonItems = @[self.backItem, self.placeholderButtonItem];
    } else {
        self.tj_navigationItem.leftBarButtonItems = @[self.placeholderItem, self.backItem];
    }
}

- (UIBarButtonItem *)placeholderButtonItem {
    if (!_placeholderButtonItem) {
        UIButton *button = [[UIButton alloc] initWithFrame:CGRectMake(0, 0, 50, 30)];
        [button addTarget:self action:@selector(popController) forControlEvents:UIControlEventTouchUpInside];
        _placeholderButtonItem = [[UIBarButtonItem alloc] initWithCustomView:button];
    }
    return _placeholderButtonItem;
}

@end
