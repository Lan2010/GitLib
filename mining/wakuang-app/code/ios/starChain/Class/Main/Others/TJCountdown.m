//
//  TJCountdown.m
//  starChain
//
//  Created by rlx on 2018/6/11.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import "TJCountdown.h"

@implementation TJCountdown


+ (void)startTime:(UIButton *)getCodeButton {
    __block int timeout = 119; //倒计时时间
    dispatch_queue_t queue = dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_DEFAULT, 0);
    dispatch_source_t _timer = dispatch_source_create(DISPATCH_SOURCE_TYPE_TIMER, 0, 0,queue);
    dispatch_source_set_timer(_timer,dispatch_walltime(NULL, 0),1.0*NSEC_PER_SEC, 0); //每秒执行
    
    dispatch_source_set_event_handler(_timer, ^{
        if(timeout <= 0) { //倒计时结束，关闭
            dispatch_source_cancel(_timer);
            dispatch_async(dispatch_get_main_queue(), ^{
                //设置界面的按钮显示 根据自己需求设置（倒计时结束后调用）
                [getCodeButton setTitle:@"发送验证码" forState:UIControlStateNormal];
                getCodeButton.userInteractionEnabled = YES;
                [getCodeButton setTitleColor:[UIColor whiteColor] forState:UIControlStateNormal];
            });
        } else {
            // int minutes = timeout / 60; //这里注释掉了，这个是用来测试多于60秒时计算分钟的。
            int seconds = timeout % 120;
            NSString *strTime = [NSString stringWithFormat:@"%d", seconds];
            dispatch_async(dispatch_get_main_queue(), ^{
                //设置界面的按钮显示 根据自己需求设置
                [getCodeButton setTitle:[NSString stringWithFormat:@"%@s重发",strTime] forState:UIControlStateNormal];
                //设置可点击
                getCodeButton.userInteractionEnabled = NO;
                [getCodeButton setTitleColor:UIColor_Hex(0x999999) forState:UIControlStateNormal];
            });
            timeout--;
        }
    });
    
    dispatch_resume(_timer);
}

@end
