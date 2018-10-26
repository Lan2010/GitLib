//
//  XLAddressBookContentController.m
//  starChain
//
//  Created by rlx on 2018/7/12.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import "XLAddressBookContentController.h"
#import "PPPersonModel.h"
#import "XLPersonModel.h"
#import "XLPageLoadingView.h"


@interface XLAddressBookContentController ()

@property (strong, nonatomic) NSArray *addressBookModels;
@property (strong, nonatomic) NSArray *datas;
@property (strong, nonatomic) NSArray *keys;
@property (strong, nonatomic) NSDictionary *dataDict;

@property (weak, nonatomic) XLPageLoadingView *pageLoadingView;


@end

static NSString * const addressBookCell = @"addressBookCell";

@implementation XLAddressBookContentController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    self.tableView.rowHeight = 44;
    self.keys = [NSArray array];
    self.dataDict = [NSDictionary dictionary];
    
    [self.tableView registerClass:[UITableViewCell class] forCellReuseIdentifier:addressBookCell];
    [self.tableView makeConstraints:^(MASConstraintMaker *make) {make.edges.offset(0);}];
    
    
    XLPageLoadingView *pageLoadingView = [XLPageLoadingView loadingAnimationWithSuperView:self.view];
    pageLoadingView.frame = CGRectMake(0, 0, KScreenWidth, KScreenHeight - TOPMAGRIN);
    self.pageLoadingView = pageLoadingView;
    
}


- (void)setType:(NSInteger)type {
    _type = type;
    [self loadAddressBookData];
}

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
    return _keys.count;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    NSString *key = _keys[section];
    return [self.dataDict[key] count];
}

- (NSString *)tableView:(UITableView *)tableView titleForHeaderInSection:(NSInteger)section {
    return _keys[section];
}

- (CGFloat)tableView:(UITableView *)tableView heightForHeaderInSection:(NSInteger)section {
    return 30;
}

- (NSArray *)sectionIndexTitlesForTableView:(UITableView *)tableView{
    return _keys;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:addressBookCell forIndexPath:indexPath];
    NSString *key = _keys[indexPath.section];
    XLPersonModel *model = [self.dataDict[key] objectAtIndex:indexPath.row];
    cell.textLabel.text = model.name;

    return cell;
}

- (NSInteger)tableView:(UITableView *)tableView sectionForSectionIndexTitle:(NSString*)title atIndex:(NSInteger)index {
    
    NSLog(@"index = %ld", (long)index);
    
    return index - 1;
}

#pragma mark - 加载通讯录数据
- (void)loadAddressBookData {
    
    [TJNetworkTool requestWithUrl:@"User.GetMailListResInfo" parameters:@{@"type": @(self.type)} success:^(id data) {
        if ([data[@"code"] intValue] == 0) {
            
            [self.pageLoadingView stopAnimation];
            if (self.loadDataComplete) self.loadDataComplete();
            
            // 根据模型的名字去重
            self.datas = [XLPersonModel mj_objectArrayWithKeyValuesArray:data[@"info"]];
            [self toHeavyWithArray:self.datas complete:^(NSMutableArray *array) {

                self.datas = array;
                
                NSMutableDictionary *addressBookDict = [NSMutableDictionary dictionary];
                [self.datas enumerateObjectsUsingBlock:^(XLPersonModel *model, NSUInteger idx, BOOL * _Nonnull stop) {
                    
                    //获取到姓名的大写首字母
                    NSString *firstLetterString =  [model.name getFirstLetterString];
                    //如果该字母对应的联系人模型不为空,则将此联系人模型添加到此数组中
                    if (addressBookDict[firstLetterString]) {
                        [addressBookDict[firstLetterString] addObject:model];
                        
                    } else { //没有出现过该首字母，则在字典中新增一组key-value
                        //创建新发可变数组存储该首字母对应的联系人模型
                        NSMutableArray *arrGroupNames = [NSMutableArray array];
                        [arrGroupNames addObject:model];
                        //将首字母-姓名数组作为key-value加入到字典中
                        [addressBookDict setObject:arrGroupNames forKey:firstLetterString];
                    }
                    
                }];
                
                // 将addressBookDict字典中的所有Key值进行排序: A~Z
                NSArray *nameKeys = [[addressBookDict allKeys] sortedArrayUsingSelector:@selector(compare:)];
   
                // 将 "#" 排列在 A~Z 的后面
                if ([nameKeys.firstObject isEqualToString:@"#"]) {
                    NSMutableArray *mutableNamekeys = [NSMutableArray arrayWithArray:nameKeys];
                    [mutableNamekeys insertObject:nameKeys.firstObject atIndex:nameKeys.count];
                    [mutableNamekeys removeObjectAtIndex:0];
                    nameKeys = mutableNamekeys;
                }
                
                self.dataDict = addressBookDict;
                self.keys = nameKeys;
                [self.tableView reloadData];
                
                NSLog(@"加载下来的addressBookDict = %@, nameKeys = %@, type = %d", addressBookDict, nameKeys, self.type);
                
            }];
        } else {
            
            self.pageLoadingView.showType = XLShowTypeEmpty;
            
        }
        
    } failure:^(id error) {
        
        
    }];
    
}

#pragma mark - 根据关键字模型去重
- (void)toHeavyWithArray:(NSArray <XLPersonModel *>*)array complete:(void (^)(NSMutableArray *))complete {
    
    dispatch_async(dispatch_get_global_queue(0, 0), ^{
        NSMutableDictionary *tmpDict = [NSMutableDictionary dictionary];
        for (XLPersonModel *model in array) {
            [tmpDict setObject:model forKey:model.name];
        }
        
        dispatch_async(dispatch_get_main_queue(), ^{
            complete([tmpDict.allValues mutableCopy]);
        });
    });
    
}




@end
