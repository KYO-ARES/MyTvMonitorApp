import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.mytvmonitorapp.model.Camera
import com.example.mytvmonitorapp.model.Tab

// 需要在项目中定义的枚举类
enum class LayoutType {
    GRID, LIST, SINGLE
}

@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val tabs by viewModel.tabs.collectAsState()
    var selectedTabIndex by remember { mutableStateOf(0) }

    Column {
        TabRow(selectedTabIndex = selectedTabIndex) {
            tabs.forEachIndexed { index, tab ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    text = {
                        Text(
                            text = tab.name,
                            style = MaterialTheme.typography.labelLarge,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                )
            }
        }

        tabs.getOrNull(selectedTabIndex)?.let { currentTab ->
            when (currentTab.layoutType) {
                LayoutType.GRID -> CameraGrid(
                    cameras = currentTab.cameras,
                    onCameraClick = { camera ->
                        navController.navigate("fullscreen/${camera.id}")
                    }
                )
                LayoutType.LIST -> CameraList(
                    cameras = currentTab.cameras,
                    onCameraClick = { camera ->
                        navController.navigate("fullscreen/${camera.id}")
                    }
                )
                LayoutType.SINGLE -> currentTab.cameras.firstOrNull()?.let { camera ->
                    FullscreenCamera(
                        camera = camera,
                        onBack = { selectedTabIndex = maxOf(0, selectedTabIndex - 1) }
                    )
                }
            }
        }
    }
}

// 需要实现的预览组件（示例）
@Composable
fun CameraGrid(
    cameras: List<Camera>,
    onCameraClick: (Camera) -> Unit
) {
    // 实现网格布局
}

@Composable
fun CameraList(
    cameras: List<Camera>,
    onCameraClick: (Camera) -> Unit
) {
    // 实现列表布局
}

@Composable
fun FullscreenCamera(
    camera: Camera,
    onBack: () -> Unit
) {
    // 实现全屏显示
}