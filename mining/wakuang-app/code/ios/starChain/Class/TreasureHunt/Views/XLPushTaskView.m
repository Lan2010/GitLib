//
//  XLself.m
//  starChain
//
//  Created by rlx on 2018/7/16.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import "XLPushTaskView.h"
#import "XLTaskView.h"
#import "XLTaskModel.h"

@implementation XLPushTaskView

- (instancetype)initWithFrame:(CGRect)frame model:(XLTaskModel *)model {
    if ([super initWithFrame:frame]) {

        self.backgroundColor = UIColor_Hex(0xe5e5e5);
        self.userInteractionEnabled = YES;
        self.layer.cornerRadius = 5;
        self.layer.masksToBounds = NO;
        self.layer.shadowColor = [UIColor colorWithRed:105.0f/255.0f green:99.0f/255.0f blue:192.0f/255.0f alpha:0.26f].CGColor;
        self.layer.shadowOpacity = 0.5;
        self.layer.shadowRadius = 5;
        self.clipsToBounds = YES;
        
        XLTaskView *taskContentView = [[XLTaskView alloc] initWithFrame:CGRectMake(0, 0, self.tj_width, 100)];
        taskContentView.backgroundColor = [UIColor whiteColor];
        [self addSubview:taskContentView];
        taskContentView.model = model;
        
        UIView *instructionsView = [[UIView alloc] initWithFrame:CGRectMake(0, CGRectGetMaxY(taskContentView.frame) + 0.5, self.tj_width, 50 - 0.5)];
        [self addSubview:instructionsView];
        instructionsView.backgroundColor = [UIColor whiteColor];
        
        UILabel *taskNameLable = [UILabel lableWithSuperView:instructionsView fontSize:15 color:UIColor_Hex(0x2f1959) title:@"您是否接受该任务？" textAlignment:NSTextAlignmentLeft];
        [taskNameLable makeConstraints:^(MASConstraintMaker *make) {
            make.left.offset(20);
            make.centerY.offset(0);
        }];
        [instructionsView addSubview:taskNameLable];
        
        UIButton *determineButton = [UIButton tj_WithSuperView:instructionsView];
        determineButton.tag = 1000;
        [determineButton setImage:[UIImage imageNamed:@"btn-ringt"] forState:UIControlStateNormal];
        [determineButton addTarget:self action:@selector(didClickButton:) forControlEvents:UIControlEventTouchUpInside];
        [determineButton makeConstraints:^(MASConstraintMaker *make) {
            make.centerY.offset(0);
            make.width.height.offset(30);
            make.right.offset(-15);
        }];
        
        UIButton *cancelButton = [UIButton tj_WithSuperView:instructionsView];
        cancelButton.tag = 1001;
        [cancelButton addTarget:self action:@selector(didClickButton:) forControlEvents:UIControlEventTouchUpInside];
        [cancelButton setImage:[UIImage imageNamed:@"btn-fault"] forState:UIControlStateNormal];
        [cancelButton makeConstraints:^(MASConstraintMaker *make) {
            make.centerY.width.height.equalTo(determineButton);
            make.right.equalTo(determineButton.left).offset(-15);
        }];
        
    }
    return self;
}

- (void)didClickButton:(UIButton *)button {
    if (self.didClickButtonBlock) self.didClickButtonBlock(button.tag - 1000);
    
}


@end
