//
//  XLCompleteTaskCell.m
//  starChain
//
//  Created by rlx on 2018/6/12.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import "XLCompleteTaskCell.h"


@interface XLCompleteTaskCell()

@property (weak, nonatomic) UILabel *placeLable;
@property (weak, nonatomic) UILabel *starCountLable;
@property (weak, nonatomic) UILabel *dateLable;


@end

@implementation XLCompleteTaskCell


- (instancetype)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier {
    if (self = [super initWithStyle:style reuseIdentifier:reuseIdentifier]) {
        [self addSubView];
        [self rasterizeAndOffScreenRendering];
    }
    return self;
}

- (void)addSubView {
    UILabel *placeLable = [UILabel lableWithSuperView:self.contentView fontSize:14 color:UIColor_Hex(0x000000) title:@"" textAlignment:NSTextAlignmentLeft];
    [placeLable makeConstraints:^(MASConstraintMaker *make) {
        make.left.offset(14);
        make.top.offset(10);
    }];
    
    UILabel *starCountLable = [UILabel lableWithSuperView:self.contentView fontSize:14 color:UIColor_Hex(0x6963C0) title:@"" textAlignment:NSTextAlignmentLeft];
    [starCountLable makeConstraints:^(MASConstraintMaker *make) {
        make.right.offset(-14);
        make.centerY.offset(0);
    }];
    
    UILabel *dateLable = [UILabel lableWithSuperView:self.contentView fontSize:13 color:UIColor_Hex(0xa0a0a0) title:@"" textAlignment:NSTextAlignmentLeft];
    [dateLable makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(placeLable);
        make.bottom.offset(-10);
    }];
    
    self.placeLable = placeLable;
    self.starCountLable = starCountLable;
    self.dateLable = dateLable;
    self.selectionStyle = UITableViewCellSelectionStyleNone;
}

- (void)setTaskModel:(XLCompleteTaskModel *)taskModel {
    _taskModel = taskModel;
    self.placeLable.text = taskModel.task_name;
    self.starCountLable.text = [NSString stringWithFormat:@"+%@星星", taskModel.starPoint];
    self.dateLable.text = taskModel.end_time;
}



@end
