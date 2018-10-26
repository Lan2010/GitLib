//
//  QHZWebViewController.m
//  qhz
//
//  Created by 夏铁军 on 16/11/7.
//  Copyright © 2016年 qhz. All rights reserved.
//

#import "XLWebViewController.h"
#import "WebViewJavascriptBridge.h"


@interface XLWebViewController () <UIWebViewDelegate>

 @property (weak, nonatomic) NSTimer *timer;

@property (strong, nonatomic) UIWebView *webView;
@property (strong, nonatomic) UIBarButtonItem *placeholderItem;
@property (strong, nonatomic) UIBarButtonItem *backItem;
@property (strong, nonatomic) UIBarButtonItem *closeItem;
@property (strong, nonatomic) UIBarButtonItem *placeholderButtonItem;
@property (strong, nonatomic) UIActivityIndicatorView *activitydicator;

@property (copy, nonatomic) NSString *urlString;
@property (copy, nonatomic) NSString *shareText;
@property (copy, nonatomic) NSString *shareUrlString;
@property (copy, nonatomic) NSString *shareTitle;
@property (copy, nonatomic) NSString *shareImgaeUrl;
@property (copy, nonatomic) NSString *tj_title;

@property (assign, nonatomic) BOOL isPost;
@property (assign, nonatomic) BOOL receiveShareSueeccedNotification;

@property WebViewJavascriptBridge *bridge;

@end

@implementation XLWebViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    self.fd_interactivePopDisabled = self.gestureDisablePop;
}

- (void)loadWebPageWithTitle:(NSString *)title urlString:(NSString *)urlString {
    urlString = [urlString replacingEmptyString];
    _tj_title = title;
    _urlString = urlString;
    [self setDefaultTitle];
    [self addBridge];
    [self loadWebPage];
}

- (void)postCommitWithUrlString:(NSString *)urlString data:(NSDictionary *)data {
    urlString = [urlString replacingEmptyString];
    _isPost = YES;
    _urlString =urlString;
    [self setDefaultTitle];
    [self addBridge];
    [self.view addSubview:self.activitydicator];
    [self.view bringSubviewToFront:self.activitydicator];
 }


- (void)setDefaultTitle {
    self.tj_navigationItem.title = (_tj_title.length) ? _tj_title : @"捡星星";
}

- (void)addBridge {
    [WebViewJavascriptBridge enableLogging];
    _bridge = [WebViewJavascriptBridge bridgeForWebView:self.webView];
    [_bridge setWebViewDelegate:self];
    
    [_bridge registerHandler:@"jumpToNative" handler:^(id data, WVJBResponseCallback responseCallback) {
        TJLog(@"交互数据:%@", data);
        if (responseCallback) {
            responseCallback(@"Qianhezi");
        }
    }];
    
    [_bridge registerHandler:@"onShare" handler:^(id data, WVJBResponseCallback responseCallback) {
        if (!data) return ;
        if (responseCallback) {
            responseCallback(@"Qianhezi");
        }
    }];
    
    [_bridge registerHandler:@"closeWebView" handler:^(id data, WVJBResponseCallback responseCallback) {
        [self.navigationController popViewControllerAnimated:YES];
        if (responseCallback) {
            responseCallback(@"Qianhezi");
        }
    }];
}

- (void)jumpNativePageWithIndex:(NSInteger)index {
    UITabBarController *tabBarController = (UITabBarController *)[UIApplication sharedApplication].keyWindow.rootViewController;
    if (tabBarController.selectedIndex == index) {
        [self.navigationController popToRootViewControllerAnimated:YES];
    } else {
        tabBarController.selectedIndex = index;
        [self.navigationController popViewControllerAnimated:YES];
    }
}

- (void)loadWebPage {
    [self.view addSubview:self.activitydicator];
    [self.view bringSubviewToFront:self.activitydicator];
    [self.webView loadRequest:[NSURLRequest requestWithURL:[NSURL URLWithString:_urlString]]];
}

- (UIActivityIndicatorView *)activitydicator {
    if (!_activitydicator) {
        _activitydicator = [[UIActivityIndicatorView alloc] init];
        _activitydicator.center = CGPointMake(self.view.center.x, self.view.center.y);
        [_activitydicator setActivityIndicatorViewStyle:UIActivityIndicatorViewStyleGray];
        [_activitydicator startAnimating];
    }
    return _activitydicator;
}

