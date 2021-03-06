//
//  NYMineViewController.m
//  Doctor
//
//  Created by niuyao on 2018/11/13.
//  Copyright © 2018年 joymates. All rights reserved.
//

#import "NYMineViewController.h"
#import "NYMyTypeCell.h"
#import "NYLoginViewController.h"
#import "NYBaseNavViewController.h"
#import "NYChuZhenTimeListViewController.h"
#import "UINavigationBar+Awesome.h"
#import "NYMyHeaderView.h"
#import "NYHuanZheInfoListViewController.h"
#import "NYMyJieZhenListViewController.h"
#import "NYMyInfoDetailViewController.h"
#import "NYMyInfoDetailModel.h"

#define NAVBAR_CHANGE_POINT 50

@interface NYMineViewController ()<UITableViewDelegate,UITableViewDataSource>
{
    NYMyInfoDetailModel * _infoModel;
}
@property (nonatomic, strong) UITableView *tableView;

@end

@implementation NYMineViewController

- (void)viewWillAppear:(BOOL)animated{
    [super viewWillAppear:animated];
    [self getDoctorDetailInfo]; //获取医生信息

    [self scrollViewDidScroll:self.tableView];
    [self.navigationController.navigationBar setShadowImage:[UIImage new]];
    
}
- (void)viewWillDisappear:(BOOL)animated{
    [super viewWillDisappear:animated];
    [self.navigationController.navigationBar lt_reset];
    
}

- (void)scrollViewDidScroll:(UIScrollView *)scrollView
{
    NSLog(@"%f",scrollView.contentOffset.y);
    if (scrollView.contentOffset.y <= 0) {
        scrollView.contentOffset = CGPointMake(0, 0);
    }
    
    
    UIColor * color = MAINCOLOR;
    CGFloat offsetY = scrollView.contentOffset.y;
    if (offsetY > NAVBAR_CHANGE_POINT) {
        CGFloat alpha = MIN(1, 1 - ((NAVBAR_CHANGE_POINT + 64 - offsetY) / 64));
        [self.navigationController.navigationBar lt_setBackgroundColor:[color colorWithAlphaComponent:alpha]];
    } else {
        [self.navigationController.navigationBar lt_setBackgroundColor:[color colorWithAlphaComponent:0]];
    }
}


- (UITableView *)tableView {
    if (!_tableView) {
        _tableView = [[UITableView alloc] initWithFrame:CGRectZero style:UITableViewStyleGrouped];
        [self.view addSubview:_tableView];
        _tableView.sd_layout
        .topSpaceToView(self.view, -64)
        .rightSpaceToView(self.view, 0)
        .leftSpaceToView(self.view, 0)
        .bottomSpaceToView(self.view, 0);
        _tableView.delegate = self;
        _tableView.dataSource = self;
        _tableView.separatorInset = UIEdgeInsetsMake(0, 0, 0, 0);
        _tableView.tableFooterView = [UIView new];
        _tableView.showsVerticalScrollIndicator = NO;
        _tableView.showsHorizontalScrollIndicator = NO;
    }
    return _tableView;
}


#pragma mark - 获取医生信息
- (void)getDoctorDetailInfo
{
    [PPHTTPRequest GetDoctorDetailInfoWithParameters:nil success:^(id response) {
        [SVProgressHUD dismiss];
        if ([response[@"code"] integerValue] == 0) {
            _infoModel = [NYMyInfoDetailModel mj_objectWithKeyValues:response[@"data"][@"info"]];
            
            [self.tableView reloadData];
        }
    } failure:^(NSError *error) {
    }];
}

- (void)viewDidLoad {
    [super viewDidLoad];
    self.view.backgroundColor = [UIColor whiteColor];
    self.edgesForExtendedLayout = UIRectEdgeNone;
    self.navigationItem.title = @"我的中心";
    
    [self.tableView registerClass:[NYMyTypeCell class] forCellReuseIdentifier:@"Cell"];
    [self.tableView registerClass:[NYMyHeaderView class] forHeaderFooterViewReuseIdentifier:@"HeaderViewID"];
    
    [self.navigationController.navigationBar lt_setBackgroundColor:[UIColor clearColor]];

}

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView
{
    return 3;
}
    
- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    if (section == 0) {
        return 0;
    }else if (section == 1 ){
        return 3;
    }
    return 1;
}
    
- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath
{
    return 50;
}
    
- (CGFloat)tableView:(UITableView *)tableView heightForHeaderInSection:(NSInteger)section
{
    if (section == 0) {
        return (NYScreenW*0.75)<=220?220:(NYScreenW*0.75);
    }
    return 0.0001f;
}
    
- (CGFloat)tableView:(UITableView *)tableView heightForFooterInSection:(NSInteger)section
{
    if (section == 2) {
        return 100;
    }
    return 8;
}
    
