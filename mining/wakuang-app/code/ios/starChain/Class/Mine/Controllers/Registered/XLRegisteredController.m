//
//  XLRegisteredController.m
//  starChain
//
//  Created by rlx on 2018/6/11.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import "XLRegisteredController.h"
#import "XLTextFieldView.h"
#import "XLButton.h"
#import "TJCountdown.h"
#import "XLLoginController.h"
#import "XLRegisteredSuccessController.h"
#import "XLButton.h"
#import "XLPhoneNumberformat.h"

@interface XLRegisteredController ()<UITextFieldDelegate>

@property (weak, nonatomic) UIImageView *contentImageView;
@property (weak, nonatomic) UITextField *phoneNumberTextField;
@property (weak, nonatomic) UITextField *passwordTextField;
@property (weak, nonatomic) UITextField *SMSTextField;
@property (weak, nonatomic) UITextField *invitationTextField;
@property (weak, nonatomic) UITextField *textField;
@property (weak, nonatomic) XLTextFieldView *SMSTextFieldView;
@property (weak, nonatomic) XLTextFieldView *passwordTextFieldView;
@property (weak, nonatomic) UIScrollView *contentScrollView;
@property (weak, nonatomic) XLButton *registeredButton;
@property (copy, nonatomic) NSString *phoneNumber;
@property (assign, nonatomic) BOOL isAgreed;


@end

@implementation XLRegisteredController

- (void)viewDidLoad {
    [super viewDidLoad];
 
    self.tj_navigationBar.tintColor = [UIColor whiteColor];
    self.titleColor = [UIColor whiteColor];
    self.navBarAlpha = YES;
    self.tj_navigationItem.leftBarButtonItems = [UIBarButtonItem backBarItemWithTarget:self action:@selector(didClickBackItem)];

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
    
    XLTextFieldView *invitationTextFieldView = [[XLTextFieldView alloc] initWithIconName:@"Invitation_icon" placeHolder:@"邀请码(选填)"];
    [contentImageView addSubview:invitationTextFieldView];
    [invitationTextFieldView makeConstraints:^(MASConstraintMaker *make) {
        make.left.right.height.equalTo(phoneTextFieldView);
        make.top.equalTo(passwordTextFieldView.bottom).offset(H(10));
    }];
    
    XLButton *agreedButton = [XLButton buttonWithSuperView:contentImageView fontSize:12 color:[UIColor whiteColor] title:@"同意"];
    [agreedButton addTarget:self action:@selector(didClickAgreedButton:) forControlEvents:UIControlEventTouchUpInside];
    [agreedButton setImage:[UIImage imageNamed:@"icon_disagree"] forState:UIControlStateSelected];
    [agreedButton setImage:[UIImage imageNamed:@"icon_agree"] forState:UIControlStateNormal];
    [agreedButton setImageEdgeInsets:UIEdgeInsetsMake(0, -10, 0, 0)];
    [agreedButton setTitleEdgeInsets:UIEdgeInsetsMake(0, -3, 0, 0)];
    [agreedButton makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(phoneTextFieldView).offset(5);
        make.top.equalTo(invitationTextFieldView.bottom).offset(1);
        make.width.offset(45);
        make.height.offset(30);
    }];
    
    UILabel *agreementLable = [UILabel lableWithSuperView:contentImageView fontSize:12 color:[UIColor whiteColor] title:@"《用户服务协议》" textAlignment:NSTextAlignmentLeft];
    [agreementLable addTapGesturesWithTarget:self action:@selector(didClickAgreementButton)];
    [agreementLable makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(agreedButton.right);
        make.top.height.equalTo(agreedButton);
    }];
    
    XLButton *registeredButton = [XLButton buttonWithSuperView:contentImageView fontSize:16 color:[UIColor whiteColor] title:@"注册"];
    [registeredButton setBackgroundImage:[UIImage imageNamed:@"gradientButtonBg"] forState:UIControlStateNormal];
    [registeredButton shearRoundedCornersWithRadiu:ZOOM5(45) * 0.5];
    [registeredButton addTarget:self action:@selector(didClickRegisteredButton) forControlEvents:UIControlEventTouchUpInside];
    [registeredButton makeConstraints:^(MASConstraintMaker *make) {
        make.top.equalTo(agreementLable.bottom).offset(30);
        make.left.right.equalTo(passwordTextFieldView);
        make.height.offset(xl_bottomButtonH);
    }];
    
    NSString *directLoginButtonTitle = @"已有账户? 直接登录";
    CGSize directLoginButtonTitleSize = [directLoginButtonTitle getStringSizeWithWidth:KScreenWidth fontSize:14];
    UIButton *directLoginButton = [UIButton buttonWithSuperView:contentImageView fontSize:14 color:[UIColor whiteColor] title:nil];
    NSAttributedString *attString = [directLoginButtonTitle attributedStringWithLoction:8 foregroundFont:14 backgroundFont:14 foregroundcolor:[UIColor whiteColor] backgroundColor:UIColor_Hex(0xAC56FA)];
    [directLoginButton setAttributedTitle:attString forState:UIControlStateNormal];
    [directLoginButton addTarget:self action:@selector(didClickDirectLoginButton) forControlEvents:UIControlEventTouchUpInside];
    [directLoginButton makeConstraints:^(MASConstraintMaker *make) {
        make.centerX.offset(0);
        make.bottom.offset(-21);
        make.height.offset(30);
        make.width.offset(directLoginButtonTitleSize.width + 10);
    }];
    
    
    self.SMSTextFieldView = SMSTextFieldView;
    self.phoneNumberTextField = phoneTextFieldView.textField;
    self.passwordTextField = passwordTextFieldView.textField;
    self.invitationTextField = invitationTextFieldView.textField;
    self.SMSTextField  = SMSTextFieldView.textField;
    
    self.phoneNumberTextField.delegate = self;
    self.SMSTextField.delegate = self;
    self.passwordTextField.delegate = self;
    self.invitationTextField.delegate = self;
    
    self.phoneNumberTextField.clearButtonMode = UITextFieldViewModeWhileEditing;
    self.passwordTextField.clearButtonMode = UITextFieldViewModeWhileEditing;
    
    self.phoneNumberTextField.keyboardType = UIKeyboardTypeNumberPad;
    self.SMSTextField.keyboardType = UIKeyboardTypeNumberPad;
    
    self.passwordTextField.secureTextEntry = YES;
    self.passwordTextField.returnKeyType = UIReturnKeyDone;
    self.invitationTextField.returnKeyType = UIReturnKeyDone;

    
    self.registeredButton = registeredButton;

    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(keyboardWillShow:) name:UIKeyboardWillShowNotification object:nil];
    
}

