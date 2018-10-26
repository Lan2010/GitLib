//
//  HBRSAHandler.m
//  iOSRSAHandlerDemo
//
//  Created by wangfeng on 15/10/19.
//  Copyright (c) 2015年 HustBroventure. All rights reserved.
//
#import "HBRSAHandler.h"
#include <openssl/rsa.h>
#include <openssl/pem.h>
#include <openssl/err.h>
#include <openssl/md5.h>

typedef enum {
    RSA_PADDING_TYPE_NONE       = RSA_NO_PADDING,
    RSA_PADDING_TYPE_PKCS1      = RSA_PKCS1_PADDING,
    RSA_PADDING_TYPE_SSLV23     = RSA_SSLV23_PADDING
}RSA_PADDING_TYPE;

#define  PADDING   RSA_PADDING_TYPE_PKCS1


//- (NSString *)decryptWithPublicKey:(NSString*)content;
//- (NSString *)encryptWithPrivatecKey1:(NSString*)content;

@implementation HBRSAHandler {
    RSA* _rsa_pub;
    RSA* _rsa_pri;
}

static HBRSAHandler *instance = nil;

+ (instancetype)shareRSAHandler {
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        instance = [[HBRSAHandler alloc] init];
        [instance importKeyWithType:KeyTypePublic andkeyString:publickey];
        [instance importKeyWithType:KeyTypePrivate andkeyString:privateKey];
    });
    return instance;
    
}

-(BOOL)importKeyWithType:(KeyType)type andPath:(NSString *)path {
//     TJLog(@"path = %@",path);
    BOOL status = NO;
    const char* cPath = [path cStringUsingEncoding:NSUTF8StringEncoding];
    FILE* file = fopen(cPath, "rb");
    if (!file) {
        return status;
    }
    if (type == KeyTypePublic) {
        _rsa_pub = NULL;
        if((_rsa_pub = PEM_read_RSA_PUBKEY(file, NULL, NULL, NULL))){
            status = YES;
        }
        
        
    }else if(type == KeyTypePrivate){
        _rsa_pri = NULL;
        if ((_rsa_pri = PEM_read_RSAPrivateKey(file, NULL, NULL, NULL))) {
            status = YES;
        }

    }
    fclose(file);
    return status;
}

- (BOOL)importKeyWithType:(KeyType)type andkeyString:(NSString *)keyString {
    if (!keyString) {
        return NO;
    }
    BOOL status = NO;
    BIO *bio = NULL;
    RSA *rsa = NULL;
    bio = BIO_new(BIO_s_file());
    NSString* temPath = NSTemporaryDirectory();
    NSString* rsaFilePath = [temPath stringByAppendingPathComponent:@"RSAKEY"];
    NSString* formatRSAKeyString = [self formatRSAKeyWithKeyString:keyString andKeytype:type];
    BOOL writeSuccess = [formatRSAKeyString writeToFile:rsaFilePath atomically:YES encoding:NSUTF8StringEncoding error:nil];
    if (!writeSuccess) {
        return NO;
    }
    const char* cPath = [rsaFilePath cStringUsingEncoding:NSUTF8StringEncoding];
    BIO_read_filename(bio, cPath);
    if (type == KeyTypePrivate) {
        rsa = PEM_read_bio_RSAPrivateKey(bio, NULL, NULL, "");
        _rsa_pri = rsa;
        if (rsa != NULL && 1 == RSA_check_key(rsa)) {
            status = YES;
        } else {
            status = NO;
        }


    }
    else{
        rsa = PEM_read_bio_RSA_PUBKEY(bio, NULL, NULL, NULL);
        _rsa_pub = rsa;
        if (rsa != NULL) {
            status = YES;
        } else {
            status = NO;
        }
    }
    
           BIO_free_all(bio);
    [[NSFileManager defaultManager] removeItemAtPath:rsaFilePath error:nil];
    return status;
}

- (NSString *)encryptWithPrivatecKey1:(NSString*)content {
    if (!_rsa_pri) {
        TJLog(@"please import privatec key first");
        return nil;
    }
    int status;
    int length  = (int)[content length];
    unsigned char input[length + 1];
    bzero(input, length + 1);
    int i = 0;
    for (; i < length; i++)
    {
        input[i] = [content characterAtIndex:i];
    }
    
    NSInteger  flen = [self getBlockSizeWithRSA_PADDING_TYPE:PADDING andRSA:_rsa_pri];
    
    char *encData = (char*)malloc(flen);
    bzero(encData, flen);
    status = RSA_private_encrypt(length, (unsigned char*)input, (unsigned char*)encData, _rsa_pri, PADDING);
    if (status){
        NSData *returnData = [NSData dataWithBytes:encData length:status];
        free(encData);
        encData = NULL;
        NSString *ret = [returnData base64EncodedStringWithOptions: NSDataBase64Encoding64CharacterLineLength];
        return ret;
    }
    
    free(encData);
    encData = NULL;
    
    return nil;
}

