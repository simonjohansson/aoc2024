import org.example.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day17Test {
    @Test
    fun parse() {
        val input = """
            Register A: 729
            Register B: 0
            Register C: 0

            Program: 0,1,5,4,3,0
            """.trimIndent()

        val expected = Day17(
            A = 729,
            B = 0,
            C = 0,
            ops = listOf(0, 1, 5, 4, 3, 0),
            pointer = 0,
        )

        assertEquals(expected, Day17.parse(input))
    }

    @Test
    fun adv() {
        assertEquals(2, Day17(A = 8, ops = listOf(0, 2)).compute().A)
        assertEquals(4, Day17(A = 16, B = 2, ops = listOf(0, 5)).compute().A)
    }

    @Test
    fun bxl() {
        // If register B contains 29, the program 1,7 would set register B to 26.
        assertEquals(26, Day17(B = 29, ops = listOf(1, 7)).compute().B)
    }

    @Test
    fun bst() {
        // If register C contains 9, the program 2,6 would set register B to 1.
        assertEquals(1, Day17(C = 9, ops = listOf(2, 6)).compute().B)
    }

    @Test
    fun jnz() {
        assertEquals(1, Day17(ops = listOf(3)).compute().pointer)
        assertEquals(1337, Day17(A = 1337, ops = listOf(3)).compute().pointer)
    }

    @Test
    fun bxc() {
        assertEquals(1, Day17(ops = listOf(4, 0)).compute().C)
    }

    @Test
    fun tests() {
        // If register C contains 9, the program 2,6 would set register B to 1.
        assertEquals(1, Day17(C = 9, ops = listOf(2, 6)).compute().B)

        // If register A contains 10, the program 5,0,5,1,5,4 would output 0,1,2.
        assertEquals(listOf(0, 1, 2), Day17(A = 10, ops = listOf(5, 0, 5, 1, 5, 4)).compute().out)

        // If register A contains 2024, the program 0,1,5,4,3,0 would output 4,2,5,6,7,7,7,7,3,1,0 and leave 0 in register A.
        val case3 = Day17(A = 2024, ops = listOf(0, 1, 5, 4, 3, 0)).compute()
        assertEquals(0, case3.A)
        assertEquals(listOf(4, 2, 5, 6, 7, 7, 7, 7, 3, 1, 0), case3.out)

        // If register B contains 29, the program 1,7 would set register B to 26.
        assertEquals(26, Day17(B = 29, ops = listOf(1, 7)).compute().B)

        // If register B contains 2024 and register C contains 43690, the program 4,0 would set register B to 44354.
        assertEquals(44354, Day17(B = 2024, C = 43690, ops = listOf(4, 0)).compute().B)
    }

    @Test
    fun part1() {
        val input = """
            Register A: 729
            Register B: 0
            Register C: 0

            Program: 0,1,5,4,3,0
        """.trimIndent()

        assertEquals("4,6,3,5,6,3,5,2,1,0", Day17.parse(input).part1())
    }
}