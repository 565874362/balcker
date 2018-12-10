//
//  NYHomeHuanZheInfoDetailViewController.m
//  Doctor
//
//  Created by niuyao on 2018/12/5.
//  Copyright © 2018 joymates. All rights reserved.
//

#import "NYHomeHuanZheInfoDetailViewController.h"
#import "NYHomeHuanZheInfoCell.h"
#import "NYHomeListModel.h"
#import "NYHomeHuanZheInfoTimeCell.h"
#import "NYHomeHuanZheInfoImgCell.h"
#import "NYHomeHuanZheInfoVoiceCell.h"
#import "HUPhotoBrowser.h"
#import "NYRegisterShanChangCell.h"
#import "BRPlaceholderTextView.h"
#import "NYHomeHuanZheChoiceCheckCell.h"
#import "NYNeedCheckViewController.h"

@interface NYHomeHuanZheInfoDetailViewController ()<UITableViewDelegate,UITableViewDataSource>
{
    NYHomeListModel * _model;
}
@property (nonatomic, strong) UITableView *tableView;


@end

@implementation NYHomeHuanZheInfoDetailViewController

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
        _tableView.separatorStyle = UITableViewCellSeparatorStyleNone;
        _tableView.tableFooterView = [UIView new];
        _tableView.showsVerticalScrollIndicator = YES;
        _tableView.showsHorizontalScrollIndicator = NO;
    }
    return _tableView;
}

- (void)viewDidLoad {
    [super viewDidLoad];
    self.view.backgroundColor = [UIColor whiteColor];
    self.navigationItem.title = @"患者信息";
    [self.tableView registerClass:[UITableViewCell class] forCellReuseIdentifier:@"Cell"];
    [self.tableView registerClass:[NYHomeHuanZheInfoCell class] forCellReuseIdentifier:@"HomeHuanZheInfoCellID"];
    [self.tableView registerClass:[NYHomeHuanZheInfoTimeCell class] forCellReuseIdentifier:@"HomeHuanZheInfoTimeCellID"];
    [self.tableView registerClass:[NYHomeHuanZheInfoImgCell class] forCellReuseIdentifier:@"HomeHuanZheInfoImgCellID"];
    [self.tableView registerClass:[NYHomeHuanZheInfoVoiceCell class] forCellReuseIdentifier:@"HomeHuanZheInfoVoiceCellID"];
    [self.tableView registerClass:[NYRegisterShanChangCell class] forCellReuseIdentifier:@"NYRegisterShanChangCellID"];
    [self.tableView registerClass:[NYHomeHuanZheChoiceCheckCell class] forCellReuseIdentifier:@"NYHomeHuanZheChoiceCheckCellID"];
    
    _model = [[NYHomeListModel alloc] init];
    _model.state = @"0"; 
    _model.content = @"这几天我家宝宝嘴里起了好多小泡。哭的特别厉害也不敢吃东西，后来去诊所，医生说这是小儿疱疹性口炎，我想问一下！！！这几天我家宝宝嘴里起了好多小泡。哭的特别厉害也不敢吃东西，后来去诊所，医生说这是小儿疱疹性口炎，我想问一下！！！";

    if ([_model.state integerValue] == 0) {
        //抢单按钮
        [self initJieZhenButton];
    }
}

