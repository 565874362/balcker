//
//  NYChoiceChuZhenTimeViewController.m
//  Doctor
//
//  Created by niuyao on 2018/12/3.
//  Copyright © 2018 joymates. All rights reserved.
//

#import "NYChoiceChuZhenTimeViewController.h"
#import "NYChuZhenTitleCell.h"
#import "NYChuZhenSetCountCell.h"
#import "NYChoiceChuZhenTimeCell.h"
#import "NYChoiceWeekCell.h"
#import "NYJieZhenZhouQiModel.h"
#import "NYMyView.h"

@interface NYChoiceChuZhenTimeViewController ()<UITableViewDelegate,UITableViewDataSource,UITextFieldDelegate>
{
    HHTextField * _amStartTimeTF;
    HHTextField * _amEndTimeTF;
    HHTextField * _amCountTF;
    
    HHTextField * _pmStartTimeTF;
    HHTextField * _pmEndTimeTF;
    HHTextField * _pmCountTF;
    
    NSMutableArray * _dataArray;
    
    NSArray * _seletedDayArr;
}
@property (nonatomic, strong) UITableView *tableView;

@end

@implementation NYChoiceChuZhenTimeViewController

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
    self.navigationItem.title = @"选择出诊时间表";
    [self.tableView registerClass:[NYChuZhenTitleCell class] forCellReuseIdentifier:@"TitleCell"];
    [self.tableView registerClass:[NYChuZhenSetCountCell class] forCellReuseIdentifier:@"SetCountCell"];
    [self.tableView registerClass:[NYChoiceChuZhenTimeCell class] forCellReuseIdentifier:@"ChoiceChuZhenTimeCell"];
    [self.tableView registerClass:[NYChoiceWeekCell class] forCellReuseIdentifier:@"ChoiceWeekCell"];

    _dataArray = [NSMutableArray arrayWithCapacity:7];
    
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

#pragma mark - 获取数据
- (void)loadData
{
    [PPHTTPRequest GetJieZhenZhouQiInfoWithParameters:nil success:^(id response) {
        [self.tableView.mj_header endRefreshing];
        
        if ([response[@"code"] integerValue] == 0) {
            NSArray * listArr = response[@"data"][@"diagnoseDates"];
            
            [_dataArray removeAllObjects];
            for (NSDictionary *datc in listArr) {
                NYJieZhenZhouQiModel *doctorModel = [NYJieZhenZhouQiModel mj_objectWithKeyValues:datc];
                [_dataArray addObject:doctorModel];
            }
        }
        [self.tableView reloadData];
    } failure:^(NSError *error) {
        [self.tableView.mj_header endRefreshing];
        MYALERT(@"获取失败");
    }];
}

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView
{
    return 3;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    if (section == 0) {
        return 2;
    }else if (section == 1){
        return 3;
    }
    return 3;
}

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath
{
    if (indexPath.section == 0) {
        if (indexPath.row == 0) {
            return 50;
        }else if(indexPath.row == 1){
            return 300;
        }
    }else if (indexPath.section == 1){
        return 50;
    }else if (indexPath.section == 2){
        return 50;
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
        return 120;
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
        
        UIButton * putBtn = [UIButton buttonWithType:UIButtonTypeCustom];
        [putBtn setBackgroundColor:MAINCOLOR];
        [putBtn setTitle:@"提交" forState:UIControlStateNormal];
        [bgView addSubview:putBtn];
        
        putBtn.sd_layout
        .leftSpaceToView(bgView, 20)
        .topSpaceToView(bgView, 50)
        .rightSpaceToView(bgView, 20)
        .heightIs(50);
        
        putBtn.sd_cornerRadius = @5;
        [putBtn addTarget:self action:@selector(clickPutBtn:) forControlEvents:UIControlEventTouchUpInside];
        
        return bgView;
    }
    return [UIView new];
}

