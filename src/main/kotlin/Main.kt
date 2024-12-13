package org.example

class Reader() {
    companion object {
        fun Read(file: String) = this::class.java.getClassLoader().getResourceAsStream(file)!!.bufferedReader().readText()
    }
}

fun main() {
    println(Day13.parse(Reader.Read("day13.txt")).part2())
}