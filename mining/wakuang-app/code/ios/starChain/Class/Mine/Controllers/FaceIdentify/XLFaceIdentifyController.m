//
//  XLFaceIdentifyController.m
//  starChain
//
//  Created by rlx on 2018/6/25.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import "XLFaceIdentifyController.h"
#import <AVFoundation/AVFoundation.h>

@interface XLFaceIdentifyController ()<AVCaptureMetadataOutputObjectsDelegate, AVCaptureVideoDataOutputSampleBufferDelegate>

@property (strong, nonatomic) AVCaptureDevice *device;
@property (strong, nonatomic) AVCaptureDeviceInput *input;
@property (strong, nonatomic) AVCaptureMetadataOutput *output;
@property (strong, nonatomic) AVCaptureSession *session;
@property (strong, nonatomic) AVCaptureVideoPreviewLayer *previewLayer;
@property (strong, nonatomic) AVCaptureVideoDataOutput *imageOutPut;
@property (strong, nonatomic) UIImage *image;
@property (strong, nonatomic) CABasicAnimation *transformAnimation;

@property (weak, nonatomic) UIImageView *rotateImageView;
@property (weak, nonatomic) UIView *maskView;
@property (weak, nonatomic) MBProgressHUD *hud;

@property (assign, nonatomic) BOOL isFace;

@end

@implementation XLFaceIdentifyController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    self.tj_navigationItem.title = @"人脸识别";
    self.image = nil;
    
    AVAuthorizationStatus authStatus = [AVCaptureDevice authorizationStatusForMediaType:AVMediaTypeVideo];
    if (authStatus == AVAuthorizationStatusDenied) {
        UIAlertView *alertView = [[UIAlertView alloc]initWithTitle:@"请打开相机权限" message:@"设置-隐私-相机" delegate:self cancelButtonTitle:@"确定" otherButtonTitles:@"取消", nil];
        alertView.tag = 100;
        [alertView show];
    } else{
        [self customCamera];
        [self addSubView];
    }
}

- (void)addSubView {
    
    UIView *maskView = [[UIView alloc] initWithFrame:self.previewLayer.frame];
    maskView.backgroundColor = [UIColor whiteColor];
    [self.view addSubview:maskView];
    self.maskView = maskView;
    
    UIImageView *rotateImageView = [[UIImageView alloc] initWithImage:[UIImage imageNamed:@"rotate"]];
    [maskView addSubview:rotateImageView];
    rotateImageView.center = CGPointMake(maskView.center.x, maskView.center.y - H(120));
    rotateImageView.bounds = CGRectMake(0, 0, H(265), H(265));
    
    UIBezierPath *path = [UIBezierPath bezierPathWithRect:maskView.bounds];
    UIBezierPath *circlePath = [UIBezierPath bezierPathWithArcCenter:rotateImageView.center radius:H(120) startAngle:0 endAngle:2 * M_PI clockwise:NO];
    [path appendPath:circlePath];
    CAShapeLayer *shapeLayer = [CAShapeLayer layer];
    shapeLayer.path = path.CGPath;
    maskView.layer.mask = shapeLayer;
    
    [rotateImageView.layer addAnimation:self.transformAnimation forKey:@"rotation"];
    
    CGSize scanViewSize = rotateImageView.bounds.size;
    
    CGFloat left = (self.previewLayer.frame.size.width - scanViewSize.width) * 0.5;
    CGFloat top = (self.previewLayer.frame.size.height - scanViewSize.height) * 0.5;
    
    [self.output setRectOfInterest:CGRectMake(top / self.previewLayer.frame.size.height, left / self.previewLayer.frame.size.width, scanViewSize.height / self.previewLayer.frame.size.height, scanViewSize.width /  self.previewLayer.frame.size.width)];
    self.rotateImageView = rotateImageView;
    
    
}

- (CABasicAnimation *)transformAnimation {
    if (!_transformAnimation) {
        CABasicAnimation *animation = [CABasicAnimation animationWithKeyPath:@"transform.rotation.z"];
        animation.fromValue = @(0);
        animation.toValue = @(M_PI * 2);
        animation.repeatCount = MAXFLOAT;
        animation.duration = 1.5;
        animation.fillMode = kCAFillModeForwards;
        animation.removedOnCompletion = NO;
        _transformAnimation = animation;
    }
    return _transformAnimation;
}