#pragma mark - 点击提交按钮
- (void)clickPutBtn:(UIButton *)button
{
    [_amCountTF resignFirstResponder];
    [_pmCountTF resignFirstResponder];
    
    NSMutableArray * muA = [NSMutableArray array];
    for (int i = 0; i < _seletedDayArr.count; i++) {
        NYMyView * view = _seletedDayArr[i];
        if (view.isSeleted == YES) {
            NYJieZhenZhouQiModel * model = _dataArray[i];
            [muA addObject:model.date];
        }
    }
    
    if (muA.count == 0) {
        MYALERT(@"请选择接诊时间");
        return;
    }
    
    
    NSString * amStartString = [_amStartTimeTF.text stringByTrimmingCharactersInSet:[NSCharacterSet whitespaceAndNewlineCharacterSet]];
    
    NSString * amEndString = [_amEndTimeTF.text stringByTrimmingCharactersInSet:[NSCharacterSet whitespaceAndNewlineCharacterSet]];
    
    NSString * pmStartString = [_pmStartTimeTF.text stringByTrimmingCharactersInSet:[NSCharacterSet whitespaceAndNewlineCharacterSet]];
    
    NSString * pmEndString = [_pmEndTimeTF.text stringByTrimmingCharactersInSet:[NSCharacterSet whitespaceAndNewlineCharacterSet]];
    
    NSString * amCountString = [_amCountTF.text stringByTrimmingCharactersInSet:[NSCharacterSet whitespaceAndNewlineCharacterSet]];
    
    
    NSString * pmCountString = [_pmCountTF.text stringByTrimmingCharactersInSet:[NSCharacterSet whitespaceAndNewlineCharacterSet]];
    
    
    if (amStartString.length == 0 && pmStartString.length == 0) {
        MYALERT(@"请选择开始时间");
        return;
    }
    
    if (amEndString.length == 0 && pmEndString.length == 0) {
        MYALERT(@"请选择结束时间");
        return;
    }
    
    if (amCountString.length == 0 && pmCountString.length == 0) {
        MYALERT(@"请输入接诊人数");
        return;
    }
    
    [SVProgressHUD showWithStatus:nil];
    [SVProgressHUD setDefaultMaskType:(SVProgressHUDMaskTypeClear)];
    
    WEAKSELF
    NSDictionary * dict = @{
                            @"moringBegin":_amStartTimeTF.text.length==0?@"":_amStartTimeTF.text,
                            @"moringEnd":_amEndTimeTF.text.length==0?@"":_amEndTimeTF.text,
                            @"morningNum":_amCountTF.text.length==0?@"":@([_amCountTF.text integerValue]),
                            @"dates":muA,
                            @"afternoonBegin":_pmStartTimeTF.text.length==0?@"":_pmStartTimeTF.text,
                            @"afternoonEnd":_pmEndTimeTF.text.length==0?@"":_pmEndTimeTF.text,
                            @"afternoonNum":_pmCountTF.text.length==0?@"":@([_pmCountTF.text integerValue]),
                            };
    
    [PPHTTPRequest postAddJieZhenTimeInfoWithParameters:dict success:^(id response) {
        [SVProgressHUD dismiss];
        if ([response[@"code"] integerValue] == 0) {
            MYALERT(@"提交成功");
            [weakSelf.navigationController popViewControllerAnimated:YES];
        }else{
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
            NYChuZhenTitleCell * cell = [tableView dequeueReusableCellWithIdentifier:@"TitleCell"];
            cell.titleLB.text = @"接诊周期";
            return cell;
        }else if (indexPath.row == 1){
            NYChoiceWeekCell * cell = [tableView dequeueReusableCellWithIdentifier:@"ChoiceWeekCell"];
            cell.weekDayArray = [_dataArray copy];
            cell.clickChoiceWeek = ^(NSArray * _Nonnull cellViewArray) {
                _seletedDayArr = cellViewArray;
            };
            return cell;
        }
    }else if (indexPath.section == 1){
        if (indexPath.row == 0) {
            NYChuZhenTitleCell * cell = [tableView dequeueReusableCellWithIdentifier:@"TitleCell"];
            cell.titleLB.text = @"上午";
            return cell;
        }else if (indexPath.row == 1){
            NYChoiceChuZhenTimeCell * cell = [tableView dequeueReusableCellWithIdentifier:@"ChoiceChuZhenTimeCell"];
            _amStartTimeTF = cell.startTimeTF;
            _amStartTimeTF.delegate = self;
            _amStartTimeTF.tag = 1;
            
            _amEndTimeTF = cell.endTimeTF;
            _amEndTimeTF.delegate = self;
            _amEndTimeTF.tag = 2;
            return cell;
        }else if (indexPath.row == 2){
            NYChuZhenSetCountCell * cell = [tableView dequeueReusableCellWithIdentifier:@"SetCountCell"];
            _amCountTF = cell.countTF;
            return cell;
        }
    }else if (indexPath.section == 2){
        if (indexPath.row == 0) {
            NYChuZhenTitleCell * cell = [tableView dequeueReusableCellWithIdentifier:@"TitleCell"];
            cell.titleLB.text = @"下午";
            return cell;
        }else if (indexPath.row == 1){
            NYChoiceChuZhenTimeCell * cell = [tableView dequeueReusableCellWithIdentifier:@"ChoiceChuZhenTimeCell"];
            _pmStartTimeTF = cell.startTimeTF;
            _pmStartTimeTF.delegate = self;
            _pmStartTimeTF.tag = 3;

            _pmEndTimeTF = cell.endTimeTF;
            _pmEndTimeTF.delegate = self;
            _pmEndTimeTF.tag = 4;

            return cell;
        }else if (indexPath.row == 2){
            NYChuZhenSetCountCell * cell = [tableView dequeueReusableCellWithIdentifier:@"SetCountCell"];
            _pmCountTF = cell.countTF;
            return cell;
        }
    }
    return nil;
}

- (BOOL)textFieldShouldBeginEditing:(UITextField *)textField
{
    if (textField.tag == 1 || textField.tag == 2) { //
        // 自定义多列字符串
        NSArray *dataSource = @[@[@"00", @"01", @"02", @"03", @"04", @"05", @"06",@"07", @"08", @"09", @"10", @"11", @"12"], @[@"00", @"30"]];
        NSArray *defaultSelArr = [textField.text componentsSeparatedByString:@":"];
        [BRStringPickerView showStringPickerWithTitle:@"选择时间" dataSource:dataSource defaultSelValue:defaultSelArr isAutoSelect:YES themeColor:nil resultBlock:^(id selectValue) {
            textField.text = [NSString stringWithFormat:@"%@:%@", selectValue[0], selectValue[1]];
        } cancelBlock:^{
            NSLog(@"点击了背景视图或取消按钮");
        }];
    }else if (textField.tag == 3 || textField.tag == 4){
        // 自定义多列字符串
        NSArray *dataSource = @[@[@"13",@"14", @"15", @"16", @"17", @"18", @"19", @"20",@"21", @"22", @"23"], @[@"00", @"30"]];
        NSArray *defaultSelArr = [textField.text componentsSeparatedByString:@":"];
        [BRStringPickerView showStringPickerWithTitle:@"选择时间" dataSource:dataSource defaultSelValue:defaultSelArr isAutoSelect:YES themeColor:nil resultBlock:^(id selectValue) {
            textField.text = [NSString stringWithFormat:@"%@:%@", selectValue[0], selectValue[1]];
        } cancelBlock:^{
            NSLog(@"点击了背景视图或取消按钮");
        }];
    }

    return NO;
}

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath
{
    [tableView deselectRowAtIndexPath:indexPath animated:YES];
}

@end
