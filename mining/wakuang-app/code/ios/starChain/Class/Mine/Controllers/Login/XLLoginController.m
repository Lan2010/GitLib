//
//  XLLoginController.m
//  starChain
//
//  Created by rlx on 2018/6/11.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import "XLLoginController.h"
#import "XLTextFieldView.h"
#import "XLButton.h"
#import "TJCountdown.h"
#import "XLPhoneNumberformat.h"
#import "XLAddressModel.h"

@interface XLLoginController ()<UITextFieldDelegate>

@property (weak, nonatomic) UITextField *phoneNumberTextField;
@property (weak, nonatomic) UITextField *passwordTextField;
@property (weak, nonatomic) UITextField *SMSTextField;
@property (weak, nonatomic) XLTextFieldView *SMSTextFieldView;
@property (weak, nonatomic) XLTextFieldView *passwordTextFieldView;
@property (weak, nonatomic) UILabel *SMSValidationLable;
@property (assign, nonatomic) BOOL isPasswordLogin;
@property (copy, nonatomic) NSString *phoneNumber;
@property (weak, nonatomic) MBProgressHUD *hud;

@end

@implementation XLLoginController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    self.tj_navigationItem.title = @"登录";
    self.tj_navigationBar.tintColor = [UIColor whiteColor];
    self.titleColor = [UIColor whiteColor];
    self.navBarAlpha = YES;
    self.isPasswordLogin = YES;
    self.showStyle = ControllerShowStyleModal;
    
    UIImageView *contentImageView = [[UIImageView alloc] init];
    contentImageView.userInteractionEnabled = YES;
    [self.view insertSubview:contentImageView atIndex:0];
    contentImageView.image = [UIImage imageNamed:@"loginBackground"];
    [contentImageView makeConstraints:^(MASConstraintMaker *make) {make.edges.offset(0);}];

    XLTextFieldView *phoneTextFieldView = [[XLTextFieldView alloc] initWithIconName:@"iphone_icon" placeHolder:@"请输入您的手机号码"];
    [contentImageView addSubview:phoneTextFieldView];
    [phoneTextFieldView makeConstraints:^(MASConstraintMaker *make) {
        make.left.offset(18);
        make.right.offset(-18);
        make.height.offset(ZOOM5(45));
        make.top.offset(ZOOM5(180));
    }];
    
    XLTextFieldView *passwordTextFieldView = [[XLTextFieldView alloc] initWithIconName:@"icon_safe" placeHolder:@"请输入6-16位密码"];
    [contentImageView addSubview:passwordTextFieldView];
    passwordTextFieldView.rightButton.hidden = NO;
    [passwordTextFieldView makeConstraints:^(MASConstraintMaker *make) {
        make.left.right.height.equalTo(phoneTextFieldView);
        make.top.equalTo(phoneTextFieldView.bottom).offset(H(10));
    }];
    
    XLTextFieldView *SMSTextFieldView = [[XLTextFieldView alloc] initWithIconName:@"icon_safe" placeHolder:@"请输入短信验证码"];
    [contentImageView addSubview:SMSTextFieldView];
    SMSTextFieldView.hidden = YES;
    [SMSTextFieldView makeConstraints:^(MASConstraintMaker *make) {
        make.left.right.height.equalTo(phoneTextFieldView);
        make.top.equalTo(phoneTextFieldView.bottom).offset(H(10));
    }];
    
    UIButton *getCodeButton = [UIButton buttonWithSuperView:SMSTextFieldView fontSize:14 color:[UIColor whiteColor] title:@"获取验证码"];
    [getCodeButton addTarget:self action:@selector(startTime:) forControlEvents:UIControlEventTouchUpInside];
    [getCodeButton makeConstraints:^(MASConstraintMaker *make) {
        make.right.offset(-12);
        make.centerY.offset(0);
        make.width.offset(80);
        make.height.offset(35);
    }];
    
    XLButton *loginButton = [XLButton buttonWithSuperView:contentImageView fontSize:16 color:[UIColor whiteColor] title:@"登录"];
    [loginButton setBackgroundImage:[UIImage imageNamed:@"gradientButtonBg"] forState:UIControlStateNormal];
    [loginButton shearRoundedCornersWithRadiu:ZOOM5(45) * 0.5];
    [loginButton addTarget:self action:@selector(didClickLoginButton) forControlEvents:UIControlEventTouchUpInside];
    [loginButton makeConstraints:^(MASConstraintMaker *make) {
        make.top.equalTo(passwordTextFieldView.bottom).offset(30);
        make.left.right.equalTo(passwordTextFieldView);
        make.height.offset(xl_bottomButtonH);
    }];
    
    UILabel *SMSValidationLable = [UILabel lableWithSuperView:self.view fontSize:14 color:[UIColor whiteColor] title:@"用短信验证码登录" textAlignment:NSTextAlignmentCenter];
    [SMSValidationLable addTapGesturesWithTarget:self action:@selector(tapSMSValidationLable)];
    [SMSValidationLable makeConstraints:^(MASConstraintMaker *make) {
        make.centerX.offset(0);
        make.top.equalTo(loginButton.bottom).offset(20);
    }];
    
    UIView *separatedView = [UIView tj_WithSuperView:contentImageView];
    separatedView.backgroundColor = [UIColor whiteColor];
    [separatedView makeConstraints:^(MASConstraintMaker *make) {
        make.bottom.offset(-30 - TabbarSafeBottomMargin);
        make.width.offset(1);
        make.height.offset(15);
        make.centerX.offset(0);
    }];
    
    UILabel *registered = [UILabel lableWithSuperView:contentImageView fontSize:14 color:[UIColor whiteColor] title:@"立即注册" textAlignment:NSTextAlignmentLeft];
    [registered addTapGesturesWithTarget:self action:@selector(tapRegistered)];
    [registered makeConstraints:^(MASConstraintMaker *make) {
        make.centerY.equalTo(separatedView);
        make.right.equalTo(separatedView.left).offset(-8);
        make.bottom.offset(-21);
    }];
    
    UILabel *forgotPassword = [UILabel lableWithSuperView:contentImageView fontSize:14 color:[UIColor whiteColor] title:@"忘记密码" textAlignment:NSTextAlignmentLeft];
    [forgotPassword addTapGesturesWithTarget:self action:@selector(tapForgotPassword)];
    [forgotPassword makeConstraints:^(MASConstraintMaker *make) {
        make.centerY.equalTo(separatedView);
        make.left.equalTo(separatedView.right).offset(8);
        make.bottom.equalTo(registered);
    }];
    
    self.passwordTextFieldView = passwordTextFieldView;
    self.SMSTextFieldView = SMSTextFieldView;
    self.phoneNumberTextField = phoneTextFieldView.textField;
    self.passwordTextField = passwordTextFieldView.textField;
    self.SMSTextField = SMSTextFieldView.textField;
    self.SMSValidationLable = SMSValidationLable;
    
    self.phoneNumberTextField.delegate = self;
    self.passwordTextField.delegate = self;
    self.SMSTextField.delegate = self;
    
    self.phoneNumberTextField.clearButtonMode = UITextFieldViewModeWhileEditing;
    self.passwordTextField.clearButtonMode = UITextFieldViewModeWhileEditing;
    
    self.passwordTextField.secureTextEntry = YES;
    
    self.phoneNumberTextField.keyboardType = UIKeyboardTypeNumberPad;
    self.SMSTextField.keyboardType = UIKeyboardTypeNumberPad;


    
    NSString *boxPhone= [USERDEFAULTS objectForKey:phone];
    if (boxPhone) {
        NSMutableString *Mstr = [[NSMutableString alloc] initWithString:boxPhone];
        if (Mstr.length == 11) {
            [Mstr insertString:@" " atIndex:3];
            [Mstr insertString:@" " atIndex:8];
            self.phoneNumberTextField.text = (Mstr) ? Mstr : @"";
        }
    }
    
    self.passwordTextField.returnKeyType = UIReturnKeyDone;
}

