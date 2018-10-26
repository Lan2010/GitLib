//
//  XLTaskModel.h
//  starChain
//
//  Created by rlx on 2018/6/23.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface XLTaskModel : NSObject

/** 城市 */
@property (copy, nonatomic) NSString *city;
/** 结束时间 */
@property (copy, nonatomic) NSString *end_time;
/** 任务名字 */
@property (copy, nonatomic) NSString *task_name;
/** 标志的图片 */
@property (copy, nonatomic) NSString *task_lcon;
/** 任务ID */
@property (strong, nonatomic) NSNumber *task_id;
/** 地址ID */
@property (copy, nonatomic) NSString *tl_id;
/** 唯一标识 */
@property (strong, nonatomic) NSNumber *order_no;
/** 任务范围 */
@property (strong, nonatomic) NSNumber *task_radius;
/** 关键字 */
@property (copy, nonatomic) NSString *keyword;
/** 开始时间 */
@property (copy, nonatomic) NSString *begin_time;
/** 麦当劳(南山餐厅) 详细名称 */
@property (copy, nonatomic) NSString *name;
/** 地址 */
@property (copy, nonatomic) NSString *place;
/** 纬度 */
@property (copy, nonatomic) NSString *lat;
/** 经度 */
@property (copy, nonatomic) NSString *lng;
/** 城市代码 */
@property (copy, nonatomic) NSString *city_code;
/** 状态 1 接受了 */
@property (copy, nonatomic) NSString *status;
/** 距离 */
@property (copy, nonatomic) NSString *distance;
/** 任务的星星 */
@property (copy, nonatomic) NSString *task_award;

@end
