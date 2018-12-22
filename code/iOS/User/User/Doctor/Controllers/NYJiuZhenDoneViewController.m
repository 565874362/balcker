//
//  NYJiuZhenDoneViewController.m
//  User
//
//  Created by niuyao on 2018/12/18.
//  Copyright © 2018 joymates. All rights reserved.
//

#import "NYJiuZhenDoneViewController.h"
#import "NYChuZhenTitleCell.h"
#import "NYJieZhenDetailCell.h"
#import "NYJieZhenDoctorDetailCell.h"

#import "NYPayInfoModel.h"
#import "NYYuYueJiuZhenModel.h"
#import "NYJiuZhenDoneTopCell.h"
#import "NYDoctorInfoDetailViewController.h"
#import "NYJiuZhenDonePayWayCell.h"
#import "NYPayWayModel.h"

#import "WXApi.h"
#import <AlipaySDK/AlipaySDK.h>

#import "NYWenZhenDetailViewController.h"
@interface NYJiuZhenDoneViewController ()<UITableViewDelegate,UITableViewDataSource>
{
    NYYuYueJiuZhenModel * _jiuZhenModel;
    
    NSMutableArray * _payWayArray;
}

@property (nonatomic, strong) UITableView *tableView;

@end

@implementation NYJiuZhenDoneViewController

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
        
        _tableView.estimatedRowHeight = 0;
        _tableView.estimatedSectionHeaderHeight = 0;
        _tableView.estimatedSectionFooterHeight = 0;
        
    }
    return _tableView;
}

- (void)viewDidLoad {
    [super viewDidLoad];
    self.view.backgroundColor = [UIColor whiteColor];
    self.navigationItem.title = @"就诊确认";
    
    [self.tableView registerClass:[UITableViewCell class] forCellReuseIdentifier:@"Cell"];
    [self.tableView registerClass:[NYChuZhenTitleCell class] forCellReuseIdentifier:@"TitleCell"];
    [self.tableView registerClass:[NYJieZhenDetailCell class] forCellReuseIdentifier:@"JieZhenDetailCellID"];
    [self.tableView registerClass:[NYJiuZhenDoneTopCell class] forCellReuseIdentifier:@"NYJiuZhenDoneTopCellID"];
    [self.tableView registerClass:[NYJiuZhenDonePayWayCell class] forCellReuseIdentifier:@"NYJiuZhenDonePayWayCellID"];

    
    [self loadData];
    
    _payWayArray = [NSMutableArray array];
    
    // 判断手机有没有微信
    if ([WXApi isWXAppInstalled]) {
        NYPayWayModel * model1 = [[NYPayWayModel alloc] init];
        model1.typeName = @"微信支付";
        model1.imageName = @"WeChat";
        model1.payType = 1;
        model1.isSeleted = NO;
        [_payWayArray addObject:model1];
        
        NYPayWayModel * model2 = [[NYPayWayModel alloc] init];
        model2.typeName = @"支付宝支付";
        model2.imageName = @"Pay_treasure";
        model2.payType = 2;
        model2.isSeleted = YES;
        [_payWayArray addObject:model2];

    }else{
        NYPayWayModel * model2 = [[NYPayWayModel alloc] init];
        model2.typeName = @"支付宝支付";
        model2.imageName = @"Pay_treasure";
        model2.payType = 2;
        model2.isSeleted = YES;
        [_payWayArray addObject:model2];
    }
    
    

    UIBarButtonItem * leftItem = [[UIBarButtonItem alloc] initWithImage:[UIImage imageNamed:@"Common_back_n"] style:UIBarButtonItemStylePlain target:self action:@selector(clickLeftBackItem:)];
    self.navigationItem.leftBarButtonItem = leftItem;
    
}

- (void)clickLeftBackItem:(UIBarButtonItem *)item
{
    for (UIViewController * vc in self.navigationController.viewControllers) {
        if ([vc isMemberOfClass:[NYDoctorInfoDetailViewController class]]) {
            [self.navigationController popToViewController:vc animated:YES];
            break;
        }else if ([vc isMemberOfClass:[NYWenZhenDetailViewController class]]){
            [self.navigationController popToViewController:vc animated:YES];
            break;
        }
    }
}

#pragma mark - 请求数据
- (void)loadData
{
    WEAKSELF
    [SVProgressHUD showWithStatus:nil];
    [SVProgressHUD setDefaultMaskType:(SVProgressHUDMaskTypeClear)];
    
    [PPHTTPRequest GetGuaHaoDetialInfoWithParameters:self.jiuZhenID success:^(id response) {
        [SVProgressHUD dismiss];
        if ([response[@"code"] integerValue] == 0) {
            _jiuZhenModel = [NYYuYueJiuZhenModel mj_objectWithKeyValues:response[@"data"][@"info"]];
            
            [weakSelf setBottomUI];
            
        }else{
            MYALERT(@"请求失败");
        }
        [weakSelf.tableView reloadData];
        
    } failure:^(NSError *error) {
        [SVProgressHUD dismiss];
        MYALERT(@"请求失败");
    }];
}

#pragma mark - 底部按钮
- (void)setBottomUI
{
    UIButton * doneButton = [UIButton buttonWithType:UIButtonTypeCustom];
    [doneButton setTitle:@"提交预约" forState:UIControlStateNormal];
    [doneButton setBackgroundColor:MAINCOLOR];
    [doneButton setTitleColor:[UIColor whiteColor] forState:UIControlStateNormal];
    [self.view addSubview:doneButton];
    
    doneButton.sd_layout
    .leftSpaceToView(self.view, 0)
    .bottomSpaceToView(self.view, 0)
    .rightSpaceToView(self.view, 0)
    .heightIs(50);
    [doneButton addTarget:self action:@selector(clickDoneButton:) forControlEvents:UIControlEventTouchUpInside];
}

