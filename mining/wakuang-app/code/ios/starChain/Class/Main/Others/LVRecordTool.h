//
//  LVRecordTool.h
//  RecordAndPlayVoice
//
//  Created by PBOC CS on 15/3/14.
//  Copyright (c) 2015年 liuchunlao. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface LVRecordTool : NSObject

@property (strong, nonatomic, readonly) NSURL *fileUrl;

@property (assign, nonatomic, readonly) double currentTime;

- (void)startRecording;
- (void)stopRecording;
- (void)stopPlay;
- (void)playRecordingFile;
- (void)deleteRecordingFile;

+ (instancetype)sharedRecordTool;

@end
