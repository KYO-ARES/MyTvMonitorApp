import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import org.webrtc.SurfaceViewRenderer
import com.example.mytvmonitorapp.model.Camera

/**
 * 单个摄像头视图组件
 */
@Composable
fun CameraView(
    camera: Camera,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    isFocused: Boolean = false
) {
    val context = LocalContext.current
    val focusRequester = remember { FocusRequester() }
    val surfaceViewRenderer = remember { SurfaceViewRenderer(context) }

    Box(
        modifier = modifier
            .aspectRatio(16f / 9f) // 默认16:9比例，实际会根据内容自适应
            .border(
                width = if (isFocused) 4.dp else 2.dp,
                color = if (isFocused) MaterialTheme.colorScheme.primary else Color.Gray
            )
            .background(Color.Black)
            .focusRequester(focusRequester)
            .focusable()
            .padding(4.dp),
        contentAlignment = Alignment.Center
    ) {
        // 摄像头视频流渲染视图
        AndroidView(
            factory = { surfaceViewRenderer },
            modifier = Modifier.fillMaxSize()
        )

        // 摄像头名称
        Text(
            text = camera.name,
            color = Color.White,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(8.dp)
        )

        DisposableEffect(camera) {
            // 初始化WebRTC或RTMP流
            when (camera.protocol) {
                Camera.Protocol.WEBRTC -> {
                    // 初始化WebRTC
                }
                Camera.Protocol.RTMP -> {
                    // 初始化RTMP
                }
                Camera.Protocol.HLS -> {
                    // 初始化HLS
                }
            }

            onDispose {
                // 清理资源
                when (camera.protocol) {
                    Camera.Protocol.WEBRTC -> {
                        // 停止WebRTC
                    }
                    Camera.Protocol.RTMP -> {
                        // 停止RTMP
                    }
                    Camera.Protocol.HLS -> {
                        // 停止HLS
                    }
                }
            }
        }
    }
}