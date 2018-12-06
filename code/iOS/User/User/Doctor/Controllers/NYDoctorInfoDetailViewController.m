//
//  NYDoctorInfoDetailViewController.m
//  User
//
//  Created by niuyao on 2018/12/6.
//  Copyright © 2018 joymates. All rights reserved.
//

#import "NYDoctorInfoDetailViewController.h"
#import "NYDoctorDetailInfoCell.h"
#import "NYDoctorModel.h"

@interface NYDoctorInfoDetailViewController ()<UITableViewDelegate,UITableViewDataSource>

@property (nonatomic, strong) UITableView *tableView;


@end

@implementation NYDoctorInfoDetailViewController

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
    self.navigationItem.title = @"医生详情";
    [self.tableView registerClass:[NYDoctorDetailInfoCell class] forCellReuseIdentifier:@"NYDoctorDetailInfoCellID"];
    
    
}


- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView
{
    return 1;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    return 1;
}

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath
{
    NYDoctorModel * model = [[NYDoctorModel alloc] init];
    model.shanChang1 = @"咳嗽:过敏性咳嗽（咳嗽变异性哮喘）、过敏性咳嗽（咳嗽变异性哮喘）过敏性咳嗽（咳嗽变异性哮喘）过敏性咳嗽（咳嗽变异性哮喘）过敏性咳嗽（咳嗽变异性哮喘）过敏性咳嗽（咳嗽变异性哮喘）";
    //擅长2
    model.shanChang2 = @"哮喘:过敏性咳嗽（咳嗽变异性哮喘）、过敏性咳嗽（咳嗽变异性哮喘）过敏性咳嗽（咳嗽变异性哮喘）过敏性咳嗽（咳嗽变异性哮喘）过敏性咳嗽（咳嗽变异性哮喘）过敏性咳嗽（咳嗽变异性哮喘）";
    //擅长3
    model.shanChang3 = @"呼吸道感染:过敏性咳嗽（咳嗽变异性哮喘）、过敏性咳嗽（咳嗽变异性哮喘）过敏性咳嗽（咳嗽变异性哮喘）过敏性咳嗽（咳嗽变异性哮喘）过敏性咳嗽（咳嗽变异性哮喘）过敏性咳嗽（咳嗽变异性哮喘）";
    model.yiyuanName = @"西安儿童医院";
    return [self.tableView cellHeightForIndexPath:indexPath model:model keyPath:@"doctorModel" cellClass:[NYDoctorDetailInfoCell class] contentViewWidth:[UIScreen mainScreen].bounds.size.width];
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
    NYDoctorDetailInfoCell * cell = [tableView dequeueReusableCellWithIdentifier:@"NYDoctorDetailInfoCellID"];
    cell.doctorModel = nil;
    return cell;
}

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath
{
    [tableView deselectRowAtIndexPath:indexPath animated:YES];
}

@end
