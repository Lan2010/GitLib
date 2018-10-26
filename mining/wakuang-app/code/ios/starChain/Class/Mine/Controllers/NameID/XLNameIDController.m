//
//  XLNameIDController.m
//  starChain
//
//  Created by rlx on 2018/6/12.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import "XLNameIDController.h"
#import "XLLineView.h"
#import "XLButton.h"


@interface XLNameIDController ()<UITextFieldDelegate>

@property (weak, nonatomic) UITextField *nameTextField;
@property (weak, nonatomic) UITextField *IDTextField;


@end

@implementation XLNameIDController

- (void)viewDidLoad {
    [super viewDidLoad];
        
    XLLineView *nameContetView = [[XLLineView alloc] initWithTopLine:NO bottomLine:YES];
    [self.view addSubview:nameContetView];
    [nameContetView makeConstraints:^(MASConstraintMaker *make) {
        make.left.right.offset(0);
        make.top.equalTo(self.tj_navigationBar.bottom).offset(10);
        make.height.offset(44);
    }];
    
    XLLineView *IDContetView = [[XLLineView alloc] initWithTopLine:NO bottomLine:YES];
    [self.view addSubview:IDContetView];
    [IDContetView makeConstraints:^(MASConstraintMaker *make) {
        make.left.right.height.equalTo(nameContetView);
        make.top.equalTo(nameContetView.bottom);
    }];
    
    UILabel *nameLable = [UILabel lableWithSuperView:nameContetView fontSize:16 color:UIColor_Hex(0x303030) title:@"真实姓名" textAlignment:NSTextAlignmentLeft];
    [nameLable makeConstraints:^(MASConstraintMaker *make) {
        make.width.offset(70);
        make.left.offset(14);
        make.centerY.offset(0);
    }];
    
    UITextField *nameTextField = [self addTextFieldWithPlaceholder:@"请输入真实姓名"];
    [nameContetView addSubview:nameTextField];
    [nameTextField makeConstraints:^(MASConstraintMaker *make) {
        make.centerY.offset(0);
        make.left.equalTo(nameLable.right).offset(16);
        make.height.offset(40);
        make.right.offset(-14);
    }];
    
    UILabel *IDLable = [UILabel lableWithSuperView:IDContetView fontSize:16 color:UIColor_Hex(0x303030) title:@"身份证号" textAlignment:NSTextAlignmentLeft];
    [IDLable makeConstraints:^(MASConstraintMaker *make) {
        make.left.width.equalTo(nameLable);
        make.centerY.offset(0);
    }];
    
    UITextField *IDTextField = [self addTextFieldWithPlaceholder:@"请输入身份证号"];
    [IDContetView addSubview:IDTextField];
    IDTextField.keyboardType = UIKeyboardTypeASCIICapable;
    [IDTextField makeConstraints:^(MASConstraintMaker *make) {
        make.centerY.offset(0);
        make.left.equalTo(IDLable.right).offset(20);
        make.height.offset(40);
        make.right.offset(-14);
    }];
    
    XLButton *commitButton = [XLButton buttonWithSuperView:self.view fontSize:15 color:[UIColor whiteColor] title:@"提交"];
    [commitButton setBackgroundImage:[UIImage imageNamed:@"gradientButtonBg"] forState:UIControlStateNormal];
    [commitButton shearRoundedCornersWithRadiu:ZOOM5(45) * 0.5];
    [commitButton addTarget:self action:@selector(didClickCommitButton) forControlEvents:UIControlEventTouchUpInside];
    [commitButton makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(IDLable);
        make.right.equalTo(IDTextField);
        make.height.offset(ZOOM5(45));
        make.top.equalTo(IDContetView.bottom).offset(60);
    }];
    
    nameTextField.delegate = self;
    IDTextField.delegate = self;
    
    self.nameTextField = nameTextField;
    self.IDTextField = IDTextField;
    
}

- (void)didClickCommitButton {
    
    NSString *name = [self.nameTextField.text replacingEmptyString];
    NSString *ID = [self.IDTextField.text replacingEmptyString];
    
    if (!name.length) {
        [self showMessageAutoHide:@"请输入真实姓名"];
        return;
    }
    
    if (!ID.length) {
        [self showMessageAutoHide:@"请输入身份证"];
        return;
    }
    
    [self.view endEditing:YES];
    
    NSDictionary *parameters = @{
                                 @"name": name,
                                 @"cardID":ID
                                 };
    [TJNetworkTool requestWithUrl:@"Real.CardReal" parameters:parameters success:^(id data) {
        [self showMessageAutoHide:data[@"msg"]];
        
        if ([data[@"code"] intValue] == 0) {
            dispatch_after(dispatch_time(DISPATCH_TIME_NOW, (int64_t)(1 * NSEC_PER_SEC)), dispatch_get_main_queue(), ^{
                [self.navigationController popViewControllerAnimated:YES];
            });
        }
    } failure:^(id error) {
        
    }];
    
}

- (UITextField *)addTextFieldWithPlaceholder:(NSString *)placeholder {
    UITextField *textField = [[UITextField alloc] init];
    textField.font = [UIFont systemFontOfSize:16];
    textField.clearButtonMode = UITextFieldViewModeWhileEditing;
    textField.attributedPlaceholder = [[NSAttributedString alloc] initWithString:placeholder attributes:@{NSForegroundColorAttributeName: UIColor_Hex(0xa0a0a0)}];
    return textField;
}

- (BOOL)textField:(UITextField *)textField shouldChangeCharactersInRange:(NSRange)range replacementString:(NSString *)string {
    NSInteger existedLength = textField.text.length;
    NSInteger selectedLength = range.length;
    NSInteger replaceLength = string.length;
    NSInteger currentLengh = existedLength - selectedLength + replaceLength;
     if (string.length == 0) return YES;
     if (textField == _nameTextField && currentLengh > 10) {
        return NO;
    } else if (textField == _IDTextField && currentLengh > 18) {
        return NO;
    } else {
        return YES;
    }
}

- (void)touchesBegan:(NSSet<UITouch *> *)touches withEvent:(UIEvent *)event {
    [self.view endEditing:YES];
}

@end
