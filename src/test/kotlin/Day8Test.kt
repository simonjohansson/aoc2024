import org.example.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day8Test {

    @Test
    fun parse() {
        val expected = Day8Input(
            maxX = 11,
            maxY = 11,
            antennas = mapOf(
                '0' to listOf(
                    8 to 1,
                    5 to 2,
                    7 to 3,
                    4 to 4,
                ),
                'A' to listOf(
                    6 to 5,
                    8 to 8,
                    9 to 9,
                )
            ),
        )

        assertEquals(expected.maxX, Day8Input.parse(Reader().Read("day8.example1.txt")).maxX)
        assertEquals(expected.maxY, Day8Input.parse(Reader().Read("day8.example1.txt")).maxY)
        assertEquals(expected.antennas, Day8Input.parse(Reader().Read("day8.example1.txt")).antennas)
    }

    @Test
    fun `find permutations`() {
        val input = listOf(
            1 to 1,
            2 to 2,
            3 to 3,
            4 to 4,
        )
        val expected = listOf(
            (1 to 1) to (2 to 2),
            (1 to 1) to (3 to 3),
            (1 to 1) to (4 to 4),

            (2 to 2) to (3 to 3),
            (2 to 2) to (4 to 4),

            (3 to 3) to (4 to 4),
        )

        assertEquals(expected, input.findPermutations())
    }

    @Test
    fun `find antiNodes`() {
        assertEquals(
            listOf(
                (-3 to -3),
                (6 to 6),
            ), ((0 to 0) to (3 to 3)).findAntiNodes()
        )

        assertEquals(
            listOf(
                (0 to -3),
                (0 to 6),
            ), ((0 to 0) to (0 to 3)).findAntiNodes()
        )

        assertEquals(
            listOf(
                (0 to -3),
                (0 to 6),
            ), ((0 to 0) to (0 to 3)).findAntiNodes()
        )

        assertEquals(
            listOf(
                (6 to -3),
                (-3 to 6),
            ), ((3 to 0) to (0 to 3)).findAntiNodes()
        )

    }

    @Test
    fun part1() {
        assertEquals(14, Day8(Reader().Read("day8.example1.txt")).part1())
    }
}