- (UIWebView *)webView {
    if (!_webView) {
        UIWebView *webView = [[UIWebView alloc] initWithFrame:CGRectZero];
        webView.scrollView.maximumZoomScale = 2;
        webView.scrollView.minimumZoomScale = 0.5;
        webView.scalesPageToFit = YES;
        _webView = webView;
        webView.backgroundColor = [UIColor whiteColor];
        [self.view addSubview:webView];
        
        [webView makeConstraints:^(MASConstraintMaker *make) {
            make.top.equalTo(self.tj_navigationBar.bottom);
            make.left.right.bottom.offset(0);
        }];
        
        if (@available(iOS 11.0, *)) {webView.scrollView.contentInsetAdjustmentBehavior = UIScrollViewContentInsetAdjustmentNever;}

    }
    return _webView;
}

- (void)back {
    [self.activitydicator stopAnimating];
    [self.activitydicator removeFromSuperview];
    [self.navigationController popViewControllerAnimated:YES];
}

- (UIBarButtonItem *)backItem {
    if (!_backItem) {
        _backItem = [[UIBarButtonItem alloc] initWithImage:[UIImage imageNamed:@"fh-icon"] style:UIBarButtonItemStylePlain target:self action:@selector(popController)];
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
    
    [self.activitydicator stopAnimating];

    if (_isPost) {
        [self back];
        return;
    }
    
    if (self.webView.canGoBack) {
        [self.webView goBack];
        if (iOS11) {
            self.tj_navigationItem.leftBarButtonItems = @[self.backItem, self.closeItem];
        } else {
            self.tj_navigationItem.leftBarButtonItems = @[self.placeholderItem, self.backItem, self.closeItem];
        }
    } else {
        [self back];
    }
}

- (void)webViewDidStartLoad:(UIWebView *)webView {
 
    [self.activitydicator startAnimating];
   
    TJLog(@"webViewDidStartLoad");
}

- (void)webViewDidFinishLoad:(UIWebView *)webView {
    if (@available(iOS 11.0, *)) {self.webView.scrollView.contentInsetAdjustmentBehavior = UIScrollViewContentInsetAdjustmentAlways;}

    [self.activitydicator stopAnimating];
    TJLog(@"webViewDidFinishLoad");
 
}

- (void)webView:(UIWebView *)webView didFailLoadWithError:(NSError *)error {
    if (@available(iOS 11.0, *)) {self.webView.scrollView.contentInsetAdjustmentBehavior = UIScrollViewContentInsetAdjustmentAlways;}

    [self.activitydicator stopAnimating];
 
}

- (void)sharewithText:(NSString *)text urlString:(NSString *)urlString imageUrl:(NSString *)imageUrl title:(NSString *)title {
    self.tj_navigationItem.rightBarButtonItem = [[UIBarButtonItem alloc] initWithImage:[UIImage imageNamed:@"share"] style:UIBarButtonItemStylePlain target:self action:@selector(shareActivity)];
    _shareText = text;
    _shareImgaeUrl = imageUrl;
    _shareTitle = title;
    _shareUrlString = urlString;
}

- (void)shareActivity {
    id images = nil;
    if (!_shareImgaeUrl.length || !_shareImgaeUrl) {
        images = [UIImage imageNamed:@"yaoqing"];
    } else {
        images = _shareImgaeUrl;
    }
}

- (void)shareSueeccd:(NSNotification *)notification {

    TJLog(@"分享成功");
 
}

- (void)viewWillAppear:(BOOL)animated {
    [super viewWillAppear:animated];
    
    if (iOS11) {
        self.tj_navigationItem.leftBarButtonItems = @[self.backItem, self.placeholderButtonItem];
    } else {
        self.tj_navigationItem.leftBarButtonItems = @[self.placeholderItem, self.backItem];
    }

    [[NSURLCache sharedURLCache] removeAllCachedResponses];
    
    [self.bridge callHandler:@"webviewOnShow" data:@"qianhezi" responseCallback:^(id responseData) {
        TJLog(@"responseData = %@",responseData);
    }];
    
}

- (UIBarButtonItem *)placeholderButtonItem {
    if (!_placeholderButtonItem) {
        UIButton *button = [[UIButton alloc] initWithFrame:CGRectMake(0, 0, 50, 30)];
        [button addTarget:self action:@selector(popController) forControlEvents:UIControlEventTouchUpInside];
        _placeholderButtonItem = [[UIBarButtonItem alloc] initWithCustomView:button];
    }
    return _placeholderButtonItem;
}

- (UIStatusBarStyle)preferredStatusBarStyle {
    return UIStatusBarStyleDefault;
}



@end
