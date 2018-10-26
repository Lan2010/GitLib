//
//  XLAlertView.m
//  starChain
//
//  Created by rlx on 2018/6/27.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import "XLAlertViewController.h"
#import "XLGradientButton.h"

@interface XLAlertViewController()

@property (copy, nonatomic) NSString *message;
@property (strong, nonatomic) NSArray *buttonTitles;
@property (strong, nonatomic) NSMutableArray <UIButton *>*buttons;
@property (strong, nonatomic) NSMutableArray <UIView *>*buttonContentViews;
@property (weak, nonatomic) UIView *maskView;
@property (weak, nonatomic) UIView *contentView;

@end

@implementation XLAlertViewController

ControllerPresentationCustom

+ (instancetype)alertViewWithMessage:(NSString *)message buttonTitles:(NSArray *)buttonTitles {
    XLAlertViewController *alertView = [[self alloc] init];
    alertView.message = message;
    alertView.buttonTitles = buttonTitles;
    [alertView addSubView];
    return alertView;
}


- (void)addSubView {
    
    self.buttons = [NSMutableArray arrayWithCapacity:self.buttonTitles.count];
    self.buttonContentViews = [NSMutableArray arrayWithCapacity:self.buttonTitles.count];
    
    UIView *maskView = [[UIView alloc] initWithFrame:self.view.bounds];
    
    [self.view addSubview:maskView];
 
    maskView.backgroundColor = [[UIColor blackColor] colorWithAlphaComponent:0];

 
    CGFloat W = ZOOM5(315);
    
    UIView *contentView = [[UIView alloc] init];
    contentView.backgroundColor = [UIColor whiteColor];
    [contentView shearRoundedCornersWithRadiu:5];
    [maskView addSubview:contentView];
 
    CGFloat textMaxW = ZOOM5(280);
    
    TJLog(@"self.message = %@", self.message);
  
    UILabel *textLable = [[UILabel alloc] init];
    textLable.text = self.message;
    textLable.textColor = [UIColor purpleColor];
    [contentView addSubview:textLable];
    textLable.numberOfLines = 9;
    textLable.textAlignment = NSTextAlignmentCenter;
    textLable.font = [UIFont systemFontOfSize:18];
    [textLable makeConstraints:^(MASConstraintMaker *make) {
        make.top.offset(32);
        make.width.offset(textMaxW);
        make.centerX.offset(0);
    }];
    
 
    for (int i = 0; i < self.buttonTitles.count; i++) {
        
        UIView *buttonContentView = [[UIView alloc] init];
        [contentView addSubview:buttonContentView];
        
        UIButton *button = [[UIButton alloc] init];
        [buttonContentView addSubview:button];
        button.titleLabel.font = [UIFont systemFontOfSize:15];
        [button makeConstraints:^(MASConstraintMaker *make) {
            make.left.right.top.bottom.offset(0);
        }];

        [button setTitle:self.buttonTitles[i] forState:UIControlStateNormal];
        [buttonContentView shearRoundedCornersWithRadiu:16];
        [button addTarget:self action:@selector(didClickButtonBlock:) forControlEvents:UIControlEventTouchUpInside];
        
        [self.buttonContentViews addObject:buttonContentView];
        [self.buttons addObject:button];

        button.tag = 1000 + i;
 
    }

    
    CGFloat buttonW = ZOOM5(108);
    CGFloat buttonY = MAX(textLable.tj_height + 32, 84);
    
    if (self.buttonTitles.count == 1) {
        
        self.buttonContentViews.firstObject.frame = CGRectMake((W - buttonW) * 0.5, buttonY, buttonW, 32);
        
    } else {
        
        CGFloat leftMagrin = ZOOM5(43);
        
        self.buttonContentViews.firstObject.frame = CGRectMake(leftMagrin, buttonY, buttonW, 32);
        self.buttonContentViews.firstObject.layer.borderColor = [UIColor_RGB(105, 99, 192) CGColor];
        self.buttonContentViews.firstObject.layer.borderWidth = 0.5;
        [self.buttons.firstObject setTitleColor:UIColor_Hex(0x6963c0) forState:UIControlStateNormal];

         self.buttonContentViews.lastObject.frame = CGRectMake(W - leftMagrin - buttonW, buttonY, buttonW,32);
         [self.buttonContentViews.lastObject addGradientLayerWithColors:@[UIColor_RGB(105, 99, 192), UIColor_RGB(172, 86, 250)] endPoint:CGPointMake(0, 1)];
         self.buttonContentViews.lastObject.layer.borderWidth = 0;
      }
    
    
    [contentView makeConstraints:^(MASConstraintMaker *make) {
        make.center.offset(0);
        make.width.offset(W);
        make.bottom.equalTo(self.buttonContentViews.firstObject).offset(20);
    }];
    
    TJLog(@"contentView = %@", contentView);
    
    self.contentView = contentView;
    
    
    
    [self.view setNeedsLayout];
    [self.view layoutIfNeeded];
    
    contentView.transform = CGAffineTransformMakeScale(0.5, 0.5);

    self.maskView = maskView;
    
}

- (void)viewWillAppear:(BOOL)animated {
    [super viewWillAppear:animated];
    
    [UIView animateWithDuration:0.25 delay:0 usingSpringWithDamping:0.65 initialSpringVelocity:0.5 options:UIViewAnimationOptionCurveEaseInOut animations:^{
        self.contentView.transform = CGAffineTransformMakeScale(1, 1);
        self.maskView.backgroundColor = [[UIColor blackColor] colorWithAlphaComponent:0.45];
    } completion:nil];
}

- (void)didClickButtonBlock:(UIButton *)button {

    if (self.didClickButtonBlock) self.didClickButtonBlock(button);
    [self dismissViewControllerAnimated:NO completion:nil];
}

@end
