//
//  XLForgotPasswordController.m
//  starChain
//
//  Created by rlx on 2018/6/11.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import "XLForgotPasswordController.h"
#import "XLTextFieldView.h"
#import "XLButton.h"
#import "TJCountdown.h"
#import "XLPhoneNumberformat.h"

@interface XLForgotPasswordController ()<UITextFieldDelegate>

@property (weak, nonatomic) UITextField *phoneNumberTextField;
@property (weak, nonatomic) UITextField *passwordTextField;
@property (weak, nonatomic) UITextField *SMSTextField;
@property (weak, nonatomic) UITextField *confirmPasswordTextField;
@property (weak, nonatomic) UIScrollView *contentScrollView;
@property (copy, nonatomic) NSString *phoneNumber;

@end

@implementation XLForgotPasswordController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    self.tj_navigationBar.tintColor = [UIColor whiteColor];
    self.titleColor = [UIColor whiteColor];
    self.navBarAlpha = YES;
    [self addSubView];
}

- (void)addSubView {
    
    UIScrollView *contentScrollView = [[UIScrollView alloc] init];
    [self.view insertSubview:contentScrollView atIndex:0];
    [contentScrollView makeConstraints:^(MASConstraintMaker *make) {make.edges.offset(0);}];
    self.contentScrollView = contentScrollView;
    
    UIImageView *contentImageView = [[UIImageView alloc] init];
    [contentScrollView addSubview:contentImageView];
    [contentImageView addTapGesturesWithTarget:self action:@selector(tapContentImageView)];
    contentImageView.userInteractionEnabled = YES;
    contentImageView.image = [UIImage imageNamed:@"loginBackground"];
    [contentImageView makeConstraints:^(MASConstraintMaker *make) {
        make.edges.offset(0);
        make.width.offset(KScreenWidth);
        make.height.offset(KScreenHeight);
    }];
 
    XLTextFieldView *phoneTextFieldView = [[XLTextFieldView alloc] initWithIconName:@"iphone_icon" placeHolder:@"请输入您的手机号码"];
    [contentImageView addSubview:phoneTextFieldView];
    [phoneTextFieldView makeConstraints:^(MASConstraintMaker *make) {
        make.left.offset(18);
        make.right.offset(-18);
        make.height.offset(ZOOM5(45));
        make.top.offset(ZOOM5(180));
    }];
    
    XLTextFieldView *SMSTextFieldView = [[XLTextFieldView alloc] initWithIconName:@"icon_safe" placeHolder:@"请输入短信验证码"];
    [contentImageView addSubview:SMSTextFieldView];
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
    
    XLTextFieldView *passwordTextFieldView = [[XLTextFieldView alloc] initWithIconName:@"icon_safe" placeHolder:@"请输入6-16位密码"];
    [contentImageView addSubview:passwordTextFieldView];
    passwordTextFieldView.rightButton.hidden = NO;
    [passwordTextFieldView makeConstraints:^(MASConstraintMaker *make) {
        make.left.right.height.equalTo(phoneTextFieldView);
        make.top.equalTo(SMSTextFieldView.bottom).offset(H(10));
    }];
    
    XLTextFieldView *confirmPasswordTextFieldView = [[XLTextFieldView alloc] initWithIconName:@"icon_safe" placeHolder:@"请输入确认密码"];
    [contentImageView addSubview:confirmPasswordTextFieldView];
    confirmPasswordTextFieldView.rightButton.hidden = NO;
    [confirmPasswordTextFieldView makeConstraints:^(MASConstraintMaker *make) {
        make.left.right.height.equalTo(phoneTextFieldView);
        make.top.equalTo(passwordTextFieldView.bottom).offset(H(10));
    }];
    
    XLButton *conmitButton = [XLButton buttonWithSuperView:contentImageView fontSize:16 color:[UIColor whiteColor] title:@"提交"];
    [conmitButton setBackgroundImage:[UIImage imageNamed:@"gradientButtonBg"] forState:UIControlStateNormal];
    [conmitButton shearRoundedCornersWithRadiu:ZOOM5(45) * 0.5];
    [conmitButton addTarget:self action:@selector(didClickCommitButton) forControlEvents:UIControlEventTouchUpInside];
    [conmitButton makeConstraints:^(MASConstraintMaker *make) {
        make.top.equalTo(confirmPasswordTextFieldView.bottom).offset(30);
        make.left.right.equalTo(passwordTextFieldView);
        make.height.offset(xl_bottomButtonH);
    }];
    
    self.SMSTextField = SMSTextFieldView.textField;
    self.phoneNumberTextField = phoneTextFieldView.textField;
    self.passwordTextField = passwordTextFieldView.textField;
    self.confirmPasswordTextField = confirmPasswordTextFieldView.textField;
    
    self.phoneNumberTextField.delegate = self;
    self.SMSTextField.delegate = self;
    self.passwordTextField.delegate = self;
    self.confirmPasswordTextField.delegate = self;
    
    self.phoneNumberTextField.clearButtonMode = UITextFieldViewModeWhileEditing;
    self.passwordTextField.clearButtonMode = UITextFieldViewModeWhileEditing;
    self.confirmPasswordTextField.clearButtonMode = UITextFieldViewModeWhileEditing;
    
    self.passwordTextField.secureTextEntry = YES;
    self.confirmPasswordTextField.secureTextEntry = YES;
    
    self.passwordTextField.returnKeyType = UIReturnKeyDone;
    self.confirmPasswordTextField.returnKeyType = UIReturnKeyDone;
 
    self.phoneNumberTextField.keyboardType = UIKeyboardTypeNumberPad;
    self.SMSTextField.keyboardType = UIKeyboardTypeNumberPad;
}

