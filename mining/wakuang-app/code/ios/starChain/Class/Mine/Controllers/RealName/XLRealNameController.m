//
//  XLRealNameController.m
//  starChain
//
//  Created by rlx on 2018/6/15.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import "XLRealNameController.h"
#import "XLReadNameCell.h"
#import "XLRealNameModel.h"
#import <AVFoundation/AVCaptureDevice.h>
#import <AVFoundation/AVMediaFormat.h>
#import "XLPageLoadingView.h"
#import "XLNoPublicController.h"

@interface XLRealNameController ()

@property (strong, nonatomic) NSArray *datas;
@property (strong, nonatomic) NSArray *models;
@property (strong, nonatomic) NSDictionary *controllorNameDict;
@property (weak, nonatomic) XLPageLoadingView *pageLoadingView;

@end

static NSString * const readNameCell = @"readNameCell";

@implementation XLRealNameController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    [self addSubView];
    [self initBornDatas];
}

- (void)loadData {
    [TJNetworkTool requestWithUrl:@"User.GetStarModuleList" parameters:nil success:^(id data) {
        [self.pageLoadingView stopAnimation];
        if ([data[@"code"] intValue] == 0 && data[@"info"]) {
            self.models = [XLRealNameModel mj_objectArrayWithKeyValuesArray:data[@"info"]];
            [self.collctionView reloadData];
        }
    } failure:^(id error) {
        self.pageLoadingView.showType = XLShowTypeLoadError;
    }];
}

- (void)addSubView {
    UICollectionViewFlowLayout *flowLayout = (UICollectionViewFlowLayout *)self.collctionView.collectionViewLayout;
    flowLayout.minimumInteritemSpacing = 1;
    flowLayout.minimumLineSpacing = 10;
    flowLayout.itemSize = CGSizeMake((KScreenWidth - flowLayout.minimumInteritemSpacing * 2) / 3, 130);
    flowLayout.sectionInset = UIEdgeInsetsMake(10, 0, 0, 0);
    self.collctionView.backgroundColor = [UIColor tj_backgroundColor];
    [self.collctionView registerClass:[XLReadNameCell class] forCellWithReuseIdentifier:readNameCell];
    [self.collctionView makeConstraints:^(MASConstraintMaker *make) {
        make.left.right.bottom.offset(0);
        make.top.equalTo(self.tj_navigationBar.bottom);
    }];
    
    XLPageLoadingView *pageLoadingView = [XLPageLoadingView loadingAnimationWithSuperView:self.view];
    self.pageLoadingView = pageLoadingView;
    pageLoadingView.showType = XLShowTypeLoading;
    
}

- (NSInteger)collectionView:(UICollectionView *)collectionView numberOfItemsInSection:(NSInteger)section {
    return self.datas.count;
}

- (UICollectionViewCell *)collectionView:(UICollectionView *)collectionView cellForItemAtIndexPath:(NSIndexPath *)indexPath {
    XLReadNameCell *cell = [collectionView dequeueReusableCellWithReuseIdentifier:readNameCell forIndexPath:indexPath];
    cell.models = self.models;
    cell.dict = self.datas[indexPath.row];
    return  cell;
}

- (void)collectionView:(UICollectionView *)collectionView didSelectItemAtIndexPath:(NSIndexPath *)indexPath {
     NSDictionary *dict = self.datas[indexPath.row];
     XLReadNameCell *cell = (XLReadNameCell *)[collectionView cellForItemAtIndexPath:indexPath];
    
    if (cell.state) {
        [self showMessageAutoHide:@"已经完成认证"]; return;
    }
    
    TJLog(@"dict = %@", dict);
 
   if ([dict[@"type_code"] isEqualToString:@"Face"]){
        [AVCaptureDevice requestAccessForMediaType:AVMediaTypeVideo completionHandler:^(BOOL granted) {
            dispatch_async(dispatch_get_main_queue(), ^{
                if (granted) {
                    [self.navigationController pushViewControllerWithName:dict[@"controllerName"] title:dict[@"type_name"] animated:YES];
                } else {
                    [self alertWithTitle:@"" message:@"现在不能使用相机扫人脸识别，前往开启？" leftButtonName:@"取消" rightButtonName:@"前往开启" leftButtonBlock:^{
                    } rightButtonBlock:^{
                        [[UIApplication sharedApplication] openURL:[NSURL URLWithString:UIApplicationOpenSettingsURLString]];
                    }];
                }
            });
        }];
 
    }  else if ([dict[@"controllerName"] isEqualToString:@"XLNoPublicController"]) {
        XLNoPublicController *noPublicController = [[XLNoPublicController alloc] init];
        noPublicController.rewardCount = cell.starCount;
        noPublicController.title = dict[@"text"];
        [self.navigationController pushViewController:noPublicController animated:YES];
    }  else {
        [self.navigationController pushViewControllerWithName:dict[@"controllerName"] title:dict[@"text"] animated:YES];
    }
}

