package com.linx.learncompose.note

/**
 * 2021/6/22 22:13  [02]
 *
 *
 * 声明式UI —— 只需要对UI的元素进行一次性的声明,并且不手动更新UI
 * val name by remember { mutableStateOf("Hello") }
 *
 * 对于老项目增加compose支持,新增一个Compose项目，然后将Compose项目的配置代码copy到老项目里去
 *
 * Compose界面必须在@Composable函数内写
 *
 * Compose特性：独立于平台(不依赖Android平台),只有底层依赖Android原生; 更简单地实现预览功能(归功于前者);
 *
 * 用矢量描述的图 —— 矢量图，描述规则，占内存小，消除锯齿现象 ImageVector (xml)
 * 位图 —— 像素点，ImageBitmap (png)
 * Compose有painterResource，生成Painter类型的参数，给ImageVector就生成包含ImageVector的对象，给ImageBitmap就生成包含ImageBitmap的对象
 *
 * github.com/google/accompanist好几个库的组合，[accompanist]伴奏者，围绕compose做的一堆辅助工具
 *
 * Flutter利用NDK和底层的渲染引擎进行交互,缺点是没法和原生系统进行交互
 *
 * 布局
 * [Row] 左右结构
 * [Column] 上下结构
 *
 * [Modifier]
 *
 * androidx.compose
 * [Compose]有6个组(每个组下会有多个包)，从下层到上层分别为(上层依照顺序依赖下层)
 * [material] (Material Design)设计风格 FloatingActionButton Button(用Material Design风格设计出来的按钮)
 * [foundation] 提供一套相对完整的UI体系,基本的视图控件, 包括Column() Row() Image()
 * [animation] 动画(有了UI之后就可以做动画)
 * [ui] 提供和UI相关的最基础的功能，绘制、测量、布局、触摸反馈这些功能的最底层支持,包括所有控件最后都会调用的Layout()函数
 * [runtime] 整个compose最底层的概念模型，包括数据结构、状态转换机制、State和基于State的MutableState、remember
 * [compiler] kotlin 编译器插件 不在dependencies内配置,在composeOptions内配置
 *
 * 一般引用同名的包就会包含其他依赖
 * android.compose.ui:ui:compose_version
 * [两个例外]
 * [ui-tooling] 提供一些和UI相关的工具，例如预览功能[@Preview]
 * [material-icons-extended(扩展)] [icons-core(核心,已经在material内依赖)] 提供Material Design的一些矢量图标 这两个是ui组内的东西
 * [material-icons-extended(扩展)] 依赖了 [icons-core(核心,已经在material内依赖)]; [icons-core(核心,已经在material内依赖)]依赖了ui组
 *
 * [三条原则]
 * 1、一般写代码的时候依赖最上面的material就够了
 * 2、[ui-tooling] 提供一些和UI相关的工具，例如预览功能[@Preview] 需要单独列出来
 * 3、[material-icons-extended(扩展)] 需要单独列出来
 *
 *
 */
