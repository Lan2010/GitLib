//
//  UITableViewCell+rasterize.h
//  qhz
//
//  Created by 夏铁军 on 16/12/25.
//  Copyright © 2016年 qhz. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface UITableViewCell (rasterize)

- (void)rasterizeAndOffScreenRendering;

@end

@interface UICollectionViewCell (rasterize)

- (void)rasterizeAndOffScreenRendering;

@end

