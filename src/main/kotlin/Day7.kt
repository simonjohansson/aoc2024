package org.example

data class Node(val value: Long, var left: Node? = null, var right: Node? = null) {
    fun applyNumAtLeafNodes(num: Long): Node {
        if (left == null && right == null) {
            return this.apply {
                left = Node(this.value + num)
                right = Node(this.value * num)
            }
        } else {
            return this.apply {
                left = left!!.applyNumAtLeafNodes(num)
                right = right!!.applyNumAtLeafNodes(num)
            }
        }
    }

    fun findAllLeafNodeValues(): List<Long> {
        if (this.left == null && this.right == null) {
            return listOf(this.value)
        }
        return this.left!!.findAllLeafNodeValues() + this.right!!.findAllLeafNodeValues()
    }
}

data class Day7Line(val wanted: Long, val numbers: List<Long>) {
    fun isTrue() = numbers.drop(1).fold(Node(numbers.first())) { acc, num ->
        acc.applyNumAtLeafNodes(num)
    }.findAllLeafNodeValues().any { it == wanted }


    companion object {
        fun parse(input: String): List<Day7Line> =
            input.lines().map { line ->
                line.split(":").let { parts ->
                    Day7Line(
                        parts[0].toLong(),
                        parts[1].trim().split(" ").map { it.toLong() })
                }
            }
    }
}

data class Day7(val input: String) {
    fun part1() = Day7Line.parse(input)
        .filter { it.isTrue() }
        .sumOf { it.wanted }

}