//
//  XLPhoneNumberformat.h
//  starChain
//
//  Created by rlx on 2018/6/13.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import <Foundation/Foundation.h>

typedef NS_ENUM(NSInteger, XLFormatType) {
    XLFormatTypePhoneNumber,
    XLFormatTypeIdCard
};

@interface XLPhoneNumberformat : NSObject

+ (BOOL)phoneNumberformatWithTextField:(UITextField *)textField shouldChangeCharactersInRange:(NSRange)range replacementString:(NSString *)string formatType:(XLFormatType)formatType;

@end
