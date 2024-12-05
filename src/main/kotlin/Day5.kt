package org.example

import kotlin.math.floor

data class Rule(val firsPage: Int, val secondPage: Int)
typealias Rules = List<Rule>

data class Update(val pages: List<Int>)
typealias Updates = List<Update>

data class Day5Input(val rules: Rules, val updates: Updates) {
    companion object {
        fun parse(input: String): Day5Input {
            return input.lines().filter(String::isNotBlank)
                .fold(Pair(emptyList<Rule>(), emptyList<Update>())) { (rules, updates), line ->
                    if (line.contains("|")) {
                        line.split("|").let { (firstPage, secondPage) ->
                            Pair(rules + Rule(firstPage.toInt(), secondPage.toInt()), updates)
                        }
                    } else {
                        line.split(",").map { it.toInt() }.let { pages ->
                            Pair(rules, updates + Update(pages))
                        }
                    }
                }.let {
                    Day5Input(it.first, it.second)
                }
        }
    }
}

data class Day5(val input: String) {

    private fun correctOrder(update: Update, rules: Rules): Boolean {
        return update.pages.let { pages ->
            pages.flatMapIndexed { index: Int, page: Int ->
                pages.subList(index + 1, pages.size).map { otherPage ->
                    Pair(page, otherPage)
                }
            }.all {
                rules.contains(Rule(it.first, it.second))
            }
        }
    }

    private fun findCorrectOrder(update: Update, rules: Rules): Update {
        val map = HashMap<Int, List<Int>>()
        for (page in update.pages) {
            val m = map.getOrElse(page) { emptyList() }
            map[page] = m + rules.filter { it.firsPage == page && it.secondPage in update.pages }.map { it.secondPage }
        }

        return map
            .map { (k, v) -> Pair(k, v.count()) }
            .sortedByDescending { it.second }
            .map { it.first }
            .let {
                Update(it)
            }
    }

    fun middleNumber(update: Update) =
        update.pages[floor(update.pages.count() / 2.0).toInt()]


    fun part1() = Day5Input.parse(input).let { input ->
        input.updates
            .filter { update -> correctOrder(update, input.rules) }
            .sumOf { middleNumber(it) }
    }

    fun part2() = Day5Input.parse(input).let { input ->
        input.updates
            .filter { update -> !correctOrder(update, input.rules) }
            .map { update -> findCorrectOrder(update, input.rules) }
            .sumOf { middleNumber(it) }
    }
}