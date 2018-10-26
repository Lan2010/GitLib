//
//  XLStarRankingCell.m
//  starChain
//
//  Created by rlx on 2018/7/7.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import "XLStarRankingCell.h"
#import "XLCompleteTaskModel.h"

@interface XLStarRankingCell()

@property (weak, nonatomic) UILabel *rankingLable;
@property (weak, nonatomic) UILabel *nameLable;
@property (weak, nonatomic) UILabel *starCountLable;
@property (weak, nonatomic) UIImageView *starImageView;


@end


@implementation XLStarRankingCell


- (instancetype)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier {
    if (self = [super initWithStyle:style reuseIdentifier:reuseIdentifier]) {
        [self addSubView];
        [self rasterizeAndOffScreenRendering];
    }
    return self;
}

- (void)addSubView {
    
    UILabel *rankingLable = [UILabel lableWithSuperView:self.contentView fontSize:15 color:UIColor_Hex(0x01a2c) title:@"" textAlignment:NSTextAlignmentCenter];
    [rankingLable makeConstraints:^(MASConstraintMaker *make) {
        make.left.offset(14);
        make.centerY.offset(0);
        make.width.offset(22);
     }];
    
    UILabel *nameLable = [UILabel lableWithSuperView:self.contentView fontSize:15 color:UIColor_Hex(0x201a2c) title:@"" textAlignment:NSTextAlignmentLeft];
    [nameLable makeConstraints:^(MASConstraintMaker *make) {
        make.left.offset(57);
        make.centerY.offset(0);
        make.right.equalTo(self.contentView.centerX).offset(20);
    }];
    
    UILabel *starCountLable = [UILabel lableWithSuperView:self.contentView fontSize:15 color:UIColor_Hex(0x201a2c) title:@"" textAlignment:NSTextAlignmentLeft];
    [starCountLable makeConstraints:^(MASConstraintMaker *make) {
        make.centerY.offset(0);
        make.right.offset(-14);
    }];
    
    self.rankingLable = rankingLable;
    self.nameLable = nameLable;
    self.starCountLable = starCountLable;
    self.selectionStyle = UITableViewCellSelectionStyleNone;
}

- (void)setModel:(XLStarRankingModel *)model {
    _model = model;

    self.rankingLable.text = model.num;
    self.nameLable.text = [NSString stringWithFormat:@"%@", model.phone];
    self.starCountLable.text = model.starPoint;
}


 
@end
