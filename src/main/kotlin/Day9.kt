package org.example

sealed interface Block {
    fun size(): Int
    fun increaseSize(): Block
    fun decreasSize(): Block
}

data class Free(val size: Int = 1) : Block {
    override fun size() = size
    override fun increaseSize() = this.copy(size = size + 1)
    override fun decreasSize() = this.copy(size = size - 1)


    override fun toString() = ".".repeat(size)
}

data class File(val index: Long, val size: Int = 1) : Block {
    override fun size() = size
    override fun toString() = index.toString().repeat(size)
    override fun increaseSize() = this.copy(size = size + 1)
    override fun decreasSize() = this.copy(size = size - 1)
}

data class Day9Input(val blocks: List<Block>) {
    companion object {
        fun parse(input: String) = Day9Input(
            input.toCharArray().map { it.toString().toInt() }.let { blocks ->
                blocks.drop(1).fold(listOf<Block>(File(index = 0, size = blocks.first()))) { acc, curr ->
                    if (acc.last() is File) {
                        acc + Free(curr)
                    } else {
                        val lastFile: File = acc.findLast { it is File } as File
                        acc + File(lastFile.index + 1, curr)
                    }
                }
            }
        )
    }

    override fun toString(): String {
        return this.blocks.map { it.toString() }.joinToString("")
    }

    private fun isFragmented(tmp: List<Block>) =
        tmp.indexOfFirst { it is Free }.let { indexOfFirstFreeBlock ->
            tmp.indexOfLast { it is File }.let { indexOfLastFile ->
                indexOfLastFile < indexOfFirstFreeBlock
            }
        }

    fun fragment(): Day9Input {
        var tmp = blocks.toMutableList()
        while (!isFragmented(tmp)) {
            val firstFreeIndex = tmp.indexOfFirst { it is Free }
            val firstFree = tmp[firstFreeIndex] as Free
            val lastFileIndex = tmp.indexOfLast { it is File }
            val lastFile = tmp[lastFileIndex] as File

            tmp[lastFileIndex] = lastFile.decreasSize()
            tmp[firstFreeIndex] = firstFree.decreasSize()

            tmp = (tmp.subList(0, firstFreeIndex) +
                    File(lastFile.index) +
                    tmp.subList(firstFreeIndex, tmp.count()))
                .filter { it.size() > 0 }
                .toMutableList()

            if (tmp.last() is Free) {
                tmp[tmp.count() - 1] = tmp.last().increaseSize()
            } else {
                tmp = (tmp + Free()).toMutableList()
            }
        }

        return Day9Input(tmp.toList())
    }

    fun flatten(): List<Block> {
        return this.blocks.fold(listOf()) { acc, block ->
            acc + generateSequence { if (block is File) File(block.index) else Free() }.take(block.size())
        }
    }

    fun moveFiles(): Day9Input {
        var tmp = blocks.toMutableList()

        for (block in tmp.reversed()) {

            if (block is Free) {
                continue
            }
            val index = tmp.indexOfFirst { it == block }
            val indexOfFirstFreeBigEnough = tmp.indexOfFirst { it is Free && it.size() >= block.size() }

            if (indexOfFirstFreeBigEnough == -1 || indexOfFirstFreeBigEnough > index) {
                continue
            }

            if (tmp[indexOfFirstFreeBigEnough].size() == block.size()) {
                tmp[indexOfFirstFreeBigEnough] = block
                tmp[index] = Free(block.size())
            } else {
                tmp[index] = Free(block.size())
                tmp = (tmp.subList(0, indexOfFirstFreeBigEnough) +
                        block +
                        Free(tmp[indexOfFirstFreeBigEnough].size() - block.size()) +
                        tmp.subList(indexOfFirstFreeBigEnough + 1, tmp.size)).toMutableList()
            }
        }

        return Day9Input(tmp)
    }
}

data class Day9(val input: String) {
    fun sum(blocks: List<Block>) = generateSequence(0.toLong()) { it + 1 }.zip(blocks.asSequence())
        .sumOf { (i, f) ->
            when (f) {
                is File -> i * f.index
                is Free -> 0
            }
        }


    fun part1() = Day9Input.parse(input).let { disk ->
        sum(disk.fragment().flatten())
    }


    fun part2() = Day9Input.parse(input).let { disk ->
        sum(disk.moveFiles().flatten())
    }

}