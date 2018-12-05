//
//  HHTextField.h
//  ecsp
//
//  Created by hong7 on 15/6/6.
//  Copyright (c) 2015年 hong7. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface HHTextField : UITextField


/*The inset or outset margins for the edges of the text content drawing rectangle.
Use this property to resize and reposition the effective drawing rectangle for the text content. You can specify a
different value for each of the four insets (top, left, bottom, right). A positive value shrinks, or insets, that
edge—moving it closer to the center of the button. A negative value expands, or outsets, that edge. Use the
`UIEdgeInsetsMake` function to construct a value for this property.
The default value is `UIEdgeInsetsZero`.
*/
@property (nonatomic) UIEdgeInsets textEdgeInsets;

/**
 The inset or outset margins for the edges of the clear button drawing rectangle.
 Use this property to resize and reposition the effective drawing rectangle for the clear button content. You can
 specify a different value for each of the four insets (top, left, bottom, right), but only the top and right insets are
 respected. A positive value will move the clear button farther away from the top right corner. Use the
 `UIEdgeInsetsMake` function to construct a value for this property.
 The default value is `UIEdgeInsetsZero`.
 */
@property (nonatomic) UIEdgeInsets clearButtonEdgeInsets;

/**
 The inset or outset margins for the edges of the view assigned to `rightView`.
 Use this property to resize and reposition the effective drawing rectangle for the right view content. You can
 specify a different value for each of the four insets (top, left, bottom, right), but only the top and right insets are
 respected. A positive value will move the view farther away from the top right corner. Use the
 `UIEdgeInsetsMake` function to construct a value for this property.
 The default value is `UIEdgeInsetsZero`.
 */
@property (nonatomic) UIEdgeInsets rightViewInsets;

/**
 The inset or outset margins for the edges of the view assigned to `leftView`.
 Use this property to resize and reposition the effective drawing rectangle for the left view content. You can
 specify a different value for each of the four insets (top, left, bottom, right), but only the top and right insets are
 respected. A positive value will move the view farther away from the top right corner. Use the
 `UIEdgeInsetsMake` function to construct a value for this property.
 The default value is `UIEdgeInsetsZero`.
 */
@property (nonatomic) UIEdgeInsets leftViewInsets;


@property (nonatomic,assign) NSInteger minLength;

@property (nonatomic,assign) NSInteger maxLength;
@end
