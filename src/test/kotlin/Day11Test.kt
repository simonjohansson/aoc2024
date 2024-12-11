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

    @Test
    fun blinkMap() {
        assertEquals(
            mutableMapOf(Stone(0L) to 2L, Stone(1L) to 2L, Stone(2L) to 1L),
            listOf(Stone(0), Stone(1), Stone(0), Stone(1), Stone(2)).blinkMap()
        )
    }

    @Test
    fun blinkMapBlink() {
//        assertEquals(mutableMapOf(Stone(253000L) to 1L, Stone(1L) to 1L, Stone(7L) to 1L), Day11Input.parse("125 17").stones.blinkMap().blink(1))
//
//        assertEquals(mutableMapOf(
//            Stone(253L) to 1L,
//            Stone(0L) to 1L,
//            Stone(2024L) to 1L,
//            Stone(14168L) to 1L
//        ), Day11Input.parse("125 17").stones.blinkMap().blink(2))
//
//        assertEquals(mutableMapOf(
//            Stone(512072L) to 1L,
//            Stone(1L) to 1L,
//            Stone(20L) to 1L,
//            Stone(24L) to 1L,
//            Stone(28676032L) to 1L
//        ), Day11Input.parse("125 17").stones.blinkMap().blink(3))
//
//        assertEquals(mutableMapOf(
//            Stone(512L) to 1L,
//            Stone(72L) to 1L,
//            Stone(2024L) to 1L,
//            Stone(2L) to 2L,
//            Stone(0L) to 1L,
//            Stone(4L) to 1L,
//            Stone(2867L) to 1L,
//            Stone(6032L) to 1L
//        ), Day11Input.parse("125 17").stones.blinkMap().blink(4))

        assertEquals(
            mutableMapOf(
                Stone(1036288L) to 1L,
                Stone(7L) to 1L,
                Stone(2L) to 1L,
                Stone(20L) to 1L,
                Stone(24L) to 1L,
                Stone(4048L) to 2L,
                Stone(1L) to 1L,
                Stone(8096L) to 1L,
                Stone(28L) to 1L,
                Stone(67L) to 1L,
                Stone(60L) to 1L,
                Stone(32L) to 1L,
            ), Day11Input.parse("125 17").stones.blinkMap().blink(5)
        )
    }

    @Test
    fun `part1 with map`() {
        assertEquals(22, Day11Input.parse("125 17").stones.blinkMap().blink(6).toList().sumOf { it.second })
    }
}