import org.example.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day9Test {

    @Test
    fun parse() {
        assertEquals("0..111....22222", Day9Input.parse("12345").toString().toString())
        assertEquals("00...111...2...333.44.5555.6666.777.888899", Day9Input.parse("2333133121414131402").toString())
    }

    @Test
    fun fragment() {
        assertEquals("022111222......", Day9Input.parse("12345").fragment().toString())
        assertEquals(
            "0099811188827773336446555566..............",
            Day9Input.parse("2333133121414131402").fragment().toString()
        )
    }

    @Test
    fun flatten() {
        assertEquals(
            listOf(
                File(0),
                File(1),
                File(1),
                File(2),
                File(2),
                File(2),
                Free(),
                Free(),
                Free(),
                Free(),
                Free(),
                Free()
            ), Day9Input.parse("12345").fragment().flatten()
        )
    }

    @Test
    fun part1() {
        assertEquals(1928, Day9("2333133121414131402").part1())
    }

    @Test
    fun moveFiles() {
        assertEquals("00992111777.44.333....5555.6666.....8888..", Day9Input.parse("2333133121414131402").moveFiles().toString())
    }

    @Test
    fun part2() {
        assertEquals(2858, Day9("2333133121414131402").part2())
    }

}