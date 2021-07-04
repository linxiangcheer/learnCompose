package com.linx.learncompose.note

/**
 * Modifier全解析  2021/6/30
 *
 * [ComposedModifier]
 * composed() Modifier的工厂
 *
 * [LayoutModifier]
 * animateContentSize() 对控件做个标记，控件尺寸有变化的时候会以动画的形式膨胀/缩小
 *
 * paddingFrom() 设置padding的时候不是对控件的边缘padding,而是对其他基准线进行padding,对文字的Baseline做padding
 * paddingFromBaseline()  内部的基准线（文字底部）
 *
 * aspectRatio() 内部控件的宽高比
 *
 * 对内部进行测量的时候,让内部尽量的小/尽量的大,不是具体的值
 *       MinIntrinsicWidthModifier/ MaxIntrinsicWidthModifier
 * width(intrinsicSize: IntrinsicSize)
 * height(intrinsicSize: IntrinsicSize)
 * requiredWidth(intrinsicSize: IntrinsicSize)
 * requiredHeight(intrinsicSize: IntrinsicSize)
 *
 * offset(x: Dp = 0.dp, y: Dp = 0.dp) 在测量完成之后再对内部的内容进行偏移
 * absoluteOffset(x: Dp = 0.dp, y: Dp = 0.dp)
 *
 * offset(offset: Density.() -> IntOffset) 给出一个计算方式，返回值就是offset偏移值
 * absoluteOffset(offset: Density.() -> IntOffset)
 *
 * padding()多个
 * padding(paddingValues: PaddingValues) 和padding作用差不多，写法不一样
 *
 * 控件大小布满父布局，不指定具体大小
 * fillMaxSize()
 * fillMaxWidth()
 * fillMaxHeight()
 *
 * height(height: Dp) / heightIn(min: Dp, max: Dp)
 * width(width: Dp) / widthIn(min: Dp, max: Dp)
 * requiredHeight(height: Dp) / requiredHeightIn(min: Dp, max: Dp)
 * requiredSize(width: Dp, height: Dp) / requiredSize(size: Dp) /
 *   requiredSizeIn(minWidth: Dp = Dp.Unspecified, minHeight: Dp = Dp.Unspecified,
 *      maxWidth: Dp = Dp.Unspecified, maxHeight: Dp = Dp.Unspecified)
 * requiredWidth(width: Dp) / requiredWidthIn(min: Dp, max: Dp)
 * size(width: Dp, height: Dp) / size(size: Dp) /
 *   sizeIn(minWidth: Dp = Dp.Unspecified, minHeight: Dp = Dp.Unspecified,
 *      maxWidth: Dp = Dp.Unspecified, maxHeight: Dp = Dp.Unspecified)
 *
 * Compose控件默认宽高就是wrap，所以这里跟xml的wrap不是一个东西
 * 忽略父控件给的最小/大值,默认是忽略最小值,如果想忽略最大值就要自己写
 * wrapContentSize()
 * wrapContentWidth()
 * wrapContentHeight()
 *
 * defaultMinSize() 控件从左往右设置的时候，有可能右边的控件有自己的最小值，但是我不想要用我的最小值，直接用对方的最小值
 *
 * scroll() private 让控件具有滚动、滑动功能
 *   horizontalScroll() / verticalScroll()
 *
 * CoreTextFieId()
 *
 * zindex(zindex: Float) 改变绘制顺序，原本谁先添加谁先绘制
 *
 * graphicsLayer(block: GraphicsLayerScope.() -> Unit) 一般并不会用，它像是在做自定义控件的时候做一个离屏缓冲
 *   alpha() 增加透明度
 *   clip(shape: Shape) 切割
 *   clipToBounds() 切割
 *   rotate(degrees: Float) 旋转
 *   scale(scaleX: Float, scaleY: Float) 缩放
 *   shadow() 增加阴影
 *   toolingGraphicsLayer() 做测试用，显示出一个额外的层，方便观察
 *
 * 移动
 * graphicsLayer(
 *   scaleX: Float = 1f,
 *   scaleY: Float = 1f,
 *   alpha: Float = 1f,
 *   translationX: Float = 0f,
 *   translationY: Float = 0f,
 *   shadowElevation: Float = 0f,
 *   rotationX: Float = 0f,
 *   rotationY: Float = 0f,
 *   rotationZ: Float = 0f,
 *   cameraDistance: Float = DefaultCameraDistance,
 *   transformOrigin: TransformOrigin = TransformOrigin.Center,
 *   shape: Shape = RectangleShape,
 *   clip: Boolean = false
 *   )
 *
 * layout() 自定义的测量与布局 —— 自定义View
 *
 * paint() 用来画图的
 *  @Composable Image()
 *  @Composable Icon()
 *
 *
 * [DrawModifier] —— 自定义绘制用这个
 * background() 背景颜色、背景刷子,简单的背景规则
 *
 * indication() 一般也不用,给目前的显示内容做一个标记,例如做一个波纹效果
 *   @Composable Button()等等
 *
 * drawBehind() 自定义绘制,用Canvas绘制背景
 *
 * drawWithCache() 自己往上面画东西
 *  border() 内容有一个边缘线,带圆边/不带原边
 *
 *
 * [OnRemeasuredModifier] 当你进行重新自我测量的时候用
 * onSizeChanged() 当尺寸改变的时候就会得到这个回调
 *
 *
 * [RemeasurementModifier] 动态布局的时候使用
 * LazyColumn()
 * LazyRow()
 *
 *
 * [OnGloballyPositionedModifier] 全局的位置测量，就是一旦发生了重新测量，不管结果变没变，都会得到这个回调
 * onGloballyPositioned() 好处,可以得到你相对于窗口(windows)的位置; 坏处是会被调用很多次，做重工作的时候要做好缓存纪录;例如如果位置没发生改变就不做工作
 *
 *
 * [ParentDataModifier] 为父控件提供数据的modifier
 *  BoxScope.Modifier.align(alignment: Alignment)
 *  BoxScope.Modifier.matchParentSize()
 *
 *  RowScope.Modifier.weight()
 *  ColumnScope.Modifier.weight()
 *
 * 里面可能会有多条基准线，按照BaseLine对齐
 *  ColumnScope.Modifier.align(alignment: Alignment.Horizontal)
 *  RpwScope.Modifier.align(alignment: Alignment.Vertical)
 *
 *  ColumnScope.Modifier.alignBy()
 *  RowScope.Modifier.alignBy()
 *
 *  RpwScope.Modifier.alignByBaseline()
 *
 *  layoutId(layoutId: Any) 可能做内部测量的时候测的比较细，但是定位不到他们，这时候先给它们起个名字，再去定位
 *
 *
 * [PointerInputModifier] 自定义触摸
 * pointerInteropFilter(requestDisallowInterceptTouchEvent: (RequestDisallowInterceptTouchEvent)? = null
 *   , onTouchEvent: (MotionEvent) -> Boolean)
 *
 * printerInpit() —— 自定义触摸
 *   combinedClickable()
 *     draggable() 多个 让一个控件能被拖动
 *       scrollable()
 *         CoreTextFieId()
 *     @Composable Slider()
 *   clickable()
 *   transformable()
 *     toggleable() 切换成可用/不可用
 *     triStateToggleable()
 *   ClickableText()
 *
 *   longPressDragGestureFilter()(internal)
 *     @Composable CoreTextFiedId()
 *
 *   mouseDragGestureDetector()(internal)
 *     @Composable CoreTextFieId()
 *
 *   mouseDragGestureFilter()(internal)
 *     @Composable CoreText()
 *
 *   tapPressTextFieIdModifier()(internal)
 *     @Composable CoreTextFieId()
 *
 *
 *  [FocusModifier]
 *  focusModifier() 让一个控件具有焦点,可以被选中
 *
 *  focusRequester(focusRequester: FocusRequester) 传入focusRequester对象可以控制让谁获取到焦点
 *
 *  focusOrder(focusOrderReceiver: FocusOrderReceiver.() -> Unit) 当你按上下左右（电视）这些按键的时候，下一个会选中谁靠的是它们的顺序,靠这个方法手动调整
 *
 *  onFocusEvent(onFocusEvent: (FocusState) -> Unit) 指焦点状态有改变的时候会有回调
 *
 *  onKeyEvent(onKeyEvent: (KeyEvent) -> Boolean) 按键,例如返回键什么的
 *  onPreviewKeyEvent(onPreviewKeyEvent: (KeyEvent) -> Boolean)
 *
 *
 *  [NestedScrollModifier]
 *  nestedScroll(connection: NestedScrollConnection, dispatcher: NestedScrollDispatcher? = null) 专门用来做嵌套滑动的
 *
 *
 *  [SemanticsModifier] 两个作用，一是实现无障碍功能； 而是用semantics做测试的工作
 *  clearAndSetSemantice(properties: (SemanticsPropertyReceiver.() -> Unit))
 *  semantics(mergeDescendants: Boolean = false, properties: (SemanticsPropertyReceiver.() -> Unit))
 *
 *
 *
 *
 *
 *
 *
 */




















