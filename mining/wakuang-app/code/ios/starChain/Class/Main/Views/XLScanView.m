//
//  XLScanView.m
//  starChain
//
//  Created by rlx on 2018/6/16.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import "XLScanView.h"
#import <AVFoundation/AVFoundation.h>

@interface XLScanView ()<AVCaptureMetadataOutputObjectsDelegate>

@property (weak, nonatomic) UIView *invitationButton;
@property (weak, nonatomic) UIImageView *scanView;
@property (weak, nonatomic) UIImageView *lineView;
@property (weak, nonatomic) UILabel *instructions;

@property (strong, nonatomic) AVCaptureSession *session;
@property (strong, nonatomic) AVCaptureDevice *device;
@property (strong, nonatomic) CAShapeLayer *cropLayer;

@property (copy, nonatomic) NSString *scanMessage;

@property (assign, nonatomic) NSInteger line_tag;
@property (assign, nonatomic) BOOL isRequesting;
@property (assign, nonatomic) NSInteger scanW;


@end

@implementation XLScanView

- (instancetype)initWithFrame:(CGRect)frame{
    if (self = [super initWithFrame:frame]) {
        [self instanceDevice];
    }
    return self;
}
 
- (void)instanceDevice{
    
    CGSize scanViewSize = [UIImage imageNamed:@"kuang"].size;
    CGFloat left = (self.frame.size.width - scanViewSize.width) * 0.5;
    CGFloat top = (self.frame.size.height - scanViewSize.height) * 0.5 - 32;
    
    [self addScanInterfaceWithRect:CGRectMake(left, top, scanViewSize.width, scanViewSize.height)];//必须先添加才有作用
    
    self.device = [AVCaptureDevice defaultDeviceWithMediaType:AVMediaTypeVideo];
    if ([self.device lockForConfiguration:nil]) {
        //自动对焦
        if ([self.device isFocusModeSupported:AVCaptureFocusModeContinuousAutoFocus]) {
            [self.device setFocusMode:AVCaptureFocusModeContinuousAutoFocus];
        }
        [self.device unlockForConfiguration];
    }
    
    AVCaptureDeviceInput *input = [AVCaptureDeviceInput deviceInputWithDevice: self.device error:nil];
    
    AVCaptureMetadataOutput *output = [[AVCaptureMetadataOutput alloc]init];
    [output setMetadataObjectsDelegate:self queue:dispatch_get_main_queue()];
    
    ///top 与 left 互换  width 与 height 互换
    [output setRectOfInterest:CGRectMake(top / self.frame.size.height, left / self.frame.size.width, scanViewSize.height / self.frame.size.height, scanViewSize.width /  self.frame.size.width)];
    self.session = [[AVCaptureSession alloc]init];
    [self.session setSessionPreset:AVCaptureSessionPresetHigh];
    self.session.sessionPreset = AVCaptureSessionPresetPhoto;
    
    if ([self.session canAddInput:input]) {
        [self.session addInput:input];
    }
    
    if ([self.session canAddOutput:output]) {
        [self.session addOutput:output];
        
        NSMutableArray *metadataObjectTypes = [[NSMutableArray alloc] init];
        
        if ([output.availableMetadataObjectTypes containsObject:AVMetadataObjectTypeQRCode]) {
            [metadataObjectTypes addObject:AVMetadataObjectTypeQRCode];
        }
        if ([output.availableMetadataObjectTypes containsObject:AVMetadataObjectTypeEAN13Code]) {
            [metadataObjectTypes addObject:AVMetadataObjectTypeEAN13Code];
        }
        if ([output.availableMetadataObjectTypes containsObject:AVMetadataObjectTypeEAN8Code]) {
            [metadataObjectTypes addObject:AVMetadataObjectTypeEAN8Code];
        }
        if ([output.availableMetadataObjectTypes containsObject:AVMetadataObjectTypeCode128Code]) {
            [metadataObjectTypes addObject:AVMetadataObjectTypeCode128Code];
        }
        output.metadataObjectTypes = metadataObjectTypes;
        
    }
    
    AVCaptureVideoPreviewLayer *layer = [AVCaptureVideoPreviewLayer layerWithSession:_session];
    layer.videoGravity = AVLayerVideoGravityResizeAspectFill;
    layer.frame = self.layer.bounds;
    [self.layer insertSublayer:layer atIndex:0];
 
    [self.session addObserver:self forKeyPath:@"running" options:NSKeyValueObservingOptionNew context:nil];
//    [self startRunning];
}