- (void)dealloc {
    [[NSNotificationCenter defaultCenter] removeObserver:self name:UIKeyboardWillShowNotification object:nil];
}

- (void)textFieldDidBeginEditing:(UITextField *)textField {
    self.textField = textField;
}

- (void)keyboardWillShow:(NSNotification *)notification {
    NSDictionary *userInfo = [notification userInfo];
    NSValue *aValue = [userInfo objectForKey:UIKeyboardFrameEndUserInfoKey];
    CGRect keyboardRect = [aValue CGRectValue];
    CGFloat keyboardH = keyboardRect.size.height;
    
    CGRect textFieldINContentRect = [self.textField.superview convertRect:self.textField.frame toView:self.contentImageView];
    CGFloat textFieldMaxY = CGRectGetMaxY(textFieldINContentRect);
    if (KScreenHeight - textFieldMaxY < (keyboardH + 5)) {
        [self.contentScrollView setContentOffset:CGPointMake(0, keyboardH - (KScreenHeight - textFieldMaxY) + 5) animated:YES];
    } else {
        [self.contentScrollView setContentOffset:CGPointZero animated:YES];
    }
}

- (BOOL)textFieldShouldReturn:(UITextField *)textField {
    [self tapContentImageView];
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
    } else if (textField == _SMSTextField && currentLengh > 6) {
        return NO;
    } else if (textField == _passwordTextField && currentLengh > 15) {
        return NO;
    } else if (textField == _invitationTextField && currentLengh > 20) {
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
    [TJNetworkTool requestWithUrl:@"Sms.RegCode" parameters:parameters success:^(id data) {
        if (![data[@"code"] intValue]) {
            [TJCountdown startTime:button];
            self.SMSTextField.text = nil;
        }
        [self showMessageAutoHide:data[@"msg"]];
    } failure:^(id error) { }];
    
}


- (void)didClickDirectLoginButton {
    
    TJLog(@"modalLoginViewControllers = %d",self.modalControllers);
    if (self.modalControllers) {
        XLLoginController *loginController = (XLLoginController *)[self presentViewControllerWithName:@"XLLoginController" title:@"登录"];
        [loginController setLoginSuccess:^{
            [self.navigationController popViewControllerAnimated:YES];
        }];
    } else {
        [self.navigationController popViewControllerAnimated:YES];
    }
}

