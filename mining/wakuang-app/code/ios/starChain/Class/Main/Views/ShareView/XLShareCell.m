//
//  XLShareCell.m
//  starChain
//
//  Created by rlx on 2018/6/13.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import "XLShareCell.h"

@interface XLShareCell ()

@property (copy, nonatomic) NSString *iconName;
@property (copy, nonatomic) NSString *title;

@end

@implementation XLShareCell


- (instancetype)initWithFrame:(CGRect)frame iconName:(NSString *)iconName title:(NSString *)title {
    if (self = [super initWithFrame:frame]) {
        _iconName = iconName;
        _title = title;
        [self addSubView];
    }
    return self;
}


- (void)addSubView {
    
    self.backgroundColor = [UIColor whiteColor];
    
    UIImage *image = [UIImage imageNamed:_iconName];
    UIImageView *imageView = [UIImageView tj_WithSuperView:self];
    imageView.image = image;
    imageView.userInteractionEnabled = YES;
    [imageView makeConstraints:^(MASConstraintMaker *make) {
        make.centerX.offset(0);
        make.top.offset(0);
        make.size.equalTo(image.size);
    }];
    
    UILabel *titleLable = [UILabel lableWithSuperView:self fontSize:12 color:UIColor_Hex(0x333333) title:_title textAlignment:NSTextAlignmentCenter];
    titleLable.userInteractionEnabled = YES;
    
    [titleLable makeConstraints:^(MASConstraintMaker *make) {
        make.top.equalTo(imageView.bottom).offset(6);
        make.centerX.offset(0);
    }];
}

 

@end
