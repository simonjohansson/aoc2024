package org.example

data class Day10Input(val nums: List<List<Int>>) {
    companion object {
        fun parse(input: String) = Day10Input(
            input.lines().map { it.toCharArray().map { it.digitToInt() } }
        )
    }
}

data class Day10(val input: String) {
    fun part1() = Day10Input.parse(input).let { grid ->
        -10
    }
}