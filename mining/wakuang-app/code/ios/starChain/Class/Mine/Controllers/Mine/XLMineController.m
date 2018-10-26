//
//  XLMineController.m
//  starChain
//
//  Created by rlx on 2018/6/6.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import "XLMineController.h"
#import "XLMineHeaderView.h"
#import "XLShareController.h"
#import "XLMineCell.h"
#import "XLLoginController.h"
#import "XLInvitationController.h"
#import <AVFoundation/AVCaptureDevice.h>
#import <AVFoundation/AVMediaFormat.h>
#import "XLAlertViewController.h"

@interface XLMineController ()

@property (weak, nonatomic) XLMineHeaderView *headerView;

@property (strong, nonatomic) NSArray *datas;
@property (strong, nonatomic) NSDictionary *invitationDict;

@property (copy, nonatomic) NSString *authDetailText;
@property (copy, nonatomic) NSString *geneDetailText;
@property (copy, nonatomic) NSString *InvitationDetailText;
@property (copy, nonatomic) NSString *millDetailText;

@property (assign, nonatomic) BOOL bindingMill;
@property (assign, nonatomic) int rewardCount;

@end

static NSString * const mineCell= @"mineCell";

@implementation XLMineController


- (void)viewDidLoad {
    [super viewDidLoad];
    
    [self listeningNotification];
    
    self.tj_navigationItem.title = @"我的";
    self.tj_navigationBar.tintColor = [UIColor whiteColor];
    self.titleColor = [UIColor whiteColor];
    self.navBarAlpha = YES;
    self.tj_navigationItem.rightBarButtonItem = [[UIBarButtonItem alloc] initWithImage:[UIImage imageNamed:@"icon_Setup"] style:UIBarButtonItemStylePlain target:self action:@selector(didClickSetingItem)];
    self.tableView.separatorStyle = UITableViewCellSeparatorStyleNone;
    [self.tableView registerClass:[XLMineCell class] forCellReuseIdentifier:mineCell];
    [self.tableView makeConstraints:^(MASConstraintMaker *make) {
        make.top.left.right.offset(0);
        make.bottom.offset(-TabbarSafeBottomMargin);
    }];
    
    [self.view bringSubviewToFront:self.tj_navigationBar];
    
    XLMineHeaderView *headerView = [[XLMineHeaderView alloc] initWithFrame:CGRectMake(0, 0, KScreenWidth, H(280) + StatusBarHight - 20)];
    self.headerView = headerView;
    self.tableView.tableHeaderView = headerView;
    [headerView setTapPhoneNumberLableBlock:^{
        if (![USERDEFAULTS boolForKey:login]) {
            [self login]; return;
        }
    }];
    
    [headerView setTapInvitationImageViewBlock:^{
        if (![USERDEFAULTS boolForKey:login]) {
            [self login]; return;
        }
        [self.navigationController pushViewControllerWithName:@"XLAddressBookController" title:nil animated:YES];
    }];
    
    UIView *footView = [[UIView alloc] initWithFrame:CGRectMake(0, 0, KScreenWidth, 30)];
    UILabel *serviceLable = [UILabel lableWithSuperView:footView fontSize:12 color:UIColor_Hex(0xa0a0a0) title:@"用户服务协议" textAlignment:NSTextAlignmentLeft];
    [serviceLable addTapGesturesWithTarget:self action:@selector(tapServiceLable)];
    [serviceLable makeConstraints:^(MASConstraintMaker *make) {
        make.top.offset(-3);
        make.top.centerX.offset(0);
    }];
    
    self.tableView.tableFooterView = footView;
    
}

