//
//  XLRefreshController.h
//  starChain
//
//  Created by rlx on 2018/6/6.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import "XLBaseController.h"



@interface XLRefreshController : XLBaseController

@property (weak, nonatomic) UIScrollView *scrollView;
@property (weak, nonatomic) UITableView *tableView;
@property (weak, nonatomic) UICollectionView *collctionView;
@property (weak, nonatomic) MJRefreshHeader *refreshHeader;
@property (weak, nonatomic) MJRefreshFooter *refreshFooter;

@property (assign, nonatomic) UITableViewStyle tableViewStyle;

- (void)loadNewData;
- (void)loadMoreData;

@end
