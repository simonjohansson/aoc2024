package org.example

sealed class Day3Option {
    data class Mul(val a: Int, val b: Int) : Day3Option()
    class Do : Day3Option()
    class Dont : Day3Option()
}

data class Day3(val input: String) {
    private fun parse() =
        "mul\\(([0-9]{1,3}),([0-9]{1,3})\\)|do\\(\\)|don't\\(\\)".toRegex()
            .findAll(input).toList()
            .map {
                when (it.groupValues[0]) {
                    "do()" -> Day3Option.Do()
                    "don't()" -> Day3Option.Dont()
                    else -> Day3Option.Mul(it.groupValues[1].toInt(), it.groupValues[2].toInt())
                }
            }

    fun part1(): Int {
        return parse()
            .filterIsInstance<Day3Option.Mul>()
            .sumOf { it.a * it.b }
    }

    fun part2() = parse().fold(Pair(0, true)) { (acc, shouldMul), op ->
        when (op) {
            is Day3Option.Do -> Pair(acc, true)
            is Day3Option.Dont -> Pair(acc, false)
            is Day3Option.Mul -> Pair(acc + (op.a * op.b * if (shouldMul) 1 else 0), shouldMul)
        }
    }.first
}