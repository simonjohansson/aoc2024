import org.example.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day10Test {

    @Test
    fun parse() {
        val input = """
            123
            456
            789
            """.trimIndent()
        assertEquals(listOf(
            listOf(1,2,3),
            listOf(4,5,6),
            listOf(7,8,9),
        ), Day10Input.parse(input).nums)
    }

    @Test
    fun part1() {
        assertEquals(36, Day10(Reader.Read("day10.example1.txt")).part1())
    }
}