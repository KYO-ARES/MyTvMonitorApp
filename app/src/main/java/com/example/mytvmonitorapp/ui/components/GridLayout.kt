import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mytvmonitorapp.model.Camera

/**
 * 摄像头网格布局组件
 * 根据屏幕大小自动调整每行显示的摄像头数量
 */
@Composable
fun CameraGrid(
    cameras: List<Camera>,
    modifier: Modifier = Modifier,
    onCameraClick: (Camera) -> Unit = {},
    focusedCameraId: String? = null
) {
    // 根据摄像头数量计算最佳列数 (4x6, 3x8等)
    val columns = when {
        cameras.size <= 4 -> 2
        cameras.size <= 9 -> 3
        cameras.size <= 16 -> 4
        cameras.size <= 24 -> 6
        else -> 6
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(columns),
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(cameras) { camera ->
            CameraView(
                camera = camera,
                onClick = { onCameraClick(camera) },
                isFocused = camera.id == focusedCameraId
            )
        }
    }
}