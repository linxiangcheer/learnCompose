package com.linx.learncompose.note

/**
 * 2021/6/26
 *
 * 自定义@Composable里面只有一个带@Composable的函数 （ Column/ Row /Box 嵌套 ）  不要Text() Image()这样
 *
 * @Composable的规则
 * 1、所有加了@Composable注解的函数，都需要在别的加了@Composable的函数里面才能调用
 * 2、所有内部没调用任何其他@Composable函数的函数，都没必要加上这个注解，因为没意义
 *
 *
 * 自定义Composable在传统的布局中约等于xml+自定义View
 * 这也就是自定义Composable的使用场景
 * 还有绘制、布局、触摸反馈的定制
 * onDraw() onMeasure() onLayout() onTouchEvent() onInterceptTouchEvent()
 *
 *
 */