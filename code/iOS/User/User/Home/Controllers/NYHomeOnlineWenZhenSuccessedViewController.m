//
//  NYHomeOnlineWenZhenSuccessedViewController.m
//  User
//
//  Created by niuyao on 2018/12/6.
//  Copyright © 2018 joymates. All rights reserved.
//

#import "NYHomeOnlineWenZhenSuccessedViewController.h"
#import "NYDoctorListCell.h"
#import "NYDoctorModel.h"
#import "NYTiShiSuccessedCell.h"
#import "NYDoctorInfoDetailViewController.h"
#import "NYMyWenZhenListViewController.h"

@interface NYHomeOnlineWenZhenSuccessedViewController ()<UITableViewDelegate,UITableViewDataSource>
{
    NSMutableArray * _dataArray;
}
@property (nonatomic, strong) UITableView *tableView;


@end

@implementation NYHomeOnlineWenZhenSuccessedViewController

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
    self.navigationItem.title = @"提示";
    [self.tableView registerClass:[NYDoctorListCell class] forCellReuseIdentifier:@"Cell"];
    [self.tableView registerClass:[NYTiShiSuccessedCell class] forCellReuseIdentifier:@"NYTiShiSuccessedCellID"];
    
    
    [self initBottomUI];
    
}


#pragma mark - 初始化底部
- (void)initBottomUI
{
    UIView * bgView = [[UIView alloc] init];
    bgView.backgroundColor = [UIColor whiteColor];
    [self.view addSubview:bgView];
    
    bgView.sd_layout
    .leftSpaceToView(self.view, 0)
    .bottomSpaceToView(self.view, 0)
    .rightSpaceToView(self.view, 0)
    .heightIs(100);
    
    UIButton * doneButton = [UIButton buttonWithType:UIButtonTypeCustom];
    [doneButton setTitle:@"返回" forState:UIControlStateNormal];
    [doneButton setBackgroundColor:MAINCOLOR];
    [doneButton setTitleColor:[UIColor whiteColor] forState:UIControlStateNormal];
    [bgView addSubview:doneButton];
    
    doneButton.sd_layout
    .leftSpaceToView(bgView, 15)
    .topSpaceToView(bgView, 20)
    .rightSpaceToView(bgView, 15)
    .heightIs(50);
    doneButton.sd_cornerRadius = @5;
    [doneButton addTarget:self action:@selector(clickBackButton:) forControlEvents:UIControlEventTouchUpInside];
    
}

#pragma mark - 点击返回按钮

- (void)clickBackButton:(UIButton *)button
{
    NYMyWenZhenListViewController * vc = [[NYMyWenZhenListViewController alloc] init];
    vc.pushType = 1;
    [self.navigationController pushViewController:vc animated:YES];
}


- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView
{
    return 1;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    return _dataArray.count+1;
}

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath
{
    if (indexPath.row == 0) {
        return 220;
    }else{
        NYDoctorModel * model = _dataArray[indexPath.row-1];
        return [self.tableView cellHeightForIndexPath:indexPath model:model keyPath:@"doctorModel" cellClass:[NYDoctorListCell class] contentViewWidth:[UIScreen mainScreen].bounds.size.width];
    }
    return 0;
}

- (CGFloat)tableView:(UITableView *)tableView heightForHeaderInSection:(NSInteger)section
{
    return 0.0001f;
}

- (CGFloat)tableView:(UITableView *)tableView heightForFooterInSection:(NSInteger)section
{
    return 100;
}

-(UIView *)tableView:(UITableView *)tableView viewForHeaderInSection:(NSInteger)section {
    return [UIView new];
}

- (UIView *)tableView:(UITableView *)tableView viewForFooterInSection:(NSInteger)section {
    return [UIView new];
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    if (indexPath.row == 0) {
        NYTiShiSuccessedCell * cell = [tableView dequeueReusableCellWithIdentifier:@"NYTiShiSuccessedCellID"];
        return cell;
    }else{
        NYDoctorListCell * cell = [tableView dequeueReusableCellWithIdentifier:@"Cell"];
        cell.doctorModel = _dataArray[indexPath.row-1];
        return cell;
    }
    return nil;
}

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath
{
    [tableView deselectRowAtIndexPath:indexPath animated:YES];
    if (indexPath.row > 0) {
        NYDoctorInfoDetailViewController * vc = [[NYDoctorInfoDetailViewController alloc] init];
        NYDoctorModel * model = _dataArray[indexPath.row-1];
        vc.doctorID = model.id;
        [self.navigationController pushViewController:vc animated:YES];
    }
}

@end