- (void)loadUserGetUserCenterStarData {
    [TJNetworkTool requestWithUrl:@"User.GetUserCenterStar" parameters:nil success:^(id data) {
        if ([data[@"code"] intValue] == 0 && data[@"info"]) {
            if (![data[@"info"] isKindOfClass:[NSArray class]]) return ;
            
            [data[@"info"] enumerateObjectsUsingBlock:^(NSDictionary *obj, NSUInteger idx, BOOL * _Nonnull stop) {
                NSString *detailText = ([obj[@"status"] intValue] != 1) ? [NSString stringWithFormat:@"送%d星星", [obj[@"value"] intValue]] : @"";
                if ([obj[@"type"] isEqualToString:@"AuthAll"]) {//实名认证
                    self.authDetailText = detailText;
                } else if ([obj[@"type"] isEqualToString:@"Gene"]) {//数字生命
                    self.geneDetailText =  detailText;
                } else if ([obj[@"type"] isEqualToString:@"Invitation"]) {//邀请好友
                    self.InvitationDetailText =  detailText;
                    self.rewardCount =  [obj[@"value"] intValue];
                }
            }];
            [self.tableView reloadData];
        }
    } failure:^(id error) {}];
}

- (void)login {
  
     [self presentViewControllerWithName:@"XLLoginController" title:nil];
}

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath {
    return self.datas.count * 50 + 32;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    return 1;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    
    XLMineCell *cell = [tableView dequeueReusableCellWithIdentifier:mineCell forIndexPath:indexPath];
    cell.datas = self.datas;
    [cell setDidClickRow:^(NSInteger index) {
        
        NSDictionary *dict = self.datas[index];
        if ([dict[@"login"] intValue] && ![USERDEFAULTS boolForKey:login]) {
            [self login]; return;
        }
        
        NSString *controllerName = dict[@"controllerName"];
        if ([controllerName isEqualToString:@"XLInvitationController"]) {
            XLInvitationController *invitationController = [[XLInvitationController alloc] init];
            invitationController.dataDict = self.invitationDict;
            invitationController.rewardCount = self.rewardCount;
            [self.navigationController pushViewController:invitationController animated:YES];
        } else if (dict[@"url"]) {
            [self loadInteractionWebPageWithUrlString:dict[@"url"] title:dict[@"text"]];
        } else if ([controllerName isEqualToString:@"XLBindingMillController"]) {
            [self didBindingMillItem];
        } else if (controllerName.length) {
            [self.navigationController pushViewControllerWithName:controllerName title:dict[@"text"] animated:YES];
        }
        
    }];
    
    return cell;
}

- (void)didClickSetingItem {
    
    if (![USERDEFAULTS boolForKey:login]) {
        [self login]; return;
    }
    [self.navigationController pushViewControllerWithName:@"XLSetingController" title:nil animated:YES];
}

- (void)tapServiceLable {
    [self loadInteractionWebPageWithUrlString:[USERDEFAULTS objectForKey:@"RegisterUrl"] title:@"用户服务协议"];
}

- (void)scrollViewDidScroll:(UIScrollView *)scrollView {
    CGFloat offsetY = scrollView.contentOffset.y;
    if (offsetY < 0) {
        offsetY = 0;
        [scrollView setContentOffset:CGPointMake(scrollView.contentOffset.x, offsetY)];
    }
}

- (UIStatusBarStyle)preferredStatusBarStyle {
    return UIStatusBarStyleLightContent;
}


- (void)didBindingMillItem {
    
    if (self.bindingMill) return;
    
    [AVCaptureDevice requestAccessForMediaType:AVMediaTypeVideo completionHandler:^(BOOL granted) {
        dispatch_async(dispatch_get_main_queue(), ^{
            if (granted) {
                [self.navigationController pushViewControllerWithName:@"XLBindingMillController" title:nil animated:YES];
            } else {
                [self alertWithTitle:@"" message:@"现在不能使用相机扫码绑定充电宝，前往开启？" leftButtonName:@"取消" rightButtonName:@"前往开启" leftButtonBlock:^{
                } rightButtonBlock:^{
                    [[UIApplication sharedApplication] openURL:[NSURL URLWithString:UIApplicationOpenSettingsURLString]];
                }];
                return ;
            }
        });
    }];
}

- (void)loadHeaderData {
    NSString *phoneNumber = [USERDEFAULTS objectForKey:phone];
    phoneNumber = [USERDEFAULTS boolForKey:login] ? [phoneNumber hiddenAmongPhone] : @"登录/注册";
    self.headerView.phoneNumberLable.text = phoneNumber;
}

