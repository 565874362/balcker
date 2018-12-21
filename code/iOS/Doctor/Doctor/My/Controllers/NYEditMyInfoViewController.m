//
//  NYEditMyInfoViewController.m
//  Doctor
//
//  Created by niuyao on 2018/12/5.
//  Copyright © 2018 joymates. All rights reserved.
//

#import "NYEditMyInfoViewController.h"
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
#import "NYMyInfoDetailModel.h"
#import "NYAdeptEntitiesModel.h"

@interface NYEditMyInfoViewController ()<UITableViewDelegate,UITableViewDataSource,UIImagePickerControllerDelegate, UINavigationControllerDelegate,TZImagePickerControllerDelegate,UIAlertViewDelegate,UITextFieldDelegate>
{
    UIImageView * _headerImg; //用户头像
    UIImageView * _zigeImg; //资格证书
    
    
    UIImageView * _card1Img;
    UIImageView * _card2Img;
    
    NSInteger _sex; //1男 0女
    
    JKCountDownButton * _codeButton;
    
    
    UIImage * _userImage;
    UIImage * _zigeImage;
    UIImage * _card1Image;
    UIImage * _card2Image;
    
    NSString * _imageString1;
    NSString * _imageString2;
    NSString * _imageString3;
    NSString * _imageString4;


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

@implementation NYEditMyInfoViewController

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
    self.navigationItem.title = @"编辑个人信息";
    [self.tableView registerClass:[UITableViewCell class] forCellReuseIdentifier:@"Cell"];
//    [self.tableView registerClass:[NYNameInputCell class] forCellReuseIdentifier:@"INPUTCELLID"];
    [self.tableView registerClass:[NYChuZhenTitleCell class] forCellReuseIdentifier:@"TitleCell"];
    [self.tableView registerClass:[NYRegisterInputPhoneCell class] forCellReuseIdentifier:@"NYRegisterPhoneCellID"];
    [self.tableView registerClass:[NYRegisterChoiceSexCell class] forCellReuseIdentifier:@"NYChoiceSexCellID"];
    [self.tableView registerClass:[NYRegisterShanChangCell class] forCellReuseIdentifier:@"NYRegisterShanChangCellID"];
    [self.tableView registerClass:[NYRegisterChoicePictureCell class] forCellReuseIdentifier:@"ChoicePictureCellID"];
    [self.tableView registerClass:[NYRegisterUploadCardImgCell class] forCellReuseIdentifier:@"NYUploadCardImgCellID"];
    [self.tableView registerClass:[NYRegisterUploadCardCell class] forCellReuseIdentifier:@"NYRegisterCardCEllID"];
    
    
    _sex = [_infoModel.gender integerValue];
    
    _userImage = nil;
    _zigeImage = nil;
    _card1Image = nil;
    _card2Image = nil;

    _imageString1 = _infoModel.photo;
    _imageString2 = _infoModel.physicianLicence;

    NSArray * carImgArr = [_infoModel.identityCard componentsSeparatedByString:@","];

    _imageString3 = carImgArr[0];
    _imageString4 = carImgArr[1];
}

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView
{
    return 8;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    if (section == 0 || section == 3 || section == 4 || section == 5 || section == 6 || section == 7) {
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
    }
    return 0;
}

- (CGFloat)tableView:(UITableView *)tableView heightForHeaderInSection:(NSInteger)section
{
    return 0.0001f;
}

- (CGFloat)tableView:(UITableView *)tableView heightForFooterInSection:(NSInteger)section
{
    if (section == 7) {
        return 100;
    }
    return 8;
}

-(UIView *)tableView:(UITableView *)tableView viewForHeaderInSection:(NSInteger)section {
    return [UIView new];
}

- (UIView *)tableView:(UITableView *)tableView viewForFooterInSection:(NSInteger)section {
    if (section == 7) {
        UIView * bgView = [[UIView alloc] init];
        bgView.backgroundColor = [UIColor clearColor];
        
        UIButton * doneButton = [UIButton buttonWithType:UIButtonTypeCustom];
        [doneButton setTitle:@"提交" forState:UIControlStateNormal];
        [doneButton setBackgroundColor:MAINCOLOR];
        [doneButton setTitleColor:[UIColor whiteColor] forState:UIControlStateNormal];
        [bgView addSubview:doneButton];
        
        doneButton.sd_layout
        .leftSpaceToView(bgView, 30)
        .topSpaceToView(bgView, 30)
        .rightSpaceToView(bgView, 30)
        .heightIs(50);
        doneButton.sd_cornerRadius = @5;
        [doneButton addTarget:self action:@selector(clickDoneButton:) forControlEvents:UIControlEventTouchUpInside];
        
        
        return bgView;
    }
    return [UIView new];
}

