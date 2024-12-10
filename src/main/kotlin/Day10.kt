package org.example

data class Day10Input(val nums: List<List<Int>>) {
    companion object {
        fun parse(input: String) = Day10Input(
            emptyList()
        )
    }
}

data class Day10(val input: String) {
    fun part1() = Day10Input.parse(input).let { grid ->
        10
    }
}