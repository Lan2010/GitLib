//
//  QHZGuideController.h
//  qhz
//
//  Created by lei wei on 2017/7/27.
//  Copyright © 2017年 qhz. All rights reserved.
//

#import <UIKit/UIKit.h>

@class JYMaskView, QHZGuideController;

@protocol GuideControllerDelegate <NSObject>

@optional

-(void)dismissGuideController:(QHZGuideController *)guideController maskView:(JYMaskView *)maskView;

@end

@interface QHZGuideController : UIViewController

@property (weak, nonatomic) id <GuideControllerDelegate> delegate;
@property (copy, nonatomic) NSString *controllerName;



@end
