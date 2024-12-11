import org.example.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.util.*

class Day11Test {

    @Test
    fun parse() {
        val input = """125 17""".trimIndent()
        assertEquals(listOf(Stone(125), Stone(17)), Day11Input.parse(input).stones)
    }

    @Test
    fun blinkStone() {
        assertEquals(listOf(Stone(1)), Stone(0).blink())
        assertEquals(listOf(Stone(9), Stone(9)), Stone(99).blink())
        assertEquals(listOf(Stone(10), Stone(0)), Stone(1000).blink())
        assertEquals(listOf(Stone(2024)), Stone(1).blink())
    }

    @Test
    fun blinkList() {
        assertEquals(
            listOf(
                Stone(253000),
                Stone(1),
                Stone(7),
            ), listOf(Stone(125), Stone(17)).blink()
        )

        assertEquals(
            listOf(
                Stone(253),
                Stone(0),
                Stone(2024),
                Stone(14168),
            ), listOf(Stone(125), Stone(17)).blink().blink()
        )

        assertEquals(
            listOf(
                Stone(253),
                Stone(0),
                Stone(2024),
                Stone(14168),
            ), listOf(Stone(125), Stone(17)).blink(2)
        )

        assertEquals(
            listOf(
                Stone(512072),
                Stone(1),
                Stone(20),
                Stone(24),
                Stone(28676032),
            ), listOf(Stone(125), Stone(17)).blink(3)
        )

        assertEquals(
            listOf(
                Stone(512),
                Stone(72),
                Stone(2024),
                Stone(2),
                Stone(0),
                Stone(2),
                Stone(4),
                Stone(2867),
                Stone(6032),
            ), listOf(Stone(125), Stone(17)).blink(4)
        )

        assertEquals(
            listOf(
                Stone(1036288),
                Stone(7),
                Stone(2),
                Stone(20),
                Stone(24),
                Stone(4048),
                Stone(1),
                Stone(4048),
                Stone(8096),
                Stone(28),
                Stone(67),
                Stone(60),
                Stone(32),
            ), listOf(Stone(125), Stone(17)).blink(5)
        )

        assertEquals(22, listOf(Stone(125), Stone(17)).blink(6).count())
        assertEquals(22, listOf(Stone(125), Stone(17)).blink(6).count())
    }

    @Test
    fun part1() {
        assertEquals(55312, Day11("125 17").part1())
    }
}