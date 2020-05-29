package life.notarobot.template.utils.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.dialog_confirmation.view.*
import life.notarobot.template.R

class ConfirmationDialog(
    private val title: Int = R.string.dialog_confirmation_default_title,
    private val description: Int = R.string.dialog_confirmation_default_description,
    private val cancelAction: (() -> Unit)? = null,
    private val confirmAction: (() -> Unit)? = null
) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val inflater = requireActivity().layoutInflater
            val view = inflater.inflate(R.layout.dialog_confirmation, null)

            view.dialog_button_confirm.setOnClickListener {
                confirmAction?.invoke()
                dismiss()
            }

            view.dialog_button_cancel.setOnClickListener {
                cancelAction?.invoke()
                dismiss()
            }

            view.dialog_title.text = getText(title)
            view.dialog_description.text = getText(description)

            AlertDialog.Builder(it)
                .setView(view)
                .create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}