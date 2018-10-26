//
//  XLReadNameCell.h
//  starChain
//
//  Created by rlx on 2018/6/15.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface XLReadNameCell : UICollectionViewCell

@property (strong, nonatomic) NSArray *models;
@property (strong, nonatomic) NSDictionary *dict;

@property (assign, nonatomic) BOOL state;
@property (assign, nonatomic) int starCount;


@end
