package com.linx.learncompose

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.linx.learncompose.ui.theme.WeComposeTheme

class MainActivity : ComponentActivity() {

    private val intentO by lazy {
        Intent(this, OneActivity::class.java)
    }

    private val intentT by lazy {
        Intent(this, TwoActivity::class.java)
    }

    private val intentThree by lazy {
        Intent(this, ThreeActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeComposeTheme() {
                //Text底层是Canvas的drawText()
//                Text("扔物线")

//                Image(
//                    painterResource(id = R.drawable.ic_launcher_background),
//                    contentDescription = "喂喂喂",
//                    Modifier.padding(8.dp)
//                )
                //网络加载图片
//                CoilImage(
//                    "https://cdn.alzashop.com/ImgWashx?fd=f3&cd=GPX1066b1",
//                    contentDescription = "Coil Image"
//                )

                Column {
                    /**
                     * [Column] 上下结构
                     *
                     * [Modifier]
                     * [padding] 和margin统一了，不存在margin
                     * 内边距，在background之后设置padding = padding
                     * 外边距，在background之前设置padding = margin
                     *
                     * [Modifier] 代码顺序会影响执行效果
                     * [RoundedCornerShape] 圆角
                     *
                     * [Modifier.clip] 可以切任何东西  [CircleShape]切成圆形
                     * [Modifier.height]高度 [Modifier.width]宽度 统一 = [Modifier.size]宽高
                     * 不写宽高就等于默认 wrap_content
                     *
                     * [Modifier.fillMaxSize] [Modifier.fillMaxWidth] [Modifier.fillMaxHeight] 布满的宽高
                     *
                     * [fontSize] 文字尺寸是函数参数
                     * [color] 颜色也是函数参数
                     *
                     * 判断是在Modifier设置还是函数参数设置
                     * 通用的属性设置用[Modifier] 背景色、边距、尺寸
                     * 专项的设置用函数参数（文字尺寸、文字颜色是文字控件才有的）
                     *
                     * [clickable]点击事件如果不想在外边距中触发，就要写在两个padding的中间
                     */
                    val mainViewModel: MainViewModel = viewModel()
                    Column(
                        Modifier
                            .padding(8.dp)
                            .clickable {
                                mainViewModel.i += 1
                                mainViewModel.change.value = "点击整个页面一次 ${mainViewModel.i}"
                                startActivity(intentO)
                            }
                            .background(Color.Red, RoundedCornerShape(8.dp))
                            .padding(8.dp)
                    ) {
                        Text(
                            text = mainViewModel.change.value,
                            Modifier
                                .padding(8.dp)
                                .background(Color.Green)
                                .padding(8.dp),
                            fontSize = 16.sp,
                            color = Color.Blue
                        )
                        Image(
                            painter = painterResource(id = R.drawable.ic_launcher_background),
                            contentDescription = "照片",
                            Modifier
                                .size(100.dp)
                                .clip(CircleShape)
                        )
                        Button(
                            onClick = {
                                mainViewModel.i += 1
                                mainViewModel.change.value = "点击按钮一次 ${mainViewModel.i}"
                            },
                            Modifier
                                .padding(8.dp)
                                .background(Color.White)
                                .padding(8.dp)
                        ) {
                            /**
                             * Button默认是一个空格，需要手动将文字写入按钮中
                             * 里面的布局可以自定义
                             * [Icon]纯色
                             * [Image]图片
                             */
                            Image(
                                painter = painterResource(id = R.drawable.ic_launcher_background),
                                contentDescription = "喂喂喂"
                            )
                            Text(text = "智力+10")
                        }
                        /**
                         * RecyclerVew = [LazyColumn]和[LazyRow]
                         * [LazyColumn]上下结构的列表
                         * [LazyRow]左右结构的列表
                         */
                        val list = listOf<String>("1", "2", "3", "4", "5", "6", "7")
                        LazyColumn(
                            Modifier
                                .padding(10.dp)
                                .background(Color.White)
                                .padding(10.dp)
                                .width(20.dp)
                                .height(50.dp)
                                .fillMaxWidth()
                        ) {
                            items(list) { item ->
                                Text(text = item,
                                    Modifier
                                        .clickable {
                                            startActivity(intentT)
                                        }
                                        .padding(10.dp)
                                        .background(Color.White)
                                        .padding(10.dp))
                            }
                        }
                        LazyRow(
                            Modifier
                                .padding(10.dp)
                                .background(Color.White)
                                .padding(10.dp)
                                .height(20.dp)
                                .width(20.dp)
                                .clickable {
                                    startActivity(intentThree)
                                }
                        ) {
                            items(list) { item ->
                                Text(text = item)
                            }
                        }
                    }

                    /**
                     * [Row]左右结构
                     */
                    Row {
                        Text(text = "你好")
                        Image(
                            painter = painterResource(id = R.drawable.ic_launcher_background),
                            contentDescription = "照片"
                        )
                    }
                    ZhuKai("Hello") // Hello:这是个短名字
                    ZhuKai("RengWuXian") // RengWuXian:这是个长名字
                    ZhuKai("ZhuKai") // ZhuKai:这是个长名字
                }

            }
        }
    }

}

/**
 * 自定义Composable的使用姿势
 * 里面只调用一个Composable
 * （ Column/ Row /Box 嵌套 ）
 * 不要Text() Image()这样
 */
@Composable
private fun ZhuKai(name: String) {
    Column(Modifier.background(Color.Red)) {

        /**
         * 加上[remember]，可以保证如果name不变的话 remember内的函数不会走，提高性能
         */
        val calculatedName = remember(name) {
            name + if (name.length > 5) {
                ": 这是个长名字"
            } else {
                ": 这是个短名字"
            }
        }

        Text(calculatedName)
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "扔物线"
        )
    }
}

@Composable
fun MainScreen() {

}

@Preview
@Composable
fun Test() {
    WeComposeTheme {
        Text("扔物线")
    }
}