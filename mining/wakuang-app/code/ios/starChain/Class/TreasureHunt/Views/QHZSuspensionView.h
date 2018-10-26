//
//  QHZSuspensionView.h
//  qhz
//
//  Created by lei wei on 2017/12/7.
//  Copyright © 2017年 qhz. All rights reserved.
//

#import <UIKit/UIKit.h>

typedef NS_ENUM(NSUInteger, QHZSuspensionViewLeanType) {
    QHZSuspensionViewLeanTypeHorizontal,
    QHZSuspensionViewLeanTypeEachSide
};

@interface QHZSuspensionView : UIButton

@property (nonatomic, assign) QHZSuspensionViewLeanType leanType;


@end
