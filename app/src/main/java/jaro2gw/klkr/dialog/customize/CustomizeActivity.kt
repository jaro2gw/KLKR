package jaro2gw.klkr.dialog.customize

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import jaro2gw.klkr.R
import jaro2gw.klkr.database.Clicker
import kotlinx.android.synthetic.main.activity_customize.*

class CustomizeActivity : AppCompatActivity() {
    internal lateinit var clicker: Clicker
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customize)

        clicker = intent.getParcelableExtra("clicker")
        gridView_color.adapter = ColorViewAdapter(this)
        editText_name.setText(clicker.name)
        editText_count.setText(clicker.count.toString())

        finish_button.setOnClickListener {
            clicker.name = editText_name.text.toString()
            clicker.count = with(editText_count.text.toString()) {
                when (this) {
                    ""   -> 0
                    else -> this.toInt()
                }
            }
            setResult(Activity.RESULT_OK, Intent().putExtra("clicker", clicker))
            finish()
        }
    }
}
