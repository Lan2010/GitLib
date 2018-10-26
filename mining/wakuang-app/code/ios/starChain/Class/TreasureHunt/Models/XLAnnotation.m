//
//  XLAnnotation.m
//  starChain
//
//  Created by rlx on 2018/6/23.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import "XLAnnotation.h"

@implementation XLAdvertisingStarAnnotation


@end

@implementation XLStarAnnotation

- (NSString *)description {
    return [NSString stringWithFormat:
            @"\n adImageUrl = %@,\n adLink = %@,\n advertName = %@,\n advertisement_describe = %@,\n annotation.starCount = %@, \n annotation.linkStarPoint = %@,\n annotation.adLink = %@,\n annotation.starType = %ld", self.adImageUrl, self.adLink, self.advertName, self.advertRemark, self.starCount, self.linkStarPoint, self.adLink, self.starType];
}


@end

@implementation XLTasklocationAnnotation


@end

@implementation XLMeLocationAnnotation


@end


