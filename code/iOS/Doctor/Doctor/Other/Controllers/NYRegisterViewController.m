//
//  NYRegisterViewController.m
//  Doctor
//
//  Created by niuyao on 2018/12/4.
//  Copyright © 2018 joymates. All rights reserved.
//

#import "NYRegisterViewController.h"
#import "NYNameInputCell.h"
#import "NYChuZhenTitleCell.h"
#import "NYRegisterInputPhoneCell.h"
#import "NYRegisterChoiceSexCell.h"
#import "RankButton.h"
#import "NYRegisterShanChangCell.h"
#import "NYRegisterChoicePictureCell.h"
#import <TZImagePickerController.h>
#import <TZImageManager.h>
#import "NYRegisterUploadCardImgCell.h"
#import "NYRegisterUploadCardCell.h"
#import "BRPlaceholderTextView.h"

@interface NYRegisterViewController ()<UITableViewDelegate,UITableViewDataSource,UIImagePickerControllerDelegate, UINavigationControllerDelegate,TZImagePickerControllerDelegate,UIAlertViewDelegate,UITextFieldDelegate>
{
    UIImageView * _headerImg; //用户头像
    UIImageView * _zigeImg; //资格证书

    
    UIImageView * _card1Img;
    UIImageView * _card2Img;
    
    NSInteger _sex; //1男 0女
    
    JKCountDownButton * _codeButton;
    NSString * _captchaId;

    
    UIImage * _userImage;
    UIImage * _zigeImage;
    UIImage * _card1Image;
    UIImage * _card2Image;
    
    
//    NSString * _phoneStr;
//    NSString * _danweiStr;
//    NSString * _zhiweiStr;
//    NSString * _keshiStr;
//    NSString * _pirceStr;
//    NSString * _shanChangStr1;
//    NSString * _shanChangStr2;
//    NSString * _shanChangStr3;
//    NSString * _shanChangContentStr1;
//    NSString * _shanChangContentStr2;
//    NSString * _shanChangContentStr3;
//    NSString * _numberStr;
//    NSString * _codeStr;
    
}
@property (nonatomic, strong) UIImageView *choiceImg;

@property (nonatomic, strong) UITableView *tableView;
@property (nonatomic, strong) UIImagePickerController *imagePickerVc;

@property (nonatomic,strong) HHTextField * nameTF;
@property (nonatomic,strong) HHTextField * danWeiTF;
@property (nonatomic,strong) HHTextField * zhiWeiTF;
@property (nonatomic,strong) HHTextField * keShiTF;
@property (nonatomic,strong) HHTextField * priceTF;

@property (nonatomic,strong) HHTextField * shanChangTF1;
@property (nonatomic,strong) BRPlaceholderTextView * shanChangTextView1;

@property (nonatomic,strong) HHTextField * shanChangTF2;
@property (nonatomic,strong) BRPlaceholderTextView * shanChangTextView2;

@property (nonatomic,strong) HHTextField * shanChangTF3;
@property (nonatomic,strong) BRPlaceholderTextView * shanChangTextView3;

@property (nonatomic,strong) HHTextField * phoneTF;
@property (nonatomic,strong) HHTextField * codeTF;


@property (nonatomic,strong) NSArray * danweiArray;
@property (nonatomic,strong) NSArray * zhiweiArray;
@property (nonatomic,strong) NSArray * keshiArray;

@end

@implementation NYRegisterViewController

- (UITableView *)tableView {
    if (!_tableView) {
        _tableView = [[UITableView alloc] initWithFrame:CGRectZero style:UITableViewStyleGrouped];
        [self.view addSubview:_tableView];
        _tableView.sd_layout
        .topSpaceToView(self.view, 0)
        .rightSpaceToView(self.view, 0)
        .leftSpaceToView(self.view, 0)
        .bottomSpaceToView(self.view, 0);
        _tableView.delegate = self;
        _tableView.dataSource = self;
        _tableView.separatorInset = UIEdgeInsetsMake(0, 0, 0, 0);
        _tableView.tableFooterView = [UIView new];
        _tableView.showsVerticalScrollIndicator = YES;
        _tableView.showsHorizontalScrollIndicator = NO;
    }
    return _tableView;
}