- (BOOL)textField:(UITextField *)textField shouldChangeCharactersInRange:(NSRange)range replacementString:(NSString *)string {
    NSInteger existedLength = textField.text.length;
    NSInteger selectedLength = range.length;
    NSInteger replaceLength = string.length;
    NSInteger currentLengh = existedLength - selectedLength + replaceLength;
    if (textField == _phoneNumberTextField) {
        return [XLPhoneNumberformat phoneNumberformatWithTextField:textField shouldChangeCharactersInRange:range replacementString:string formatType:XLFormatTypePhoneNumber];
    }  else if (string.length == 0) {
        return YES;
    } else if (textField == _passwordTextField && currentLengh > 16) {
        return NO;
    } else if (textField == _SMSTextField && currentLengh > 6) {
        return NO;
    } else {
        return YES;
    }
}
 
- (BOOL)textFieldShouldReturn:(UITextField *)textField {
    [self.view endEditing:YES];
    return YES;
}

- (void)tapSMSValidationLable {
    _isPasswordLogin = !_isPasswordLogin;
    _SMSTextFieldView.hidden = _isPasswordLogin;
    _passwordTextFieldView.hidden = !_isPasswordLogin;
    _SMSValidationLable.text = (_isPasswordLogin) ? @"用短信验证码登录" : @"用帐号密码登录";
    if (_isPasswordLogin) {
        _SMSTextField.text = nil;
    } else {
        _passwordTextField.text = nil;
    }
}