- (void)customCamera{
    
    self.view.backgroundColor = [UIColor whiteColor];
    
    self.device = [AVCaptureDevice defaultDeviceWithMediaType:AVMediaTypeVideo];
    self.input = [[AVCaptureDeviceInput alloc]initWithDevice:self.device error:nil];
    self.output = [[AVCaptureMetadataOutput alloc] init];
    self.imageOutPut = [[AVCaptureVideoDataOutput alloc] init];
    self.session = [[AVCaptureSession alloc] init];
    
    
    if ([self.session canSetSessionPreset:AVCaptureSessionPreset1280x720]) {
        self.session.sessionPreset = AVCaptureSessionPreset1280x720;
    }
    
    if ([self.session canAddInput:self.input]) {
        [self.session addInput:self.input];
    }
    
    if ([self.session canAddOutput:self.output]) {
        [self.session addOutput:self.output];
        self.output.metadataObjectTypes = @[AVMetadataObjectTypeFace];
        [self.output setMetadataObjectsDelegate:self queue:dispatch_get_main_queue()];
    }
    
    if ([self.session canAddOutput:self.imageOutPut]) {
        [self.session addOutput:self.imageOutPut];
        dispatch_queue_t queue = dispatch_queue_create("myQueue", NULL);
        [self.imageOutPut setSampleBufferDelegate:self queue:queue];
        self.imageOutPut.videoSettings = [NSDictionary dictionaryWithObject: [NSNumber numberWithInt:kCVPixelFormatType_32BGRA] forKey:(id)kCVPixelBufferPixelFormatTypeKey];
    }
    
    self.previewLayer = [[AVCaptureVideoPreviewLayer alloc]initWithSession:self.session];
    self.previewLayer.frame = CGRectMake(0, NavBarHight, KScreenWidth, KScreenHeight - NavBarHight);
    self.previewLayer.videoGravity = AVLayerVideoGravityResizeAspectFill;
    [self.view.layer addSublayer:self.previewLayer];
    
    self.previewLayer.connection.videoOrientation = AVCaptureVideoOrientationPortrait;
    
    [self.session startRunning];
    [self switchCamera];
}

- (AVCaptureVideoOrientation)avOrientationForDeviceOrientation:(UIDeviceOrientation)deviceOrientation
{
    AVCaptureVideoOrientation result = (AVCaptureVideoOrientation)deviceOrientation;
    if ( deviceOrientation == UIDeviceOrientationLandscapeLeft )
        result = AVCaptureVideoOrientationLandscapeRight;
    else if ( deviceOrientation == UIDeviceOrientationLandscapeRight )
        result = AVCaptureVideoOrientationLandscapeLeft;
    return result;
}

- (void)captureOutput:(AVCaptureOutput *)output didOutputSampleBuffer:(CMSampleBufferRef)sampleBuffer fromConnection:(AVCaptureConnection *)connection {
    
    if(sampleBuffer == NULL)  {
        return ;
    }
    
    if (self.isFace) {
        
        if (!self.image) {
            
            UIImage *oldImage = [self imageFromSampleBuffer:sampleBuffer];
            NSData *newData = UIImageJPEGRepresentation(oldImage, 0.3);
            UIImage *image = [UIImage imageWithData:newData];
            
            UIDeviceOrientation orientation = [UIDevice currentDevice].orientation;
            if (orientation != UIDeviceOrientationPortrait) {
                CGFloat degree = 0;
                if (orientation == UIDeviceOrientationPortraitUpsideDown) {
                    degree = 180;// M_PI;
                } else if (orientation == UIDeviceOrientationLandscapeLeft) {
                    degree = -90;// -M_PI_2;
                } else if (orientation == UIDeviceOrientationLandscapeRight) {
                    degree = 90;// M_PI_2;
                }
                image = [image rotatedByDegrees:degree];
            }
            
            self.image = image;
            
            dispatch_async(dispatch_get_main_queue(), ^{
                self.hud =  [MBProgressHUD showLogining:KeyWindow message:@"正在识别"];
                [self updateImageWithImage:image];
            });
        }
    }
    
}


- (UIImage *)imageFromSampleBuffer:(CMSampleBufferRef) sampleBuffer {
    CVImageBufferRef imageBuffer = CMSampleBufferGetImageBuffer(sampleBuffer);
    CVPixelBufferLockBaseAddress(imageBuffer, 0);
    
    void *baseAddress = CVPixelBufferGetBaseAddress(imageBuffer);
    
    size_t bytesPerRow = CVPixelBufferGetBytesPerRow(imageBuffer);
    size_t width = CVPixelBufferGetWidth(imageBuffer);
    size_t height = CVPixelBufferGetHeight(imageBuffer);
    
    CGColorSpaceRef colorSpace = CGColorSpaceCreateDeviceRGB();
    CGContextRef context = CGBitmapContextCreate(baseAddress, width, height, 8,
                                                 bytesPerRow, colorSpace, kCGBitmapByteOrder32Little | kCGImageAlphaPremultipliedFirst);
    CGImageRef quartzImage = CGBitmapContextCreateImage(context);
    CVPixelBufferUnlockBaseAddress(imageBuffer,0);
    
    CGContextRelease(context);
    CGColorSpaceRelease(colorSpace);
    
    UIImage *image = [UIImage imageWithCGImage:quartzImage scale:1.0f orientation:UIImageOrientationRight];
    CGImageRelease(quartzImage);
    
    return (image);
}

