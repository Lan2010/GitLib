//
//  XLPagingHeaderView.h
//  starChain
//
//  Created by rlx on 2018/7/12.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface XLPagingHeaderView : UIView

@property (assign, nonatomic) BOOL isSeparatedLine;
@property (assign, nonatomic) NSInteger currentIndex;
@property (copy, nonatomic) void (^didClickButtonBlock)(UIButton *button);

- (instancetype)initWithFrame:(CGRect)frame titles:(NSArray *)titles separatedLine:(BOOL)separatedLine;



@end
