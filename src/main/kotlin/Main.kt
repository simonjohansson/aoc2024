package org.example

class Reader() {
    companion object {
        fun Read(file: String) = this::class.java.getClassLoader().getResourceAsStream(file)!!.bufferedReader().readText()
    }
}

fun main() {
    println(Day10(Reader.Read("day10.txt")).part1())
}