package life.notarobot.template

import android.app.Activity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import life.notarobot.template.databinding.ActivityMainBinding

class MainActivity : Activity() {
//    override fun onBackClicked() {
//        onBackPressed()
//    }
//
//    override fun onFragmentInteraction(view: View) {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // There is a side effect to this, cannot delete
        @Suppress("UNUSED_VARIABLE")
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
    }
}