- (void)didClickRegisteredButton {
    
    NSString *phoneNumber = [_phoneNumberTextField.text replacingEmptyString];
    NSString *password = _passwordTextField.text;
    NSString *verificationCode = _SMSTextField.text;
    
    if (phoneNumber.length == 0) {
        [MBProgressHUD showHUDAddedTo:self.view messagqe:@"请输入手机号码"];
        return;
    } else if (phoneNumber.length != 11) {
       [MBProgressHUD showHUDAddedTo:self.view messagqe:@"请输入正确的手机号码"];
        return;
    } else if (verificationCode.length == 0) {
        [MBProgressHUD showHUDAddedTo:self.view messagqe:@"请输入短信验证码"];
        return;
    } else if (verificationCode.length < 6) {
         [MBProgressHUD showHUDAddedTo:self.view messagqe:@"请输入6位数验证码"];
        return;
    } else if (password.length == 0) {
         [MBProgressHUD showHUDAddedTo:self.view messagqe:@"请输入注册密码"];
        return;
    } else if ((password.length < 6 || password.length > 16)) {
        [MBProgressHUD showHUDAddedTo:self.view messagqe:@"请输入6-16位密码"];
        return;
    } else if (!_isAgreed) {
         [MBProgressHUD showHUDAddedTo:self.view messagqe:@"请阅读并同意协议"];
        return;
    }
    
    TJLog(@"%@, %@, %@,%@",phoneNumber, password, verificationCode, @(_isAgreed));
    
    NSDictionary *parameters = @{
                                 @"phone": phoneNumber,
                                 @"password": password,
                                 @"phoneCode": verificationCode,
                                 @"agreement": @(_isAgreed),
                                 };
    
    NSMutableDictionary *MParameters = [NSMutableDictionary dictionary];
    [MParameters addEntriesFromDictionary:parameters];
    
    if (_invitationTextField.text.length) {
        NSString *inviteCode = _invitationTextField.text;
        [MParameters setObject:inviteCode forKey:@"inviteCode"];
    }
    
    [self tapContentImageView];
    TJLog(@"MParameters.copy = %@", MParameters.copy);
    
    
    [TJNetworkTool requestWithUrl:@"User.Register" parameters:MParameters.copy success:^(id data) {

        if ([data[@"code"] intValue] == 0) {
            
            NSUserDefaults *userDefaults = [NSUserDefaults standardUserDefaults];
            NSDictionary *dict = data[@"info"];
            
            [TJKeychain tj_setObject:dict[@"token"] forKey:token];
            [TJKeychain tj_setObject:dict[@"device_id"] forKey:deviceID];

            [userDefaults setBool:YES forKey:login];
            [userDefaults setObject:dict[@"phone"] forKey:phone];
            [[NSNotificationCenter defaultCenter] postNotificationName:registeredSucceedNotification object:nil];
            [[NSNotificationCenter defaultCenter] postNotificationName:loginSucceedNotification object:nil];
            
            XLRegisteredSuccessController *registeredSuccessfullyController = [[XLRegisteredSuccessController alloc] init];
            registeredSuccessfullyController.message = dict[@"return_msg"];
            [self.navigationController pushViewController:registeredSuccessfullyController animated:YES];
 
        } else {
            [self alertWithTitle:nil message:data[@"msg"] leftButtonName:@"确定" rightButtonName:nil leftButtonBlock:nil rightButtonBlock:nil];
        }

    } failure:^(id error) {
        [self showMessageAutoHide:@"注册失败"];
    }];
}

- (void)didClickAgreedButton:(UIButton *)button {
    button.selected = !button.selected;
    _isAgreed = button.selected;
    if (button.selected) {
        TJLog(@"同意");
    } else {
        TJLog(@"不同意");
    }
}

- (void)didClickAgreementButton {
    [self loadInteractionWebPageWithUrlString:[USERDEFAULTS objectForKey:@"RegisterUrl"] title:@"用户服务协议"];
}

- (void)didClickBackItem {
    if (self.modalControllers) {
        [self dismissToRootViewController];
    } else {
        [self.navigationController popViewControllerAnimated:YES];
    }
}

- (void)tapContentImageView {
    if (self.contentScrollView.isDecelerating) return;
    [self.contentScrollView setContentOffset:CGPointZero animated:YES];
    [self.view endEditing:YES];
}

- (UIStatusBarStyle)preferredStatusBarStyle {
    return UIStatusBarStyleLightContent;
}



@end
