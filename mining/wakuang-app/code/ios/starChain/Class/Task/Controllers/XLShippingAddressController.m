//
//  XLShippingAddressController.m
//  starChain
//
//  Created by rlx on 2018/6/12.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import "XLShippingAddressController.h"
#import "XLLineView.h"
#import "XLButton.h"
#import "XLAddressModel.h"

@interface XLShippingAddressController ()<UIPickerViewDataSource, UIPickerViewDelegate, UITextFieldDelegate>

@property (weak, nonatomic) UIView *maskView;
@property (weak, nonatomic) UIPickerView *pickerView;
@property (weak, nonatomic) UILabel *chooseRegion;
@property (weak, nonatomic) UITextField *streetTextField;
@property (weak, nonatomic) UITextField *workUnitsTextField;
@property (weak, nonatomic) UITextField *workAddressTextField;

@property (strong, nonatomic) NSNumber *provinceID;
@property (strong, nonatomic) NSNumber *cityID;
@property (strong, nonatomic) NSNumber *districtID;
@property (strong, nonatomic) NSArray *provinces;
@property (strong, nonatomic) NSArray *citys;
@property (strong, nonatomic) NSArray *regions;
@property (strong, nonatomic) NSArray *dataArr;

@property (copy, nonatomic) NSString *province;
@property (copy, nonatomic) NSString *city;
@property (copy, nonatomic) NSString *region;

@property (assign, nonatomic) BOOL isAddAddress;
@property (assign, nonatomic) NSInteger address_id;

@end

@implementation XLShippingAddressController

- (void)viewDidLoad {
    [super viewDidLoad];
        
    [self readCityListData];
    [self addSubView];
    [self loadAddressDetailsData];
}