- (void)viewDidLoad {
    [super viewDidLoad];
    self.view.backgroundColor = [UIColor whiteColor];
    self.navigationItem.title = @"注册";
    [self.tableView registerClass:[UITableViewCell class] forCellReuseIdentifier:@"Cell"];
//    [self.tableView registerClass:[NYNameInputCell class] forCellReuseIdentifier:@"INPUTCELLID"];
    [self.tableView registerClass:[NYChuZhenTitleCell class] forCellReuseIdentifier:@"TitleCell"];
    [self.tableView registerClass:[NYRegisterInputPhoneCell class] forCellReuseIdentifier:@"NYRegisterPhoneCellID"];
    [self.tableView registerClass:[NYRegisterChoiceSexCell class] forCellReuseIdentifier:@"NYChoiceSexCellID"];
    [self.tableView registerClass:[NYRegisterShanChangCell class] forCellReuseIdentifier:@"NYRegisterShanChangCellID"];
    [self.tableView registerClass:[NYRegisterChoicePictureCell class] forCellReuseIdentifier:@"ChoicePictureCellID"];
    [self.tableView registerClass:[NYRegisterUploadCardImgCell class] forCellReuseIdentifier:@"NYUploadCardImgCellID"];
    [self.tableView registerClass:[NYRegisterUploadCardCell class] forCellReuseIdentifier:@"NYRegisterCardCEllID"];
    
    _sex = 0;
    _captchaId = @""; //验证码编号

    _userImage = nil;
    _zigeImage = nil;
    _card1Image = nil;
    _card2Image = nil;
    
}

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView
{
    return 9;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    if (section == 0 || section == 3 || section == 4 || section == 5 || section == 6 || section == 7 || section == 8) {
        return 2;
    }else if (section == 1){
        return 3;
    }else if (section == 2){
        return 1;
    }
    return 0;
}

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath
{
    if (indexPath.section == 0) {
        return 50;
    }else if (indexPath.section == 1){
        return 50;
    }else if (indexPath.section == 2){
        return 50;
    }else if (indexPath.section == 3){
        if (indexPath.row == 0) {
            return 50;
        }else if (indexPath.row == 1){
            return 120;
        }
    }else if (indexPath.section == 4){
        if (indexPath.row == 0) {
            return 50;
        }else if (indexPath.row == 1){
            return 120;
        }
    }else if (indexPath.section == 5){
        if (indexPath.row == 0) {
            return 50;
        }else if (indexPath.row == 1){
            return 120;
        }
    }else if (indexPath.section == 6){
        if (indexPath.row == 0) {
            return 50;
        }else if (indexPath.row == 1){
            return 260;
        }
    }else if (indexPath.section == 7){
        if (indexPath.row == 0) {
            return 50;
        }else if (indexPath.row == 1){
            return 260;
        }
    }else if (indexPath.section == 8){
        return 50;
    }
    return 0;
}

- (CGFloat)tableView:(UITableView *)tableView heightForHeaderInSection:(NSInteger)section
{
    return 0.0001f;
}

- (CGFloat)tableView:(UITableView *)tableView heightForFooterInSection:(NSInteger)section
{
    if (section == 8) {
        return 200;
    }
    return 8;
}

-(UIView *)tableView:(UITableView *)tableView viewForHeaderInSection:(NSInteger)section {
    return [UIView new];
}

- (UIView *)tableView:(UITableView *)tableView viewForFooterInSection:(NSInteger)section {
    if (section == 8) {
        UIView * bgView = [[UIView alloc] init];
        bgView.backgroundColor = [UIColor clearColor];
        
        UIButton * registerButton = [UIButton buttonWithType:UIButtonTypeCustom];
        [registerButton setTitle:@"注册" forState:UIControlStateNormal];
        [registerButton setBackgroundColor:COLOR_RED];
        [registerButton setTitleColor:[UIColor whiteColor] forState:UIControlStateNormal];
        [bgView addSubview:registerButton];
        
        registerButton.sd_layout
        .leftSpaceToView(bgView, 30)
        .topSpaceToView(bgView, 40)
        .rightSpaceToView(bgView, 30)
        .heightIs(50);
        registerButton.sd_cornerRadius = @5;
        [registerButton addTarget:self action:@selector(clickRegisterButton:) forControlEvents:UIControlEventTouchUpInside];
        
        
        UIButton * loginButton = [UIButton buttonWithType:UIButtonTypeCustom];
        [loginButton setTitle:@"登录" forState:UIControlStateNormal];
        [loginButton setBackgroundColor:MAINCOLOR];
        [loginButton setTitleColor:[UIColor whiteColor] forState:UIControlStateNormal];
        [bgView addSubview:loginButton];
        
        loginButton.sd_layout
        .leftSpaceToView(bgView, 30)
        .topSpaceToView(registerButton, 30)
        .rightSpaceToView(bgView, 30)
        .heightIs(50);
        loginButton.sd_cornerRadius = @5;
        [loginButton addTarget:self action:@selector(clickLoginButton:) forControlEvents:UIControlEventTouchUpInside];

        return bgView;
    }
    return [UIView new];
}

#pragma mark - 点击登录按钮
- (void)clickLoginButton:(UIButton *)button
{
    [self.navigationController popViewControllerAnimated:YES];
}

#pragma mark - 点击注册

