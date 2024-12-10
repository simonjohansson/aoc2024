package org.example

import java.util.Optional
import kotlin.jvm.optionals.getOrDefault

data class GridCord(val x: Int, val y: Int)
data class Day10Grid(val nums: List<List<Int>>) {
    fun peek(cord: GridCord): Optional<Int> =
        if (cord.x < 0 || cord.y < 0 || cord.x > nums.first().count() - 1 || cord.y > nums.count() - 1)
            Optional.empty()
        else
            Optional.of(nums[cord.y][cord.x])

    fun at(cord: GridCord) = nums[cord.y][cord.x]
}


data class Day10Input(val grid: Day10Grid) {
    companion object {
        fun parse(input: String) = Day10Input(
            Day10Grid(input.lines().map { it.toCharArray().map { it.digitToInt() } })
        )
    }

    fun findStartPositions(): List<GridCord> = this.grid.nums.foldIndexed(listOf()) { y, acc, line ->
        acc + line.mapIndexed { x, d -> Triple(d, x, y) }
            .filter { (d, _, _) -> d == 0 }
            .map { (_, x, y) -> GridCord(x, y) }
    }

    fun findPaths(currPosition: GridCord, visited: List<GridCord>): List<List<GridCord>> {
        println(visited)
        val current = grid.at(currPosition)
        if (current == 9) {
            return listOf(visited + currPosition)
        }

        return listOf(
            currPosition.copy(x = currPosition.x - 1),
            currPosition.copy(x = currPosition.x + 1),
            currPosition.copy(y = currPosition.y - 1),
            currPosition.copy(y = currPosition.y + 1),
        )
            .filter { !visited.contains(it) }
            .filter { (grid.peek(it).getOrDefault(Int.MIN_VALUE) - current) == 1 }
            .map { findPaths(it, visited + currPosition) }
            .flatten()
    }

    fun findPaths(startPosition: GridCord): List<List<GridCord>> {
        return findPaths(startPosition, listOf()).filter { it.isNotEmpty() }
    }
}

data class Day10(val input: String) {
    fun part1() = Day10Input.parse(input).let { grid ->
        grid.findStartPositions().sumOf { startPosition ->
            grid.findPaths(startPosition).map { it.last() }.toSet().count()
        }
    }
}