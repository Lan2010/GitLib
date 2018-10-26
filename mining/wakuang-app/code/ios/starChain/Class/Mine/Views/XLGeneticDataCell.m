//
//  XLGeneticDataCell.m
//  starChain
//
//  Created by rlx on 2018/7/17.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import "XLGeneticDataCell.h"

@implementation XLGeneticDataCell

- (instancetype)initWithFrame:(CGRect)frame {
    if (self = [super initWithTopLine:NO bottomLine:YES]) {
        [self addSubView];
    }
    return self;
}

- (void)addSubView {
    UILabel *textLable = [UILabel lableWithSuperView:self fontSize:16 color:UIColor_Hex(0x303030) title:@"" textAlignment:NSTextAlignmentLeft];
    [textLable makeConstraints:^(MASConstraintMaker *make) {
        make.left.offset(14);
        make.centerY.offset(0);
    }];
    
    UIButton *arrowButton = [UIButton tj_WithSuperView:self];
    [arrowButton setImage:[UIImage imageNamed:@"right"] forState:UIControlStateNormal];
    [arrowButton addTarget:self action:@selector(didClickArrowButton) forControlEvents:UIControlEventTouchUpInside];
    [arrowButton makeConstraints:^(MASConstraintMaker *make) {
        make.right.offset(-12);
        make.centerY.offset(0);
        make.width.height.offset(12);
    }];
    
    UILabel *detailsLable = [UILabel lableWithSuperView:self fontSize:16 color:UIColor_Hex(0xa0a0a0) title:@"" textAlignment:NSTextAlignmentRight];
    [detailsLable addTapGesturesWithTarget:self action:@selector(didClickArrowButton)];
    [detailsLable makeConstraints:^(MASConstraintMaker *make) {
        make.centerY.offset(0);
        make.right.equalTo(arrowButton.left).offset(-5);
    }];
    
    self.detailsLable = detailsLable;
    self.textLable = textLable;
}

- (void)didClickArrowButton {
    if (self.tapDetailsLableBlock) self.tapDetailsLableBlock(self.detailsLable);
}

@end

@implementation XLGeneticDataInputCell

- (instancetype)initWithFrame:(CGRect)frame {
    if (self = [super initWithTopLine:NO bottomLine:YES]) {
        [self addSubView];
    }
    return self;
}

- (void)addSubView {
    
    UILabel *textLable = [UILabel lableWithSuperView:self fontSize:16 color:UIColor_Hex(0x303030) title:@"" textAlignment:NSTextAlignmentLeft];
    [textLable makeConstraints:^(MASConstraintMaker *make) {
        make.width.offset((KScreenWidth - 28) * 0.3);
        make.left.offset(14);
        make.centerY.offset(0);
    }];
    
    UITextField *textField = [[UITextField alloc] init];
    textField.font = [UIFont systemFontOfSize:16];
    textField.textAlignment = NSTextAlignmentRight;
    textField.attributedPlaceholder = [[NSAttributedString alloc] initWithString:@"" attributes:@{NSForegroundColorAttributeName: UIColor_Hex(0xa0a0a0)}];
    [self addSubview:textField];
    [textField makeConstraints:^(MASConstraintMaker *make) {
        make.centerY.offset(0);
        make.right.offset(-14);
        make.height.offset(40);
        make.width.offset((KScreenWidth - 28) * 0.7);
    }];
 
    self.textLable = textLable;
    self.textField = textField;
}

- (void)setPlaceholder:(NSString *)placeholder {
    _placeholder = placeholder;
    _textField.attributedPlaceholder = [[NSAttributedString alloc] initWithString:placeholder attributes:@{NSForegroundColorAttributeName: UIColor_Hex(0xa0a0a0)}];
}

@end

