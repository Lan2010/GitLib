//
//  XLStarRecordCell.m
//  starChain
//
//  Created by rlx on 2018/7/5.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import "XLStarRecordCell.h"

@interface XLStarRecordCell()

@property (weak, nonatomic) UILabel *placeLable;
@property (weak, nonatomic) UILabel *starCountLable;
@property (weak, nonatomic) UILabel *dateLable;


@end

@implementation XLStarRecordCell

- (instancetype)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier {
    if (self = [super initWithStyle:style reuseIdentifier:reuseIdentifier]) {
        [self addSubView];
        [self rasterizeAndOffScreenRendering];
    }
    return self;
}

- (void)addSubView {
    UILabel *placeLable = [UILabel lableWithSuperView:self.contentView fontSize:16 color:UIColor_Hex(0x01a2c) title:@"" textAlignment:NSTextAlignmentLeft];
    [placeLable makeConstraints:^(MASConstraintMaker *make) {
        make.left.offset(14);
        make.top.offset(10);
    }];
    
    UILabel *starCountLable = [UILabel lableWithSuperView:self.contentView fontSize:14 color:UIColor_Hex(0xff6551) title:@"" textAlignment:NSTextAlignmentLeft];
    [starCountLable makeConstraints:^(MASConstraintMaker *make) {
        make.right.offset(-14);
        make.centerY.offset(0);
    }];
    
    UILabel *dateLable = [UILabel lableWithSuperView:self.contentView fontSize:12 color:UIColor_Hex(0xa2a1b4) title:@"" textAlignment:NSTextAlignmentLeft];
    [dateLable makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(placeLable);
        make.bottom.offset(-10);
    }];
    
    self.placeLable = placeLable;
    self.starCountLable = starCountLable;
    self.dateLable = dateLable;
    self.selectionStyle = UITableViewCellSelectionStyleNone;
}

- (void)setModel:(XLStarRecordModel *)model {
    _model = model;
    
    self.placeLable.text = model.recordsType;
    self.starCountLable.text = [NSString stringWithFormat:@"+%@", model.operStarPoint];
    self.dateLable.text = model.createTime;
}

@end
