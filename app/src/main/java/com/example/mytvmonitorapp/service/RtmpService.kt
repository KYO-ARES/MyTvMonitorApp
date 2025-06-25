import android.content.Context
import android.view.SurfaceView
import com.pedro.rtplibrary.rtmp.RtmpCamera1
import com.pedro.rtmp.utils.ConnectCheckerRtmp
import org.webrtc.SurfaceViewRenderer

class RtmpService(private val context: Context) : ConnectCheckerRtmp {
    private var rtmpCamera: RtmpCamera1? = null

    // 必须实现的接口方法
    override fun onConnectionStartedRtmp(rtmpUrl: String) {}
    override fun onConnectionSuccessRtmp() {}
    override fun onConnectionFailedRtmp(reason: String) {}
    override fun onNewBitrateRtmp(bitrate: Long) {}
    override fun onDisconnectRtmp() {}
    override fun onAuthErrorRtmp() {}
    override fun onAuthSuccessRtmp() {}

    fun startStream(streamUrl: String) {
        // 使用SurfaceView作为替代方案
        val surfaceView = SurfaceView(context)
        rtmpCamera = RtmpCamera1(surfaceView, this).apply {
            startStream(streamUrl)
        }
    }

    fun stopStream() {
        rtmpCamera?.stopStream()
        rtmpCamera = null
    }
}