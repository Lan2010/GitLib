//
//  XLScanView.h
//  starChain
//
//  Created by rlx on 2018/6/16.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import <UIKit/UIKit.h>

@class XLScanView;

@protocol XLScanViewDelegate <NSObject>

-(void)scanView:(XLScanView *)scanView resultString:(NSString *)resultString;

@end


@interface XLScanView : UIView

@property (nonatomic,assign) id <XLScanViewDelegate> delegate;

- (void)removeAnimation;
- (void)startRunning;
- (void)stopRunning;

@end
