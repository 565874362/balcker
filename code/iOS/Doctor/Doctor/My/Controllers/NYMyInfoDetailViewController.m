//
//  NYMyInfoDetailViewController.m
//  Doctor
//
//  Created by niuyao on 2018/12/5.
//  Copyright © 2018 joymates. All rights reserved.
//

#import "NYMyInfoDetailViewController.h"
#import "NYMyInfoDetailCell.h"
#import "NYMyInfoDetailModel.h"
#import "NYEditMyInfoViewController.h"
#import "NYMyInfoDetailModel.h"

@interface NYMyInfoDetailViewController ()<UITableViewDelegate,UITableViewDataSource>
{
    NYMyInfoDetailModel * _infoModel;
}

@property (nonatomic, strong) UITableView *tableView;



@end

@implementation NYMyInfoDetailViewController

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
        _tableView.separatorStyle = UITableViewCellSeparatorStyleNone;
        _tableView.showsVerticalScrollIndicator = YES;
        _tableView.showsHorizontalScrollIndicator = NO;
    }
    return _tableView;
}

- (void)viewDidLoad {
    [super viewDidLoad];
    self.view.backgroundColor = [UIColor whiteColor];
    self.navigationItem.title = @"我的信息";
    [self.tableView registerClass:[NYMyInfoDetailCell class] forCellReuseIdentifier:@"MyInfoDetailCellID"];
    
//    [self getDoctorDetailInfo]; //获取医生信息
    [self setupRefresh];
    [self.tableView.mj_header beginRefreshing];

}

//集成刷新控件
- (void)setupRefresh{
    // 1.下拉刷新
    MJRefreshNormalHeader * header = [MJRefreshNormalHeader headerWithRefreshingTarget:self refreshingAction:@selector(getDoctorDetailInfo)];
    header.lastUpdatedTimeLabel.hidden = YES;
    self.tableView.mj_header = header;
    
}

#pragma mark - 获取医生信息
- (void)getDoctorDetailInfo
{
    WEAKSELF
    [PPHTTPRequest GetDoctorDetailInfoWithParameters:nil success:^(id response) {
        [self.tableView.mj_header endRefreshing];
        [SVProgressHUD dismiss];
        if ([response[@"code"] integerValue] == 0) {
            weakSelf.navigationItem.rightBarButtonItem = [[UIBarButtonItem alloc] initWithTitle:@"编辑" style:UIBarButtonItemStylePlain target:self action:@selector(clickEditItem:)];
            
            _infoModel = [NYMyInfoDetailModel mj_objectWithKeyValues:response[@"data"][@"info"]];

            [weakSelf.tableView reloadData];
        }else{
            MYALERT(@"获取失败");
        }
    } failure:^(NSError *error) {
        MYALERT(@"获取失败");
        [self.tableView.mj_header endRefreshing];
    }];
}

#pragma mark - 点击编辑
- (void)clickEditItem:(UIBarButtonItem *)item
{
    WEAKSELF
    NYEditMyInfoViewController * vc = [[NYEditMyInfoViewController alloc] init];
    vc.infoModel = _infoModel;
    vc.ChangDoctorInfoSuccessed = ^{
        [weakSelf getDoctorDetailInfo];
    };
    [self.navigationController pushViewController:vc animated:YES];
}

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView
{
    if (_infoModel) {
        return 1;
    }
    return 0;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    return 1;
}

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath
{
    return [self.tableView cellHeightForIndexPath:indexPath model:_infoModel keyPath:@"myInfoModel" cellClass:[NYMyInfoDetailCell class] contentViewWidth:[UIScreen mainScreen].bounds.size.width];
}

- (CGFloat)tableView:(UITableView *)tableView heightForHeaderInSection:(NSInteger)section
{
    return 0.0001f;
}

- (CGFloat)tableView:(UITableView *)tableView heightForFooterInSection:(NSInteger)section
{
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
    NYMyInfoDetailCell * cell = [tableView dequeueReusableCellWithIdentifier:@"MyInfoDetailCellID"];
    cell.myInfoModel = _infoModel;
    return cell;
}

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath
{
    [tableView deselectRowAtIndexPath:indexPath animated:YES];
}

@end
