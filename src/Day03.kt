fun main() {

    fun getEqualItems(rucksack: String): Char {
        val rucksackItems = rucksack.toList()
        val rucksackSize = rucksackItems.size
        val firstCompartment = rucksackItems.subList(0, (rucksackSize + 1) / 2)
        val secondCompartment = rucksackItems.subList((rucksackSize + 1) / 2, rucksackSize)
        return firstCompartment.first { item -> secondCompartment.contains(item) }
    }

    fun getPriorityItem(item: Char): Int {
        val codeDelta =  if (item.isLowerCase()) 96 else 38
        return item.code - codeDelta
    }

    fun part1(input: List<String>): Int =
        input.map { getEqualItems(it) }.sumOf { getPriorityItem(it) }

    fun getGroupEqualItems(group: List<String>): Char =
        group[0].toList().first { item ->
            group[1].toList().contains(item) && group[2].toList().contains(item) }


    fun part2(input: List<String>): Int =
        input.chunked(3).map(::getGroupEqualItems).sumOf(::getPriorityItem)


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 157)
    check(part2(testInput) == 70)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
