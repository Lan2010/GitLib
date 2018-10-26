//
//  XLBindingMillController.m
//  starChain
//
//  Created by rlx on 2018/6/16.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import "XLBindingMillController.h"
#import <AVFoundation/AVFoundation.h>
#import "XLScanView.h"
#import "XLWebViewController.h"


@interface XLBindingMillController ()<XLScanViewDelegate>

@property (weak, nonatomic) XLScanView *scanView;
@property (strong, nonatomic) AVCaptureDevice *device;
@property (strong, nonatomic) UIActivityIndicatorView *activitydicator;
@property (assign, nonatomic) BOOL lightOn;

@end

@implementation XLBindingMillController

- (void)viewDidLoad {
    [super viewDidLoad];
    [self addSubView];
}

- (void)addSubView {
    
    self.tj_navigationItem.title = @"绑定充电宝";
    self.tj_navigationItem.rightBarButtonItem = [[UIBarButtonItem alloc] initWithImage:[UIImage imageNamed:@"Torch1"] style:UIBarButtonItemStylePlain target:self action:@selector(openFlashlight)];
 
    XLScanView *scanView = [[XLScanView alloc]initWithFrame:CGRectMake(0, TOPMAGRIN, KScreenWidth, KScreenHeight - TOPMAGRIN)];
    scanView.delegate = self;
    _scanView = scanView;
    [self.view addSubview:scanView];
    _device = [AVCaptureDevice defaultDeviceWithMediaType:AVMediaTypeVideo];
    
    [self.view addSubview:self.activitydicator];
    
}

- (void)loadWebPageWithUrlString:(NSString *)urlString title:(NSString *)title {
    if (!urlString.length) return;
    XLWebViewController *webController = [[XLWebViewController alloc] init];
    webController.gestureDisablePop = YES;
    [webController loadWebPageWithTitle:title urlString:urlString];
    [self.navigationController pushViewController:webController animated:YES];
}

- (void)dismissViewController {
    [self dismissViewControllerAnimated:YES completion:nil];
}

- (void)openFlashlight {
    
    if (![_device hasTorch]) {
        [self showMessageAutoHide:@"手电筒坏了"];
        return;
    }
    
    _lightOn = !_lightOn;
    //修改之前一定要锁上
    [_device lockForConfiguration:nil];
    if (_lightOn) {
        self.tj_navigationItem.rightBarButtonItem = [[UIBarButtonItem alloc] initWithImage:[UIImage imageNamed:@"Torch"] style:UIBarButtonItemStylePlain target:self action:@selector(openFlashlight)];
        [_device setTorchMode: AVCaptureTorchModeOn];
    } else {
        self.tj_navigationItem.rightBarButtonItem = [[UIBarButtonItem alloc] initWithImage:[UIImage imageNamed:@"Torch1"] style:UIBarButtonItemStylePlain target:self action:@selector(openFlashlight)];
        [_device setTorchMode: AVCaptureTorchModeOff];
    }
    
    [_device unlockForConfiguration];
    
}

- (void)scanView:(XLScanView *)scanView resultString:(NSString *)resultString {

    [self playSound];
    
    [self alertWithTitle:@"" message:@"是否绑定" leftButtonName:@"取消" rightButtonName:@"确定" leftButtonBlock: ^{
        [scanView startRunning];

    } rightButtonBlock: ^{
        self.activitydicator.hidden = NO;
        [self.activitydicator startAnimating];
        
        [TJNetworkTool requestWithUrl:@"Charge.Bind" parameters:@{@"Mac": resultString} success:^(id data) {
            [self.activitydicator stopAnimating];
            self.activitydicator.hidden = YES;
            
            [self alertWithTitle:@"" message:data[@"msg"] leftButtonName:nil rightButtonName:@"确定" leftButtonBlock: nil rightButtonBlock: ^{
                
                if ([data[@"code"] intValue] == 0) {
                    [self.navigationController popViewControllerAnimated:YES];
                } else {
                    [scanView startRunning];
                }
            }];
        } failure:^(id error) {
            self.activitydicator.hidden = YES;
            [scanView startRunning];
        }];
    }];
 
}

- (UIActivityIndicatorView *)activitydicator {
    if (!_activitydicator) {
        _activitydicator = [[UIActivityIndicatorView alloc] init];
        if (KScreenHeight < 667) {
            _activitydicator.center = CGPointMake(self.view.center.x, self.view.center.y - 32);
        } else {
            _activitydicator.center = self.view.center;
        }
        [_activitydicator setActivityIndicatorViewStyle:UIActivityIndicatorViewStyleGray];
        _activitydicator.transform = CGAffineTransformMakeScale(1.4, 1.6);
        _activitydicator.hidden = YES;
    }
    return _activitydicator;
}

- (void)playSound {
    NSString *soundIDName = @"qrcode_completed.mp3";
    NSString *urlString = [NSString stringWithFormat:@"%@",[[NSBundle mainBundle] pathForResource:soundIDName ofType:nil]];
    NSURL *url = [NSURL URLWithString:urlString];
    static SystemSoundID soundID = 10000;
    AudioServicesCreateSystemSoundID((__bridge CFURLRef _Nonnull)url, &soundID);
    AudioServicesPlaySystemSound(soundID);
}

- (void)viewWillAppear:(BOOL)animated {
    [super viewWillAppear:animated];
    [_scanView startRunning];
}

- (void)viewDidDisappear:(BOOL)animated {
    [super viewDidDisappear:animated];
    [_scanView stopRunning];
    [self.activitydicator stopAnimating];
}

@end
