//
//  XLCollectionController.h
//  starChain
//
//  Created by rlx on 2018/6/6.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import "XLBaseController.h"

@interface XLCollectionController : XLBaseController <UICollectionViewDelegate, UICollectionViewDataSource>

@property (weak, nonatomic) UICollectionView *collctionView;

@end
