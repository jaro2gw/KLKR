package jaro2gw.klkr.model.clicker

import android.view.View
import jaro2gw.klkr.R

class Clicker(var name: String = "Something", var count: Int = 0, var color: Int = R.color.colorAccent) {
    fun inc(view: View) {
        ++count
    }

    fun dec(view: View) {
        --count
    }
}
