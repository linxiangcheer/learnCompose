//package com.linx.learncompose.composeBottom
//
///*
// * Copyright 2021 The Android Open Source Project
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *      http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
///**
// * Compose底层
// */
//import android.view.ViewGroup
//import androidx.activity.ComponentActivity
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.CompositionContext
//import androidx.compose.ui.platform.ComposeView
//import androidx.lifecycle.ViewTreeLifecycleOwner
//import androidx.lifecycle.ViewTreeViewModelStoreOwner
//import androidx.savedstate.ViewTreeSavedStateRegistryOwner
//
///**
// * Composes the given composable into the given activity. The [content] will become the root view
// * of the given activity.
// *
// * This is roughly equivalent to calling [ComponentActivity.setContentView] with a [ComposeView]
// * i.e.:
// *
// * ```
// * setContentView(
// *   ComposeView(this).apply {
// *     setContent {
// *       MyComposableContent()
// *     }
// *   }
// * )
// * ```
// *
// * @param parent The parent composition reference to coordinate scheduling of composition updates
// * @param content A `@Composable` function declaring the UI contents
// */
///**
// * [ComponentActivity]相当于当今时代的Activity
// * [AppCompatActivity]\[FragmentActivity]也是继承[ComponentActivity]
// */
//public fun ComponentActivity.setContent(
//    parent: CompositionContext? = null,
//    content: @Composable () -> Unit
//) {
//
//    /**
//     * 最底部(根)的View叫drcorView,用window.decorView取出
//     * 内部的结构是LinearLayout 内部分别为AppBar(Action Bar旧) + 主布局(Id为: android.R.id.content)
//     */
//    val existingComposeView = window.decorView
//            //找到id为content的ViewGroup
//        .findViewById<ViewGroup>(android.R.id.content)
//        /**
//         * 取出第1个子View  如果setContentView(R.layout.activity_main),那么第一个子View就是布局名为activity_main的View
//         * as?代表如果取到了才强转为ComposeView，如果取不到就为null
//         */
//        .getChildAt(0) as? ComposeView
//
//    /**
//     * 为空判断
//     * 如果为空就用[with]函数：临时进入(existingComposeView)对象的上下文
//     */
//    if (existingComposeView != null) with(existingComposeView) {
//        /**
//         * [AbstractComposeViewA]是[ComposeView]的父类
//         *
//         * [parent] 默认值为null
//         */
//        setParentCompositionContext(parent)
//        /**
//         * 这里进入了existingComposeView对象的上下文，即为existingComposeView.setContent(content)
//         *
//         *
//         */
//        setContent(content)
//    } else
//    /**
//     * 为空就创建一个ComposeView对象，然后
//     * [apply]进入调用者的上下文
//     */
//        ComposeView(this).apply {
//        // Set content and parent **before** setContentView
//        // to have ComposeView create the composition on attach
//        setParentCompositionContext(parent)
//        setContent(content)
//        // Set the view tree owners before setting the content view so that the inflation process
//        // and attach listeners will see them already present
//            /**
//             * [setOwners]可以忽略,帮lifecycle创建Owner,帮别的库修复bug
//             */
//        setOwners()
//            /**
//             * 把刚创建的ComposeView设置进了Activity
//             */
//        setContentView(this, DefaultActivityContentLayoutParams)
//    }
//}
//
//private val DefaultActivityContentLayoutParams = ViewGroup.LayoutParams(
//    ViewGroup.LayoutParams.WRAP_CONTENT,
//    ViewGroup.LayoutParams.WRAP_CONTENT
//)
//
///**
// * These owners are not set before AppCompat 1.3+ due to a bug, so we need to set them manually in
// * case developers are using an older version of AppCompat.
// * 帮lifecycle创建Owner,帮别的库修复bug
// */
//private fun ComponentActivity.setOwners() {
//    val decorView = window.decorView
//    if (ViewTreeLifecycleOwner.get(decorView) == null) {
//        ViewTreeLifecycleOwner.set(decorView, this)
//    }
//    if (ViewTreeViewModelStoreOwner.get(decorView) == null) {
//        ViewTreeViewModelStoreOwner.set(decorView, this)
//    }
//    if (ViewTreeSavedStateRegistryOwner.get(decorView) == null) {
//        ViewTreeSavedStateRegistryOwner.set(decorView, this)
//    }
//}
