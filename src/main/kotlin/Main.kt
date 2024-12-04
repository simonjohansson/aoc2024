package org.example

class Reader() {
    fun Read(file: String) = this::class.java.getClassLoader().getResourceAsStream(file)!!.bufferedReader().readText()
}

fun main() {
    println(Day4(Reader().Read("day4.txt")).part2())
}