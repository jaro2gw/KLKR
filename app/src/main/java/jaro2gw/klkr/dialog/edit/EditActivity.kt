package jaro2gw.klkr.dialog.edit

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.*
import jaro2gw.klkr.R

class EditActivity : AppCompatActivity() {
    private fun colorSelect(gridView: GridView, adapter: ColorViewAdapter, intent: Intent, position: Int) {
        (gridView.getChildAt(adapter.colors.indexOf(intent.getIntExtra("color", ColorViewAdapter.colors[0]))) as ImageView).setImageResource(R.drawable.blank)
        (gridView.getChildAt(position) as ImageView).setImageResource(R.drawable.check)
        intent.putExtra("color", adapter.colors[position])
        adapter.notifyDataSetChanged()
    }

    private lateinit var nameText: EditText
    private lateinit var countText: EditText
    private lateinit var gridView: GridView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        nameText = findViewById(R.id.editText_name)
        countText = findViewById(R.id.editText_count)
        gridView = findViewById(R.id.gridView_color)

        with(gridView) {
            adapter = ColorViewAdapter(this@EditActivity)
            onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
                colorSelect(this, adapter as ColorViewAdapter, intent, position)
            }
        }

        with(intent) {
            nameText.setText(getStringExtra("name"))
            countText.setText(getStringExtra("count"))
        }

        findViewById<ImageButton>(R.id.edit_button).setOnClickListener {
            setResult(Activity.RESULT_OK, Intent()
                    .putExtras(intent)
                    .putExtra("name", nameText.text.toString())
                    .putExtra("count", countText.text.toString()))
            finish()
        }
    }
}
