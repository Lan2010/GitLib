//
//  XLTaskController.m
//  starChain
//
//  Created by rlx on 2018/6/6.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import "XLTaskController.h"
#import "XLTaskCell.h"
#import "XLPageLoadingView.h"
#import "XLTaskModel.h"
#import <AVFoundation/AVCaptureDevice.h>
#import <AVFoundation/AVMediaFormat.h>
#import "XLAlertViewController.h"

#import <BaiduMapAPI_Location/BMKLocationComponent.h>

 
@interface XLTaskController ()

@property (assign, nonatomic) NSInteger pageIndex;
@property (strong, nonatomic) NSMutableArray *datas;

@property (weak, nonatomic) XLPageLoadingView *pageLoadingView;
@property (assign, nonatomic) BOOL openLocation;
@property (assign, nonatomic) NSInteger cityCode;

@end

static NSString * const taskCell = @"taskCell";

@implementation XLTaskController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    [self addSubView];
    [self loadNewData];
    [self listeningNotification];
}

- (void)addSubView {
    
    self.pageIndex = 2;
    self.datas = [NSMutableArray array];
    self.tj_navigationItem.title = @"任务";
    self.cityCode = [XLGlobalFunc globalFunc].cityCode;
    

    self.tableView.mj_header = self.refreshHeader;
    self.tableView.mj_footer = self.refreshFooter;
    self.hiddenLineView = YES;
    self.tableView.rowHeight = 130;
    self.tableView.backgroundColor = [UIColor whiteColor];
    self.tableView.separatorStyle = UITableViewCellSeparatorStyleNone;
    [self.tableView registerClass:[XLTaskCell class] forCellReuseIdentifier:taskCell];
    [self.tableView makeConstraints:^(MASConstraintMaker *make) {make.edges.equalTo(UIEdgeInsetsMake(self.tj_navigationBar.tj_height, 0, 0, 0));}];
    
    XLPageLoadingView *pageLoadingView = [XLPageLoadingView loadingAnimationWithSuperView:self.view];
    self.pageLoadingView = pageLoadingView;
    pageLoadingView.showType = XLShowTypeLoading;
    if (!self.openLocation) {
        pageLoadingView.showType = XLShowTypelocationError;
    }
    
    [pageLoadingView setDidClickReloadButtonBlock:^{        
        [self loadNewData];
    }];
}

- (BOOL)openLocation {
    CLAuthorizationStatus authorizationStatus = [CLLocationManager authorizationStatus];
    if (![CLLocationManager locationServicesEnabled] || authorizationStatus  == kCLAuthorizationStatusDenied || authorizationStatus == kCLAuthorizationStatusNotDetermined || authorizationStatus == kCLAuthorizationStatusRestricted) {
        return NO;
    } else {
        return YES;
    }
}

- (void)listeningNotification {
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(loadNewData) name:loginSucceedNotification object:nil];
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(applicationWillEnterForeground) name:UIApplicationWillEnterForegroundNotification object:nil];
}

- (void)dealloc {
    [[NSNotificationCenter defaultCenter] removeObserver:self name:loginSucceedNotification object:nil];
    [[NSNotificationCenter defaultCenter] removeObserver:self name:UIApplicationWillEnterForegroundNotification object:nil];
}

- (void)loadNewData {
    
    [self loadUserInfoData];
    [self.refreshFooter resetNoMoreData];
 
    CLLocationCoordinate2D  coordinate = [XLGlobalFunc globalFunc].coordinate;
    if (coordinate.latitude == 0 || coordinate.longitude == 0 || self.cityCode == 0) return;
    
    NSDictionary *parameters = @{
                                 @"pageindex": @1,
                                 @"pagesize": @15,
                                 @"lat": @(coordinate.latitude),
                                 @"lng": @(coordinate.longitude),
                                 @"mi": @2000,
                                 @"cityCode": @(self.cityCode)
                                 };
    [TJNetworkTool requestWithUrl:@"Task.GetTask" parameters:parameters success:^(id data) {
        
        [self.refreshHeader endRefreshing];
        
        if ([data[@"code"] intValue] == 0) {
            
            self.pageIndex = 2;
            [self.pageLoadingView stopAnimation];
             self.datas = [XLTaskModel mj_objectArrayWithKeyValuesArray:data[@"info"]];
            [self.tableView reloadData];
            
        } else {
            self.pageLoadingView.showType = XLShowTypeEmpty;
        }
    } failure:^(id error) {
        self.pageLoadingView.showType = XLShowTypeLoadError;
        [self.refreshHeader endRefreshing];
    }];
}

