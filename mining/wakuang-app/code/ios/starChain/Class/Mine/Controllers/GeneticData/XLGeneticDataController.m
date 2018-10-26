//
//  XLGeneticDataController.m
//  starChain
//
//  Created by rlx on 2018/7/17.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import "XLGeneticDataController.h"
#import "XLGeneticDataCell.h"
#import "XLButton.h"


typedef NS_ENUM(NSInteger, XLGeneticDataType) {
    XLGeneticDataTypeGender,//性别
    XLGeneticDataTypeBorn,//出生
    XLGeneticDataTypeNativePlace,//籍贯
    XLGeneticDataTypeMovementTime,//运动时间
};

@interface XLGeneticDataController ()<UIPickerViewDataSource, UIPickerViewDelegate, UITextFieldDelegate>

@property (weak, nonatomic) UIView *maskView;
@property (weak, nonatomic) UIPickerView *pickerView;
@property (weak, nonatomic) UILabel *genderLable;
@property (weak, nonatomic) UILabel *bornLable;
@property (weak, nonatomic) UILabel *nativePlaceLable;
@property (weak, nonatomic) UILabel *movementTimeLable;
@property (weak, nonatomic) UITextField *nameTextField;
@property (weak, nonatomic) UITextField *heightTextField;
@property (weak, nonatomic) UITextField *weightTextField;
@property (weak, nonatomic) UITextField *geneticdisordersTextField;
@property (weak, nonatomic) UITextField *textField;
@property (weak, nonatomic) UIScrollView *scrollView;
@property (weak, nonatomic) UIView *contentView;

@property (strong, nonatomic) NSArray *genderDatas;
@property (strong, nonatomic) NSArray *bornDatas;
@property (strong, nonatomic) NSArray *nativePlaceDatas;
@property (strong, nonatomic) NSArray *movementTimeDatas;
@property (strong, nonatomic) NSArray *geneticdisordersDatas;
@property (strong, nonatomic) NSArray *yearDatas;
@property (strong, nonatomic) NSArray *monthDatas;
@property (strong, nonatomic) NSArray *dayDatas;
@property (strong, nonatomic) NSArray *provinces;
@property (strong, nonatomic) NSArray *citys;
@property (strong, nonatomic) NSArray *regions;

@property (copy, nonatomic) NSString *year;
@property (copy, nonatomic) NSString *month;
@property (copy, nonatomic) NSString *day;
@property (copy, nonatomic) NSString *province;
@property (copy, nonatomic) NSString *city;
@property (copy, nonatomic) NSString *region;
@property (copy, nonatomic) NSString *bornString;
@property (copy, nonatomic) NSString *nativePlaceString;

@property (assign, nonatomic) XLGeneticDataType type;

@end

