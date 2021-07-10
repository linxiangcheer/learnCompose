package com.linx.learncompose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Compose和传统View系统的交互
 */
class SixViewModel: ViewModel() {

    val num1 = MutableLiveData<Int>(1)

}