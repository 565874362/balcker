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

@interface NYDoctorInfoDetailViewController ()<UITableViewDelegate,UITableViewDataSource,RCIMUserInfoDataSource,RCIMConnectionStatusDelegate>
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
    
    [RCIM sharedRCIM].userInfoDataSource = self; //设置代理
    [[RCIM sharedRCIM] setConnectionStatusDelegate:self];

    
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

- (void)viewWillAppear:(BOOL)animated
{
    [super viewWillAppear:animated];
    if (ISLOGIN) {
        if ([UserInfo getRongYunToken].length == 0) {
            [self getToken];
        }else{
            [self connectRongYun];
        }
    }
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
            
            if (ISLOGIN) {
                if ([UserInfo getRongYunToken].length == 0) {
                    [self getToken];
                }else{
                    [self connectRongYun];
                }
            }

            
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
    
    //跳转到聊天界面
    RCConversationViewController * vc = [[RCConversationViewController alloc] initWithConversationType:ConversationType_PRIVATE targetId:_model.accountId];
    vc.displayUserNameInCell = NO;
    vc.title = @"咨询";
    [self.navigationController pushViewController:vc animated:YES];

}

#pragma mark - 连接融云
- (void)connectRongYun{
    
    //连接融云 --- token从app服务器获取
    [[RCIM sharedRCIM] connectWithToken:[UserInfo getRongYunToken] success:^(NSString *userId) {
        NSLog(@"登陆成功。当前登录的用户ID：%@", userId);
        
        NSString * imgStr = nil;
        if ([UserInfo getPic].length != 0 ) {
            imgStr = [UserInfo getPic];
        }
        
        
        RCUserInfo * userInfo = [[RCUserInfo alloc] initWithUserId:userId name:@"患者" portrait:imgStr];
        
        [[RCIM sharedRCIM] setCurrentUserInfo:userInfo];
        [RCIM sharedRCIM].enableMessageRecall = YES;
        [RCIM sharedRCIM].enableMessageAttachUserInfo = YES;
        

        
    } error:^(RCConnectErrorCode status) {
        NSLog(@"登陆的错误码为:%zi", status);
    } tokenIncorrect:^{
        //token过期或者不正确。
        //如果设置了token有效期并且token过期，请重新请求您的服务器获取新的token
        //如果没有设置token有效期却提示token错误，请检查您客户端和服务器的appkey是否匹配，还有检查您获取token的流程。
        NSLog(@"token错误");
        
        [self getToken];
    }];
    
}

#pragma mark - 获取token
- (void)getToken
{
    [PPHTTPRequest GetTokenInfoWithParameters:nil success:^(id response) {
        
        if ([response[@"code"] integerValue] == 0) {
            
            [UserInfo setRongYunToken:response[@"data"][@"token"]];
            
            [self connectRongYun];
            
        }else{
            MYALERT(@"连接融云失败");
        }
    } failure:^(NSError *error) {
        MYALERT(@"连接融云失败");
    }];
}


- (void)getUserInfoWithUserId:(NSString *)userId completion:(void (^)(RCUserInfo *))completion{
    
    [PPHTTPRequest GetUserDetailInfoWithParameters:userId success:^(id response) {
        if ([response[@"code"] integerValue] == 0) {
            NSString * nameString = response[@"data"][@"info"][@"name"];
            NSString * picString = response[@"data"][@"info"][@"photo"];
            
            if ([NSObject isNilOrNull:nameString]) {
                nameString = @"医生";
            }
            
            if ([NSObject isNilOrNull:picString]) {
                picString = @"";
            }
            
#warning 先判断本地是否保存该用户信息，再请求APP服务器获取用户信息
            RCUserInfo * userInfo = [[RCUserInfo alloc] initWithUserId:userId name:nameString portrait:picString];
            
            completion(userInfo);
        }else{
            //            MYALERT(@"请求失败");
        }
    } failure:^(NSError *error) {
        //        MYALERT(@"请求失败");
    }];
}

- (void)onRCIMConnectionStatusChanged:(RCConnectionStatus)status
{
    if(status == ConnectionStatus_KICKED_OFFLINE_BY_OTHER_CLIENT)
    {
        [[NSUserDefaults standardUserDefaults] setBool:NO forKey:@"ISLOGIN"];
        [[NSUserDefaults standardUserDefaults] synchronize];
        
        [UserInfo setToken:nil];
        [UserInfo setRongYunToken:nil];
        
        [self.tabBarController setSelectedIndex:0];
        [self.navigationController popToRootViewControllerAnimated:NO];

        
        //账号在另外设备登录
        NSLog(@"账号在另外设备上面登录");
        UIAlertController *alert=[UIAlertController alertControllerWithTitle:@"账号在另外设备上面登录" message:nil preferredStyle:UIAlertControllerStyleAlert];
        [alert addAction:[UIAlertAction actionWithTitle:@"确定" style:0 handler:^(UIAlertAction * _Nonnull action) {
        }]];
        [self presentViewController:alert animated:YES completion:nil];
    }
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
