//
//  XLModifyPasswordController.m
//  starChain
//
//  Created by rlx on 2018/6/11.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import "XLModifyPasswordController.h"
#import "XLLineView.h"
#import "XLButton.h"

@interface XLModifyPasswordController ()<UITextFieldDelegate>

@property (weak, nonatomic) UITextField *oldPasswordTextField;
@property (weak, nonatomic) UITextField *passwordTextField;
@property (weak, nonatomic) UITextField *confirmPasswordTextField;

@end

@implementation XLModifyPasswordController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    self.tj_navigationItem.title = @"修改登录密码";
    [self addSubView];
}

- (void)addSubView {
    XLLineView *oldPasswordContetView = [[XLLineView alloc] initWithTopLine:NO bottomLine:YES];
    [self.view addSubview:oldPasswordContetView];
    [oldPasswordContetView makeConstraints:^(MASConstraintMaker *make) {
        make.left.right.offset(0);
        make.top.equalTo(self.tj_navigationBar.bottom).offset(10);
        make.height.offset(44);
    }];
    
    XLLineView *newPasswordContetView = [[XLLineView alloc] initWithTopLine:NO bottomLine:YES];
    [self.view addSubview:newPasswordContetView];
    [newPasswordContetView makeConstraints:^(MASConstraintMaker *make) {
        make.left.right.height.equalTo(oldPasswordContetView);
        make.top.equalTo(oldPasswordContetView.bottom);
    }];
    
    XLLineView *confirmPasswordContetView = [[XLLineView alloc] initWithTopLine:NO bottomLine:YES];
    [self.view addSubview:confirmPasswordContetView];
    [confirmPasswordContetView makeConstraints:^(MASConstraintMaker *make) {
        make.left.right.height.equalTo(oldPasswordContetView);
        make.top.equalTo(newPasswordContetView.bottom);
    }];
    
    UITextField *oldPasswordTextField = [self addTextFieldWithPlaceholder:@"请输入旧登录密码"];
    [oldPasswordContetView addSubview:oldPasswordTextField];
    [oldPasswordTextField makeConstraints:^(MASConstraintMaker *make) {
        make.centerY.offset(0);
        make.left.offset(14);
        make.height.offset(40);
        make.right.offset(-54);
    }];
    
    UITextField *newPasswordTextField = [self addTextFieldWithPlaceholder:@"请输入新登录密码"];
    [newPasswordContetView addSubview:newPasswordTextField];
    [newPasswordTextField makeConstraints:^(MASConstraintMaker *make) {
        make.centerY.offset(0);
        make.left.height.right.equalTo(oldPasswordTextField);
    }];
    
    UITextField *confirmPasswordTextField = [self addTextFieldWithPlaceholder:@"请输入确认新密码"];
    [confirmPasswordContetView addSubview:confirmPasswordTextField];
    [confirmPasswordTextField makeConstraints:^(MASConstraintMaker *make) {
        make.centerY.offset(0);
        make.left.height.right.equalTo(oldPasswordTextField);
    }];
    
    XLButton *commitButton = [XLButton buttonWithSuperView:self.view fontSize:15 color:[UIColor whiteColor] title:@"提交"];
    [commitButton setBackgroundImage:[UIImage imageNamed:@"gradientButtonBg"] forState:UIControlStateNormal];
    [commitButton shearRoundedCornersWithRadiu:ZOOM5(45) * 0.5];
    [commitButton addTarget:self action:@selector(didClickCommitButton) forControlEvents:UIControlEventTouchUpInside];
    [commitButton makeConstraints:^(MASConstraintMaker *make) {
        make.left.offset(14);
        make.right.offset(-14);
        make.height.offset(ZOOM5(45));
        make.top.equalTo(confirmPasswordContetView.bottom).offset(60);
    }];
    
    oldPasswordTextField.delegate = self;
    newPasswordTextField.delegate = self;
    confirmPasswordTextField.delegate = self;
    
    _oldPasswordTextField = oldPasswordTextField;
    _passwordTextField = newPasswordTextField;
    _confirmPasswordTextField = confirmPasswordTextField;
    
    self.oldPasswordTextField.secureTextEntry = YES;
    self.oldPasswordTextField.secureTextEntry = YES;
    self.confirmPasswordTextField.secureTextEntry = YES;

}

