//
//  XLStarRecordController.m
//  starChain
//
//  Created by rlx on 2018/6/16.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import "XLStarRecordController.h"
#import "XLStarRecordCell.h"
#import "XLPageLoadingView.h"

@interface XLStarRecordController ()

@property (strong, nonatomic) XLPageLoadingView *pageLoadingView;

@property (assign, nonatomic) NSInteger pageIndex;
@property (strong, nonatomic) NSMutableArray *datas;

@end

static NSString * const starRecordCell = @"starRecordCell";


@implementation XLStarRecordController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    self.pageIndex = 1;
    self.datas = [NSMutableArray array];
    self.tj_navigationItem.title = @"记录";
    self.tableView.contentInset = UIEdgeInsetsMake(10, 0, 0, 0);
    self.tableView.mj_footer = self.refreshFooter;
    self.tableView.rowHeight = 60;
    [self.tableView registerClass:[XLStarRecordCell class] forCellReuseIdentifier:starRecordCell];
    [self.tableView makeConstraints:^(MASConstraintMaker *make) {make.edges.equalTo(UIEdgeInsetsMake(self.tj_navigationBar.tj_height, 0, 0, 0));}];
    
    [self loadMoreData];
    
    XLPageLoadingView *pageLoadingView = [XLPageLoadingView loadingAnimationWithSuperView:self.view];
    self.pageLoadingView = pageLoadingView;
    pageLoadingView.showType = XLShowTypeLoading;
    
}


- (void)loadMoreData {
    
    NSDictionary *parameters = @{
                                 @"pageindex": @(_pageIndex),
                                 @"pagesize": @15,
                                 };
    
    TJLog(@"parameters = %@", parameters);
    
    [TJNetworkTool requestWithUrl:@"Account.Records" parameters:parameters success:^(id data) {
        
        [self.refreshFooter endRefreshing];
        
        if ([data[@"code"] intValue]) {
            if (self.pageIndex > 1) {
                [self.refreshFooter endRefreshingWithNoMoreData];
            } else {
                self.pageLoadingView.showType = XLShowTypeEmpty;
            }
        } else {
            self.pageIndex++;
            [self.datas addObjectsFromArray:[XLStarRecordModel mj_objectArrayWithKeyValuesArray:data[@"info"]]];
            [self.pageLoadingView stopAnimation];
            [self.tableView reloadData];
        }
    } failure:^(id error) {
        
    }];
}



- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    return self.datas.count;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    XLStarRecordCell *cell = [tableView dequeueReusableCellWithIdentifier:starRecordCell forIndexPath:indexPath];
    cell.model = self.datas[indexPath.row];
    return  cell;
}


@end
