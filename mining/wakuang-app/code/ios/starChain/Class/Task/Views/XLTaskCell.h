//
//  XLTaskCell.h
//  starChain
//
//  Created by rlx on 2018/6/14.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "XLTaskModel.h"

 
@interface XLTaskCell : UITableViewCell

@property (strong, nonatomic) XLTaskModel *model;
@property (assign, nonatomic) BOOL isStart;

@end
