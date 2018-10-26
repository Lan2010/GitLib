//
//  NSObject+category.m
//  starChain
//
//  Created by rlx on 2018/6/6.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import "NSString+category.h"

@implementation NSString (sandBox)

- (instancetype)tj_cachePath {
    return  [[NSSearchPathForDirectoriesInDomains(NSCachesDirectory, NSUserDomainMask, YES) lastObject] stringByAppendingPathComponent:[self lastPathComponent]];
}

- (instancetype)tj_documentsPath {
    return [[NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES) lastObject] stringByAppendingPathComponent:[self lastPathComponent]];
}

- (instancetype)tj_tempPath {
    return [NSTemporaryDirectory() stringByAppendingPathComponent:[self lastPathComponent]];
}

@end

@implementation NSString (category)

+ (BOOL)pureDigitalCharacters:(NSString *)string {
    string = [string stringByTrimmingCharactersInSet:[NSCharacterSet decimalDigitCharacterSet]];
    if(string.length > 0) {
        return NO;
    }
    return YES;
}

- (nonnull NSString *)replacingEmptyString {
    if ([self containsString:@" "]) return [self stringByReplacingOccurrencesOfString:@" " withString:@""];
    return self;
}

- (nonnull NSString *)stringEscape {
    return [self stringByAddingPercentEncodingWithAllowedCharacters:[NSCharacterSet characterSetWithCharactersInString:@"#%<>[\\]^`{|}\"]+"].invertedSet];
}

- (instancetype)hiddenAmongPhone {
    if (self.length == 11) return [self stringByReplacingCharactersInRange:NSMakeRange(3, 4) withString:@"****"];
    return @"";
}

- (CGSize)getStringSizeWithWidth:(CGFloat)width fontSize:(CGFloat)fontSize {
    return  [self boundingRectWithSize:CGSizeMake(width, CGFLOAT_MAX) options:NSStringDrawingUsesLineFragmentOrigin attributes:@{NSFontAttributeName: [UIFont systemFontOfSize:fontSize]} context:nil].size;
}

// 十六进制转换为普通字符串的。
+ (NSString *)stringFromHexString:(NSString *)hexString { //
    
    char *myBuffer = (char *)malloc((int)[hexString length] / 2 + 1);
    bzero(myBuffer, [hexString length] / 2 + 1);
    for (int i = 0; i < [hexString length] - 1; i += 2) {
        unsigned int anInt;
        NSString * hexCharStr = [hexString substringWithRange:NSMakeRange(i, 2)];
        NSScanner * scanner = [[NSScanner alloc] initWithString:hexCharStr];
        [scanner scanHexInt:&anInt];
        myBuffer[i / 2] = (char)anInt;
    }
    NSString *unicodeString = [NSString stringWithCString:myBuffer encoding:4];
    return unicodeString;
    
}

//普通字符串转换为十六进制的。

+ (NSString *)hexStringFromString:(NSString *)string{
    NSData *myD = [string dataUsingEncoding:NSUTF8StringEncoding];
    Byte *bytes = (Byte *)[myD bytes];
    //下面是Byte 转换为16进制。
    NSString *hexStr=@"";
    for(int i=0;i<[myD length];i++) {
        NSString *newHexStr = [NSString stringWithFormat:@"%x",bytes[i]&0xff];///16进制数
        if([newHexStr length]==1)  hexStr = [NSString stringWithFormat:@"%@0%@",hexStr,newHexStr];
        else hexStr = [NSString stringWithFormat:@"%@%@",hexStr,newHexStr];
    }
    return hexStr;
}

+ (NSString *)convertDataToHexStr:(NSData *)data {
    
    if (!data || [data length] == 0) {
        return @"";
    }
    NSMutableString *string = [[NSMutableString alloc] initWithCapacity:[data length]];
    
    [data enumerateByteRangesUsingBlock:^(const void *bytes, NSRange byteRange, BOOL *stop) {
        unsigned char *dataBytes = (unsigned char*)bytes;
        for (NSInteger i = 0; i < byteRange.length; i++) {
            NSString *hexStr = [NSString stringWithFormat:@"%x", (dataBytes[i]) & 0xff];
            if ([hexStr length] == 2) {
                [string appendString:hexStr];
            } else {
                [string appendFormat:@"0%@", hexStr];
            }
        }
    }];
    
    return string;
}

