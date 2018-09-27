package jaro2gw.klkr

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import jaro2gw.klkr.ui.warningdialog.WarningDialogFragment

class WarningDialog : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.warning_dialog_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, WarningDialogFragment.newInstance())
                    .commitNow()
        }
    }

}
