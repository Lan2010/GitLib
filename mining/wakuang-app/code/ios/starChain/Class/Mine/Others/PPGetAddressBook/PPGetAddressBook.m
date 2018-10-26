//
//  PPAddressBook.m
//  PPAddressBook
//
//  Created by AndyPang on 16/8/17.
//  Copyright © 2016年 AndyPang. All rights reserved.
//

#import "PPGetAddressBook.h"

#define kPPAddressBookHandle [PPAddressBookHandle sharedAddressBookHandle]

#define START NSDate *startTime = [NSDate date]
#define END NSLog(@"Time: %f", -[startTime timeIntervalSinceNow])

@implementation PPGetAddressBook

+ (void)requestAddressBookAuthorization {
    
    [kPPAddressBookHandle requestAuthorizationWithSuccessBlock:^{
        
    } failure:^{
        
    }];
}

+ (void)initialize
{
    [self getOrderAddressBook:nil authorizationFailure:nil];
}

#pragma mark - 获取原始顺序所有联系人
+ (void)getOriginalAddressBook:(AddressBookArrayBlock)addressBookArray authorizationFailure:(AuthorizationFailure)failure
{
    // 将耗时操作放到子线程
    dispatch_queue_t queue = dispatch_queue_create("addressBook.array", DISPATCH_QUEUE_SERIAL);
    
    dispatch_async(queue, ^{
        
        NSMutableArray *array = [NSMutableArray array];
        [kPPAddressBookHandle getAddressBookDataSource:^(PPPersonModel *model) {
            
            [array addObject:model];
            
        } authorizationFailure:^{
            
            dispatch_async(dispatch_get_main_queue(), ^{
                failure ? failure() : nil;
            });
        }];
        
        // 将联系人数组回调到主线程
        dispatch_async(dispatch_get_main_queue(), ^{
            addressBookArray ? addressBookArray(array) : nil ;
        });
    });
    
}

#pragma mark - 获取按A~Z顺序排列的所有联系人
+ (void)getOrderAddressBook:(AddressBookDictBlock)addressBookInfo authorizationFailure:(AuthorizationFailure)failure
{
    
    // 将耗时操作放到子线程
    dispatch_queue_t queue = dispatch_queue_create("addressBook.infoDict", DISPATCH_QUEUE_SERIAL);
    
    dispatch_async(queue, ^{
        
        NSMutableDictionary *addressBookDict = [NSMutableDictionary dictionary];
        
        
        [kPPAddressBookHandle getAddressBookDataSource:^(PPPersonModel *model) {
            
            
            //获取到姓名的大写首字母
            NSString *firstLetterString =  [model.name getFirstLetterString];
            //如果该字母对应的联系人模型不为空,则将此联系人模型添加到此数组中
            if (addressBookDict[firstLetterString])
            {
                    [addressBookDict[firstLetterString] addObject:model];
             
            }
            //没有出现过该首字母，则在字典中新增一组key-value
            else
            {
                //创建新发可变数组存储该首字母对应的联系人模型
                NSMutableArray *arrGroupNames = [NSMutableArray array];
                [arrGroupNames addObject:model];
                //将首字母-姓名数组作为key-value加入到字典中
                [addressBookDict setObject:arrGroupNames forKey:firstLetterString];
            }
        } authorizationFailure:^{
            
            dispatch_async(dispatch_get_main_queue(), ^{
                failure ? failure() : nil;
            });
        }];
        
        // 将addressBookDict字典中的所有Key值进行排序: A~Z
        NSArray *nameKeys = [[addressBookDict allKeys] sortedArrayUsingSelector:@selector(compare:)];
        
        // 将 "#" 排列在 A~Z 的后面
        if ([nameKeys.firstObject isEqualToString:@"#"])
        {
            NSMutableArray *mutableNamekeys = [NSMutableArray arrayWithArray:nameKeys];
            [mutableNamekeys insertObject:nameKeys.firstObject atIndex:nameKeys.count];
            [mutableNamekeys removeObjectAtIndex:0];
            
            dispatch_async(dispatch_get_main_queue(), ^{
                addressBookInfo ? addressBookInfo(addressBookDict,mutableNamekeys) : nil;
            });
            return;
        }
        
        NSLog(@"addressBookDict = %@", addressBookDict);
        NSLog(@"nameKeys = %@", nameKeys);

        
        // 将排序好的通讯录数据回调到主线程
        dispatch_async(dispatch_get_main_queue(), ^{
            
            
            addressBookInfo ? addressBookInfo(addressBookDict,nameKeys) : nil;
        });
        
    });
    
}

@end
