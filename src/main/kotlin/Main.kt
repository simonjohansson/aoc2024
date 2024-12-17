package org.example

class Reader() {
    companion object {
        fun Read(file: String) = this::class.java.getClassLoader().getResourceAsStream(file)!!.bufferedReader().readText()
    }
}

fun main() {
    println(Day17.parse(Reader.Read("day17.txt")).part1())
}