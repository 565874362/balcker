//
//  NYYuYueJiuZhenViewController.m
//  User
//
//  Created by niuyao on 2018/12/7.
//  Copyright © 2018 joymates. All rights reserved.
//

#import "NYYuYueJiuZhenViewController.h"
#import "NYNameInputCell.h"
#import "NYRegisterChoiceSexCell.h"
#import "RankButton.h"

@interface NYYuYueJiuZhenViewController ()<UITableViewDelegate,UITableViewDataSource>

@property (nonatomic, strong) UITableView *tableView;



@end

@implementation NYYuYueJiuZhenViewController

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
    self.navigationItem.title = @"预约就诊";
    [self.tableView registerClass:[UITableViewCell class] forCellReuseIdentifier:@"Cell"];
    [self.tableView registerClass:[NYNameInputCell class] forCellReuseIdentifier:@"INPUTCELLID"];
    [self.tableView registerClass:[NYRegisterChoiceSexCell class] forCellReuseIdentifier:@"NYChoiceSexCellID"];

}

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView
{
    return 3;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    if (section == 0) {
        return 4;
    }else if (section == 1){
        return 1;
    }else if (section == 2){
        return 1;
    }
    return 0;
}

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath
{
    return 50;
}

- (CGFloat)tableView:(UITableView *)tableView heightForHeaderInSection:(NSInteger)section
{
    return 0.0001f;
}

- (CGFloat)tableView:(UITableView *)tableView heightForFooterInSection:(NSInteger)section
{
    if (section == 2) {
        return 200;
    }
    return 8;
}

-(UIView *)tableView:(UITableView *)tableView viewForHeaderInSection:(NSInteger)section {
    return [UIView new];
}

- (UIView *)tableView:(UITableView *)tableView viewForFooterInSection:(NSInteger)section {
    if (section == 2) {
        UIView * bgView = [[UIView alloc] init];
        bgView.backgroundColor = [UIColor clearColor];
        
        UIButton * doneButton = [UIButton buttonWithType:UIButtonTypeCustom];
        [doneButton setTitle:@"提交" forState:UIControlStateNormal];
        [doneButton setBackgroundColor:MAINCOLOR];
        [doneButton setTitleColor:[UIColor whiteColor] forState:UIControlStateNormal];
        [bgView addSubview:doneButton];
        
        doneButton.sd_layout
        .leftSpaceToView(bgView, 30)
        .topSpaceToView(bgView, 50)
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
    
}


- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    if (indexPath.section == 0) {
        if (indexPath.row == 0) {
            NYNameInputCell * cell = [tableView dequeueReusableCellWithIdentifier:@"INPUTCELLID"];
            cell.typeLB.text = @"患者姓名";
            cell.inputTF.placeholder = @"请输入患者姓名";
            return cell;
        }else if (indexPath.row == 1){
            NYNameInputCell * cell = [tableView dequeueReusableCellWithIdentifier:@"INPUTCELLID"];
            cell.typeLB.text = @"年龄";
            cell.inputTF.placeholder = @"请输入实际年龄";
            cell.inputTF.keyboardType = UIKeyboardTypePhonePad;
            return cell;
        }else if (indexPath.row == 2){
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
        }else if (indexPath.row == 3){
            NYNameInputCell * cell = [tableView dequeueReusableCellWithIdentifier:@"INPUTCELLID"];
            cell.typeLB.text = @"联系电话";
            cell.inputTF.placeholder = @"请输入联系电话";
            cell.inputTF.keyboardType = UIKeyboardTypePhonePad;
            return cell;
        }
    }else if (indexPath.section == 1){
        if (indexPath.row == 0) {
            NYNameInputCell * cell = [tableView dequeueReusableCellWithIdentifier:@"INPUTCELLID"];
            cell.typeLB.text = @"就诊时间";
            cell.inputTF.placeholder = @"选择就诊时间";
            return cell;
        }
    }else if (indexPath.section == 2){
        if (indexPath.row == 0) {
            NYNameInputCell * cell = [tableView dequeueReusableCellWithIdentifier:@"INPUTCELLID"];
            cell.typeLB.text = @"预约费";
            cell.inputTF.text = @"20.00";
            cell.inputTF.enabled = NO;
            return cell;
        }
    }
    
    UITableViewCell * cell = [tableView dequeueReusableCellWithIdentifier:@"Cell"];
    return cell;
}

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath
{
    [tableView deselectRowAtIndexPath:indexPath animated:YES];
}

@end
