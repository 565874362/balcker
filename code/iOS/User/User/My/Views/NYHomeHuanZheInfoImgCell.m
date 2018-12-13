//
//  NYHomeHuanZheInfoImgCell.m
//  Doctor
//
//  Created by niuyao on 2018/12/5.
//  Copyright © 2018 joymates. All rights reserved.
//

#import "NYHomeHuanZheInfoImgCell.h"

@implementation NYHomeHuanZheInfoImgCell

- (instancetype)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier
{
    if (self == [super initWithStyle:style reuseIdentifier:reuseIdentifier]) {
        self.selectionStyle = UITableViewCellSelectionStyleNone;
        [self setup];
    }
    return self;
}

- (void)setup
{
    for (id obj in self.contentView.subviews) {
        if ([obj isKindOfClass:[UIImageView class]]) {
            [(UIImageView *)obj removeFromSuperview];
        }
    }
    
    
    //总列数
    int totalColumns = 3;
    //间隙
    CGFloat margin = 15;
    
    //每一格的尺寸
    CGFloat cellW = (NYScreenW-4*margin)*0.33;
    CGFloat cellH = cellW;
    
    
    //    根据格子个数创建对应的框框
    for(int index = 0; index< 3; index++) {
        
        UIImageView *cellView = [[UIImageView alloc ]init ];
        cellView.image = [UIImage imageNamed:@"placeholderImage"];
        cellView.clipsToBounds = YES;
        cellView.tag = index;
//        cellView.layer.cornerRadius = 5.0f;
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
        
    }
}

- (void)clickZheKouView:(UITapGestureRecognizer *)tap
{
    if (_clickImg) {
        _clickImg(tap.view.tag,tap.view);
    }
}
@end
