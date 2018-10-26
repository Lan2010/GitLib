//
//  XLBaseController.h
//  starChain
//
//  Created by rlx on 2018/6/6.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "XLNavigationBar.h"

typedef NS_ENUM(NSInteger, ControllerShowStyle) {
    ControllerShowStylePush,
    ControllerShowStyleModal
};

@interface XLBaseController : UIViewController

@property (strong, nonatomic) UIColor *titleColor;
@property (strong, nonatomic) XLNavigationBar *tj_navigationBar;
@property (strong, nonatomic) UINavigationItem *tj_navigationItem;
@property (assign, nonatomic) ControllerShowStyle showStyle;

@property (assign, nonatomic) BOOL navBarAlpha;
@property (assign, nonatomic) BOOL hiddenNavigationBar;
@property (assign, nonatomic) BOOL hiddenLineView;


@end