#pragma mark - 点击提交按钮

- (void)clickDoneButton:(UIButton *)button
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
    
    if (_imageString1.length == 0) {
        MYALERT(@"请上传本人照片");
        return;
    }
    
    if (_imageString2.length == 0) {
        MYALERT(@"请上传资格证书");
        return;
    }
    
    if (_imageString3.length == 0) {
        MYALERT(@"请上传身份证正面照");
        return;
    }
    
    if (_imageString4.length == 0) {
        MYALERT(@"请上传身份证反面照");
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
    NSDictionary * dict = @{
                            @"adepts":shanChangArray,
                            @"gender":@(_sex),
                            @"hosId":@(danWeiCount),
                            @"hosName":danWeiString,
                            @"identityCard":[NSString stringWithFormat:@"%@,%@",_imageString3,_imageString4],
                            @"name":nameString,
                            @"offId":@(keShiCount),
                            @"offName":keShiString,
                            @"photo":_imageString1,
                            @"physicianLicence":_imageString2,
                            @"positionId":@(zhiWeiCount),
                            @"positionName":zhiWeiString,
                            @"registrationFee":@([priceString doubleValue]),
                            };
    
    [PPHTTPRequest postEditDoctorDetailInfoWithParameters:dict success:^(id response) {
        [SVProgressHUD dismiss];
        if ([response[@"code"] integerValue] == 0) {
            MYALERT(@"修改成功,等待审核");
            if (_ChangDoctorInfoSuccessed) {
                _ChangDoctorInfoSuccessed();
            }
            [weakSelf.navigationController popViewControllerAnimated:YES];
        }else{
            MYALERT(response[@"msg"]);
        }
    } failure:^(NSError *error) {
        [SVProgressHUD dismiss];
        MYALERT(@"修改失败");
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
            
            _nameTF.text = _infoModel.name;
            
            return cell;
        }else if (indexPath.row == 1){
            NYRegisterChoiceSexCell * cell = [tableView dequeueReusableCellWithIdentifier:@"NYChoiceSexCellID"];
            if (_sex == 0) {
                cell.womanBtn.selected = YES;
                cell.manBtn.selected = NO;
            }else if (_sex == 1){
                cell.womanBtn.selected = NO;
                cell.manBtn.selected = YES;
            }
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
            _danWeiTF.text = _infoModel.hosName;
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
            
            _zhiWeiTF.text = _infoModel.positionName;
            
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
            
            _keShiTF.text = _infoModel.offName;
            
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
            _priceTF.keyboardType = UIKeyboardTypeNumberPad;
            
            _priceTF.text = [NSString stringWithFormat:@"%.2f",[_infoModel.registrationFee doubleValue]];
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
            
            NSArray * shanChangArr = _infoModel.adeptEntities;
            if (shanChangArr.count >= 1) {
                NYAdeptEntitiesModel * adM = shanChangArr[0];
                _shanChangTF1.text = adM.name;
            }
            
            return cell;
        }else if (indexPath.row == 1){
            NYRegisterShanChangCell * cell = [tableView dequeueReusableCellWithIdentifier:@"NYRegisterShanChangCellID"];
            cell.infoTextView.placeholder = @"临床症状表现";
            _shanChangTextView1 = cell.infoTextView;
            
            NSArray * shanChangArr = _infoModel.adeptEntities;
            if (shanChangArr.count >= 1) {
                NYAdeptEntitiesModel * adM = shanChangArr[0];
                _shanChangTextView1.text = adM.describe;
            }

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
            
            NSArray * shanChangArr = _infoModel.adeptEntities;
            if (shanChangArr.count >= 2) {
                NYAdeptEntitiesModel * adM = shanChangArr[1];
                _shanChangTF2.text = adM.name;
            }

            return cell;
        }else if (indexPath.row == 1){
            NYRegisterShanChangCell * cell = [tableView dequeueReusableCellWithIdentifier:@"NYRegisterShanChangCellID"];
            cell.infoTextView.placeholder = @"临床症状表现";
            _shanChangTextView2 = cell.infoTextView;
            
            NSArray * shanChangArr = _infoModel.adeptEntities;
            if (shanChangArr.count >= 2) {
                NYAdeptEntitiesModel * adM = shanChangArr[1];
                _shanChangTextView2.text = adM.describe;
            }

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
            
            NSArray * shanChangArr = _infoModel.adeptEntities;
            if (shanChangArr.count >= 3) {
                NYAdeptEntitiesModel * adM = shanChangArr[2];
                _shanChangTF3.text = adM.name;
            }

            return cell;
        }else if (indexPath.row == 1){
            NYRegisterShanChangCell * cell = [tableView dequeueReusableCellWithIdentifier:@"NYRegisterShanChangCellID"];
            cell.infoTextView.placeholder = @"临床症状表现";
            _shanChangTextView3 = cell.infoTextView;
            
            NSArray * shanChangArr = _infoModel.adeptEntities;
            if (shanChangArr.count >= 3) {
                NYAdeptEntitiesModel * adM = shanChangArr[2];
                _shanChangTextView3.text = adM.describe;
            }

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
            
            [_headerImg sd_setImageWithURL:[NSURL URLWithString:_imageString1] placeholderImage:[UIImage imageNamed:@"placeholderImage"]];
            
            [_zigeImg sd_setImageWithURL:[NSURL URLWithString:_imageString2] placeholderImage:[UIImage imageNamed:@"placeholderImage"]];
            
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
            
            [_card1Img sd_setImageWithURL:[NSURL URLWithString:_imageString3] placeholderImage:[UIImage imageNamed:@"placeholderImage"]];
            
            [_card2Img sd_setImageWithURL:[NSURL URLWithString:_imageString4] placeholderImage:[UIImage imageNamed:@"placeholderImage"]];

            cell.clickChoicePictureBtn1 = ^{
                [weakSelf clickHeaderImgWith:_card1Img];
            };
            cell.clickChoicePictureBtn2 = ^{
                [weakSelf clickHeaderImgWith:_card2Img];
            };
            return cell;
        }
    }
    
    UITableViewCell * cell = [tableView dequeueReusableCellWithIdentifier:@"Cell"];
    return cell;
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
    }
    
    return NO;
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
//    self.choiceImg.image = image;
//
//    if (self.choiceImg.tag == 1) {
//        _userImage = image;
//    }else if (self.choiceImg.tag == 2){
//        _zigeImage = image;
//    }else if (self.choiceImg.tag == 3){
//        _card1Image = image;
//    }else if (self.choiceImg.tag == 4){
//        _card2Image = image;
//    }

    [picker dismissViewControllerAnimated:YES completion:nil];
    
    [self uploadHeadImage:image];
}

