package life.notarobot.template.utils

import android.content.Context

fun dpToPixels(dp: Int, context: Context?): Int {
    return if (context != null) {
        (dp * (context.resources.displayMetrics.density).toInt())
    } else {
        0
    }
}