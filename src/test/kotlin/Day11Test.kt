import org.example.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day11Test {
    @Test
    fun blink() {
        assertEquals(
            mapOf(
                253000L to 1L,
                1L to 1L,
                7L to 1L
            ),
            Day11Input.parse("125 17").blink(1)
        )

        assertEquals(
            mapOf(
                253L to 1L,
                0L to 1L,
                2024L to 1L,
                14168L to 1L
            ), Day11Input.parse("125 17").blink(2)
        )

        assertEquals(
            mapOf(
                512072L to 1L,
                1L to 1L,
                20L to 1L,
                24L to 1L,
                28676032L to 1L
            ), Day11Input.parse("125 17").blink(3)
        )

        assertEquals(
            mapOf(
                512L to 1L,
                72L to 1L,
                2024L to 1L,
                2L to 2L,
                0L to 1L,
                4L to 1L,
                2867L to 1L,
                6032L to 1L
            ), Day11Input.parse("125 17").blink(4)
        )

        assertEquals(
            mapOf(
                1036288L to 1L,
                7L to 1L,
                2L to 1L,
                20L to 1L,
                24L to 1L,
                4048L to 2L,
                1L to 1L,
                8096L to 1L,
                28L to 1L,
                67L to 1L,
                60L to 1L,
                32L to 1L,
            ), Day11Input.parse("125 17").blink(5)
        )
    }

    @Test
    fun part1() {
        assertEquals(55312, Day11("125 17").part1())
    }
}