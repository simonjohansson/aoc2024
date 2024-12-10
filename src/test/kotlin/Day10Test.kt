import org.example.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day10Test {

    @Test
    fun parse() {
        assertEquals("0..111....22222", Day9Input.parse(Reader.Read("day10.example1.txt")).toString())
    }

    @Test
    fun part1() {
        assertEquals(36, Day9("2333133121414131402").part1())
    }
}