- (BOOL)textField:(UITextField *)textField shouldChangeCharactersInRange:(NSRange)range replacementString:(NSString *)string {
    NSInteger existedLength = textField.text.length;
    NSInteger selectedLength = range.length;
    NSInteger replaceLength = string.length;
    NSInteger currentLengh = existedLength - selectedLength + replaceLength;
    if ((textField == _oldPasswordTextField || textField == _passwordTextField || textField == _confirmPasswordTextField) && currentLengh > 16) {
        return NO;
    }  else {
        return YES;
    }
}

- (void)didClickCommitButton {
    
    NSString *oldPassword = [_oldPasswordTextField.text replacingEmptyString];
    NSString *password = _passwordTextField.text;
    NSString *confirmPassword = _confirmPasswordTextField.text;
    
    TJLog(@"oldPassword = %@,password = %@,confirmPassword = %@",oldPassword, password, confirmPassword);
    
    if (oldPassword.length == 0) {
        [self showMessageAutoHide:@"请输入旧密码"]; return;
    }
    if ((oldPassword.length < 6 || oldPassword.length > 16)) {
        [self showMessageAutoHide:@"请输入6-16位旧密码"]; return;
    }
    if (password.length == 0) {
        [self showMessageAutoHide:@"请输入新密码"]; return;
    }
    if ((password.length < 6 || password.length > 16)) {
        [self showMessageAutoHide:@"请输入6-16位新密码"]; return;
    }
    if ((confirmPassword.length == 0)) {
        [self showMessageAutoHide:@"请输入确认密码"]; return;
    }
    if ((confirmPassword.length < 6 || confirmPassword.length > 16)) {
        [self showMessageAutoHide:@"请输入6-16位确认密码"]; return;
    }
    if (![confirmPassword isEqualToString:password]) {
        [self showMessageAutoHide:@"新密码和确认密码不同"]; return;
    }
    
    [self.view endEditing:YES];
    
    NSDictionary *parameters = @{
                                 @"usedPwd": oldPassword,
                                 @"newPwd": password,
                                 @"chkNewPwd": confirmPassword
                                 };
    [TJNetworkTool requestWithUrl:@"User.ChangePwd" parameters:parameters success:^(id data) {
        [self showMessageAutoHide:data[@"msg"]];
        if (![data[@"code"] intValue]) {
            dispatch_after(dispatch_time(DISPATCH_TIME_NOW, (int64_t)(1 * NSEC_PER_SEC)), dispatch_get_main_queue(), ^{
                [self.navigationController popViewControllerAnimated:YES];
            });
        }
    } failure:^(id error) { }];
    
}

- (void)touchesBegan:(NSSet<UITouch *> *)touches withEvent:(UIEvent *)event {
    [self.view endEditing:YES];
}

- (UITextField *)addTextFieldWithPlaceholder:(NSString *)placeholder {
    UITextField *textField = [[UITextField alloc] init];
    textField.font = [UIFont systemFontOfSize:16];
    textField.secureTextEntry = YES;
    textField.clearButtonMode = UITextFieldViewModeWhileEditing;
    textField.attributedPlaceholder = [[NSAttributedString alloc] initWithString:placeholder attributes:@{NSForegroundColorAttributeName: UIColor_Hex(0xa0a0a0)}];
    return textField;
}

- (void)viewWillDisappear:(BOOL)animated {
    [super viewWillDisappear:animated];
    [self.view endEditing:YES];
}

@end
