//
//  NYCommentListViewController.m
//  User
//
//  Created by niuyao on 2018/12/7.
//  Copyright © 2018 joymates. All rights reserved.
//

#import "NYCommentListViewController.h"
#import "NYDoctorDetialCommentCountCell2.h"
#import "NYDoctorCommentCell2.h"
#import "NYDoctorModel.h"

@interface NYCommentListViewController ()<UITableViewDelegate,UITableViewDataSource>

@property (nonatomic, strong) UITableView *tableView;



@end

@implementation NYCommentListViewController

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
    self.navigationItem.title = @"评价";
    [self.tableView registerClass:[UITableViewCell class] forCellReuseIdentifier:@"Cell"];
    [self.tableView registerClass:[NYDoctorDetialCommentCountCell2 class] forCellReuseIdentifier:@"NYDoctorDetialCommentCountCellID"];
    [self.tableView registerClass:[NYDoctorCommentCell2 class] forCellReuseIdentifier:@"NYDoctorCommentCellID"];
    
    
}



- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView
{
    return 1;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    return 10;
}

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath
{
    if (indexPath.section == 0){
        if (indexPath.row == 0) {
            return 50;
        }else{
            NYDoctorModel * model = [[NYDoctorModel alloc] init];
            model.content = @"很感谢刘振环医生的详细解读，真的是吃了一颗定心丸，自己也不用胡思乱想了，很感谢刘振环医生的详细解读，真的是吃了一颗定心丸，自己也不用胡思乱想了很感谢刘振环医生的详细解读，真的是吃了一颗定心丸，自己也不用胡思乱想了";
            return [self.tableView cellHeightForIndexPath:indexPath model:model keyPath:@"commModel" cellClass:[NYDoctorCommentCell2 class] contentViewWidth:[UIScreen mainScreen].bounds.size.width];
        }
    }
    return 0;
}

- (CGFloat)tableView:(UITableView *)tableView heightForHeaderInSection:(NSInteger)section
{
    return 0.0001f;
}

- (CGFloat)tableView:(UITableView *)tableView heightForFooterInSection:(NSInteger)section
{
    if (section == 0) {
        return 0;
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
    if (indexPath.section == 0){
        if (indexPath.row == 0) {
            NYDoctorDetialCommentCountCell2 * cell = [tableView dequeueReusableCellWithIdentifier:@"NYDoctorDetialCommentCountCellID"];
            return cell;
        }else{
            NYDoctorCommentCell2 * cell = [tableView dequeueReusableCellWithIdentifier:@"NYDoctorCommentCellID"];
            cell.commModel = nil;
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
