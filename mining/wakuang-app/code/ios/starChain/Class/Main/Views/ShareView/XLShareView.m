//
//  XLShareView.m
//  starChain
//
//  Created by rlx on 2018/6/13.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import "XLShareView.h"
#import "XLShareCell.h"

@interface XLShareView ()

@property (strong, nonatomic) NSArray *icons;
@property (strong, nonatomic) NSArray *titles;

@end

@implementation XLShareView

- (instancetype)initWithFrame:(CGRect)frame {
    if (self = [super initWithFrame:frame]) {
        [self addSubView];
    };
    return self;
}

- (void)addSubView {
    
    _icons = @[@"weixin_icon", @"friend_icon", @"weibo_icon"];
    _titles = @[@"微信好友", @"朋友圈", @"微博"];
    
    self.backgroundColor = UIColor_Hex(0xebe7e7);
    UILabel *shareLable = [UILabel lableWithSuperView:self fontSize:16 color:UIColor_Hex(0x303030) title:@"分享到" textAlignment:NSTextAlignmentCenter];
    [shareLable makeConstraints:^(MASConstraintMaker *make) {
        make.centerX.offset(0);
        make.height.offset(30);
        make.top.offset(5);
    }];
    
    UIView *coreView = [UIView tj_WithSuperView:self];
    coreView.backgroundColor = UIColor_Hex(0xebe7e7);
    [coreView makeConstraints:^(MASConstraintMaker *make) {
        make.left.right.offset(0);
        make.top.equalTo(shareLable.bottom);
        make.height.equalTo(90);
    }];
    
    UIView *tailView = [UIView tj_WithSuperView:self];
    tailView.userInteractionEnabled = YES;
    tailView.backgroundColor = [UIColor whiteColor];
    [tailView makeConstraints:^(MASConstraintMaker *make) {
        make.top.equalTo(coreView.bottom);
        make.centerX.offset(0);
        make.left.right.offset(0);
        make.bottom.offset(0);
    }];
    
    UIButton *cancalButton = [UIButton buttonWithSuperView:tailView fontSize:16 color:UIColor_Hex(0x303030) title:@"取消"];
    [cancalButton addTarget:self action:@selector(didClickCancalButton) forControlEvents:UIControlEventTouchUpInside];
    [cancalButton makeConstraints:^(MASConstraintMaker *make) {
        make.centerX.top.offset(0);
        make.width.offset(160);
        make.height.offset(46);
    }];
    
    
    int colSpace = 24;//行间距
    int topSpace = 5;
    int leftSpace = H(34);
    
    
    CGFloat shareCellX = 0;
    CGFloat shareCellY = 0;
    CGFloat shareCellW = 85;
    CGFloat shareCellH = shareCellW;
    
    int rowSpace = (KScreenWidth - shareCellW * 3 - leftSpace * 2) * 0.5;//列间距
    
    for (int i = 0; i < _titles.count; i++) {
        int row = i / 3;
        int col = i % 3;
        
        shareCellX = leftSpace + col * (rowSpace + shareCellW);
        shareCellY = topSpace + row * (colSpace + shareCellH);
        
        XLShareCell *shareCell = [[XLShareCell alloc] initWithFrame:CGRectMake(shareCellX, shareCellY, shareCellW, shareCellH) iconName:_icons[i] title:_titles[i]];
        shareCell.tag = i + 100;
        shareCell.backgroundColor = UIColor_Hex(0xebe7e7);
        [coreView addSubview:shareCell];
        [shareCell addTapGesturesWithTarget:self action:@selector(didClickItem:)];
    }
}

- (void)didClickItem:(UITapGestureRecognizer *)tapGestureRecognizer {
    if (self.didClickItemBlock) self.didClickItemBlock(tapGestureRecognizer.view.tag - 100);
}

- (void)didClickCancalButton {
    if (self.didClickCanCalButtonBlock) self.didClickCanCalButtonBlock();
}


@end
