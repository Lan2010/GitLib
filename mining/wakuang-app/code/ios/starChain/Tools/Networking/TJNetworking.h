//
//  TJNetworking.h
//  qhz
//
//  Created by 夏铁军 on 16/10/25.
//  Copyright © 2016年 qhz. All rights reserved.
//

#import <AFNetworking.h>


typedef NS_ENUM(NSInteger, XLUpdataFileType) {
    XLUpdataFileTypeImage,
    XLUpdataFileTypeFile
};

@interface TJNetworking : AFHTTPSessionManager

+ (instancetype)shareNetworking;

- (void)requestWithUrl:(NSString *)url
            parameters:(id)parameters
               success:(void (^)(id))success
               failure:(void (^)(id))failure;

- (void)uploadFileType:(XLUpdataFileType)type
                   Url:(NSString *)url
              fileData:(NSData *)fileData
            parameters:(id)parameters
               success:(void (^)(id))success
               failure:(void (^)(id))failure;


@end
