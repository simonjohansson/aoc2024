import org.example.Day2
import org.example.Day3
import org.example.Reader
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day3Test {

    @Test
    fun part1() {
        assertEquals(161, Day3(Reader().Read("day3.example1.txt")).part1())
    }

    @Test
    fun part2() {
        assertEquals(48, Day3(Reader().Read("day3.example2.txt")).part2())
    }
}