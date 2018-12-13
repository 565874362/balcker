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
#import "NYDoctorDetialCommentCountCell.h"
#import "NYDoctorCommentCell.h"
#import "NYDoctorModel.h"
#import "NYDoctorDetailModelBtnCell.h"
#import "NYCommentListViewController.h"
#import "NYYuYueJiuZhenViewController.h"

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
    [self.tableView registerClass:[UITableViewCell class] forCellReuseIdentifier:@"Cell"];
    [self.tableView registerClass:[NYDoctorDetailInfoCell class] forCellReuseIdentifier:@"NYDoctorDetailInfoCellID"];
    [self.tableView registerClass:[NYDoctorDetialCommentCountCell class] forCellReuseIdentifier:@"NYDoctorDetialCommentCountCellID"];
    [self.tableView registerClass:[NYDoctorCommentCell class] forCellReuseIdentifier:@"NYDoctorCommentCellID"];
    [self.tableView registerClass:[NYDoctorDetailModelBtnCell class] forCellReuseIdentifier:@"NYDoctorDetailModelBtnCellID"];

    
    [self initBottomUI];
}

- (void)initBottomUI
{
    UIView * bgView = [[UIView alloc] init];
    bgView.backgroundColor = [UIColor whiteColor];
    [self.view addSubview:bgView];
    
    bgView.sd_layout
    .leftSpaceToView(self.view, 0)
    .bottomSpaceToView(self.view, 0)
    .rightSpaceToView(self.view, 0)
    .heightIs(50);
    
    UIButton * doneButton = [UIButton buttonWithType:UIButtonTypeCustom];
    [doneButton setTitle:@"咨询" forState:UIControlStateNormal];
    [doneButton setBackgroundColor:[UIColor whiteColor]];
    [doneButton setTitleColor:MAINCOLOR forState:UIControlStateNormal];
    [bgView addSubview:doneButton];
    
    doneButton.sd_layout
    .leftSpaceToView(bgView, 0)
    .topSpaceToView(bgView, 0)
    .widthRatioToView(bgView, 0.5)
    .bottomSpaceToView(bgView, 0);
    [doneButton addTarget:self action:@selector(clickZiXunButton:) forControlEvents:UIControlEventTouchUpInside];
    
    UIButton * jiuZhenButton = [UIButton buttonWithType:UIButtonTypeCustom];
    [jiuZhenButton setTitle:@"就诊(20.00元)" forState:UIControlStateNormal];
    [jiuZhenButton setBackgroundColor:MAINCOLOR];
    [jiuZhenButton setTitleColor:[UIColor whiteColor] forState:UIControlStateNormal];
    [bgView addSubview:jiuZhenButton];
    
    jiuZhenButton.sd_layout
    .leftSpaceToView(doneButton, 0)
    .topSpaceToView(bgView, 0)
    .rightSpaceToView(bgView, 0)
    .bottomSpaceToView(bgView, 0);
    [jiuZhenButton addTarget:self action:@selector(clickJiuZhenButton:) forControlEvents:UIControlEventTouchUpInside];

}

#pragma mark - 点击咨询按钮
- (void)clickZiXunButton:(UIButton *)button
{
    RCUserInfo * userInfo = [[RCUserInfo alloc] initWithUserId:@"10003" name:@"小病患者" portrait:@"https://ss0.baidu.com/6ONWsjip0QIZ8tyhnq/it/u=2395369128,1437498474&fm=175&app=25&f=JPEG?w=640&h=640&s=DEA02DC5060614EC4915E892030030D3"];
    [[RCIM sharedRCIM] refreshUserInfoCache:userInfo withUserId:@"10003"];
    
    
    RCConversationViewController * vc = [[RCConversationViewController alloc] initWithConversationType:ConversationType_PRIVATE targetId:@"10004"];
    vc.displayUserNameInCell = NO;
    vc.title = @"医生";
    [self.navigationController pushViewController:vc animated:YES];
}

