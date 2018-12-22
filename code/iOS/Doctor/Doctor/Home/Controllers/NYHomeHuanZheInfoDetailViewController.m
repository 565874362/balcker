//
//  NYHomeHuanZheInfoDetailViewController.m
//  Doctor
//
//  Created by niuyao on 2018/12/5.
//  Copyright © 2018 joymates. All rights reserved.
//

#import "NYHomeHuanZheInfoDetailViewController.h"
#import "NYHomeHuanZheInfoCell.h"
#import "NYHomeListModel.h"
#import "NYHomeHuanZheInfoTimeCell.h"
#import "NYHomeHuanZheInfoImgCell.h"
#import "NYHomeHuanZheInfoVoiceCell.h"
#import "HUPhotoBrowser.h"
#import "NYRegisterShanChangCell.h"
#import "BRPlaceholderTextView.h"
#import "NYHomeHuanZheChoiceCheckCell.h"
#import "NYNeedCheckViewController.h"
#import "NYAlreadyChoiceCheckCell.h"
#import "NYAlreadyChoiceCheckTitleCell.h"
#import "NYAlreadyChoiceCheckAllPriceCell.h"

#import "NYHuanZheDetailModel.h"
#import "NYMyInfoDetailModel.h"
#import "D3RecordButton.h"
#import "NYNeedCheckModel.h"

#import "NYWenZhenDetailTopCell.h"
#import "NYWenZhenDetailYiZhuCell.h"

@interface NYHomeHuanZheInfoDetailViewController ()<AVAudioPlayerDelegate,UITableViewDelegate,UITableViewDataSource>
{
    NYHuanZheDetailModel * _model;
    NSArray * _seletedCheckArray;
    AVAudioPlayer * _play;
    
    UIImageView * _qiangDanImg;
    UIButton * _tijiaoButton;
    
    BRPlaceholderTextView * _chuBuTextView;
    BRPlaceholderTextView * _yiZhuTextView;
    
}
@property (nonatomic, strong) UITableView *tableView;


@end

@implementation NYHomeHuanZheInfoDetailViewController

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
        _tableView.estimatedRowHeight = 0;
        _tableView.estimatedSectionHeaderHeight = 0;
        _tableView.estimatedSectionFooterHeight = 0;
    }
    return _tableView;
}

