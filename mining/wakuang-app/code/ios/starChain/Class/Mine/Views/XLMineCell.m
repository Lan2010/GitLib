//
//  XLMineCell.m
//  starChain
//
//  Created by rlx on 2018/6/14.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import "XLMineCell.h"
#import "XLMineStyleValue1Cell.h"

@interface XLMineCell ()<UITableViewDataSource,UITableViewDelegate>

@property (weak, nonatomic) UILabel *tj_detailTextLable;
@property (weak, nonatomic) UIImageView *iconView;
@property (weak, nonatomic) UILabel *typeText;
@property (weak, nonatomic) UITableView *tableView;

@end


@implementation XLMineCell

static NSString * const mineStyleValue1Cell = @"mineStyleValue1Cell";


- (instancetype)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier {
    if (self = [super initWithStyle:style reuseIdentifier:reuseIdentifier]) {
        [self addSubView];
    }
    return self;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    return self.datas.count;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    XLMineStyleValue1Cell *cell = [tableView dequeueReusableCellWithIdentifier:mineStyleValue1Cell forIndexPath:indexPath];
    cell.dict = self.datas[indexPath.row];
    cell.clipsToBounds = YES;
    return cell;
}

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath {
    if (self.didClickRow) self.didClickRow(indexPath.row);
}

- (void)addSubView {

    UIView *backgroundImageView = [UIView tj_WithSuperView:self.contentView];
    backgroundImageView.userInteractionEnabled = YES;
    [backgroundImageView makeConstraints:^(MASConstraintMaker *make) {
        make.left.offset(15);
        make.right.offset(-15);
        make.top.offset(16);
        make.bottom.offset(-16);
    }];

    UITableView *tableView = [[UITableView alloc] initWithFrame:CGRectZero style:UITableViewStylePlain];
    tableView.layer.cornerRadius = 8;
    tableView.layer.masksToBounds = NO;
    tableView.layer.shadowColor = UIColor_Hex(0x2F1959).CGColor;//shadowColor阴影颜色
    tableView.layer.shadowOpacity = 0.1;//阴影透明度，默认0
    tableView.layer.shadowRadius = 8;//阴影半径，默认3
    
    tableView.separatorColor = [UIColor tj_separatorColor];
    tableView.tableFooterView = [[UIView alloc] init];
    [tableView setSeparatorInset:UIEdgeInsetsZero];
    [tableView setLayoutMargins:UIEdgeInsetsZero];
    tableView.showsHorizontalScrollIndicator = NO;
    tableView.showsVerticalScrollIndicator = NO;
    tableView.rowHeight = 50;
    tableView.scrollEnabled = NO;
    [tableView registerClass:[XLMineStyleValue1Cell class] forCellReuseIdentifier:mineStyleValue1Cell];
    
    if (iOS11) {
        tableView.contentInsetAdjustmentBehavior = UIScrollViewContentInsetAdjustmentNever;
        tableView.estimatedSectionHeaderHeight = 0;
        tableView.estimatedSectionFooterHeight = 0;
        tableView.estimatedRowHeight = 0;
    }
    
    tableView.dataSource = self;
    tableView.delegate = self;
    tableView.scrollEnabled = NO;
    [backgroundImageView addSubview:tableView];
    [tableView makeConstraints:^(MASConstraintMaker *make) {
        make.top.left.right.bottom.offset(0);
    }];
    
    self.tableView = tableView;

    self.clipsToBounds = YES;
    self.selectionStyle = UITableViewCellSelectionStyleNone;
    self.contentView.backgroundColor = [UIColor tj_backgroundColor];
}

 
- (void)setDatas:(NSArray *)datas{
    _datas = datas;
    [self.tableView reloadData];
}


@end