- (void)subjectAreaDidChange {
    //先进行判断是否支持控制对焦
    
    TJLog(@"subjectAreaDidChange");
    
    if (_device.isFocusPointOfInterestSupported && [_device isFocusModeSupported:AVCaptureFocusModeAutoFocus]) {
 
        NSError *error = nil;
        //对cameraDevice进行操作前，需要先锁定，防止其他线程访问，
        [_device lockForConfiguration:&error];
        [_device setFocusMode:AVCaptureFocusModeAutoFocus];
        [_device setFocusPointOfInterest:self.center];
        //操作完成后，记得进行unlock。
        [_device unlockForConfiguration];
    }
}

- (void)observeValueForKeyPath:(NSString *)keyPath
                      ofObject:(id)object
                        change:(NSDictionary *)change
                       context:(void *)context{
    
    if ([object isKindOfClass:[AVCaptureSession class]]) {
        BOOL isRunning = ((AVCaptureSession *)object).isRunning;
        if (isRunning) {
            [self addAnimation];
        }
    }
}

-(void)captureOutput:(AVCaptureOutput *)captureOutput didOutputMetadataObjects:(NSArray *)metadataObjects fromConnection:(AVCaptureConnection *)connection{
    if (metadataObjects.count>0) {
        
        [self stopRunning];
        [self removeAnimation];
        
        AVMetadataMachineReadableCodeObject * metadataObject = [metadataObjects objectAtIndex :0];
        //输出扫描字符串
        NSString *data = metadataObject.stringValue;
        
        if (data) {
            NSLog(@"%@",data);
            _scanMessage = data;
            if(self.delegate && [self.delegate respondsToSelector:@selector(scanView:resultString:)]) {
                [self.delegate scanView:self resultString:_scanMessage ];
            }
            NSLog(@"%@",_scanMessage);
        }
    }
}


- (void)addScanInterfaceWithRect:(CGRect)rect{
    
    self.cropLayer = [[CAShapeLayer alloc] init];
    CGMutablePathRef path = CGPathCreateMutable();
    CGPathAddRect(path, nil, rect);
    CGPathAddRect(path, nil, self.bounds);
    
    [self.cropLayer setFillRule:kCAFillRuleEvenOdd];
    [self.cropLayer setPath:path];
    [self.cropLayer setFillColor:[UIColor blackColor].CGColor];
    [self.cropLayer setOpacity:0.5];
    [self.cropLayer setNeedsDisplay];
    [self.layer addSublayer:self.cropLayer];
    
    UIImageView *scanView = [[UIImageView alloc] init];
    scanView.frame = rect;
    self.scanView = scanView;
    [self addSubview:scanView];
    scanView.image = [UIImage imageNamed:@"kuang"];
    
    UIImageView *lineView = [[UIImageView alloc] init];
    lineView.frame = CGRectMake(0, 0, rect.size.width, 2);
    [scanView addSubview:lineView];
    lineView.image = [UIImage imageNamed:@"scanline"];
    lineView.contentMode = UIViewContentModeScaleAspectFill;
    lineView.hidden = YES;
    lineView.clipsToBounds = YES;
    self.lineView = lineView;
    
    UILabel *instructions = [UILabel lableWithSuperView:self fontSize:12 color:[UIColor whiteColor] title:@"扫描充电宝二维码, 绑定充电宝" textAlignment:NSTextAlignmentCenter];
    CGFloat width = [instructions.text getStringSizeWithWidth:KScreenWidth fontSize:12].width;
    instructions.frame = CGRectMake((KScreenWidth - width) * 0.5, CGRectGetMaxY(_scanView.frame) + 5, width, 30);
    self.instructions = instructions;
}

 
- (void)addAnimation{
    self.lineView.hidden = NO;
    CABasicAnimation *animationMove = [CABasicAnimation animationWithKeyPath:@"transform.translation.y"];
    [animationMove setFromValue:@2];
    [animationMove setToValue:@(self.scanView.frame.size.height - 2)];
    animationMove.duration = 2;
    animationMove.repeatCount = OPEN_MAX;
    animationMove.fillMode = kCAFillModeForwards;
    animationMove.removedOnCompletion = NO;
    animationMove.autoreverses = YES;
    animationMove.timingFunction = [CAMediaTimingFunction functionWithName:kCAMediaTimingFunctionEaseInEaseOut];
    [self.lineView.layer addAnimation:animationMove forKey:@"LineAnimation"];
}

- (void)removeAnimation {
    [self.lineView.layer removeAnimationForKey:@"LineAnimation"];
    self.lineView.hidden = YES;
}

- (void)startRunning {
    [self.session startRunning];
}

- (void)stopRunning {
    [self.session stopRunning];
}

- (void)dealloc{
    [self.session removeObserver:self forKeyPath:@"running"];
}

@end
