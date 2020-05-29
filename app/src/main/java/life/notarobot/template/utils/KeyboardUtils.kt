package life.notarobot.template.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService

// Passed view have to be focusable
fun showSoftKeyboard(view: View, context: Context?) {
    if (view.requestFocus() && context != null) {
        val imm = getSystemService(context, InputMethodManager::class.java) as InputMethodManager
        imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
    }
}