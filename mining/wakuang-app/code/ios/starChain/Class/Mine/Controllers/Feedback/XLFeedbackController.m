//
//  XLFeedbackController.m
//  starChain
//
//  Created by rlx on 2018/6/11.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import "XLFeedbackController.h"
#import "XLButton.h"

@interface XLFeedbackController ()<UITextFieldDelegate, UITextViewDelegate>

@property (copy, nonatomic) NSString *info;
@property (copy, nonatomic) NSString *phone;
@property (weak, nonatomic) UITextField *phoneNumberTextField;
@property (weak, nonatomic) UITextView *feedbackTextView;
@property (weak, nonatomic) UIButton *commitButton;

@property (strong, nonatomic) NSDictionary *dataDict;
@property (strong, nonatomic) UILabel *placeHolderlLable;

@property (weak, nonatomic) MBProgressHUD *hud;


@end

@implementation XLFeedbackController

- (void)viewDidLoad {
    [super viewDidLoad];
    [self addSubView];
}

#pragma mark - 添加子控件
- (void)addSubView {
    
    UITextView *feedbackTextView = [UITextView tj_WithSuperView:self.view];
    feedbackTextView.returnKeyType = UIReturnKeyDone;
    feedbackTextView.frame = CGRectMake(0, TOPMAGRIN, KScreenWidth, 139);
    feedbackTextView.font = [UIFont systemFontOfSize:16];
    [feedbackTextView becomeFirstResponder];
    feedbackTextView.delegate = self;
    _feedbackTextView = feedbackTextView;
    
    UILabel *placeHolderlLable = [UILabel lableWithSuperView:feedbackTextView fontSize:15 color:UIColor_Hex(0xc1c2c3) title:@"请输入您的宝贵意见, 我们将不断改进" textAlignment:NSTextAlignmentLeft];
    [placeHolderlLable addTapGesturesWithTarget:self action:@selector(starInput)];
    placeHolderlLable.frame = CGRectMake(6.3, 4, 300, 30);
    _placeHolderlLable = placeHolderlLable;
    placeHolderlLable.textColor = UIColor_Hex(0x999999);
    
    UIView *contentView = [UIView tj_WithSuperView:self.view];
    contentView.backgroundColor = [UIColor whiteColor];
    [contentView makeConstraints:^(MASConstraintMaker *make) {
        make.left.right.offset(0);
        make.top.equalTo(feedbackTextView.bottom).offset(9);
        make.height.offset(44);
    }];
    
    UILabel *contactType = [UILabel lableWithSuperView:contentView fontSize:16 color:UIColor_Hex(0x666666) title:@"联系方式" textAlignment:NSTextAlignmentLeft];
    [contactType makeConstraints:^(MASConstraintMaker *make) {
        make.left.offset(16);
        make.width.offset(100);
        make.height.offset(44);
        make.top.offset(0);
    }];
    
    UITextField *phoneNumberTextField = [UITextField tj_WithSuperView:contentView];
    phoneNumberTextField.placeholder = @"手机号码, 选填";
    phoneNumberTextField.borderStyle = UITextBorderStyleNone;
    phoneNumberTextField.font = [UIFont systemFontOfSize:15];
    phoneNumberTextField.returnKeyType = UIReturnKeyDone;
    phoneNumberTextField.delegate = self;
    _phoneNumberTextField = phoneNumberTextField;
    [phoneNumberTextField makeConstraints:^(MASConstraintMaker *make) {
        make.centerY.equalTo(contactType);
        make.left.equalTo(contactType.right).offset(H(8));
        make.height.equalTo(35);
        make.width.offset(150);
    }];
    
    XLButton *commitButton = [XLButton buttonWithSuperView:self.view fontSize:15 color:[UIColor whiteColor] title:@"提交"];
    [commitButton setBackgroundImage:[UIImage imageNamed:@"gradientButtonBg"] forState:UIControlStateNormal];
    [commitButton shearRoundedCornersWithRadiu:ZOOM5(45) * 0.5];
    [commitButton addTarget:self action:@selector(didClickCommitButton:) forControlEvents:UIControlEventTouchUpInside];
    _commitButton = commitButton;
    [commitButton makeConstraints:^(MASConstraintMaker *make) {
        make.left.offset(16);
        make.right.offset(-16);
        make.height.offset(ZOOM5(45));
        make.top.equalTo(phoneNumberTextField.bottom).offset(60);
    }];
}

- (BOOL)textView:(UITextView *)textView shouldChangeTextInRange:(NSRange)range replacementText:(NSString *)text {
    NSInteger existedLength = textView.text.length;
    NSInteger selectedLength = range.length;
    NSInteger replaceLength = text.length;
    NSInteger currentLengh = existedLength - selectedLength + replaceLength;
    if ([text isEqualToString:@"\n"]) {
        [textView resignFirstResponder];
        return NO;
    } else if (textView == _feedbackTextView && currentLengh > 160) {
        if (_hud) return NO;
         _hud = [self showMessageAutoHide:@"最多可输入160个字"];
        return NO;
    } else {
        return YES;
    }
}

#pragma mark - 点击提交按钮
- (void)didClickCommitButton:(UIButton *)commitButton {
    [self.view endEditing:YES];
    
    _phone = [_phoneNumberTextField.text replacingEmptyString];
    _info = self.feedbackTextView.text;
    
    if (_info.length == 0) {
        [self showMessageAutoHide:@"请输入反馈内容"];
        return;
    }
    
    NSMutableDictionary *parameters = [NSMutableDictionary dictionary];
    [parameters setValue:_info forKey:@"febackInfo"];
    if (_phone.length) {
        [parameters setValue:_phone forKey:@"phone"];
    }
 
    _hud = [self showMessage:@"提交中"];
    
    TJLog(@"parameters = %@", parameters);
    
    [TJNetworkTool requestWithUrl:@"User.AddFeback" parameters:parameters.copy success:^(id data) {
        [self.hud hideHUDAfter:0];
        if (![data[@"code"] intValue]) {
            self.feedbackTextView.text = nil;
            self.placeHolderlLable.hidden = NO;
            [self.feedbackTextView becomeFirstResponder];
        }
        [self showMessageAutoHide:data[@"msg"]];
    } failure:^(id erroe) {
        [self.hud hideHUDAfter:0];
     }];
 }

#pragma mark - textView 输入改变
- (void)textViewDidChange:(UITextView *)textView {
    _info = textView.text;
    _info = [_info stringByReplacingOccurrencesOfString:@" " withString:@""];
    _placeHolderlLable.hidden = textView.text.length;
}

- (void)touchesBegan:(NSSet<UITouch *> *)touches withEvent:(UIEvent *)event {
    [self.view endEditing:YES];
}

- (BOOL)textFieldShouldReturn:(UITextField *)textField {
    [self.view endEditing:YES];
    return YES;
}

- (void)starInput {
    [self.feedbackTextView becomeFirstResponder];
}


@end
