//
//  XLStarRankingController.m
//  starChain
//
//  Created by rlx on 2018/7/7.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import "XLStarRankingController.h"
#import "XLStarRankingCell.h"
#import "XLCompleteTaskModel.h"
#import "XLRankingContentView.h"

@interface XLStarRankingController ()

@property (strong, nonatomic) NSArray *datas;
@property (assign, nonatomic) NSInteger pageIndex;
@property (strong, nonatomic) NSArray <XLRankingContentView *>*rankingContentViews;


@end

static NSString * const starRankingCell = @"starRankingCell";

@implementation XLStarRankingController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    [self.view addGradientLayerWithColors:@[UIColor_Hex(0x321B5D), UIColor_Hex(0xab55f9)] endPoint:CGPointMake(0, 1)];
    self.tj_navigationBar.tintColor = [UIColor whiteColor];
    self.titleColor = [UIColor whiteColor];
    self.navBarAlpha = YES;
    self.tj_navigationItem.title = @"星星排行榜";
    
    UIImage *firstRankingImage = [UIImage imageNamed:@"top1"];
    UIImageView *firstRankingImageView = [UIImageView tj_WithSuperView:self.view];
    firstRankingImageView.image = firstRankingImage;
    [firstRankingImageView makeConstraints:^(MASConstraintMaker *make) {
        make.centerX.offset(0);
        make.top.equalTo(self.tj_navigationBar.bottom).offset(12);
        make.width.height.offset(H(59));
    }];
    
    UIImage *secondRankingImage = [UIImage imageNamed:@"top2"];
    UIImageView *secondRankingImageView = [UIImageView tj_WithSuperView:self.view];
    secondRankingImageView.image = secondRankingImage;
    [secondRankingImageView makeConstraints:^(MASConstraintMaker *make) {
        make.left.offset(H(56));
        make.top.equalTo(firstRankingImageView).offset(H(21));
        make.width.offset(H(51));
        make.height.offset(H(53));
    }];
    
    UIImage *threeRankingImage = [UIImage imageNamed:@"top3"];
    UIImageView *threeRankingImageView = [UIImageView tj_WithSuperView:self.view];
    threeRankingImageView.image = threeRankingImage;
    [threeRankingImageView makeConstraints:^(MASConstraintMaker *make) {
        make.right.offset(H(-56));
        make.top.equalTo(secondRankingImageView);
        make.width.height.equalTo(secondRankingImageView);
    }];
    
    XLRankingContentView *firstRankingContentView = [XLRankingContentView tj_WithSuperView:self.view];
    firstRankingContentView.image = [UIImage imageNamed:@"bg-rank-c"];
     [firstRankingContentView makeConstraints:^(MASConstraintMaker *make) {
        make.centerX.offset(0);
        make.top.equalTo(firstRankingImageView.bottom).offset(H(16));
         make.width.offset(H(124));
         make.height.offset(H(65));
    }];

    XLRankingContentView *secondRankingContentView = [XLRankingContentView tj_WithSuperView:self.view];
    secondRankingContentView.image = [UIImage imageNamed:@"bg-rank-l"];
    [secondRankingContentView makeConstraints:^(MASConstraintMaker *make) {
        make.right.equalTo(firstRankingContentView.left);
        make.bottom.equalTo(firstRankingContentView);
        make.width.offset(H(101));
        make.height.offset(H(53));
    }];

    XLRankingContentView *threeRankingContentView = [XLRankingContentView tj_WithSuperView:self.view];
    threeRankingContentView.image = [UIImage imageNamed:@"bg-rank-r"];
    [threeRankingContentView makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(firstRankingContentView.right);
        make.bottom.equalTo(firstRankingContentView);
        make.width.height.equalTo(secondRankingContentView);
    }];
 
    self.tableView.rowHeight = 44;
    self.tableView.backgroundColor = [UIColor whiteColor];
    [self.tableView shearRoundedCornersWithRadiu:5];
    self.tableView.clipsToBounds = YES;
    [self.tableView registerClass:[XLStarRankingCell class] forCellReuseIdentifier:starRankingCell];
    [self.tableView makeConstraints:^(MASConstraintMaker *make) {
        make.left.offset(H(16));
        make.right.bottom.offset(H(-16));
        make.top.equalTo(threeRankingContentView.bottom);
    }];
    [self loadStarRankingData];
    
    self.rankingContentViews = @[firstRankingContentView, secondRankingContentView, threeRankingContentView];
    
}

- (void)loadStarRankingData {
    [TJNetworkTool requestWithUrl:@"Account.Ranking" parameters:nil success:^(id data) {
        if ([data[@"code"] intValue] == 0) {
            NSArray *array = [XLStarRankingModel mj_objectArrayWithKeyValuesArray:data[@"info"]];
            NSMutableArray *Marr = [NSMutableArray arrayWithCapacity:3];
            NSMutableArray *dataMarr = [NSMutableArray array];
            [array enumerateObjectsUsingBlock:^(id  _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
                if (idx < 3) {
                    [Marr addObject:obj];
                } else {
                    [dataMarr addObject:obj];
                }
            }];
            
            [Marr enumerateObjectsUsingBlock:^(id  _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
                self.rankingContentViews[idx].model = obj;
            }];
            
            self.datas = dataMarr.copy;
            [self.tableView reloadData];
        }
    } failure:^(id error) {}];
}


- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    return self.datas.count;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    XLStarRankingCell *cell = [tableView dequeueReusableCellWithIdentifier:starRankingCell forIndexPath:indexPath];
    cell.model = self.datas[indexPath.row];;
    return  cell;
}

- (UIStatusBarStyle)preferredStatusBarStyle {
    return UIStatusBarStyleLightContent;
}


@end
