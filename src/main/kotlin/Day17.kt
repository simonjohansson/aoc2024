package org.example

import java.math.BigInteger
import java.util.*
import java.util.Optional.empty
import kotlin.jvm.optionals.getOrElse
import kotlin.math.pow


data class Day17(
    val A: Int = 0,
    val B: Int = 0,
    val C: Int = 0,
    val ops: List<Int> = emptyList(),
    val pointer: Int = 0,
    val out: List<Int> = emptyList(),
) {
    companion object {
        fun parse(input: String) = input.split("\n\n").let { parts ->
            val registers = parts.first().lines().map { register ->
                register.split(": ").last().toInt()
            }
            val ops = parts.last().split(": ").last().split(",").map { it.toInt() }
            Day17(registers[0], registers[1], registers[2], ops)
        }
    }

    private fun readNextInstruction() = Pair(ops[pointer], ops[pointer + 1])

    fun literalOperand(operand: Int) = operand
    fun comboOperand(operand: Int) = when (operand) {
        0, 1, 2, 3 -> operand
        4 -> A
        5 -> B
        6 -> C
        else -> throw IllegalArgumentException("Invalid combo operand $operand")
    }

    private fun adv(operand: Int) = (A / 2.0.pow(comboOperand(operand))).toInt()
    private fun bxl(operand: Int) = B xor literalOperand(operand)
    private fun bst(operand: Int) = comboOperand(operand) % 8
    private fun jnz(operand: Int) = if (A == 0) pointer + 2 else literalOperand(operand)
    private fun bxc() = B xor C
    private fun out(operand: Int) = comboOperand(operand) % 8

    private fun computeNext() = readNextInstruction().let { (op, operand) ->
        when (op) {
            0 -> this.copy(A = adv(operand), pointer = pointer + 2)
            1 -> this.copy(B = bxl(operand), pointer = pointer + 2)
            2 -> this.copy(B = bst(operand), pointer = pointer + 2)
            3 -> this.copy(pointer = jnz(operand))
            4 -> this.copy(B = bxc(), pointer = pointer + 2)
            5 -> this.copy(out = out + out(operand), pointer = pointer + 2)
            6 -> this.copy(B = adv(operand), pointer = pointer + 2)
            7 -> this.copy(C = adv(operand), pointer = pointer + 2)
            else -> throw IllegalArgumentException("Invalid operand $op")
        }
    }

    fun compute(): Day17 {
        var curr = this
        while (curr.pointer < curr.ops.count()) {
            println(curr)
            curr = curr.computeNext()
        }
        println(curr)
        return curr
    }

    fun part1() = this.compute().out.joinToString(",")
}