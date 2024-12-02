package org.example

import kotlin.math.abs

private data class Report(val levels: List<Int>) {
    private fun windowed() = levels.windowed(2).map { Pair(it[0], it[1]) }
    private fun increasing() = windowed().all { it.first > it.second }
    private fun decreasing() = windowed().all { it.first < it.second }
    private fun min1Max3Diff() = windowed().all { abs(it.first - it.second) in 1..3 }

    private fun subsets() = this.levels.mapIndexed { index, _ ->
        Report(this.levels.filterIndexed { idx, _ -> idx != index })
    }

    private fun safe() = increasing() or decreasing() and min1Max3Diff()

    fun Safe(problemDampener: Boolean = false) =
        if (problemDampener) safe() or subsets().any { it.safe() } else safe()
    
}

data class Day2(val input: String) {
    private fun reports(): List<Report> =
        input.split("\n").map { line ->
            line.split(" ").map { it.toInt() }
        }.map { Report(it) }

    fun part1() = reports().count { it.Safe() }

    fun part2() = reports().count { it.Safe(true) }

}