- (void)loadMoreData {
    
    CLLocationCoordinate2D coordinate = [XLGlobalFunc globalFunc].coordinate;
    if (coordinate.latitude == 0 || coordinate.longitude == 0 || self.cityCode == 0) return;
    
    NSDictionary *parameters = @{
                                 @"pageindex": @(_pageIndex),
                                 @"pagesize": @15,
                                 @"lat": @(coordinate.latitude),
                                 @"lng": @(coordinate.longitude),
                                 @"mi": @2000,
                                 @"cityCode": @(self.cityCode)
                                 };
    [TJNetworkTool requestWithUrl:@"Task.GetTask" parameters:parameters success:^(id data) {
        
        if ([data[@"code"] intValue] == 0) {
            [self.refreshFooter endRefreshing];
            self.pageIndex++;
            NSArray *arr = [XLTaskModel mj_objectArrayWithKeyValuesArray:data[@"info"]];
            [self.datas addObjectsFromArray:arr];
            [self.tableView reloadData];

        } else {
            if (self.pageIndex > 1) {
                [self.refreshFooter endRefreshingWithNoMoreData];
            }
        }
        
    } failure:^(id error) {
        [self.refreshFooter endRefreshing];
        
    }];
}


- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    return self.datas.count;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    XLTaskCell *cell = [tableView dequeueReusableCellWithIdentifier:taskCell forIndexPath:indexPath];
    cell.model = self.datas[indexPath.row];
    return  cell;
}

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath {

    [self loadUserInfoData];

    XLTaskModel *model = self.datas[indexPath.row];
    XLTaskCell *cell = [tableView cellForRowAtIndexPath:indexPath];
    if (model.status.intValue) return;
    if (!cell.isStart) return;

    if (![XLGlobalFunc globalFunc].bindingMill) {
        [self alertWithmessage:@"您还未绑定充电宝" leftButtonName:@"取消" rightButtonName:@"前往绑定" leftButtonBlock:nil rightButtonBlock:^{
            [self didBindingMillItem];
        }];
        return;
    }
    
    [self alertWithmessage:[NSString stringWithFormat:@"是否接受%@任务", model.task_name] leftButtonName:@"取消" rightButtonName:@"接受任务" leftButtonBlock:nil rightButtonBlock:^{
        [self loadUserAcceptTaskDataWithOrderNO:model.order_no addressId: model.tl_id];
    }];
    
}

- (void)loadUserAcceptTaskDataWithOrderNO:(NSNumber *)orderNO addressId:(NSString *)addressId {
    
    NSDictionary *parameters = @{@"orderNO": orderNO, @"tl_id": addressId};
    TJLog(@"Task.UserAcceptTask, parameters =  %@", parameters);
    [TJNetworkTool requestWithUrl:@"Task.UserAcceptTask" parameters:parameters success:^(id data) {
        
        [self showMessageAutoHide:data[@"msg"]];
        
        if ([data[@"code"] intValue] == 0) {
            [self loadNewData];
            dispatch_after(dispatch_time(DISPATCH_TIME_NOW, (int64_t)(1 * NSEC_PER_SEC)), dispatch_get_main_queue(), ^{
                [[NSNotificationCenter defaultCenter] postNotificationName:acceptTaskNotification object:nil userInfo:nil];
                self.tabBarController.selectedIndex = 0;
            });
        }
 
    } failure:^(id error) { }];
}

- (void)didBindingMillItem {
    
    [AVCaptureDevice requestAccessForMediaType:AVMediaTypeVideo completionHandler:^(BOOL granted) {
        dispatch_async(dispatch_get_main_queue(), ^{
            if (granted) {
                [self.navigationController pushViewControllerWithName:@"XLBindingMillController" title:nil animated:YES];
            }
        });
    }];
    
    AVAuthorizationStatus authStatus =  [AVCaptureDevice authorizationStatusForMediaType:AVMediaTypeVideo];
    if (authStatus == AVAuthorizationStatusRestricted || authStatus ==AVAuthorizationStatusDenied) {
        [self alertWithTitle:@"" message:@"现在不能使用相机扫码绑定充电宝，前往开启？" leftButtonName:@"取消" rightButtonName:@"前往开启" leftButtonBlock:^{
        } rightButtonBlock:^{
            [[UIApplication sharedApplication] openURL:[NSURL URLWithString:UIApplicationOpenSettingsURLString]];
        }];
        return;
    }
}

- (void)loadUserInfoData {
    if (![USERDEFAULTS boolForKey:login]) return;
    [TJNetworkTool requestWithUrl:@"User.Info" parameters:nil success:^(id data) {
        if ([data[@"code"] intValue] == 0) {
           NSDictionary *all_status = data[@"info"][@"all_status"];
           [XLGlobalFunc globalFunc].bindingMill = [all_status[@"Charge"] intValue];
         }
    } failure:^(id error) {}];
    
}

- (void)applicationWillEnterForeground {
    if ([self openLocation] && self.pageLoadingView.showType == XLShowTypelocationError) {
        self.pageLoadingView.showType = XLShowTypeLoading;
    }
    [self loadNewData];
}

- (void)viewWillAppear:(BOOL)animated {
    [super viewWillAppear:animated];
    [self loadUserInfoData];
}

- (void)viewWillDisappear:(BOOL)animated {
    [super viewWillDisappear:animated];
    self.hidesBottomBarWhenPushed = NO;
}

@end
