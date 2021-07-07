package com.linx.learncompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.layout.layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.constrainHeight
import androidx.compose.ui.unit.constrainWidth
import androidx.compose.ui.unit.dp
import kotlin.math.max
import kotlin.math.roundToInt

class FiveActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column {
                FivePreview()
                FivePreviewA()
                FivePreviewB {
                    Text(text = "hahaha")
                    Text(text = "hehehe")
                }
            }
        }
    }

}

@Composable
fun FivePreviewC() {
    /**
     * [IntrinsicSize.Min] 用最小固有尺寸（内部内容的最小值）来作为自己的尺寸
     * 也就是说这里Row的高度是内部控件最小的尺寸
     * 尽管Divider的高度是fillMaxHeight,但是Row的最高尺寸被限制为IntrinsicSize.Min
     * 所以Divider的高度是Row内部最矮的控件的高度
     *
     * [IntrinsicSize.Max] 内部内容的最大值
     * 这里Min和Max相同
     *
     * 分割线的最小高度 0 (因为分割线里面没有内容，因为不能辅助父控件做测量,而文字需要全部显示)
     * 文字的最小高度 文字的高度
     *
     * 分割线的最大高度 0 (因为分割线里面没有内容，因为不能辅助父控件做测量,而文字需要全部显示)
     * 文字的最大高度 文字的高度
     */
    Row(Modifier.height(IntrinsicSize.Min)) {
        Text(text = "text1")
        //分割线
        Divider(Modifier.width(1.dp).fillMaxHeight(), color = Color.Red)
        Text(text = "text2")
    }
}

/**
 * 自定义控件的正确姿势
 */
@Composable
fun FivePreviewB(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    /**
     * 都是content，但是Layout的content不是最后一个参数，不能像Colum那样直接写labda函数
     *
     * 尽管Layout可以随心所欲定制任何界面
     * [content]提供内部元素
     * [measurePolicy]提供内部元素的测量和布局的算法
     * 但如此一来失去了扩展性(开放性)
     *
     * Layout函数和Modifier.layout有一个区别
     * 前者的measurables是复数(内部可以有多个组件),后者的是单数（内部只有一个组件）
     *
     * 正确姿势，写一个自定义的Layout，做一个规则出来，然后动态如填入布局(使用的人想填什么填什么)
     * 填完后使用统一的规则展示
     * 1、content往外暴露
     * 2、modifier往外暴露
     *
     */
    Layout(content, modifier) { measurables, constraints ->
        //写一个简单版本的Column

        //上写接口的Column布局，所以with是所有子控件内最宽的那个
        var width = 0
        //高度是所有控件的高度之和
        var height = 0
        /**
         * [measurables]是复数，因为Layout()函数内部可能有多个组件
         */
        val placebles = mutableListOf<Placeable>()
        for (measurable in measurables) {
            //单个布局的测量
            val placeable = measurable.measure(constraints)
            //找出所有子控件内最宽的那个
            width = max(width, placeable.width)
            //控件的高度之和
            height += placeable.height
            //测量并添加到List<Placeable>内
            placebles.add(placeable)
        }
        //摆放组件
        layout(width, height) {
            //当前高度
            var currentHeight = 0
            for (placeable in placebles) {
                /**
                 * x 横坐标
                 * y 纵坐标
                 * 第一个控件摆放开始的位置是0, 0
                 */
                placeable.placeRelative(0, currentHeight)
                currentHeight += placeable.height
            }
        }
    }
}

@Preview
@Composable
fun FivePreviewA() {
    Column(
        Modifier
            .size(48.dp)
            .background(Color.Magenta)
    ) {

        Text(
            text = "哈哈",
            /**
             * [measurable] modifier修饰的组件
             * [constraints] 索要修饰组件被测量时候的尺寸限制 最大最小多宽多高
             *
             * 只适用于修改内部组件的位置偏移和内部组件的尺寸,不能修改内部组件的内部组件
             *
             * Modifier讲究顺序,右边的Modifier会先应用,而左边的Modifier效果会应用在右边的Modifier效果之上
             */
            Modifier
                .background(Color.Red)
                .layout { measurable, constraints ->
                    val padding = 8.dp
                        .toPx()
                        .roundToInt()
                    val paddedConstraints = constraints.apply {
                        //修改宽度
                        constrainWidth(maxWidth - padding * 2)
                        //修改高度
                        constrainHeight(maxHeight - padding * 2)
                    }
                    //measure是有返回值的 返回一个测量完成，可以摆放的对象
                    val placeable = measurable.measure(paddedConstraints)

                    /**
                     * 摆放内部组件的函数
                     * 传入结构的组件
                     *
                     * layout()的返回值是MeasureResult,这里写在labda最后一个，自动返回到Modifier.layout()内
                     */
                    layout(placeable.width + padding * 2, placeable.height + padding * 2) {
                        //摆放的坐标
                        placeable.placeRelative(padding, padding)
                    }
                }
                .background(Color.Green)
        )
    }
}

@Preview
@Composable
fun FivePreview() {
    Column {
        Text(text = "hahaha",
            //代码顺序决定绘制顺序
            Modifier.drawWithContent {
                //绘制矩形
                drawRect(Color.Green)
                //原本内容的绘制
                drawContent()
            }
        )
        //绘制背景，不用调用drawContent绘制原本的内容
        Text(text = "AAAA",
            Modifier
                .drawBehind {
                    //绘制圆
                    drawCircle(Color.Red)
                }
                .size(60.dp))

        /**
         * 带缓存的绘制
         * 不是内容会带缓存，因为Compose自带缓存
         *
         * [对绘制之前做的准备过程做缓存]
         * 创建和初始化一些对象
         * 不缓存的话ReCompose会影响性能
         *
         * remember也可以缓存，但是要在Compose函数内
         */
        Text(text = "BBBB", Modifier.drawWithCache {
            val path = Path()
            //...这里创建和初始化一些对象 对这部分缓存
            onDrawWithContent {  //或者onDrawBehind{}
                //绘制背景
                //绘制前景
            }
        })

        //完全自定义View，不继承任何
        Canvas(modifier = Modifier) {

        }
    }
}

//和下面的效果一样
@Composable
fun CustomCanvas() {
    Box {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "1"
        )
        Canvas(modifier = Modifier) {

        }
    }
}

//和上面的效果一样
@Composable
fun CustomDraw() {
    Image(
        painter = painterResource(id = R.drawable.ic_launcher_background),
        contentDescription = "喂喂喂", Modifier.drawWithContent {
            drawContent()
            //绘制代码
        })
}