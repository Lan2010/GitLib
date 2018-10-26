//
//  XLStarDetailsController.h
//  starChain
//
//  Created by rlx on 2018/7/5.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import "XLBaseController.h"
#import "XLAnnotationView.h"
#import <BaiduMapAPI_Map/BMKMapComponent.h>

@interface XLStarDetailsController : XLBaseController

@property (strong, nonatomic) XLStarAnnotation *annotation;//广告的链接, 广告图标, 广告的星星数数量, 广告的宣传图
@property (weak, nonatomic) BMKMapView *mapView;
@property (copy, nonatomic) NSString *availableStarPointString;

@end
