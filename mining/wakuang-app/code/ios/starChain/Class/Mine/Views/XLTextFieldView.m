//
//  XLTextFieldView.m
//  starChain
//
//  Created by rlx on 2018/6/11.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import "XLTextFieldView.h"

@interface XLTextFieldView()

@property (copy, nonatomic) NSString *iconName;
@property (copy, nonatomic) NSString *placeHolder;

@end


@implementation XLTextFieldView

- (instancetype)initWithIconName:(NSString *)iconName placeHolder:(NSString *)placeHolder{
    self = [super initWithFrame:CGRectZero];
    if (self) {
        self.placeHolder = placeHolder;
        self.iconName = iconName;
        [self addSubView];
        self.backgroundColor = [[UIColor blackColor] colorWithAlphaComponent:0.47];
        [self shearRoundedCornersWithRadiu:ZOOM5(45) * 0.5];
    }
    return self;
}

- (void)addSubView {
    
    self.backgroundColor = [UIColor whiteColor];
    UIImage *iconImage = [UIImage imageNamed:self.iconName];
    UIImageView *iconImageView = [UIImageView imageViewWithImageName:self.iconName superView:self];
    [iconImageView makeConstraints:^(MASConstraintMaker *make) {
        make.left.offset(12);
        make.centerY.offset(0);
        make.size.equalTo(iconImage.size);
    }];
    
    UITextField *textField = [UITextField tj_WithSuperView:self];
    textField.font = [UIFont systemFontOfSize:14];
    NSMutableAttributedString *placeholder = [[NSMutableAttributedString alloc] initWithString:self.placeHolder];
    [placeholder addAttribute:NSForegroundColorAttributeName value:UIColor_Hex(0xB6B6B6) range:NSMakeRange(0, self.placeHolder.length)];
    textField.attributedPlaceholder = placeholder;
    textField.borderStyle = UITextBorderStyleNone;
    textField.textColor = [UIColor whiteColor];
    [textField makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(iconImageView.right).offset(6);
        make.right.offset(ZOOM5(-50));
        make.top.offset(2);
        make.bottom.offset(-2);
    }];
    
    UIButton *rightButton = [UIButton tj_WithSuperView:self];
    rightButton.hidden = YES;
    [rightButton addTarget:self action:@selector(didClickRightButton:) forControlEvents:UIControlEventTouchUpInside];
    [rightButton setImage:[UIImage imageNamed:@"bkj-icon"] forState:UIControlStateNormal];
    [rightButton setImage:[UIImage imageNamed:@"kj-icon"] forState:UIControlStateSelected];
    [rightButton makeConstraints:^(MASConstraintMaker *make) {
        make.right.offset(ZOOM5(-12));
        make.centerY.offset(0);
        make.height.width.offset(33);
    }];
    
    self.rightButton = rightButton;
    self.textField = textField;
}

- (void)didClickRightButton:(UIButton *)button {
    button.selected = !button.selected;
    _textField.secureTextEntry = !button.selected;
}


@end
