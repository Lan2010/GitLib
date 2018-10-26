//
//  XLRealNameModel.h
//  starChain
//
//  Created by rlx on 2018/6/16.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface XLRealNameModel : NSObject

/** 模块的标识码 */
@property (copy, nonatomic) NSString *type_code;
/** 注册送积分 */
@property (copy, nonatomic) NSString *type_name;
/** 可获取的星星数量 */
@property (copy, nonatomic) NSString *type_value;
/** ID */
@property (strong, nonatomic) NSNumber *ID;
/** 1 代表该用户已经获取了 0代表没有 */
@property (strong, nonatomic) NSNumber *status;



@end
