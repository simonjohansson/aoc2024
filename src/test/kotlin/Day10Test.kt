import org.example.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.util.*

class Day10Test {

    @Test
    fun parse() {
        val input = """
            123
            456
            789
            """.trimIndent()
        assertEquals(
            listOf(
                listOf(1, 2, 3),
                listOf(4, 5, 6),
                listOf(7, 8, 9),
            ), Day10Input.parse(input).grid.nums
        )
    }

    @Test
    fun findStartPositions() {
        val input = """
            120
            023
            301
        """.trimIndent()

        assertEquals(
            listOf(
                GridCord(2, 0),
                GridCord(0, 1),
                GridCord(1, 2),
            ), Day10Input.parse(input).findStartPositions()
        )
    }

    @Test
    fun peek() {
        val input = """
            120
            023
            301
        """.trimIndent()

        // OOB
        assertEquals(Optional.empty<Int>(), Day10Input.parse(input).grid.peek(GridCord(0, -1)))
        assertEquals(Optional.empty<Int>(), Day10Input.parse(input).grid.peek(GridCord(-1, 0)))
        assertEquals(Optional.empty<Int>(), Day10Input.parse(input).grid.peek(GridCord(3, 0)))
        assertEquals(Optional.empty<Int>(), Day10Input.parse(input).grid.peek(GridCord(0, 3)))

        assertEquals(Optional.of(1), Day10Input.parse(input).grid.peek(GridCord(0, 0)))
        assertEquals(Optional.of(0), Day10Input.parse(input).grid.peek(GridCord(2, 0)))
        assertEquals(Optional.of(2), Day10Input.parse(input).grid.peek(GridCord(1, 1)))
        assertEquals(Optional.of(1), Day10Input.parse(input).grid.peek(GridCord(2, 2)))
    }

    @Test
    fun part1() {
        val input = """
            5550555
            5551555
            5552555
            6543456
            7555557
            8555558
            9555559
        """.trimIndent()

        val input2 = """
            1190119
            1111198
            1112117
            6543456
            7651987
            8761111
            9871111
        """.trimIndent()

        assertEquals(2, Day10(input).part1())
        assertEquals(4, Day10(input2).part1())
        assertEquals(36, Day10(Reader.Read("day10.example1.txt")).part1())
    }
}