+ (NSString *)hexStringFromData:(NSData *)myD{
    
    Byte *bytes = (Byte *)[myD bytes];
    //下面是Byte 转换为16进制。
    NSString *hexStr=@"";
    for(int i=0;i<[myD length];i++)
        
    {
        NSString *newHexStr = [NSString stringWithFormat:@"%x",bytes[i] & 0xff];///16进制数
        
        if([newHexStr length]==1)
            
            hexStr = [NSString stringWithFormat:@"%@0%@",hexStr,newHexStr];
        else
            
            hexStr = [NSString stringWithFormat:@"%@%@",hexStr,newHexStr];
    }
    TJLog(@"%@",hexStr);
    
    return hexStr;
}

+ (NSData *)convertHexStrToData:(NSString *)str {
    if (!str || [str length] == 0) {
        return nil;
    }
    
    NSMutableData *hexData = [[NSMutableData alloc] initWithCapacity:8];
    NSRange range;
    if ([str length] % 2 == 0) {
        range = NSMakeRange(0, 2);
    } else {
        range = NSMakeRange(0, 1);
    }
    for (NSInteger i = range.location; i < [str length]; i += 2) {
        unsigned int anInt;
        NSString *hexCharStr = [str substringWithRange:range];
        NSScanner *scanner = [[NSScanner alloc] initWithString:hexCharStr];
        
        [scanner scanHexInt:&anInt];
        NSData *entity = [[NSData alloc] initWithBytes:&anInt length:1];
        [hexData appendData:entity];
        
        range.location += range.length;
        range.length = 2;
    }
    
    //    TJLog(@"hexdata: %@", hexData);
    return hexData;
}

@end

@implementation NSString (attributedString)

- (nonnull NSAttributedString *)attributedStringWithLoction:(NSInteger)loction foregroundFont:(CGFloat)foregroundFont backgroundFont:(CGFloat)backgroundFont color:(nonnull UIColor *)color {
    NSMutableAttributedString *newAttrStr = [[NSMutableAttributedString alloc] initWithString: self];
    [newAttrStr addAttribute:NSFontAttributeName value:UIFont_Px(foregroundFont) range:NSMakeRange(0,loction)];
    [newAttrStr addAttribute:NSFontAttributeName value:UIFont_Px(backgroundFont) range:NSMakeRange(loction,self.length - loction)];
    [newAttrStr addAttribute:NSForegroundColorAttributeName value:color range:NSMakeRange(0,self.length)];
    return  newAttrStr.copy;
}

- (nonnull NSAttributedString *)attributedStringWithLoction:(NSInteger)loction foregroundFont:(CGFloat)foregroundFont backgroundFont:(CGFloat)backgroundFont foregroundcolor:(nonnull UIColor *)foregroundcolor backgroundColor:(nonnull UIColor *)backgroundColor {
    NSMutableAttributedString *newAttrStr = [[NSMutableAttributedString alloc] initWithString: self];
    [newAttrStr addAttribute:NSFontAttributeName value:UIFont_Px(foregroundFont) range:NSMakeRange(0,(loction))];
    [newAttrStr addAttribute:NSFontAttributeName value:UIFont_Px(backgroundFont) range:NSMakeRange(loction,self.length - (loction))];
    [newAttrStr addAttribute:NSForegroundColorAttributeName value:foregroundcolor range:NSMakeRange(0,self.length)];
    [newAttrStr addAttribute:NSForegroundColorAttributeName value:backgroundColor range:NSMakeRange(loction,self.length - (loction))];
    return newAttrStr.copy;
    
}

