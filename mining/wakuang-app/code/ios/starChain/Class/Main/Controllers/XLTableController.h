//
//  XLTableController.h
//  starChain
//
//  Created by rlx on 2018/6/6.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import "XLBaseController.h"

@interface XLTableController : XLBaseController

@property (assign, nonatomic) UITableViewStyle tableViewStyle;
@property (weak, nonatomic) UITableView *tableView;

@end
