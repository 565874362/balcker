//
//  NYChoiceWeekCell.h
//  Doctor
//
//  Created by niuyao on 2018/12/3.
//  Copyright © 2018 joymates. All rights reserved.
//

#import <UIKit/UIKit.h>

NS_ASSUME_NONNULL_BEGIN

@interface NYChoiceWeekCell : UITableViewCell

@property (nonatomic,copy) void(^clickChoiceWeek)(NSArray * cellViewArray);

@property (nonatomic,strong) NSArray * weekDayArray;
@end

NS_ASSUME_NONNULL_END
