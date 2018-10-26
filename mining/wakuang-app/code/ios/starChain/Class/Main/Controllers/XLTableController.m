//
//  XLTableController.m
//  starChain
//
//  Created by rlx on 2018/6/6.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import "XLTableController.h"

@interface XLTableController ()<UITableViewDataSource, UITableViewDelegate>

@end

@implementation XLTableController

- (void)viewDidLoad {
    [super viewDidLoad];
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

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    return 0;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    return nil;
}

@end
