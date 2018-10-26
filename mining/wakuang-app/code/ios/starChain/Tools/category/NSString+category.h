//
//  NSObject+category.h
//  starChain
//
//  Created by rlx on 2018/6/6.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface NSString (sandBox)

@property (nullable, nonatomic, readonly) NSString *tj_documentsPath;
@property (nullable, nonatomic, readonly) NSString *tj_cachePath;
@property (nullable, nonatomic, readonly) NSString *tj_tempPath;

@end

@interface NSString (category)

+ (BOOL)pureDigitalCharacters:(NSString *)string;

- (instancetype)hiddenAmongPhone;

- (nonnull NSString *)stringEscape;

- (nonnull NSString *)replacingEmptyString;

- (CGSize)getStringSizeWithWidth:(CGFloat)width fontSize:(CGFloat)fontSize;

+ (nonnull NSString *)hexStringFromData:( NSData * _Nullable )myD;

+ (nonnull NSString *)convertDataToHexStr:(nonnull NSData *)data;

+ (nonnull NSData *)convertHexStrToData:(nonnull NSString *)str;

+ (nonnull NSString *)stringFromHexString:(nonnull NSString *)hexString;

+ (nonnull NSString *)hexStringFromString:(nonnull NSString *)string;

@end

@interface NSString (attributedString)

/** 通过一个位置来设置两段的字符串, 可以设置不同字体大小  */

- (nonnull NSAttributedString *)attributedStringWithLoction:(NSInteger)loction foregroundFont:(CGFloat)foregroundFont backgroundFont:(CGFloat)backgroundFont color:(nonnull UIColor *)color;

/** 通过一个位置来设置两段的字符串, 可以设置不同颜色 */
- (nonnull NSAttributedString *)attributedStringWithLoction:(NSInteger)loction foregroundFont:(CGFloat)foregroundFont backgroundFont:(CGFloat)backgroundFont foregroundcolor:(nonnull UIColor *)foregroundcolor backgroundColor:(nonnull UIColor *)backgroundColor;

/** 通过一个位置来设置两段的字符串, 可以设置不同字体大小, 和字体的粗细  */
- (nonnull NSAttributedString *)attributedStringWithLoction:(NSInteger)loction foregroundFont:(CGFloat)foregroundFont backgroundFont:(CGFloat)backgroundFont weight:(CGFloat)weight color:(nonnull UIColor *)color;

/** 通过开始位置和结束的位置和分成两段, 可以设置不同颜色和字体大小 */
- (nonnull NSAttributedString *)attributedStringWithRanger:(NSRange)ranger foregroundFont:(CGFloat)foregroundFont backgroundFont:(CGFloat)backgroundFont foregroundcolor:(nonnull UIColor *)foregroundcolor backgroundColor:(nonnull UIColor *)backgroundColor;

/** 字符串中设置数字的颜色 */
- (nonnull NSAttributedString *)attributedStringWithNumberString:(nonnull NSString *)numberString numberStringColor:(nullable UIColor *)numberString;

- (NSAttributedString *)titleKernSpacing:(CGFloat)kernSpacing;

- (nonnull NSAttributedString *)titleMargin:(CGFloat)magrin;

- (nonnull NSAttributedString *)titleMargin:(CGFloat)magrin withAlignment:(NSTextAlignment)alignment;

+ (nonnull NSString *)filterHTML:(nonnull NSString *)html;

+ (nonnull NSAttributedString *)attributedStringWithHtmlText:(nonnull NSString *)text lineSpace:(CGFloat)lineSpace;

+ (nonnull NSAttributedString *)attributedStringWithHtmlText:(nonnull NSString *)text lineSpace:(CGFloat)lineSpace font:(CGFloat)font;

+ (nonnull NSAttributedString *)attributedStringWithHtmlText:(nonnull NSString *)text lineSpace:(CGFloat)lineSpace font:(CGFloat)font color:(nullable UIColor *)color;

- (NSString *)getFirstLetterString;

@end