#pragma mark - 提交预约按钮
- (void)clickDoneButton:(UIButton *)button
{
    NSInteger seletedIndex = 2;
    for (NYPayWayModel * model in _payWayArray) {
        if (model.isSeleted) {
            seletedIndex = model.payType;
        }
    }
    
    if (seletedIndex == 1) { //微信
        [self WechatPay];
    }else{
        //支付宝
        [[AlipaySDK defaultService] payOrder:@"" fromScheme:@"BaiHuaUserAlipaySDK" callback:^(NSDictionary* resultDic) {
            NSLog(@"%@",resultDic);
        }];
    }
}

#pragma mark 微信支付方法
- (void)WechatPay{
    //需要创建这个支付对象
    PayReq *req   = [[PayReq alloc] init];
    //由用户微信号和AppID组成的唯一标识，用于校验微信用户
    req.openID = @"";
    // 商家id，在注册的时候给的
    req.partnerId = @"";
    // 预支付订单这个是后台跟微信服务器交互后，微信服务器传给你们服务器的，你们服务器再传给你
    req.prepayId  = @"";
    // 根据财付通文档填写的数据和签名
    req.package  = @"";
    // 随机编码，为了防止重复的，在后台生成
    req.nonceStr  = @"";
    // 这个是时间戳，也是在后台生成的，为了验证支付的
    NSString * stamp = @"";
    req.timeStamp = stamp.intValue;
    // 这个签名也是后台做的
    req.sign = @"";
    //发送请求到微信，等待微信返回onResp
    [WXApi sendReq:req];
}

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView
{
    if (_jiuZhenModel == nil) {
        return 0;
    }
    return 3;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    if (section == 0) {
        return 1;
    }else if (section == 1){
        return 6;
    }else if (section == 2){
        return _payWayArray.count;
    }
    return 0;
}

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath
{
    if (indexPath.section == 0) {
        if (indexPath.row == 0){
            return 260;
        }
    }else if (indexPath.section == 1){
        return 50;
    }else if (indexPath.section == 2){
        return 60;
    }
    return 0;
}

- (CGFloat)tableView:(UITableView *)tableView heightForHeaderInSection:(NSInteger)section
{
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
    return [UIView new];
}

- (UIView *)tableView:(UITableView *)tableView viewForFooterInSection:(NSInteger)section {
    return [UIView new];
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    if (indexPath.section == 0) {
        if (indexPath.row == 0){
            NYJiuZhenDoneTopCell * cell = [tableView dequeueReusableCellWithIdentifier:@"NYJiuZhenDoneTopCellID"];
            cell.jiuZhenModel = _jiuZhenModel;
            return cell;
        }
    }else if (indexPath.section == 1) {
        if (indexPath.row == 0) {
            NYChuZhenTitleCell * cell = [tableView dequeueReusableCellWithIdentifier:@"TitleCell"];
            cell.titleLB.text = @"患者信息";
            return cell;
        }else{
            NYJieZhenDetailCell * cell = [tableView dequeueReusableCellWithIdentifier:@"JieZhenDetailCellID"];
            if (indexPath.row == 1) {
                cell.typeLB.text = @"患者姓名";
                cell.infoLB.text = _jiuZhenModel.name;
            }else if (indexPath.row == 2){
                cell.typeLB.text = @"年龄";
                cell.infoLB.text = [NSString stringWithFormat:@"%@岁",_jiuZhenModel.age];
            }else if (indexPath.row == 3){
                cell.typeLB.text = @"性别";
                cell.infoLB.text = [_jiuZhenModel.gender integerValue]==0?@"女":@"男";
            }else if (indexPath.row == 4){
                cell.typeLB.text = @"联系电话";
                cell.infoLB.text = _jiuZhenModel.phone;
            }else if (indexPath.row == 5){
                cell.typeLB.text = @"就诊时间";
                cell.infoLB.text = [NSString stringWithFormat:@"%@ %@",_jiuZhenModel.visitTime,[_jiuZhenModel.timePart integerValue]==0?@"上午":@"下午"];
            }
            return cell;
        }
    }else if (indexPath.section == 2){
        NYJiuZhenDonePayWayCell * cell = [tableView dequeueReusableCellWithIdentifier:@"NYJiuZhenDonePayWayCellID"];
        NYPayWayModel * payWayModel = _payWayArray[indexPath.row];
        cell.typeLB.text = payWayModel.typeName;
        cell.leftIMG.image = [UIImage imageNamed:payWayModel.imageName];
        if (payWayModel.isSeleted) {
            cell.rightIMG.image = [UIImage imageNamed:@"register_pact_hover"];
        }else{
            cell.rightIMG.image = [UIImage imageNamed:@"register_pact"];
        }

        if (indexPath.row == 0) {
            cell.lineView.hidden = NO;
        }else if (indexPath.row == 1){
            cell.lineView.hidden = YES;
        }
        return cell;
    }
    
    UITableViewCell * cell = [tableView dequeueReusableCellWithIdentifier:@"Cell"];
    return cell;
}

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath
{
    [tableView deselectRowAtIndexPath:indexPath animated:YES];
    if (indexPath.section == 2) {
        NYPayWayModel * payWayModel = _payWayArray[indexPath.row];
        
        for (NYPayWayModel * obj in _payWayArray) {
            obj.isSeleted = NO;
        }
        payWayModel.isSeleted = YES;
        [self.tableView reloadData];
    }
}

@end