- (nonnull NSAttributedString *)attributedStringWithLoction:(NSInteger)loction foregroundFont:(CGFloat)foregroundFont backgroundFont:(CGFloat)backgroundFont weight:(CGFloat)weight color:(nonnull UIColor *)color {
    NSMutableAttributedString *newAttrStr = [[NSMutableAttributedString alloc] initWithString: self];
    if (@available(iOS 8.2, *)) {
        [newAttrStr addAttribute:NSFontAttributeName value:[UIFont systemFontOfSize:foregroundFont weight:weight] range:NSMakeRange(0,loction)];
        [newAttrStr addAttribute:NSFontAttributeName value:[UIFont systemFontOfSize:backgroundFont weight:weight] range:NSMakeRange(loction,self.length - loction)];
    } else {
        [newAttrStr addAttribute:NSFontAttributeName value:[UIFont systemFontOfSize:foregroundFont] range:NSMakeRange(0,loction)];
        [newAttrStr addAttribute:NSFontAttributeName value:[UIFont systemFontOfSize:backgroundFont] range:NSMakeRange(loction,self.length - loction)];
    }
    [newAttrStr addAttribute:NSForegroundColorAttributeName value:color range:NSMakeRange(0,self.length)];
    return  newAttrStr.copy;
}

- (nonnull NSAttributedString *)attributedStringWithRanger:(NSRange)ranger foregroundFont:(CGFloat)foregroundFont backgroundFont:(CGFloat)backgroundFont foregroundcolor:(nonnull UIColor *)foregroundcolor backgroundColor:(nonnull UIColor *)backgroundColor {
    
    NSMutableAttributedString *newAttrStr = [[NSMutableAttributedString alloc] initWithString: self];
    [newAttrStr addAttribute:NSFontAttributeName value:UIFont_Px(foregroundFont) range:ranger];
    [newAttrStr addAttribute:NSFontAttributeName value:UIFont_Px(backgroundFont) range:ranger];
    [newAttrStr addAttribute:NSFontAttributeName value:UIFont_Px(foregroundFont) range:ranger];
    
    [newAttrStr addAttribute:NSForegroundColorAttributeName value:foregroundcolor range:ranger];
    [newAttrStr addAttribute:NSForegroundColorAttributeName value:backgroundColor range:ranger];
    [newAttrStr addAttribute:NSForegroundColorAttributeName value:foregroundcolor range:ranger];
    
    return  newAttrStr.copy;
}

- (nonnull NSAttributedString *)attributedStringWithNumberString:(nonnull NSString *)numberString numberStringColor:(nullable UIColor *)numberStringColor {
    NSMutableAttributedString *newAttrStr = [[NSMutableAttributedString alloc] initWithString:self];
    [newAttrStr addAttribute:NSForegroundColorAttributeName value:numberStringColor range:[self rangeOfString:numberString]];
    return newAttrStr;
}

- (nonnull NSAttributedString *)titleMargin:(CGFloat)magrin {
    return [self titleMargin:magrin withAlignment:NSTextAlignmentLeft];
}

- (NSAttributedString *)titleKernSpacing:(CGFloat)kernSpacing {
    NSMutableAttributedString *attributedString =  [[NSMutableAttributedString alloc] initWithString:self attributes:@{NSKernAttributeName : @(kernSpacing)}];
    return attributedString;
}

- (NSAttributedString *)titleMargin:(CGFloat)magrin withAlignment:(NSTextAlignment)alignment {
    NSMutableAttributedString *attributedString = [[NSMutableAttributedString alloc] initWithString:self];
    NSMutableParagraphStyle *paragraphStyle = [[NSMutableParagraphStyle alloc] init];
    [paragraphStyle setLineSpacing:magrin];
    paragraphStyle.alignment = alignment;
    [attributedString addAttribute:NSParagraphStyleAttributeName value:paragraphStyle range:NSMakeRange(0, self.length)];
    return attributedString;
}

+ (nonnull NSString *)filterHTML:(nonnull NSString *)html {
    if (!html) return nil;
    NSScanner *scanner = [NSScanner scannerWithString:html];
    NSString *text = nil;
    while([scanner isAtEnd] == NO) {
        [scanner scanUpToString:@"<" intoString:nil];
        //找到标签的结束位置
        [scanner scanUpToString:@">" intoString:&text];
        //替换字符
        html = [html stringByReplacingOccurrencesOfString:[NSString stringWithFormat:@"%@>",text] withString:@""];
        html = [html replacingEmptyString];
    }
    return html;
}

