

#import "NSArray+Log.h"

@implementation NSArray (Log)

/// 打印数组和字典时会自动调用这个方法,在分类中重写这个方法时,在使用时不需要导入头文件
- (NSString *)descriptionWithLocale:(id)locale indent:(NSUInteger)level {
    NSMutableString *stringM = [NSMutableString string];
    [stringM appendString:@"(\n"];
    [self enumerateObjectsUsingBlock:^(id  _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
        [stringM appendFormat:@"\t%@,\n",obj];
    }];
    [stringM appendString:@")\n"];
    return stringM;
}

@end

@implementation NSDictionary (Log)

- (NSString *)descriptionWithLocale:(id)locale indent:(NSUInteger)level {
    NSMutableString *stringM = [NSMutableString string];
    [stringM appendString:@"{\n"];
    [self enumerateKeysAndObjectsUsingBlock:^(id  _Nonnull key, id  _Nonnull obj, BOOL * _Nonnull stop) {
        [stringM appendFormat:@"\t%@ = %@;\n",key,obj];
    }];
    [stringM appendString:@"}\n"];
    return stringM;
}

@end

