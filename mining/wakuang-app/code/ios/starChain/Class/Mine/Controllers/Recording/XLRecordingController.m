//
//  XLRecordingController.m
//  starChain
//
//  Created by rlx on 2018/6/20.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import "XLRecordingController.h"
#import "LVRecordTool.h"
#import "XLRecordingAnimationView.h"
#import <AVFoundation/AVFoundation.h>


@interface XLRecordingController ()

@property (strong, nonatomic) LVRecordTool *recordTool;
@property (strong, nonatomic) XLRecordingAnimationView *recordingAnimationView;
@property (weak, nonatomic) UILabel *instructionsLable;
@property (weak, nonatomic) UIButton *recordBtton;
@property (assign, nonatomic) BOOL existRecordFile;
@property (weak, nonatomic) UIView *recordCompleleView;
@property (assign, nonatomic) BOOL granted;

@end

@implementation XLRecordingController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    self.tj_navigationItem.title = @"声音识别";
    self.view.backgroundColor = [UIColor whiteColor];
    self.existRecordFile = NO;
    
    self.recordTool = [LVRecordTool sharedRecordTool];
    
    UILabel *instructionsLable = [UILabel lableWithSuperView:self.view fontSize:14 color:UIColor_Hex(0x777777) title:@"请打开手机话筒，阅读以下这段话" textAlignment:NSTextAlignmentLeft];
    self.instructionsLable = instructionsLable;
    [instructionsLable makeConstraints:^(MASConstraintMaker *make) {
        make.centerX.offset(0);
        make.top.equalTo(self.tj_navigationBar.bottom).offset(29);
    }];
    
//   做任务，看广告，领星星
    UILabel *readingContentLable = [UILabel lableWithSuperView:self.view fontSize:15 color:UIColor_Hex(0x6963c0) title:@"做任务，看广告，领星星" textAlignment:NSTextAlignmentLeft];
    [readingContentLable makeConstraints:^(MASConstraintMaker *make) {
        make.centerX.offset(0);
        make.top.equalTo(instructionsLable.bottom).offset(H(28));
    }];
    
    UIButton *recordBtton = [UIButton buttonWithSuperView:self.view fontSize:16 color:[UIColor whiteColor] title:@"按住说话"];
    [recordBtton setBackgroundImage:[UIImage imageNamed:@"gradientButtonBg"] forState:UIControlStateNormal];
    [recordBtton shearRoundedCornersWithRadiu:ZOOM5(45) * 0.5];
    [recordBtton setTitle:@"按住说话" forState:UIControlStateNormal];
    [recordBtton setTitle:@"松开结束" forState:UIControlStateHighlighted];
    [recordBtton addTarget:self action:@selector(didTouchDown:) forControlEvents:UIControlEventTouchDown];
    [recordBtton addTarget:self action:@selector(didTouchUpInside:) forControlEvents:UIControlEventTouchUpInside];
    [recordBtton addTarget:self action:@selector(didTouchDragExit:) forControlEvents:UIControlEventTouchDragExit];
    [recordBtton makeConstraints:^(MASConstraintMaker *make) {
        make.top.equalTo(readingContentLable.bottom).offset(212);
        make.left.offset(18);
        make.right.offset(-18);
        make.height.offset(xl_bottomButtonH);
    }];
 
    
    _recordingAnimationView = [[XLRecordingAnimationView alloc] initWithFrame:CGRectMake((KScreenWidth - 126) * 0.5, self.instructionsLable.tj_y, 126, 126)];
    [self.view addSubview:_recordingAnimationView];
    _recordingAnimationView.hidden = YES;
    
    [_recordingAnimationView makeConstraints:^(MASConstraintMaker *make) {
        make.top.equalTo(readingContentLable.bottom).offset(28);
        make.centerX.offset(0);
        make.width.height.offset(126);
    }];
    
    UIView *recordCompleleView = [UIView tj_WithSuperView:self.view];
    recordCompleleView.hidden = YES;
    [recordCompleleView makeConstraints:^(MASConstraintMaker *make) {
        make.top.equalTo(readingContentLable.bottom).offset(90);
        make.height.offset(50);
        make.left.offset(18);
        make.right.offset(-18);
    }];
    
    UIView *recordPlayView = [UIView tj_WithSuperView:recordCompleleView];
    recordPlayView.layer.borderWidth = 1;
    recordPlayView.layer.borderColor = UIColor_Hex(0xBBBBBB).CGColor;
    recordPlayView.layer.cornerRadius = 23;
    recordPlayView.backgroundColor = UIColor_Hex(0xEEEDFF);
    [recordPlayView makeConstraints:^(MASConstraintMaker *make) {
        make.left.centerY.offset(0);
        make.height.offset(46);
        make.width.offset(H(240));
    }];
    
    UIButton *recordPlayButton = [UIButton buttonWithSuperView:recordPlayView fontSize:16 color:UIColor_Hex(0x303030) title:@"新录音"];
    [recordPlayButton addTarget:self action:@selector(didRecordPlayButton) forControlEvents:UIControlEventTouchUpInside];
    [recordPlayButton makeConstraints:^(MASConstraintMaker *make) {
        make.centerY.offset(0);
        make.left.offset(10);
        make.width.offset(70);
        make.height.offset(40);
    }];
    
    UIButton *reRecordingButton = [UIButton buttonWithSuperView:recordCompleleView fontSize:16 color:UIColor_Hex(0x6963C0) title:@"重新录制"];
    [reRecordingButton addTarget:self action:@selector(didReRecordingButton) forControlEvents:UIControlEventTouchUpInside];
    [reRecordingButton makeConstraints:^(MASConstraintMaker *make) {
        make.centerY.right.offset(0);
        make.width.offset(70);
        make.height.offset(40);
    }];
    self.recordBtton = recordBtton;
    self.recordCompleleView = recordCompleleView;
    
    
    [self requestAVMediaTypeAudioPermissions];
 
}

