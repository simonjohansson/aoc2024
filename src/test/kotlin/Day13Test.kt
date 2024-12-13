import org.example.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.util.Optional
import javax.swing.text.html.HTMLEditorKit.Parser

class Day13Test {
    @Test
    fun parse() {
        val input = """
            Button A: X+94, Y+34
            Button B: X+22, Y+67
            Prize: X=8400, Y=5400

            Button A: X+26, Y+66
            Button B: X+67, Y+21
            Prize: X=12748, Y=12176
        """.trimIndent()

        val expected = listOf(
            ClawMachine((94L to 34L), (22L to 67L), (8400L to 5400L)),
            ClawMachine((26L to 66L), (67L to 21L), (12748L to 12176L))

        )

        assertEquals(expected, Day13.parse(input).clawMachines)
    }

    @Test
    fun play() {
        assertEquals(Optional.of(80L to 40L), ClawMachine((94L to 34), (22L to 67), (8400L to 5400)).play())
        assertEquals(Optional.empty<Pair<Long,Long>>(), ClawMachine((26L to 66), (67L to 21), (12748L to 12176)).play())
        assertEquals(Optional.empty<Pair<Long,Long>>(), ClawMachine((69L to 23), (27L to 71), (18641L to 10279)).play())
    }

    @Test
    fun part1() {
        assertEquals(480, Day13.parse(Reader.Read("day13.example1.txt")).part1())
    }
}