import org.example.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
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
            ClawMachine((94 to 34), (22 to 67), (8400 to 5400)),
            ClawMachine((26 to 66), (67 to 21), (12748 to 12176))

        )

        assertEquals(expected, Day13.parse(input).clawMachines)
    }

    @Test
    fun play() {
        assertEquals((80 to 40), ClawMachine((94 to 34), (22 to 67), (8400 to 5400)).play())
        assertEquals((141 to 135), ClawMachine((26 to 66), (67 to 21), (12748 to 12176)).play())
        assertEquals((244 to 65), ClawMachine((69 to 23), (27 to 71), (18641 to 10279)).play())
    }

    @Test
    fun part1() {
        assertEquals(480, Day13.parse(Reader.Read("day13.example1.txt")).part1())
    }
}