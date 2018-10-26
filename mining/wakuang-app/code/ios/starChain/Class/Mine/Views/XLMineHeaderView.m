//
//  XLMineHeaderView.m
//  starChain
//
//  Created by rlx on 2018/6/11.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import "XLMineHeaderView.h"


@interface XLMineHeaderView()


@end


@implementation XLMineHeaderView

- (instancetype)initWithFrame:(CGRect)frame {
    self = [super initWithFrame:frame];
    if (self) {
        [self addSubView];
    }
    return self;
}

- (void)addSubView {
    
    NSString *imageName = (IPHONEX) ? @"me_bg_X" : @"me_bg";
    CGFloat height = (IPHONEX) ? H(150 + (StatusBarHight - 20)) : H(150);
    UIImageView *backImageView = [UIImageView imageViewWithImageName:imageName superView:self];
    [backImageView makeConstraints:^(MASConstraintMaker *make) {
        make.top.offset(0);
        make.left.right.offset(0);
        make.height.offset(height);
    }];
    
    UIImageView *whiteImageView = [UIImageView tj_WithSuperView:self];
    whiteImageView.image = [UIImage imageNamed:@"account_bg"];
    [whiteImageView makeConstraints:^(MASConstraintMaker *make) {
        make.left.offset(3);
        make.right.offset(-3);
        make.top.offset(H(106) + (StatusBarHight - 20));
        make.height.offset(H(115));
    }];
    
    UIImageView *headPortraitImageView = [UIImageView imageViewWithImageName:@"Headportrait" superView:self];
    [headPortraitImageView makeConstraints:^(MASConstraintMaker *make) {
        make.centerY.equalTo(whiteImageView.top).offset(H(5));
        make.centerX.offset(0);
        make.width.offset(H(84));
        make.height.offset(H(86));
    }];
    
    UIImageView *invitationImageView = [UIImageView imageViewWithImageName:@"Operationentrance" superView:self];
    [invitationImageView addTapGesturesWithTarget:self action:@selector(tapInvitationImageView)];
    [invitationImageView makeConstraints:^(MASConstraintMaker *make) {
        make.top.equalTo(whiteImageView.bottom).offset(-2);
        make.height.offset(H(69));
        make.left.offset(16);
        make.right.offset(-16);
    }];

    UILabel *phoneNumberLable = [UILabel lableWithSuperView:whiteImageView fontSize:16 color:UIColor_Hex(0x303030) title:@"" textAlignment:NSTextAlignmentLeft];
    [phoneNumberLable addTapGesturesWithTarget:self action:@selector(tapPhoneNumberLable)];
    [phoneNumberLable makeConstraints:^(MASConstraintMaker *make) {
        make.top.equalTo(headPortraitImageView.bottom).offset(H(15));
        make.centerX.offset(0);
    }];
    
    backImageView.userInteractionEnabled = YES;
    headPortraitImageView.userInteractionEnabled = YES;
    headPortraitImageView.userInteractionEnabled = YES;
    
    self.phoneNumberLable = phoneNumberLable;
}

- (void)tapPhoneNumberLable {
    if (self.tapPhoneNumberLableBlock) self.tapPhoneNumberLableBlock();
}

- (void)tapInvitationImageView {
    if (self.tapInvitationImageViewBlock) self.tapInvitationImageViewBlock();
    
}

 
@end
