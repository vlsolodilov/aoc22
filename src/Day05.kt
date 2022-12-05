fun main() {

    val testCrates: List<Stack<Char>> = listOf<Stack<Char>>(
        mutableListOf('Z', 'N'),
        mutableListOf('M', 'C', 'D'),
        mutableListOf('P'),
    )

    val crates: List<Stack<Char>> = listOf<Stack<Char>>(
        mutableListOf('N', 'S', 'D', 'C', 'V', 'Q', 'T'),
        mutableListOf('M', 'F', 'V'),
        mutableListOf('F', 'Q', 'W', 'D', 'P', 'N', 'H', 'M'),
        mutableListOf('D', 'Q', 'R', 'T', 'F'),
        mutableListOf('R', 'F', 'M', 'N', 'Q', 'H', 'V', 'B'),
        mutableListOf('C', 'F', 'G', 'N', 'P', 'W', 'Q'),
        mutableListOf('W', 'F', 'R', 'L', 'C', 'T'),
        mutableListOf('T', 'Z', 'N', 'S'),
        mutableListOf('M', 'S', 'D', 'J', 'R', 'Q', 'H', 'N'),
    )

    fun getRearrangementProcedure(procedure: String): RearrangementProcedure {
        val procedureInput = procedure.split(" ")
        return RearrangementProcedure(
            count = procedureInput[1].toInt(),
            from = procedureInput[3].toInt() - 1,
            to = procedureInput[5].toInt() - 1,
        )
    }

    fun doRearrangement(crates: List<Stack<Char>>, procedure: RearrangementProcedure) =
        repeat(procedure.count) {
            crates[procedure.to].push(crates[procedure.from].pop()!!)
        }

    fun doRearrangement2(crates: List<Stack<Char>>, procedure: RearrangementProcedure) {
        crates[procedure.to] += crates[procedure.from].takeLast(procedure.count)
        repeat(procedure.count) {
            crates[procedure.from].pop()
        }
    }

    fun getTopCrates(crates: List<Stack<Char>>): String =
        crates.map { it.peek() }.joinToString("")

    fun part1(crates: List<Stack<Char>>, input: List<String>): String {
        val cratesCopy = crates.map { it.toMutableList() }.toMutableList()
        input.map(::getRearrangementProcedure).forEach {
                doRearrangement(cratesCopy, it)
        }
        return getTopCrates(cratesCopy)
    }



    fun part2(crates: List<Stack<Char>>, input: List<String>): String {
        val cratesCopy = crates.map { it.toMutableList() }.toMutableList()
        input.map(::getRearrangementProcedure).forEach {
            doRearrangement2(cratesCopy, it)
        }
        return getTopCrates(cratesCopy)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")
    check(part1(testCrates, testInput) == "CMZ")
    check(part2(testCrates, testInput) == "MCD")

    val input = readInput("Day05")
    println(part1(crates, input))
    println(part2(crates, input))
}

data class RearrangementProcedure(val count: Int, val from: Int, val to: Int)

/**
 * Stack as type alias of Mutable List
 */
typealias Stack<T> = MutableList<T>

/**
 * Pushes item to [Stack]
 * @param item Item to be pushed
 */
fun <T> Stack<T>.push(item: T) = add(item)

/**
 * Pops (removes and return) last item from [Stack]
 * @return item Last item if [Stack] is not empty, null otherwise
 */
fun <T> Stack<T>.pop(): T? = if (isNotEmpty()) removeAt(lastIndex) else null

/**
 * Peeks (return) last item from [Stack]
 * @return item Last item if [Stack] is not empty, null otherwise
 */
fun <T> Stack<T>.peek(): T? = if (isNotEmpty()) this[lastIndex] else null

