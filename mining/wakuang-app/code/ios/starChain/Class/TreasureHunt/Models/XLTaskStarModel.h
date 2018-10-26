//
//  XLStarModel.h
//  starChain
//
//  Created by rlx on 2018/6/27.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface XLAdvertisingStarModel : NSObject

/** 广告名字 */
@property (copy, nonatomic) NSString *advertName;
/** 总的点击次数 */
@property (copy, nonatomic) NSString *totalCount;
/** 总的星星数量 */
@property (copy, nonatomic) NSString *totalStarPoint;
/** 广告的类型 */
@property (copy, nonatomic) NSString *advertisementType;
/** 纬度 */
@property (copy, nonatomic) NSString *Lat;
/** 地区 */
@property (copy, nonatomic) NSString *area;
/** 城市对应的节点 */
@property (copy, nonatomic) NSString *cityCode;
/** 经度 */
@property (copy, nonatomic) NSString *Lng;
/** 城市 */
@property (copy, nonatomic) NSString *city;
/** 结束时间 */
@property (copy, nonatomic) NSString *endTime;
/** 浏览一次获取到的星星 */
@property (copy, nonatomic) NSString *onceStarPoint;
/** 点击一次获取到的星星 */
@property (copy, nonatomic) NSString *onceClickStarPoint;
/** 对应的ID */
@property (copy, nonatomic) NSString *ad_id;
/** 广告logo */
@property (copy, nonatomic) NSString *advertIcon;
/** 广告图片 */
@property (strong, nonatomic) NSArray *advertPic;
/** 开始时间 */
@property (copy, nonatomic) NSString *beginTime;
/** 广告的url */
@property (copy, nonatomic) NSString *ad_url;
/** 广告语 */
@property (copy, nonatomic) NSString *advertRemark;

@end

@interface XLTaskStarModel : NSObject

/** 任务ID */
@property (copy, nonatomic) NSString *taskId;
/** 备注 */
@property (copy, nonatomic) NSString *remark;
/** 记录token */
@property (copy, nonatomic) NSString *recordToken;
/** 任务星星数 */
@property (copy, nonatomic) NSString *operStarPoint;
/** 广告星星数 */
@property (copy, nonatomic) NSString *onceStarPoint;
/** 点击一次跳转获取到的星星 */
@property (copy, nonatomic) NSString *onceClickStarPoint;
/** 经纬度 */
@property (copy, nonatomic) NSString *longitudeAndLatitude;
/** 记录类型 */
@property (copy, nonatomic) NSString *recordsType;
/** 广告ID */
@property (copy, nonatomic) NSString *advertisementId;
/** 创建时间 */
@property (copy, nonatomic) NSString *createTime;
/** 广告的url */
@property (copy, nonatomic) NSString *ad_url;
/** 广告logo */
@property (copy, nonatomic) NSString *advertIcon;
/** 广告图片 */
@property (strong, nonatomic) NSArray *advertPic;
/** 广告语 */
@property (copy, nonatomic) NSString *advertRemark;
/** 广告名字 */
@property (copy, nonatomic) NSString *advertName;
/** 对应的ID */
@property (copy, nonatomic) NSString *ad_id;




@end
