//
//  XLRealNameController.m
//  starChain
//
//  Created by rlx on 2018/6/15.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import "XLRealNameController.h"
#import "XLReadNameCell.h"

@interface XLRealNameController ()

@property (strong, nonatomic) NSArray *datas;

@end

static NSString * const readNameCell = @"readNameCell";

@implementation XLRealNameController

- (void)viewDidLoad {
    [super viewDidLoad];
    
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
    
    
}

- (NSInteger)collectionView:(UICollectionView *)collectionView numberOfItemsInSection:(NSInteger)section {
    return self.datas.count;
}

- (UICollectionViewCell *)collectionView:(UICollectionView *)collectionView cellForItemAtIndexPath:(NSIndexPath *)indexPath {
    XLReadNameCell *cell = [collectionView dequeueReusableCellWithReuseIdentifier:readNameCell forIndexPath:indexPath];
    cell.dict = self.datas[indexPath.row];
    return  cell;
}

- (void)collectionView:(UICollectionView *)collectionView didSelectItemAtIndexPath:(NSIndexPath *)indexPath {
    NSDictionary *dict = self.datas[indexPath.row];
    [self.navigationController pushViewControllerWithName:dict[@"controllerName"] title:dict[@"text"] animated:YES];
}

- (NSArray *)datas {
    if (!_datas) {
        _datas = @[
                   @{
                       @"icon": @"icon_num",
                       @"text": @"姓名身份证",
                       @"starCount": @"+10星星",
                       @"controllerName": @"XLNameIDController",
                       },
                   @{
                       @"icon": @"bankCard",
                       @"text": @"绑定银行卡",
                       @"starCount": @"+10星星",
                       @"controllerName": @"XLTiedCardController",
                       },
                   @{
                       @"icon": @"Voice",
                       @"text": @"声音识别",
                       @"starCount": @"+10星星",
                       @"controllerName": @"",
                       },
                   @{
                       @"icon": @"Face",
                       @"text": @"人脸识别",
                       @"starCount": @"+10星星",
                       @"controllerName": @"",
                       },
                   @{
                       @"icon": @"gene",
                       @"text": @"基因数据",
                       @"starCount": @"+10星星",
                       @"controllerName": @"",
                       },
                   @{
                       @"icon": @"address",
                       @"text": @"送货地址",
                       @"starCount": @"+10星星",
                       @"controllerName": @"XLShippingAddressController",
                       },
                   @{
                       @"icon": @"microPublic",
                       @"text": @"关注公众号",
                       @"starCount": @"+10星星",
                       @"controllerName": @"XLNoPublicController",
                       }
                   ];
    }
    return _datas;
}

@end