- (void)startTime:(UIButton *)button {
    [self.view endEditing:YES];
    
    _phoneNumber = [_phoneNumberTextField.text replacingEmptyString];
 
    if (_phoneNumber.length != 11) {
        [self showMessageAutoHide:@"请输入正确的手机号码"];
        return;
    };
    
   //发送验证码
    NSDictionary *parameters = @{@"phone":_phoneNumber};
    [TJNetworkTool requestWithUrl:@"Sms.LoginPhoneCode" parameters:parameters success:^(id data) {
                TJLog(@"%@",data);
                if (![data[@"code"] intValue]) {
                    [TJCountdown startTime:button];
                    self.SMSTextField.text = nil;
                }
              [self showMessageAutoHide:data[@"msg"]];
    } failure:^(id error) { }];
 
}

- (void)didClickLoginButton {
    
    _phoneNumber = _phoneNumberTextField.text;
    NSString *password = _passwordTextField.text;
    
    NSString *SMSCode = _SMSTextField.text;
    
    TJLog(@"%@, %@",_phoneNumber, password);
    
    _phoneNumber = [_phoneNumber replacingEmptyString];
    
    if (_phoneNumber.length == 0) {
        [MBProgressHUD showHUDAddedTo:self.view messagqe:@"请输入手机号码"];
        return;
    }
     if (_phoneNumber.length != 11) {
        [MBProgressHUD showHUDAddedTo:self.view messagqe:@"请输入正确的手机号码"];
        return;
     }
    
    NSMutableDictionary *parameters = [NSMutableDictionary dictionaryWithObject:_phoneNumber forKey:@"phone"];

    if (_isPasswordLogin) {
        if (password.length == 0) {
            [MBProgressHUD showHUDAddedTo:self.view messagqe:@"请输入登录密码"];
            return;
        }
        if (password.length < 6 || password.length > 16) {
            [MBProgressHUD showHUDAddedTo:self.view messagqe:@"请输入6-16位密码"];
            return;
        }
        [parameters setObject:password forKey:@"password"];
    } else {
        if (SMSCode.length == 0) {
            [MBProgressHUD showHUDAddedTo:self.view messagqe:@"请输入验证码"];
            return;
        }
        if (SMSCode.length != 6) {
            [MBProgressHUD showHUDAddedTo:self.view messagqe:@"请输入6位数验证码"];
            return;
        }
        [parameters setObject:SMSCode forKey:@"smsCode"];
    }
    
    [self.view endEditing:YES];

    
    NSUserDefaults *userDefaults = [NSUserDefaults standardUserDefaults];
    
    
    _hud = [self showMessage:@"登录中"];
    
    TJLog(@"登录parameters = %@", parameters);

    [TJNetworkTool requestWithUrl:(_isPasswordLogin) ? @"User.Login" : @"User.smsLogin" parameters:parameters success:^(id data) {
        
        [self.hud hideHUDAfter:0];
        
        NSDictionary *info = data[@"info"];
        
        if (![data[@"code"] intValue]) {
            
            
            [TJKeychain tj_setObject: info[@"token"] forKey:token];
            [TJKeychain tj_setObject: info[@"device_id"] forKey:deviceID];
            
            [userDefaults setBool:YES forKey:self.phoneNumber];
            
            [userDefaults setBool:YES forKey:login];
            [userDefaults setObject:info[@"phone"] forKey:phone];
            
            [[NSNotificationCenter defaultCenter] postNotificationName:loginSucceedNotification object:nil userInfo:nil];
            
            if (self.loginSuccess) self.loginSuccess();
            
            
            [self dismiss];
        } else {
            [MBProgressHUD showHUDAddedTo:self.view messagqe:data[@"msg"]];
        }
        
    } failure:^(id error) {
        [self showMessageAutoHide:@"登录失败"];
        [self.hud hideHUDAfter:0];
    }];
 
}

- (void)tapRegistered {
    [self.navigationController pushViewControllerWithName:@"XLRegisteredController" title:@"注册" animated:YES];
}

- (void)tapForgotPassword {
    [self.navigationController pushViewControllerWithName:@"XLForgotPasswordController" title:@"找回密码" animated:YES];
}

- (void)viewWillAppear:(BOOL)animated {
    [super viewWillAppear:animated];
    self.tj_navigationItem.leftBarButtonItems = [UIBarButtonItem backBarItemWithTarget:self action:@selector(dismiss)];
}

- (void)dismiss {
    if (self.dissmissSingleController) {
        [self dismissViewControllerAnimated:YES completion:nil];
    } else {
        [self dismissToRootViewController];
    }
}

- (UIStatusBarStyle)preferredStatusBarStyle {
    return UIStatusBarStyleLightContent;
}

- (void)touchesBegan:(NSSet<UITouch *> *)touches withEvent:(UIEvent *)event {
    [self.view endEditing:YES];
}


@end
