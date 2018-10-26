//
//  XLPhoneNumberformat.m
//  starChain
//
//  Created by rlx on 2018/6/13.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import "XLPhoneNumberformat.h"

@implementation XLPhoneNumberformat

+ (BOOL)phoneNumberformatWithTextField:(UITextField *)textField shouldChangeCharactersInRange:(NSRange)range replacementString:(NSString *)string formatType:(XLFormatType)formatType {
    if (textField) {
        NSString* text = textField.text;
        //删除
        if([string isEqualToString:@""]){
            
            //删除一位
            if(range.length == 1){
                //最后一位,遇到空格则多删除一次
                if (range.location == text.length-1 ) {
                    if ([text characterAtIndex:text.length-1] == ' ') {
                        [textField deleteBackward];
                    }
                    return YES;
                } else { //从中间删除
                    NSInteger offset = range.location;
                    
                    if (range.location < text.length && [text characterAtIndex:range.location] == ' ' && [textField.selectedTextRange isEmpty]) {
                        [textField deleteBackward];
                        offset --;
                    }
                    [textField deleteBackward];
                    textField.text = [self parseString:textField.text formatType:formatType];
                    UITextPosition *newPos = [textField positionFromPosition:textField.beginningOfDocument offset:offset];
                    textField.selectedTextRange = [textField textRangeFromPosition:newPos toPosition:newPos];
                    return NO;
                }
            } else if (range.length > 1) {
                BOOL isLast = NO;
                //如果是从最后一位开始
                if(range.location + range.length == textField.text.length ){
                    isLast = YES;
                }
                [textField deleteBackward];
                textField.text = [self parseString:textField.text formatType:formatType];
                
                NSInteger offset = range.location;
                
                BOOL conditions = (formatType == XLFormatTypePhoneNumber) ? (range.location == 3 || range.location  == 8) : (range.location == 6 || range.location  == 15);
                if (conditions) {
                    offset ++;
                }
                if (isLast) {
                    //光标直接在最后一位了
                } else{
                    UITextPosition *newPos = [textField positionFromPosition:textField.beginningOfDocument offset:offset];
                    textField.selectedTextRange = [textField textRangeFromPosition:newPos toPosition:newPos];
                }
                return NO;
            } else{
                return YES;
            }
        } else if(string.length >0){
            
            int length = (formatType == XLFormatTypePhoneNumber) ? 11 : 18;
            
            //限制输入字符个数
            if (([self noneSpaseString:textField.text].length + string.length - range.length > length) ) {
                return NO;
            }
            //判断是否是纯数字(搜狗，百度输入法，数字键盘居然可以输入其他字符)
            if (![NSString pureDigitalCharacters:string]) {
                return NO;
            }
            
            [textField insertText:string];
            textField.text = [self parseString:textField.text formatType:formatType];
            
            NSInteger offset = range.location + string.length;
            if (range.location == 3 || range.location  == 8) {
                offset ++;
            }
            UITextPosition *newPos = [textField positionFromPosition:textField.beginningOfDocument offset:offset];
            textField.selectedTextRange = [textField textRangeFromPosition:newPos toPosition:newPos];
            return NO;
        } else{
            return YES;
        }
    }
    return YES;
}

+ (NSString *)noneSpaseString:(NSString *)string  {
    return [string stringByReplacingOccurrencesOfString:@" " withString:@""];
}

+ (NSString *)parseString:(NSString *)string  formatType:(XLFormatType)formatType {
    if (!string) {
        return nil;
    }
    
    NSMutableString *mStr = [NSMutableString stringWithString:[string stringByReplacingOccurrencesOfString:@" " withString:@""]];
    
    if (formatType == XLFormatTypePhoneNumber) {
        if (mStr.length > 3) {
            [mStr insertString:@" " atIndex:3];
        }
        if (mStr.length > 8) {
            [mStr insertString:@" " atIndex:8];
        }
    } else {
        if (mStr.length > 6) {
            [mStr insertString:@" " atIndex:6];
        }
        if (mStr.length > 15) {
            [mStr insertString:@" " atIndex:15];
        }
    }
    return  mStr;
}


@end
