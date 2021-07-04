package com.linx.learncompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 *
 * Modifier  2021/6/30
 *
 * [companion object] 在接口/类内部创建一个单例对象 类似于Java的静态方法和静态变量
 *
 * [Live Literals] Compose一些常量的修改可以实时在界面上更新
 *
 * [Modifier.then] 把Modifier各个参数合并起来,是所有Modifier的默认实现
 * 合起来后变成CombinedModifier(paddingModifier, backgroundModifier, sizeModifier...),合并成一个链式的Modifier
 *
 * Modifier最简单的单例实现是没有什么操作的
 *
 * kotlin的[with]函数能临时切换上下文（增加上下文）
 *
 * Modifier、CombinedModifier、Modifier里所有的接口和实现类都直接或间接地继承了Element这个接口
 * Element主要是为了给CombinedModifier使用的
 * [Element.any]给出一个条件判断，看看Modifier内有没有至少一个符合条件的，有的话就返回true
 * [Element.all]给出一个条件判断，看看Modifier是不是全都符合条件，如果全都符合就返回true
 * [Element.foldIn]给出一个初始值，给出一个对这个初始值进行计算的算法，这样就可以不断地把Element链上初始值，先加入的Modifier先加上初始值
 * [Element.foldOut]和foldIn相反,从右往左(先进后出),拿出去装箱
 *
 * Modifier.padding(1.dp).background(Color.Red) 从右往左测量,先测量background测量padding
 * 左边会覆盖掉右边的值
 * 测试1：Modifier.size(80.dp).size(160.dp) —— 结果是80dp
 * 测试2：Modifier.size(160.dp).size(80.dp) —— 结果是160dp
 * requiredSize(Height\Width) 强制的宽高 不受左边右边影响
 *
 *
 * Elemnet的各种子接口
 * 1、[LayoutModifier]对界面元素的位置和尺寸做测量和修饰的Modifier
 *      - PaddingModifier [measure]测量
 *      - SizeModifier
 *      - requiredSize 强制的宽高
 *
 *
 *
 *
 * background
 *
 */
class ThreeActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            /**
             * requiredSize(Height\Width) 强硬的size 不听左边右边的影响
             * 但是左边/右边也是requiredSize,就会受到影响
             */
            ThreeScreen(Modifier.size(80.dp).requiredHeight(160.dp))
        }
    }

}

/**
 * 开放一个modifier函数给外界，让外界传入
 *
 * 第一个有默认值的参数有个特权就是一定可以不用写函数名称
 */
@Composable
fun ThreeScreen(modifier: Modifier = Modifier) {
    Box(modifier.background(Color.Blue).padding(4.dp)) {

    }
}











