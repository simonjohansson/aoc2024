package org.example

import javax.swing.text.html.HTML.Tag.P

data class ClawMachine(val A: Pair<Int, Int>, val B: Pair<Int, Int>, val P: Pair<Int, Int>) {
    fun play(): Pair<Int, Int> {
        val a = ((P.first * B.second) - (P.second * B.first)) / ((A.first * B.second) - (A.second * B.first))
        val b = ((A.first * P.second) - (A.second * P.first)) / ((A.first * B.second) - (A.second * B.first))
        return Pair(a, b)
    }
}

data class Day13(val clawMachines: List<ClawMachine>) {
    companion object {
        fun parse(input: String) = input.split("\n\n").let { machines ->
            val find: (String) -> Pair<Int, Int> = {
                "([0-9]+)".toRegex().findAll(it)
                    .toList().map { it.value.toInt() }.let { nums ->
                        (nums.first() to nums.last())
                    }
            }

            machines.map { machine ->
                ClawMachine(
                    find(machine.lines().first()),
                    find(machine.lines()[1]),
                    find(machine.lines().last())
                )
            }
        }.let {
            Day13(it)
        }
    }

    private fun check(machine: ClawMachine, AB: Pair<Int, Int> ): Boolean {
        val (px, py) = machine.P
        val (ax, ay) = machine.A
        val (bx, by) = machine.B
        val (A, B) = AB

        return px == (A*ax + B*bx) && py == (A*ay + B*by)
    }

    fun part1() = clawMachines
        .map { Pair(it, it.play()) }
        .filter { check(it.first, it.second) }
        .sumOf { it.second.first*3 + it.second.second*1 }
}