#pragma mark - 点击就诊按钮
- (void)clickJiuZhenButton:(UIButton *)button
{
    NYYuYueJiuZhenViewController * vc = [[NYYuYueJiuZhenViewController alloc] init];
    [self.navigationController pushViewController:vc animated:YES];
}


- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView
{
    return 2;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    if (section == 0) {
        return 1;
    }
    return 5;
}

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath
{
    if (indexPath.section == 0) {
        NYDoctorModel * model = [[NYDoctorModel alloc] init];
        model.shanChang1 = @"咳嗽:过敏性咳嗽（咳嗽变异性哮喘）、过敏性咳嗽（咳嗽变异性哮喘）过敏性咳嗽（咳嗽变异性哮喘）过敏性咳嗽（咳嗽变异性哮喘）过敏性咳嗽（咳嗽变异性哮喘）过敏性咳嗽（咳嗽变异性哮喘）";
        //擅长2
        model.shanChang2 = @"哮喘:过敏性咳嗽（咳嗽变异性哮喘）、过敏性咳嗽（咳嗽变异性哮喘）过敏性咳嗽（咳嗽变异性哮喘）过敏性咳嗽（咳嗽变异性哮喘）过敏性咳嗽（咳嗽变异性哮喘）过敏性咳嗽（咳嗽变异性哮喘）";
        //擅长3
        model.shanChang3 = @"呼吸道感染:过敏性咳嗽（咳嗽变异性哮喘）、过敏性咳嗽（咳嗽变异性哮喘）过敏性咳嗽（咳嗽变异性哮喘）过敏性咳嗽（咳嗽变异性哮喘）过敏性咳嗽（咳嗽变异性哮喘）过敏性咳嗽（咳嗽变异性哮喘）";
        model.yiyuanName = @"西安儿童医院";
        return [self.tableView cellHeightForIndexPath:indexPath model:model keyPath:@"doctorModel" cellClass:[NYDoctorDetailInfoCell class] contentViewWidth:[UIScreen mainScreen].bounds.size.width];
    }else if (indexPath.section == 1){
        if (indexPath.row == 0) {
            return 50;
        }else if (indexPath.row == 4){
            return 100;
        }else{
            NYDoctorModel * model = [[NYDoctorModel alloc] init];
            model.content = @"很感谢刘振环医生的详细解读，真的是吃了一颗定心丸，自己也不用胡思乱想了，很感谢刘振环医生的详细解读，真的是吃了一颗定心丸，自己也不用胡思乱想了很感谢刘振环医生的详细解读，真的是吃了一颗定心丸，自己也不用胡思乱想了";
            return [self.tableView cellHeightForIndexPath:indexPath model:model keyPath:@"commModel" cellClass:[NYDoctorCommentCell class] contentViewWidth:[UIScreen mainScreen].bounds.size.width];
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
    return 80;
}

-(UIView *)tableView:(UITableView *)tableView viewForHeaderInSection:(NSInteger)section {
    return [UIView new];
}

- (UIView *)tableView:(UITableView *)tableView viewForFooterInSection:(NSInteger)section {
    return [UIView new];
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    WEAKSELF
    if (indexPath.section == 0) {
        NYDoctorDetailInfoCell * cell = [tableView dequeueReusableCellWithIdentifier:@"NYDoctorDetailInfoCellID"];
        cell.doctorModel = nil;
        return cell;
    }else if (indexPath.section == 1){
        if (indexPath.row == 0) {
            NYDoctorDetialCommentCountCell * cell = [tableView dequeueReusableCellWithIdentifier:@"NYDoctorDetialCommentCountCellID"];
            return cell;
        }else if (indexPath.row == 4){
            NYDoctorDetailModelBtnCell * cell = [tableView dequeueReusableCellWithIdentifier:@"NYDoctorDetailModelBtnCellID"];
            cell.clickShowModelButton = ^{
                NYCommentListViewController * vc = [[NYCommentListViewController alloc] init];
                [weakSelf.navigationController pushViewController:vc animated:YES];
            };
            return cell;
        }else{
            NYDoctorCommentCell * cell = [tableView dequeueReusableCellWithIdentifier:@"NYDoctorCommentCellID"];
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
