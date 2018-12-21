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
#import "NYCommentModel.h"

#import "NYLoginViewController.h"
#import "NYBaseNavViewController.h"

@interface NYDoctorInfoDetailViewController ()<UITableViewDelegate,UITableViewDataSource>
{
    NYDoctorModel * _model;
    NSMutableArray * _dataArray;
    
    NSInteger _totalCommentCount;
}
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
    _dataArray = [NSMutableArray array];
    _totalCommentCount = 0;
    
    [self.tableView registerClass:[UITableViewCell class] forCellReuseIdentifier:@"Cell"];
    [self.tableView registerClass:[NYDoctorDetailInfoCell class] forCellReuseIdentifier:@"NYDoctorDetailInfoCellID"];
    [self.tableView registerClass:[NYDoctorDetialCommentCountCell class] forCellReuseIdentifier:@"NYDoctorDetialCommentCountCellID"];
    [self.tableView registerClass:[NYDoctorCommentCell class] forCellReuseIdentifier:@"NYDoctorCommentCellID"];
    [self.tableView registerClass:[NYDoctorDetailModelBtnCell class] forCellReuseIdentifier:@"NYDoctorDetailModelBtnCellID"];

    
//    [self initBottomUI];
    
//    [self loadDoctorDetialInfoData]; //获取医生详情
//
//    [self getCommentData]; //获取评论
    
    [self setupRefresh];
    [self.tableView.mj_header beginRefreshing];

}

//集成刷新控件
- (void)setupRefresh{
    // 1.下拉刷新
    MJRefreshNormalHeader * header = [MJRefreshNormalHeader headerWithRefreshingTarget:self refreshingAction:@selector(loadData)];
    header.lastUpdatedTimeLabel.hidden = YES;
    self.tableView.mj_header = header;
    
}

- (void)loadData
{
    [self loadDoctorDetialInfoData]; //获取医生详情

}

#pragma mark - 获取医生信息详情
- (void)loadDoctorDetialInfoData
{
    WEAKSELF
//    [SVProgressHUD showWithStatus:nil];
//    [SVProgressHUD setDefaultMaskType:(SVProgressHUDMaskTypeClear)];
    
    [PPHTTPRequest GetDoctorDetailInfoWithParameters:self.doctorID success:^(id response) {
        [SVProgressHUD dismiss];
        if ([response[@"code"] integerValue] == 0) {
            _model = [NYDoctorModel mj_objectWithKeyValues:response[@"data"][@"info"]];
            
            [weakSelf getCommentData]; //获取评论

            [self initBottomUI];
            
        }else{
            [self.tableView.mj_header endRefreshing];
            MYALERT(@"请求失败");
        }
        [weakSelf.tableView reloadData];
        
    } failure:^(NSError *error) {
        [self.tableView.mj_header endRefreshing];
        [SVProgressHUD dismiss];
        MYALERT(@"请求失败");
    }];

}

