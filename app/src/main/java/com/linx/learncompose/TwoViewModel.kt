package com.linx.learncompose

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel

class TwoViewModel : ViewModel() {

    /**
     * [by] 只能一对一
     * 一对多的时候需要修改为 =
     */

    var num by mutableStateOf(1)

    var nums by mutableStateOf(mutableListOf(1, 2, 3))

    var flag by mutableStateOf(1)

    val numsList = mutableStateListOf(1, 2, 3)

    val map = mutableStateMapOf<Int, String>(0 to "Zero", 1 to "One", 2 to "Two", 3 to "Three")

}