- (NSArray *)datas {
    _datas = @[
               @{
                   @"text": @"实名认证",
                   @"icon": @"icon_Realname",
                   @"controllerName": @"XLNameIDController",
                   @"type_code": @"Auth",
                   },
               @{
                   @"text": @"绑定银行卡",
                   @"icon": @"bankCard",
                   @"controllerName": @"XLTiedCardController",
                   @"type_code": @"BindBank",
                   },
               @{
                   @"text": @"人脸识别",
                   @"icon": @"Face",
                   @"controllerName": @"XLFaceIdentifyController",
                   @"type_code": @"Face",
                   },
               @{
                   @"text": @"生物数据",
                   @"icon": @"gene",
                   @"controllerName": @"XLGeneticDataController",
                   @"type_code": @"Gene",
                   },
               @{
                   @"text": @"关注公众号",
                   @"icon": @"microPublic",
                   @"controllerName": @"XLNoPublicController",
                   @"type_code": @"Wechat",
                   },
               ];
    return _datas;
}

- (void)initBornDatas {
    
    int currentYear = (int)[NSDate date].year;
    int currentMonth = (int)[NSDate date].month;
    int currentDay = (int)[NSDate date].day;
    
    TJLog(@"currentYear = %d", currentYear);
    TJLog(@"currentMonth = %d", currentMonth);
    TJLog(@"currentDay = %d", currentDay);
    
    NSMutableArray *datas = [NSMutableArray array];
    CGFloat addYear = currentYear - 1900;
    
    dispatch_async(dispatch_get_global_queue(0, 0), ^{
        
        for (int i = currentYear - addYear; i <= currentYear; i++) {
            
            NSMutableDictionary *yearDict = [NSMutableDictionary dictionary];
            NSString *yearString = [NSString stringWithFormat:@"%d年", i];
            NSMutableArray *monthDatas = [NSMutableArray arrayWithCapacity:12];
            yearDict[@"year"] = yearString;
            yearDict[@"items"] = monthDatas;
            [datas addObject:yearDict];
            
            for (int j = 1; j <= 12; j++) {
                
                
                if (!(i >= currentYear && j > currentMonth)) {
                    
                    NSMutableDictionary *monthDict = [NSMutableDictionary dictionary];
                    NSMutableArray *dayDatas = [NSMutableArray array];
                    
                    NSString *monthString = [NSString stringWithFormat:@"%d月", j];
                    monthDict[@"month"] = monthString;
                    monthDict[@"items"] = dayDatas;
                    [monthDatas addObject:monthDict];
                    
                    NSInteger dayCount = [NSDate getDaysInYear2:i month:j];
                    
                    if (i == currentYear && j == currentMonth) {
                        
                        for (int k = 1; k <= currentDay; k++) {
                            
                            NSString *dayString = [NSString stringWithFormat:@"%d日", k];
                            [dayDatas addObject:dayString];
                        }
                    } else {
                        for (int k = 1; k <= dayCount; k++) {
                            
                            NSString *dayString = [NSString stringWithFormat:@"%d日", k];
                            [dayDatas addObject:dayString];
                        }
                    }
                    
                    [XLGlobalFunc globalFunc].bornDatas = datas.copy;
                }
            }
        }
        
    });
    
}

- (void)viewDidAppear:(BOOL)animated {
    [super viewDidAppear:animated];
    [self loadData];
}

@end