- (void)clickRegisterButton:(UIButton *)button
{
    NSString * nameString = [self.nameTF.text stringByTrimmingCharactersInSet:[NSCharacterSet whitespaceAndNewlineCharacterSet]];
    
    NSString * danWeiString = [self.danWeiTF.text stringByTrimmingCharactersInSet:[NSCharacterSet whitespaceAndNewlineCharacterSet]];
    
    NSString * zhiWeiString = [self.zhiWeiTF.text stringByTrimmingCharactersInSet:[NSCharacterSet whitespaceAndNewlineCharacterSet]];
    
    NSString * keShiString = [self.keShiTF.text stringByTrimmingCharactersInSet:[NSCharacterSet whitespaceAndNewlineCharacterSet]];
    
    NSString * priceString = [self.priceTF.text stringByTrimmingCharactersInSet:[NSCharacterSet whitespaceAndNewlineCharacterSet]];
    

    NSString * shanChangString1 = [self.shanChangTF1.text stringByTrimmingCharactersInSet:[NSCharacterSet whitespaceAndNewlineCharacterSet]];
    
    NSString * shanChangString2 = [self.shanChangTF2.text stringByTrimmingCharactersInSet:[NSCharacterSet whitespaceAndNewlineCharacterSet]];
    
    NSString * shanChangString3 = [self.shanChangTF3.text stringByTrimmingCharactersInSet:[NSCharacterSet whitespaceAndNewlineCharacterSet]];

    NSString * shanChangContentString1 = [self.shanChangTextView1.text stringByTrimmingCharactersInSet:[NSCharacterSet whitespaceAndNewlineCharacterSet]];
    
    NSString * shanChangContentString2 = [self.shanChangTextView2.text stringByTrimmingCharactersInSet:[NSCharacterSet whitespaceAndNewlineCharacterSet]];
    
    NSString * shanChangContentString3 = [self.shanChangTextView3.text stringByTrimmingCharactersInSet:[NSCharacterSet whitespaceAndNewlineCharacterSet]];

    
    
    NSString * phoneString = [self.phoneTF.text stringByTrimmingCharactersInSet:[NSCharacterSet whitespaceAndNewlineCharacterSet]];
    
    NSString * codeString = [self.codeTF.text stringByTrimmingCharactersInSet:[NSCharacterSet whitespaceAndNewlineCharacterSet]];

    
    if (nameString.length == 0) {
        MYALERT(@"请输入患者姓名");
        return;
    }
    if (danWeiString.length == 0) {
        MYALERT(@"请选择工作单位");
        return;
    }
    if (zhiWeiString.length == 0) {
        MYALERT(@"请选择职位");
        return;
    }
    if (keShiString.length == 0) {
        MYALERT(@"请选择科室");
        return;
    }
    if (priceString.length == 0) {
        MYALERT(@"请输入预约金额");
        return;
    }
    
    if (shanChangString1.length == 0) {
        MYALERT(@"请输入擅长1名称");
        return;
    }
    if (shanChangContentString1.length == 0) {
        MYALERT(@"请输入擅长1内容");
        return;
    }

    if (shanChangString2.length == 0) {
        MYALERT(@"请输入擅长2名称");
        return;
    }
    if (shanChangContentString2.length == 0) {
        MYALERT(@"请输入擅长2内容");
        return;
    }

    if (shanChangString3.length == 0) {
        MYALERT(@"请输入擅长3名称");
        return;
    }
    if (shanChangContentString3.length == 0) {
        MYALERT(@"请输入擅长3内容");
        return;
    }

    if (_userImage == nil) {
        MYALERT(@"请上传本人照片");
        return;
    }
    
    if (_zigeImage == nil) {
        MYALERT(@"请上传资格证书");
        return;
    }

    if (_card1Image == nil) {
        MYALERT(@"请上传身份证正面照");
        return;
    }

    if (_card2Image == nil) {
        MYALERT(@"请上传身份证反面照");
        return;
    }

    if (phoneString.length == 0) {
        MYALERT(@"请输入手机号");
        return;
    }
    if (phoneString.length != 11) {
        MYALERT(@"手机格式不正确");
        return;
    }

    if (codeString.length == 0) {
        MYALERT(@"请输入验证码");
        return;
    }

    
    NSInteger danWeiCount = 0;
    for (NSDictionary * obj in self.danweiArray) {
        if ([obj[@"name"] isEqualToString:danWeiString]) {
            danWeiCount = [obj[@"id"] integerValue];
        }
    }
    
    NSInteger zhiWeiCount = 0;
    for (NSDictionary * obj in self.zhiweiArray) {
        if ([obj[@"name"] isEqualToString:zhiWeiString]) {
            zhiWeiCount = [obj[@"id"] integerValue];
        }
    }
    
    NSInteger keShiCount = 0;
    for (NSDictionary * obj in self.keshiArray) {
        if ([obj[@"name"] isEqualToString:keShiString]) {
            keShiCount = [obj[@"id"] integerValue];
        }
    }

    NSArray * shanChangArray = @[@{@"name":shanChangString1,@"describe":shanChangContentString1},
        @{@"name":shanChangString2,@"describe":shanChangContentString2},
        @{@"name":shanChangString3,@"describe":shanChangContentString3}];
    
    
    [SVProgressHUD showWithStatus:nil];
    [SVProgressHUD setDefaultMaskType:(SVProgressHUDMaskTypeClear)];
    
    WEAKSELF
    [PPHTTPRequest postUploadWithparameters:nil images:@[_userImage,_zigeImage,_card1Image,_card2Image] success:^(id response) {
        if ([response[@"code"] integerValue] == 0) {
            NSArray * imageArray = response[@"data"][@"urls"];
            
            NSDictionary * dict = @{@"account":phoneString,
                                    @"adepts":shanChangArray,
                                    @"captchaCode":codeString,
                                    @"captchaId":_captchaId,
                                    @"gender":@(_sex),
                                    @"hosId":@(danWeiCount),
                                    @"hosName":danWeiString,
                                    @"identityCard":[NSString stringWithFormat:@"%@,%@",imageArray[2],imageArray[3]],
                                    @"name":nameString,
                                    @"offId":@(keShiCount),
                                    @"offName":keShiString,
                                    @"photo":imageArray[0],
                                    @"physicianLicence":imageArray[1],
                                    @"positionId":@(zhiWeiCount),
                                    @"positionName":zhiWeiString,
                                    @"registrationFee":@([priceString doubleValue]),
                                    };
            
            [PPHTTPRequest postRegisterInfoWithParameters:dict success:^(id response) {
                [SVProgressHUD dismiss];
                if ([response[@"code"] integerValue] == 0) {
                    MYALERT(@"注册成功，等待审核");
                    [weakSelf.navigationController popViewControllerAnimated:YES];
                }else{
                    MYALERT(response[@"msg"]);
                }
            } failure:^(NSError *error) {
                [SVProgressHUD dismiss];
                MYALERT(@"注册失败");
            }];
        }else{
            [SVProgressHUD dismiss];
            MYALERT(@"注册失败");
        }
    } failure:^(NSError *error) {
        [SVProgressHUD dismiss];
        MYALERT(@"注册失败");
    }];

}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    WEAKSELF
    if (indexPath.section == 0) { //姓名
        if (indexPath.row == 0) {
//            NYNameInputCell * cell = [tableView dequeueReusableCellWithIdentifier:@"INPUTCELLID"];
            static NSString * cellID = @"1";
            NYNameInputCell * cell = [tableView dequeueReusableCellWithIdentifier:cellID];
            if (cell == nil) {
                cell = [[NYNameInputCell alloc] initWithStyle:UITableViewCellStyleDefault reuseIdentifier:cellID];
            }

            cell.typeLB.text = @"姓名";
            cell.inputTF.placeholder = @"请输入姓名";
            _nameTF = cell.inputTF;
            return cell;
        }else if (indexPath.row == 1){
            NYRegisterChoiceSexCell * cell = [tableView dequeueReusableCellWithIdentifier:@"NYChoiceSexCellID"];
            cell.clickManButton = ^(RankButton * _Nonnull setBtn) {
                _sex = 1;
                if (!setBtn.isSelected) {
                    setBtn.selected = !setBtn.selected;
                }
            };
            cell.clickWomanButton = ^(RankButton * _Nonnull setBtn) {
                _sex = 0;
                if (!setBtn.isSelected) {
                    setBtn.selected = !setBtn.selected;
                }
            };
            return cell;
        }
    }else if (indexPath.section == 1){//单位
        if (indexPath.row == 0) {
//            NYNameInputCell * cell = [tableView dequeueReusableCellWithIdentifier:@"INPUTCELLID"];
            static NSString * cellID = @"2";
            NYNameInputCell * cell = [tableView dequeueReusableCellWithIdentifier:cellID];
            if (cell == nil) {
                cell = [[NYNameInputCell alloc] initWithStyle:UITableViewCellStyleDefault reuseIdentifier:cellID];
            }

            cell.typeLB.text = @"单位";
            cell.inputTF.placeholder = @"请选择工作单位";
            _danWeiTF = cell.inputTF;
            _danWeiTF.delegate = self;
            return cell;
        }else if (indexPath.row == 1){
//            NYNameInputCell * cell = [tableView dequeueReusableCellWithIdentifier:@"INPUTCELLID"];
            static NSString * cellID = @"3";
            NYNameInputCell * cell = [tableView dequeueReusableCellWithIdentifier:cellID];
            if (cell == nil) {
                cell = [[NYNameInputCell alloc] initWithStyle:UITableViewCellStyleDefault reuseIdentifier:cellID];
            }

            cell.typeLB.text = @"职位";
            cell.inputTF.placeholder = @"请选择职位";
            _zhiWeiTF = cell.inputTF;
            _zhiWeiTF.delegate = self;
            return cell;
        }else if (indexPath.row == 2){
//            NYNameInputCell * cell = [tableView dequeueReusableCellWithIdentifier:@"INPUTCELLID"];
            static NSString * cellID = @"4";
            NYNameInputCell * cell = [tableView dequeueReusableCellWithIdentifier:cellID];
            if (cell == nil) {
                cell = [[NYNameInputCell alloc] initWithStyle:UITableViewCellStyleDefault reuseIdentifier:cellID];
            }

            cell.typeLB.text = @"科室";
            cell.inputTF.placeholder = @"选择科室";
            _keShiTF = cell.inputTF;
            _keShiTF.delegate = self;
            return cell;
        }
    }else if (indexPath.section == 2){//预约费
        if (indexPath.row == 0) {
//            NYNameInputCell * cell = [tableView dequeueReusableCellWithIdentifier:@"INPUTCELLID"];
            static NSString * cellID = @"5";
            NYNameInputCell * cell = [tableView dequeueReusableCellWithIdentifier:cellID];
            if (cell == nil) {
                cell = [[NYNameInputCell alloc] initWithStyle:UITableViewCellStyleDefault reuseIdentifier:cellID];
            }

            cell.typeLB.text = @"预约费";
            cell.inputTF.placeholder = @"请输入预约费金额";
            _priceTF = cell.inputTF;
            _priceTF.keyboardType = UIKeyboardTypeDecimalPad;
            _priceTF.delegate = self;
            return cell;
        }
    }else if (indexPath.section == 3){//擅长1
        if (indexPath.row == 0) {
//            NYNameInputCell * cell = [tableView dequeueReusableCellWithIdentifier:@"INPUTCELLID"];
            static NSString * cellID = @"6";
            NYNameInputCell * cell = [tableView dequeueReusableCellWithIdentifier:cellID];
            if (cell == nil) {
                cell = [[NYNameInputCell alloc] initWithStyle:UITableViewCellStyleDefault reuseIdentifier:cellID];
            }

            cell.typeLB.text = @"擅长1";
            cell.inputTF.placeholder = @"请输入名称";
            _shanChangTF1 = cell.inputTF;
            return cell;
        }else if (indexPath.row == 1){
            NYRegisterShanChangCell * cell = [tableView dequeueReusableCellWithIdentifier:@"NYRegisterShanChangCellID"];
            cell.infoTextView.placeholder = @"临床症状表现";
            _shanChangTextView1 = cell.infoTextView;
            return cell;
        }
    }else if (indexPath.section == 4){ //擅长2
        if (indexPath.row == 0) {
//            NYNameInputCell * cell = [tableView dequeueReusableCellWithIdentifier:@"INPUTCELLID"];
            static NSString * cellID = @"7";
            NYNameInputCell * cell = [tableView dequeueReusableCellWithIdentifier:cellID];
            if (cell == nil) {
                cell = [[NYNameInputCell alloc] initWithStyle:UITableViewCellStyleDefault reuseIdentifier:cellID];
            }

            cell.typeLB.text = @"擅长2";
            cell.inputTF.placeholder = @"请输入名称";
            _shanChangTF2 = cell.inputTF;
            return cell;
        }else if (indexPath.row == 1){
            NYRegisterShanChangCell * cell = [tableView dequeueReusableCellWithIdentifier:@"NYRegisterShanChangCellID"];
            cell.infoTextView.placeholder = @"临床症状表现";
            _shanChangTextView2 = cell.infoTextView;
            return cell;
        }
    }else if (indexPath.section == 5){ //擅长3
        if (indexPath.row == 0) {
//            NYNameInputCell * cell = [tableView dequeueReusableCellWithIdentifier:@"INPUTCELLID"];
            static NSString * cellID = @"8";
            NYNameInputCell * cell = [tableView dequeueReusableCellWithIdentifier:cellID];
            if (cell == nil) {
                cell = [[NYNameInputCell alloc] initWithStyle:UITableViewCellStyleDefault reuseIdentifier:cellID];
            }

            cell.typeLB.text = @"擅长3";
            cell.inputTF.placeholder = @"请输入名称";
            _shanChangTF3 = cell.inputTF;
            return cell;
        }else if (indexPath.row == 1){
            NYRegisterShanChangCell * cell = [tableView dequeueReusableCellWithIdentifier:@"NYRegisterShanChangCellID"];
            cell.infoTextView.placeholder = @"临床症状表现";
            _shanChangTextView3 = cell.infoTextView;
            return cell;
        }
    }
