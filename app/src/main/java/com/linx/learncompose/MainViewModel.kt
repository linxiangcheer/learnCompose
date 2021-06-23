package com.linx.learncompose

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    var i = 0

    var change = mutableStateOf("喂喂喂")

}