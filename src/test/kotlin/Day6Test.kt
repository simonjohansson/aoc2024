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
        assertEquals(expected, Day6Input.parse(Reader().Read("day6.example1.txt")))
    }

    @Test
    fun part1() {
        assertEquals(41, Day6(Reader().Read("day6.example1.txt")).part1())
    }
}