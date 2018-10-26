//
//  TJNetworking.m
//  qhz
//
//  Created by 夏铁军 on 16/10/25.
//  Copyright © 2016年 qhz. All rights reserved.
//

#import "TJNetworking.h"
#import "JKEncrypt.h"
#import "TJUUID.h"
#import "HBRSAHandler.h"
#import "QHZEncryptManager.h"
#import "QHZDecryptManager.h"



@protocol QHZNetworkingDelegate <NSObject>



@optional

- (NSURLSessionDataTask *)dataTaskWithHTTPMethod:(NSString *)method
                                       URLString:(NSString *)URLString
                                      parameters:(id)parameters
                                  uploadProgress:(nullable void (^)(NSProgress *uploadProgress)) uploadProgress
                                downloadProgress:(nullable void (^)(NSProgress *downloadProgress)) downloadProgress
                                         success:(void (^)(NSURLSessionDataTask *, id))success
                                         failure:(void (^)(NSURLSessionDataTask *, NSError *))failure;

@end

@interface TJNetworking ()<QHZNetworkingDelegate>


@end

@implementation TJNetworking

static TJNetworking *instance = nil;


+ (TJNetworking *)shareNetworking {
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        instance = [[TJNetworking alloc] initWithBaseURL:[NSURL URLWithString:baseUrlString]];
        [instance.requestSerializer willChangeValueForKey:@"timeoutInterval"];
        instance.requestSerializer.timeoutInterval = 15.f;
        [instance.requestSerializer didChangeValueForKey:@"timeoutInterval"];
        instance.responseSerializer.acceptableContentTypes = [NSSet setWithObjects:@"text/plain", @"multipart/form-data", @"application/json", @"text/html", @"image/jpeg", @"image/png", @"application/octet-stream", @"text/json", nil];
    });
    return instance;
}

- (void)requestWithUrl:(NSString *)url
            parameters:(id)parameters
               success:(void (^)(id))success
               failure:(void (^)(id))failure {
    
    TJLog(@"%@ ----- parameters = %@", url, parameters);

    [[self dataTaskWithHTTPMethod:@"POST" URLString:[NSString stringWithFormat:@"MobileApi?service=%@", url] parameters:[self encryptionParametersWithParameters:parameters] uploadProgress:nil downloadProgress:nil success:^(NSURLSessionDataTask *task, id responseObject) {

        NSNumber *ret = responseObject[@"ret"];
        int result = [ret intValue];
//
//        TJLog(@"%@ ----- responseObject = %@", url, responseObject);
//

        switch (result) {
            case 200: {
                BOOL succeed = [QHZDecryptManager validationSignWithResponseData: responseObject];
                NSDictionary *data = [QHZDecryptManager decryptWithString:responseObject[@"data"]];
                success(data);
                TJLog(@"%@ ----- data = %@", url, data);

//                                if (succeed) {
//                                    NSDictionary *data = [QHZDecryptManager decryptWithString:responseObject[@"data"]];
//                                    success(data);
//                                    TJLog(@"%@ ----- responseObject = %@", url, data);
//                                } else {
//                                    [[NSNotificationCenter defaultCenter] postNotificationName:tokenErrornNedLoginNotification object:nil userInfo:nil];
//                                }
            }
                break;
            case 4005:
            case 4006: {
                NSDictionary *dict = @{@"error": responseObject[@"error"]};
                failure(dict);
            }
                break;
            case 4007:
            case 4008:
            case 4009: {
                [[NSNotificationCenter defaultCenter] postNotificationName:tokenErrornNedLoginNotification object:nil userInfo:nil];
                [USERDEFAULTS setBool:NO forKey:login];
                REMOVOCOOKIES
            }
                break;
            default: {
                TJLog(@"%@ ----- responseObject = %@", url, responseObject);
            }
                break;
        }
        
    } failure:^(NSURLSessionDataTask *task, NSError *error) {
        TJLog(@"error = %@", error);
        failure(error);
    }] resume];
}

- (void)uploadFileType:(XLUpdataFileType)type
                   Url:(NSString *)url
              fileData:(NSData *)fileData
            parameters:(id)parameters
               success:(void (^)(id))success
               failure:(void (^)(id))failure {
    NSDictionary *responseParameters = [self encryptionParametersWithParameters:parameters];
    
    TJLog(@"responseParameters = %@", responseParameters);
    
    [self POST:[NSString stringWithFormat:@"MobileApi?service=%@", url] parameters:responseParameters constructingBodyWithBlock:^(id<AFMultipartFormData>  _Nonnull formData) {
        
        if (type == XLUpdataFileTypeImage) {
            NSDateFormatter *formatter = [[NSDateFormatter alloc] init];
            [formatter setDateFormat:@"yyMMddHHmmss"];
            NSString *fileName =  [NSString stringWithFormat:@"%@.png",[formatter stringFromDate:[NSDate date]]];
            [formData appendPartWithFileData:fileData name:@"file" fileName:fileName mimeType:@"image.png"];
            
        } else {
            [formData appendPartWithFileData:fileData name:@"file" fileName:@"myRecord.caf" mimeType:@"audio/caf"];
        }
        
        
    } progress:nil success:^(NSURLSessionDataTask * _Nonnull task, id  _Nullable responseObject) {
        NSNumber *ret = responseObject[@"ret"];
        int result = [ret intValue];
        if (result == 200) {
            BOOL succeed = [QHZDecryptManager validationSignWithResponseData: responseObject];
            //            if (succeed) {
            NSDictionary *data = [QHZDecryptManager decryptWithString:responseObject[@"data"]];
            success(data);
            TJLog(@"%@ ----- responseObject = %@", url, data);
            
            //            } else {
            //                [[NSNotificationCenter defaultCenter] postNotificationName:tokenErrornNedLoginNotification object:nil userInfo:nil];
            //            }
        } else if (result == 4009 || result == 4008 || result == 4007) {
            [[NSNotificationCenter defaultCenter] postNotificationName:tokenErrornNedLoginNotification object:nil userInfo:nil];
        } else {
            [[NSNotificationCenter defaultCenter] postNotificationName:resultErrrorNotification object:nil userInfo:nil];
        }
    } failure:^(NSURLSessionDataTask * _Nullable task, NSError * _Nonnull error) {
        
    }];
}
 
- (NSDictionary *)encryptionParametersWithParameters:(NSDictionary *)parameters {
    if (!parameters) parameters = [NSDictionary dictionary];
    NSString *desStr = [JKEncrypt doEncryptStr:[parameters jsonString]];
    NSString *tokenStr = [TJKeychain tj_objectForKey:token];
    NSString *timeInterval = [NSString stringWithFormat:@"%ld",(long)[[NSDate date] timeIntervalSince1970]];
    NSString *uuid = [TJUUID getUUID];
    NSString *v = [NSBundle mainBundle].infoDictionary[@"CFBundleShortVersionString"];
    NSDictionary *dict = @{
                           @"s": @"",
                           @"d": uuid,
                           @"mt": @"3",
                           @"t": timeInterval,
                           @"sv": @"2.0.0",
                           @"v": v,
                           @"token": tokenStr,
                           @"data": desStr
                           };
    
    return [QHZEncryptManager encrptWithParameters:dict];
}

@end
