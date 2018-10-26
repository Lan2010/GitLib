//
//  XLMineCell.h
//  starChain
//
//  Created by rlx on 2018/6/14.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface XLMineCell : UITableViewCell

@property (strong, nonatomic) NSArray *datas;

@property (copy, nonatomic) void (^didClickRow)(NSInteger);
@property (copy, nonatomic) void (^didClickServiceLable)(void);


@end