- (NSData *)encrypt_privatecKey:(NSString*)content {
    if (!_rsa_pri) {
        TJLog(@"please import privatec key first");
        return nil;
    }
    int status;
    int length  = (int)[content length];
    unsigned char input[length + 1];
    bzero(input, length + 1);
    int i = 0;
    for (; i < length; i++) {
        input[i] = [content characterAtIndex:i];
    }
    
    NSInteger  flen = [self getBlockSizeWithRSA_PADDING_TYPE:PADDING andRSA:_rsa_pri];
    
    char *encData = (char*)malloc(flen);
    bzero(encData, flen);
    status = RSA_private_encrypt(length, (unsigned char*)input, (unsigned char*)encData, _rsa_pri, PADDING);
    if (status) {
        NSData *returnData = [NSData dataWithBytes:encData length:status];
        free(encData);
        encData = NULL;
        return returnData;
    }
    
    free(encData);
    encData = NULL;
    
    return nil;
}

- (NSString *)decrypt_publicKey:(NSData *)data {
    if (!_rsa_pub) {
        TJLog(@"please import private key first");
        return nil;
    }    int status;
    
    int length = (int)[data length];
    NSInteger flen = [self getBlockSizeWithRSA_PADDING_TYPE:PADDING andRSA:_rsa_pub];
    char *decData = (char*)malloc(flen);
    bzero(decData, flen);
    
    status = RSA_public_decrypt(length, (unsigned char*)[data bytes], (unsigned char*)decData, _rsa_pub, PADDING);
    
    if (status) {
        NSMutableString *decryptString = [[NSMutableString alloc] initWithBytes:decData length:strlen(decData) encoding:NSASCIIStringEncoding];
        free(decData);
        decData = NULL;
        return decryptString;
    }
    free(decData);
    decData = NULL;
    
    return nil;
}

- (NSString *)decryptWithPublicKey:(NSString*)content {
    if (!_rsa_pub) {
        TJLog(@"please import private key first");
        return nil;
    }    int status;
    
        //NSData *data = [content base64DecodedData];
    NSData *data = [[NSData alloc]initWithBase64EncodedString:content options:NSDataBase64DecodingIgnoreUnknownCharacters];
    int length = (int)[data length];
    
    NSInteger flen = [self getBlockSizeWithRSA_PADDING_TYPE:PADDING andRSA:_rsa_pub];
    char *decData = (char*)malloc(flen);
    bzero(decData, flen);
    
    status = RSA_public_decrypt(length, (unsigned char*)[data bytes], (unsigned char*)decData, _rsa_pub, PADDING);
   
    if (status)
        {
        NSMutableString *decryptString = [[NSMutableString alloc] initWithBytes:decData length:strlen(decData) encoding:NSASCIIStringEncoding];
        free(decData);
        decData = NULL;
            
             TJLog(@"decryptString = %@",decryptString);
        
        return decryptString;
        }
    
    free(decData);
    decData = NULL;
    
    return nil;
}

- (int)getBlockSizeWithRSA_PADDING_TYPE:(RSA_PADDING_TYPE)padding_type andRSA:(RSA*)rsa {
    int len = RSA_size(rsa);
    
    if (padding_type == RSA_PADDING_TYPE_PKCS1 || padding_type == RSA_PADDING_TYPE_SSLV23) {
        len -= 11;
    }
    
    return len;
}

-(NSString*)formatRSAKeyWithKeyString:(NSString*)keyString andKeytype:(KeyType)type {
    NSInteger lineNum = -1;
    NSMutableString *result = [NSMutableString string];
    
    if (type == KeyTypePrivate) {
        [result appendString:@"-----BEGIN PRIVATE KEY-----\n"];
        lineNum = 79;
    }else if(type == KeyTypePublic){
    [result appendString:@"-----BEGIN PUBLIC KEY-----\n"];
         lineNum = 76;
    }
   
    int count = 0;
    for (int i = 0; i < [keyString length]; ++i) {
        unichar c = [keyString characterAtIndex:i];
        if (c == '\n' || c == '\r') {
            continue;
        }
        [result appendFormat:@"%c", c];
        if (++count == lineNum) {
            [result appendString:@"\n"];
            count = 0;
        }
    }
    if (type == KeyTypePrivate) {
        [result appendString:@"\n-----END PRIVATE KEY-----"];
       
    }else if(type == KeyTypePublic){
        [result appendString:@"\n-----END PUBLIC KEY-----"];
    }
    return result;
 
}

@end