@implementation XLGeneticDataController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    NSString *path = [[NSBundle mainBundle] pathForResource:@"citys.plist" ofType:nil];
    self.provinces = [NSArray arrayWithContentsOfFile:path];
    
    self.bornDatas = [XLGlobalFunc globalFunc].bornDatas;
    self.yearDatas = self.bornDatas;

    self.genderDatas = @[@"男", @"女"];
    self.movementTimeDatas = @[@"1小时", @"2小时", @"3小时以上"];
    self.nativePlaceDatas = self.provinces;
    
    self.yearDatas = self.bornDatas;
    self.monthDatas = [NSArray array];
    self.dayDatas = [NSArray array];
    
    UIScrollView *scrollView = [[UIScrollView alloc] init];
    [self.view addSubview:scrollView];
    self.scrollView.backgroundColor = [UIColor tj_backgroundColor];
    self.scrollView = scrollView;
    [scrollView makeConstraints:^(MASConstraintMaker *make) {
        make.top.equalTo(self.tj_navigationBar.bottom);
        make.left.right.bottom.offset(0);
    }];
    
    UIView *contentView = [UIView tj_WithSuperView:scrollView];
    contentView.backgroundColor = [UIColor whiteColor];
    self.contentView = contentView;
    [contentView addTapGesturesWithTarget:self action:@selector(tapContentView)];
    [contentView makeConstraints:^(MASConstraintMaker *make) {
        make.top.offset(10);
        make.left.right.bottom.offset(0);
        make.width.equalTo(KScreenWidth);
        make.height.equalTo(KScreenHeight - NavBarHight - 10);
    }];
    
    XLGeneticDataInputCell *nameContetView = [XLGeneticDataInputCell tj_WithSuperView:contentView];
    self.nameTextField = nameContetView.textField;
    self.nameTextField.delegate = self;
    nameContetView.textLable.text = @"姓名";
    nameContetView.placeholder = @"请输入真实姓名";
    self.nameTextField.returnKeyType = UIReturnKeyDone;
    [nameContetView makeConstraints:^(MASConstraintMaker *make) {
        make.left.right.top.offset(0);
        make.height.offset(44);
    }];
    
    XLGeneticDataCell *genderContetView = [XLGeneticDataCell tj_WithSuperView:contentView];
    genderContetView.textLable.text = @"性别";
    genderContetView.detailsLable.text = @"请选择性别";
    [genderContetView makeConstraints:^(MASConstraintMaker *make) {
        make.left.right.height.equalTo(nameContetView);
        make.top.equalTo(nameContetView.bottom);
    }];
    
    XLGeneticDataCell *bornContetView = [XLGeneticDataCell tj_WithSuperView:contentView];
    bornContetView.textLable.text = @"出生日期";
    bornContetView.detailsLable.text = @"请选择出生日期";
    [bornContetView makeConstraints:^(MASConstraintMaker *make) {
        make.left.right.height.equalTo(nameContetView);
        make.top.equalTo(genderContetView.bottom);
    }];
    
    XLGeneticDataCell *nativePlaceContetView = [XLGeneticDataCell tj_WithSuperView:contentView];
    nativePlaceContetView.textLable.text = @"籍贯";
    nativePlaceContetView.detailsLable.text = @"请选择籍贯地址";
    [nativePlaceContetView makeConstraints:^(MASConstraintMaker *make) {
        make.left.right.height.equalTo(nameContetView);
        make.top.equalTo(bornContetView.bottom);
    }];
 
    XLGeneticDataInputCell *heightContetView = [XLGeneticDataInputCell tj_WithSuperView:contentView];
    self.heightTextField = heightContetView.textField;
    self.heightTextField.delegate = self;
    heightContetView.placeholder = @"请输入身高(cm)";
    heightContetView.textLable.text = @"身高";
    heightContetView.textField.keyboardType = UIKeyboardTypeNumberPad;
    [heightContetView makeConstraints:^(MASConstraintMaker *make) {
        make.left.right.height.equalTo(nameContetView);
        make.top.equalTo(nativePlaceContetView.bottom);
    }];
    
    XLGeneticDataInputCell *weightContetView = [XLGeneticDataInputCell tj_WithSuperView:contentView];
    self.weightTextField = weightContetView.textField;
    self.weightTextField.delegate = self;
    weightContetView.placeholder = @"请输入体重(kg)";
    weightContetView.textLable.text = @"体重";
    weightContetView.textField.keyboardType = UIKeyboardTypeDecimalPad;
    [weightContetView makeConstraints:^(MASConstraintMaker *make) {
        make.left.right.height.equalTo(nameContetView);
        make.top.equalTo(heightContetView.bottom);
    }];
    
    XLGeneticDataCell *movementTimeContetView = [XLGeneticDataCell tj_WithSuperView:contentView];
    movementTimeContetView.textLable.text = @"运动时间";
    movementTimeContetView.detailsLable.text = @"请选择运动时间";
    [movementTimeContetView makeConstraints:^(MASConstraintMaker *make) {
        make.left.right.height.equalTo(nameContetView);
        make.top.equalTo(weightContetView.bottom);
    }];
    
    XLGeneticDataInputCell *geneticdisordersContetView = [XLGeneticDataInputCell tj_WithSuperView:contentView];
    geneticdisordersContetView.textLable.text = @"家族遗传病";
    geneticdisordersContetView.placeholder = @"请输入家族遗传病,没有则写无";
    self.geneticdisordersTextField = geneticdisordersContetView.textField;
    self.geneticdisordersTextField.delegate = self;
    self.geneticdisordersTextField.returnKeyType = UIReturnKeyDone;
    [geneticdisordersContetView makeConstraints:^(MASConstraintMaker *make) {
        make.left.right.height.equalTo(nameContetView);
        make.top.equalTo(movementTimeContetView.bottom);
    }];
    
    XLButton *commitButton = [XLButton buttonWithSuperView:contentView fontSize:15 color:[UIColor whiteColor] title:@"提交"];
    [commitButton setBackgroundImage:[UIImage imageNamed:@"gradientButtonBg"] forState:UIControlStateNormal];
    [commitButton shearRoundedCornersWithRadiu:ZOOM5(45) * 0.5];
    [commitButton addTarget:self action:@selector(didClickCommitButton) forControlEvents:UIControlEventTouchUpInside];
    [commitButton makeConstraints:^(MASConstraintMaker *make) {
        make.left.offset(14);
        make.right.offset(-14);
        make.height.offset(ZOOM5(45));
        make.top.equalTo(geneticdisordersContetView.bottom).offset(60);
    }];
    
    [genderContetView setTapDetailsLableBlock:^(UILabel *lable) {
        self.type = XLGeneticDataTypeGender;
        self.genderLable = lable;
        [self addPickerView];
    }];

    [bornContetView setTapDetailsLableBlock:^(UILabel *lable) {
        
        self.bornDatas = [XLGlobalFunc globalFunc].bornDatas;
        self.yearDatas = self.bornDatas;

        TJLog(@"bornDatas = %@, yearDatas = %@", self.bornDatas, self.yearDatas);
        
        if (!self.bornDatas.count) return ;
        if (!self.yearDatas.count) return ;
        
        self.type = XLGeneticDataTypeBorn;
        self.bornLable = lable;
        [self addPickerView];
    }];
    
    [nativePlaceContetView setTapDetailsLableBlock:^(UILabel *lable) {
        self.type = XLGeneticDataTypeNativePlace;
        self.nativePlaceLable = lable;
        [self addPickerView];
    }];
    
    [movementTimeContetView setTapDetailsLableBlock:^(UILabel *lable) {
        self.type = XLGeneticDataTypeMovementTime;
        self.movementTimeLable = lable;
        [self addPickerView];
    }];
    
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(keyboardWillShow:) name:UIKeyboardWillShowNotification object:nil];
    
}

