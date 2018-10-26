//
//  XLShareController.m
//  starChain
//
//  Created by rlx on 2018/6/13.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import "XLShareController.h"
#import "XLShareView.h"
#import <UMShare/UMShare.h>

#define SocialManager [UMSocialManager defaultManager]

@interface XLShareController ()

@property (weak, nonatomic) UIView *maskView;
@property (weak, nonatomic) XLShareView *shareView;
@property (weak, nonatomic) UIView *contentView;

@property (strong, nonatomic) NSArray *platformTypes;
@property (strong, nonatomic) UIImage *image;

@property (copy, nonatomic) NSString *shareUrlString;
@property (assign, nonatomic) NSInteger pageindex;

@end

@implementation XLShareController

ControllerPresentationCustom

- (void)viewDidLoad {
    [super viewDidLoad];
    [self addSubView];
}

- (void)addSubView {
    
    UIView *maskView = [[UIView alloc] initWithFrame:self.view.bounds];
    maskView.backgroundColor = [[UIColor blackColor] colorWithAlphaComponent:0];
    [self.view addSubview:maskView];
    [maskView addTapGesturesWithTarget:self action:@selector(didClickCancelButton)];
    
    UIView *contentView = [[UIView alloc] initWithFrame:CGRectMake(0, KScreenHeight, KScreenWidth, 170 + TabbarSafeBottomMargin)];
    [maskView addSubview:contentView];
    contentView.backgroundColor = UIColor_Hex(0xebe7e7);
    self.contentView = contentView;
    
    XLShareView *shareView = [[XLShareView alloc] initWithFrame:CGRectMake(0, 0, KScreenWidth, 170)];
    [contentView addSubview:shareView];
    
    WEAKSELF
    [shareView setDidClickCanCalButtonBlock:^{
        [weakSelf didClickCancelButton];
    }];
    
    [shareView setDidClickItemBlock:^(NSInteger index) {
        [weakSelf shareWithIndex:index];
    }];
    
    self.shareView = shareView;
    self.maskView = maskView;
}

- (void)shareWithIndex:(NSInteger)index {
    [self didClickCancelButton];
        
    if ((![SocialManager isInstall:UMSocialPlatformType_WechatSession]) && (index == 0 || index == 1)) {
        [self alertWithTitle:@"没有安装微信,无法进行分享" message:nil buttonTitle:@"确定"];
        return;
    }

    UMSocialPlatformType platformType = [_platformTypes[index] intValue];
    [self shareImageToPlatformType:platformType];
    
}

- (void)shareImageToPlatformType:(UMSocialPlatformType)platformType {
    [self shareImageToPlatformType:platformType withThumb:nil image:_image];
}

- (void)shareImageToPlatformType:(UMSocialPlatformType)platformType withThumb:(id)thumb image:(id)image {
    UMSocialMessageObject *messageObject = [UMSocialMessageObject messageObject];
    
    UMShareImageObject *shareObject = [[UMShareImageObject alloc] init];
    shareObject.thumbImage = thumb;
    
    [shareObject setShareImage:image];
     messageObject.shareObject = shareObject;
 
    [[UMSocialManager defaultManager] shareToPlatform:platformType messageObject:messageObject currentViewController:self completion:^(id data, NSError *error) {
        if (error) {
            UMSocialLogInfo(@"************Share fail with error %@*********",error);
        }else{
            if ([data isKindOfClass:[UMSocialShareResponse class]]) {
                UMSocialShareResponse *resp = data;
                //分享结果消息
                UMSocialLogInfo(@"response message is %@",resp.message);
                //第三方原始返回的数据
                UMSocialLogInfo(@"response originalResponse data is %@",resp.originalResponse);
                
            }else{
                UMSocialLogInfo(@"response data is %@",data);
            }
        }
        [self alertWithError:error];
    }];
}


- (void)alertWithError:(NSError *)error {
    NSString *result = nil;
    if (!error) {
        result = [NSString stringWithFormat:@"分享成功"];
    } else{
        result = [NSString stringWithFormat:@"分享失败"];
    }
    [self alertWithTitle:@"提示" message:result buttonTitle:@"确定"];
}


- (void)shareWithImage:(UIImage *)image {
    _platformTypes = @[
                       @(UMSocialPlatformType_WechatSession),
                       @(UMSocialPlatformType_WechatTimeLine),
                       @(UMSocialPlatformType_Sina)
                       ];
    _image = image;
}

- (void)didClickCancelButton {
    [UIView animateWithDuration:0.25 animations:^{
        self.contentView.tj_y = CGRectGetMaxY(self.maskView.frame);
        self.maskView.backgroundColor = [[UIColor blackColor] colorWithAlphaComponent:0];
    } completion:^(BOOL finished) {
        [self.maskView.subviews enumerateObjectsUsingBlock:^(__kindof UIView * _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
            [obj removeFromSuperview];
        }];
        [self.maskView removeFromSuperview];
        [self dismissViewControllerAnimated:NO completion:nil];
    }];
}

- (void)viewWillAppear:(BOOL)animated {
    [super viewWillAppear:animated];
    
    [UIView animateWithDuration:0.25 animations:^{
        self.maskView.backgroundColor = [[UIColor blackColor] colorWithAlphaComponent:0.5];
        self.contentView.tj_y = CGRectGetMaxY(self.maskView.frame) - 170 - TabbarSafeBottomMargin;
    }];
    
}

- (void)alertWithTitle:(NSString *)title message:(NSString *)message buttonTitle: (NSString *)buttonTitle {
    [[[UIAlertView alloc] initWithTitle:title message:message delegate:self cancelButtonTitle:buttonTitle otherButtonTitles:nil] show];
}

- (void)dealloc {
    TJLog(@"dealloc");
}

@end
