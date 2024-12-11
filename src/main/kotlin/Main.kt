package org.example

class Reader() {
    companion object {
        fun Read(file: String) = this::class.java.getClassLoader().getResourceAsStream(file)!!.bufferedReader().readText()
    }
}

fun main() {
    println(Day11("773 79858 0 71 213357 2937 1 3998391").part1())
    println(Day11("773 79858 0 71 213357 2937 1 3998391").part2())
}