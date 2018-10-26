//
//  XLAnnotationView.m
//  starChain
//
//  Created by rlx on 2018/6/23.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import "XLAnnotationView.h"
#import "BubbleLayer.h"


@interface XLStarAnnotationView()

@property (weak, nonatomic) UIImageView *imageView;
@property (assign, nonatomic) CGSize imageSize;
@property (strong, nonatomic) NSDateFormatter *formatter;


@end

@implementation XLStarAnnotationView

- (id)initWithAnnotation:(id<BMKAnnotation>)annotation reuseIdentifier:(NSString *)reuseIdentifier {
    if (self = [super initWithAnnotation:annotation reuseIdentifier:reuseIdentifier]) {
        
        UIImage *image = [UIImage imageNamed:@"index-star"];
        UIImageView *imageView = [[UIImageView alloc] initWithImage:image];
        [self addSubview:imageView];
        self.imageView = imageView;
        self.bounds = imageView.bounds;
        [self addTapGesturesWithTarget:self action:@selector(tapSelf)];
    }
    return self;
}


- (void)tapSelf {
    if ([self.delegate respondsToSelector:@selector(didSelectStarAnnotationView:)]) {
        [self.delegate didSelectStarAnnotationView:self];
    }
}

- (NSDateFormatter *)formatter {
    if (!_formatter) {
        _formatter = [[NSDateFormatter alloc] init];
        [_formatter setDateFormat:@"YYYY-MM-dd HH:mm:ss"];
     }
    return _formatter;
}

- (void)setStarAnannotation:(XLStarAnnotation *)starAnannotation {
    _starAnannotation = starAnannotation;
    
    TJLog(@"starAnannotation.starType = %ld", (long)starAnannotation.starType);
    
    
//    if (starAnannotation.starType == XLStarTypeAD) {
//        self.imageView.image = [UIImage imageNamed:@"star-ad"];
//
//    } else if (starAnannotation.starType == XLStarTypeRandom) {
//        self.imageView.image = [UIImage imageNamed:@"star-sl"];
//
//    } else if (starAnannotation.starType == XLStarTypeTask) {
//        self.imageView.image = [UIImage imageNamed:@"index-star"];
//    }
//
    
    if (starAnannotation.createTime.length) {
        
        NSDate *date = [self.formatter dateFromString:starAnannotation.createTime];
        
        NSLog(@"date = %@", date);
        NSLog(@"starAnannotation.beginTime = %@", starAnannotation.createTime);
        
        NSString *currentString = [self.formatter stringFromDate:date];
        NSLog(@"currentString = %@", currentString);
        
        NSTimeInterval timeInterval = [[NSDate date] timeIntervalSinceDate:date];
 
        if (starAnannotation.starType == XLStarTypeTask || starAnannotation.starType == XLStarTypeRandom) { //或者是随机的

            if (timeInterval > 22 * 60 * 60) {//开始时间大于22个小时之后星星就变暗
                self.imageView.image = [UIImage imageNamed:@"index-star1"];

            } else {
                self.imageView.image = [UIImage imageNamed:@"index-star"];
            }
        }

        NSLog(@"timeInterval 相差 = %f", timeInterval - 22 * 60 * 60);
    }

    if (!starAnannotation.adImageUrl.length) return;
    if (![starAnannotation.adImageUrl containsString:@"http"]) return;
    
    [[SDWebImageManager sharedManager] downloadImageWithURL:[NSURL URLWithString:starAnannotation.adImageUrl] options:0 progress:nil completed:^(UIImage *image, NSError *error, SDImageCacheType cacheType, BOOL finished, NSURL *imageURL) {
        NSLog(@"下载的image = %@", image);
        starAnannotation.adImage = image;
    }];
}


- (void)didMoveToSuperview {
    CAKeyframeAnimation *anim = [CAKeyframeAnimation animation];
    anim.keyPath = @"transform.scale";
    anim.values = @[@1, @1.2];
    
    CABasicAnimation *opaqueAnim = [CABasicAnimation animation];
    opaqueAnim.keyPath = @"opacity";
    opaqueAnim.fromValue = @0.7;
    opaqueAnim.toValue = @1;
    
    CAAnimationGroup *animationGroup = [[CAAnimationGroup alloc] init];
    animationGroup.duration = 1;
    animationGroup.repeatCount = CGFLOAT_MAX;
    animationGroup.fillMode = kCAFillModeForwards;
    animationGroup.removedOnCompletion = NO;
    animationGroup.animations = @[anim, opaqueAnim];
    animationGroup.autoreverses = YES;
    
    [self.imageView.layer addAnimation:animationGroup forKey:nil];
}

