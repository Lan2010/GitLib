//
//  LVRecordTool.m
//  RecordAndPlayVoice
//
//  Created by PBOC CS on 15/3/14.
//  Copyright (c) 2015年 liuchunlao. All rights reserved.
//


#import <AVFoundation/AVFoundation.h>
#import "LVRecordTool.h"

#define kRecordAudioFile @"myRecord.caf"


@interface LVRecordTool () <AVAudioRecorderDelegate>


@property (nonatomic,strong) AVAudioPlayer *audioPlayer;
@property (nonatomic,strong) AVAudioRecorder *audioRecorder;
@property (strong, nonatomic) AVAudioSession *audioSession;


@end

@implementation LVRecordTool

static id instance;
#pragma mark - 单例
+ (instancetype)sharedRecordTool {
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        if (instance == nil) {
            instance = [[self alloc] init];
        }
    });
    return instance;
}

+ (instancetype)allocWithZone:(struct _NSZone *)zone {
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        if (instance == nil) {
            instance = [super allocWithZone:zone];
        }
    });
    return instance;
}


#pragma mark - 私有方法


/** 录音机设置 */
-(NSDictionary *)getAudioSetting{
    NSMutableDictionary *dicM=[NSMutableDictionary dictionary];
    //设置录音格式
    [dicM setObject:@(kAudioFormatAppleIMA4) forKey:AVFormatIDKey];
    //设置录音采样率，8000是电话采样率，对于一般录音已经够了
    [dicM setObject:@(44100.0f) forKey:AVSampleRateKey];
    //设置通道,这里采用单声道
    [dicM setObject:@(1) forKey:AVNumberOfChannelsKey];
    //每个采样点位数,分为8、16、24、32
    [dicM setObject:@(8) forKey:AVLinearPCMBitDepthKey];
    //是否使用浮点数采样
    [dicM setObject:@(YES) forKey:AVLinearPCMIsFloatKey];
    //....其他设置等
    return dicM;
}

/** 录音机对象 */
-(AVAudioRecorder *)audioRecorder{
    if (!_audioRecorder) {
        //创建录音格式设置
        NSDictionary *setting = [self getAudioSetting];
        //创建录音机
        NSError *error=nil;
        _audioRecorder = [[AVAudioRecorder alloc]initWithURL:self.fileUrl settings:setting error:&error];
        _audioRecorder.delegate=self;
        _audioRecorder.meteringEnabled=YES;//如果要监控声波则必须设置为YES
        [_audioRecorder prepareToRecord];

        if (error) {
            NSLog(@"创建录音机对象时发生错误，错误信息：%@",error.localizedDescription);
            return nil;
        }
    }
    return _audioRecorder;
}

- (NSURL *)fileUrl {
    NSString *urlStr = [NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES) lastObject];
    urlStr = [urlStr stringByAppendingPathComponent:kRecordAudioFile];
    NSLog(@"file path:%@",urlStr);
    return [NSURL fileURLWithPath:urlStr];
}


- (void)startRecording {
    
    [self deleteRecordingFile];
    [self stopPlay];
    
    _audioSession = [AVAudioSession sharedInstance];
    [_audioSession setCategory:AVAudioSessionCategoryPlayAndRecord error:nil];
    [_audioSession setActive:YES error:nil];
    // 设置成扬声器播放
    UInt32 doChangeDefault = 1;
    AudioSessionSetProperty(kAudioSessionProperty_OverrideCategoryDefaultToSpeaker, sizeof(doChangeDefault), &doChangeDefault);
    [self.audioRecorder record];//首次使用应用时如果调用record方法会询问用户是否允许使用麦克风
  
}

- (void)stopRecording {
    if ([self.audioRecorder isRecording]) {
        [self.audioRecorder stop];
     }
 }

- (double)currentTime {
    return self.audioRecorder.currentTime;
}

#pragma mark - 录音机代理方法
-(void)audioRecorderDidFinishRecording:(AVAudioRecorder *)recorder successfully:(BOOL)flag{

    NSLog(@"录音完成!");
}

- (void)playRecordingFile {
    
    // 正在播放就返回
    if ([self.audioPlayer isPlaying]) return;
    
    self.audioPlayer = [[AVAudioPlayer alloc] initWithContentsOfURL:self.fileUrl error:NULL];
     //设置为播放和录音状态，以便可以在录制完之后播放录音
    [self.audioPlayer prepareToPlay];
    [self.audioPlayer play];
}

- (void)deleteRecordingFile {
    NSFileManager *fileManager = [NSFileManager defaultManager];
    [fileManager removeItemAtURL:self.fileUrl error:NULL];
}

- (void)stopPlay {
    if (self.audioPlayer.isPlaying) [self.audioPlayer stop];
}

@end
