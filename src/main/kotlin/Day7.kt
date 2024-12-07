package org.example

data class Node(val value: Long, var left: Node? = null, var middle: Node? = null, var right: Node? = null) {
    private fun isLeafNode() = left == null && middle == null && right == null

    fun applyNumAtLeafNodes(num: Long): Node = apply {
        left = left?.applyNumAtLeafNodes(num) ?: Node(value + num)
        middle = middle?.applyNumAtLeafNodes(num) ?: Node("${value}${num}".toLong())
        right = right?.applyNumAtLeafNodes(num) ?: Node(value * num)
    }

    fun findAllLeafNodeValues(includeMiddle: Boolean): List<Long> = if (isLeafNode()) listOf(value) else
        left!!.findAllLeafNodeValues(includeMiddle) + right!!.findAllLeafNodeValues(includeMiddle) +
                if (includeMiddle) middle!!.findAllLeafNodeValues(includeMiddle) else listOf()
}

data class Day7Line(val wanted: Long, val numbers: List<Long>) {
    fun isTrue(includeMiddle: Boolean = false) = numbers.drop(1)
        .fold(Node(numbers.first())) { acc, num -> acc.applyNumAtLeafNodes(num) }
        .findAllLeafNodeValues(includeMiddle).any { it == wanted }

    companion object {
        fun parse(input: String) = input.lines().map { line ->
            line.split(":").let { parts ->
                Day7Line(
                    parts[0].toLong(),
                    parts[1].trim().split(" ").map { it.toLong() }
                )
            }
        }
    }
}

data class Day7(val input: String) {
    fun part1() = Day7Line.parse(input)
        .filter { it.isTrue() }
        .sumOf { it.wanted }

    fun part2() = Day7Line.parse(input)
        .filter { it.isTrue(true) }
        .sumOf { it.wanted }
}