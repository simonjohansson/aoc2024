package org.example

import java.math.BigInteger
import java.util.*
import java.util.Optional.empty
import kotlin.jvm.optionals.getOrElse


data class Robot(val p: Pair<Int, Int>, val v: Pair<Int, Int>) {
    fun move(width: Int, height: Int, times: Int): Robot {
        // In python -10 % 11 == 1
        // In JVM land -10 % 11 == -10
        // With bigint BigInteger.valueOf(-10).mod(BigInteger.valueOf(11)).toInt() == 1

        // To find new (x, y) after times moves along v
        // x' = (p.x + v.x*times) % width
        // y' = (p.y + v.y*times) % height

        return this.copy(
            p = (p.first + (v.first * times)).toBigInteger().mod(width.toBigInteger()).toInt() to
                    (p.second + (v.second * times)).toBigInteger().mod(height.toBigInteger()).toInt()
        )
    }
}

data class Day14(val robots: List<Robot>, val width: Int = 101, val height: Int = 103) {
    companion object {
        fun parse(input: String) = input.lines().let { robots ->
            robots.map { robot ->
                robot.split(" ").let { parts ->
                    val p = parts.first().split("=").last().split(",").let { p ->
                        p.first().toInt() to p.last().toInt()
                    }
                    val v = parts.last().split("=").last().split(",").let { v ->
                        v.first().toInt() to v.last().toInt()

                    }
                    Robot(p, v)
                }
            }.let { parsedRobots ->
                Day14(parsedRobots)
            }
        }
    }

    fun part1(): Int {
        return robots.map { it.move(width, height, 100) }.let { robotsAfter100Moves ->
            val grid =
                generateSequence { generateSequence { 0 }.take(width).toMutableList() }.take(height).toMutableList()
            robotsAfter100Moves.fold(grid) { acc, robot ->
                acc[robot.p.second][robot.p.first] += 1
                acc
            }
        }.let { grid ->
            val midLineHeight = (height - 1) / 2
            val midLineWidth = (width - 1) / 2
            listOf(
                grid.take(midLineHeight).map { it.take(midLineWidth) },
                grid.take(midLineHeight).map { it.drop(midLineWidth + 1) },
                grid.drop(midLineHeight + 1).map { it.take(midLineWidth) },
                grid.drop(midLineHeight + 1).map { it.drop(midLineWidth + 1) }
            )
        }.let { quadrants ->
            quadrants.map { quadrant ->
                quadrant.sumOf { line ->
                    line.sumOf { it }
                }
            }
        }.let { robotsPerQuandrant ->
            robotsPerQuandrant.fold(1) { acc, i -> acc * i }
        }
    }

    fun part2() {
        generateSequence(0) { it + 1 }
            .first { num ->
                println(num)
                val grid =
                    generateSequence { generateSequence { " " }.take(width).toMutableList() }.take(height)
                        .toMutableList()
                robots.map { it.move(width, height, num) }.fold(grid) { acc, robot ->
                    acc[robot.p.second][robot.p.first] = "x"
                    acc
                }

//                if (grid.joinToString { "\n" }.contains("xxxxxxxx")) {
                val strGrid = grid.joinToString("\n") { it.joinToString("") }
                if (strGrid.contains("xxxxxxxx")) {
                    println(strGrid)
                    return@first true
                }
                false
            }
    }
}