- (void)loadUserInfoData {
    [TJNetworkTool requestWithUrl:@"User.Info" parameters:nil success:^(id data) {
        if ([data[@"code"] intValue] == 0) {
            NSDictionary *all_status = data[@"info"][@"all_status"];
            self.bindingMill = [all_status[@"Charge"] intValue];
            self.millDetailText = all_status[@"charge_mac"];
            [XLGlobalFunc globalFunc].bindingMill = self.bindingMill;
            [XLGlobalFunc globalFunc].updateAddressBook = [all_status[@"Maillist"] intValue];
            [self.tableView reloadData];
        }
    } failure:^(id error) {}];
    
}

- (void)loadInvitationData {
    [TJNetworkTool requestWithUrl:@"User.GetInvitation" parameters:nil success:^(id data) {
        if ([data[@"code"] intValue] == 0) {
            self.invitationDict = data[@"info"];
        }
    } failure:^(id error) {}];
}

- (void)loadNewData {
    [self loadHeaderData];
    if ([USERDEFAULTS boolForKey:login]) {
        [self loadUserInfoData];
        [self loadInvitationData];
        [self loadUserGetUserCenterStarData];
    } else {
        [self cancellation];
    }
}

#pragma mark - 退出登录
- (void)cancellation {
    _InvitationDetailText = _millDetailText = _authDetailText = _geneDetailText = nil;
    [self.tableView reloadData];
}

- (void)listeningNotification {
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(login) name:tokenErrornNedLoginNotification object:nil];
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(loadNewData) name:loginSucceedNotification object:nil];
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(cancellation) name:cancellationNotification object:nil];
}

- (void)dealloc {
    [[NSNotificationCenter defaultCenter] removeObserver:self name:loginSucceedNotification object:nil];
    [[NSNotificationCenter defaultCenter] removeObserver:self name:tokenErrornNedLoginNotification object:nil];
    [[NSNotificationCenter defaultCenter] removeObserver:self name:cancellationNotification object:nil];
}

- (void)viewWillAppear:(BOOL)animated {
    [super viewWillAppear:animated];
    [self loadNewData];
}

- (void)viewWillDisappear:(BOOL)animated {
    [super viewWillDisappear:animated];
    self.hidesBottomBarWhenPushed = NO;
}

- (NSArray *)datas {
    _datas = @[
               @{
                   @"text": @"邀请好友",
                   @"icon": @"icon_friend",
                   @"detailText": (_InvitationDetailText) ? _InvitationDetailText : @"",
                   @"controllerName": @"XLInvitationController",
                   @"login": @"1",
                   },
               @{
                   @"text": @"我的充电宝",
                   @"icon": @"icon_Portablebattery",
                   @"detailText": ([USERDEFAULTS boolForKey:login] ? (!_bindingMill) ? @"未绑定" : ((_millDetailText) ? _millDetailText : @"") : @""),
                   @"controllerName": @"XLBindingMillController",
                   @"login": @"1",
                   @"arrow": ([USERDEFAULTS boolForKey:login] ? ((!_bindingMill) ? @1 : @0) : @1)
                   },
               @{
                   @"text": @"已完成任务",
                   @"icon": @"icon_task",
                   @"controllerName": @"XLCompleteTaskController",
                   @"login": @"1"
                   },
               @{
                   @"text": @"实名奖励",
                   @"icon": @"icon_Realname",
                   @"detailText": (_authDetailText) ? _authDetailText : @"",
                   @"controllerName": @"XLRealNameController",
                   @"login": @"1",
                   },
               @{
                   @"text": @"数字生命",
                   @"icon": @"icon_num",
                   @"detailText": (_geneDetailText) ? _geneDetailText : @"",
                   @"controllerName": @"XLDigitalLifeController",
                   @"login": @"1",
                   },
               @{
                   @"text": @"关于我们",
                   @"icon": @"icon_aboutme",
                   @"controllerName": @"XLAboutWeController",
                   //                       @"url": ([USERDEFAULTS objectForKey:@"AboutUrl"] ? [USERDEFAULTS objectForKey:@"AboutUrl"] : @""),
                   @"login": @"0"
                   },
               ];
    
    return _datas;
}


@end
