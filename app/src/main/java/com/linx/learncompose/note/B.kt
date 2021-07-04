package com.linx.learncompose.note

/**
 * 2021/6/24
 *
 * [03]Compose底层原理介绍
 *
 * 看compose是如何将下面这段代码显示到界面上的
 * setContent {
 *    Text("Hello")
 * }
 *
 * [ComponentActivity]相当于当今时代的Activity
 * [AppCompatActivity]\[FragmentActivity]也是继承[ComponentActivity]
 *
 * [AtomicReference] Java线程安全包装类
 *
 *
 *
 * [SlotTable] Compose用来记录结构、元素改动信息的类
 * [Snapshot] 现在状态和下一状态事件的差异
 *
 * Text("Hello")先解析成一个[SlotTable]的平面结构再解析成[LayoutNot]这种树状结构,为了数据的更新
 * 调用处： Text("Hello")
 * |到
 * SlotTable(实际上的数据结构) 数据有变化的时候以最低的成本做更新
 * |到
 * 表面显示: LayoutNote
 *
 *
 *
 * ComposeView外层到内层
 * [ComposeView]
 *   [AndroidComposeView] //显示、触摸 真正干活的View
 *
 * ——Compose View的层
 * [decorView(FragmentLayout)]
 *     [LinearLayout]
 *         [android.R.id.content]
 *             [ComposeView]
 *                 [AndroidComposeView]  每一个androidComposeView都会创建自己的LayoutNode
 *                     root: [LayoutNode]  布局、绘制、触摸反馈 [Column](Modifier, MeasurePolicy)
 *                        子的[LayoutNode] —— (实际是Text("Hello")/Image()) 创建过程中会根据Text{} Image{} Icon{} Column{} Row{} Box{} —— Text(Modifier, MeasurePolicy)... 创建一个一个的LayoutNode
 *                            如果Column里还有子View就会再创建子LayoutNode的子[LayoutNode] —— [Image](Modifier, MeasurePolicy)
 *
 */
class B {

    //内部类，只有set
    var a = 0
        private set

    fun aa() {

        a = a
        a = 1
    }
}

fun bb() {
    val a = B().a
//    B().a = 0
}





