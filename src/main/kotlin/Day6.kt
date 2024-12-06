package org.example

enum class Direction {
    UP, DOWN, LEFT, RIGHT
}

data class Guard(val x: Int, val y: Int, val direction: Direction) {
    fun move() = when (this.direction) {
        Direction.UP -> this.copy(y = this.y - 1)
        Direction.DOWN -> this.copy(y = y + 1)
        Direction.LEFT -> this.copy(x = this.x - 1)
        Direction.RIGHT -> this.copy(x = this.x + 1)
    }

    fun changeDirection() = when (this.direction) {
        Direction.UP -> this.copy(direction = Direction.RIGHT)
        Direction.DOWN -> this.copy(direction = Direction.LEFT)
        Direction.LEFT -> this.copy(direction = Direction.UP)
        Direction.RIGHT -> this.copy(direction = Direction.DOWN)
    }
}

data class Day6Input(val lines: List<String>, val guard: Guard) {
    companion object {
        fun containsDirection(line: String): Boolean {
            return line.contains("^") || line.contains("v") || line.contains("<") || line.contains(">")
        }

        fun findIndexOfDirection(line: String) =
            line.indexOfFirst { it == '^' || it == 'v' || it == '<' || it == '>' }

        fun direction(line: String) =
            when (line[findIndexOfDirection(line)]) {
                '^' -> Direction.UP
                'v' -> Direction.DOWN
                '<' -> Direction.LEFT
                '>' -> Direction.RIGHT
                else -> {
                    throw IllegalArgumentException(line)
                }
            }


        fun parse(input: String): Day6Input {
            return input.lines()
                .foldIndexed(
                    Pair<List<String>, Guard>(
                        emptyList(),
                        Guard(-1, -1, Direction.UP)
                    )
                ) { index, acc, line ->
                    if (containsDirection(line)) {
                        Pair(
                            acc.first + line.replace("[v^<>]".toRegex(), "."),
                            acc.second.copy(x = findIndexOfDirection(line), y = index, direction = direction(line))
                        )
                    } else {
                        Pair(acc.first + line, acc.second)
                    }
                }.let { (lines, guard) ->
                    Day6Input(lines, guard)
                }
        }
    }
}

data class Day6(val input: String) {
    private fun peek(lines: List<String>, guard: Guard) = when (guard.direction) {
        Direction.UP -> Pair(guard.x, guard.y - 1)
        Direction.DOWN -> Pair(guard.x, guard.y + 1)
        Direction.LEFT -> Pair(guard.x - 1, guard.y)
        Direction.RIGHT -> Pair(guard.x + 1, guard.y)
    }.let { (peekX, peekY) ->
        if (peekX < 0 || peekY < 0 || peekX > lines.first().count() - 1 || peekY > lines.count() - 1) {
            "OOB"
        } else {
            lines[peekY][peekX]
        }
    }

    fun t(
        lines: List<String>,
        guard: Guard,
        visited: MutableList<Guard>,
        seen: MutableSet<Guard>
    ): Pair<List<Guard>, Boolean> =
        if (seen.contains(guard)) visited to true
        else when (peek(lines, guard)) {
            "OOB" -> visited.apply { add(guard) } to false
            '#' -> t(lines, guard.changeDirection(), visited.apply { add(guard) }, seen.apply { add(guard) })
            else -> t(lines, guard.move(), visited.apply { add(guard) }, seen.apply { add(guard) })
        }


    fun traverse(input: Day6Input): Pair<List<Guard>, Boolean> {
        return t(input.lines, input.guard, mutableListOf(), mutableSetOf())
    }

    fun part1() = Day6Input.parse(input).let { input ->
        traverse(input).first.map { Pair(it.x, it.y) }.toSet().count()
    }

    fun part2(): Int {
        return Day6Input.parse(input).let { input ->
            traverse(input).first
                .asSequence()
                .map { Pair(it.x, it.y) }.toSet()
                .filter { (x, y) -> !(input.guard.x == x && input.guard.y == y) }
                .map { (x, y) ->
                    Day6Input(input.lines.mapIndexed { index, line ->
                        if (index == y) line.mapIndexed { i, c -> if (i == x) '#' else c }.joinToString("")
                        else line
                    }, input.guard)
                }.count { traverse(it).second }
        }
    }
}