//    else if (indexPath.section == 6){ //上传本人照片
//        if (indexPath.row == 0) {
//            NYChuZhenTitleCell * cell = [tableView dequeueReusableCellWithIdentifier:@"TitleCell"];
//            cell.titleLB.text = @"上传本人照片";
//            return cell;
//        }else if (indexPath.row == 1){
//            NYRegisterChoicePictureCell * cell = [tableView dequeueReusableCellWithIdentifier:@"ChoicePictureCellID"];
//            _headerImg = cell.pictureIMG;
//            cell.clickChoicePictureImg = ^{
//                [weakSelf clickHeaderImgWith:_headerImg];
//            };
//            return cell;
//        }
//    }
    else if (indexPath.section == 6){ //上传医师资格证
        if (indexPath.row == 0) {
            NYChuZhenTitleCell * cell = [tableView dequeueReusableCellWithIdentifier:@"TitleCell"];
            cell.titleLB.text = @"上传图片";
            return cell;
        }else if (indexPath.row == 1){
            NYRegisterUploadCardImgCell * cell = [tableView dequeueReusableCellWithIdentifier:@"NYUploadCardImgCellID"];
            _headerImg = cell.pictureIMG;
            _headerImg.tag = 1;
            _zigeImg = cell.pictureIMG2;
            _zigeImg.tag = 2;
            
            cell.clickChoicePictureBtn = ^{
                [weakSelf clickHeaderImgWith:_headerImg];
            };
            
            cell.clickChoicePictureBtn2 = ^{
                [weakSelf clickHeaderImgWith:_zigeImg];
            };
            return cell;
        }
    }else if (indexPath.section == 7){ //上传身份证图片
        if (indexPath.row == 0) {
            NYChuZhenTitleCell * cell = [tableView dequeueReusableCellWithIdentifier:@"TitleCell"];
            cell.titleLB.text = @"上传身份证图片";
            return cell;
        }else if (indexPath.row == 1){
            NYRegisterUploadCardCell * cell = [tableView dequeueReusableCellWithIdentifier:@"NYRegisterCardCEllID"];
            _card1Img = cell.pictureIMG1;
            _card2Img = cell.pictureIMG2;
            _card1Img.tag = 3;
            _card2Img.tag = 4;
            cell.clickChoicePictureBtn1 = ^{
                [weakSelf clickHeaderImgWith:_card1Img];
            };
            cell.clickChoicePictureBtn2 = ^{
                [weakSelf clickHeaderImgWith:_card2Img];
            };
            return cell;
        }
    }else if (indexPath.section == 8){ //手机号&验证码
        if (indexPath.row == 0) {
            NYRegisterInputPhoneCell * cell = [tableView dequeueReusableCellWithIdentifier:@"NYRegisterPhoneCellID"];
            _phoneTF = cell.phoneTF;
            _phoneTF.keyboardType = UIKeyboardTypePhonePad;
            _phoneTF.delegate = self;
            _phoneTF.maxLength = 11;
            _codeButton = cell.codeButton;
            cell.clickGetCodeButton = ^{
                [weakSelf sendCode:_codeButton];
            };
            return cell;
        }else if (indexPath.row == 1){
//            NYNameInputCell * cell = [tableView dequeueReusableCellWithIdentifier:@"INPUTCELLID"];
            static NSString * cellID = @"9";
            NYNameInputCell * cell = [tableView dequeueReusableCellWithIdentifier:cellID];
            if (cell == nil) {
                cell = [[NYNameInputCell alloc] initWithStyle:UITableViewCellStyleDefault reuseIdentifier:cellID];
            }

            cell.typeLB.text = @"验证码";
            cell.inputTF.placeholder = @"请输入短信验证码";
            _codeTF = cell.inputTF;
            _codeTF.keyboardType = UIKeyboardTypePhonePad;
            _codeTF.delegate = self;
            _codeTF.maxLength = 6;
            return cell;
        }
    }
    
    UITableViewCell * cell = [tableView dequeueReusableCellWithIdentifier:@"Cell"];
    return cell;
}

