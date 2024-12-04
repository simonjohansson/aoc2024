package org.example

import java.util.regex.Pattern

data class Line(var line: String) {
    fun count() =
        Pattern.compile("XMAS").matcher(line + " " + line.reversed()).results().count().toInt()
}

data class Grid(var lines: List<Line>) {
    fun count() = lines.sumOf {
        it.count()
    }

    fun verticalLines() =
        Grid(
            (0..<this.lines.count()).map {
                Line(lines.map { line -> line.line[it] }.joinToString(""))
            }
        )

    private fun diag(lns: List<Line>): List<Line> {
        val lineLen = lns.first().line.count()
        val numLines = lns.count()

        val newLines = mutableListOf<Line>()

        for (l in 0..<numLines) {
            for (c in 0..<lineLen) {
                val tmpLine = mutableListOf(lns[l].line[c].toString())
                var tmpCharIndex = c + 1
                var tmpLineIndex = l + 1
                while (true) {
                    if (tmpCharIndex > lineLen - 1 || tmpLineIndex > numLines - 1) {
                        break
                    }
                    tmpLine.add(lns[tmpLineIndex].line[tmpCharIndex].toString())
                    tmpLineIndex++
                    tmpCharIndex++
                }
                newLines.add(Line(tmpLine.joinToString("")))
                if (l != 0) {
                    break
                }
            }
        }
        return newLines
    }

    fun diagonalLines(): Grid {
        return Grid(
            this.diag(lines) + this.diag(lines.map { it.copy(line = it.line.reversed()) })
        )
    }

    companion object {
        fun parse(input: String) =
            Grid(input.lines().map { Line(it) })
    }
}

data class Day4(val input: String) {
    fun part1(): Int {
        return Grid.parse(input).let { grid ->
            grid.count() +
                    grid.verticalLines().count() +
                    grid.diagonalLines().count()
        }
    }

    fun part2(): Int {
        var count = 0
        input.split("\n").map { line -> line.toCharArray() }.let { lines ->
            lines.forEachIndexed { currLine, line ->
                line.forEachIndexed { currChar, char ->
                    if (char == 'A') {
                        try {
                            val aboveLeft = lines[currLine - 1][currChar - 1]
                            val belowRight = lines[currLine + 1][currChar + 1]

                            val aboveRight = lines[currLine - 1][currChar + 1]
                            val belowLeft = lines[currLine + 1][currChar - 1]

                            val one = aboveLeft.toString() + char + belowRight
                            val two = aboveRight.toString() + char + belowLeft

                            if ((one == "MAS" || one == "SAM") && (two == "MAS" || two == "SAM")) {
                                count++
                            }
                        } catch (_: Exception) {
                        }
                    }
                }
            }
        }

        return count
    }
}