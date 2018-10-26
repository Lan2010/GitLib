//
//  XLSetingController.m
//  starChain
//
//  Created by rlx on 2018/6/11.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import "XLSetingController.h"
#import "XLButton.h"

@interface XLSetingController ()

@property (strong, nonatomic) NSArray *datas;

@property (weak, nonatomic) XLButton *exitLoginButton;

@property (copy, nonatomic) NSString *appVersion;
@property (copy, nonatomic) NSString *phoneNumber;


@end

static NSString * const settingCell = @"settingCell";

@implementation XLSetingController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    self.tj_navigationItem.title = @"设置";
    self.appVersion = [[[NSBundle mainBundle] infoDictionary] objectForKey:@"CFBundleShortVersionString"];
    self.appVersion = [NSString stringWithFormat:@"V%@", self.appVersion];
    
    self.phoneNumber = [USERDEFAULTS objectForKey:phone];
    self.phoneNumber = (self.phoneNumber) ? [self.phoneNumber hiddenAmongPhone] : @"";
    
    self.tableView.rowHeight = 44;
    self.tableView.contentInset = UIEdgeInsetsMake(10, 0, 0, 0);
    [self.tableView makeConstraints:^(MASConstraintMaker *make) {
        make.top.equalTo(self.tj_navigationBar.bottom);
        make.left.right.bottom.offset(0);
    }];
    
    UIView *footerView = [[UIView alloc] initWithFrame:CGRectMake(0, 0, KScreenWidth, 105)];
    footerView.backgroundColor = [UIColor clearColor];
    self.tableView.tableFooterView = footerView;
    
    XLButton *exitLoginButton = [XLButton buttonWithSuperView:footerView fontSize:16 color:[UIColor whiteColor] title:@"退出登录"];
    [exitLoginButton setBackgroundImage:[UIImage imageNamed:@"gradientButtonBg"] forState:UIControlStateNormal];
    [exitLoginButton shearRoundedCornersWithRadiu:ZOOM5(45) * 0.5];
    [exitLoginButton addTarget:self action:@selector(didClickExitLoginButton) forControlEvents:UIControlEventTouchUpInside];
    [exitLoginButton makeConstraints:^(MASConstraintMaker *make) {
        make.left.offset(14);
        make.right.offset(-14);
        make.height.offset(xl_bottomButtonH);
        make.bottom.offset(0);
    }];
 
    self.exitLoginButton = exitLoginButton;
}

#pragma mark - Table view data source
- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    return self.datas.count;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:settingCell];
    if (!cell) {
        cell = [[UITableViewCell alloc] initWithStyle:UITableViewCellStyleValue1 reuseIdentifier:settingCell];
        cell.textLabel.font = [UIFont systemFontOfSize:16];
        cell.detailTextLabel.font = [UIFont systemFontOfSize:13];
        cell.textLabel.textColor = UIColor_Hex(0x303030);
        cell.detailTextLabel.textColor = UIColor_Hex(0xa0a0a0);
        
    }
    
    NSDictionary *dict = self.datas[indexPath.row];
    cell.textLabel.text = dict[@"text"];
    cell.detailTextLabel.text = dict[@"detailText"];
    cell.selectionStyle = UITableViewCellAccessoryDisclosureIndicator;
    cell.accessoryType = UITableViewCellAccessoryDisclosureIndicator;
    
    if (indexPath.row == 0 || indexPath.row == self.datas.count - 1) {
        cell.selectionStyle = UITableViewCellSelectionStyleNone;
        cell.accessoryType = UITableViewCellAccessoryNone;
    }
    
    return cell;
    
}

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath {
    [tableView deselectRowAtIndexPath:indexPath animated:YES];
    
    NSDictionary *dict = self.datas[indexPath.row];
    if (indexPath.row == 1 || indexPath.row == 2) {
        [self.navigationController pushViewControllerWithName:dict[@"controllersName"] title:dict[@"text"] animated:YES];
    }
 
}

- (void)didClickExitLoginButton {
    [self alertWithTitle:nil message:@"确定退出账号?" leftButtonName:@"取消" rightButtonName:@"确定" leftButtonBlock:nil rightButtonBlock:^{
        
        [TJNetworkTool requestWithUrl:@"User.LoginOut" parameters:nil success:^(id data) {} failure:^(id error) {}];
        
        REMOVOCOOKIES
        [TJKeychain tj_setObject:@"" forKey:token];
        [USERDEFAULTS setBool:NO forKey:login];
        [[NSNotificationCenter defaultCenter] postNotificationName:cancellationNotification object:nil];
        self.tabBarController.selectedIndex = 0;
        [self.navigationController popToRootViewControllerAnimated:YES];
        
    }];
 
}

- (NSArray *)datas {
    _datas = @[
               @{
                   @"text": @"手机号码",
                   @"controllersName": @"QHZLoginLogController",
                   @"detailText": self.phoneNumber
                   },
               @{
                   @"text": @"登录密码",
                   @"controllersName": @"XLModifyPasswordController",
                   @"detailText": @"修改",
                   },
               @{
                   @"text": @"意见反馈",
                   @"controllersName": @"XLFeedbackController"
                   },
               @{
                   @"text": @"版本信息",
                   @"detailText": self.appVersion
                   }
               ];
 
    return _datas;
}


@end
