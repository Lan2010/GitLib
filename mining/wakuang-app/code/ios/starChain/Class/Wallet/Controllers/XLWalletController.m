//
//  XLStarController.m
//  starChain
//
//  Created by rlx on 2018/6/16.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import "XLWalletController.h"
#import "XLStarRecordController.h"
#import "XLStarHeaderView.h"
#import "XLHomeSectionHeaderView.h"
#import "XLStarRecordCell.h"

#import <CoreLocation/CoreLocation.h>


@interface XLWalletController ()<XLStarHeaderViewDelegate>

@property (weak, nonatomic) XLStarHeaderView *headerView;

@property (strong, nonatomic) NSMutableArray *datas;
@property (strong, nonatomic) NSArray *starRecordDatas;

@property (assign, nonatomic) NSInteger pageIndex;


@end


static NSString * const starRecordCell = @"starRecordCell";
static NSString * const sectionView = @"sectionView";

@implementation XLWalletController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    self.datas = [NSMutableArray array];
    self.tj_navigationItem.title = @"钱包";
    self.tj_navigationBar.tintColor = [UIColor whiteColor];
    self.titleColor = [UIColor whiteColor];
    self.navBarAlpha = YES;
    self.tableView.mj_footer = self.refreshFooter;
    self.tableView.rowHeight = 60;
    self.pageIndex = 2;
    
    [self addSubView];
    
 
}

- (void)addSubView {
    
    [self.tableView registerClass:[XLStarRecordCell class] forCellReuseIdentifier:starRecordCell];
    [self.tableView makeConstraints:^(MASConstraintMaker *make) {make.edges.offset(0);}];
    
    XLStarHeaderView *headerView = [[XLStarHeaderView alloc] initWithFrame:CGRectMake(0, 0, KScreenWidth, 407 - 105)];
    headerView.delegate = self;
    headerView.starCountLable.text = self.totalCount;
    self.tableView.tableHeaderView = headerView;
    self.headerView = headerView;
    self.tableView.mj_footer.hidden = YES;
 
    [self.view bringSubviewToFront:self.tj_navigationBar];
    
}

- (void)didCLickArrowButton:(XLStarHeaderView *)headerView button:(UIButton *)button {
    headerView.tj_height = (button.selected) ? 407 : 407 - 105;
    headerView.contentView.tj_height = headerView.tj_height - 10;
    self.tableView.tableHeaderView = headerView;
}

- (void)loadNewData {
    
    [self.datas removeAllObjects];
    [self.refreshFooter resetNoMoreData];
    
    NSDictionary *parameters = @{
                                 @"pageindex": @(1),
                                 @"pagesize": @15,
                                 };
    [TJNetworkTool requestWithUrl:@"Account.Records" parameters:parameters success:^(id data) {
 
        self.pageIndex = 2;
        
        if ([data[@"code"] intValue] == 0) {
            self.tableView.mj_footer.hidden = NO;
            self.datas = [XLStarRecordModel mj_objectArrayWithKeyValuesArray:data[@"info"]];
            [self.tableView reloadData];
        }
    } failure:^(id error) { }];
}

- (void)loadStarRecordData {
    
    if (![USERDEFAULTS boolForKey:login]) return;
    
    NSString *currentDateString = [NSString stringWithFormat:@"%.f", [[NSDate date] timeIntervalSince1970] * 1000];
    NSDictionary *parameters = @{
                                 @"days": @30,
                                 @"endTime": currentDateString,
                                 };
    NSLog(@"parameters = %@", parameters);
    
    [TJNetworkTool requestWithUrl:@"User.GetWithDayList" parameters:parameters success:^(id data) {
        
        if ([data[@"code"] intValue] == 0) {
            self.starRecordDatas = data[@"info"];
            self.headerView.starRecordDatas = self.starRecordDatas;
        } else {
            self.starRecordDatas = [NSArray array];
            self.headerView.starRecordDatas = self.starRecordDatas;
        }
    } failure:^(id error) {}];
}

- (void)loadMoreData {
    
    if (![USERDEFAULTS boolForKey:login]) return;
    
    NSDictionary *parameters = @{
                                 @"pageindex": @(_pageIndex),
                                 @"pagesize": @15,
                                 };
    [TJNetworkTool requestWithUrl:@"Account.Records" parameters:parameters success:^(id data) {
        
        
        if ([data[@"code"] intValue]) {
            if (self.pageIndex > 1) {
                [self.refreshFooter endRefreshingWithNoMoreData];
            }
        } else {
            [self.refreshFooter endRefreshing];
            self.tableView.mj_footer.hidden = NO;
            self.pageIndex++;
            [self.datas addObjectsFromArray:[XLStarRecordModel mj_objectArrayWithKeyValuesArray:data[@"info"]]];
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
    if (indexPath.row < self.datas.count) {
        cell.model = self.datas[indexPath.row];
    }
    return  cell;
}

- (void)scrollViewDidScroll:(UIScrollView *)scrollView {
    CGFloat offsetY = scrollView.contentOffset.y;
    if (offsetY < 0) {
        offsetY = 0;
        [scrollView setContentOffset:CGPointMake(scrollView.contentOffset.x, offsetY)];
        self.tj_navigationBar.tintColor = [UIColor whiteColor];
        self.titleColor = [UIColor whiteColor];
    } else {
        if (offsetY >= NavBarHight) {
            self.tj_navigationBar.tintColor = [UIColor clearColor];
            self.titleColor = [UIColor clearColor];
        } else {
            self.tj_navigationBar.tintColor = [UIColor whiteColor];
            self.titleColor = [UIColor whiteColor];
        }
    }
}

#pragma mark - 加载总共的星星数量
- (void)loadTotalStarCountData {
    
    if (![USERDEFAULTS boolForKey:login]) return;
    
    [TJNetworkTool requestWithUrl:@"Account.Info" parameters:nil success:^(id data) {
        
        if ([data[@"code"] intValue] == 0) {
            NSString *availableStarPointString = [NSString stringWithFormat:@"%@", data[@"info"][@"availableStarPoint"]];
            self.headerView.starCount = availableStarPointString;
 
        } else {
            self.headerView.starCount = @"0";
         }
 
    } failure:^(id error) {}];
}

- (void)viewDidAppear:(BOOL)animated {
    [super viewDidAppear:animated];
    
    [self loadTotalStarCountData];
    [self loadStarRecordData];
    [self loadNewData];
}

- (UIStatusBarStyle)preferredStatusBarStyle {
    return UIStatusBarStyleLightContent;
}

@end