- (void)requestAVMediaTypeAudioPermissions {
    [AVCaptureDevice requestAccessForMediaType:AVMediaTypeAudio completionHandler:^(BOOL granted) {
        
        self.granted = granted;
        
        NSLog(@"%@",granted ? @"麦克风准许":@"麦克风不准许");
        if (!granted) {
            [self alertWithTitle:@"提示" message:@"未允许访问麦克风, 不能录音" leftButtonName:@"取消" rightButtonName:@"去开启" leftButtonBlock:nil rightButtonBlock:^{
                [[UIApplication sharedApplication] openURL:[NSURL URLWithString:UIApplicationOpenSettingsURLString]];
            }];
        }
    }];
}

- (BOOL)checkAudioStatus{
    AVAuthorizationStatus authStatus = [AVCaptureDevice authorizationStatusForMediaType:AVMediaTypeAudio];
    if (authStatus == AVAuthorizationStatusAuthorized) {
        return YES;
    } else {
        return NO;
    }
}


/** 播放 */
- (void)didRecordPlayButton {
    NSLog(@"播放录音");
    [self.recordTool playRecordingFile];
}

/** 重新录制 */
- (void)didReRecordingButton {
    [self.recordTool deleteRecordingFile];
    self.existRecordFile = NO;
    
    self.recordCompleleView.hidden = YES;
    [self.recordBtton setTitle:@"按住说话" forState:UIControlStateNormal];
    [self.recordBtton setTitle:@"松开结束" forState:UIControlStateHighlighted];
}

- (void)didTouchDown:(UIButton *)button {
    
    if (self.existRecordFile) {
        self.recordCompleleView.hidden = NO;
        [self.recordBtton setTitle:@"提交" forState:UIControlStateNormal];
        [self.recordBtton setTitle:@"提交" forState:UIControlStateHighlighted];
    } else {
        
        [self requestAVMediaTypeAudioPermissions];
        if (!self.granted) return;
     
        [self.recordTool startRecording];
        _recordingAnimationView.hidden = NO;
        self.recordCompleleView.hidden = YES;
        
        [self.recordBtton setTitle:@"按住说话" forState:UIControlStateNormal];
        [self.recordBtton setTitle:@"松开结束" forState:UIControlStateHighlighted];
    }
}

- (void)didTouchUpInside:(UIButton *)button {
    
    [self requestAVMediaTypeAudioPermissions];
    if (!self.granted) return;

    if (self.existRecordFile) {
        TJLog(@"录音完成提交文件");
         NSData *data = [NSData dataWithContentsOfURL:self.recordTool.fileUrl];
         [TJNetworkTool uploadFileType:XLUpdataFileTypeImage Url:@"User.Upload" fileData:data parameters:@{@"type": @"Voice"} success:^(id data) {
             [self alertWithTitle:@"" message:data[@"msg"] leftButtonName:nil rightButtonName:@"确定" leftButtonBlock:nil rightButtonBlock:^{
                 [self.navigationController popViewControllerAnimated:YES];
             }];
        } failure:^(id error) {}];
        
        return;
    }
    
    double currentTime = self.recordTool.currentTime;
    
    NSLog(@"%lf", currentTime);
    
    _recordingAnimationView.hidden = YES;

    if (currentTime < 1) {
        [self alertWithMessage:@"说话时间太短"];
        _recordingAnimationView.hidden = YES;
        self.existRecordFile = NO;

        dispatch_async(dispatch_get_global_queue(0, 0), ^{
            [self.recordTool stopRecording];
            [self.recordTool deleteRecordingFile];
        });
    } else {
        
        dispatch_async(dispatch_get_global_queue(0, 0), ^{
            [self.recordTool stopRecording];
            dispatch_async(dispatch_get_main_queue(), ^{
                
                self.existRecordFile = YES;
                self.recordCompleleView.hidden = NO;
                [self.recordBtton setTitle:@"提交" forState:UIControlStateNormal];
                
                NSLog(@"已成功录音");
                
                TJLog(@"self.recordTool.existFile = %d", self.self.existRecordFile);
            });
        });
    }
    
}

- (void)didTouchDragExit:(UIButton *)button {
    
    _recordingAnimationView.hidden = YES;

    dispatch_async(dispatch_get_global_queue(0, 0), ^{
        
        [self.recordTool stopRecording];
        [self.recordTool deleteRecordingFile];
        
        dispatch_async(dispatch_get_main_queue(), ^{
            [self alertWithMessage:@"已取消录音"];
        });
    });
}

#pragma mark - 弹窗提示
- (void)alertWithMessage:(NSString *)message {
    UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"提示" message:message delegate:self cancelButtonTitle:@"确定" otherButtonTitles: nil];
    [alert show];
}

- (void)dealloc {
    TJLog(@"dealloc");
    [self.recordTool stopRecording];
    [self.recordTool stopPlay];
}

@end