- (void)dealloc {
    [[NSNotificationCenter defaultCenter] removeObserver:self name:UIKeyboardWillShowNotification object:nil];
}

- (void)textFieldDidBeginEditing:(UITextField *)textField {
    self.textField = textField;
}

- (void)keyboardWillShow:(NSNotification *)notification {
    NSDictionary *userInfo = [notification userInfo];
    NSValue *aValue = [userInfo objectForKey:UIKeyboardFrameEndUserInfoKey];
    CGRect keyboardRect = [aValue CGRectValue];
    CGFloat keyboardH = keyboardRect.size.height;
    
    CGRect textFieldINContentRect = [self.textField.superview convertRect:self.textField.frame toView:self.contentView];
    CGFloat textFieldMaxY = CGRectGetMaxY(textFieldINContentRect) + self.scrollView.tj_y + self.contentView.tj_y;
    if (KScreenHeight - textFieldMaxY < (keyboardH + 5)) {
        [self.scrollView setContentOffset:CGPointMake(0, keyboardH - (KScreenHeight - textFieldMaxY) + 5) animated:YES];
    } else {
        [self.scrollView setContentOffset:CGPointZero animated:YES];
    }
}

- (BOOL)textField:(UITextField *)textField shouldChangeCharactersInRange:(NSRange)range replacementString:(NSString *)string {
    NSInteger existedLength = textField.text.length;
    NSInteger selectedLength = range.length;
    NSInteger replaceLength = string.length;
    NSInteger currentLengh = existedLength - selectedLength + replaceLength;
    if (textField == _nameTextField && currentLengh > 20) {
        return NO;
    } else if ((textField == _heightTextField) && currentLengh > 3) {
        return NO;
    } else if (textField == _weightTextField && currentLengh > 4) {
        return NO;
    } else if (textField == _geneticdisordersTextField && currentLengh > 4) {
        return NO;
    } else {
        return YES;
    }
}