#pragma mark - 获取验证码
- (void)sendCode:(JKCountDownButton *)button
{
    NSString * phone = [_phoneTF.text stringByTrimmingCharactersInSet:[NSCharacterSet whitespaceAndNewlineCharacterSet]];
    if (phone.length == 0) {
        [SVProgressHUD showInfoWithStatus:@"手机号不能为空"];
        return;
    }else if (phone.length != 11){
        [SVProgressHUD showInfoWithStatus:@"手机号不正确"];
        return;
    }
    
    [SVProgressHUD showWithStatus:nil];
    [SVProgressHUD setDefaultMaskType:(SVProgressHUDMaskTypeClear)];
    
    [PPHTTPRequest postGetCodeWithParameters:phone success:^(id response) {
        [SVProgressHUD dismiss];
        if ([response[@"code"] integerValue] == 0) {
            [SVProgressHUD showSuccessWithStatus:@"验证码已发送"];
            
            _captchaId = response[@"data"][@"captchaId"]; //验证码编号
            
            // 发送验证码
            _codeButton.enabled = NO;
            [_codeButton startCountDownWithSecond:60];
            [_codeButton countDownChanging:^NSString *(JKCountDownButton *countDownButton,NSUInteger second) {
                NSString *title = [NSString stringWithFormat:@"%zd",second];
                return title;
            }];
            [_codeButton countDownFinished:^NSString *(JKCountDownButton *countDownButton, NSUInteger second) {
                _codeButton.enabled = YES;
                return @"重新获取";
            }];
        }else{
            [SVProgressHUD showErrorWithStatus:@"请求失败"];
        }
        
    } failure:^(NSError *error) {
        [SVProgressHUD dismiss];
        [SVProgressHUD showErrorWithStatus:@"请求失败"];
    }];
    
}


