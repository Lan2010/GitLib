//
//  XLCompleteTaskModel.h
//  starChain
//
//  Created by rlx on 2018/6/12.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface XLCompleteTaskModel : NSObject

@property (copy, nonatomic) NSString *task_name;
@property (copy, nonatomic) NSString *end_time;
@property (copy, nonatomic) NSString *starPoint;


@end

@interface XLStarRecordModel : NSObject

@property (copy, nonatomic) NSString *operStarPoint;
@property (copy, nonatomic) NSString *createTime;
@property (copy, nonatomic) NSString *recordsType;

@end

@interface XLStarRankingModel : NSObject

@property (copy, nonatomic) NSString *num;
@property (copy, nonatomic) NSString *phone;
@property (copy, nonatomic) NSString *starPoint;
@property (assign, nonatomic) NSInteger row;

@end


