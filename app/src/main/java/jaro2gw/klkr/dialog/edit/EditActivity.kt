package jaro2gw.klkr.dialog.edit

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import jaro2gw.klkr.R
import kotlinx.android.synthetic.main.activity_edit.*

class EditActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        gridView_color.adapter = ColorViewAdapter(this@EditActivity)
        with(intent) {
            editText_name.setText(getStringExtra("name"))
            editText_count.setText(getStringExtra("count"))
        }

        edit_button.setOnClickListener {
            setResult(Activity.RESULT_OK, Intent()
                    .putExtras(intent)
                    .putExtra("name", editText_name.text.toString())
                    .putExtra("count", editText_count.text.toString()))
            finish()
        }
    }
}
