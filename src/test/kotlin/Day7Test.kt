import org.example.Day7
import org.example.Day7Line
import org.example.Reader
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import javax.swing.text.html.HTMLEditorKit.Parser

class Day7Test {

    @Test
    fun parse() {
        val input = """
            190: 10 19
            3267: 81 40 27
        """.trimIndent()

        val expected = listOf(
            Day7Line(190, listOf(10, 19)),
            Day7Line(3267, listOf(81, 40, 27)),
        )

        assertEquals(expected, Day7Line.parse(input))
    }

    @Test
    fun part1() {
        assertEquals(3749, Day7(Reader().Read("day7.example1.txt")).part1())

    }
}