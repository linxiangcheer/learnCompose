package com.linx.learncompose.note

/**
 * 2021/7/4
 * Compose的动画
 *
 * 当下都是属性动画，在Api11中加入
 * Compose选择开发一套新的最上层的动画Api
 *
 * animateXxxAsState()
 *
 * 生命是UI是直接操作属性，而不会去先拿到对象
 * 对于动画过程的设置也是隐式的
 *
 * 设置属性的初始值
 * 让属性以动画的形式去拿到变化值
 *
 *
 * animateDpAsState限制
 * 1、抛弃手动设置动画初始值的功能 -- 使用Animateble()
 *
 *
 * 动画使用animateXxAsState还是Animatable
 * 基于状态切换型的动画就用animateXxAsState,从一个状态切换至另一个状态,两个状态都是很确定的值
 * 详细定制的就用Animatable
 *
 * 好用的东西
 * Modifier.animateContentSize() 加载Compose,改变尺寸地时候就会以动画地形式改变
 * Crossfade(监听的变量) 当内部有两个或以上的不同的组件，一会儿显示A一会儿显示B，就用这个
 * AnimatedVisibility 显示/消失的时候是淡入淡出的效果
 *
 *
 */
