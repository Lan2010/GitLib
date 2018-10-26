//
//  UITableViewCell+rasterize.m
//  qhz
//
//  Created by 夏铁军 on 16/12/25.
//  Copyright © 2016年 qhz. All rights reserved.
//

#import "UITableViewCell+rasterize.h"

@implementation UITableViewCell (rasterize)

- (void)rasterizeAndOffScreenRendering {
    self.layer.shouldRasterize = YES;
    self.layer.rasterizationScale = [UIScreen mainScreen].scale;
    self.layer.drawsAsynchronously = YES;
}

@end

@implementation UICollectionViewCell (rasterize)

- (void)rasterizeAndOffScreenRendering {
    self.layer.shouldRasterize = YES;
    self.layer.rasterizationScale = [UIScreen mainScreen].scale;
    self.layer.drawsAsynchronously = YES;
}

@end




