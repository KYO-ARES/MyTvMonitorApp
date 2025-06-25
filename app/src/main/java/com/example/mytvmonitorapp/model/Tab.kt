package com.example.mytvmonitorapp.model

import androidx.compose.runtime.Immutable

/**
 * 监控标签页数据类
 * @param id 唯一标识符
 * @param name 标签页显示名称
 * @param cameras 摄像头列表（默认空列表）
 * @param layoutType 布局类型（默认网格布局）
 */
@Immutable
data class Tab(
    val id: String,
    val name: String,
    val cameras: List<Camera> = emptyList(),
    val layoutType: LayoutType = LayoutType.GRID
) {
    /**
     * 布局类型枚举
     */
    enum class LayoutType {
        GRID,    // 网格布局（默认）
        LIST,    // 列表布局
        SINGLE   // 单画面布局
    }

    companion object {
        /**
         * 创建默认标签页（用于空状态）
         */
        fun default() = Tab(
            id = "default_tab",
            name = "默认标签",
            cameras = emptyList()
        )
    }
}