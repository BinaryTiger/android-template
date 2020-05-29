package life.notarobot.template.utils.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View.GONE
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.dialog_info.view.*
import life.notarobot.template.R

class InformationDialog(
    private val title: Int? = R.string.dialog_info_default_title,
    private val description: Int = R.string.dialog_info_default_description
) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val inflater = requireActivity().layoutInflater
            val view = inflater.inflate(R.layout.dialog_info, null)

            view.dialog_button_confirm.setOnClickListener {
                dismiss()
            }

            if (title != null) {
                view.dialog_title.text = getText(title)
            } else {
                view.dialog_title.visibility = GONE
            }

            view.dialog_description.text = getText(description)

            AlertDialog.Builder(it)
                .setView(view)
                .create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}