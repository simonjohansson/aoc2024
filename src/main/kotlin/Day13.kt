package org.example

import java.util.*
import java.util.Optional.empty
import kotlin.jvm.optionals.getOrElse


data class ClawMachine(val A: Pair<Long, Long>, val B: Pair<Long, Long>, val P: Pair<Long, Long>) {
    private fun check(result: Pair<Long, Long>): Boolean {
        val (px, py) = P
        val (ax, ay) = A
        val (bx, by) = B
        val (a, b) = result

        return px == (a * ax + b * bx) && py == (a * ay + b * by)
    }

    fun play(): Optional<Pair<Long, Long>> {
        val a = ((P.first * B.second) - (P.second * B.first)) / ((A.first * B.second) - (A.second * B.first))
        val b = ((A.first * P.second) - (A.second * P.first)) / ((A.first * B.second) - (A.second * B.first))
        return if (check(Pair(a, b))) Optional.of(Pair(a, b)) else empty()
    }
}

data class Day13(val clawMachines: List<ClawMachine>) {
    companion object {
        fun parse(input: String) = input.split("\n\n").let { machines ->
            val find: (String) -> Pair<Long, Long> = {
                "([0-9]+)".toRegex().findAll(it)
                    .toList().map { it.value.toLong() }.let { nums ->
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

    fun part1() = clawMachines
        .map { it.play() }
        .sumOf { result ->
            result.map { it.first * 3 + it.second }.getOrElse { 0 }
        }

    fun part2() =
        clawMachines.map { clawMachine -> clawMachine.copy(P = (clawMachine.P.first + 10000000000000L to clawMachine.P.second + 10000000000000)) }
            .map { it.play() }
            .sumOf { result ->
                result.map { it.first * 3 + it.second }.getOrElse { 0 }
            }
}