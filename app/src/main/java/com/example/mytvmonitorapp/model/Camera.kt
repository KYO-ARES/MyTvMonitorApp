package com.example.mytvmonitorapp.model

import androidx.compose.runtime.Immutable

/**
 * 摄像头数据类
 * @param id 唯一标识
 * @param name 显示名称
 * @param streamUrl 视频流地址
 * @param protocol 流媒体协议类型
 * @param isOnline 在线状态
 * @param lastActiveTime 最后活跃时间
 */
@Immutable
data class Camera(
    val id: String,
    val name: String,
    val streamUrl: String,
    val protocol: Protocol,
    val isOnline: Boolean = false,
    val lastActiveTime: Long = System.currentTimeMillis()
) {
    enum class Protocol {
        RTMP,
        WEBRTC,
        HLS
    }

    companion object {
        /**
         * 创建测试用摄像头
         */
        fun createTestCamera() = Camera(
            id = "test_cam_${System.currentTimeMillis()}",
            name = "测试摄像头",
            streamUrl = "rtmp://example.com/live/stream",
            protocol = Protocol.RTMP,
            isOnline = true
        )
    }
}