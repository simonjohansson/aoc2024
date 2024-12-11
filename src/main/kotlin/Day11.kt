package org.example

data class Day11Input(private val stones: Map<Long, Long>) {
    companion object {
        fun parse(input: String) = Day11Input(
            stones = input.split(" ")
                .map { it.toLong() }
                .groupBy { it }
                .map { it.key to it.value.count().toLong() }
                .toMap()
        )
    }

    private fun Long.blink(): List<Long> =
        if (this == 0L) listOf(1L)
        else
            if (this.toString().count() % 2 == 0) this.toString().let { asString ->
                listOf(
                    asString.substring(0, asString.count() / 2).toLong(),
                    asString.substring(asString.count() / 2).toLong()
                )
            }
            else listOf(this * 2024)

    private fun Map<Long, Long>.blink(): Map<Long, Long> {
        val new = mutableMapOf<Long, Long>().withDefault { 0 }
        this.toList().forEach { (stone, count) ->
            stone.blink().forEach {
                new[it] = new.getValue(it) + count
            }
        }
        return new
    }

    fun blink(n: Int = 1) = generateSequence(this.stones.blink()) { it.blink() }.take(n).last()

}

data class Day11(val input: String) {
    fun blinkAndSum(n: Int) = Day11Input.parse(input).blink(n)
        .toList()
        .sumOf { it.second }

    fun part1() = blinkAndSum(25)
    fun part2() = blinkAndSum(75)
}