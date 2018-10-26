//
//  XLTextFieldView.h
//  starChain
//
//  Created by rlx on 2018/6/11.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface XLTextFieldView : UIView

@property (weak, nonatomic) UITextField *textField;
@property (weak, nonatomic) UIButton *rightButton;

- (instancetype)initWithIconName:(NSString *)iconName placeHolder:(NSString *)placeHolder;


@end
