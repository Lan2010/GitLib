//
//  XLStarHeaderView.h
//  starChain
//
//  Created by rlx on 2018/6/25.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import <UIKit/UIKit.h>

@class XLStarHeaderView, XLStarRecordModel;

@protocol XLStarHeaderViewDelegate <NSObject>

-(void)didCLickArrowButton:(XLStarHeaderView *)headerView button:(UIButton *)button;

@end


@interface XLStarHeaderView : UIView

@property (strong, nonatomic) NSDictionary *dict;
@property (weak, nonatomic) UIView *contentView;


@property (copy, nonatomic) NSString *starCount;

@property (weak, nonatomic) id <XLStarHeaderViewDelegate> delegate;
@property (weak, nonatomic) UILabel *starCountLable;
@property (strong, nonatomic) NSArray *starRecordDatas;



@end
