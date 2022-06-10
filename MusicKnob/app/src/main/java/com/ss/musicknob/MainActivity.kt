package com.ss.musicknob

import android.os.Bundle
import android.view.MotionEvent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ss.musicknob.ui.theme.MusicKnobTheme
import kotlin.math.PI
import kotlin.math.atan2
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MusicKnobTheme {
                // A surface container using the 'background' color from the theme
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xff101010))
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .border(1.dp, Color.Green, RoundedCornerShape(10.dp))
                            .padding(30.dp)
                    )
                    {
                        var volume by remember { mutableStateOf(0f) }
                        val barCount = 20

                        MusicKnob(modifier = Modifier.size(100.dp)) {
                            volume = it
                        }

                        Spacer(modifier = Modifier.width(20.dp))

                        VolumeBar(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(30.dp),
                            activeBar = (barCount * volume).roundToInt(),
                            countBar = barCount)

                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Composable
fun VolumeBar(
    modifier: Modifier = Modifier,
    activeBar: Int = 0,
    countBar: Int = 0,
) {
    BoxWithConstraints(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        val barWith = remember { constraints.maxWidth / (2f * countBar) }

        Canvas(modifier = modifier) {
            for (i in 0 until countBar) {
                drawRoundRect(
                    color = if (i <= activeBar) Color.Green else Color.DarkGray,
                    topLeft = Offset(i * barWith * 2f + barWith / 2f, 0f),
                    size = Size(barWith, constraints.maxHeight.toFloat()),
                    cornerRadius = CornerRadius(5f)
                )
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MusicKnob(
    modifier: Modifier = Modifier,
    limitingAngle: Float = 25f,
    onValueChange: (Float) -> Unit,
) {
    var rotation by remember { mutableStateOf(limitingAngle) }
    var touchX by remember { mutableStateOf(0f) }
    var touchY by remember { mutableStateOf(0f) }
    var centerX by remember { mutableStateOf(0f) }
    var centerY by remember { mutableStateOf(0f) }

    Image(
        painter = painterResource(id = R.drawable.music_knob),
        contentDescription = "Music knob",
        modifier = modifier
            .fillMaxSize()
            .onGloballyPositioned {
                val windowBounds = it.boundsInWindow()
                centerX = windowBounds.size.width / 2f
                centerY = windowBounds.size.height / 2f
            }
            .pointerInteropFilter { event ->
                touchX = event.x
                touchY = event.y
                val angle = -atan2(centerX - touchX, centerY - touchY) * (180f / PI).toFloat()

                when (event.action) {
                    MotionEvent.ACTION_DOWN,
                    MotionEvent.ACTION_MOVE,
                    -> {
                        if (angle !in -limitingAngle..limitingAngle) {
                            val fixedAngle = if (angle in -180f..-limitingAngle) {
                                360f + angle
                            } else {
                                angle
                            }
                            rotation = fixedAngle

                            val percent = (fixedAngle - limitingAngle) / (360f - 2 * limitingAngle)
                            onValueChange(percent)
                            true
                        } else false
                    }
                    else -> false
                }
            }
            .rotate(rotation)

    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MusicKnobTheme {
        var volume by remember {
            mutableStateOf(0f)
        }

        MusicKnob(
            modifier = Modifier.size(100.dp)
        ) {
            volume = it
        }
    }
}