package jaro2gw.klkr.utils

import com.google.gson.Gson
import jaro2gw.klkr.model.clicker.Clicker
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.util.*

class GsonParser {
    companion object {
        fun readClickers(): List<Clicker> {
            val gson = Gson()
//            val rdr = BufferedReader(FileReader(File("clickers.txt")))
//            return rdr.lines()
//                    .map { gson.fromJson(it, Clicker::class.java) }
//                    .collect(Collectors.toList())
//            return Files.newBufferedReader(Paths.get("C:\\Users\\jaroi\\IdeaProjects\\KLKR\\app\\src\\main\\java\\jaro2gw\\klkr\\utils\\clickers.txt"))
//                    .lines()
//                    .map { gson.fromJson(it, Clicker::class.java) }
//                    .collect(Collectors.toList())
            return LinkedList()
        }

        fun writeClickers(clickers: List<Clicker>) {
            val gson = Gson()
            val writer = BufferedWriter(FileWriter(File("clickers.txt")))
            writer.write("TEST")
//            Files.newBufferedWriter(Paths.get("C:\\Users\\jaroi\\IdeaProjects\\KLKR\\app\\src\\main\\java\\jaro2gw\\klkr\\utils\\clickers.txt"))
//                    .use { it ->
//                        it.write(clickers.stream()
//                                .map { gson.toJson(it) }
//                                .collect(Collectors.joining(System.lineSeparator())))
//                    }
        }
    }
}