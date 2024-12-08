package org.example

import kotlin.math.abs

typealias Cord = Pair<Int, Int>
typealias CordPair = Pair<Cord, Cord>

fun CordPair.findAntiNodes(): Pair<Sequence<Cord>, Sequence<Cord>> =
    this.let { (c1, c2) ->
        val xDelta = abs(c1.first - c2.first)
        val yDelta = abs(c1.second - c2.second)
        if (c1.first < c2.first)
            Pair(
                generateSequence(c1) { it.first - xDelta to it.second - yDelta },
                generateSequence(c2) { it.first + xDelta to it.second + yDelta }
            )
        else
            Pair(
                generateSequence(c1) { it.first + xDelta to it.second - yDelta },
                generateSequence(c2) { it.first - xDelta to it.second + yDelta }
            )
    }

typealias Cords = List<Cord>

fun Cords.findPairs() =
    (0..<this.count()).flatMap { i ->
        (i + 1..<this.count()).map { j ->
            Pair(this[i], this[j])
        }
    }


class Day8Input(val maxX: Int, val maxY: Int, val antennas: Map<Char, Cords>) {
    companion object {
        private fun findAntennas(lines: List<String>) =
            lines.flatMapIndexed { y, line ->
                line.toCharArray().mapIndexed { x, c ->
                    Triple(c, x, y)
                }
            }.filter { it.first != '.' }
                .fold(mutableMapOf<Char, List<Pair<Int, Int>>>()) { acc, tripple ->
                    acc.apply {
                        put(
                            tripple.first,
                            getOrDefault(tripple.first, listOf()) + listOf(Pair(tripple.second, tripple.third))
                        )
                    }
                }


        fun parse(input: String) = Day8Input(
            maxX = input.lines().first().count() - 1,
            maxY = input.lines().count() - 1,
            antennas = findAntennas(input.lines())
        )
    }

    private fun isOOB(cord: Cord) =
        cord.first < 0 || cord.first > maxX || cord.second < 0 || cord.second > maxY

    fun findAntiNodes(includeResonantHarmonics: Boolean = false) = this.antennas.flatMap { (_, antennas) ->
        antennas.findPairs()
            .flatMap { pair ->
                pair.findAntiNodes().let { (a1, a2) ->
                    if (includeResonantHarmonics) a1.takeWhile { a -> !isOOB(a) } + a2.takeWhile { a -> !isOOB(a) }
                    else
                        a1.drop(1).take(1).takeWhile { a -> !isOOB(a) } +
                                a2.drop(1).take(1).takeWhile { a -> !isOOB(a) }
                }
            }
    }
}

data class Day8(val input: String) {
    fun part1() = Day8Input.parse(input)
        .findAntiNodes()
        .toSet()
        .count()

    fun part2() = Day8Input.parse(input)
        .findAntiNodes(true)
        .toSet()
        .count()
}