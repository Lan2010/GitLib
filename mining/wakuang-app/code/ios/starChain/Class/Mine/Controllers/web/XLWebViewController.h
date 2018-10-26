//
//  QHZWebViewController.h
//  qhz
//
//  Created by 夏铁军 on 16/11/7.
//  Copyright © 2016年 qhz. All rights reserved.
//

#import "XLBaseController.h"


@interface XLWebViewController : XLBaseController

@property (assign, nonatomic) BOOL gestureDisablePop;
@property (assign, nonatomic) BOOL isNeedShare;

- (void)postCommitWithUrlString:(NSString *)urlString data:(NSDictionary *)data;
- (void)loadWebPageWithTitle:(NSString *)title urlString:(NSString *)urlString;

- (void)sharewithText:(NSString *)text urlString:(NSString *)urlString imageUrl:(NSString *)imageUrl title:(NSString *)title;


@end


