//
//  NYChuZhenTimeListViewController.m
//  Doctor
//
//  Created by niuyao on 2018/12/3.
//  Copyright © 2018 joymates. All rights reserved.
//

#import "NYChuZhenTimeListViewController.h"
#import "NYChuZhenTitleCell.h"
#import "NYChuZhenListCell.h"
#import "NYChuZhenListModel.h"
#import "NYChoiceChuZhenTimeViewController.h"

@interface NYChuZhenTimeListViewController ()<UITableViewDelegate,UITableViewDataSource,DZNEmptyDataSetSource,DZNEmptyDataSetDelegate>
{
    NSMutableArray * _dataArray;
}

@property (nonatomic, strong) UITableView *tableView;

@end

@implementation NYChuZhenTimeListViewController

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
    }
    return _tableView;
}

- (void)viewDidLoad {
    [super viewDidLoad];
    self.view.backgroundColor = [UIColor whiteColor];
    self.navigationItem.title = @"接诊时间表";
    [self.tableView registerClass:[NYChuZhenTitleCell class] forCellReuseIdentifier:@"TitleCell"];
    [self.tableView registerClass:[NYChuZhenListCell class] forCellReuseIdentifier:@"ListCell"];

    [self setRightItem];
    
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
    [PPHTTPRequest GetJieZhenTimeInfoWithParameters:nil success:^(id response) {
        [self.tableView.mj_header endRefreshing];
        
        if ([response[@"code"] integerValue] == 0) {
            NSArray * listArr = response[@"data"][@"diagnoseList"];
            
            [_dataArray removeAllObjects];
            for (NSDictionary *datc in listArr) {
                NYChuZhenListModel *doctorModel = [NYChuZhenListModel mj_objectWithKeyValues:datc];
                [_dataArray addObject:doctorModel];
            }
        }
        [self.tableView reloadData];
    } failure:^(NSError *error) {
        [self.tableView.mj_header endRefreshing];
        MYALERT(@"获取失败");
    }];
}



#pragma mark - 右边item
- (void)setRightItem
{
    UIBarButtonItem * item = [[UIBarButtonItem alloc] initWithImage:[UIImage imageNamed:@"schedule_white"] style:UIBarButtonItemStylePlain target:self action:@selector(clickRightItem:)];
    self.navigationItem.rightBarButtonItem = item;
}

- (void)clickRightItem:(UIBarButtonItem *)item
{
    NYChoiceChuZhenTimeViewController * vc = [[NYChoiceChuZhenTimeViewController alloc] init];
    [self.navigationController pushViewController:vc animated:YES];
}

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView
{
    return _dataArray.count;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    if (section == 0) {
        return 2;
    }
    return 1;
}

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath
{
    if (indexPath.section == 0) {
        if (indexPath.row == 0) {
            return 50;
        }else{
            return 95;
        }
    }
    return 95;
}

- (CGFloat)tableView:(UITableView *)tableView heightForHeaderInSection:(NSInteger)section
{
    return 0.0001f;
}

- (CGFloat)tableView:(UITableView *)tableView heightForFooterInSection:(NSInteger)section
{
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
        if (indexPath.row == 0) {
            NYChuZhenTitleCell * cell = [tableView dequeueReusableCellWithIdentifier:@"TitleCell"];
            cell.titleLB.text = @"接诊时间";
            return cell;
        }else if (indexPath.row == 1){
            NYChuZhenListCell * cell = [tableView dequeueReusableCellWithIdentifier:@"ListCell"];
            cell.chuZhenListModel = _dataArray[indexPath.section];
            return cell;
        }
    }
    NYChuZhenListCell * cell = [tableView dequeueReusableCellWithIdentifier:@"ListCell"];
    cell.chuZhenListModel = _dataArray[indexPath.section];
    return cell;
}

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath
{
    [tableView deselectRowAtIndexPath:indexPath animated:YES];
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
