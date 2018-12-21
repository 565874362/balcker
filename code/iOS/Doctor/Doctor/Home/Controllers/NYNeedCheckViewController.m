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

@interface NYNeedCheckViewController ()<UITableViewDelegate,UITableViewDataSource,DZNEmptyDataSetSource,DZNEmptyDataSetDelegate>
{
    NSMutableArray * _dataArray;
    UILabel * _allPriceLB;
}
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
        _tableView.emptyDataSetSource = self;
        _tableView.emptyDataSetDelegate = self;
        _tableView.estimatedRowHeight = 0;
        _tableView.estimatedSectionHeaderHeight = 0;
        _tableView.estimatedSectionFooterHeight = 0;

    }
    return _tableView;
}

- (void)viewDidLoad {
    [super viewDidLoad];
    self.view.backgroundColor = [UIColor whiteColor];
    self.navigationItem.title = @"需做检查";
    [self.tableView registerClass:[NYNeedCheckCell class] forCellReuseIdentifier:@"Cell"];
    
    
    _dataArray = [NSMutableArray array];
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
    [PPHTTPRequest GetJianKangCheckInfoWithParameters:nil success:^(id response) {
        [self.tableView.mj_header endRefreshing];
        
        if ([response[@"code"] integerValue] == 0) {
            NSArray * listArr = response[@"data"][@"list"];
            
            [_dataArray removeAllObjects];
            for (NSDictionary *datc in listArr) {
                NYNeedCheckModel *doctorModel = [NYNeedCheckModel mj_objectWithKeyValues:datc];
                [_dataArray addObject:doctorModel];
            }
            
            for (NYNeedCheckModel * alreadyModel in _alreadyChoiceArr) {
                for (NYNeedCheckModel * obj in _dataArray) {
                    if ([alreadyModel.id integerValue] == [obj.id integerValue]) {
                        obj.isSeleted = YES;
                        break;
                    }
                }
            }
            
            
            [self initBottomUI];

        }else{
            MYALERT(@"暂无数据");
        }
        [self.tableView reloadData];
    } failure:^(NSError *error) {
        [self.tableView.mj_header endRefreshing];
        MYALERT(@"获取失败");
    }];
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
    
    priceLB.text = @"共计: 0.00元";
    
    _allPriceLB = priceLB;
    
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
    
    
    //初始化价格
    double allPrice = 0.0;
    for (NYNeedCheckModel * model in _dataArray) {
        if (model.isSeleted) {
            double pric = [model.price doubleValue];
            allPrice += pric;
        }
    }
    
    _allPriceLB.text = [NSString stringWithFormat:@"共计: %.2f元",allPrice];
    
}

#pragma mark - 点击确认按钮
- (void)clickDoneButton:(UIButton *)button
{
    NSMutableArray * arr = [NSMutableArray array];
    
    for (NYNeedCheckModel * model in _dataArray) {
        if (model.isSeleted) {
            [arr addObject:model];
        }
    }
    
    if (arr.count == 0) {
        return MYALERT(@"请选择需要做的检查");
    }else{
        if (_clickDoneChoicCheck) {
            _clickDoneChoicCheck([arr copy]);
        }
        [self.navigationController popViewControllerAnimated:YES];
    }
}

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView
{
    return 1;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    return _dataArray.count;
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
    cell.checkModel = _dataArray[indexPath.row];
    return cell;
}

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath
{
    [tableView deselectRowAtIndexPath:indexPath animated:YES];
    
    NYNeedCheckModel * model = _dataArray[indexPath.row];
    model.isSeleted = !model.isSeleted;
    
    double allPrice = 0.0;
    for (NYNeedCheckModel * model in _dataArray) {
        if (model.isSeleted) {
            double pric = [model.price doubleValue];
            allPrice += pric;
        }
    }
    
    _allPriceLB.text = [NSString stringWithFormat:@"共计: %.2f元",allPrice];
    
    [self.tableView reloadData];

}


#pragma mark - 空数据代理方法
//- (UIImage *)imageForEmptyDataSet:(UIScrollView *)scrollView
//{
//    return [UIImage imageNamed:@"ic_launcher"];
//}

- (NSAttributedString *)buttonTitleForEmptyDataSet:(UIScrollView *)scrollView forState:(UIControlState)state {
    NSString *text = @"暂无数据";
    NSDictionary *attributes = @{NSFontAttributeName: [UIFont boldSystemFontOfSize:17.0f],
                                 NSForegroundColorAttributeName: [UIColor lightGrayColor]};
    return [[NSAttributedString alloc] initWithString:text attributes:attributes];
}

- (BOOL)emptyDataSetShouldAllowScroll:(UIScrollView *)scrollView
{
    return YES;
}

@end