- (void)addSubView {
    
    XLLineView *cityContetView = [[XLLineView alloc] initWithTopLine:NO bottomLine:YES];
    [self.view addSubview:cityContetView];
    [cityContetView makeConstraints:^(MASConstraintMaker *make) {
        make.left.right.offset(0);
        make.top.equalTo(self.tj_navigationBar.bottom).offset(10);
        make.height.offset(44);
    }];
    
    XLLineView *streetContetView = [[XLLineView alloc] initWithTopLine:NO bottomLine:YES];
    [self.view addSubview:streetContetView];
    [streetContetView makeConstraints:^(MASConstraintMaker *make) {
        make.left.right.height.equalTo(cityContetView);
        make.top.equalTo(cityContetView.bottom);
    }];
    
    XLLineView *workUnitsContetView = [[XLLineView alloc] initWithTopLine:NO bottomLine:YES];
    [self.view addSubview:workUnitsContetView];
    [workUnitsContetView makeConstraints:^(MASConstraintMaker *make) {
        make.left.right.height.equalTo(cityContetView);
        make.top.equalTo(streetContetView.bottom);
    }];
    
    XLLineView *workAddressContetView = [[XLLineView alloc] initWithTopLine:NO bottomLine:YES];
    [self.view addSubview:workAddressContetView];
    [workAddressContetView makeConstraints:^(MASConstraintMaker *make) {
        make.left.right.height.equalTo(cityContetView);
        make.top.equalTo(workUnitsContetView.bottom);
    }];
    
    UILabel *cityLable = [UILabel lableWithSuperView:cityContetView fontSize:16 color:UIColor_Hex(0x303030) title:@"城市" textAlignment:NSTextAlignmentLeft];
    [cityLable makeConstraints:^(MASConstraintMaker *make) {
        make.left.offset(14);
        make.centerY.offset(0);
        make.width.offset(70);
    }];
    
    UIImage *image = [UIImage imageNamed:@"right"];
    XLButton *arrowButton = [XLButton tj_WithSuperView:cityContetView];
    arrowButton.imageEdgeInsets = UIEdgeInsetsMake(0, 10, 0, 0);
    [arrowButton setImage:image forState:UIControlStateNormal];
    [arrowButton makeConstraints:^(MASConstraintMaker *make) {
        make.right.offset(-14);
        make.centerY.offset(0);
        make.width.height.offset(18);
    }];
    
    UILabel *chooseRegion = [UILabel lableWithSuperView:cityContetView fontSize:16 color:UIColor_Hex(0xa0a0a0) title:@"请选择城市" textAlignment:NSTextAlignmentRight];
    [chooseRegion makeConstraints:^(MASConstraintMaker *make) {
        make.centerY.offset(0);
        make.right.equalTo(arrowButton.left);
        make.height.offset(40);
    }];
    
    UIButton *chooseRegionButton = [UIButton tj_WithSuperView:cityContetView];
    [chooseRegionButton addTarget:self action:@selector(didClickChooseButton) forControlEvents:UIControlEventTouchUpInside];
    [chooseRegionButton makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(chooseRegion);
        make.right.equalTo(arrowButton);
        make.centerY.offset(0);
        make.height.offset(40);
    }];
    
    UILabel *streetLable = [UILabel lableWithSuperView:streetContetView fontSize:16 color:UIColor_Hex(0x303030) title:@"街道" textAlignment:NSTextAlignmentLeft];
    [streetLable makeConstraints:^(MASConstraintMaker *make) {
        make.left.width.equalTo(cityLable);
        make.centerY.offset(0);
    }];
    
    UITextField *streetTextField = [self addTextFieldWithPlaceholder:@"请输入街道"];
    [streetContetView addSubview:streetTextField];
    [streetTextField makeConstraints:^(MASConstraintMaker *make) {
        make.centerY.offset(0);
        make.left.equalTo(streetLable.right).offset(30);
        make.height.offset(40);
        make.right.offset(-14);
    }];
    
    
    UILabel *workUnitsLable = [UILabel lableWithSuperView:workUnitsContetView fontSize:16 color:UIColor_Hex(0x303030) title:@"工作单位" textAlignment:NSTextAlignmentLeft];
    [workUnitsLable makeConstraints:^(MASConstraintMaker *make) {
        make.left.width.equalTo(cityLable);
        make.centerY.offset(0);
    }];
    
    UITextField *workUnitsTextField = [self addTextFieldWithPlaceholder:@"请输入工作单位名称"];
    [workUnitsContetView addSubview:workUnitsTextField];
    [workUnitsTextField makeConstraints:^(MASConstraintMaker *make) {
        make.centerY.offset(0);
        make.right.height.equalTo(streetTextField);
        make.left.equalTo(workUnitsLable.right).offset(30);
    }];
    
    UILabel *workAddressLable = [UILabel lableWithSuperView:workAddressContetView fontSize:16 color:UIColor_Hex(0x303030) title:@"单位地址" textAlignment:NSTextAlignmentLeft];
    [workAddressLable makeConstraints:^(MASConstraintMaker *make) {
        make.left.width.equalTo(cityLable);
        make.centerY.offset(0);
    }];
    
    UITextField *workAddressTextField = [self addTextFieldWithPlaceholder:@"请输入工作单位地址"];
    [workAddressContetView addSubview:workAddressTextField];
    [workAddressTextField makeConstraints:^(MASConstraintMaker *make) {
        make.centerY.offset(0);
        make.right.height.equalTo(workUnitsTextField);
        make.left.equalTo(workAddressLable.right).offset(30);
    }];
    
    XLButton *commitButton = [XLButton buttonWithSuperView:self.view fontSize:15 color:[UIColor whiteColor] title:@"提交"];
    [commitButton setBackgroundImage:[UIImage imageNamed:@"gradientButtonBg"] forState:UIControlStateNormal];
    [commitButton shearRoundedCornersWithRadiu:ZOOM5(45) * 0.5];
    [commitButton addTarget:self action:@selector(didClickCommitButton) forControlEvents:UIControlEventTouchUpInside];
    [commitButton makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(workAddressLable);
        make.right.equalTo(workAddressTextField);
        make.height.offset(ZOOM5(45));
        make.top.equalTo(workAddressContetView.bottom).offset(60);
    }];
    
    self.streetTextField = streetTextField;
    self.workUnitsTextField = workUnitsTextField;
    self.workAddressTextField = workAddressTextField;
    self.chooseRegion = chooseRegion;
    
    self.streetTextField.delegate = self;
    self.workUnitsTextField.delegate = self;
    self.workAddressTextField.delegate = self;
}

