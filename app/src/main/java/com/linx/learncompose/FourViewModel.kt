package com.linx.learncompose

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel

class FourViewModel: ViewModel() {

    val big = mutableStateOf(false)

    val bigTwo = mutableStateOf(false)

    val bigThree = mutableStateOf(false)

    val bigFour = mutableStateOf(true)

}