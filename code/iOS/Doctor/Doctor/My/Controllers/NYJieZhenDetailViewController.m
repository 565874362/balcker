//
//  NYJieZhenDetailViewController.m
//  Doctor
//
//  Created by niuyao on 2018/12/5.
//  Copyright © 2018 joymates. All rights reserved.
//

#import "NYJieZhenDetailViewController.h"
#import "NYChuZhenTitleCell.h"
#import "NYJieZhenDetailCell.h"

@interface NYJieZhenDetailViewController ()<UITableViewDelegate,UITableViewDataSource>

@property (nonatomic, strong) UITableView *tableView;


@end

@implementation NYJieZhenDetailViewController

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
    self.navigationItem.title = @"接诊详情";
    [self.tableView registerClass:[UITableViewCell class] forCellReuseIdentifier:@"Cell"];
    [self.tableView registerClass:[NYChuZhenTitleCell class] forCellReuseIdentifier:@"TitleCell"];
    [self.tableView registerClass:[NYJieZhenDetailCell class] forCellReuseIdentifier:@"JieZhenDetailCellID"];
    
    
}

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView
{
    return 2;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    if (section == 0) {
        return 6;
    }else if (section == 1){
        return 4;
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
            NYChuZhenTitleCell * cell = [tableView dequeueReusableCellWithIdentifier:@"TitleCell"];
            cell.titleLB.text = @"患者信息";
            return cell;
        }else{
            NYJieZhenDetailCell * cell = [tableView dequeueReusableCellWithIdentifier:@"JieZhenDetailCellID"];
            if (indexPath.row == 1) {
                cell.typeLB.text = @"患者信息";
                cell.infoLB.text = @"赵小强";
            }else if (indexPath.row == 2){
                cell.typeLB.text = @"年龄";
                cell.infoLB.text = @"5岁";
            }else if (indexPath.row == 3){
                cell.typeLB.text = @"性别";
                cell.infoLB.text = @"男";
            }else if (indexPath.row == 4){
                cell.typeLB.text = @"联系电话";
                cell.infoLB.text = @"15596679811";
            }else if (indexPath.row == 5){
                cell.typeLB.text = @"接诊时间";
                cell.infoLB.text = @"2018-10-30 周五 下午";
            }
            return cell;
        }
    }else if (indexPath.section == 1){
        if (indexPath.row == 0) {
            NYChuZhenTitleCell * cell = [tableView dequeueReusableCellWithIdentifier:@"TitleCell"];
            cell.titleLB.text = @"支付信息";
            return cell;
        }else{
            NYJieZhenDetailCell * cell = [tableView dequeueReusableCellWithIdentifier:@"JieZhenDetailCellID"];
            if (indexPath.row == 1) {
                cell.typeLB.text = @"支付方式";
                cell.infoLB.text = @"微信支付";
            }else if (indexPath.row == 2){
                cell.typeLB.text = @"支付金额";
                cell.infoLB.text = @"20.00元";
                cell.infoLB.textColor = COLOR_RED;
            }else if (indexPath.row == 3){
                cell.typeLB.text = @"支付时间";
                cell.infoLB.text = @"2018-10-30 12:15:26";
            }
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
