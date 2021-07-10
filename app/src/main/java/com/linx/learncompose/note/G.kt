package com.linx.learncompose.note


/**
 * 2021/7/6  -- 能自定义View吗
 * 传统的自定义View在Compose里的等价物
 *
 * 易 -> 难
 * 绘制、布局、触摸
 *
 * [View系统的绘制]
 * 重写onDraw(Canvas)
 * onDraw的super.onDraw(canvas)之前写绘制代码就是绘制顺序
 *
 * [Compose]
 * Modifier.drawWithContent{} 和内容一起绘制，我们要自行安排绘制顺序
 * drawContent() 绘制原本的内容
 *
 * Modifier.drawBehind{} 专门绘制背景,不用绘制原本的内容，因为这个是背景,会自动调用drawContent
 *
 * drawRect 绘制矩形
 * drawCircle 绘制圆
 *
 * Canvas {} 完全自定义View，不继承任何
 *
 *
 * [布局]
 * Modifier.layout() 只适用于修改内部组件的位置偏移和内部组件的尺寸,不能修改内部组件的内部组件
 *
 * layout() 摆放内部组件的函数
 *
 * Modifier讲究顺序,右边的Modifier会先应用,而左边的Modifier效果会应用在右边的Modifier效果之上
 *
 * Layout() 函数
 *
 * Intrinsic: 内在特性
 * [Intrinsic Measurement] -- 固有特性测量;  禁止重复测量的同时引入的这个函数,可以在正式测量之前做固有特性测量  内部内容的最大值/最小值
 *      —————— 如果我限制你的宽为xxx(最大/最小)，那么你最多/最少会是多高； 如果我限制你的高为xxx(最大/最小)，那么你最多/最少会是多宽
 * Compose内，只能测量一次 measurable.measure(constraints), 多次测量会报错
 *
 * 传统测量环节会有试探性的测量,所以可能会有多次测量
 *
 *
 * [触摸反馈]
 * 指的是自己设置触摸反馈的规则，然后实现出来
 * 传统： 重写onTouchEvent()普通的 \onInterceptTouchEvent() ViewGroup拦截子View \ dispatchTouchEvent()根的触摸反馈但是会破坏比较底层的东西(拦截)
 *
 *
 * LazyColumn和Column(Modifier.verticalScroll())对比，LazyColumn是懒加载，只加载显示出来的东西，所以更省内存
 *
 * RecyclerView有缓存机制\ScrollView没有
 *
 */