#pragma mark - 初始化抢单按钮
- (void)initJieZhenButton
{
    UIImageView * imageV = [[UIImageView alloc] init];
    imageV.image = [UIImage imageNamed:@"placeholderImage"];
    imageV.clipsToBounds = YES;
    imageV.userInteractionEnabled = YES;
    [self.view addSubview:imageV];
    
    [imageV addGestureRecognizer:[[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(clickJieZhenQiangDan:)]];
    
    imageV.sd_layout
    .centerXEqualToView(self.view)
    .bottomSpaceToView(self.view, 20)
    .widthIs(100)
    .heightEqualToWidth();

    imageV.sd_cornerRadius = @50;
    
    UILabel * label = [[UILabel alloc] init];
    label.font = FONT(20);
    label.textAlignment = NSTextAlignmentCenter;
    label.textColor = [UIColor whiteColor];
    [imageV addSubview:label];
    
    label.sd_layout
    .spaceToSuperView(UIEdgeInsetsMake(0, 0, 0, 0));
    
    label.text = @"接诊";
}

#pragma mark - 点击抢单按钮
- (void)clickJieZhenQiangDan:(UITapGestureRecognizer *)tap
{
    UIImageView * img = (UIImageView *)tap.view;
    img.hidden = YES;
    
    _model.state = @"1";
    [self.tableView reloadData];
    
    [self initTiJiaoButton];
}

#pragma mark - 创建提交按钮
- (void)initTiJiaoButton
{
    UIButton * doneButton = [UIButton buttonWithType:UIButtonTypeCustom];
    [doneButton setTitle:@"提交" forState:UIControlStateNormal];
    [doneButton setBackgroundColor:MAINCOLOR];
    [doneButton setTitleColor:[UIColor whiteColor] forState:UIControlStateNormal];
    [self.view addSubview:doneButton];
    
    doneButton.sd_layout
    .leftSpaceToView(self.view, 0)
    .bottomSpaceToView(self.view, 0)
    .rightSpaceToView(self.view, 0)
    .heightIs(50);
    [doneButton addTarget:self action:@selector(clickDoneButton:) forControlEvents:UIControlEventTouchUpInside];
}

#pragma mark - 点击提交按钮
- (void)clickDoneButton:(UIButton *)button
{
    button.hidden = YES;
    
    _model.state = @"2";
    [self.tableView reloadData];
}

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView
{
    if ([_model.state integerValue] == 0) {
        return 1;
    }else if ([_model.state integerValue] == 1){
        return 4;
    }else if ([_model.state integerValue] == 2){
        return 4;
    }
    return 0;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    if (section == 0) {
        return 4;
    }
    if ([_model.state integerValue] == 1){
        if (section == 1){
            return 1;
        }else if (section == 2){
            return 1;
        }else if (section == 3){
            return 1;
        }
    }else if ([_model.state integerValue] == 2){
        if (section == 1){
            return 1;
        }else if (section == 2){
            return 1;
        }else if (section == 3){
            return 1;
        }
    }
    return 0;
}

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath
{
    if (indexPath.section == 0) {
        if (indexPath.row == 0) {
            return [self.tableView cellHeightForIndexPath:indexPath model:_model keyPath:@"homeListModel" cellClass:[NYHomeHuanZheInfoCell class] contentViewWidth:[UIScreen mainScreen].bounds.size.width];
        }else if (indexPath.row == 1){
            return 50;
        }else if (indexPath.row == 2){
            return (NYScreenW-15*4)*0.33+30;
        }else if (indexPath.row == 3){
            return 50;
        }
    }

    if ([_model.state integerValue] == 1){
        if (indexPath.section == 1 || indexPath.section == 3) {
            return 100;
        }
        return 50;
    }else if ([_model.state integerValue] == 2){
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
    if ([_model.state integerValue] == 0) {
        if (section == 0) {
            return 120;
        }
    }else if ([_model.state integerValue] == 1){
        if (section == 3) {
            return 60;
        }
    }
    return 8;
}

-(UIView *)tableView:(UITableView *)tableView viewForHeaderInSection:(NSInteger)section {
    return [UIView new];
}

- (UIView *)tableView:(UITableView *)tableView viewForFooterInSection:(NSInteger)section {
    return [UIView new];
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    if (indexPath.section == 0) {
        if (indexPath.row == 0) {
            NYHomeHuanZheInfoCell * cell = [tableView dequeueReusableCellWithIdentifier:@"HomeHuanZheInfoCellID"];
            cell.homeListModel = _model;
            return cell;
        }else if (indexPath.row == 1){
            NYHomeHuanZheInfoVoiceCell * cell = [tableView dequeueReusableCellWithIdentifier:@"HomeHuanZheInfoVoiceCellID"];
            return cell;
        }else if (indexPath.row == 2){
            NYHomeHuanZheInfoImgCell * cell = [tableView dequeueReusableCellWithIdentifier:@"HomeHuanZheInfoImgCellID"];
            cell.clickImg = ^(NSInteger index, UIImageView * _Nonnull imageView) {
                [HUPhotoBrowser showFromImageView:imageView withImages:@[imageView.image] atIndex:0];
            };
            return cell;
        }else if (indexPath.row == 3){
            NYHomeHuanZheInfoTimeCell * cell = [tableView dequeueReusableCellWithIdentifier:@"HomeHuanZheInfoTimeCellID"];
            cell.homeListModel = _model;
            return cell;
        }
    }

    if ([_model.state integerValue] == 1){
        if (indexPath.section == 1) {
            NYRegisterShanChangCell * cell = [tableView dequeueReusableCellWithIdentifier:@"NYRegisterShanChangCellID"];
            cell.infoTextView.placeholder = @"初步诊断:";
            return cell;
        }else if (indexPath.section == 2){
            NYHomeHuanZheChoiceCheckCell * cell = [tableView dequeueReusableCellWithIdentifier:@"NYHomeHuanZheChoiceCheckCellID"];
            return cell;
        }else if (indexPath.section == 3){
            NYRegisterShanChangCell * cell = [tableView dequeueReusableCellWithIdentifier:@"NYRegisterShanChangCellID"];
            cell.infoTextView.placeholder = @"温馨医嘱:";
            return cell;
        }
    }else if ([_model.state integerValue] == 2){
        
    }

    UITableViewCell * cell = [tableView dequeueReusableCellWithIdentifier:@"Cell"];
    return cell;
}

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath
{
    [tableView deselectRowAtIndexPath:indexPath animated:YES];
    if (indexPath.section == 2) {
        if (indexPath.row == 0) {
            NYNeedCheckViewController * vc = [[NYNeedCheckViewController alloc] init];
            [self.navigationController pushViewController:vc animated:YES];
        }
    }
}

@end
