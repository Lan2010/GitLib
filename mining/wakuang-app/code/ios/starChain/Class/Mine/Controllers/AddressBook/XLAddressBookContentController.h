//
//  XLAddressBookContentController.h
//  starChain
//
//  Created by rlx on 2018/7/12.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import "XLTableController.h"

@interface XLAddressBookContentController : XLTableController

@property (assign, nonatomic) NSInteger type;
@property (copy, nonatomic) void (^loadDataComplete)(void);

@end
