//
//  XLMainController.m
//  starChain
//
//  Created by rlx on 2018/6/6.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import "XLMainController.h"
#import "XLBaseNavController.h"
#import "XLTabBar.h"
#import "XLTaskController.h"
#import "XLLoginController.h"
#import "XLWalletController.h"


@interface XLMainController ()<UITabBarControllerDelegate>

@property (strong, nonatomic) NSArray *tabItemConfigurations;
@property (assign, nonatomic) CGSize iconSize;
@property (assign, nonatomic) CGFloat topMagrin;

@end


@implementation XLMainController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    [self setingTabBar];
    [self addChildViewController]; 
}

- (BOOL)tabBarController:(UITabBarController *)tabBarController shouldSelectViewController:(UIViewController *)viewController {
    XLBaseNavController *baseNav = (XLBaseNavController *)viewController;
    
    if (![USERDEFAULTS boolForKey:login]) {
        
        if ([baseNav.childViewControllers.firstObject isKindOfClass:[XLTaskController class]]) {
 
         XLLoginController *loginController = (XLLoginController *)[self presentViewControllerWithName:@"XLLoginController" title:nil];
           [loginController setLoginSuccess:^{
            self.selectedIndex = 1;
           }];
            return NO;
        } else if ([baseNav.childViewControllers.firstObject isKindOfClass:[XLWalletController class]]) {
            
            XLLoginController *loginController = (XLLoginController *)[self presentViewControllerWithName:@"XLLoginController" title:nil];
            [loginController setLoginSuccess:^{
                self.selectedIndex = 2;
            }];
            return NO;
        }
        
    }
    return YES;
}

- (void)setingTabBar {
    self.tabBar.translucent = NO;
    self.tabBar.tintColor = UIColor_Hex(0x594176);
    self.tabBar.barTintColor = [UIColor whiteColor];
    self.delegate = self;
    self.tabBar.subviews.firstObject.subviews.firstObject.alpha = 0.8;
    [[UITabBarItem appearance] setTitleTextAttributes:@{NSForegroundColorAttributeName:UIColor_Hex(0xb5b6b8)} forState:UIControlStateNormal];
    [[UITabBarItem appearance] setTitleTextAttributes:@{NSForegroundColorAttributeName:UIColor_Hex(0x594176)} forState:UIControlStateSelected];
}

#pragma mark - 添加所有的子控制器
- (void)addChildViewController {
    [self.tabItemConfigurations enumerateObjectsUsingBlock:^(NSDictionary *obj, NSUInteger idx, BOOL * _Nonnull stop) {
        [self addChildViewController:[[NSClassFromString(obj[@"controllerName"]) alloc] init] image:obj[@"iconName"] title:obj[@"title"]];
    }];
}

#pragma mark - 添加, 设置子控制器
- (void)addChildViewController:(UIViewController *)childController image:(NSString *)image title:(NSString *)title {
    XLBaseNavController *navigationController = [[XLBaseNavController alloc] initWithRootViewController: childController];
    navigationController.view.backgroundColor = [UIColor whiteColor];
    [self addChildViewController: navigationController];
    childController.title = title;
    childController.navigationItem.title = title;
    childController.tabBarItem.image = [UIImage imageNamed:image];
    childController.tabBarItem.selectedImage = [UIImage imageNamed:[NSString stringWithFormat:@"%@_selected", image]];
 }

- (NSArray *)tabItemConfigurations {
    if (!_tabItemConfigurations) {
        _tabItemConfigurations = @[
                                   @{
                                       @"controllerName": @"XLTreasureHuntController",
                                       @"iconName": @"home",
                                       @"title": @"寻宝"
                                       },
                                   @{
                                       @"controllerName": @"XLTaskController",
                                       @"iconName": @"task",
                                       @"title": @"任务"
                                       },
                                   @{
                                       @"controllerName": @"XLWalletController",
                                       @"iconName": @"wallet",
                                       @"title": @"钱包"
                                       },
                                   @{
                                       @"controllerName": @"XLMineController",
                                       @"iconName": @"mine",
                                       @"title": @"我的"
                                       },
                                   
                                   ];
        
    }
    return _tabItemConfigurations;
}

@end
