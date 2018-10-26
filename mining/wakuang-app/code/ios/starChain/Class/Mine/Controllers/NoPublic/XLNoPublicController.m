//
//  XLNoPublicController.m
//  starChain
//
//  Created by rlx on 2018/6/15.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import "XLNoPublicController.h"
#import "XLButton.h"

@interface XLNoPublicController ()<UITextFieldDelegate>
    
    @property (strong, nonatomic) NSMutableArray <UILabel *> *codeLables;
    @property (weak, nonatomic) UIScrollView *contentScrollView;
    @property (weak, nonatomic) UITextField *codeTextField;
    @property (weak, nonatomic) XLButton *commitButton;
    @property (assign, nonatomic) CGFloat keyboardH;
    
    @end

@implementation XLNoPublicController
    
- (void)viewDidLoad {
    [super viewDidLoad];
    
    self.tj_navigationBar.tintColor = [UIColor whiteColor];
    self.titleColor = [UIColor whiteColor];
    self.navBarAlpha = YES;
    
    UIScrollView *contentScrollView = [[UIScrollView alloc] init];
    [self.view insertSubview:contentScrollView atIndex:0];
    [contentScrollView makeConstraints:^(MASConstraintMaker *make) {make.edges.offset(0);}];
    self.contentScrollView = contentScrollView;
    [contentScrollView addTapGesturesWithTarget:self action:@selector(tapSCrollView)];
    
    UIImageView *backgroundImageView = [[UIImageView alloc] init];
    backgroundImageView.userInteractionEnabled = YES;
    [contentScrollView insertSubview:backgroundImageView atIndex:0];
    backgroundImageView.image = [UIImage imageNamed:@"follow_bg"];
    [backgroundImageView makeConstraints:^(MASConstraintMaker *make) {
        make.edges.offset(0);
        make.width.offset(KScreenWidth);
        make.height.offset(KScreenHeight);
    }];
    
    UILabel *textLable = [UILabel lableWithSuperView:backgroundImageView fontSize:ZOOM5(38) color:UIColor_Hex(0x26defd) title:[NSString stringWithFormat:@"关注微信公众号领取%d个星星", self.rewardCount] textAlignment:NSTextAlignmentCenter];
    textLable.numberOfLines = 0;
    textLable.attributedText = [textLable.text titleMargin:5 withAlignment:NSTextAlignmentCenter];
    if (@available(iOS 8.2, *)) {
        textLable.font = [UIFont systemFontOfSize:38 weight:UIFontWeightLight];
    }
    [textLable makeConstraints:^(MASConstraintMaker *make) {
        make.left.offset(H(45));
        make.right.offset(H(-45));
        make.centerX.offset(0);
        make.top.offset(H(87));
    }];
    
    UIImageView *contentImageView = [UIImageView imageViewWithImageName:@"publicConcent_bg" superView:backgroundImageView];
    contentImageView.userInteractionEnabled = YES;
    [backgroundImageView addSubview:contentImageView];
    [contentImageView makeConstraints:^(MASConstraintMaker *make) {
        make.left.offset(H(18));
        make.right.offset(H(-18));
        make.height.offset(H(436));
        make.top.offset(H(210));
    }];
    
    NSString *instructionsString = [NSString stringWithFormat:@"1.在微信公众号搜索“天智星”并关注\n2.在天智星公众号中输入“领取星星”获得验证码\n3.在下方输入验证码，验证成功后即可领取星星 注：每个账户只有1次领取机会"];
    UILabel *instructionsLable = [UILabel lableWithSuperView:contentImageView fontSize:H(14) color:UIColor_Hex(0x6963C0) title:nil textAlignment:NSTextAlignmentLeft];
    instructionsLable.numberOfLines = 0;
    instructionsLable.attributedText = [instructionsString titleMargin:H(6) withAlignment:NSTextAlignmentLeft];
    [instructionsLable makeConstraints:^(MASConstraintMaker *make) {
        make.top.offset(H(45));
        make.centerX.offset(0);
        make.left.offset(H(20));
        make.right.offset(H(-20));
    }];
    
    UILabel *inputPromptLable = [UILabel lableWithSuperView:contentImageView fontSize:H(16) color:UIColor_Hex(0x000000) title:@"请输入验证码" textAlignment:NSTextAlignmentLeft];
    [inputPromptLable makeConstraints:^(MASConstraintMaker *make) {
        make.top.equalTo(instructionsLable.bottom).offset(H(58));
        make.centerX.offset(0);
    }];
    
    UITextField *codeTextField = [UITextField tj_WithSuperView:contentImageView];
    codeTextField.keyboardType = UIKeyboardTypeNumberPad;
    codeTextField.textAlignment = NSTextAlignmentCenter;
    codeTextField.textColor = UIColor_Hex(0x512b99);
    codeTextField.font = [UIFont boldSystemFontOfSize:30];
    codeTextField.delegate = self;
    self.codeTextField = codeTextField;
    [codeTextField makeConstraints:^(MASConstraintMaker *make) {
        make.top.equalTo(inputPromptLable.bottom).offset(26);
        make.left.right.equalTo(instructionsLable);
        make.height.offset(50);
    }];
    
    UIView *lineView = [UIView tj_WithSuperView:contentImageView];
    lineView.backgroundColor = UIColor_RGB(228, 228, 236);
    [lineView makeConstraints:^(MASConstraintMaker *make) {
        make.left.right.equalTo(instructionsLable);
        make.top.equalTo(codeTextField.bottom).offset(2);
        make.height.offset(1);
    }];
    
    XLButton *commitButton = [XLButton buttonWithSuperView:self.view fontSize:15 color:[UIColor whiteColor] title:@"提交"];
    [commitButton setBackgroundImage:[UIImage imageNamed:@"gradientButtonBg"] forState:UIControlStateNormal];
    [commitButton shearRoundedCornersWithRadiu:ZOOM5(45) * 0.5];
    [commitButton addTarget:self action:@selector(didClickCommitButton) forControlEvents:UIControlEventTouchUpInside];
    [commitButton makeConstraints:^(MASConstraintMaker *make) {
        make.left.right.equalTo(instructionsLable);
        make.height.offset(ZOOM5(45));
        make.top.equalTo(lineView.bottom).offset(H(46));
    }];
    
    self.commitButton = commitButton;
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(keyboardWillShow:) name:UIKeyboardWillShowNotification object:nil];
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(textFieldDidChange:) name:UITextFieldTextDidChangeNotification object:nil];
}
    
