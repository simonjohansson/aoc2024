import org.example.Day2
import org.example.Reader
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day2Test {

    @Test
    fun part1() {
        assertEquals(2, Day2(Reader.Read("day2.example1.txt")).part1())
    }

    @Test
    fun part2() {
        assertEquals(4, Day2(Reader.Read("day2.example1.txt")).part2())
    }
}