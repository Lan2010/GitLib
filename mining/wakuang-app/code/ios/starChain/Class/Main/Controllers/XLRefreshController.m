//
//  XLRefreshController.m
//  starChain
//
//  Created by rlx on 2018/6/6.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import "XLRefreshController.h"
#import "XLRefreshHeader.h"

@interface XLRefreshController()<UITableViewDataSource, UITableViewDelegate, UICollectionViewDataSource, UICollectionViewDelegate>

@end

@implementation XLRefreshController

- (void)viewDidLoad {
    [super viewDidLoad]; 
    
}

- (UIScrollView *)scrollView {
    if (!_scrollView) {
        UIScrollView *scrollView = [[UIScrollView alloc] init];
        _scrollView = scrollView;
        [self.view addSubview:scrollView];
    }
    return _scrollView;
}

- (UITableView *)tableView {
    if (!_tableView) {
        UITableView *tableView = [[UITableView alloc] initWithFrame:CGRectZero style:self.tableViewStyle];
        tableView.separatorColor = [UIColor tj_separatorColor];
        tableView.backgroundColor = [UIColor tj_backgroundColor];
        tableView.tableFooterView = [[UIView alloc] init];
        tableView.showsHorizontalScrollIndicator = YES;
        tableView.dataSource = self;
        tableView.delegate = self;
        if (iOS11) {
            tableView.contentInsetAdjustmentBehavior = UIScrollViewContentInsetAdjustmentNever;
            tableView.estimatedSectionHeaderHeight = 0;
            tableView.estimatedSectionFooterHeight = 0;
            tableView.estimatedRowHeight = 0;
        }
        _tableView = tableView;
        [self.view addSubview:tableView];
    }
    return _tableView;
}

- (UICollectionView *)collctionView {
    if (!_collctionView) {
        UICollectionViewFlowLayout *flowLayout = [[UICollectionViewFlowLayout alloc] init];
        UICollectionView *collctionView = [[UICollectionView alloc] initWithFrame:CGRectZero collectionViewLayout:flowLayout];
        collctionView.dataSource = self;
        collctionView.delegate = self;
        _collctionView = collctionView;
        [self.view addSubview:collctionView];
    }
    return _collctionView;
}

- (MJRefreshFooter *)refreshFooter {
    if (!_refreshFooter) {
        MJRefreshAutoNormalFooter *refreshAutoNormalFooter = [MJRefreshAutoNormalFooter footerWithRefreshingTarget:self refreshingAction:@selector(loadMoreData)];
        [refreshAutoNormalFooter setTitle:@"正在加载.." forState:MJRefreshStateRefreshing];
        [refreshAutoNormalFooter setTitle:@"没有更多了" forState:MJRefreshStateNoMoreData];
        [refreshAutoNormalFooter setTitle:@"上拉获取更多" forState:MJRefreshStateIdle];
        refreshAutoNormalFooter.stateLabel.textColor = UIColor_Hex(0x999999);
        refreshAutoNormalFooter.activityIndicatorViewStyle = UIActivityIndicatorViewStyleGray;
        _refreshFooter = refreshAutoNormalFooter;
    }
    return _refreshFooter;
}

- (MJRefreshHeader *)refreshHeader {
    if (!_refreshHeader) {
        XLRefreshHeader *refreshHeader = [XLRefreshHeader headerWithRefreshingTarget:self refreshingAction:@selector(loadNewData)];
        _refreshHeader = refreshHeader;
    }
    return _refreshHeader;
}


- (void)loadNewData {}
- (void)loadMoreData {}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    return 0;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    return nil;
}

- (NSInteger)collectionView:(UICollectionView *)collectionView numberOfItemsInSection:(NSInteger)section {
    return 0;
}

- (UICollectionViewCell *)collectionView:(UICollectionView *)collectionView cellForItemAtIndexPath:(NSIndexPath *)indexPath {
    return nil;
}


@end
