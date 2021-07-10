package com.linx.learncompose.note

/**
 * 2021/7/10
 * Compose和传统View系统的交互s
 *
 * 至少一两年内,Compose和传统混合写
 *
 * 最需要掌握的
 * 1、怎么让Compose组件运行在View系统里面？ —— 关键类：ComposeView
 * 2、怎么让View系统运行在Compose组件内？ —— 关键类： AndroidView
 *
 * MutableLiveData数据怎么在Compose内使用 —— mutableLiveData的扩展函数.observeAsState(); 需要在Composable函数内使用
 * 需要添加androidx.compose.runtime:runtime-livedata
 *
 * Jetpack库集合 -> 有几个围绕Android开发的基本结构的 ViewModel、Lifecycle、LiveData...
 *
 * 对Compose： Android提供的对外对接其实是和Jetpack的对接 —— 页面切换: Navigation(支持Fragment和Compose)
 *
 * 协程的folw{} —— flow.collectAsState
 *
 * mutableStateOf和可订阅的flow无法转回mutableLiveData和flow; 如果有需求的话直接创建mutableLiveData和flow，然后手动转mutableStateOf和可订阅的flow
 *
 *
 *
 *
 */