-(UIView *)tableView:(UITableView *)tableView viewForHeaderInSection:(NSInteger)section {
    if (section == 0) {
        NYMyHeaderView * headerView = [tableView dequeueReusableHeaderFooterViewWithIdentifier:@"HeaderViewID"];
        if (_infoModel) {
            [headerView.headerImg sd_setImageWithURL:[NSURL URLWithString:_infoModel.photo] placeholderImage:[UIImage imageNamed:@"no_image"]];
            headerView.nameLB.text = _infoModel.name;
            headerView.typeLB.text = [NSString stringWithFormat:@"%@ | %@",_infoModel.positionName,_infoModel.offName];
            headerView.leaveLB.text = @"等级";
        }
        return headerView;
    }
    return [UIView new];
}
    
- (UIView *)tableView:(UITableView *)tableView viewForFooterInSection:(NSInteger)section {
    if (section == 2) {
        UIView * bgView = [[UIView alloc] init];
        bgView.backgroundColor = [UIColor clearColor];
        
        UIButton * outLogBtn = [UIButton buttonWithType:UIButtonTypeCustom];
        [outLogBtn setBackgroundColor:MAINCOLOR];
        [outLogBtn setTitle:@"退出当前账户" forState:UIControlStateNormal];
        [bgView addSubview:outLogBtn];
        
        outLogBtn.sd_layout
        .leftSpaceToView(bgView, 20)
        .topSpaceToView(bgView, 30)
        .rightSpaceToView(bgView, 20)
        .heightIs(50);
        
        outLogBtn.sd_cornerRadius = @5;
        [outLogBtn addTarget:self action:@selector(clickOutLoginBtn:) forControlEvents:UIControlEventTouchUpInside];
        
        return bgView;
    }
    return [UIView new];
}

#pragma mark - 点击退出登录
- (void)clickOutLoginBtn:(UIButton *)button
{
    
    UIAlertController *alert=[UIAlertController alertControllerWithTitle:@"确认要退出登录吗？" message:nil preferredStyle:UIAlertControllerStyleAlert];
    [alert addAction:[UIAlertAction actionWithTitle:@"取消" style:UIAlertActionStyleCancel handler:^(UIAlertAction * _Nonnull action) {
    }]];
    [alert addAction:[UIAlertAction actionWithTitle:@"确定" style:0 handler:^(UIAlertAction * _Nonnull action) {
        
        [[NSUserDefaults standardUserDefaults] setBool:NO forKey:@"ISLOGIN"];
        [[NSUserDefaults standardUserDefaults] synchronize];
        
        [UserInfo setToken:nil];
        [UserInfo setRongYunToken:nil];
        
        [UIApplication sharedApplication].delegate.window.rootViewController = [[NYBaseNavViewController alloc] initWithRootViewController:[[NYLoginViewController alloc] init]];

    }]];
    [self presentViewController:alert animated:YES completion:nil];

}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    NYMyTypeCell * cell = [tableView dequeueReusableCellWithIdentifier:@"Cell"];
    cell.accessoryType = UITableViewCellAccessoryDisclosureIndicator;
    if (indexPath.section == 1) {
        if (indexPath.row == 0) {
            cell.typeLB.text = @"患者信息";
            cell.leftIMG.image = [UIImage imageNamed:@"visits"];
        }else if (indexPath.row == 1) {
            cell.typeLB.text = @"我的接诊";
            cell.leftIMG.image = [UIImage imageNamed:@"accepts"];
        }else if (indexPath.row == 2) {
            cell.typeLB.text = @"接诊时间";
            cell.leftIMG.image = [UIImage imageNamed:@"schedule"];
        }
    }else if (indexPath.section == 2){
        if (indexPath.row == 0) {
            cell.typeLB.text = @"我的信息";
            cell.leftIMG.image = [UIImage imageNamed:@"Personal"];
        }
    }
    return cell;
}
    
- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath
{
    [tableView deselectRowAtIndexPath:indexPath animated:YES];
    if (indexPath.section == 1) {
        if (indexPath.row == 0) { //患者信息
            NYHuanZheInfoListViewController * vc = [[NYHuanZheInfoListViewController alloc] init];
            vc.hidesBottomBarWhenPushed = YES;
            [self.navigationController pushViewController:vc animated:YES];
        }else if (indexPath.row == 1) {//我的接诊
            NYMyJieZhenListViewController * vc = [[NYMyJieZhenListViewController alloc] init];
            vc.hidesBottomBarWhenPushed = YES;
            [self.navigationController pushViewController:vc animated:YES];
        }else if (indexPath.row == 2){ //接诊时间
            NYChuZhenTimeListViewController * vc = [[NYChuZhenTimeListViewController alloc] init];
            vc.hidesBottomBarWhenPushed = YES;
            [self.navigationController pushViewController:vc animated:YES];
        }
    }else if (indexPath.section == 2){
        if (indexPath.row == 0) {
            NYMyInfoDetailViewController * vc = [[NYMyInfoDetailViewController alloc] init];
            vc.hidesBottomBarWhenPushed = YES;
            [self.navigationController pushViewController:vc animated:YES];
        }
    }
    
}

@end
