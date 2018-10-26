//
//  XLMineStyleValue1Cell.m
//  starChain
//
//  Created by rlx on 2018/6/14.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import "XLMineStyleValue1Cell.h"

@interface XLMineStyleValue1Cell ()

@property (weak, nonatomic) UILabel *tj_detailTextLable;
@property (weak, nonatomic) UIImageView *iconView;
@property (weak, nonatomic) UILabel *typeText;
@property (weak, nonatomic) UIImageView *arrowView;

@end


@implementation XLMineStyleValue1Cell


- (instancetype)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier {
    if (self = [super initWithStyle:style reuseIdentifier:reuseIdentifier]) {
        [self addSubView];
    }
    return self;
}

- (void)addSubView {
    
    self.backgroundColor = [UIColor whiteColor];
    UIImageView *iconView = [UIImageView tj_WithSuperView:self.contentView];
    [iconView makeConstraints:^(MASConstraintMaker *make) {
        make.centerY.offset(0);
        make.width.height.offset(25);
        make.left.offset(14);
    }];
    
    UILabel *typeText = [UILabel lableWithSuperView:self.contentView fontSize:16 color:UIColor_Hex(0x303030) title:@"" textAlignment:NSTextAlignmentLeft];
    [typeText makeConstraints:^(MASConstraintMaker *make) {
        make.centerY.equalTo(iconView);
        make.left.equalTo(iconView.right).offset(8);
    }];
    
    UIImageView *arrowView = [UIImageView tj_WithSuperView:self.contentView];
    arrowView.image = [UIImage imageNamed:@"right"];
    [arrowView makeConstraints:^(MASConstraintMaker *make) {
        make.centerY.equalTo(iconView);
        make.width.offset(8);
        make.height.offset(14);
        make.right.offset(-14);
    }];
    
    CGFloat leftMagrin = (KScreenHeight < 667) ? 3 : 8;
    UILabel *tj_detailTextLable =  [UILabel lableWithSuperView:self.contentView fontSize:16 color:UIColor_Hex(0xa0a0a0) title:nil textAlignment:NSTextAlignmentRight];
    tj_detailTextLable.lineBreakMode = NSLineBreakByTruncatingTail;
    [tj_detailTextLable makeConstraints:^(MASConstraintMaker *make) {
        make.centerY.equalTo(iconView);
        make.right.equalTo(arrowView.left).offset(-8);
        make.left.equalTo(typeText.right).offset(leftMagrin);
    }];
    
    _iconView = iconView;
    _typeText = typeText;
    _tj_detailTextLable = tj_detailTextLable;
    _arrowView = arrowView;
    
    [tj_detailTextLable setContentCompressionResistancePriority:UILayoutPriorityFittingSizeLevel forAxis:UILayoutConstraintAxisHorizontal];
    [typeText setContentCompressionResistancePriority:UILayoutPriorityRequired forAxis:UILayoutConstraintAxisHorizontal];
    
    self.backgroundColor = [UIColor clearColor];
    self.contentView.backgroundColor = [UIColor clearColor];
    self.selectionStyle = UITableViewCellSelectionStyleNone;
}

- (void)setDict:(NSDictionary *)dict {
    _dict = dict;
        
    _iconView.image = [UIImage imageNamed:dict[@"icon"]];
    _typeText.text = dict[@"text"];
    
    NSString *detailText = dict[@"detailText"];
    if (detailText.length) {
        if ([detailText containsString:@"送"]) {
            NSMutableAttributedString *newAttrStr = [[NSMutableAttributedString alloc] initWithString:detailText];
            [newAttrStr addAttribute:NSForegroundColorAttributeName value:UIColor_Hex(0xac56fa) range:NSMakeRange(1, detailText.length - 3)];
            _tj_detailTextLable.attributedText = newAttrStr;
        } else {
            _tj_detailTextLable.text = detailText;
        }
    } else {
        _tj_detailTextLable.text = @"";
    }
    
    if (dict[@"arrow"] && ([dict[@"arrow"] intValue] == 0)) {
        _arrowView.image = nil;
        [_arrowView updateConstraints:^(MASConstraintMaker *make) {
            make.width.offset(0);
        }];
        
        [_tj_detailTextLable updateConstraints:^(MASConstraintMaker *make) {
            make.right.equalTo(self.arrowView.left);
        }];
    } else {
        UIImage *arrowImage = [UIImage imageNamed:@"right"];
        self.arrowView.image = arrowImage;
        [_arrowView updateConstraints:^(MASConstraintMaker *make) {
            make.width.offset(8);
        }];
        
        [_tj_detailTextLable updateConstraints:^(MASConstraintMaker *make) {
            make.right.equalTo(self.arrowView.left).offset(-8);
        }];
    }
}

@end