- (UIImage *)iconImage {
    return self.imageView.image;
}

@end


@interface XLTasklocationAnnotationView()

@property (weak, nonatomic) UIImageView *iconView;
@property (weak, nonatomic) UIImageView *imageView;

@end

@implementation XLTasklocationAnnotationView

- (id)initWithAnnotation:(id<BMKAnnotation>)annotation reuseIdentifier:(NSString *)reuseIdentifier {
    if (self = [super initWithAnnotation:annotation reuseIdentifier:reuseIdentifier]) {
        [self addSubView];
    }
    return self;
}

- (void)addSubView {
    
    self.bounds = CGRectMake(0, 0, 36, 46);
    
    UIImageView *imageView = [[UIImageView alloc] initWithFrame:self.bounds];
    [self addSubview:imageView];
    imageView.image = [UIImage imageNamed:@"bg-sb"];
    imageView.hidden = YES;
    
    UIImageView *iconView = [[UIImageView alloc] initWithFrame:self.bounds];
    [self addSubview:iconView];
    [iconView shearRoundedCornersWithRadiu:17];
    iconView.frame = CGRectMake(1, 1, 34, 34);
 
    self.iconView = iconView;
    self.imageView = imageView;
    
    [self addTapGesturesWithTarget:self action:@selector(tapSelf)];
    
}

- (void)tapSelf {
    if ([self.delegate respondsToSelector:@selector(didSelectStarAnnotationView:)]) {
        [self.delegate didSelectStarAnnotationView:self];
    }
}

- (void)setTasklocationAnnotation:(XLTasklocationAnnotation *)tasklocationAnnotation {
    _tasklocationAnnotation = tasklocationAnnotation;
    
    [[SDWebImageManager sharedManager] downloadImageWithURL:[NSURL URLWithString:tasklocationAnnotation.imageUrl] options:0 progress:nil completed:^(UIImage *image, NSError *error, SDImageCacheType cacheType, BOOL finished, NSURL *imageURL) {
        self.iconView.image = image;
        self.imageView.hidden = NO;
    }];
}

@end



@interface XLMelocationAnnotationView()

@property (weak, nonatomic) UIImageView *iconView;
@property (weak, nonatomic) UIView *promptView;

@end

@implementation XLMelocationAnnotationView

- (id)initWithAnnotation:(id<BMKAnnotation>)annotation reuseIdentifier:(NSString *)reuseIdentifier {
    if (self = [super initWithAnnotation:annotation reuseIdentifier:reuseIdentifier]) {
        [self addSubView];
    }
    return self;
}

- (void)addSubView {
    
    CGFloat iconViewW = [UIImage imageNamed:@"round"].size.width;
    
    CGFloat W = iconViewW + 5;
    
    self.backgroundColor = [UIColor clearColor];
    self.bounds = CGRectMake(0, 0, W, W);

    UIView *promptView = [[UIView alloc] initWithFrame:CGRectMake(- (103 * 0.5 - W * 0.5), -22, 103, 23)];
    promptView.hidden = YES;
    
    UILabel *promptLable = [[UILabel alloc] initWithFrame:CGRectMake(0, 0, 103, 20)];
    promptLable.text = @"已进入任务范围";
    promptLable.textColor = [UIColor whiteColor];
    promptLable.font = [UIFont systemFontOfSize:12];
    promptLable.textAlignment = NSTextAlignmentCenter;
    promptView.backgroundColor = [UIColor colorWithRed:47.0f/255.0f green:25.0f/255.0f blue:89.0f/255.0f alpha:0.75f];
    [promptView addSubview:promptLable];
 
    BubbleLayer *bubbleLayer = [[BubbleLayer alloc]initWithSize:promptView.bounds.size];
    bubbleLayer.arrowDirection = ArrowDirectionButtom;
    bubbleLayer.arrowHeight = 2;
    bubbleLayer.arrowWidth = 5;
    bubbleLayer.arrowPosition = 0.5;
    bubbleLayer.cornerRadius = (promptView.tj_height - 2) * 0.5;
    [promptView.layer setMask:[bubbleLayer layer]];
    
    [self addSubview:promptView];
    self.promptView = promptView;
        
}

- (void)setMeLocationAnnotation:(XLMeLocationAnnotation *)meLocationAnnotation {
    _meLocationAnnotation = meLocationAnnotation;
}

- (void)setShowPrompt:(BOOL)showPrompt {
    _showPrompt = showPrompt;
    self.promptView.hidden = !showPrompt;
}

@end