- (BOOL)textField:(UITextField *)textField shouldChangeCharactersInRange:(NSRange)range replacementString:(NSString *)string {
    NSInteger existedLength = textField.text.length;
    NSInteger selectedLength = range.length;
    NSInteger replaceLength = string.length;
    NSInteger currentLengh = existedLength - selectedLength + replaceLength;
    if ((textField == _streetTextField || textField == _workUnitsTextField || textField == _workAddressTextField) && currentLengh > 40) {
        return NO;
    } else {
        return YES;
    }
}

- (void)readCityListData {
    NSString *path = [[NSBundle mainBundle] pathForResource:@"citys.plist" ofType:nil];
    _provinces = [NSArray arrayWithContentsOfFile:path];
}

- (void)didClickChooseButton {
    
    [self.view endEditing:YES];
    
    UIWindow *widow = [UIApplication sharedApplication].keyWindow;
    UIView *maskView = [UIView tj_WithSuperView:widow];
    maskView.frame = widow.bounds;
    maskView.backgroundColor = [UIColor tj_maskColor];
    _maskView = maskView;
    [maskView addTapGesturesWithTarget:self action:@selector(didClickComplete)];
    [self addPickerView];
}

- (UITextField *)addTextFieldWithPlaceholder:(NSString *)placeholder {
    UITextField *textField = [[UITextField alloc] init];
    textField.textAlignment = NSTextAlignmentRight;
    textField.font = [UIFont systemFontOfSize:16];
    textField.attributedPlaceholder = [[NSAttributedString alloc] initWithString:placeholder attributes:@{NSForegroundColorAttributeName: UIColor_Hex(0xa0a0a0)}];
    return textField;
}