- (BOOL)textFieldShouldBeginEditing:(UITextField *)textField
{
    [_nameTF resignFirstResponder];
    [_priceTF resignFirstResponder];
    [_shanChangTF1 resignFirstResponder];
    [_shanChangTextView1 resignFirstResponder];
    [_shanChangTF2 resignFirstResponder];
    [_shanChangTextView2 resignFirstResponder];
    [_shanChangTF3 resignFirstResponder];
    [_shanChangTextView3 resignFirstResponder];

    WEAKSELF
    
    if (textField == _danWeiTF) { //选择工作单位
        [PPHTTPRequest GetHospitalListInfoWithParameters:nil success:^(id response) {
            [SVProgressHUD dismiss];
            if ([response[@"code"] integerValue] == 0) {
                self.danweiArray = response[@"data"][@"list"];
                
                NSMutableArray * muArr = [NSMutableArray array];
                for (NSDictionary * dict in self.danweiArray) {
                    [muArr addObject:dict[@"name"]];
                }
                
                // 自定义单列字符串
                NSArray *dataSource = [muArr copy];
                [BRStringPickerView showStringPickerWithTitle:@"请选择工作单位" dataSource:dataSource defaultSelValue:_danWeiTF.text isAutoSelect:YES themeColor:nil resultBlock:^(id selectValue) {
                    weakSelf.danWeiTF.text = selectValue;
                } cancelBlock:^{
                    NSLog(@"点击了背景视图或取消按钮");
                }];
                
            }else{
                MYALERT(response[@"msg"]);
            }
        } failure:^(NSError *error) {
            [SVProgressHUD dismiss];
            MYALERT(@"请求失败");
        }];
        
    }else if (textField == _zhiWeiTF){ //现在职位
        [PPHTTPRequest GetDictionaryDataInfoWithParameters:@"3" success:^(id response) {
            [SVProgressHUD dismiss];
            if ([response[@"code"] integerValue] == 0) {
                self.zhiweiArray = response[@"data"][@"dictionaries"];
                
                NSMutableArray * muArr = [NSMutableArray array];
                for (NSDictionary * dict in self.zhiweiArray) {
                    [muArr addObject:dict[@"name"]];
                }
                
                // 自定义单列字符串
                NSArray *dataSource = [muArr copy];
                [BRStringPickerView showStringPickerWithTitle:@"请选择职位" dataSource:dataSource defaultSelValue:_zhiWeiTF.text isAutoSelect:YES themeColor:nil resultBlock:^(id selectValue) {
                    weakSelf.zhiWeiTF.text = selectValue;
                } cancelBlock:^{
                    NSLog(@"点击了背景视图或取消按钮");
                }];
                
            }else{
                MYALERT(response[@"msg"]);
            }
        } failure:^(NSError *error) {
            [SVProgressHUD dismiss];
            MYALERT(@"请求失败");
        }];
    }else if (textField == _keShiTF){ //科室
        
        [PPHTTPRequest GetKeShiListInfoWithParameters:nil success:^(id response) {
            [SVProgressHUD dismiss];
            if ([response[@"code"] integerValue] == 0) {
                self.keshiArray = response[@"data"][@"list"];
                
                NSMutableArray * muArr = [NSMutableArray array];
                for (NSDictionary * dict in self.keshiArray) {
                    [muArr addObject:dict[@"name"]];
                }
                
                // 自定义单列字符串
                NSArray *dataSource = [muArr copy];
                [BRStringPickerView showStringPickerWithTitle:@"选择科室" dataSource:dataSource defaultSelValue:_keShiTF.text isAutoSelect:YES themeColor:nil resultBlock:^(id selectValue) {
                    weakSelf.keShiTF.text = selectValue;
                } cancelBlock:^{
                    NSLog(@"点击了背景视图或取消按钮");
                }];
                
            }else{
                MYALERT(response[@"msg"]);
            }
        } failure:^(NSError *error) {
            [SVProgressHUD dismiss];
            MYALERT(@"请求失败");
        }];
    }else if (textField == _phoneTF || textField == _codeTF || textField == _priceTF){
        return YES;
    }
    
    return NO;
}

