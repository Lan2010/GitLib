//
//  XLPagingHeaderView.m
//  starChain
//
//  Created by rlx on 2018/7/12.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import "XLPagingHeaderView.h"
#import "XLButton.h"


@interface XLPagingHeaderView ()

@property (strong, nonatomic) NSArray *titles;
@property (strong, nonatomic) UIView *seperatorView;
@property (strong, nonatomic) NSMutableArray *buttons;


@end

@implementation XLPagingHeaderView


- (instancetype)initWithFrame:(CGRect)frame titles:(NSArray *)titles separatedLine:(BOOL)separatedLine {
    if (self = [super initWithFrame:frame]) {
        _titles = titles;
        _isSeparatedLine = separatedLine;
        _buttons = [NSMutableArray array];
        [self addSubView];
        self.backgroundColor = [UIColor whiteColor];
    }
    return self;
}

- (void)addSubView {
    for (int i = 0; i < self.titles.count; i++) {
        XLButton *button = [XLButton buttonWithSuperView:self fontSize:16 color:UIColor_Hex(0x666666) title:self.titles[i]];
        CGFloat buttonW = KScreenWidth / self.titles.count;
        button.frame = CGRectMake(buttonW * i, 0, buttonW, CGRectGetHeight(self.frame));
        button.tag = i;
        [button addTarget:self action:@selector(didClickButton:) forControlEvents:UIControlEventTouchUpInside];
        [button setTitleColor:[UIColor tj_mainColor] forState:UIControlStateSelected];
        [button setTitleColor:UIColor_Hex(0x303030) forState:UIControlStateNormal];
        if (i == 0) {
            button.selected = YES;
        }
        [_buttons addObject:button];
    }
    
    if (_isSeparatedLine) {
        UIView *middleSeparatedLine = [UIView tj_WithSuperView:self];
        middleSeparatedLine.frame = CGRectMake(KScreenWidth * 0.5, 12, 0.5, 20);
        middleSeparatedLine.backgroundColor = [UIColor colorWithWhite:0 alpha: 0.1];
        
    }
    
    _seperatorView = [[UIView alloc] initWithFrame:CGRectMake(0, 44 - 0.5, CGRectGetWidth(self.bounds), 0.5)];
    _seperatorView.backgroundColor = [UIColor colorWithWhite:0 alpha: 0.1];
    _seperatorView.autoresizingMask = UIViewAutoresizingFlexibleTopMargin | UIViewAutoresizingFlexibleWidth;
    [self addSubview:_seperatorView];
}

- (void)didClickButton:(XLButton *)button {
    self.currentIndex = button.tag;
    if (self.didClickButtonBlock) self.didClickButtonBlock(button);
}

- (void)setCurrentIndex:(NSInteger)currentIndex {
    if (_currentIndex == currentIndex) return;
    //选择现在的
    UIButton *button = _buttons[currentIndex];
    button.selected = YES;
    //取消之前的
    XLButton *oldButton = _buttons[_currentIndex];
    oldButton.selected = NO;
    _currentIndex = currentIndex;
}


@end
