import org.example.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day4Test {

    @Test
    fun count() {
        assertEquals(2, Line("XMASAMX.MM").count())
        assertEquals(4, Grid(listOf(Line("XMASAMX.MM"), Line("XMASAMX.MM"))).count())
    }

    @Test
    fun verticalLines() {
        val input = Grid(
            listOf(
                Line("1234"),
                Line("1234"),
                Line("1234"),
                Line("1234"),
            )
        )
        val expected = Grid(
            listOf(
                Line("1111"),
                Line("2222"),
                Line("3333"),
                Line("4444"),
            )
        )

        assertEquals(expected, input.verticalLines())
    }

    @Test
    fun diagonalLines() {
        val input = Grid(
            listOf(
                Line("12345"),
                Line("12345"),
                Line("12345"),
                Line("12345"),
                Line("12345"),
            )
        )
        val expected = Grid(
            listOf(
                Line("12345"),
                Line("2345"),
                Line("345"),
                Line("45"),
                Line("5"),
                Line("1234"),
                Line("123"),
                Line("12"),
                Line("1")

            )
        )

        assertEquals(expected, input.diagonalLines())
    }

    @Test
    fun part1() {
        assertEquals(18, Day4(Reader.Read("day4.example1.txt")).part1())
    }

    @Test
    fun part2() {
        assertEquals(9, Day4(Reader.Read("day4.example2.txt")).part2())
    }

}