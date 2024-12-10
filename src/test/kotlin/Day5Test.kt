import org.example.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day5Test {

    @Test
    fun parse() {
        val expected = Day5Input(
            listOf(
                Rule(47, 53),
                Rule(97, 13),
                Rule(97, 61),
                Rule(97, 47),
                Rule(75, 29),
                Rule(61, 13),
                Rule(75, 53),
                Rule(29, 13),
                Rule(97, 29),
                Rule(53, 29),
                Rule(61, 53),
                Rule(97, 53),
                Rule(61, 29),
                Rule(47, 13),
                Rule(75, 47),
                Rule(97, 75),
                Rule(47, 61),
                Rule(75, 61),
                Rule(47, 29),
                Rule(75, 13),
                Rule(53, 13),
            ),
            listOf(
                Update(listOf(75, 47, 61, 53, 29)),
                Update(listOf(97, 61, 53, 29, 13)),
                Update(listOf(75, 29, 13)),
                Update(listOf(75, 97, 47, 61, 53)),
                Update(listOf(61, 13, 29)),
                Update(listOf(97, 13, 75, 29, 47)),
            )
        )

        assertEquals(expected, Day5Input.parse(Reader.Read("day5.example1.txt")))
    }

    @Test
    fun part1() {
        assertEquals(143, Day5(Reader.Read("day5.example1.txt")).part1())
    }

    @Test
    fun part2() {
        assertEquals(123, Day5(Reader.Read("day5.example1.txt")).part2())
    }
}