//
//  XLAddressBookController.m
//  starChain
//
//  Created by rlx on 2018/7/5.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import "XLAddressBookController.h"
#import "PPGetAddressBook.h"
#import "PPPersonModel.h"
#import "XLPersonModel.h"
#import "XLPagingHeaderView.h"
#import "XLAddressBookContentController.h"
#import "XLPageLoadingView.h"


@interface XLAddressBookController ()<UIScrollViewDelegate>

@property (strong, nonatomic) NSArray *keys;
@property (strong, nonatomic) NSDictionary *dataDict;
@property (strong, nonatomic) NSArray *addressBookModels;
@property (strong, nonatomic) NSArray *datas;
@property (strong, nonatomic) NSArray *titles;

@property (weak, nonatomic) XLPageLoadingView *pageLoadingView;
@property (weak, nonatomic) XLPagingHeaderView *headerView;
@property (weak, nonatomic) UIScrollView *mainView;

@property (assign, nonatomic) NSInteger currentIndex;


@end



@implementation XLAddressBookController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    [self addSubView];
    [self loadLocalAddressBook];
}

- (void)addSubView {
    
    self.tj_navigationItem.title = @"邀请通讯录好友";
    _titles = @[@"已注册", @"未注册"];
    
    WEAKSELF
    XLPagingHeaderView *headerView = [[XLPagingHeaderView alloc] initWithFrame:CGRectMake(0, TOPMAGRIN, KScreenWidth, 44) titles:_titles separatedLine:YES];
    _headerView = headerView;
    self.view.backgroundColor = [UIColor tj_backgroundColor];
    [self.view addSubview:headerView];
    [headerView setDidClickButtonBlock:^(UIButton *button) {
        weakSelf.currentIndex = button.tag;
         weakSelf.headerView.currentIndex = button.tag;
        [weakSelf.mainView setContentOffset:CGPointMake(button.tag * KScreenWidth, 0) animated:NO];
    }];
    
    UIScrollView *mainView = [UIScrollView tj_WithSuperView:self.view];
    _mainView = mainView;
    mainView.scrollEnabled = NO;
    mainView.delegate = self;
    mainView.contentSize = CGSizeMake(KScreenWidth * _titles.count, 0);
    
    mainView.frame = CGRectMake(0, CGRectGetMaxY(headerView.frame), KScreenWidth, KScreenHeight - 44 - TOPMAGRIN);
    
    for (int i = 0; i < _titles.count; i++) {
        XLAddressBookContentController *contentController = [[XLAddressBookContentController alloc] init];
        contentController.hiddenNavigationBar= YES;
        [self addChildViewController:contentController];
        contentController.view.frame = CGRectMake(i * KScreenWidth, 0, KScreenWidth, _mainView.frame.size.height);
        [self.mainView addSubview:contentController.view];
    };
    
    [self.pageLoadingView removeFromSuperview];
    XLPageLoadingView *pageLoadingView = [XLPageLoadingView loadingAnimationWithSuperView:self.view];
    pageLoadingView.showType = XLShowTypeLoading;
    self.pageLoadingView = pageLoadingView;
}

#pragma mark - 加载通讯录数据

- (void)startLoadloadAddressBookData {
    
    [self.childViewControllers enumerateObjectsUsingBlock:^(__kindof UIViewController * _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
        XLAddressBookContentController *contentController =  (XLAddressBookContentController *)obj;
        contentController.type =  2 - idx;
    }];
    
    XLAddressBookContentController *contentController =  self.childViewControllers.firstObject;
    [contentController setLoadDataComplete:^{
        [self.pageLoadingView stopAnimation];
    }];
}

#pragma mark - 加载本地的通讯录
- (void)loadLocalAddressBook {
    
    self.keys = [NSMutableArray array];
    self.dataDict = [NSDictionary dictionary];
    
    [[PPAddressBookHandle sharedAddressBookHandle] requestAuthorizationWithSuccessBlock:^{
        TJLog(@"授权成功");
        
        dispatch_async(dispatch_get_main_queue(), ^{
            
            if ([XLGlobalFunc globalFunc].updateAddressBook) {
                [self startLoadloadAddressBookData];
            }
            
            [self requestAuthorizationSucceed];
        });
        
    } failure:^{
        
        dispatch_async(dispatch_get_main_queue(), ^{
            self.pageLoadingView.showType = XLShowTypeDefault;
        });
    

        NSString *appName = [NSBundle mainBundle].infoDictionary[@"CFBundleDisplayName"];
        dispatch_after(dispatch_time(DISPATCH_TIME_NOW, (int64_t)(0.25 * NSEC_PER_SEC)), dispatch_get_main_queue(), ^{
            [self alertWithTitle:@"提示" message:[NSString stringWithFormat:@"请在iPhone的“设置-隐私-通讯录”选项中，允许%@访问您的通讯录", appName] leftButtonName:nil rightButtonName:@"知道了" leftButtonBlock:nil rightButtonBlock:nil];
        });
        
    }];
    
}

- (void)requestAuthorizationSucceed {
    
    [PPGetAddressBook getOrderAddressBook:^(NSDictionary<NSString *,NSArray *> *addressBookDict, NSArray *nameKeys) {
        
        TJLog(@"获取通讯录成功");
        
        self.keys = nameKeys;
        self.dataDict = addressBookDict;
        [self.tableView reloadData];
        
        
        NSArray *array = addressBookDict.allValues;
        NSMutableArray *MArr = [NSMutableArray array];
        
        [array enumerateObjectsUsingBlock:^(NSArray *array, NSUInteger idx, BOOL * _Nonnull stop) {
            [array enumerateObjectsUsingBlock:^(PPPersonModel *model, NSUInteger idx, BOOL * _Nonnull stop) {
                [model.mobileArray enumerateObjectsUsingBlock:^(id  _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
                    [MArr addObject:@{@"name": model.name, @"phone": obj}];
                }];
            }];
        }];
        
        self.addressBookModels = MArr.copy;
        [self.pageLoadingView stopAnimation];
        
        [self uploadAddressBook];
        [self startLoadloadAddressBookData];
        
    } authorizationFailure:^{  }];
}

- (void)uploadAddressBook {
    
    NSData *jsonData =[NSJSONSerialization dataWithJSONObject:self.addressBookModels options:NSJSONWritingPrettyPrinted error:nil];
    NSString *str = [[NSString alloc] initWithData:jsonData encoding:NSUTF8StringEncoding];
    str = (str.length) ? str : @"";
    TJLog(@"str = %@", str);
    [TJNetworkTool requestWithUrl:@"User.HandldMailList" parameters:@{@"phoneStr": str} success:^(id data) {
        if ([data[@"code"] intValue] == 0) {//上传成功
            TJLog(@"上传成功");
            [TJNetworkTool requestWithUrl:@"User.BindMailList" parameters:@{@"phoneStr": str} success:^(id data) {} failure:^(id error) {}];
      }
    } failure:^(id error) {}];
    
}

@end