+ (nonnull NSAttributedString *)attributedStringWithHtmlText:(nonnull NSString *)text lineSpace:(CGFloat)lineSpace {
    return [self attributedStringWithHtmlText:text lineSpace:lineSpace font:0 color:nil];
}

+ (nonnull NSAttributedString *)attributedStringWithHtmlText:(nonnull NSString *)text lineSpace:(CGFloat)lineSpace font:(CGFloat)font {
    return [self attributedStringWithHtmlText:text lineSpace:lineSpace font:font color:nil];
}


+ (nonnull NSAttributedString *)attributedStringWithHtmlText:(nonnull NSString *)text lineSpace:(CGFloat)lineSpace font:(CGFloat)font color:(nullable UIColor *)color {
    NSMutableAttributedString *attrStr = [[NSMutableAttributedString alloc] initWithData:[text dataUsingEncoding:NSUnicodeStringEncoding] options:@{ NSDocumentTypeDocumentAttribute: NSHTMLTextDocumentType } documentAttributes:nil error:nil];
    if (font) [attrStr addAttribute:NSFontAttributeName value:UIFont_Px(font) range:NSMakeRange(0,attrStr.length)];
    if (color) [attrStr addAttribute:NSForegroundColorAttributeName value:color range:NSMakeRange(0,attrStr.length)];
    NSMutableParagraphStyle *paragraphStyle = [[NSMutableParagraphStyle alloc] init];
    paragraphStyle.lineBreakMode = NSLineBreakByTruncatingTail;
    [paragraphStyle setLineSpacing:lineSpace];
    [attrStr addAttribute:NSParagraphStyleAttributeName value:paragraphStyle range:NSMakeRange(0, [attrStr length])];
    return attrStr.copy;
}

#pragma mark - 获取联系人姓名首字母(传入汉字字符串, 返回大写拼音首字母)
- (NSString *)getFirstLetterString {
    /**
     * **************************************** START ***************************************
     * 之前PPGetAddressBook对联系人排序时在中文转拼音这一部分非常耗时
     * 参考博主-庞海礁先生的一文:iOS开发中如何更快的实现汉字转拼音 http://www.olinone.com/?p=131
     * 使PPGetAddressBook对联系人排序的性能提升 3~6倍, 非常感谢!
     */
    NSMutableString *mutableString = [NSMutableString stringWithString:self];
    CFStringTransform((CFMutableStringRef)mutableString, NULL, kCFStringTransformToLatin, false);
    NSString *pinyinString = [mutableString stringByFoldingWithOptions:NSDiacriticInsensitiveSearch locale:[NSLocale currentLocale]];
    /**
     *  *************************************** END ******************************************
     */
    
    // 将拼音首字母装换成大写
    NSString *strPinYin = [[self polyphoneStringHandle:self pinyinString:pinyinString] uppercaseString];
    // 截取大写首字母
    NSString *firstString = [strPinYin substringToIndex:1];
    // 判断姓名首位是否为大写字母
    NSString * regexA = @"^[A-Z]$";
    NSPredicate *predA = [NSPredicate predicateWithFormat:@"SELF MATCHES %@", regexA];
    // 获取并返回首字母
    return [predA evaluateWithObject:firstString] ? firstString : @"#";
    
}

- (NSString *)polyphoneStringHandle:(NSString *)aString pinyinString:(NSString *)pinyinString {
    if ([aString hasPrefix:@"长"]) { return @"chang";}
    if ([aString hasPrefix:@"沈"]) { return @"shen"; }
    if ([aString hasPrefix:@"厦"]) { return @"xia";  }
    if ([aString hasPrefix:@"地"]) { return @"di";   }
    if ([aString hasPrefix:@"重"]) { return @"chong";}
    return pinyinString;
}

@end
