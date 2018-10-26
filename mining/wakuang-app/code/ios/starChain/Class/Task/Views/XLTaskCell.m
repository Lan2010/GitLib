//
//  XLTaskCell.m
//  starChain
//
//  Created by rlx on 2018/6/14.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import "XLTaskCell.h"

@interface XLTaskCell()

@property (weak, nonatomic) UILabel *placeLable;
@property (weak, nonatomic) UILabel *distanceLable;
@property (weak, nonatomic) UILabel *dateLable;
@property (weak, nonatomic) UILabel *taskStateLable;
@property (weak, nonatomic) UILabel *starCountLable;
@property (weak, nonatomic) UIImageView *iconView;
@property (strong, nonatomic) NSDateFormatter *dateFormatter;


@end


@implementation XLTaskCell
 
- (instancetype)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier {
    if (self = [super initWithStyle:style reuseIdentifier:reuseIdentifier]) {
        [self addSubView];
        [self rasterizeAndOffScreenRendering];
    }
    return self;
}

- (void)addSubView {
    
    UIImageView *contentImageView = [UIImageView imageViewWithImageName:@"task-bg-list" superView:self.contentView];
    [contentImageView makeConstraints:^(MASConstraintMaker *make) {
        make.left.offset(ZOOM5(14));
        make.right.offset(ZOOM5(-14));
        make.top.bottom.offset(0);
    }];
    
    UIImageView *iconView = [UIImageView imageViewWithImageName:@"" superView:contentImageView];
    [iconView shearRoundedCornersWithRadiu:11];
    [iconView makeConstraints:^(MASConstraintMaker *make) {
        make.width.height.offset(22);
        make.left.offset(ZOOM5(21));
        make.top.offset(12 + 16);
    }];
    
    UILabel *taskStateLable = [UILabel lableWithSuperView:contentImageView fontSize:14 color:[UIColor whiteColor] title:@"任务中" textAlignment:NSTextAlignmentCenter];
    taskStateLable.backgroundColor = [UIColor orangeColor];
    [taskStateLable shearRoundedCornersWithRadiu:12];
    [taskStateLable makeConstraints:^(MASConstraintMaker *make) {
        make.right.offset(ZOOM5(-19));
        make.width.offset(68);
        make.height.offset(24);
        make.centerY.equalTo(iconView);
    }];
    
    UILabel *placeLable = [UILabel lableWithSuperView:contentImageView fontSize:14 color:UIColor_Hex(0x201A2C) title:@"车公庙广场" textAlignment: NSTextAlignmentLeft];
    placeLable.font = [UIFont boldSystemFontOfSize:16];
    [placeLable makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(iconView.right).offset(10);
        make.centerY.equalTo(iconView);
        make.right.equalTo(taskStateLable.left);
    }];
    
    UIImageView *locationIconView = [UIImageView imageViewWithImageName:@"icon-location" superView:contentImageView];
    [locationIconView makeConstraints:^(MASConstraintMaker *make) {
        make.top.equalTo(placeLable.bottom).offset(16);
        make.width.offset(12.5);
        make.height.offset(15);
        make.centerX.equalTo(iconView);
    }];
    
    UILabel *distanceLable = [UILabel lableWithSuperView:contentImageView fontSize:16 color:UIColor_Hex(0x2F1959) title:@"1.3km" textAlignment:NSTextAlignmentLeft];
    [distanceLable makeConstraints:^(MASConstraintMaker *make) {
        make.bottom.equalTo(locationIconView).offset(2);
        make.left.equalTo(locationIconView.right).offset(6);
    }];
    
    UIImageView *starImageView = [UIImageView tj_WithSuperView:self];
    UIImage *starImage = [UIImage imageNamed:@"icon-star"];
    starImageView.image = starImage;
    [starImageView makeConstraints:^(MASConstraintMaker *make) {
        make.centerY.equalTo(locationIconView);
        make.size.equalTo(starImage.size);
        make.centerX.offset(-(starImage.size.width * 0.5));
    }];
    
    UILabel *starCountLable = [UILabel lableWithSuperView:self fontSize:15 color:[UIColor blackColor]  title:@"" textAlignment:NSTextAlignmentLeft];
    [starCountLable makeConstraints:^(MASConstraintMaker *make) {
        make.centerY.equalTo(locationIconView).offset(2);
        make.left.equalTo(starImageView.right).offset(3);
    }];
    
    UILabel *dateLable = [UILabel lableWithSuperView:contentImageView fontSize:12 color:UIColor_Hex(0xa8a6de) title:@"" textAlignment:NSTextAlignmentLeft];
    [dateLable makeConstraints:^(MASConstraintMaker *make) {
        make.top.equalTo(distanceLable.bottom).offset(14);
        make.left.equalTo(locationIconView);
    }];
    
    self.starCountLable = starCountLable;
    self.iconView = iconView;
    self.placeLable = placeLable;
    self.dateLable = dateLable;
    self.distanceLable = distanceLable;
    self.taskStateLable = taskStateLable;
    
    self.selectionStyle = UITableViewCellSelectionStyleNone;
}

- (void)setModel:(XLTaskModel *)model {
    _model = model;
    
    if (model.status.intValue) {
        self.taskStateLable.text = @"任务中";
        self.taskStateLable.backgroundColor = UIColor_Hex(0xffa05c);
        
        self.dateLable.text = [NSString stringWithFormat:@"%@至%@", [model.begin_time substringFromIndex:6], [model.end_time substringFromIndex:6]];
        
    } else {
        //是否大于当前时间
        NSDate *beginDate = [self.dateFormatter dateFromString:model.begin_time];
        NSDate *currentTimeDate = [NSDate date];
        
       NSTimeInterval TimeInterval =  [currentTimeDate timeIntervalSinceDate:beginDate];
        NSLog(@"model.begin_time = %@, TimeInterval = %f", model.begin_time, TimeInterval);
        
        if ([currentTimeDate timeIntervalSinceDate:beginDate] > 0) {//当前时间戳小于开始的时间, 未开始
            self.taskStateLable.text = @"接受";
            self.taskStateLable.backgroundColor = UIColor_Hex(0x8456fa);
            self.dateLable.text = [NSString stringWithFormat:@"%@至%@", [model.begin_time substringFromIndex:6], [model.end_time substringFromIndex:6]];
            self.isStart = YES;
        } else {
            self.dateLable.text = [NSString stringWithFormat:@"%@开始", [model.begin_time substringFromIndex:6]];
            self.taskStateLable.text = @"未开始";
            self.taskStateLable.backgroundColor = UIColor_Hex(0xc3c2d9);
            self.isStart = NO;
        }
        
        NSLog(@"currentTimeDate = %@", currentTimeDate);
    }
    
    self.starCountLable.text = [NSString stringWithFormat:@"x%@", model.task_award];
    [self.iconView sd_setImageWithURL:[NSURL URLWithString:model.task_lcon] placeholderImage:[UIImage imageNamed:@"slogon"]];
    self.placeLable.text = model.task_name;
    
    CGFloat distance = model.distance.floatValue;
    self.distanceLable.text = [NSString stringWithFormat:@"%.2fkm", distance / 1000.0];
    
}

- (NSDateFormatter *)dateFormatter {
    if (!_dateFormatter) {
        _dateFormatter = [[NSDateFormatter alloc] init];
        [_dateFormatter setDateFormat:@"yyyy-MM-dd HH:mm"];
    }
    return _dateFormatter;
}

@end

 
