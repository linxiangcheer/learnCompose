package com.linx.learncompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.linx.learncompose.ui.theme.WeComposeTheme
import kotlinx.coroutines.launch

class FourActivity : ComponentActivity() {

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeComposeTheme {
                Column {
                    val viewModel: FourViewModel = viewModel()

                    /**
                     * mutableStateOf会给函数自动添加上MutableState，能监听到变量改变
                     * remember {} 函数可以让变量只创建一次
                     *
                     * animateXxxAsState会把值包到一个State对象里,自带remember,返回State对象,开启一个协程，渐渐改变控件的大小（动画）
                     * MutableState是State的子接口，MutableState里面的变量可以手动修改，State不能手动修改(只能等它自己修改，不能手动)
                     * 动画可以用另一个MutableState去控制它
                     *
                     * animateDpAsState
                     * animateIntAsState
                     * animateColorAsState
                     * animateFloatAsState
                     * animateValueAsState
                     * animateOffsetAsState
                     * animateRectAsState
                     * animateSizeAsState
                     * animateIntOffsetAsState
                     * animateIntSizeAsState
                     *
                     */
                    //动画
                    val size by animateDpAsState(targetValue = if (viewModel.big.value) 96.dp else 48.dp)
                    Box(modifier = Modifier
                        .size(size)
                        .background(Color.Green)
                        .clickable {
                            viewModel.big.value = !viewModel.big.value
                        }) {

                    }

                    /**
                     * 解决animateDpAsState的限制 -- 抛弃手动设置动画初始值的功能
                     * Animatable和animateDpAsState的区别
                     * animateDpAsState内部调用的就是Animatable
                     *
                     * Color/ Int/ Size ... VectorConverter类型转换
                     * TwoWayConverter转换接口
                     * AnimationVectorXD : D -> Demension(维度)
                     * 有1D\2D\3D\4D维度
                     *
                     * 初始值、目标值、时间（百分比）
                     *
                     */
                    //目前只有Color和Float不需要手动写VectorConverter
                    Animatable(Color.Blue)
                    Animatable(0f)

                    val anim = remember {
                        Animatable(48.dp, Dp.VectorConverter)
                    }
                    /**
                     * animateTo方法是一个挂起函数 suspend
                     * 这个方法要放在协程里
                     *
                     * lifecycleScop.launch创建协程没有对ReCompose做优化，所以目前不要在Compose使用，不然在ReCompose内一直启动协程
                     *
                     * [LaunchedEffect]不会自动在ReCompose内重新创建，但是可以用key来控制是否需要再重启，key的值改变的时候就重启
                     *
                     */
                    LaunchedEffect(key1 = viewModel.bigTwo.value) {
                        /**
                         * [snapTo]直接将目标变为这个值，以达到设置初始值的作用
                         */
                        anim.snapTo(if (viewModel.bigTwo.value) 144.dp else 0.dp)
                        /**
                         * 目标值
                         * [sping]回弹(弹簧)效果  DampingRatioMediumBouncy中度回弹
                         * animateTo默认就是回弹效果
                         *
                         * [tween]动画时长，基于物理模型的弹簧不支持设置动画时长
                         */
                        anim.animateTo(
                            if (viewModel.bigTwo.value) 96.dp else 48.dp,
//                            spring(Spring.DampingRatioMediumBouncy)
                            tween(2000)
                        )
                    }

                    Box(
                        modifier = Modifier
                            .size(anim.value)
                            .background(Color.Blue)
                            .clickable {
                                viewModel.bigTwo.value = !viewModel.bigTwo.value
                            }
                    ) {

                    }

                    var big by remember {
                        mutableStateOf(false)
                    }
                    val sizeThree by animateDpAsState(targetValue = if (big) 96.dp else 48.dp)
                    val cornerSize by animateDpAsState(targetValue = if (big) 8.dp else 0.dp)

                    /**
                     * 同一个过程多个属性，可以使用Transition [sizeThree]+[cornerSize]
                     * 并行地多属性动画用Transition，只需要计算一次大小，性能优化
                     *
                     * Transition做两件事
                     * 1、初次调用创建一个Transition对象
                     * 2、每次执行到这的时候都会尝试更新状态
                     *
                     * 设置[label]值,容易在动画预览中分别参数
                     *
                     */
                    val bigTransition = updateTransition(targetState = big, label = "Pig")
                    //自动渐变地更新动画状态，这个状态就是进度，false -> true ,百分比, 【只需要计算一次大小，性能优化】
                    val sizeThreeB by bigTransition.animateDp(label = "sizeThreeB") { if (it) 96.dp else 48.dp }
                    val cornerSizeB by bigTransition.animateDp(label = "cornerSizeB") { if (it) 16.dp else 0.dp }

                    Box(modifier = Modifier
                        .size(sizeThreeB)
                        //圆角  以动画的形式设置
                        .clip(RoundedCornerShape(cornerSizeB))
                        .background(Color.Red)
                        .clickable {
                            big = !big
                        })

                    /**
                     * Modifier.animateContentSize() 加载Compose,改变尺寸地时候就会以动画地形式改变
                     */
                    Column(Modifier.animateContentSize()) {
                        Box(
                            Modifier
                                .size(90.dp)
                                .background(Color.Gray)
                                .clickable { big = !big }) {

                        }
                        if (big) {
                            Box(
                                Modifier
                                    .size(90.dp)
                                    .background(Color.Magenta)
                                    .clickable { big = !big }) {

                            }
                        }
                    }

                    /**
                     * 当内部有两个或以上的不同的组件，一会儿显示A一会儿显示B，就用这个
                     */
                    Crossfade(viewModel.bigThree.value) {
                        if (it) {
                            Box(
                                Modifier
                                    .size(90.dp)
                                    .background(Color.Magenta)
                                    .clickable {
                                        viewModel.bigThree.value = !viewModel.bigThree.value
                                    })
                        } else {
                            Box(
                                Modifier
                                    .size(90.dp)
                                    .background(Color.Black)
                                    .clickable {
                                        viewModel.bigThree.value = !viewModel.bigThree.value
                                    })
                        }
                    }

                    /**
                     * 测试方法需要加OptIn
                     *
                     * 显示/消失的时候是淡入淡出的效果
                     */
                    AnimatedVisibility(visible = viewModel.bigFour.value) {
                        Box(modifier = Modifier
                            .size(50.dp)
                            .background(Color.Cyan)
                            .clickable {
                                viewModel.bigFour.value = !viewModel.bigFour.value
                            })
                    }

                }
            }
        }
    }

}

@Composable
fun FourScreen(modifier: Modifier = Modifier) {
    Column(
        modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Text(text = "第八节课")
    }
}
