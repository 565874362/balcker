//
//  NYDoctorListViewController.m
//  User
//
//  Created by niuyao on 2018/11/13.
//  Copyright © 2018年 joymates. All rights reserved.
//

#import "NYDoctorListViewController.h"
#import "NYDoctorListCell.h"
#import "NYDoctorModel.h"
#import "NYDoctorInfoDetailViewController.h"

@interface NYDoctorListViewController ()<UITableViewDelegate,UITableViewDataSource>
    
@property (nonatomic, strong) UITableView *tableView;


@end

@implementation NYDoctorListViewController

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
    self.navigationItem.title = @"医生列表";
    [self.tableView registerClass:[NYDoctorListCell class] forCellReuseIdentifier:@"Cell"];
    
}
    
- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView
{
    return 10;
}
    
- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    return 1;
}
    
- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath
{
    NYDoctorModel * model = [[NYDoctorModel alloc] init];
    model.content = @"擅长：孕前检查、不孕不育、反复流产、孕前检查、不孕不育、反复流产、孕前检查、不孕不育、反复流产、孕前检查、不孕不育、反复流产、孕前检查、不孕不育、反复流产、孕前检查、不孕不育、反复流产";
    return [self.tableView cellHeightForIndexPath:indexPath model:model keyPath:@"doctorModel" cellClass:[NYDoctorListCell class] contentViewWidth:[UIScreen mainScreen].bounds.size.width];
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
    NYDoctorListCell * cell = [tableView dequeueReusableCellWithIdentifier:@"Cell"];
    cell.doctorModel = nil;
    return cell;
}
    
- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath
{
    [tableView deselectRowAtIndexPath:indexPath animated:YES];
    NYDoctorInfoDetailViewController * vc = [[NYDoctorInfoDetailViewController alloc] init];
    vc.hidesBottomBarWhenPushed = YES;
    [self.navigationController pushViewController:vc animated:YES];
}

@end