- (void)tapContentView {
    [self.view endEditing:YES];
    [self.scrollView setContentOffset:CGPointZero animated:YES];
}

- (BOOL)textFieldShouldReturn:(UITextField *)textField {
    [self tapContentView];
    return YES;
}

- (void)addPickerView {
    
    [self tapContentView];
    
    UIWindow *widow = [UIApplication sharedApplication].keyWindow;
    UIView *maskView = [UIView tj_WithSuperView:widow];
    maskView.frame = widow.bounds;
    maskView.backgroundColor = [UIColor tj_maskColor];
    _maskView = maskView;
    [maskView addTapGesturesWithTarget:self action:@selector(didClickComplete)];
    
    UIPickerView *pickerView = [[UIPickerView alloc] initWithFrame:CGRectMake(0, CGRectGetMaxY(self.maskView.frame), KScreenWidth, 216)];
    [self.maskView addSubview:pickerView];
    self.pickerView = pickerView;
    self.maskView.alpha = 0;
    pickerView.backgroundColor = UIColor_Hex(0xd1d5da);
    pickerView.dataSource = self;
    pickerView.delegate = self;
    
    if (self.type == XLGeneticDataTypeGender || self.type == XLGeneticDataTypeMovementTime) {
        [self pickerView:pickerView didSelectRow:0 inComponent:0];
    }
    
    if (self.type == XLGeneticDataTypeBorn || self.type == XLGeneticDataTypeNativePlace) {
        for (int i = 0; i < 3; i++) {
            [self pickerView:pickerView didSelectRow:0 inComponent:i];
        };
    }
    
    [UIView animateWithDuration:0.2f animations:^{
        self.maskView.alpha = 1;
    } completion:^(BOOL finished) {
        [UIView animateWithDuration:0.20f animations:^{
            pickerView.tj_y = CGRectGetMaxY(self.maskView.frame) - 216;
        }];
    }];
}


- (void)didClickComplete {
    [UIView animateWithDuration:0.2f animations:^{
        self.pickerView.tj_y = CGRectGetMaxY(self.maskView.frame);
    } completion:^(BOOL finished) {
        [UIView animateWithDuration:0.25f animations:^{
            self.maskView.alpha = 0;
        } completion:^(BOOL finished) {
            [self.maskView removeAllSubView];
            [self.maskView removeFromSuperview];
        }];
    }];
}

