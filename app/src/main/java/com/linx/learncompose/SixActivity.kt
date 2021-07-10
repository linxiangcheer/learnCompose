package com.linx.learncompose

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.Colors
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.flow

/**
 * Compose和传统View系统的交互
 */
class SixActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /**
         * 怎么让Compose组件运行在View系统里面？ —— 关键类：ComposeView
         * 在传统的View里插入Compose
         */
        /*
        setContentView(R.layout.layout_main)
        val composeView = findViewById<ComposeView>(R.id.composeView)
        composeView.setContent {
            Text(text = "haha")
        }
         */


        /**
         * 怎么让View系统运行在Compose组件内？ —— 关键类： AndroidView
         * 在Compose里插入传统的View
         */
        setContent {
            val viewModel: SixViewModel = viewModel()

            //mutableLiveData转mutableStateOf
            val numInCompose by viewModel.num1.observeAsState()
            //可订阅的flow
//            val flowInCompose = flow.collectAsState(initial = 1)

            //mutableStateOf和可订阅的flow无法转回mutableLiveData和flow; 如果有需求的话直接创建mutableLiveData和flow，然后手动转mutableStateOf和可订阅的flow

            Text(text = "上面")
            SideEffect {
                //把Compose状态向外分享
            }
            AndroidView({
                /**
                 * 第一个lambdm[只会执行一次]
                 * 创建、初始化  死代码
                 */
//                View(this).apply {
//                    setBackgroundColor(android.graphics.Color.BLUE)
//                }
                ImageView(this).apply {
                    setImageResource(R.drawable.ic_launcher_background)
                }
            }, Modifier.size(48.dp)) {
                /**
                 * 第二个lambda中执行数据的交互  因为会进入ReCompose内[重复执行]
                 * 更新、设置  活代码
                 */


            }
            Box(modifier = Modifier
                .size(48.dp)
                .background(Color.Red))
        }


    }

}



