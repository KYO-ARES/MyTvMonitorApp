import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mytvmonitorapp.model.Camera  // 确保导入模型类
import com.example.mytvmonitorapp.model.Tab    // 确保导入模型类

@Composable
fun FullScreenView(
    cameraId: String,
    navController: NavController,
    viewModel: MainViewModel = hiltViewModel()
) {
    // 1. 状态收集（解决StateFlow问题）
    val tabsState = viewModel.tabs.collectAsState()
    val tabs = tabsState.value

    // 2. 安全查找摄像头（解决所有类型推断问题）
    val camera: Camera? = remember(cameraId) {
        tabs
            ?.mapNotNull { tab: Tab ->  // 显式指定类型
                tab.cameras?.firstOrNull { camera: Camera ->  // 显式指定类型
                    camera.id == cameraId
                }
            }
            ?.firstOrNull()
    }

    // 3. 导航处理
    LaunchedEffect(cameraId) {
        if (camera == null) navController.popBackStack()
    }

    // 4. 安全显示
    if (camera != null) {
        CameraView(
            camera = camera,
            modifier = Modifier.fillMaxSize(),
            isFocused = true
        )
    }
}