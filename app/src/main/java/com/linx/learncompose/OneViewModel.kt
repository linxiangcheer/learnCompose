package com.linx.learncompose

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class OneViewModel: ViewModel() {

    var name by mutableStateOf("")

    /**
     * [by]关键字表示，左边的这个变量由右边进行代理/委托（具体实现）
     *
     * 加了代理myName就变成了字符串对象
     */
    var myName by mutableStateOf("rengwuxian")

}