//
//  NYChoiceWeekCell.m
//  Doctor
//
//  Created by niuyao on 2018/12/3.
//  Copyright © 2018 joymates. All rights reserved.
//

#import "NYChoiceWeekCell.h"
#import "NYMyView.h"
#import "NYJieZhenZhouQiModel.h"

@implementation NYChoiceWeekCell
{
    NSMutableArray * _muArray;
}
- (instancetype)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier
{
    if (self == [super initWithStyle:style reuseIdentifier:reuseIdentifier]) {
        self.selectionStyle = UITableViewCellSelectionStyleNone;
        _muArray = [NSMutableArray array];
        [self setup];
    }
    return self;
}

- (void)setup
{
    for (id obj in self.contentView.subviews) {
        if ([obj isKindOfClass:[UIView class]]) {
            [(UIView *)obj removeFromSuperview];
        }
    }
    
    
    //总列数
    int totalColumns = 3;
    //间隙
    CGFloat margin = 15;
    
    //每一格的尺寸
    CGFloat cellW = (NYScreenW-4*margin)*0.3333;
    CGFloat cellH = 80;
    
    
    [_muArray removeAllObjects];
    
    //    根据格子个数创建对应的框框
    for(int index = 0; index< 7; index++) {
        
        NYMyView *cellView = [[NYMyView alloc ]init ];
        cellView.backgroundColor = BGCOLOR;
        cellView.clipsToBounds = YES;
        cellView.tag = index;
        cellView.layer.cornerRadius = 5.0f;
        cellView.userInteractionEnabled = YES;
        
        // 计算行号  和   列号
        int row = index / totalColumns;
        int col = index % totalColumns;
        //根据行号和列号来确定 子控件的坐标
        CGFloat cellX = margin + col * (cellW + margin);
        CGFloat cellY = margin + row * (cellH + margin);
        cellView.frame = CGRectMake(cellX, cellY, cellW, cellH);
        
        [cellView addGestureRecognizer:[[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(clickZheKouView:)]];
        
        // 添加到view 中
        [self.contentView addSubview:cellView];
        
        [_muArray addObject:cellView];
        
        UILabel * timeLB = [[UILabel alloc] init];
        timeLB.tag = 1001;
        timeLB.adjustsFontSizeToFitWidth = YES;
        timeLB.font = FONT(17);
        timeLB.textColor = COLOR_LOW;
        timeLB.textAlignment = NSTextAlignmentCenter;
        [cellView addSubview:timeLB];
        
        timeLB.sd_layout
        .leftSpaceToView(cellView, 0)
        .topSpaceToView(cellView, 10)
        .rightSpaceToView(cellView, 0)
        .heightIs(30);
        
//        timeLB.text = @"11-19";
        
        
        //星期
        UILabel * offLB = [[UILabel alloc] init];
        offLB.tag = 1002;
        offLB.adjustsFontSizeToFitWidth = YES;
        offLB.font = FONT(17);
        offLB.textColor = COLOR_LOW;
        offLB.textAlignment = NSTextAlignmentCenter;
        [cellView addSubview:offLB];
        
        offLB.sd_layout
        .leftSpaceToView(cellView, 0)
        .topSpaceToView(timeLB, 0)
        .rightSpaceToView(cellView, 0)
        .bottomSpaceToView(cellView, 10);
//        if (index == 0) {
//            offLB.text = @"周一";
//        }else if (index == 1){
//            offLB.text = @"周二";
//        }else if (index == 2){
//            offLB.text = @"周三";
//        }else if (index == 3){
//            offLB.text = @"周四";
//        }else if (index == 4){
//            offLB.text = @"周五";
//        }else if (index == 5){
//            offLB.text = @"周六";
//        }else if (index == 6){
//            offLB.text = @"周日";
//        }
    }
}

- (void)clickZheKouView:(UITapGestureRecognizer *)tap
{
    
    NYMyView * cellView = (NYMyView *)tap.view;
    UILabel * timeLB = [cellView viewWithTag:1001];
    UILabel * weekLB = [cellView viewWithTag:1002];

    if (cellView.isSeleted) {
        cellView.isSeleted = NO;
        cellView.backgroundColor = BGCOLOR;
        timeLB.textColor = COLOR_LOW;
        weekLB.textColor = COLOR_LOW;
    }else{
        cellView.isSeleted = YES;
        cellView.backgroundColor = MAINCOLOR;
        timeLB.textColor = [UIColor whiteColor];
        weekLB.textColor = [UIColor whiteColor];
    }
    
    if (_clickChoiceWeek) {
        _clickChoiceWeek([_muArray copy]);
    }
}

- (void)setWeekDayArray:(NSArray *)weekDayArray
{
    _weekDayArray = weekDayArray;
    
    for (int i = 0; i< weekDayArray.count; i++) {
        NYJieZhenZhouQiModel * model = weekDayArray[i];
        
        NYMyView * view = _muArray[i];
        UILabel * timeLB = [view viewWithTag:1001];
        UILabel * weekLB = [view viewWithTag:1002];
        
        timeLB.text = model.monthDay;
        weekLB.text = model.week;
    }
}

@end
