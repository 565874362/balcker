//
//  NYMineViewController.m
//  Doctor
//
//  Created by niuyao on 2018/11/13.
//  Copyright © 2018年 joymates. All rights reserved.
//

#import "NYMineViewController.h"

@interface NYMineViewController ()<UITableViewDelegate,UITableViewDataSource>
    
@property (nonatomic, strong) UITableView *tableView;


@end

@implementation NYMineViewController

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
        _tableView.showsVerticalScrollIndicator = NO;
        _tableView.showsHorizontalScrollIndicator = NO;
    }
    return _tableView;
}
    
- (void)viewDidLoad {
    [super viewDidLoad];
    self.view.backgroundColor = [UIColor whiteColor];
    self.navigationItem.title = @"我的中心";
    
    [self.tableView registerClass:[UITableViewCell class] forCellReuseIdentifier:@"Cell"];
    
}
    
- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView
{
    return 3;
}
    
- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    if (section == 0) {
        return 1;
    }else if (section == 1 ){
        return 2;
    }
    return 1;
}
    
- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath
    {
        if (indexPath.section == 0) {
            return 200;
        }
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
        if (indexPath.section == 0 ) {
            UITableViewCell * cell = [tableView dequeueReusableCellWithIdentifier:@"Cell"];
            return cell;
        }
        UITableViewCell * cell = [tableView dequeueReusableCellWithIdentifier:@"Cell"];
        cell.accessoryType = UITableViewCellAccessoryDisclosureIndicator;
        if (indexPath.section == 1) {
            if (indexPath.row == 0) {
                cell.textLabel.text = @"我的咨询";
            }else if (indexPath.row == 1) {
                cell.textLabel.text = @"我的预约";
            }
        }else if (indexPath.section == 2){
            if (indexPath.row == 0) {
                cell.textLabel.text = @"修改密码";
            }
        }
        return cell;
    }
    
- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath
{
    [tableView deselectRowAtIndexPath:indexPath animated:YES];
}

@end