#pragma mark - 人脸识别
- (void)captureOutput:(AVCaptureOutput *)captureOutput didOutputMetadataObjects:(NSArray *)metadataObjects fromConnection:(AVCaptureConnection *)connection {
    NSArray *transformedFaces = [self transformedFaceFromFace:metadataObjects];
    
    for (AVMetadataFaceObject *face in transformedFaces) {
        
        TJLog(@"facebounds = %@", NSStringFromCGRect(face.bounds));
        TJLog(@"rotateImageViewbounds = %@", NSStringFromCGRect(self.rotateImageView.frame));
        
        if (CGRectContainsRect(self.rotateImageView.frame, face.bounds)) {//检测到人脸
            self.isFace = YES;
            return;
        } else {
            self.isFace = NO;
        }
        
    }
}

- (NSArray*)transformedFaceFromFace:(NSArray*)faces {
    NSMutableArray *newFaces = [[NSMutableArray alloc] init];
    for (AVMetadataObject *face in faces) {
        AVMetadataObject *newFace = [self.previewLayer transformedMetadataObjectForMetadataObject:face];
        [newFaces addObject:newFace];
    }
    return newFaces;
}

- (void)updateImageWithImage:(UIImage *)image {
    TJLog(@"image = %@", image);
    NSData *data = UIImagePNGRepresentation(image);
    [TJNetworkTool uploadFileType:XLUpdataFileTypeImage Url:@"User.Upload" fileData:data parameters:@{@"type": @"Face"} success:^(id data) {
        [self.hud hideHUDAfter:0];
        [self alertWithTitle:@"" message:data[@"msg"] leftButtonName:nil rightButtonName:@"确定" leftButtonBlock:nil rightButtonBlock: ^{
            [self.navigationController popViewControllerAnimated:YES];
        }];
    } failure:^(id error) {}];
}

#pragma - 保存至相册
- (void)saveImageToPhotoAlbum:(UIImage*)savedImage {
    UIImageWriteToSavedPhotosAlbum(savedImage, nil, nil, nil);
}

#pragma mark - 检查相机权限
- (BOOL)canUserCamear{
    AVAuthorizationStatus authStatus = [AVCaptureDevice authorizationStatusForMediaType:AVMediaTypeVideo];
    if (authStatus == AVAuthorizationStatusDenied) {
        UIAlertView *alertView = [[UIAlertView alloc]initWithTitle:@"请打开相机权限" message:@"设置-隐私-相机" delegate:self cancelButtonTitle:@"确定" otherButtonTitles:@"取消", nil];
        alertView.tag = 100;
        [alertView show];
        return NO;
    }
    else{
        return YES;
    }
    return YES;
}

#pragma mark - 切换前后摄像头
-(void)switchCamera{
    NSUInteger cameraCount = [[AVCaptureDevice devicesWithMediaType:AVMediaTypeVideo] count];
    if (cameraCount > 1) {
        AVCaptureDevice *newCamera = nil;
        AVCaptureDeviceInput *newInput = nil;
        AVCaptureDevicePosition position = [[self.input device] position];
        
        if (position == AVCaptureDevicePositionFront){
            newCamera = [self cameraWithPosition:AVCaptureDevicePositionBack];
        }else {
            newCamera = [self cameraWithPosition:AVCaptureDevicePositionFront];
        }
        newInput = [AVCaptureDeviceInput deviceInputWithDevice:newCamera error:nil];
        if (newInput != nil) {
            [self.session beginConfiguration];
            [self.session removeInput:self.input];
            if ([self.session canAddInput:newInput]) {
                [self.session addInput:newInput];
                self.input = newInput;
            }else {
                [self.session addInput:self.input];
            }
            [self.session commitConfiguration];
        }
    }
}

- (AVCaptureDevice *)cameraWithPosition:(AVCaptureDevicePosition)position{
    NSArray *devices = [AVCaptureDevice devicesWithMediaType:AVMediaTypeVideo];
    for ( AVCaptureDevice *device in devices )
        if ( device.position == position ) return device;
    return nil;
}

@end
