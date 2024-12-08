package org.example

class Reader() {
    fun Read(file: String) = this::class.java.getClassLoader().getResourceAsStream(file)!!.bufferedReader().readText()
}

fun main() {
    println(Day8(Reader().Read("day8.txt")).part1())
}