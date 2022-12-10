fun main() {

    fun getProgram(line: String): ProgramLine =
        if (line == "noop") {
            ProgramLine(1, 0)
        } else {
            ProgramLine(2, line.split(" ")[1].toInt())
        }

    fun getSignalStrengthList(input: List<String>): List<Int> {
        val signalStrengthList = ArrayList<Int>()
        signalStrengthList.add(1)
        var buffer = 0
        input.map(::getProgram).forEach { programLine ->
            val lastSignalStrength = signalStrengthList.last() + buffer
            repeat(programLine.cycle) {
                signalStrengthList.add(lastSignalStrength)
            }
            buffer = programLine.value
        }
        return signalStrengthList
    }

    fun part1(input: List<String>): Int {
        val signalStrengthList = getSignalStrengthList(input)
        return listOf(20, 60, 100, 140, 180, 220).sumOf { signalStrengthList[it] * it }
    }

    fun part2(input: List<String>) {
        val signalStrengthList = getSignalStrengthList(input)
        for (i in 1..240) {
            if (i % 40 in (signalStrengthList[i])..(signalStrengthList[i] + 2 )) {
                print('#')
            } else {
                print('.')
            }
            if (i != 0 && i % 40 == 0) println()
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day10_test")
    check(part1(testInput) == 13140)
    part2(testInput)
    
    val input = readInput("Day10")
    println(part1(input))
    part2(input)
}

data class ProgramLine(val cycle: Int, val value: Int)
