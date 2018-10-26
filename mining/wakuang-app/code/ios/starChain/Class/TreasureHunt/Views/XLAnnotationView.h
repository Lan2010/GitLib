//
//  XLAnnotationView.h
//  starChain
//
//  Created by rlx on 2018/6/23.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import <BaiduMapAPI_Map/BMKPinAnnotationView.h>
#import "XLAnnotation.h"

@class XLStarAnnotationView, XLAdvertisingStarAnnotationView;

@protocol XLAnnotationViewDelegate <NSObject>

-(void)didSelectStarAnnotationView:(BMKAnnotationView *)annotationView;

@end


@interface XLStarAnnotationView : BMKAnnotationView

@property (strong, nonatomic) XLStarAnnotation *starAnannotation;
@property (strong, nonatomic) XLAdvertisingStarAnnotation *advertisingStarAnnotation;

@property (weak, nonatomic) id <XLAnnotationViewDelegate> delegate;

@property (strong, nonatomic, readonly) UIImage *iconImage;


@end


@interface XLTasklocationAnnotationView : BMKAnnotationView

@property (strong, nonatomic) XLTasklocationAnnotation *tasklocationAnnotation;
@property (weak, nonatomic) id <XLAnnotationViewDelegate> delegate;



@end

@interface XLMelocationAnnotationView : BMKAnnotationView

@property (strong, nonatomic) XLMeLocationAnnotation *meLocationAnnotation;
@property (assign, nonatomic) BOOL showPrompt;


@end
 

