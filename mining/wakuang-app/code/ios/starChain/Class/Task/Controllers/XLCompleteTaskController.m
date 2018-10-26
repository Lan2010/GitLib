//
//  XLCompleteTaskController.m
//  starChain
//
//  Created by rlx on 2018/6/12.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import "XLCompleteTaskController.h"
#import "XLCompleteTaskCell.h"
#import "XLCompleteTaskModel.h"
#import "XLPageLoadingView.h"

@interface XLCompleteTaskController ()

@property (strong, nonatomic) XLPageLoadingView *pageLoadingView;
@property (strong, nonatomic) NSMutableArray *datas;

@property (assign, nonatomic) NSInteger pageIndex;


@end

static NSString * const completeTaskCell = @"completeTaskCell";

@implementation XLCompleteTaskController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    self.pageIndex = 1;
    self.tj_navigationItem.title = @"已完成任务";
    self.datas = [NSMutableArray array];
    self.tableView.contentInset = UIEdgeInsetsMake(10, 0, 0, 0);
    self.tableView.mj_footer = self.refreshFooter;
    self.tableView.rowHeight = 60;
    [self.tableView registerClass:[XLCompleteTaskCell class] forCellReuseIdentifier:completeTaskCell];
    [self.tableView makeConstraints:^(MASConstraintMaker *make) {make.edges.equalTo(UIEdgeInsetsMake(self.tj_navigationBar.tj_height, 0, 0, 0));}];
    
    XLPageLoadingView *pageLoadingView = [XLPageLoadingView loadingAnimationWithSuperView:self.view];
    self.pageLoadingView = pageLoadingView;
    pageLoadingView.showType = XLShowTypeLoading;
    
    [self loadMoreData];
    
}

- (void)loadMoreData {
    
    NSDictionary *parameters = @{
                                 @"pageindex": @(_pageIndex),
                                 @"pagesize": @15,
                                 @"type": @1
                                 };
    
    [TJNetworkTool requestWithUrl:@"Task.GetMyTask" parameters:parameters success:^(id data) {
        
        if ([data[@"code"] intValue] == 0) {
            [self.refreshFooter endRefreshing];
            self.pageIndex++;
            [self.pageLoadingView stopAnimation];
            [self.datas addObjectsFromArray: [XLCompleteTaskModel mj_objectArrayWithKeyValuesArray:data[@"info"]]];
            [self.tableView reloadData];
        } else {
            if (self.pageIndex > 1) {
                [self.refreshFooter endRefreshingWithNoMoreData];
            } else {
                self.pageLoadingView.showType = XLShowTypeEmpty;
            }
        }
    } failure:^(id error) { }];
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    return self.datas.count;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    XLCompleteTaskCell *cell = [tableView dequeueReusableCellWithIdentifier:completeTaskCell forIndexPath:indexPath];
    cell.taskModel = self.datas[indexPath.row];
    return  cell;
}



@end
