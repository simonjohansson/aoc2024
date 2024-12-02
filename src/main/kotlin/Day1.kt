package org.example

import kotlin.math.abs

data class Day1(val input: String) {
    private fun parse(): Pair<List<Int>, List<Int>> {
        val lists = input.split("\n").map { line ->
            line.split("\\s+".toRegex()).map { it.toInt() }
        }

        var list1 = mutableListOf<Int>()
        var list2 = mutableListOf<Int>()
        for (list in lists) {
            list1.add(list[0])
            list2.add(list[1])
        }

        list1.sort()
        list2.sort()

        return Pair(list1.toList(), list2.toList())
    }

    fun part1() = parse().first.zip(parse().second).sumOf {
        abs(it.first - it.second)
    }


    fun part2(): Int {
        val lists = parse()
        val list1 = lists.first
        val list2 = lists.second
        return list1.map { Pair(it, list2) }.sumOf { (num, secondList) ->
            num * secondList.count { it == num }
        }
    }
}