- (void)addPickerView {
    UIPickerView *pickerView = [[UIPickerView alloc] initWithFrame:CGRectMake(0, CGRectGetMaxY(self.maskView.frame), KScreenWidth, 216)];
    [self.maskView addSubview:pickerView];
    self.pickerView = pickerView;
    self.maskView.alpha = 0;
    pickerView.backgroundColor = UIColor_Hex(0xd1d5da);
    pickerView.dataSource = self;
    pickerView.delegate = self;
    
    for (int i = 0; i < 3; i++) {
        [self pickerView:pickerView didSelectRow:0 inComponent:i];
    };
    
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

#pragma mark - 返回多少列
- (NSInteger)numberOfComponentsInPickerView:(UIPickerView *)pickerView {
    return 3;
}

#pragma mark - 返回多少行
- (NSInteger)pickerView:(UIPickerView *)pickerView numberOfRowsInComponent:(NSInteger)component {
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

#pragma mark - 返回内容
- (NSString *)pickerView:(UIPickerView *)pickerView titleForRow:(NSInteger)row forComponent:(NSInteger)component {
    NSString *string = nil;
    if (component == 0) {
        string = self.provinces[row][@"name"];
        _province = self.provinces[row][@"name"];
        _provinceID = @([self.provinces[row][@"regionID"] intValue]);
        
    } else if (component == 1) {
        string = self.citys[row][@"name"];
        _city = self.citys[row][@"name"];
        _cityID = @([self.citys[row][@"regionID"] intValue]);
        
    } else {
        string = self.regions[row][@"name"];
        _region = self.regions[row][@"name"];
        _districtID = @([self.regions[row][@"regionID"] intValue]);
    }
    return string;
}

- (void)pickerView:(UIPickerView *)pickerView didSelectRow:(NSInteger)row inComponent:(NSInteger)component {
    if (component == 0) {
        [pickerView reloadComponent:1];
        [pickerView selectRow:0 inComponent:1 animated:YES];
        [self pickerView:pickerView didSelectRow:0 inComponent:1];
        _province = self.provinces[row][@"name"];
        _provinceID = @([self.provinces[row][@"regionID"] intValue]);
        
    } else if (component == 1) {
        [pickerView reloadComponent:2];
        [pickerView selectRow:0 inComponent:2 animated:YES];
        [self pickerView:pickerView didSelectRow:0 inComponent:2];
        _city = self.citys[row][@"name"];
        _cityID = @([self.citys[row][@"regionID"] intValue]);
        
    } else {
        _region = self.regions[row][@"name"];
        _districtID = @([self.regions[row][@"regionID"] intValue]);
    }
    _chooseRegion.textColor = UIColor_Hex(0x303030);
    _chooseRegion.text = [NSString stringWithFormat:@"%@, %@ , %@", _province, _city, _region];
    TJLog(@"%@", [NSString stringWithFormat:@"%@ : %@, %@ : %@, %@: %@", _province, _provinceID, _city, _cityID, _region, _districtID]);
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

- (void)touchesBegan:(NSSet<UITouch *> *)touches withEvent:(UIEvent *)event {
    [self.view endEditing:YES];
}

- (void)didClickCommitButton {
    
    NSString *street = _streetTextField.text;
    NSString *workUnits = _workUnitsTextField.text;
    NSString *workAddress = _workAddressTextField.text;
    
    if ([_chooseRegion.text isEqualToString:@"请选择城市"]) {
        [MBProgressHUD showHUDAddedTo:self.view messagqe:@"请选择城市"];
        return;
    };
    if (!street.length) {
        [MBProgressHUD showHUDAddedTo:self.view messagqe:@"请输入街道"];
        return;
    };
    if (!workUnits.length) {
        [MBProgressHUD showHUDAddedTo:self.view messagqe:@"请输入工作单位名称"];
        return;
    };
    if (!workAddress.length) {
        [MBProgressHUD showHUDAddedTo:self.view messagqe:@"请输入工作单位地址"];
        return;
    };
    
    if (!_cityID) return;
    if (!_districtID) return;
    if (!_provinceID) return;
    
    NSMutableDictionary *parameters = [NSMutableDictionary dictionary];
    [parameters setObject:_provinceID forKey:@"province"];
    [parameters setObject:_cityID forKey:@"city"];
    [parameters setObject:_districtID forKey:@"district"];
    [parameters setObject:street forKey:@"address"];
    [parameters setObject:workUnits forKey:@"workUnit"];
    [parameters setObject:workAddress forKey:@"workAddress"];

    if (!self.isAddAddress) [parameters setObject:@(self.address_id) forKey:@"addressID"];
    TJLog(@"parameters = %@", parameters);
 
    [XLAddressModel modifyAddAddressDataWithParameters:parameters success:^(id data) {
        [self showMessageAutoHide:data[@"msg"]];
        [self.navigationController popViewControllerAnimated:YES];
    } failure:^(id error) {
        if (![error isKindOfClass:[NSError class]]) {
            [self showMessageAutoHide:error[@"msg"]];
        }
    }];
}

- (void)loadAddressDetailsData {
    
    [TJNetworkTool requestWithUrl:@"Real.GetUserAddress" parameters:nil success:^(id data) {
        if ([data[@"code"] intValue]) {//没有地址
            self.isAddAddress = YES;
        
         } else {//有地址
             self.isAddAddress = NO;
             
             NSDictionary *info = data[@"info"];
             self.chooseRegion.textColor = UIColor_Hex(0x303030);
             self.address_id = [info[@"address_id"] intValue];
             self.chooseRegion.text = [NSString stringWithFormat:@"%@, %@, %@",info[@"province_name"], info[@"city_name"],info[@"district_name"]];
             self.streetTextField.text = info[@"address"];
             self.workUnitsTextField.text = info[@"work_unit"];
             self.workAddressTextField.text = info[@"work_address"];
             self.cityID = @([info[@"city"] intValue]);
             self.provinceID = @([info[@"province"] intValue]);
             self.districtID = @([info[@"district"] intValue]);
         }
    } failure:^(id error) {
     }];
}

@end
