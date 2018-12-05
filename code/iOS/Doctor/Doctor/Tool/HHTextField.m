//
//  HHTextField.m
//  ecsp
//
//  Created by hong7 on 15/6/6.
//  Copyright (c) 2015年 hong7. All rights reserved.
//

#import "HHTextField.h"

@implementation HHTextField

#pragma mark - UIView

- (id)initWithCoder:(NSCoder *)aDecoder {
    if ((self = [super initWithCoder:aDecoder])) {
        [self initialize];
    }
    return self;
}


- (id)initWithFrame:(CGRect)frame {
    if ((self = [super initWithFrame:frame])) {
        [self initialize];
        
        [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(textFieldDidChange:) name:UITextFieldTextDidChangeNotification object:nil];
    }
    return self;
}


-(void)dealloc {
    
    [[NSNotificationCenter defaultCenter] removeObserver:self];
}

#pragma mark - UITextField

- (CGRect)textRectForBounds:(CGRect)bounds {
    return UIEdgeInsetsInsetRect([super textRectForBounds:bounds], self.textEdgeInsets);
}


- (CGRect)editingRectForBounds:(CGRect)bounds {
    return [self textRectForBounds:bounds];
}


- (CGRect)placeholderRectForBounds:(CGRect)bounds {
    return [self textRectForBounds:bounds];
}


- (CGRect)clearButtonRectForBounds:(CGRect)bounds {
    CGRect rect = [super clearButtonRectForBounds:bounds];
    rect.origin.x += self.clearButtonEdgeInsets.right;
    rect.origin.y += self.clearButtonEdgeInsets.top;
    return rect;
}


- (CGRect)rightViewRectForBounds:(CGRect)bounds {
    CGRect rect = [super rightViewRectForBounds:bounds];
    rect.origin.x += self.rightViewInsets.right;
    rect.origin.y += self.rightViewInsets.top;
    return rect;
}


- (CGRect)leftViewRectForBounds:(CGRect)bounds {
    CGRect rect = [super leftViewRectForBounds:bounds];
    rect.origin.x += self.leftViewInsets.left;
    rect.origin.y += self.leftViewInsets.top;
    return rect;
}

#pragma mark - input control

-(NSString *)inputPrimaryLanguage {
    if ([[[UIDevice currentDevice] systemVersion] intValue]>=7) {
        return [[self textInputMode] primaryLanguage];
    }else {
#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Wdeprecated-declarations"
        return [[UITextInputMode currentInputMode] primaryLanguage];
#pragma clang diagnostic pop
    }
}

-(BOOL)isLegal {
    return [self isMinLentghLegal] && [self isMaxLentghLegal];
}

-(BOOL)isMinLentghLegal {
    if (self.maxLength == 0) return YES;
    
    if (self.text.length < self.minLength) return NO;
    return YES;
}

-(BOOL)isMaxLentghLegal {
    if (self.maxLength == 0) return YES;
    
    NSString *lang = [self inputPrimaryLanguage];
    if ([lang isEqualToString:@"zh-Hans"]) {
        
        UITextRange * selectedRange = [self markedTextRange];
        UITextPosition * position = [self positionFromPosition:selectedRange.start offset:0];
        if (!position) {
            if (self.text.length > self.maxLength) return NO;
        }
    }else {
        if (self.text.length > self.maxLength) return NO;
    }
    
    return YES;
}


-(void)truncateLegalMaxLengthText {
    [self setText:[self.text substringToIndex:self.maxLength]];
}


-(void)textFieldDidChange:(NSNotification *)notification {
    UITextField * textField = notification.object;
    
    if ([textField isEqual:self]) {
        if (![self isMaxLentghLegal]) {
            [self truncateLegalMaxLengthText];
        }
    }
}

#pragma mark - Private

- (void)initialize {
    self.textEdgeInsets = UIEdgeInsetsZero;
    self.clearButtonEdgeInsets = UIEdgeInsetsZero;
    self.leftViewInsets = UIEdgeInsetsZero;
    self.rightViewInsets = UIEdgeInsetsZero;
}
@end
