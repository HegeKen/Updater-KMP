import android.os.Build
import android.widget.Toast
import top.yukonga.updater.kmp.AndroidAppContext
import top.yukonga.updater.kmp.misc.AppUtils.atLeast
import top.yukonga.updater.kmp.misc.AppUtils.isLandscape
import top.yukonga.updater.kmp.misc.XiaomiUtils.isHyperOS
import top.yukonga.updater.kmp.misc.XiaomiUtils.isMiPad
import top.yukonga.updater.kmp.misc.miuiStringToast.MiuiStringToast

private var lastToast: Toast? = null

actual fun useToast(): Boolean = true
actual fun showToast(message: String, duration: Long) {
    val context = AndroidAppContext.getApplicationContext()
    if ((!isMiPad() && isLandscape()) || !atLeast(Build.VERSION_CODES.TIRAMISU) || !isHyperOS()) {
        lastToast?.cancel()
        lastToast = Toast.makeText(context, message, duration.toInt()).apply { show() }
    } else {
        MiuiStringToast.showStringToast(context!!, message, duration)
    }

}