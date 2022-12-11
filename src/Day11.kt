fun main() {

    val monkeyListTest = listOf<Monkey>(
        Monkey(0, mutableListOf(79, 98), { old -> old * 19}, 23, 2, 3),
        Monkey(1, mutableListOf(54, 65, 75, 74), {old -> old + 6}, 19, 2, 0),
        Monkey(2, mutableListOf(79, 60, 97), {old -> old * old}, 13, 1, 3),
        Monkey(3, mutableListOf(74), {old -> old + 3}, 17, 0, 1),
    )

    val monkeyList = listOf<Monkey>(
        Monkey(0, mutableListOf(62, 92, 50, 63, 62, 93, 73, 50), { old -> old * 7}, 2, 7, 1),
        Monkey(1, mutableListOf(51, 97, 74, 84, 99), {old -> old + 3}, 7, 2, 4),
        Monkey(2, mutableListOf(98, 86, 62, 76, 51, 81, 95), {old -> old + 4}, 13, 5, 4),
        Monkey(3, mutableListOf(53, 95, 50, 85, 83, 72), {old -> old + 5}, 19, 6, 0),
        Monkey(4, mutableListOf(59, 60, 63, 71), { old -> old * 5}, 11, 5, 3),
        Monkey(5, mutableListOf(92, 65), { old -> old * old}, 5, 6, 3),
        Monkey(6, mutableListOf(78), { old -> old + 8}, 3, 0, 7),
        Monkey(7, mutableListOf(84, 93, 54), { old -> old + 1}, 17, 2, 1),
    )

    fun startRound(monkeys: List<Monkey>, worryDivider: Int) {
        val commonDivisor = monkeys.map { it.divisibleBy }.reduce(Long::times)
        monkeys.forEach {  monkey ->
            while (monkey.items.isNotEmpty()) {
                monkey.inspectedItems++
                var item = monkey.items.removeFirst()
                item = monkey.operation(item) / worryDivider % commonDivisor
                val toMonkey = if (item % monkey.divisibleBy == 0L) {
                    monkey.firstMonkey
                } else {
                    monkey.secondMonkey
                }
                monkeys[toMonkey].items.add(item)
            }
        }
    }

    fun part1(monkeys: List<Monkey>): Long {
        val monkeysCopy = monkeys.map { it.copy() }.toMutableList()
        repeat(20) {
            startRound(monkeysCopy, 3)
        }
        return  monkeysCopy.map { it.inspectedItems }.sortedDescending().take(2).reduce(Long::times)
    }


    fun part2(monkeys: List<Monkey>): Long {
        val monkeysCopy = monkeys.map { it.copy() }.toMutableList()
        repeat(10_000) {
            startRound(monkeysCopy, 1)
        }
        return  monkeysCopy.map { it.inspectedItems }.sortedDescending().take(2).reduce(Long::times)
    }

    // test if implementation meets criteria from the description, like:
    check(part1(monkeyListTest) == 10605L)
    check(part2(monkeyListTest) == 2_713_310_158L)

    println(part1(monkeyList))
    println(part2(monkeyList))
}

data class Monkey(
    val name: Int,
    val items: MutableList<Long>,
    val operation: (Long) -> Long,
    val divisibleBy: Long,
    val firstMonkey: Int,
    val secondMonkey: Int,
    var inspectedItems: Long = 0,
)
