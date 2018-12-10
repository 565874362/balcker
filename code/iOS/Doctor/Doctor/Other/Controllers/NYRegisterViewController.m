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
    
}
@property (nonatomic, strong) UIImageView *choiceImg;

@property (nonatomic, strong) UITableView *tableView;
@property (nonatomic, strong) UIImagePickerController *imagePickerVc;

@property (nonatomic,strong) HHTextField * keShiTF;
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
    [self.tableView registerClass:[NYNameInputCell class] forCellReuseIdentifier:@"INPUTCELLID"];
    [self.tableView registerClass:[NYChuZhenTitleCell class] forCellReuseIdentifier:@"TitleCell"];
    [self.tableView registerClass:[NYRegisterInputPhoneCell class] forCellReuseIdentifier:@"NYRegisterPhoneCellID"];
    [self.tableView registerClass:[NYRegisterChoiceSexCell class] forCellReuseIdentifier:@"NYChoiceSexCellID"];
    [self.tableView registerClass:[NYRegisterShanChangCell class] forCellReuseIdentifier:@"NYRegisterShanChangCellID"];
    [self.tableView registerClass:[NYRegisterChoicePictureCell class] forCellReuseIdentifier:@"ChoicePictureCellID"];
    [self.tableView registerClass:[NYRegisterUploadCardImgCell class] forCellReuseIdentifier:@"NYUploadCardImgCellID"];
    [self.tableView registerClass:[NYRegisterUploadCardCell class] forCellReuseIdentifier:@"NYRegisterCardCEllID"];
}

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView
{
    return 10;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    if (section == 0 || section == 3 || section == 4 || section == 5 || section == 6 || section == 7 || section == 8 || section == 9) {
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
            return 200;
        }
    }else if (indexPath.section == 7){
        if (indexPath.row == 0) {
            return 50;
        }else if (indexPath.row == 1){
            return 260;
        }
    }else if (indexPath.section == 8){
        if (indexPath.row == 0) {
            return 50;
        }else if (indexPath.row == 1){
            return 500;
        }
    }else if (indexPath.section == 9){
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
    if (section == 9) {
        return 200;
    }
    return 8;
}

-(UIView *)tableView:(UITableView *)tableView viewForHeaderInSection:(NSInteger)section {
    return [UIView new];
}

