//
//  XLLoginController.h
//  starChain
//
//  Created by rlx on 2018/6/11.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import "XLBaseController.h"

@interface XLLoginController : XLBaseController

@property (copy, nonatomic) void(^loginSuccess)(void);
@property (assign, nonatomic) BOOL dissmissSingleController;

@end
