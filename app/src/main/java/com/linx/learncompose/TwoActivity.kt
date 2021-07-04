package com.linx.learncompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.linx.learncompose.ui.theme.WeComposeTheme

class TwoActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeComposeTheme {
                val viewModel: TwoViewModel = viewModel()
                TwoScreen(viewModel)
            }
        }
    }

}

@Composable
fun TwoScreen(viewModel: TwoViewModel) {
    Column {
        Text(
            "当前的值是 ${viewModel.num}",
            Modifier
                .clickable {
                    viewModel.num++
                    viewModel.flag++
                }
                .padding(10.dp)
                .background(Color.Blue)
                .padding(10.dp)
                .fillMaxWidth(), color = Color.Red
        )
        /**
         * [last]返回最后一个元素
         */
        Button(
            onClick = {
                /**
                 * [toMutableList]会创建一个跟原来的值一样的新的对象,然后add()
                 * 又改变东西又做赋值操作
                 * (不不够优雅) 用mutableStateListOf代替
                 */
                viewModel.nums = viewModel.nums.toMutableList().apply {
                    add(viewModel.nums.last() + 1)
                }
            },
            Modifier
                .background(Color.Magenta)
        ) {
            Text(text = "点击加1", Modifier.padding(10.dp))
        }
        Button(onClick = {
            viewModel.numsList.add(viewModel.numsList.last() + 1)
        }) {
            Text("更加优雅的mutableStateLifeOf")
        }
        LazyColumn(
            Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .background(Color.Green)
        ) {
            items(viewModel.nums.size) { item ->
                Text(
                    text = item.toString(),
                    Modifier
                        .padding(4.dp)
                )
            }
        }
        LazyColumn(
            Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .background(Color.Red)
        ) {
            items(viewModel.numsList.size) { item ->
                Text(text = item.toString(), Modifier.padding(4.dp))
            }
        }
        Button(onClick = {
            viewModel.map[viewModel.map.size] = "New"
        }) {
            Text(text = "点击增加Map")
        }
        LazyRow(
            Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .background(Color.Gray)) {
            items(viewModel.map.size) { item ->
                Text(viewModel.map[item].toString(), Modifier.padding(4.dp))
            }
        }
    }
}