- (void)didClickCommitButton {
    
    NSString *born = [_bornString stringByReplacingOccurrencesOfString:@"年" withString:@"-"];
    born = [born stringByReplacingOccurrencesOfString:@"月" withString:@"-"];
    born = [born stringByReplacingOccurrencesOfString:@"日" withString:@""];

    NSString *name = self.nameTextField.text;
    NSString *gender = self.genderLable.text;
    NSString *nativePlace = self.nativePlaceLable.text;
    NSString *movementTime = self.movementTimeLable.text;
    NSString *geneticdisorders = self.geneticdisordersTextField.text;
    NSString *height = self.heightTextField.text;
    NSString *weight = self.weightTextField.text;

    TJLog(@"name = %@, gender = %@, born = %@, nativePlace = %@, movementTime = %@, geneticdisorders = %@", name, gender, born, nativePlace, movementTime, geneticdisorders);
    
    if (!name.length) {
        [self showMessageAutoHide:@"请输入姓名"]; return;
    }
    
    if (!gender.length) {
        [self showMessageAutoHide:@"请选择性别"]; return;
    }
    
    if (!born.length) {
        [self showMessageAutoHide:@"请选择出生日期"]; return;
    }
    
    if (!nativePlace.length) {
        [self showMessageAutoHide:@"请选择籍贯"]; return;
    }
    
    if (!height.length) {
        [self showMessageAutoHide:@"请输入身高"]; return;
    }
    
    if (height.floatValue < 50 || height.floatValue > 300) {
        [self showMessageAutoHide:@"请输入正确身高"]; return;
    }

    if (!weight.length) {
        [self showMessageAutoHide:@"请输入体重"]; return;
    }

    if (weight.floatValue <= 0 || weight.floatValue > 500) {
        [self showMessageAutoHide:@"请输入正确体重"]; return;
    }
    
    if (!nativePlace.length) {
        [self showMessageAutoHide:@"请选择籍贯"]; return;
    }
    
    if (!movementTime.length) {
        [self showMessageAutoHide:@"请选择运动时间"]; return;
    }
    
    if (!geneticdisorders.length) {
        [self showMessageAutoHide:@"请输入家族遗传病,没有则写无"]; return;
    }
    
    if ([movementTime isEqualToString:self.movementTimeDatas[0]]) {
        movementTime = @"3600";
    }
    
    if ([movementTime isEqualToString:self.movementTimeDatas[1]]) {
        movementTime = @"7200";
    }
    
    if ([movementTime isEqualToString:self.movementTimeDatas[2]]) {
        movementTime = @"10801";
    }
    
    NSDictionary *parameters = @{
                                 @"name": name,
                                 @"brithDate":born,
                                 @"nativePlace": nativePlace,
                                 @"exerciseTime": movementTime,
                                 @"ill": geneticdisorders,
                                 @"height": @(height.floatValue/100.0),
                                 @"weight": weight,
                                 @"sex": @([gender isEqualToString:@"男"])
                                 };
    
    TJLog(@"parameters = %@", parameters);
    
    [TJNetworkTool requestWithUrl:@"User.Gene" parameters:parameters success:^(id data) {
        [self showMessageAutoHide:data[@"msg"]];
        dispatch_after(dispatch_time(DISPATCH_TIME_NOW, (int64_t)(1 * NSEC_PER_SEC)), dispatch_get_main_queue(), ^{
            [self.navigationController popViewControllerAnimated:YES];
        });
    } failure:^(id error) { }];
}

#pragma mark - 返回多少列
- (NSInteger)numberOfComponentsInPickerView:(UIPickerView *)pickerView {
    if (_type == XLGeneticDataTypeGender || _type == XLGeneticDataTypeMovementTime) return 1;
    if (_type == XLGeneticDataTypeBorn || _type == XLGeneticDataTypeNativePlace) return 3;
    return 0;
}

#pragma mark - 返回多少行
- (NSInteger)pickerView:(UIPickerView *)pickerView numberOfRowsInComponent:(NSInteger)component {
    if (_type == XLGeneticDataTypeGender)   return self.genderDatas.count;
    if (_type == XLGeneticDataTypeMovementTime)   return self.movementTimeDatas.count;
    
    if (_type == XLGeneticDataTypeBorn) {
        if (component == 0) {
            return self.yearDatas.count;
        } else if (component == 1) {
            NSInteger cityRow = [pickerView selectedRowInComponent:0];
            _monthDatas = self.yearDatas[cityRow][@"items"];
            return _monthDatas.count;
        } else {
            NSInteger regionRow = [pickerView selectedRowInComponent:1];
            _dayDatas = _monthDatas[regionRow][@"items"];
            return _dayDatas.count;
        }
    }
    
    if (_type == XLGeneticDataTypeNativePlace) {
        if (component == 0) {
            return self.provinces.count;
        } else if (component == 1) {
            NSInteger cityRow = [pickerView selectedRowInComponent:0];
            _citys= self.provinces[cityRow][@"children"];
            return _citys.count;
        } else {
            NSInteger regionRow = [pickerView selectedRowInComponent:1];
            _regions = _citys[regionRow][@"children"];
            return _regions.count;
        }
    }
    
    return 0;
}

