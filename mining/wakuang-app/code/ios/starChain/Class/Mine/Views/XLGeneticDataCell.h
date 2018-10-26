//
//  XLGeneticDataCell.h
//  starChain
//
//  Created by rlx on 2018/7/17.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import "XLLineView.h"

@interface XLGeneticDataCell : XLLineView

@property (weak, nonatomic) UILabel *textLable;
@property (weak, nonatomic) UILabel *detailsLable;

@property (copy, nonatomic) void (^tapDetailsLableBlock)(UILabel *);

@end

@interface XLGeneticDataInputCell : XLLineView

@property (weak, nonatomic) UILabel *textLable;
@property (weak, nonatomic) UITextField *textField;
@property (copy, nonatomic) NSString *placeholder;

@end

