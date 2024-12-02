package org.example

class Reader() {
    fun Read(file: String) = this::class.java.getClassLoader().getResourceAsStream(file)!!.bufferedReader().readText()
}

fun main() {
    println(Day2(Reader().Read("day2.txt")).part2())
}