#pragma mark - 返回内容
- (NSString *)pickerView:(UIPickerView *)pickerView titleForRow:(NSInteger)row forComponent:(NSInteger)component {
    if (_type == XLGeneticDataTypeGender) return self.genderDatas[row];
    if (_type == XLGeneticDataTypeMovementTime) return self.movementTimeDatas[row];
    
    if (_type == XLGeneticDataTypeBorn) {
        if (component == 0) return self.yearDatas[row][@"year"];
        else if (component == 1)  return self.monthDatas[row][@"month"];
        else return self.dayDatas[row];
    }
    
    if (_type == XLGeneticDataTypeNativePlace) {
        if (component == 0) return self.provinces[row][@"name"];
        else if (component == 1) return self.citys[row][@"name"];
        else return self.regions[row][@"name"];
    }
    
    return @"";
}

- (void)pickerView:(UIPickerView *)pickerView didSelectRow:(NSInteger)row inComponent:(NSInteger)component {
    
    if (_type == XLGeneticDataTypeGender) {
        self.genderLable.text = self.genderDatas[row];
        self.genderLable.textColor = UIColor_Hex(0x303030);
    }
    
    if (_type == XLGeneticDataTypeMovementTime) {
        self.movementTimeLable.text = self.movementTimeDatas[row];
        self.movementTimeLable.textColor = UIColor_Hex(0x303030);
    }

    if (_type == XLGeneticDataTypeBorn) {
        
        if (component == 0) {
            [pickerView reloadComponent:1];
            [pickerView selectRow:0 inComponent:1 animated:YES];
            [self pickerView:pickerView didSelectRow:0 inComponent:1];
            _year = self.yearDatas[row][@"year"];
            
        } else if (component == 1) {
            [pickerView reloadComponent:2];
            [pickerView selectRow:0 inComponent:2 animated:YES];
            [self pickerView:pickerView didSelectRow:0 inComponent:2];
            _month = self.monthDatas[row][@"month"];
        } else {
            _day = self.dayDatas[row];
        }
        
        _bornString = [NSString stringWithFormat:@"%@%@%@",_year, _month, _day];
        
        self.bornLable.text = _bornString;
        self.bornLable.textColor = UIColor_Hex(0x303030);
        
        TJLog(@"_bornString = %@", _bornString);
    }
    
    if (_type == XLGeneticDataTypeNativePlace) {
        
        if (component == 0) {
            [pickerView reloadComponent:1];
            [pickerView selectRow:0 inComponent:1 animated:YES];
            [self pickerView:pickerView didSelectRow:0 inComponent:1];
            _province = self.provinces[row][@"name"];
        } else if (component == 1) {
            [pickerView reloadComponent:2];
            [pickerView selectRow:0 inComponent:2 animated:YES];
            [self pickerView:pickerView didSelectRow:0 inComponent:2];
            _city = self.citys[row][@"name"];
        } else {
            _region = self.regions[row][@"name"];
        }
        
        _nativePlaceString = [NSString stringWithFormat:@"%@,%@,%@",_province, _city, _region];
        TJLog(@"_nativePlaceString = %@", _nativePlaceString);
        
        self.nativePlaceLable.text = _nativePlaceString;
        self.nativePlaceLable.textColor = UIColor_Hex(0x303030);
    }
    
}

- (UIView *)pickerView:(UIPickerView *)pickerView viewForRow:(NSInteger)row forComponent:(NSInteger)component reusingView:(UIView *)view{
    UILabel *pickerLabel = (UILabel *)view;
    if (!pickerLabel){
        pickerLabel = [[UILabel alloc] init];
        [pickerLabel setFont:[UIFont systemFontOfSize:17]];
        pickerLabel.adjustsFontSizeToFitWidth = YES;
        pickerLabel.textAlignment = NSTextAlignmentCenter;
    }
    pickerLabel.text = [self pickerView:pickerView titleForRow:row forComponent:component];
    return pickerLabel;
}

@end
