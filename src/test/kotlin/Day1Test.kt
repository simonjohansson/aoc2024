import org.example.Day1
import org.example.Reader
import kotlin.test.Test
import kotlin.test.assertEquals

class Day1Test {

    @Test
    fun part1() {
        assertEquals(Day1(Reader().Read("day1.example1.txt")).part1(), 11)
    }

    @Test
    fun part2() {
        assertEquals(Day1(Reader().Read("day1.example1.txt")).part2(), 31)
    }
}