- (void)imagePickerController:(UIImagePickerController *)picker didFinishPickingMediaWithInfo:(NSDictionary *)info
{
    UIImage *image = [info objectForKey:UIImagePickerControllerOriginalImage];
//    self.choiceImg.image = image;
//    if (self.choiceImg.tag == 1) {
//        _userImage = image;
//    }else if (self.choiceImg.tag == 2){
//        _zigeImage = image;
//    }else if (self.choiceImg.tag == 3){
//        _card1Image = image;
//    }else if (self.choiceImg.tag == 4){
//        _card2Image = image;
//    }

    [picker dismissViewControllerAnimated:YES completion:nil];
    
    [self uploadHeadImage:image];
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

#pragma mark - 上传图片
- (void)uploadHeadImage:(UIImage *)image{
    
    [SVProgressHUD showWithStatus:nil];
    [SVProgressHUD setDefaultMaskType:(SVProgressHUDMaskTypeClear)];
    WEAKSELF
    [PPHTTPRequest postUploadWithparameters:nil images:@[image] success:^(id response) {
        [SVProgressHUD dismiss];
        if ([response[@"code"] integerValue] == 0) {
            self.choiceImg.image = image;
            NSInteger tag = self.choiceImg.tag;
            if (tag == 1) {
                _imageString1 = [response[@"data"][@"urls"] firstObject];
            }else if (tag == 2){
                _imageString2 = [response[@"data"][@"urls"] firstObject];
            }else if (tag == 3){
                _imageString3 = [response[@"data"][@"urls"] firstObject];
            }else if (tag == 4){
                _imageString4 = [response[@"data"][@"urls"] firstObject];
            }
        }else{
            [SVProgressHUD dismiss];
            MYALERT(@"上传失败");
        }
    } failure:^(NSError *error) {
        [SVProgressHUD dismiss];
        MYALERT(@"上传失败");
    }];
}

@end