- (BOOL)textField:(UITextField *)textField shouldChangeCharactersInRange:(NSRange)range replacementString:(NSString *)string {
    //如果是限制只能输入数字的文本框
    if (_phoneTF == textField) {
        return [self validateNumber1:string];
    }else if (_codeTF == textField){
        return [self validateNumber2:string];
    }else if (_priceTF == textField){
        // 1 不能直接输入小数点
        if ( [textField.text isEqualToString:@""] && [string isEqualToString:@"."] )  return NO;
        
        // 2 输入框第一个字符为“0”时候，第二个字符如果不是“.”,那么文本框内的显示内容就是新输入的字符[textField.text length] == 1  防止例如0.5会变成5
        NSRange zeroRange = [textField.text rangeOfString:@"0"];
        if(zeroRange.length == 1 && [textField.text length] == 1 && ![string isEqualToString:@"."]){
            textField.text = string;
            return NO;
        }
        // 3 保留两位小数
        NSUInteger remain = 2;
        NSRange pointRange = [textField.text rangeOfString:@"."];
        
        // 拼接输入的最后一个字符
        NSString *tempStr = [textField.text stringByAppendingString:string];
        NSUInteger strlen = [tempStr length];
        
        // 输入框内存在小数点， 不让再次输入“.” 或者 总长度-包括小数点之前的长度>remain 就不能再输入任何字符
        if(pointRange.length > 0 &&([string isEqualToString:@"."] || strlen - (pointRange.location + 1) > remain))
            return NO;
        
        // 4 小数点已经存在情况下可以输入的字符集  and 小数点还不存在情况下可以输入的字符集
        NSCharacterSet *numbers = (pointRange.length > 0)?[NSCharacterSet characterSetWithCharactersInString:@"0123456789"] : [NSCharacterSet characterSetWithCharactersInString:@"0123456789."];
        
        NSScanner *scanner = [NSScanner scannerWithString:string];
        NSString *buffer;
        // 判断string在不在numbers的字符集合内
        BOOL scan = [scanner scanCharactersFromSet:numbers intoString:&buffer];
        
        if ( !scan && ([string length] != 0) )  // 包括输入空格scan为NO， 点击删除键[string length]为0
        {
            return NO;
        }
        
    }
    //否则返回yes,不限制其他textfield
    return YES;
}

#pragma mark - 只能输入数字
- (BOOL)validateNumber1:(NSString*)number {
    
    BOOL res = YES;
    NSCharacterSet* tmpSet = [NSCharacterSet characterSetWithCharactersInString:@"0123456789"];
    int i = 0;
    while (i < number.length) {
        NSString * string = [number substringWithRange:NSMakeRange(i, 1)];
        NSRange range = [string rangeOfCharacterFromSet:tmpSet];
        if (range.length == 0) {
            res = NO;
            break;
        }
        i++;
    }
    
    if (_phoneTF.text.length == 0 && ![number isEqualToString:@"1"]) {
        res = NO;
    }
    
    return res;
}

#pragma mark - 只能输入数字
- (BOOL)validateNumber2:(NSString*)number {
    
    BOOL res = YES;
    NSCharacterSet* tmpSet = [NSCharacterSet characterSetWithCharactersInString:@"0123456789"];
    int i = 0;
    while (i < number.length) {
        NSString * string = [number substringWithRange:NSMakeRange(i, 1)];
        NSRange range = [string rangeOfCharacterFromSet:tmpSet];
        if (range.length == 0) {
            res = NO;
            break;
        }
        i++;
    }
    
    return res;
}



- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath
{
    [tableView deselectRowAtIndexPath:indexPath animated:YES];
}


#pragma mark - 选择头像
- (void)clickHeaderImgWith:(UIImageView *)imageView
{
    self.choiceImg = imageView;
    
    UIAlertController *alert=[UIAlertController alertControllerWithTitle:nil message:nil preferredStyle:UIAlertControllerStyleActionSheet];
    [alert addAction:[UIAlertAction actionWithTitle:@"相册" style:0 handler:^(UIAlertAction * _Nonnull action) {
        [self pushImagePickerController];
    }]];
    [alert addAction:[UIAlertAction actionWithTitle:@"相机" style:0 handler:^(UIAlertAction * _Nonnull action) {
        [self takePhoto];
    }]];
    [alert addAction:[UIAlertAction actionWithTitle:@"取消" style:UIAlertActionStyleCancel handler:^(UIAlertAction * _Nonnull action) {
    }]];
    [self presentViewController:alert animated:YES completion:nil];
}

