

//
//  QHZGuideController.m
//  qhz
//
//  Created by lei wei on 2017/7/27.
//  Copyright © 2017年 qhz. All rights reserved.
//

#import "QHZGuideController.h"
#import "JYMaskView.h"

@interface QHZGuideController ()

@property (weak, nonatomic) JYMaskView *tj_maskView;

@end

@implementation QHZGuideController


ControllerPresentationCustom


- (void)viewDidLoad {
    [super viewDidLoad];
    [self addSubView];
}

- (void)addSubView {
    self.view.backgroundColor = [UIColor clearColor];
    
    if ([self.controllerName isEqualToString:@"XLTreasureHuntController"]){
   
        UIImageView *starGuideView = [UIImageView tj_WithSuperView:self.tj_maskView];
        UIImage *starGuideImage = [UIImage imageNamed:@"guide_concent"];
        starGuideView.image = starGuideImage;
        [starGuideView makeConstraints:^(MASConstraintMaker *make) {
            make.centerX.offset(0);
            make.size.equalTo(starGuideImage.size);
            make.top.offset(H(197));
        }];
 
        UIImageView *knowImageView = [UIImageView tj_WithSuperView:self.tj_maskView];
        UIImage *knowImage = [UIImage imageNamed:@"guide_btn"];
        knowImageView.image = knowImage;
        [knowImageView makeConstraints:^(MASConstraintMaker *make) {
            make.centerX.offset(0);
            make.size.equalTo(knowImage.size);
            make.top.equalTo(starGuideView.bottom).offset(20);
        }];
        [knowImageView addTapGesturesWithTarget:self action:@selector(tapKnowView)];
    }
 
}

- (JYMaskView *)tj_maskView {
    if (!_tj_maskView) {
        JYMaskView *tj_maskView = [[JYMaskView alloc] initWithFrame:self.view.bounds];
        tj_maskView.maskColor = [[UIColor blackColor] colorWithAlphaComponent:0.6];
        _tj_maskView = tj_maskView;
        [self.view addSubview:tj_maskView];
    }
    return _tj_maskView;
}

- (void)tapKnowView {
    [self dismissViewControllerAnimated:NO completion:nil];
    if ([self.delegate respondsToSelector:@selector(dismissGuideController:maskView:)]) {
        [self.delegate dismissGuideController:self maskView:self.tj_maskView];
    }
}

@end
