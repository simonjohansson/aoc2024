package org.example

sealed interface Block
data object Free : Block {
    override fun toString(): String {
        return "."
    }
}

class File(val index: Long) : Block {
    override fun toString(): String {
        return index.toString()
    }
}

fun List<Block>.stringify() = this.joinToString("") { it.toString() }

data class Day9Input(val nums: List<Block>) {
    companion object {
        fun parse(input: String) = Day9Input(
            input.toCharArray().map { it.toString().toInt() }
                .fold(Triple(emptyList<Block>(), false, 0)) { (acc, isEmpty, index), num ->
                    (if (isEmpty) generateSequence { Free }.take(num) else generateSequence { File(index.toLong()) }.take(
                        num
                    )).let { toAdd ->
                        Triple(acc + toAdd, !isEmpty, if (isEmpty) index else index + 1)
                    }

                }.first
        )
    }

    override fun toString(): String {
        return nums.stringify()
    }

    private fun isFragmented(tmp: Array<Block>) =
        tmp.indexOfFirst { it is Free }.let { indexOfFirstFreeBlock ->
            tmp.indexOfLast { it is File }.let { indexOfLastFile ->
                indexOfLastFile < indexOfFirstFreeBlock
            }
        }

    fun fragment(): Day9Input {
        val tmp = nums.toTypedArray()
        while (!isFragmented(tmp)) {
            val firstFree = tmp.indexOfFirst { it is Free }
            val lastFile = tmp.indexOfLast { it is File }
            tmp[firstFree] = tmp[lastFile]
            tmp[lastFile] = Free
        }
        return Day9Input(tmp.toList())
    }
}

data class Day9(val input: String) {
    fun part1() = Day9Input.parse(input).fragment().let { disk ->
        generateSequence(0.toLong()) { it + 1 }.zip(disk.nums.takeWhile { it is File }.map { it as File }.asSequence())
            .sumOf { (i, f) ->
                i * f.index
            }
    }
}