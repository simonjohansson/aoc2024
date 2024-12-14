package org.example

class Reader() {
    companion object {
        fun Read(file: String) = this::class.java.getClassLoader().getResourceAsStream(file)!!.bufferedReader().readText()
    }
}

fun main() {
    println(Day14.parse(Reader.Read("day14.txt")).part2())
}