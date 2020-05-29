package life.notarobot.template.utils.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.dialog_choice.view.*
import life.notarobot.template.R
import life.notarobot.template.utils.dpToPixels

class GenericChoiceDialog(
    private val title: String,
    private val choices: List<Pair<String, (() -> Unit)>>
) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val inflater = requireActivity().layoutInflater
            val view = inflater.inflate(R.layout.dialog_choice, null)
            val constraintContainer =
                view.findViewById<ConstraintLayout>(R.id.dialog_choice_container)

            view.choice_dialog_title.text = title

            var previousView: View = view.choice_dialog_title

            for (i in choices.indices) {
                val button = generateButtonWithConstraints(
                    choices[i].first,
                    constraintContainer,
                    previousView,
                    i == choices.indices.last
                )

                button.setOnClickListener {
                    choices[i].second.invoke()
                    dismiss()
                }

                previousView = button
            }

            AlertDialog.Builder(it)
                .setView(view)
                .create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    private fun generateButtonWithConstraints(
        label: String,
        constraintContainer: ConstraintLayout,
        previousView: View,
        isLast: Boolean
    ): Button {
        val button = Button(activity)
        button.id = View.generateViewId()
        button.layoutParams = ConstraintLayout.LayoutParams(0, dpToPixels(65, context))
        button.background = ResourcesCompat.getDrawable(
            resources,
            R.drawable.border_choice_dialog_choice,
            null
        )

        button.text = label
        button.elevation = 2f

        constraintContainer.addView(button)

        val constraintSet = ConstraintSet()
        constraintSet.let { set ->
            set.clone(constraintContainer)
            set.connect(
                button.id,
                ConstraintSet.TOP,
                previousView.id,
                ConstraintSet.BOTTOM,
                dpToPixels(10, context)
            )
            set.connect(
                button.id,
                ConstraintSet.LEFT,
                constraintContainer.id,
                ConstraintSet.LEFT,
                dpToPixels(10, context)
            )
            set.connect(
                button.id,
                ConstraintSet.RIGHT,
                constraintContainer.id,
                ConstraintSet.RIGHT,
                dpToPixels(10, context)
            )

            if (isLast) {
                set.connect(
                    button.id,
                    ConstraintSet.BOTTOM,
                    constraintContainer.id,
                    ConstraintSet.BOTTOM,
                    dpToPixels(20, context)
                )
            }

            set.constrainDefaultHeight(button.id, ConstraintSet.MATCH_CONSTRAINT_SPREAD)
            set.constrainDefaultWidth(button.id, ConstraintSet.MATCH_CONSTRAINT_SPREAD)
            set.applyTo(constraintContainer)
        }

        return button
    }
}

