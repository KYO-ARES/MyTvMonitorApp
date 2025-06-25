import org.webrtc.EglBase
import org.webrtc.SurfaceViewRenderer

/**
 * WebRTC服务封装
 */
class WebRtcService {
    private val eglBase = EglBase.create()

    fun initializeRenderer(renderer: SurfaceViewRenderer) {
        renderer.init(eglBase.eglBaseContext, null)
        renderer.setEnableHardwareScaler(true)
    }

    fun startStream(renderer: SurfaceViewRenderer, streamUrl: String) {
        // 实现WebRTC连接逻辑
        // 这里需要实现信令服务器连接、PeerConnection创建等
    }

    fun stopStream(renderer: SurfaceViewRenderer) {
        renderer.clearImage()
        renderer.release()
    }
}