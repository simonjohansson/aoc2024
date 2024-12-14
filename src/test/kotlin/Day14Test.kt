import org.example.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.util.Optional
import javax.swing.text.html.HTMLEditorKit.Parser
import kotlin.math.round

class Day14Test {
    @Test
    fun parse() {
        val input = """
            p=0,4 v=3,-3
            p=6,3 v=-1,-3
            p=10,3 v=-1,2
            """.trimIndent()

        val expected = listOf(
            Robot((0 to 4), (3 to -3)),
            Robot((6 to 3), (-1 to -3)),
            Robot((10 to 3), (-1 to 2)),
        )

        assertEquals(expected, Day14.parse(input).robots)
    }

    @Test
    fun move() {
        val robot = Robot((2 to 4), (2 to -3))
        assertEquals((2 to 4), robot.move(11, 7, 0).p)
        assertEquals((4 to 1), robot.move(11, 7, 1).p)
        assertEquals((6 to 5), robot.move(11, 7, 2).p)
        assertEquals((8 to 2), robot.move(11, 7, 3).p)
        assertEquals((10 to 6), robot.move(11, 7, 4).p)
        assertEquals((1 to 3), robot.move(11, 7, 5).p)
    }

    @Test
    fun part1() {
        val day = Day14.parse(Reader.Read("day14.example1.txt")).copy(width = 11, height = 7)
        assertEquals(12, day.part1())
    }
}