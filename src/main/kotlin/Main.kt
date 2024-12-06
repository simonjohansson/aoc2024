package org.example

class Reader() {
    fun Read(file: String) = this::class.java.getClassLoader().getResourceAsStream(file)!!.bufferedReader().readText()
}

fun main() {
    println(Day6(Reader().Read("day6.txt")).part2())
}