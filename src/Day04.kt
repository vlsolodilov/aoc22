fun main() {

    fun getSectionPairOfElves(pair: String): Pair<IntRange,IntRange> =
        pair.split('-',',')
            .map { it.toInt() }
            .chunked(2)
            .map { IntRange(it[0], it[1]) }
            .let { it[0] to it[1] }


    fun part1(input: List<String>): Int =
        input.map(::getSectionPairOfElves).filter {
            with(it) { first.all(second::contains) || second.all(first::contains) }
        }.size

    fun part2(input: List<String>): Int =
        input.map(::getSectionPairOfElves).filter { (it.first.any(it.second::contains)) }.size

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}
