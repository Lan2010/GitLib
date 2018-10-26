//
//  XLBaseNavController.m
//  starChain
//
//  Created by rlx on 2018/6/6.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import "XLBaseNavController.h"

@interface XLBaseNavController ()

@end

@implementation XLBaseNavController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    self.navigationBar.hidden = YES;
    self.navigationBarHidden = YES;
}

- (UIViewController *)childViewControllerForStatusBarStyle {
    return self.topViewController;
}

@end
