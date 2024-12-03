package org.example

sealed class Day3Option {
    data class Mul(val a: Int, val b: Int) : Day3Option()
    class Do : Day3Option()
    class Dont : Day3Option()
}

data class Day3(val input: String) {
    private fun parse(): List<Day3Option> {
        val options = mutableListOf<Day3Option>()

        val matches = "mul\\(([0-9]{1,3}),([0-9]{1,3})\\)|do\\(\\)|don't\\(\\)".toRegex().findAll(input).toList()
        for (match in matches) {
            when (match.groupValues[0]) {
                "do()" -> options.add(Day3Option.Do())
                "don't()" -> options.add(Day3Option.Dont())
                else -> options.add(Day3Option.Mul(match.groupValues[1].toInt(), match.groupValues[2].toInt()))
            }
        }
        return options.toList()
    }

    fun part1(): Int {
        return parse()
            .filter { it is Day3Option.Mul }
            .map { it as Day3Option.Mul }
            .map { it.a * it.b }
            .sum()
    }

    fun part2(): Int {
        var shouldMul = true
        var result = 0
        for (op in parse()) {
            when (op) {
                is Day3Option.Do -> {
                    shouldMul = true
                }

                is Day3Option.Dont -> {
                    shouldMul = false
                }

                is Day3Option.Mul -> {
                    if (shouldMul) {
                        result += op.a * op.b
                    }
                }
            }
        }
        return result
    }
}