- (void)dealloc {
    [[NSNotificationCenter defaultCenter] removeObserver:self name:UIKeyboardWillShowNotification object:nil];
    [[NSNotificationCenter defaultCenter] removeObserver:self name:UITextFieldTextDidChangeNotification object:nil];
}
    
- (void)keyboardWillShow:(NSNotification *)notification {
    NSDictionary *userInfo = [notification userInfo];
    NSValue *aValue = [userInfo objectForKey:UIKeyboardFrameEndUserInfoKey];
    CGRect keyboardRect = [aValue CGRectValue];
    self.keyboardH = keyboardRect.size.height;
    [self.contentScrollView setContentOffset:CGPointMake(0, (self.keyboardH - (KScreenHeight - self.commitButton.tj_y - self.commitButton.tj_height - 3))) animated:YES];
}
    
- (void)textFieldDidChange:(NSNotification *)notification {
    UITextField *textField = notification.object;
    if (textField != self.codeTextField) return;
    
    NSDictionary *attributes = @{NSKernAttributeName:@5};
    self.codeTextField.attributedText = [[NSAttributedString alloc] initWithString:textField.text attributes:attributes];
}
    
- (void)textFieldDidEndEditing:(UITextField *)textField {
    [self.contentScrollView setContentOffset:CGPointMake(0, 0) animated:YES];
    
}
    
#pragma mark - UITextFieldDelegate
- (BOOL)textField:(UITextField *)textField shouldChangeCharactersInRange:(NSRange)range replacementString:(NSString *)string {
    NSInteger existedLength = textField.text.length;
    NSInteger selectedLength = range.length;
    NSInteger replaceLength = string.length;
    NSInteger currentLengh = existedLength - selectedLength + replaceLength;
    if (string == 0) return YES;
    if ([string isEqualToString:@"\n"]) {
        [textField resignFirstResponder];
        return NO;
    } else if (textField == _codeTextField && currentLengh > 6) {
        return NO;
    } else {
        return YES;
    }
}
    
- (void)tapSCrollView {
    [self.view endEditing:YES];
}
    
- (void)didClickCommitButton{
    [self tapSCrollView];
    
    NSString *code = self.codeTextField.text;
    
    if (code.length == 0) {
        [self showMessageAutoHide:@"请输入验证码"];
        return;
    }
    
    if (code.length != 6) {
        [self showMessageAutoHide:@"请输入6位数验证码"];
        return;
    }
    
    [TJNetworkTool requestWithUrl:@"User.SubWechat" parameters:@{@"Code": code} success:^(id data) {
        [self showMessageAutoHide:data[@"msg"]];
    } failure:^(id error) {}];
    
}
    
- (UIStatusBarStyle)preferredStatusBarStyle {
    return UIStatusBarStyleLightContent;
}
    
    
    @end
