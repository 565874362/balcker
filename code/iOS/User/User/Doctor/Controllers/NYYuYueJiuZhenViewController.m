//
//  NYYuYueJiuZhenViewController.m
//  User
//
//  Created by niuyao on 2018/12/7.
//  Copyright © 2018 joymates. All rights reserved.
//

#import "NYYuYueJiuZhenViewController.h"
#import "NYNameInputCell.h"
#import "NYRegisterChoiceSexCell.h"
#import "RankButton.h"
#import "NYJiuZhenTimeModel.h"
#import "NYJiuZhenDoneViewController.h"

@interface NYYuYueJiuZhenViewController ()<UITableViewDelegate,UITableViewDataSource,UITextFieldDelegate>
{
    HHTextField * _nameTF;
    HHTextField * _ageTF;
    
    HHTextField * _phoneTF;
    HHTextField * _timeTF;
    HHTextField * _priceTF;
    
    NSInteger _sex;
    
    NSMutableArray * _dataArray;
    
    UILabel * _amCountLB; //上午有号吗
    UILabel * _pmCountLB; //下午有号吗
    
    UIButton * _amButton;
    UIButton * _pmButton;
    
    NSInteger _timePart; //0上午 1下午
    
    NSMutableArray * _buttonArray;
    
    NSInteger _seletedBtnIndex;
}
@property (nonatomic, strong) UITableView *tableView;



@end

@implementation NYYuYueJiuZhenViewController

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
        _tableView.showsVerticalScrollIndicator = YES;
        _tableView.showsHorizontalScrollIndicator = NO;
    }
    return _tableView;
}