#pragma mark - 获取评论
- (void)getCommentData
{
    WEAKSELF
    NSDictionary * dict = @{@"size":@(3),
                            @"current":@(1),
                            @"doctorId":self.doctorID
                            };

    [PPHTTPRequest postGetDoctorCommentListInfoWithParameters:dict success:^(id response) {
        [self.tableView.mj_header endRefreshing];
        [SVProgressHUD dismiss];
        if ([response[@"code"] integerValue] == 0) {
            
            _totalCommentCount = [response[@"data"][@"page"][@"total"] integerValue];
            
            NSArray * listArr = response[@"data"][@"page"][@"records"];

            [_dataArray removeAllObjects];
            for (NSDictionary *datc in listArr) {
                NYCommentModel *commModel = [NYCommentModel mj_objectWithKeyValues:datc];
                [_dataArray addObject:commModel];
            }
        }
//        else{
//            MYALERT(@"请求失败");
//        }
        [weakSelf.tableView reloadData];
        
    } failure:^(NSError *error) {
        [self.tableView.mj_header endRefreshing];
        [SVProgressHUD dismiss];
//        MYALERT(@"请求失败");
    }];

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
    [jiuZhenButton setTitle:[NSString stringWithFormat:@"就诊(%.2f元)",[_model.registrationFee doubleValue]] forState:UIControlStateNormal];
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
    
    if (!ISLOGIN) {
        NYLoginViewController * loginVC = [[NYLoginViewController alloc] init];
        NYBaseNavViewController * vc = [[NYBaseNavViewController alloc] initWithRootViewController:loginVC];
        [self presentViewController:vc animated:YES completion:^{
        }];
        return;
    }

    
    RCUserInfo * userInfo = [[RCUserInfo alloc] initWithUserId:@"10003" name:@"小病患者" portrait:@"https://ss0.baidu.com/6ONWsjip0QIZ8tyhnq/it/u=2395369128,1437498474&fm=175&app=25&f=JPEG?w=640&h=640&s=DEA02DC5060614EC4915E892030030D3"];
    [[RCIM sharedRCIM] refreshUserInfoCache:userInfo withUserId:@"10003"];
    
    
    RCConversationViewController * vc = [[RCConversationViewController alloc] initWithConversationType:ConversationType_PRIVATE targetId:@"10004"];
    vc.displayUserNameInCell = NO;
    vc.title = @"咨询";
    [self.navigationController pushViewController:vc animated:YES];
}

#pragma mark - 点击就诊按钮
- (void)clickJiuZhenButton:(UIButton *)button
{
    if (!ISLOGIN) {
        NYLoginViewController * loginVC = [[NYLoginViewController alloc] init];
        NYBaseNavViewController * vc = [[NYBaseNavViewController alloc] initWithRootViewController:loginVC];
        [self presentViewController:vc animated:YES completion:^{
        }];
        return;
    }

    
    NYYuYueJiuZhenViewController * vc = [[NYYuYueJiuZhenViewController alloc] init];
    vc.doctorID = self.doctorID;
    vc.priceString = _model.registrationFee;
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
    }else if (section == 1){
        if (_dataArray.count == 0) {
            return 0;
        }else{
            return 2+_dataArray.count;
        }
    }
    return 0;
}

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath
{
    if (indexPath.section == 0) {
        return [self.tableView cellHeightForIndexPath:indexPath model:_model keyPath:@"doctorModel" cellClass:[NYDoctorDetailInfoCell class] contentViewWidth:[UIScreen mainScreen].bounds.size.width];
    }else if (indexPath.section == 1){
        
        if (_dataArray.count != 0) {
            if (indexPath.row == 0) {
                return 50;
            }else if (indexPath.row == (1+_dataArray.count)){
                return 100;
            }else{
                NYCommentModel * model = _dataArray[indexPath.row-1];
                return [self.tableView cellHeightForIndexPath:indexPath model:model keyPath:@"commModel" cellClass:[NYDoctorCommentCell class] contentViewWidth:[UIScreen mainScreen].bounds.size.width];
            }
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
        cell.doctorModel = _model;
        return cell;
    }else if (indexPath.section == 1){
        
        if (_dataArray.count != 0) {
            if (indexPath.row == 0) {
                NYDoctorDetialCommentCountCell * cell = [tableView dequeueReusableCellWithIdentifier:@"NYDoctorDetialCommentCountCellID"];
                cell.titleLB.text = [NSString stringWithFormat:@"评价(%zi)",_totalCommentCount];
                return cell;
            }else if (indexPath.row == (_dataArray.count+1)){
                NYDoctorDetailModelBtnCell * cell = [tableView dequeueReusableCellWithIdentifier:@"NYDoctorDetailModelBtnCellID"];
                cell.clickShowModelButton = ^{
                    NYCommentListViewController * vc = [[NYCommentListViewController alloc] init];
                    vc.doctorID = weakSelf.doctorID;
                    [weakSelf.navigationController pushViewController:vc animated:YES];
                };
                return cell;
            }else{
                NYDoctorCommentCell * cell = [tableView dequeueReusableCellWithIdentifier:@"NYDoctorCommentCellID"];
                cell.commModel = _dataArray[indexPath.row-1];
                return cell;
            }
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
