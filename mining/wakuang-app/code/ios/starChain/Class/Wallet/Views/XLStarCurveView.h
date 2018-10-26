//
//  XLStarCurveView.h
//  starChain
//
//  Created by rlx on 2018/7/9.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface XLStarCurveView : UIView

@property (strong, nonatomic) NSArray *vaules;
@property (assign, nonatomic) BOOL showStraightline;

@property (assign, nonatomic, readonly) BOOL noGrowth;


@end
