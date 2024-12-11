package org.example

import java.util.Optional
import kotlin.jvm.optionals.getOrDefault

data class Stone(val num: Long) {
    fun blink(): List<Stone> {
        if (num == 0L) {
            return listOf(Stone(1L))
        }
        num.toString().let { asString ->
            if (asString.count() % 2 == 0) {
                val first = asString.substring(0, asString.count() / 2).toLong()
                val second = asString.substring(asString.count() / 2).toLong()
                return listOf(
                    Stone(first),
                    Stone(second),
                )
            }
        }
        return listOf(Stone(num * 2024))
    }
}

fun List<Stone>.blink() = this.fold(emptyList<Stone>()) { acc, stone ->
    acc + stone.blink()
}

fun List<Stone>.blink(n: Int) =
    generateSequence(this.blink()) { it.blink() }.take(n).last()


fun List<Stone>.blinkMap() = fold(mutableMapOf<Stone, Long>().withDefault { 0 }) { acc, stone ->
    acc[stone] = acc.getValue(stone) + 1
    acc
}

fun MutableMap<Stone, Long>.blink(i: Long): MutableMap<Stone, Long> {
    val new = mutableMapOf<Stone, Long>().withDefault { 0 }
    this.forEach { t, u ->
        t.blink().forEach {
            new[it] = new.getValue(it) + u
        }
    }
    return new
}

fun MutableMap<Stone, Long>.blink(n: Int): MutableMap<Stone, Long> {
    var copy = this
    for (i in 0 until n) {
        copy = copy.blink(i.toLong())
    }
    return copy
}


data class Day11Input(val stones: List<Stone>) {
    companion object {
        fun parse(input: String) = Day11Input(
            input.split(" ").map { Stone(it.toLong()) }
        )
    }
}

data class Day11(val input: String) {
    fun part1() = Day11Input.parse(input).let { stones ->
        stones.stones.blink(25).count()
    }

    fun part2() = Day11Input.parse(input).let { stones ->
        stones.stones.blinkMap().blink(75).toList().sumOf { it.second }
    }
}