//
//  XLRankingContentView.m
//  starChain
//
//  Created by rlx on 2018/7/13.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import "XLRankingContentView.h"
#import "XLCompleteTaskModel.h"


@interface XLRankingContentView()

@property (weak, nonatomic) UILabel *nameLable;
@property (weak, nonatomic) UILabel *starCountLable;

@end


@implementation XLRankingContentView

- (instancetype)initWithFrame:(CGRect)frame {
    if (self = [super initWithFrame:frame]) {
        [self addSubView];
    }
    return self;
}

- (void)addSubView {
    
    UILabel *nameLable = [UILabel lableWithSuperView:self fontSize:12 color:[UIColor whiteColor] title:@"" textAlignment:NSTextAlignmentCenter];
    [nameLable makeConstraints:^(MASConstraintMaker *make) {
        make.left.right.offset(0);
        make.centerY.offset(-H(10));
    }];
    
    UILabel *starCountLable = [UILabel lableWithSuperView:self fontSize:15 color:[UIColor whiteColor] title:@"" textAlignment:NSTextAlignmentCenter];
    [starCountLable makeConstraints:^(MASConstraintMaker *make) {
        make.centerY.offset(H(10));
        make.left.right.offset(0);
    }];
    
    self.nameLable = nameLable;
    self.starCountLable = starCountLable;
}

- (void)setModel:(XLStarRankingModel *)model {
    _model = model;
    self.nameLable.text = model.phone;
    self.starCountLable.text = model.starPoint;
}

 
@end