- (void)viewDidLoad {
    [super viewDidLoad];
    self.view.backgroundColor = [UIColor whiteColor];
    self.navigationItem.title = @"预约就诊";
    [self.tableView registerClass:[UITableViewCell class] forCellReuseIdentifier:@"Cell"];
    [self.tableView registerClass:[NYNameInputCell class] forCellReuseIdentifier:@"INPUTCELLID"];
    [self.tableView registerClass:[NYRegisterChoiceSexCell class] forCellReuseIdentifier:@"NYChoiceSexCellID"];

    _sex = 0;
    _dataArray = [NSMutableArray array];
    _buttonArray = [NSMutableArray array];
    _seletedBtnIndex = 0;
}

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView
{
    return 3;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    if (section == 0) {
        return 4;
    }else if (section == 1){
        return 1;
    }else if (section == 2){
        return 1;
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
    if (section == 2) {
        return 200;
    }
    return 8;
}

-(UIView *)tableView:(UITableView *)tableView viewForHeaderInSection:(NSInteger)section {
    return [UIView new];
}

- (UIView *)tableView:(UITableView *)tableView viewForFooterInSection:(NSInteger)section {
    if (section == 2) {
        UIView * bgView = [[UIView alloc] init];
        bgView.backgroundColor = [UIColor clearColor];
        
        UIButton * doneButton = [UIButton buttonWithType:UIButtonTypeCustom];
        [doneButton setTitle:@"提交" forState:UIControlStateNormal];
        [doneButton setBackgroundColor:MAINCOLOR];
        [doneButton setTitleColor:[UIColor whiteColor] forState:UIControlStateNormal];
        [bgView addSubview:doneButton];
        
        doneButton.sd_layout
        .leftSpaceToView(bgView, 30)
        .topSpaceToView(bgView, 50)
        .rightSpaceToView(bgView, 30)
        .heightIs(50);
        doneButton.sd_cornerRadius = @5;
        [doneButton addTarget:self action:@selector(clickDoneButton:) forControlEvents:UIControlEventTouchUpInside];
        
        return bgView;
    }
    return [UIView new];
}

#pragma mark - 点击提交按钮
- (void)clickDoneButton:(UIButton *)button
{
    
    NSString * nameString = [_nameTF.text stringByTrimmingCharactersInSet:[NSCharacterSet whitespaceAndNewlineCharacterSet]];
    
    NSString * ageString = [_ageTF.text stringByTrimmingCharactersInSet:[NSCharacterSet whitespaceAndNewlineCharacterSet]];
    
    NSString * phoneString = [_phoneTF.text stringByTrimmingCharactersInSet:[NSCharacterSet whitespaceAndNewlineCharacterSet]];
    
    NSString * timeString = [_timeTF.text stringByTrimmingCharactersInSet:[NSCharacterSet whitespaceAndNewlineCharacterSet]];

    
    if (nameString.length == 0) {
        MYALERT(@"请输入患者姓名");
        return;
    }
    if (ageString.length == 0) {
        MYALERT(@"请输入患者年龄");
        return;
    }
    if (phoneString.length == 0) {
        MYALERT(@"请输入联系电话");
        return;
    }
    if (phoneString.length != 11) {
        MYALERT(@"手机格式不正确");
        return;
    }
    if (timeString.length == 0) {
        MYALERT(@"请选择就诊时间");
        return;
    }

    NYJiuZhenTimeModel * jiuZhenM = _dataArray[_seletedBtnIndex];
    
    NSDictionary * dict =@{
                           @"age":@([ageString integerValue]),
                           @"doctorId":self.doctorID,
                           @"gender":@(_sex),
                           @"name":nameString,
                           @"phone":phoneString,
                           @"scheduleId":jiuZhenM.id,
                           @"timePart":@(_timePart),
                           @"visitTime":[jiuZhenM.fullDate substringWithRange:NSMakeRange(0, 10)]
                           };
    [SVProgressHUD showWithStatus:nil];
    [SVProgressHUD setDefaultMaskType:(SVProgressHUDMaskTypeClear)];

    [PPHTTPRequest postYuYueGuaHaoInfoWithParameters:dict success:^(id response) {
        [SVProgressHUD dismiss];
        if ([response[@"code"] integerValue] == 0) {
            NYJiuZhenDoneViewController * vc = [[NYJiuZhenDoneViewController alloc] init];
            vc.jiuZhenID = [NSString stringWithFormat:@"%@",response[@"data"][@"registrationId"]];
            [self.navigationController pushViewController:vc animated:YES];
        }else {
            MYALERT(response[@"msg"]);
        }
    } failure:^(NSError *error) {
        [SVProgressHUD dismiss];
        MYALERT(@"提交失败");
    }];
}


- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    if (indexPath.section == 0) {
        if (indexPath.row == 0) {
            NYNameInputCell * cell = [tableView dequeueReusableCellWithIdentifier:@"INPUTCELLID"];
            cell.typeLB.text = @"患者姓名";
            cell.inputTF.placeholder = @"请输入患者姓名";
            _nameTF = cell.inputTF;
            _nameTF.delegate = nil;
            return cell;
        }else if (indexPath.row == 1){
            NYNameInputCell * cell = [tableView dequeueReusableCellWithIdentifier:@"INPUTCELLID"];
            cell.typeLB.text = @"年龄";
            cell.inputTF.placeholder = @"请输入实际年龄";
            cell.inputTF.keyboardType = UIKeyboardTypePhonePad;
            _ageTF = cell.inputTF;
            _ageTF.delegate = self;
            _ageTF.maxLength = 2;
            return cell;
        }else if (indexPath.row == 2){
            NYRegisterChoiceSexCell * cell = [tableView dequeueReusableCellWithIdentifier:@"NYChoiceSexCellID"];
            cell.clickManButton = ^(RankButton * _Nonnull setBtn) {
                _sex = 1;
                if (!setBtn.isSelected) {
                    setBtn.selected = !setBtn.selected;
                }
            };
            cell.clickWomanButton = ^(RankButton * _Nonnull setBtn) {
                _sex = 0;
                if (!setBtn.isSelected) {
                    setBtn.selected = !setBtn.selected;
                }
            };
            return cell;
        }else if (indexPath.row == 3){
            NYNameInputCell * cell = [tableView dequeueReusableCellWithIdentifier:@"INPUTCELLID"];
            cell.typeLB.text = @"联系电话";
            cell.inputTF.placeholder = @"请输入联系电话";
            cell.inputTF.keyboardType = UIKeyboardTypePhonePad;
            _phoneTF = cell.inputTF;
            _phoneTF.delegate = self;
            _phoneTF.maxLength = 11;
            return cell;
        }
    }else if (indexPath.section == 1){
        if (indexPath.row == 0) {
            NYNameInputCell * cell = [tableView dequeueReusableCellWithIdentifier:@"INPUTCELLID"];
            cell.typeLB.text = @"就诊时间";
            cell.inputTF.placeholder = @"选择就诊时间";
            _timeTF = cell.inputTF;
            _timeTF.delegate = self;
            return cell;
        }
    }else if (indexPath.section == 2){
        if (indexPath.row == 0) {
            NYNameInputCell * cell = [tableView dequeueReusableCellWithIdentifier:@"INPUTCELLID"];
            cell.typeLB.text = @"预约费";
            cell.inputTF.text = [NSString stringWithFormat:@"%.2f元",[self.priceString doubleValue]];
            cell.inputTF.enabled = NO;
            _priceTF = cell.inputTF;
            _priceTF.delegate = nil;
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


- (BOOL)textFieldShouldBeginEditing:(UITextField *)textField
{
    [_nameTF resignFirstResponder];
    [_ageTF resignFirstResponder];
    [_phoneTF resignFirstResponder];
    
    if (textField == _timeTF) {
        WEAKSELF
        [PPHTTPRequest GetChuZhenTimeListInfoWithParameters:self.doctorID success:^(id response) {
            [SVProgressHUD dismiss];
            if ([response[@"code"] integerValue] == 0) {
                NSArray * listArr = response[@"data"][@"diagnoseList"];
                
                [_dataArray removeAllObjects];
                for (NSDictionary *datc in listArr) {
                    NYJiuZhenTimeModel * timeModel = [NYJiuZhenTimeModel mj_objectWithKeyValues:datc];
                    [_dataArray addObject:timeModel];
                }
                
                [self initTimeView];
            }else{
                MYALERT(response[@"msg"]);
            }
        } failure:^(NSError *error) {
            [SVProgressHUD dismiss];
            MYALERT(@"请求失败");
        }];
    }else if (textField == _ageTF || textField == _phoneTF){
        return YES;
    }
    
    
    return NO;
}

- (BOOL)textField:(UITextField *)textField shouldChangeCharactersInRange:(NSRange)range replacementString:(NSString *)string {
    //如果是限制只能输入数字的文本框
    if (_ageTF == textField) {
        return [self validateNumber1:string];
    }else if (_phoneTF == textField){
        return [self validateNumber2:string];
    }
    //否则返回yes,不限制其他textfield
    return YES;
}

#pragma mark - 只能输入数字
- (BOOL)validateNumber1:(NSString*)number {
    
    BOOL res = YES;
    NSCharacterSet* tmpSet = [NSCharacterSet characterSetWithCharactersInString:@"0123456789"];
    int i = 0;
    while (i < number.length) {
        NSString * string = [number substringWithRange:NSMakeRange(i, 1)];
        NSRange range = [string rangeOfCharacterFromSet:tmpSet];
        if (range.length == 0) {
            res = NO;
            break;
        }
        i++;
    }
    
    if (_ageTF.text.length == 0 && [number isEqualToString:@"0"]) {
        res = NO;
    }
    
    
    return res;
}

#pragma mark - 只能输入数字
- (BOOL)validateNumber2:(NSString*)number {
    
    BOOL res = YES;
    NSCharacterSet* tmpSet = [NSCharacterSet characterSetWithCharactersInString:@"0123456789"];
    int i = 0;
    while (i < number.length) {
        NSString * string = [number substringWithRange:NSMakeRange(i, 1)];
        NSRange range = [string rangeOfCharacterFromSet:tmpSet];
        if (range.length == 0) {
            res = NO;
            break;
        }
        i++;
    }
    
    if (_phoneTF.text.length == 0 && ![number isEqualToString:@"1"]) {
        res = NO;
    }
    
    return res;
}


#pragma mark - 初始化选择时间view
- (void)initTimeView
{
    _seletedBtnIndex = 0;
    
    UIView * bgView = [[UIView alloc] init];
    bgView.tag = 10001;
    bgView.backgroundColor = RGBA(0, 0, 0, 0.5);
    [self.navigationController.view addSubview:bgView];
    
    bgView.sd_layout
    .spaceToSuperView(UIEdgeInsetsMake(0, 0, 0, 0));
    
    _timeTF.inputView = bgView;

    //内容
    UIView * contentView = [[UIView alloc] init];
    contentView.backgroundColor = [UIColor whiteColor];
    [bgView addSubview:contentView];
    contentView.sd_layout
    .leftSpaceToView(bgView, 0)
    .bottomSpaceToView(bgView, 0)
    .rightSpaceToView(bgView, 0)
    .heightIs(250);
    
    //标题
    UILabel * titleLB = [[UILabel alloc] init];
    titleLB.textAlignment = NSTextAlignmentCenter;
    titleLB.textColor = COLOR_BIG;
    titleLB.font = FONT(16);
    [contentView addSubview:titleLB];
    
    titleLB.sd_layout
    .leftSpaceToView(contentView, 0)
    .topSpaceToView(contentView, 0)
    .rightSpaceToView(contentView, 0)
    .heightIs(40);
    
    titleLB.text = @"请选择就诊时间";
    
    //删除按钮
    UIButton * deleteBtn = [UIButton buttonWithType:UIButtonTypeCustom];
    [deleteBtn setImage:[UIImage imageNamed:@"share_delete_select_icon"] forState:UIControlStateNormal];
    [contentView addSubview:deleteBtn];
    
    deleteBtn.sd_layout
    .rightSpaceToView(contentView,10)
    .topSpaceToView(contentView, 5)
    .widthIs(30)
    .heightIs(30);
    [deleteBtn addTarget:self action:@selector(clickDeletedBtn:) forControlEvents:UIControlEventTouchUpInside];

    
    
    //地下的view
    UIView * bottomView = [[UIView alloc] init];
    bottomView.backgroundColor = BGCOLOR;
    bottomView.layer.borderColor = COLOR_LOW.CGColor;
    bottomView.layer.borderWidth = 1;
    [contentView addSubview:bottomView];
    
    bottomView.sd_layout
    .leftSpaceToView(contentView, 10)
    .bottomSpaceToView(contentView, 10)
    .rightSpaceToView(contentView, 10)
    .heightIs(120);
    
    bottomView.sd_cornerRadius = @(5);
    
    
    //中间日期view
    UIView * centerView = [[UIView alloc] init];
    centerView.backgroundColor = [UIColor whiteColor];
    [contentView addSubview:centerView];
    centerView.sd_layout
    .leftSpaceToView(contentView, 10)
    .topSpaceToView(titleLB, 5)
    .rightSpaceToView(contentView, 10)
    .bottomSpaceToView(bottomView, 5);
    
    UIScrollView * contentScroll = [[UIScrollView alloc] init];
    contentScroll.showsVerticalScrollIndicator = NO;
    contentScroll.showsHorizontalScrollIndicator = NO;
    [centerView addSubview:contentScroll];
    
    NSInteger margan = 8;
    NSInteger viewW = 100; //一个view的宽度
    NSInteger viewH = 35;
    
    contentScroll.sd_layout
    .leftSpaceToView(centerView, 0)
    .centerYEqualToView(centerView)
    .rightSpaceToView(centerView, 0)
    .heightIs(35);
    
    contentScroll.contentSize = CGSizeMake((viewW+margan)*_dataArray.count, viewH);
    
    [_buttonArray removeAllObjects];
    
    for (int i = 0; i < _dataArray.count; i++) {
        NYJiuZhenTimeModel *model = _dataArray[i];
        
        UIButton * timeButton = [UIButton buttonWithType:UIButtonTypeCustom];
        timeButton.frame = CGRectMake(i*(viewW+margan), 0, viewW, viewH);
        timeButton.tag = i;
        [timeButton setTitle:model.date forState:UIControlStateNormal];
        
        timeButton.titleLabel.font = FONT(14);
        [contentScroll addSubview:timeButton];
        timeButton.layer.cornerRadius = 5;
        [timeButton addTarget:self action:@selector(clickTimeButton:) forControlEvents:UIControlEventTouchUpInside];


        if (i == 0) {
            timeButton.selected = YES;
            [timeButton setBackgroundColor:MAINCOLOR];
            [timeButton setTitleColor:[UIColor whiteColor] forState:UIControlStateNormal];
        }else{
            timeButton.selected = NO;
            [timeButton setBackgroundColor:BGCOLOR];
            [timeButton setTitleColor:COLOR_LOW forState:UIControlStateNormal];
        }
        
        [_buttonArray addObject:timeButton];
    }
    
    
    //中间的线
    UIView * lineView = [[UIView alloc] init];
    lineView.backgroundColor = COLOR_LOW;
    [bottomView addSubview:lineView];
    lineView.sd_layout
    .leftSpaceToView(bottomView, 0)
    .rightSpaceToView(bottomView, 0)
    .heightIs(1)
    .centerXEqualToView(bottomView)
    .centerYEqualToView(bottomView);
    
    //上午
    UILabel * amLB = [[UILabel alloc] init];
    amLB.textAlignment = NSTextAlignmentCenter;
    amLB.textColor = COLOR_BIG;
    [bottomView addSubview:amLB];
    amLB.sd_layout
    .leftSpaceToView(bottomView, 0)
    .topSpaceToView(bottomView, 0)
    .bottomSpaceToView(lineView, 0)
    .widthIs(60);
    
    amLB.text = @"上午";
    
    //上午有号吗？？
    UILabel * amCountLB = [[UILabel alloc] init];
    _amCountLB = amCountLB;
    amCountLB.textAlignment = NSTextAlignmentCenter;
    amCountLB.textColor = COLOR_RED;
    [bottomView addSubview:amCountLB];
    amCountLB.sd_layout
    .leftSpaceToView(amLB, 0)
    .topSpaceToView(bottomView, 0)
    .bottomSpaceToView(lineView, 0)
    .widthIs(60);
    
    amCountLB.text = @"无";

    //上午按钮
    UIButton * amButton = [UIButton buttonWithType:UIButtonTypeCustom];
    _amButton = amButton;
    _amButton.hidden = YES;
    [amButton setTitle:@"挂号" forState:UIControlStateNormal];
    [amButton setBackgroundColor:MAINCOLOR];
    amButton.titleLabel.font = FONT(16);
    [amButton setTitleColor:[UIColor whiteColor] forState:UIControlStateNormal];
    [bottomView addSubview:amButton];
    
    amButton.sd_layout
    .topSpaceToView(bottomView, 15)
    .rightSpaceToView(bottomView, 15)
    .heightIs(30)
    .widthIs(80);
    amButton.sd_cornerRadius = @5;
    [amButton addTarget:self action:@selector(clickAMButton:) forControlEvents:UIControlEventTouchUpInside];

    
    //下午
    UILabel * pmLB = [[UILabel alloc] init];
    pmLB.textAlignment = NSTextAlignmentCenter;
    pmLB.textColor = COLOR_BIG;
    [bottomView addSubview:pmLB];
    pmLB.sd_layout
    .leftSpaceToView(bottomView, 0)
    .topSpaceToView(lineView, 0)
    .bottomSpaceToView(bottomView, 0)
    .widthIs(60);
    
    pmLB.text = @"下午";

    //下午有号吗？？
    UILabel * pmCountLB = [[UILabel alloc] init];
    _pmCountLB = pmCountLB;
    pmCountLB.textAlignment = NSTextAlignmentCenter;
    pmCountLB.textColor = COLOR_RED;
    [bottomView addSubview:pmCountLB];
    pmCountLB.sd_layout
    .leftSpaceToView(pmLB, 0)
    .topSpaceToView(lineView, 0)
    .bottomSpaceToView(bottomView, 0)
    .widthIs(60);
    
    pmCountLB.text = @"无";

    //下午按钮
    UIButton * pmButton = [UIButton buttonWithType:UIButtonTypeCustom];
    _pmButton = pmButton;
    _pmButton.hidden = YES;
    [pmButton setTitle:@"挂号" forState:UIControlStateNormal];
    [pmButton setBackgroundColor:MAINCOLOR];
    pmButton.titleLabel.font = FONT(16);
    [pmButton setTitleColor:[UIColor whiteColor] forState:UIControlStateNormal];
    [bottomView addSubview:pmButton];
    
    pmButton.sd_layout
    .topSpaceToView(lineView, 15)
    .rightSpaceToView(bottomView, 15)
    .heightIs(30)
    .widthIs(80);
    pmButton.sd_cornerRadius = @5;
    [pmButton addTarget:self action:@selector(clickPMButton:) forControlEvents:UIControlEventTouchUpInside];

    
    NYJiuZhenTimeModel * jiuzhenModel = [_dataArray firstObject];
    if (jiuzhenModel) {
        //上午
        if ([jiuzhenModel.morningRemainNum integerValue] > 0) {
            _amCountLB.text = @"有";
            _amCountLB.textColor = MAINCOLOR;
            _amButton.hidden = NO;
        }else{
            _amCountLB.text = @"无";
            _amCountLB.textColor = COLOR_RED;
            _amButton.hidden = YES;
        }
        
        //下午
        if ([jiuzhenModel.afternoonRemainNum integerValue] > 0) {
            _pmCountLB.text = @"有";
            _pmCountLB.textColor = MAINCOLOR;
            _pmButton.hidden = NO;
        }else{
            _pmCountLB.text = @"无";
            _pmCountLB.textColor = COLOR_RED;
            _pmButton.hidden = YES;
        }
    }
}

#pragma mark - 点击删除按钮
- (void)clickDeletedBtn:(UIButton *)button
{
    UIView * bgView = [self.navigationController.view viewWithTag:10001];
    [bgView removeFromSuperview];
    
}

#pragma mark - 点击上午挂号按钮
- (void)clickAMButton:(UIButton *)button
{
    UIView * bgView = [self.navigationController.view viewWithTag:10001];
    [bgView removeFromSuperview];

    _timePart = 0;
    
    NYJiuZhenTimeModel * model = _dataArray[_seletedBtnIndex];
    
    _timeTF.text = [NSString stringWithFormat:@"%@ 上午",model.fullDate];
    
    [self.tableView reloadData];
}

#pragma mark - 点击下午挂号按钮
- (void)clickPMButton:(UIButton *)button
{
    UIView * bgView = [self.navigationController.view viewWithTag:10001];
    [bgView removeFromSuperview];

    _timePart = 1;
    
    NYJiuZhenTimeModel * model = _dataArray[_seletedBtnIndex];

    _timeTF.text = [NSString stringWithFormat:@"%@ 下午",model.fullDate];

    [self.tableView reloadData];

}

#pragma mark - 点击选择时间
- (void)clickTimeButton:(UIButton *)button
{
    _seletedBtnIndex = button.tag;
    
    for (UIButton * obj in _buttonArray) {
        if (obj == button) {
            obj.selected = YES;
        }else{
            obj.selected = NO;
        }
    }
    
    for (UIButton * btn in _buttonArray) {
        if (btn.isSelected == YES) {
            [btn setBackgroundColor:MAINCOLOR];
            [btn setTitleColor:[UIColor whiteColor] forState:UIControlStateNormal];
        }else{
            [btn setBackgroundColor:BGCOLOR];
            [btn setTitleColor:COLOR_LOW forState:UIControlStateNormal];
        }
    }
    
    NYJiuZhenTimeModel * jiuzhenModel = _dataArray[button.tag];
    if (jiuzhenModel) {
        //上午
        if ([jiuzhenModel.morningRemainNum integerValue] > 0) {
            _amCountLB.text = @"有";
            _amCountLB.textColor = MAINCOLOR;
            _amButton.hidden = NO;
        }else{
            _amCountLB.text = @"无";
            _amCountLB.textColor = COLOR_RED;
            _amButton.hidden = YES;
        }
        
        //下午
        if ([jiuzhenModel.afternoonRemainNum integerValue] > 0) {
            _pmCountLB.text = @"有";
            _pmCountLB.textColor = MAINCOLOR;
            _pmButton.hidden = NO;
        }else{
            _pmCountLB.text = @"无";
            _pmCountLB.textColor = COLOR_RED;
            _pmButton.hidden = YES;
        }
    }

}
@end