- (BOOL)textFieldShouldReturn:(UITextField *)textField {
    [self.view endEditing:YES];
    return YES;
}

- (BOOL)textField:(UITextField *)textField shouldChangeCharactersInRange:(NSRange)range replacementString:(NSString *)string {
    NSInteger existedLength = textField.text.length;
    NSInteger selectedLength = range.length;
    NSInteger replaceLength = string.length;
    NSInteger currentLengh = existedLength - selectedLength + replaceLength;
    if (textField == _phoneNumberTextField) {
        return [XLPhoneNumberformat phoneNumberformatWithTextField:textField shouldChangeCharactersInRange:range replacementString:string formatType:XLFormatTypePhoneNumber];
    } else if (string.length == 0) {
        return YES;
    } else if ((textField == _passwordTextField || textField == _confirmPasswordTextField) && currentLengh > 16) {
        return NO;
    } else if (textField == _SMSTextField && currentLengh > 6) {
        return NO;
    }
    return YES;
}

- (void)startTime:(UIButton *)button {
    [self.view endEditing:YES];
    
    _phoneNumber = [_phoneNumberTextField.text replacingEmptyString];
    if (_phoneNumber.length != 11) {
        [self showMessageAutoHide:@"请输入正确的手机号码"]; return;
    };

    NSDictionary *parameters = @{@"phone":_phoneNumber};
    [TJNetworkTool requestWithUrl:@"Sms.FindCode" parameters:parameters success:^(id data) {
        if (![data[@"code"] intValue]) {
            [TJCountdown startTime:button];
            self.SMSTextField.text = nil;
        }
        
        [self showMessageAutoHide:data[@"msg"]];
    } failure:^(id error) { }];
    
}

- (void)didClickCommitButton {
    [self.view endEditing:YES];
    
    NSString *phoneNumber = [_phoneNumberTextField.text replacingEmptyString];
    NSString *verificationCode = _SMSTextField.text;
    NSString *password = _passwordTextField.text;
    NSString *confirmPassword = _confirmPasswordTextField.text;
    
    TJLog(@"phoneNumber = %@,verificationCode = %@,password = %@,confirmPassword = %@",phoneNumber, verificationCode, password, confirmPassword);
    if (phoneNumber.length == 0) {
        [self showMessageAutoHide:@"请输入手机号码"]; return;
    }
    if (phoneNumber.length != 11) {
        [self showMessageAutoHide:@"请输入正确的手机号码"]; return;
    }
    if (verificationCode.length < 6) {
        [self showMessageAutoHide:@"请输入6位数验证码"]; return;
    }
    if (password.length == 0) {
        [self showMessageAutoHide:@"请输入新密码"]; return;
    }
    if ((password.length < 6 || password.length > 16)) {
        [self showMessageAutoHide:@"请输入6-16位密码"]; return;
    }
    if ((confirmPassword.length == 0)) {
        [self showMessageAutoHide:@"请输入确认密码"]; return;
    }
    if ((confirmPassword.length < 6 || confirmPassword.length > 16)) {
        [self showMessageAutoHide:@"请输入6-16位密码"]; return;
    }
    if (![confirmPassword isEqualToString:password]) {
        [self showMessageAutoHide:@"新密码和确认密码不同"]; return;
    }
    
    NSDictionary *parameters = @{
                                 @"phone": phoneNumber,
                                 @"newPwd": password,
                                 @"code": @(verificationCode.intValue),//整型的验证码
                                 @"chkNewPwd": confirmPassword
                                 };
    
    TJLog(@"User.FindPwd = parameters = %@", parameters);
    
    [TJNetworkTool requestWithUrl:@"User.FindPwd" parameters:parameters success:^(id data) {
        if (![data[@"code"] intValue]) {
            [MBProgressHUD showHUDAddedTo:self.view messagqe:@"重置成功!"];
            dispatch_after(dispatch_time(DISPATCH_TIME_NOW, (int64_t)(1 * NSEC_PER_SEC)), dispatch_get_main_queue(), ^{
                [self.navigationController popViewControllerAnimated:YES];
            });
        } else {
            [MBProgressHUD showHUDAddedTo:self.view messagqe:data[@"msg"]];
        }
    } failure:^(id error) { }];
}

- (void)tapContentImageView {
    [self.contentScrollView setContentOffset:CGPointZero animated:YES];
    [self.view endEditing:YES];
}

- (void)touchesBegan:(NSSet<UITouch *> *)touches withEvent:(UIEvent *)event {
    [self.view endEditing:YES];
}

- (UIStatusBarStyle)preferredStatusBarStyle {
    return UIStatusBarStyleLightContent;
}

@end
