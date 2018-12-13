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

@interface NYChoiceChuZhenTimeViewController ()<UITableViewDelegate,UITableViewDataSource,UITextFieldDelegate>
{
    HHTextField * _amStartTimeTF;
    HHTextField * _amEndTimeTF;
    
    HHTextField * _pmStartTimeTF;
    HHTextField * _pmEndTimeTF;
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
            return cell;
        }
    }
    return nil;
}

- (BOOL)textFieldShouldBeginEditing:(UITextField *)textField
{
    // 自定义多列字符串
    NSArray *dataSource = @[@[@"00", @"01", @"02", @"03", @"04", @"05", @"06",@"07", @"08", @"09", @"10", @"11", @"12", @"13",@"14", @"15", @"16", @"17", @"18", @"19", @"20",@"21", @"22", @"23"], @[@"00", @"30"]];
    NSArray *defaultSelArr = [textField.text componentsSeparatedByString:@":"];
    [BRStringPickerView showStringPickerWithTitle:@"选择时间" dataSource:dataSource defaultSelValue:defaultSelArr isAutoSelect:YES themeColor:nil resultBlock:^(id selectValue) {
        textField.text = [NSString stringWithFormat:@"%@:%@", selectValue[0], selectValue[1]];
    } cancelBlock:^{
        NSLog(@"点击了背景视图或取消按钮");
    }];

    return NO;
}

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath
{
    [tableView deselectRowAtIndexPath:indexPath animated:YES];
}

@end
