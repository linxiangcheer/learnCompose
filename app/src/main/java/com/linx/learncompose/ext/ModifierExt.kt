package com.linx.learncompose.ext

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

/**
 * 未读红点，角标
 * [drawWithContent]  跟内容一起绘制
 * [show] 是否显示
 */
fun Modifier.unRead(show: Boolean): Modifier = this.drawWithContent {

    //先绘制内容，再绘制角标，角标再上面
    drawContent()

    if (show) {
        drawIntoCanvas { canvas ->
            //画笔paint
            val paint = Paint().apply {
                color = Color.Red
            }
            /**
             * 偏移 位置 宽位置：宽度-1 高位置：1
             * 第二个参数 半径
             */
            canvas.drawCircle(
                Offset(size.width - 1.dp.toPx(), 1.dp.toPx()),
                5.dp.toPx(),
                paint
            )
        }
    }

}

/**
 * 未读红点，角标
 * [badge] 根据主题判断显示的颜色
 * [drawWithContent]  跟内容一起绘制
 * [show] 是否显示
 */
fun Modifier.unRead(show: Boolean, badge: Color): Modifier = this.drawWithContent {

    //先绘制内容，再绘制角标，角标再上面
    drawContent()

    if (show) {
        drawIntoCanvas { canvas ->
            //画笔paint
            val paint = Paint().apply {
                color = badge
            }
            /**
             * 偏移 位置 宽位置：宽度-1 高位置：1
             * 第二个参数 半径
             */
            canvas.drawCircle(
                Offset(size.width - 1.dp.toPx(), 1.dp.toPx()),
                5.dp.toPx(),
                paint
            )
        }
    }

}

/**
 * 带动画效果的页面偏移(入场、退场)
 */
fun Modifier.percentOffsetX(percent: Float): Modifier = this.layout { measurable, constraints ->
    //用来自定义布局的layout
    /**
     * 下方等于没写
     */
//                val placeable = measurable.measure(constraints)
//                layout(placeable.width, placeable.height) {
    //测量完成，placeRelative偏移量
//                    placeable.placeRelative(0, 0)
//                }

    val placeable = measurable.measure(constraints)
    layout(placeable.width, placeable.height) {
        //测量完成
//                    val offset = if (viewModel.chatting) 0 else placeable.width
        //placeRelative接收整型，所以需要转成Int
        val offset = (percent * placeable.width).roundToInt()
        placeable.placeRelative(offset, 0)
    }

}