//
//  XLTiedCardController.m
//  starChain
//
//  Created by rlx on 2018/6/12.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import "XLTiedCardController.h"
#import "XLLineView.h"
#import "XLButton.h"

@interface XLTiedCardController ()<UITextFieldDelegate>

@property (weak, nonatomic) UITextField *banktTextField;
@property (weak, nonatomic) UITextField *cardTextField;

@end

@implementation XLTiedCardController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    self.tj_navigationItem.title = @"绑定银行卡";
 
    
    XLLineView *cardContetView = [[XLLineView alloc] initWithTopLine:NO bottomLine:YES];
    [self.view addSubview:cardContetView];
    [cardContetView makeConstraints:^(MASConstraintMaker *make) {
        make.left.right.offset(0);
        make.top.equalTo(self.tj_navigationBar.bottom).offset(10);
        make.height.offset(44);
    }];
 
    UILabel *cardLable = [UILabel lableWithSuperView:cardContetView fontSize:16 color:UIColor_Hex(0x303030) title:@"银行卡号" textAlignment:NSTextAlignmentLeft];
    [cardLable makeConstraints:^(MASConstraintMaker *make) {
        make.width.offset(70);
        make.left.offset(14);
        make.centerY.offset(0);
    }];
    
    UITextField *cardTextField = [self addTextFieldWithPlaceholder:@"请输入银行卡号"];
    cardTextField.keyboardType = UIKeyboardTypeNumberPad;
    [cardContetView addSubview:cardTextField];
    [cardTextField makeConstraints:^(MASConstraintMaker *make) {
        make.centerY.offset(0);
        make.left.equalTo(cardLable.right).offset(20);
        make.height.offset(40);
        make.right.offset(-14);
    }];

    XLButton *commitButton = [XLButton buttonWithSuperView:self.view fontSize:15 color:[UIColor whiteColor] title:@"提交"];
    [commitButton setBackgroundImage:[UIImage imageNamed:@"gradientButtonBg"] forState:UIControlStateNormal];
    [commitButton shearRoundedCornersWithRadiu:ZOOM5(45) * 0.5];
    [commitButton addTarget:self action:@selector(didClickCommitButton) forControlEvents:UIControlEventTouchUpInside];
    [commitButton makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(cardLable);
        make.right.equalTo(cardTextField);
        make.height.offset(ZOOM5(45));
        make.top.equalTo(cardContetView.bottom).offset(60);
    }];
    
    cardTextField.delegate = self;
 
    self.cardTextField = cardTextField;
}

- (void)didClickCommitButton {
    
    NSString *bankCardNo = [self.cardTextField.text replacingEmptyString];
    
    TJLog(@"bankCardNo = %@", bankCardNo);
 
    
    if (!bankCardNo.length) {
        [self showMessageAutoHide:@"请输入银行卡号"];
        return;
    }
    
    [self.view endEditing:YES];
    
    [TJNetworkTool requestWithUrl:@"Real.AddBank" parameters:@{@"bankCardNo": bankCardNo,} success:^(id data) {
        [self showMessageAutoHide:data[@"msg"]];
        
        if ([data[@"code"] intValue] == 0) {
            dispatch_after(dispatch_time(DISPATCH_TIME_NOW, (int64_t)(1 * NSEC_PER_SEC)), dispatch_get_main_queue(), ^{
                [self.navigationController popViewControllerAnimated:YES];
            });
        }
    } failure:^(id error) {
        
    }];
    
}

- (BOOL)textField:(UITextField *)textField shouldChangeCharactersInRange:(NSRange)range replacementString:(NSString *)string {
    NSInteger existedLength = textField.text.length;
    NSInteger selectedLength = range.length;
    NSInteger replaceLength = string.length;
    NSInteger currentLengh = existedLength - selectedLength + replaceLength;
    if (textField == _cardTextField && currentLengh > 19) {
        return NO;
    } else {
        return YES;
    }
}

- (UITextField *)addTextFieldWithPlaceholder:(NSString *)placeholder {
    UITextField *textField = [[UITextField alloc] init];
    textField.font = [UIFont systemFontOfSize:16];
    textField.clearButtonMode = UITextFieldViewModeWhileEditing;
    textField.delegate = self;
    textField.attributedPlaceholder = [[NSAttributedString alloc] initWithString:placeholder attributes:@{NSForegroundColorAttributeName: UIColor_Hex(0xa0a0a0)}];
    return textField;
}


- (void)touchesBegan:(NSSet<UITouch *> *)touches withEvent:(UIEvent *)event {
    [self.view endEditing:YES];
}

@end
