//
//  NYNeedCheckViewController.m
//  Doctor
//
//  Created by niuyao on 2018/12/10.
//  Copyright © 2018 joymates. All rights reserved.
//

#import "NYNeedCheckViewController.h"
#import "NYNeedCheckCell.h"
#import "NYNeedCheckModel.h"

@interface NYNeedCheckViewController ()<UITableViewDelegate,UITableViewDataSource>

@property (nonatomic, strong) UITableView *tableView;


@end

@implementation NYNeedCheckViewController

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
    self.navigationItem.title = @"需做检查";
    [self.tableView registerClass:[NYNeedCheckCell class] forCellReuseIdentifier:@"Cell"];
    
    [self initBottomUI];
}

#pragma mark - 初始化底部UI
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
    
    //共计价格
    UILabel * priceLB = [[UILabel alloc] init];
    priceLB.textAlignment = NSTextAlignmentRight;
    priceLB.adjustsFontSizeToFitWidth = YES;
    priceLB.textColor = COLOR_BIG;
    priceLB.font = FONT(16);
    [bgView addSubview:priceLB];
    
    priceLB.sd_layout
    .leftSpaceToView(bgView, 15)
    .topSpaceToView(bgView, 0)
    .bottomSpaceToView(bgView, 0);
    
    [priceLB setSingleLineAutoResizeWithMaxWidth:NYScreenW*0.3];
    
    priceLB.text = @"共计: 25.00元";

    
    UIButton * doneButton = [UIButton buttonWithType:UIButtonTypeCustom];
    [doneButton setTitle:@"确认" forState:UIControlStateNormal];
    [doneButton setBackgroundColor:MAINCOLOR];
    [doneButton setTitleColor:[UIColor whiteColor] forState:UIControlStateNormal];
    [bgView addSubview:doneButton];
    
    doneButton.sd_layout
    .heightIs(35)
    .rightSpaceToView(bgView, 15)
    .centerYEqualToView(bgView)
    .widthIs(90);
    
    doneButton.sd_cornerRadius = @17.5;
    
    
    [doneButton addTarget:self action:@selector(clickDoneButton:) forControlEvents:UIControlEventTouchUpInside];
}

#pragma mark - 点击确认按钮
- (void)clickDoneButton:(UIButton *)button
{
    
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
    return 60;
}

- (CGFloat)tableView:(UITableView *)tableView heightForHeaderInSection:(NSInteger)section
{
    return 0.0001f;
}

- (CGFloat)tableView:(UITableView *)tableView heightForFooterInSection:(NSInteger)section
{
    return 60;
}

-(UIView *)tableView:(UITableView *)tableView viewForHeaderInSection:(NSInteger)section {
    return [UIView new];
}

- (UIView *)tableView:(UITableView *)tableView viewForFooterInSection:(NSInteger)section {
    return [UIView new];
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    NYNeedCheckCell * cell = [tableView dequeueReusableCellWithIdentifier:@"Cell"];
    return cell;
}

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath
{
    [tableView deselectRowAtIndexPath:indexPath animated:YES];
}

@end
