import org.example.*
import org.junit.jupiter.api.Assertions.*
import kotlin.test.Test

class Day6Test {
    @Test
    fun parse() {
        val expected = Day6Input(
            listOf(
                "....#.....",
                ".........#",
                "..........",
                "..#.......",
                ".......#..",
                "..........",
                ".#........", // We change the guard to a . in pos 4
                "........#.",
                "#.........",
                "......#...",
            ),
            Guard(4, 6, Direction.UP)
        )
        assertEquals(expected, Day6Input.parse(Reader.Read("day6.example1.txt")))
    }

    @Test
    fun testNastyEdgeCase() {
        val inputStr = """
            ..>.#
            ...#.
        """.trimIndent()

        val expected = listOf(
            Guard(x=2, y=0, direction= Direction.RIGHT),
            Guard(x=3, y=0, direction= Direction.RIGHT),
            Guard(x=3, y=0, direction= Direction.DOWN),
            Guard(x=3, y=0, direction= Direction.LEFT),
            Guard(x=2, y=0, direction= Direction.LEFT),
            Guard(x=1, y=0, direction= Direction.LEFT),
            Guard(x=0, y=0, direction= Direction.LEFT)
        ) to false
        val input = Day6Input.parse(inputStr)
        assertEquals(expected, Day6(inputStr).traverse(input))
    }

    @Test
    fun part1() {
        assertEquals(41, Day6(Reader.Read("day6.example1.txt")).part1())
    }

    @Test
    fun part2() {
        assertEquals(6, Day6(Reader.Read("day6.example1.txt")).part2())
    }
}