- (void)viewDidLoad {
    [super viewDidLoad];
    self.view.backgroundColor = [UIColor whiteColor];
    self.navigationItem.title = @"患者信息";
    [self.tableView registerClass:[UITableViewCell class] forCellReuseIdentifier:@"Cell"];
    [self.tableView registerClass:[NYHomeHuanZheInfoCell class] forCellReuseIdentifier:@"HomeHuanZheInfoCellID"];
    [self.tableView registerClass:[NYHomeHuanZheInfoTimeCell class] forCellReuseIdentifier:@"HomeHuanZheInfoTimeCellID"];
    [self.tableView registerClass:[NYHomeHuanZheInfoImgCell class] forCellReuseIdentifier:@"HomeHuanZheInfoImgCellID"];
    [self.tableView registerClass:[NYHomeHuanZheInfoVoiceCell class] forCellReuseIdentifier:@"HomeHuanZheInfoVoiceCellID"];
    [self.tableView registerClass:[NYRegisterShanChangCell class] forCellReuseIdentifier:@"NYRegisterShanChangCellID"];
    [self.tableView registerClass:[NYHomeHuanZheChoiceCheckCell class] forCellReuseIdentifier:@"NYHomeHuanZheChoiceCheckCellID"];
    [self.tableView registerClass:[NYAlreadyChoiceCheckCell class] forCellReuseIdentifier:@"NYAlreadyChoiceCheckCellID"];
    [self.tableView registerClass:[NYAlreadyChoiceCheckTitleCell class] forCellReuseIdentifier:@"NYAlreadyChoiceCheckTitleCellID"];
    [self.tableView registerClass:[NYAlreadyChoiceCheckAllPriceCell class] forCellReuseIdentifier:@"NYAlreadyChoiceCheckAllPriceCellID"];
    
    [self.tableView registerClass:[NYWenZhenDetailTopCell class] forCellReuseIdentifier:@"NYWenZhenDetailTopCellID"];
    [self.tableView registerClass:[NYWenZhenDetailYiZhuCell class] forCellReuseIdentifier:@"NYWenZhenDetailYiZhuCellID"];
    
    [self initJieZhenButton];//抢单按钮
    [self initTiJiaoButton]; //提交按钮

    
    
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
    WEAKSELF
    [PPHTTPRequest GetWenZhenDetailInfoWithParameters:self.detailID success:^(id response) {
        [SVProgressHUD dismiss];
        [self.tableView.mj_header endRefreshing];
        if ([response[@"code"] integerValue] == 0) {
            _model = [NYHuanZheDetailModel mj_objectWithKeyValues:response[@"data"]];
            
            if ([_model.info.status integerValue] == 1) {
                _qiangDanImg.hidden = NO;
                _tijiaoButton.hidden = YES;
            }else if ([_model.info.status integerValue] == 2){
                
                NSString * str1 = [NSString stringWithFormat:@"%@",_model.doctor.id];
                NSString * str2 = [NSString stringWithFormat:@"%@",[UserInfo getAccount]];

                if ([str1 isEqualToString:str2]) { //我抢的单子
                    _qiangDanImg.hidden = YES;
                    _tijiaoButton.hidden = NO;
                }else{
                    //不是我抢的单子
                    _qiangDanImg.hidden = YES;
                    _tijiaoButton.hidden = YES;
                    
                    _model.info.status = @"1";
                    MYALERT(@"该订单已被抢");
                }
                
                
            }else if([_model.info.status integerValue] == 3){
                NSString * str1 = [NSString stringWithFormat:@"%@",_model.doctor.id];
                NSString * str2 = [NSString stringWithFormat:@"%@",[UserInfo getAccount]];

                if ([str1 isEqualToString:str2]) { //我抢的单子
                    _qiangDanImg.hidden = YES;
                    _tijiaoButton.hidden = YES;
                }else{
                    //不是我抢的单子
                    _qiangDanImg.hidden = YES;
                    _tijiaoButton.hidden = YES;
                    
                    _model.info.status = @"1";
                    
                    MYALERT(@"该订单已被抢");
                }
            }
            
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


#pragma mark - 初始化抢单按钮
- (void)initJieZhenButton
{
    if (_qiangDanImg == nil) {
        UIImageView * imageV = [[UIImageView alloc] init];
        imageV.image = [UIImage imageNamed:@"accepts_btn"];
        imageV.clipsToBounds = YES;
        imageV.userInteractionEnabled = YES;
        [self.view addSubview:imageV];
        
        [imageV addGestureRecognizer:[[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(clickJieZhenQiangDan:)]];
        
        imageV.sd_layout
        .centerXEqualToView(self.view)
        .bottomSpaceToView(self.view, 20)
        .widthIs(100)
        .heightEqualToWidth();
        
        imageV.sd_cornerRadius = @50;
        
        _qiangDanImg = imageV;
        _qiangDanImg.hidden = YES;
    }
}

#pragma mark - 点击抢单按钮
- (void)clickJieZhenQiangDan:(UITapGestureRecognizer *)tap
{
    WEAKSELF
    [SVProgressHUD showWithStatus:nil];
    [SVProgressHUD setDefaultMaskType:(SVProgressHUDMaskTypeClear)];
    
    [PPHTTPRequest GetDoctorClickJieZhenInfoWithParameters:_model.info.id success:^(id response) {
        [SVProgressHUD dismiss];
        if ([response[@"code"] integerValue] == 0) {
            [self loadData];
        }else{
            MYALERT(@"请求失败");
        }
        [weakSelf.tableView reloadData];
    } failure:^(NSError *error) {
        [SVProgressHUD dismiss];
        MYALERT(@"请求失败");
    }];
}

#pragma mark - 创建提交按钮
- (void)initTiJiaoButton
{
    if (_tijiaoButton == nil) {
        UIButton * doneButton = [UIButton buttonWithType:UIButtonTypeCustom];
        [doneButton setTitle:@"提交" forState:UIControlStateNormal];
        [doneButton setBackgroundColor:MAINCOLOR];
        [doneButton setTitleColor:[UIColor whiteColor] forState:UIControlStateNormal];
        [self.view addSubview:doneButton];
        
        doneButton.sd_layout
        .leftSpaceToView(self.view, 0)
        .bottomSpaceToView(self.view, 0)
        .rightSpaceToView(self.view, 0)
        .heightIs(50);
        [doneButton addTarget:self action:@selector(clickDoneButton:) forControlEvents:UIControlEventTouchUpInside];
        
        _tijiaoButton = doneButton;
        _tijiaoButton.hidden = YES;
        
    }
}

#pragma mark - 点击提交按钮
- (void)clickDoneButton:(UIButton *)button
{
    
    [_chuBuTextView resignFirstResponder];
    [_yiZhuTextView resignFirstResponder];
    
    
    
    NSString * chuZhenString = [_chuBuTextView.text stringByTrimmingCharactersInSet:[NSCharacterSet whitespaceAndNewlineCharacterSet]];
    
    NSString * yiZhuString = [_yiZhuTextView.text stringByTrimmingCharactersInSet:[NSCharacterSet whitespaceAndNewlineCharacterSet]];
    
    
    
    if (chuZhenString.length == 0) {
        MYALERT(@"请输入初步诊断");
        return;
    }
    
    if (yiZhuString.length == 0) {
        MYALERT(@"请输入医嘱");
        return;
    }
    
    [SVProgressHUD showWithStatus:nil];
    [SVProgressHUD setDefaultMaskType:(SVProgressHUDMaskTypeClear)];
    
    WEAKSELF
    NSDictionary * dict = nil;
    if (_seletedCheckArray.count == 0) {
        dict = @{
                 @"advice":yiZhuString,
                 @"id":self.detailID,
                 @"response":chuZhenString,
                 };
    }else{
        NSMutableArray * idArr = [NSMutableArray array];
        for (NYNeedCheckModel * model in _seletedCheckArray) {
            [idArr addObject:model.id];
        }
        dict = @{
                 @"advice":yiZhuString,
                 @"exaIds":idArr,
                 @"id":self.detailID,
                 @"response":chuZhenString,
                 };
    }
    
    [PPHTTPRequest postDoctorHuiFuWenZhenInfoWithParameters:dict success:^(id response) {
        [SVProgressHUD dismiss];
        if ([response[@"code"] integerValue] == 0) {
            MYALERT(@"提交成功");
            [self loadData];
            [weakSelf.tableView reloadData];
        }else{
            MYALERT(response[@"msg"]);
        }
    } failure:^(NSError *error) {
        [SVProgressHUD dismiss];
        MYALERT(@"提交失败");
    }];
}

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView
{
    if ([_model.info.status integerValue] == 1) {
        return 4;
    }else if ([_model.info.status integerValue] == 2){
        return 4+3;
    }else if ([_model.info.status integerValue] == 3){
        return 5;
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
    }
    
    if ([_model.info.status integerValue] == 1) {
        
    }else if ([_model.info.status integerValue] == 2){
        if (section == 4){
            return 1;
        }else if (section == 5){
            if (_seletedCheckArray.count == 0) {
                return 1;
            }else{
                return 3+_seletedCheckArray.count;
            }
        }else if (section == 6){
            return 1;
        }
    }else if ([_model.info.status integerValue] == 3){
        if (section == 4){
            if (_model.healthExaminations.count == 0) {
                return 2;
            }else{
                return 2+_model.healthExaminations.count+2;
            }
        }
    }

    return 0;
}

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath
{
    if (indexPath.section == 0) {
        return [self.tableView cellHeightForIndexPath:indexPath model:_model.info keyPath:@"homeListModel" cellClass:[NYHomeHuanZheInfoCell class] contentViewWidth:[UIScreen mainScreen].bounds.size.width];
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
    }

    if ([_model.info.status integerValue] == 2){
        if (indexPath.section == 4 || indexPath.section == 6) {
            return 100;
        }
        return 50;
    }else if ([_model.info.status integerValue] == 3){
        if (indexPath.section == 4) {
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
    }

    return 0;
}

- (CGFloat)tableView:(UITableView *)tableView heightForHeaderInSection:(NSInteger)section
{
    return 0.0001f;
}

- (CGFloat)tableView:(UITableView *)tableView heightForFooterInSection:(NSInteger)section
{
    if ([_model.info.status integerValue] == 1) {
        if (section == 3) {
            return 120;
        }else{
            return 0.0001f;
        }
    }else if ([_model.info.status integerValue] == 2){
        if (section == 0 || section == 1 || section == 2) {
            return 0.0001f;
        }else if (section == 6) {
            return 60;
        }else{
            return 8;
        }
    }else if ([_model.info.status integerValue] == 3){
        if (section == 0 || section == 1 || section == 2) {
            return 0.0001f;
        }else{
            return 8;
        }
    }
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


- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    WEAKSELF
    if (indexPath.section == 0) {
        NYHomeHuanZheInfoCell * cell = [tableView dequeueReusableCellWithIdentifier:@"HomeHuanZheInfoCellID"];
        cell.homeListModel = _model.info;
        return cell;
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
        cell.homeListModel = _model.info;
        return cell;
    }

    if ([_model.info.status integerValue] == 2){
        if (indexPath.section == 4) {
            NYRegisterShanChangCell * cell = [tableView dequeueReusableCellWithIdentifier:@"NYRegisterShanChangCellID"];
            cell.infoTextView.placeholder = @"初步诊断:";
            _chuBuTextView = cell.infoTextView;
            return cell;
        }else if (indexPath.section == 5){
            if (indexPath.row == 0) {
                NYHomeHuanZheChoiceCheckCell * cell = [tableView dequeueReusableCellWithIdentifier:@"NYHomeHuanZheChoiceCheckCellID"];
                return cell;
            }else if (indexPath.row == 1){
                NYAlreadyChoiceCheckTitleCell * cell = [tableView dequeueReusableCellWithIdentifier:@"NYAlreadyChoiceCheckTitleCellID"];
                return cell;
            }else if (indexPath.row == (2+_seletedCheckArray.count)){
                NYAlreadyChoiceCheckAllPriceCell * cell = [tableView dequeueReusableCellWithIdentifier:@"NYAlreadyChoiceCheckAllPriceCellID"];
                cell.choiceCheckArr = _seletedCheckArray;
                return cell;
            }else{
                NYAlreadyChoiceCheckCell * cell = [tableView dequeueReusableCellWithIdentifier:@"NYAlreadyChoiceCheckCellID"];
                cell.checkModel = _seletedCheckArray[indexPath.row-2];
                return cell;
            }
        }else if (indexPath.section == 6){
            NYRegisterShanChangCell * cell = [tableView dequeueReusableCellWithIdentifier:@"NYRegisterShanChangCellID"];
            cell.infoTextView.placeholder = @"温馨医嘱:";
            _yiZhuTextView = cell.infoTextView;
            return cell;
        }
    }else if ([_model.info.status integerValue] == 3){
        if (indexPath.section == 4) {
            if (_model.healthExaminations.count == 0) {
                if (indexPath.row == 0) {
                    NYWenZhenDetailTopCell * cell = [tableView dequeueReusableCellWithIdentifier:@"NYWenZhenDetailTopCellID"];
                    cell.huanZheDetailModel = _model;
                    return cell;
                }else if (indexPath.row == 1){
                    NYWenZhenDetailYiZhuCell * cell = [tableView dequeueReusableCellWithIdentifier:@"NYWenZhenDetailYiZhuCellID"];
                    cell.huanZheDetailModel = _model;
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
                    return cell;
                }else{
                    NYAlreadyChoiceCheckCell * cell = [tableView dequeueReusableCellWithIdentifier:@"NYAlreadyChoiceCheckCellID"];
                    cell.huanZheCheckModel = _model.healthExaminations[indexPath.row-2];
                    return cell;
                }
            }

        }
    }

    UITableViewCell * cell = [tableView dequeueReusableCellWithIdentifier:@"Cell"];
    return cell;
}

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath
{
    WEAKSELF
    [tableView deselectRowAtIndexPath:indexPath animated:YES];
    if (indexPath.section == 5) {
        if (indexPath.row == 0) {
            NYNeedCheckViewController * vc = [[NYNeedCheckViewController alloc] init];
            vc.alreadyChoiceArr = _seletedCheckArray;
            vc.clickDoneChoicCheck = ^(NSArray * _Nonnull choiceArr) {
                _seletedCheckArray = choiceArr;
                [weakSelf.tableView reloadData];
            };
            [self.navigationController pushViewController:vc animated:YES];
        }
    }
}

@end
