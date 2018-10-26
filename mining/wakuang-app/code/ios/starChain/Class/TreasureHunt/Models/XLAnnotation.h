//
//  XLAnnotation.h
//  starChain
//
//  Created by rlx on 2018/6/23.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import <BaiduMapAPI_Map/BMKPointAnnotation.h>

typedef NS_ENUM(NSInteger, XLStarType) {
    XLStarTypeTask,
    XLStarTypeAD,
    XLStarTypeRandom
};

@interface XLAdvertisingStarAnnotation : BMKPointAnnotation

@property (copy, nonatomic) NSString *advertisementType;
@property (copy, nonatomic) NSString *imageUrl;
@property (copy, nonatomic) NSString *imageName;
@property (copy, nonatomic) NSString *popText;
@property (copy, nonatomic) NSString *text;
@property (copy, nonatomic) NSString *advertName;

@property (strong, nonatomic) NSArray *alertImageUrls;

@property (copy, nonatomic) NSString *advertId;
@property (copy, nonatomic) NSString *starPoint;


@end

@interface XLStarAnnotation : BMKPointAnnotation

@property (copy, nonatomic) NSString *recordsType;
@property (copy, nonatomic) NSString *text;
@property (copy, nonatomic) NSString *recordToken;
@property (copy, nonatomic) NSString *adLink;
@property (copy, nonatomic) NSString *starCount;
@property (copy, nonatomic) NSString *adIcon;
@property (copy, nonatomic) NSString *adImageUrl;
@property (copy, nonatomic) NSString *advertRemark;
@property (copy, nonatomic) NSString *advertName;
@property (copy, nonatomic) NSString *createTime;
@property (copy, nonatomic) NSString *taskId;
@property (copy, nonatomic) NSString *linkStarPoint;
@property (strong, nonatomic) UIImage *adImage;

@property (assign, nonatomic) XLStarType starType;

@property (copy, nonatomic) NSString *advertisementType;
@property (copy, nonatomic) NSString *advertId;


@end

@interface XLTasklocationAnnotation : BMKPointAnnotation

@property (copy, nonatomic) NSString *imageUrl;
@property (copy, nonatomic) NSString *name;
@property (assign, nonatomic) double task_radius;



@end

@interface XLMeLocationAnnotation : BMKPointAnnotation


@end





