package jaro2gw.klkr

class Score(var name: String = "Something", var count: Int = 0, var color: String = "#000000") {

    fun inc() = ++count

    fun dec() = --count
}