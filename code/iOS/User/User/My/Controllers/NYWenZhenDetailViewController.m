//
//  NYWenZhenDetailViewController.m
//  User
//
//  Created by niuyao on 2018/12/7.
//  Copyright © 2018 joymates. All rights reserved.
//

#import "NYWenZhenDetailViewController.h"
#import "NYMyWenZhenModel.h"
#import "NYHomeHuanZheInfoCell.h"
#import "NYHomeHuanZheInfoTimeCell.h"
#import "NYHomeHuanZheInfoImgCell.h"
#import "NYHomeHuanZheInfoVoiceCell.h"
#import "HUPhotoBrowser.h"
#import "D3RecordButton.h"

#import "NYWenZhenDetailTopCell.h"
#import "NYAlreadyChoiceCheckCell.h"
#import "NYAlreadyChoiceCheckTitleCell.h"
#import "NYAlreadyChoiceCheckAllPriceCell.h"
#import "NYWenZhenDetailYiZhuCell.h"
#import "NYYuYueJiuZhenViewController.h"

#import "NYHuanZheDetailModel.h"
#import "NYHuanZheDetialCheckModel.h"
#import "NYDoctorModel.h"

@interface NYWenZhenDetailViewController ()<UITableViewDelegate,UITableViewDataSource,AVAudioPlayerDelegate,RCIMUserInfoDataSource,RCIMConnectionStatusDelegate>
{
    NYHuanZheDetailModel * _model;
    AVAudioPlayer * _play;

}
@property (nonatomic, strong) UITableView *tableView;


@end

@implementation NYWenZhenDetailViewController

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
        _tableView.separatorStyle = UITableViewCellSeparatorStyleNone;
        _tableView.tableFooterView = [UIView new];
        _tableView.showsVerticalScrollIndicator = YES;
        _tableView.showsHorizontalScrollIndicator = NO;
    }
    return _tableView;
}

- (void)viewDidLoad {
    [super viewDidLoad];
    self.view.backgroundColor = [UIColor whiteColor];
    self.navigationItem.title = @"问诊详情";
    [self.tableView registerClass:[UITableViewCell class] forCellReuseIdentifier:@"Cell"];
    [self.tableView registerClass:[NYHomeHuanZheInfoCell class] forCellReuseIdentifier:@"HomeHuanZheInfoCellID"];
    [self.tableView registerClass:[NYHomeHuanZheInfoTimeCell class] forCellReuseIdentifier:@"HomeHuanZheInfoTimeCellID"];
    [self.tableView registerClass:[NYHomeHuanZheInfoImgCell class] forCellReuseIdentifier:@"HomeHuanZheInfoImgCellID"];
    [self.tableView registerClass:[NYHomeHuanZheInfoVoiceCell class] forCellReuseIdentifier:@"HomeHuanZheInfoVoiceCellID"];
    [self.tableView registerClass:[NYWenZhenDetailTopCell class] forCellReuseIdentifier:@"NYWenZhenDetailTopCellID"];
    [self.tableView registerClass:[NYAlreadyChoiceCheckCell class] forCellReuseIdentifier:@"NYAlreadyChoiceCheckCellID"];
    [self.tableView registerClass:[NYAlreadyChoiceCheckTitleCell class] forCellReuseIdentifier:@"NYAlreadyChoiceCheckTitleCellID"];
    [self.tableView registerClass:[NYAlreadyChoiceCheckAllPriceCell class] forCellReuseIdentifier:@"NYAlreadyChoiceCheckAllPriceCellID"];
    [self.tableView registerClass:[NYWenZhenDetailYiZhuCell class] forCellReuseIdentifier:@"NYWenZhenDetailYiZhuCellID"];

    [self setupRefresh];
    [self.tableView.mj_header beginRefreshing];

    [RCIM sharedRCIM].userInfoDataSource = self; //设置代理
    [[RCIM sharedRCIM] setConnectionStatusDelegate:self];

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
    WEAKSELF
//    [SVProgressHUD showWithStatus:nil];
//    [SVProgressHUD setDefaultMaskType:(SVProgressHUDMaskTypeClear)];
    
    [PPHTTPRequest GetWenZhenDetailInfoWithParameters:self.wenZhenID success:^(id response) {
        [SVProgressHUD dismiss];
        [self.tableView.mj_header endRefreshing];
        if ([response[@"code"] integerValue] == 0) {
            _model = [NYHuanZheDetailModel mj_objectWithKeyValues:response[@"data"]];
        }else{
            MYALERT(@"请求失败");
        }
        [weakSelf.tableView reloadData];
    } failure:^(NSError *error) {
        [self.tableView.mj_header endRefreshing];
        [SVProgressHUD dismiss];
        MYALERT(@"请求失败");
    }];
}


- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView
{
    if (_model == nil) {
        return 0;
    }else{
        if ([_model.info.status integerValue] == 1 || [_model.info.status integerValue] == 2) {
            return 4;
        }else if([_model.info.status integerValue] == 3){
            return 5;
        }
    }
    return 0;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    if (section == 0) {
        return 1;
    }else if (section == 1){
        if (_model.info.voiceDescribe.length == 0) {
            return 0;
        }
        return 1;
    }else if (section == 2){
        if (_model.info.image.length == 0) {
            return 0;
        }
        return 1;
    }else if (section == 3){
        return 1;
    }else if (section == 4){
        if (_model.healthExaminations.count == 0) {
            return 2;
        }else{
            return 2+_model.healthExaminations.count+2;
        }
    }
    return 0;
}

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath
{
    if (indexPath.section == 0) {
        return [self.tableView cellHeightForIndexPath:indexPath model:_model.info keyPath:@"wenZhenModel" cellClass:[NYHomeHuanZheInfoCell class] contentViewWidth:[UIScreen mainScreen].bounds.size.width];
    }else if (indexPath.section == 1){
        return 50;
    }else if (indexPath.section == 2){
        CGFloat PIC_WIDTH = (NYScreenW-(15*4))*0.33;
        NSArray * imgArray = [_model.info.image componentsSeparatedByString:@","];
        NSInteger row = imgArray.count / 3;
        if (imgArray.count % 3 == 0) {
            return (PIC_WIDTH+15)*row+15;
        }else{
            return (PIC_WIDTH+15)*(row+1)+15;
        }
    }else if (indexPath.section == 3){
        return 50;
    }else if (indexPath.section == 4){
        
        if (_model.healthExaminations.count == 0) {
            if (indexPath.row == 0) {
                return [self.tableView cellHeightForIndexPath:indexPath model:_model keyPath:@"huanZheDetailModel" cellClass:[NYWenZhenDetailTopCell class] contentViewWidth:[UIScreen mainScreen].bounds.size.width];
            }else if (indexPath.row == 1){
                return [self.tableView cellHeightForIndexPath:indexPath model:_model keyPath:@"huanZheDetailModel" cellClass:[NYWenZhenDetailYiZhuCell class] contentViewWidth:[UIScreen mainScreen].bounds.size.width];
            }
        }else{
            if (indexPath.row == 0) {
                return [self.tableView cellHeightForIndexPath:indexPath model:_model keyPath:@"huanZheDetailModel" cellClass:[NYWenZhenDetailTopCell class] contentViewWidth:[UIScreen mainScreen].bounds.size.width];
            }else if (indexPath.row == (3+_model.healthExaminations.count)){
                return [self.tableView cellHeightForIndexPath:indexPath model:_model keyPath:@"huanZheDetailModel" cellClass:[NYWenZhenDetailYiZhuCell class] contentViewWidth:[UIScreen mainScreen].bounds.size.width];
            }else{
                return 50;
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
    return 0.0001f;
}

-(UIView *)tableView:(UITableView *)tableView viewForHeaderInSection:(NSInteger)section {
    return [UIView new];
}

- (UIView *)tableView:(UITableView *)tableView viewForFooterInSection:(NSInteger)section {
    return [UIView new];
}

#pragma mark - 播放语音
- (void)clickPlayVoiceBtn
{
    NSData * voiceData = [[NSData alloc] initWithBase64EncodedString:_model.info.voiceDescribe options:NSDataBase64DecodingIgnoreUnknownCharacters];
    
    NSError *error;
    _play = [[AVAudioPlayer alloc]initWithData:voiceData error:&error];
    _play.delegate = self;
    NSLog(@"%@",error);
    _play.volume = 1.0f;
    [_play play];
    NSLog(@"yesssssssssss..........%f",_play.duration);
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


- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    WEAKSELF
    if (indexPath.section == 0) {
        if (indexPath.row == 0) {
            NYHomeHuanZheInfoCell * cell = [tableView dequeueReusableCellWithIdentifier:@"HomeHuanZheInfoCellID"];
            cell.wenZhenModel = _model.info;
            return cell;
        }
    }else if (indexPath.section == 1){
        NYHomeHuanZheInfoVoiceCell * cell = [tableView dequeueReusableCellWithIdentifier:@"HomeHuanZheInfoVoiceCellID"];
        cell.clickVoiceButton = ^{
            [weakSelf clickPlayVoiceBtn];
        };
        return cell;
    }else if (indexPath.section == 2){
        NYHomeHuanZheInfoImgCell * cell = [tableView dequeueReusableCellWithIdentifier:@"HomeHuanZheInfoImgCellID"];
        cell.imageArray = [_model.info.image componentsSeparatedByString:@","];
        cell.clickImg = ^(NSInteger index, UIImageView * _Nonnull imageView) {
            NSArray * imArr = [_model.info.image componentsSeparatedByString:@","];
            [HUPhotoBrowser showFromImageView:imageView withURLStrings:imArr atIndex:index];
        };
        return cell;
    }else if (indexPath.section == 3){
        NYHomeHuanZheInfoTimeCell * cell = [tableView dequeueReusableCellWithIdentifier:@"HomeHuanZheInfoTimeCellID"];
        cell.wenZhenModel = _model.info;
        return cell;
    }else if (indexPath.section == 4){
        
        
        if (_model.healthExaminations.count == 0) {
            if (indexPath.row == 0) {
                NYWenZhenDetailTopCell * cell = [tableView dequeueReusableCellWithIdentifier:@"NYWenZhenDetailTopCellID"];
                cell.huanZheDetailModel = _model;
                return cell;
            }else if (indexPath.row == 1){
                NYWenZhenDetailYiZhuCell * cell = [tableView dequeueReusableCellWithIdentifier:@"NYWenZhenDetailYiZhuCellID"];
                cell.huanZheDetailModel = _model;
                cell.clickZiXunBlock = ^{
                    //跳转到聊天界面
                    RCConversationViewController * vc = [[RCConversationViewController alloc] initWithConversationType:ConversationType_PRIVATE targetId:_model.doctor.accountId];
                    vc.displayUserNameInCell = NO;
                    vc.title = @"咨询";
                    [self.navigationController pushViewController:vc animated:YES];
                    
                };
                cell.clickJiuZhenBlock = ^{
                    NYYuYueJiuZhenViewController * vc = [[NYYuYueJiuZhenViewController alloc] init];
                    vc.doctorID = _model.doctor.id;
                    vc.priceString = _model.doctor.registrationFee;
                    [self.navigationController pushViewController:vc animated:YES];
                };
                return cell;
            }
        }else{
            if (indexPath.row == 0) {
                NYWenZhenDetailTopCell * cell = [tableView dequeueReusableCellWithIdentifier:@"NYWenZhenDetailTopCellID"];
                cell.huanZheDetailModel = _model;
                return cell;
            }else if (indexPath.row == 1){
                NYAlreadyChoiceCheckTitleCell * cell = [tableView dequeueReusableCellWithIdentifier:@"NYAlreadyChoiceCheckTitleCellID"];
                return cell;
            }else if (indexPath.row == (2+_model.healthExaminations.count)){
                NYAlreadyChoiceCheckAllPriceCell * cell = [tableView dequeueReusableCellWithIdentifier:@"NYAlreadyChoiceCheckAllPriceCellID"];
                cell.huanZheDetailModel = _model;
                return cell;
            }else if (indexPath.row == (3+_model.healthExaminations.count)){
                NYWenZhenDetailYiZhuCell * cell = [tableView dequeueReusableCellWithIdentifier:@"NYWenZhenDetailYiZhuCellID"];
                cell.huanZheDetailModel = _model;
                cell.clickZiXunBlock = ^{
                    //跳转到聊天界面
                    RCConversationViewController * vc = [[RCConversationViewController alloc] initWithConversationType:ConversationType_PRIVATE targetId:_model.doctor.accountId];
                    vc.displayUserNameInCell = NO;
                    vc.title = @"咨询";
                    [self.navigationController pushViewController:vc animated:YES];
                };
                cell.clickJiuZhenBlock = ^{
                    NYYuYueJiuZhenViewController * vc = [[NYYuYueJiuZhenViewController alloc] init];
                    vc.doctorID = _model.doctor.id;
                    vc.priceString = _model.doctor.registrationFee;

                    [self.navigationController pushViewController:vc animated:YES];
                };
                return cell;
            }else{
                NYAlreadyChoiceCheckCell * cell = [tableView dequeueReusableCellWithIdentifier:@"NYAlreadyChoiceCheckCellID"];
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