- (UIView *)tableView:(UITableView *)tableView viewForFooterInSection:(NSInteger)section {
    if (section == 9) {
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
    
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    WEAKSELF
    if (indexPath.section == 0) { //姓名
        if (indexPath.row == 0) {
            NYNameInputCell * cell = [tableView dequeueReusableCellWithIdentifier:@"INPUTCELLID"];
            cell.typeLB.text = @"姓名";
            cell.inputTF.placeholder = @"请输入姓名";
            return cell;
        }else if (indexPath.row == 1){
            NYRegisterChoiceSexCell * cell = [tableView dequeueReusableCellWithIdentifier:@"NYChoiceSexCellID"];
            cell.clickManButton = ^(RankButton * _Nonnull setBtn) {
                if (!setBtn.isSelected) {
                    setBtn.selected = !setBtn.selected;
                }
            };
            cell.clickWomanButton = ^(RankButton * _Nonnull setBtn) {
                if (!setBtn.isSelected) {
                    setBtn.selected = !setBtn.selected;
                }
            };
            return cell;
        }
    }else if (indexPath.section == 1){//单位
        if (indexPath.row == 0) {
            NYNameInputCell * cell = [tableView dequeueReusableCellWithIdentifier:@"INPUTCELLID"];
            cell.typeLB.text = @"单位";
            cell.inputTF.placeholder = @"请输入工作单位";
            return cell;
        }else if (indexPath.row == 1){
            NYNameInputCell * cell = [tableView dequeueReusableCellWithIdentifier:@"INPUTCELLID"];
            cell.typeLB.text = @"职位";
            cell.inputTF.placeholder = @"请输入职位";
            return cell;
        }else if (indexPath.row == 2){
            NYNameInputCell * cell = [tableView dequeueReusableCellWithIdentifier:@"INPUTCELLID"];
            cell.typeLB.text = @"科室";
            cell.inputTF.placeholder = @"选择科室";
            _keShiTF = cell.inputTF;
            _keShiTF.delegate = self;
            return cell;
        }
    }else if (indexPath.section == 2){//预约费
        if (indexPath.row == 0) {
            NYNameInputCell * cell = [tableView dequeueReusableCellWithIdentifier:@"INPUTCELLID"];
            cell.typeLB.text = @"预约费";
            cell.inputTF.placeholder = @"请输入预约费金额";
            return cell;
        }
    }else if (indexPath.section == 3){//擅长1
        if (indexPath.row == 0) {
            NYNameInputCell * cell = [tableView dequeueReusableCellWithIdentifier:@"INPUTCELLID"];
            cell.typeLB.text = @"擅长1";
            cell.inputTF.placeholder = @"请输入名称";
            return cell;
        }else if (indexPath.row == 1){
            NYRegisterShanChangCell * cell = [tableView dequeueReusableCellWithIdentifier:@"NYRegisterShanChangCellID"];
            cell.infoTextView.placeholder = @"临床症状表现";
            return cell;
        }
    }else if (indexPath.section == 4){ //擅长2
        if (indexPath.row == 0) {
            NYNameInputCell * cell = [tableView dequeueReusableCellWithIdentifier:@"INPUTCELLID"];
            cell.typeLB.text = @"擅长2";
            cell.inputTF.placeholder = @"请输入名称";
            return cell;
        }else if (indexPath.row == 1){
            NYRegisterShanChangCell * cell = [tableView dequeueReusableCellWithIdentifier:@"NYRegisterShanChangCellID"];
            cell.infoTextView.placeholder = @"临床症状表现";
            return cell;
        }
    }else if (indexPath.section == 5){ //擅长3
        if (indexPath.row == 0) {
            NYNameInputCell * cell = [tableView dequeueReusableCellWithIdentifier:@"INPUTCELLID"];
            cell.typeLB.text = @"擅长3";
            cell.inputTF.placeholder = @"请输入名称";
            return cell;
        }else if (indexPath.row == 1){
            NYRegisterShanChangCell * cell = [tableView dequeueReusableCellWithIdentifier:@"NYRegisterShanChangCellID"];
            cell.infoTextView.placeholder = @"临床症状表现";
            return cell;
        }
    }else if (indexPath.section == 6){ //上传本人照片
        if (indexPath.row == 0) {
            NYChuZhenTitleCell * cell = [tableView dequeueReusableCellWithIdentifier:@"TitleCell"];
            cell.titleLB.text = @"上传本人照片";
            return cell;
        }else if (indexPath.row == 1){
            NYRegisterChoicePictureCell * cell = [tableView dequeueReusableCellWithIdentifier:@"ChoicePictureCellID"];
            _headerImg = cell.pictureIMG;
            cell.clickChoicePictureImg = ^{
                [weakSelf clickHeaderImgWith:_headerImg];
            };
            return cell;
        }
    }else if (indexPath.section == 7){ //上传医师资格证
        if (indexPath.row == 0) {
            NYChuZhenTitleCell * cell = [tableView dequeueReusableCellWithIdentifier:@"TitleCell"];
            cell.titleLB.text = @"上传医师资格证";
            return cell;
        }else if (indexPath.row == 1){
            NYRegisterUploadCardImgCell * cell = [tableView dequeueReusableCellWithIdentifier:@"NYUploadCardImgCellID"];
            _zigeImg = cell.pictureIMG;
            cell.clickChoicePictureBtn = ^{
                [weakSelf clickHeaderImgWith:_zigeImg];
            };
            return cell;
        }
    }else if (indexPath.section == 8){ //上传身份证图片
        if (indexPath.row == 0) {
            NYChuZhenTitleCell * cell = [tableView dequeueReusableCellWithIdentifier:@"TitleCell"];
            cell.titleLB.text = @"上传身份证图片";
            return cell;
        }else if (indexPath.row == 1){
            NYRegisterUploadCardCell * cell = [tableView dequeueReusableCellWithIdentifier:@"NYRegisterCardCEllID"];
            _card1Img = cell.pictureIMG1;
            _card2Img = cell.pictureIMG2;
            cell.clickChoicePictureBtn1 = ^{
                [weakSelf clickHeaderImgWith:_card1Img];
            };
            cell.clickChoicePictureBtn2 = ^{
                [weakSelf clickHeaderImgWith:_card2Img];
            };
            return cell;
        }
    }else if (indexPath.section == 9){ //手机号&验证码
        if (indexPath.row == 0) {
            NYRegisterInputPhoneCell * cell = [tableView dequeueReusableCellWithIdentifier:@"NYRegisterPhoneCellID"];
            cell.clickGetCodeButton = ^{
                
            };
            return cell;
        }else if (indexPath.row == 1){
            NYNameInputCell * cell = [tableView dequeueReusableCellWithIdentifier:@"INPUTCELLID"];
            cell.typeLB.text = @"验证码";
            cell.inputTF.placeholder = @"请输入短信验证码";
            return cell;
        }
    }
    
    UITableViewCell * cell = [tableView dequeueReusableCellWithIdentifier:@"Cell"];
    return cell;
}

- (BOOL)textFieldShouldBeginEditing:(UITextField *)textField
{
    WEAKSELF
    // 自定义单列字符串
    NSArray *dataSource = @[@"儿科", @"妇科", @"产科", @"精神科", @"神经科", @"内分泌科"];
    [BRStringPickerView showStringPickerWithTitle:@"科室选择" dataSource:dataSource defaultSelValue:_keShiTF.text isAutoSelect:YES themeColor:nil resultBlock:^(id selectValue) {
        weakSelf.keShiTF.text = selectValue;
    } cancelBlock:^{
        NSLog(@"点击了背景视图或取消按钮");
    }];
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
    self.choiceImg.image = image;
    [picker dismissViewControllerAnimated:YES completion:nil];
    
//    [self uploadHeadImage:image];
}

- (void)imagePickerController:(UIImagePickerController *)picker didFinishPickingMediaWithInfo:(NSDictionary *)info
{
    UIImage *image = [info objectForKey:UIImagePickerControllerOriginalImage];
    self.choiceImg.image = image;
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
