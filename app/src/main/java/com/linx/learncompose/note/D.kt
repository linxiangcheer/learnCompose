package com.linx.learncompose.note

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

/**
 * 2021/6/28
 *
 * Compose的订阅，重新赋值就会产生行为
 *
 *
 * Compose是自动更新，所以需要这些性能优化（过度更新）
 * 传统视图是主动更新，所以不会有过度更新
 *
 * 被动的Compose的性能优化
 * Kotlin编译器会在ReCompose内的函数做工作(添加参数)
 * 如果参数没有改变的话函数里面的代码就会跳过
 *
 * Heavy() —— 只会在初始化调用一遍，外面的Composable不会再调用这个函数，因为其没有参数
 * Heavy(int: Int) —— 如果参数int改变的话ReCompose就会检查int是否又改变，如果有改变的话就会重新执行Heavy函数(参数没有使用的话也不会重新执行)
 *
 * 如果在ReCompose函数内，就算参数int不是mutableStateOf，但是只要值改变的话也会进入ReCompose重新执行的函数内
 *
 *
 * 一个变量的值是否改变靠的是Compose的[Structral Equality] 就是 kotlin的双等号== ; Java的equals() ———— 结构相等(内容相等),包含引用相等
 * 另外[Referential Equality] 就是 kotlin的三等号 === ; Java的== ———— 引用相等(同一个对象)
 *
 * ReCompose会跳过 ==（结构相等）的对象,对象是val
 * 如果是var的话就不会跳过,因为这个时候会被Compose看作不可靠的类(var),Compose会使用引用相等，确保是用一个对象相同的值
 * 如果对象是val（可靠的类）,Compose就会使用结构相等,只要内容相等就可以了
 *
 * 为什么var是不可靠的类
 * 因为参数换成新对象的时候,只要他们结构相等,就不会进入ReCompose,所以var变量当下不会出现问题,但是未来可能会造成监听失效问题,无法检测稳定性
 *
 * @Stable注解[稳定、可靠],添加之后加了var的类会变得可信任，只要内容相等ReCompose会跳过执行这个类
 * data class User(var name: String) {}
 * [一般是接口才会加这个注解]
 *
 */

/**
 * 此时User这个类也会被信任，标准写法
 */
class User(name: String) {
    var name: String by mutableStateOf(name)
}

/**
 * Map的键需要保证唯一
 */
fun A() {
    val map = mutableMapOf<String, String>()
    map["hi"] = "嗨"
    map["hi"] = "哈"
    map["hi"] = "喂"
}