- (UIImagePickerController *)imagePickerVc {
    if (_imagePickerVc == nil) {
        _imagePickerVc = [[UIImagePickerController alloc] init];
        _imagePickerVc.delegate = self;
        // set appearance / 改变相册选择页的导航栏外观
        _imagePickerVc.navigationBar.barTintColor = self.navigationController.navigationBar.barTintColor;
        _imagePickerVc.navigationBar.tintColor = self.navigationController.navigationBar.tintColor;
        UIBarButtonItem *tzBarItem, *BarItem;
        if (iOS9Later) {
            tzBarItem = [UIBarButtonItem appearanceWhenContainedInInstancesOfClasses:@[[TZImagePickerController class]]];
            BarItem = [UIBarButtonItem appearanceWhenContainedInInstancesOfClasses:@[[UIImagePickerController class]]];
        } else {
            tzBarItem = [UIBarButtonItem appearanceWhenContainedIn:[TZImagePickerController class], nil];
            BarItem = [UIBarButtonItem appearanceWhenContainedIn:[UIImagePickerController class], nil];
        }
        NSDictionary *titleTextAttributes = [tzBarItem titleTextAttributesForState:UIControlStateNormal];
        [BarItem setTitleTextAttributes:titleTextAttributes forState:UIControlStateNormal];
    }
    return _imagePickerVc;
}

- (void)imagePickerController:(TZImagePickerController *)picker didFinishPickingPhotos:(NSArray<UIImage *> *)photos sourceAssets:(NSArray *)assets isSelectOriginalPhoto:(BOOL)isSelectOriginalPhoto infos:(NSArray<NSDictionary *> *)infos {
    UIImage *image = photos[0];
    self.choiceImg.image = image;
    if (self.choiceImg.tag == 1) {
        _userImage = image;
    }else if (self.choiceImg.tag == 2){
        _zigeImage = image;
    }else if (self.choiceImg.tag == 3){
        _card1Image = image;
    }else if (self.choiceImg.tag == 4){
        _card2Image = image;
    }
    [picker dismissViewControllerAnimated:YES completion:nil];
    
//    [self uploadHeadImage:image];
}

- (void)imagePickerController:(UIImagePickerController *)picker didFinishPickingMediaWithInfo:(NSDictionary *)info
{
    UIImage *image = [info objectForKey:UIImagePickerControllerOriginalImage];
    self.choiceImg.image = image;
    if (self.choiceImg.tag == 1) {
        _userImage = image;
    }else if (self.choiceImg.tag == 2){
        _zigeImage = image;
    }else if (self.choiceImg.tag == 3){
        _card1Image = image;
    }else if (self.choiceImg.tag == 4){
        _card2Image = image;
    }

    [picker dismissViewControllerAnimated:YES completion:nil];
    
//    [self uploadHeadImage:image];
}
#pragma mark - 拍照
- (void)takePhoto {
    AVAuthorizationStatus authStatus = [AVCaptureDevice authorizationStatusForMediaType:AVMediaTypeVideo];
    if ((authStatus == AVAuthorizationStatusRestricted || authStatus == AVAuthorizationStatusDenied) && iOS7Later) {
        // 无相机权限 做一个友好的提示
        UIAlertView * alert = [[UIAlertView alloc]initWithTitle:@"无法使用相机" message:@"请在iPhone的""设置-隐私-相机""中允许访问相机" delegate:self cancelButtonTitle:@"取消" otherButtonTitles:@"设置", nil];
        alert.delegate = self;
        [alert show];
        // 拍照之前还需要检查相册权限
    } else if ([TZImageManager authorizationStatus] == 2) { // 已被拒绝，没有相册权限，将无法保存拍的照片
        UIAlertView * alert = [[UIAlertView alloc]initWithTitle:@"无法访问相册" message:@"请在iPhone的""设置-隐私-相册""中允许访问相册" delegate:self cancelButtonTitle:@"取消" otherButtonTitles:@"设置", nil];
        alert.delegate = self;
        alert.tag = 1;
        [alert show];
    } else { // 调用相机
        UIImagePickerControllerSourceType sourceType = UIImagePickerControllerSourceTypeCamera;
        if ([UIImagePickerController isSourceTypeAvailable: UIImagePickerControllerSourceTypeCamera]) {
            self.imagePickerVc.sourceType = sourceType;
            if(iOS8Later) {
                _imagePickerVc.modalPresentationStyle = UIModalPresentationOverCurrentContext;
            }
            [self presentViewController:_imagePickerVc animated:YES completion:nil];
        } else {
            NSLog(@"模拟器中无法打开照相机,请在真机中使用");
        }
    }
}
#pragma mark - 选择照片
- (void)pushImagePickerController {
    
    TZImagePickerController *pvc = [[TZImagePickerController alloc] initWithMaxImagesCount:1 columnNumber:3 delegate:self];
    pvc.allowPickingVideo = NO;
    pvc.allowPickingImage = YES;
    pvc.allowPickingOriginalPhoto = NO;
    pvc.allowPickingGif = NO;
    pvc.delegate = self;
    pvc.showSelectBtn = NO;
    pvc.allowCrop = YES;
    pvc.cropRect = CGRectMake(0, (NYScreenH - NYScreenW)/2, NYScreenW, NYScreenW);
    [self presentViewController:pvc animated:YES completion:nil];
}

@end
