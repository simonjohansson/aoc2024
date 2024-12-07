package org.example

class Reader() {
    fun Read(file: String) = this::class.java.getClassLoader().getResourceAsStream(file)!!.bufferedReader().readText()
}

fun main() {
    println(Day7(Reader().Read("day7.txt")).part1())
}