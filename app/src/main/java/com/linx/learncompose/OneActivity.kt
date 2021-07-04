package com.linx.learncompose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.linx.learncompose.ui.theme.WeComposeTheme
import com.linx.learncompose.ui.theme.WeTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * 状态订阅和自动更新
 *
 * 编译器查看会修改代码逻辑，把可能会被重新调用的代码块加上返回值并包起来，包起来的内容包含执行代码本身，然后存在某个地方标记着
 * 当重新执行的条件达成时，返回值里的代码块会被拿出来重新执行 —— ReCompose(重新执行)
 * 和@Composable一个函数的代码块都会重新执行
 * 所以mutableState一般不能和要改变的模块写在同一个函数内,不然mutableState初始化过程也会被ReCompose包起来重新执行
 * 除非使用remember函数，然后ReCompose重新执行的时候不会重新创建变量，只会在内存中将缓存拿出来，如果没有改变的话就不会重新赋值
 *
 * remember可以预防变量反复初始化
 * 使用在可能进入ReCompose环境的变量(无法判断)
 * 结论： 所有创建在@Composable注解函数内的mutableState变量全都包上一层remember
 *
 * 带参数remember 括号内有键(key)
 * 如果key没变就不需要计算，如果变了就需要
 *
 *
 * Compose可以具有无"内部"状态这个特点(不是绝对) —— 下方的[Hello]函数就是有"内部"状态的(里面有一个仅仅它自己能看到的常量)
 * 状态——TextView中的text，TextView.getText获取文字状态
 * Compose的文字是变量，直接获取变量数据就可以了，这就是所谓的无"内部"状态
 *
 * Compose如何获取别的控件的状态
 * 无"内部"状态的组件 —— 直接拿给它状态的外部变量
 * 有"内部"状态的组件 —— [拿不到]，把它们写成变量就可以拿到 —— State Hoisting 状态上提
 *
 * 一个有状态的控件就是把状态关在控件里，无状态的控件拿到外部（变成参数），就可以共享了
 *
 * 如果某些状态需要让别的控件知道，那么就把它放在父控件里，不够的话再State Hoisting状态上提，原则是能不往上提就不往上提，能访问到它的范围越小越好，出错几率会小
 *
 */
class OneActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeComposeTheme {
                Column {
                    OneScreen()
                    ShowCharCount(value = "你好")
                }
            }
        }
    }

    @Composable
    fun OneScreen() {
        val viewModel: OneViewModel = viewModel()
        var nn by remember {
            mutableStateOf("rengwuxian")
        }
        Text(nn, Modifier
            .fillMaxSize()
            .clickable {
                Toast
                    .makeText(this, "Hello", Toast.LENGTH_SHORT)
                    .show()
                lifecycleScope.launch {
                    //3秒钟
                    delay(3000)
                    nn = "ZhuKai"
                }
            }
            .background(Color.White), color = Color.Blue)
    }

}

@Composable
fun ShowCharCount(value: String) {
    val key2 = "haha"
    /**
     * 带参数remember 括号内有键(key)
     * 如果key没变就不需要计算，如果变了就需要
     */
    val str = remember(value, key2) {
        value.length + key2.length
    }
    Text("字符串的长度是 $str")
}

@Composable
fun Hello() {
    Text("Hello")
}
