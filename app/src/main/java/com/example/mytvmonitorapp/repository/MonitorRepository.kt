import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MonitorRepository {
    // 确保这是Flow类型
    val tabs: Flow<List<Tab>> = flow {
        // 模拟数据获取
        emit(emptyList()) // 替换为实际数据源
    }

    // 或者如果有异步操作
    /*
    val tabs: Flow<List<Tab>> = flow {
        emit(getTabsFromNetwork())
    }

    private suspend fun getTabsFromNetwork(): List<Tab> {
        // 实际网络请求
        return emptyList()
    }
    */
}

// 数据模型
data class Tab(val id: String, val name: String)