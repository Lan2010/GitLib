//
//  XLStrategyController.m
//  starChain
//
//  Created by rlx on 2018/6/29.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import "XLStrategyController.h"

@interface XLStrategyController ()

@end

@implementation XLStrategyController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    UIImage *image = [UIImage imageNamed:@"StrategyInstructions"];
    
    UIScrollView *scrollView = [[UIScrollView alloc] init];
    [self.view addSubview:scrollView];
    [scrollView makeConstraints:^(MASConstraintMaker *make) {
        make.top.equalTo(self.tj_navigationBar.bottom);
        make.left.right.bottom.offset(0);
    }];
    
    UIImageView *imageView = [[UIImageView alloc] initWithImage:image];
    [scrollView addSubview:imageView];
    [imageView makeConstraints:^(MASConstraintMaker *make) {
        make.top.left.right.bottom.offset(0);
        make.width.equalTo(KScreenWidth);
        make.height.offset(image.size.height * KScreenWidth / image.size.width);
    }];
}



@end
