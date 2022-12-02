import java.lang.Exception
import java.lang.RuntimeException

fun main() {

    fun getScoreOfShape(round: String): Int =
        when (round.last()) {
            'X' -> 1
            'Y' -> 2
            'Z' -> 3
            else -> throw Exception("Error getScoreOfShape")
        }

    fun getScoreOfOutcome(round: String): Int =
        when (round) {
            "A Y", "B Z", "C X" -> 6
            "A X", "B Y", "C Z" -> 3
            else -> 0
        }


    fun part1(input: List<String>): Int =
        input.sumOf { round ->
            getScoreOfOutcome(round) + getScoreOfShape(round)
        }

    fun getScoreOfShape2(round: String): Int =
        when (round) {
            "A Y", "B X", "C Z" -> 1
            "A Z", "B Y", "C X" -> 2
            "A X", "B Z", "C Y" -> 3
            else -> throw Exception("Error getScoreOfShape2")
        }

    fun getScoreOfOutcome2(round: String): Int =
        when (round.last()) {
            'X' -> 0
            'Y' -> 3
            'Z' -> 6
            else -> throw Exception("Error getScoreOfOutcome2")
        }

    fun part2(input: List<String>): Int =
        input.sumOf { round ->
            getScoreOfOutcome2(round) + getScoreOfShape2(round)
        }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 15)
    check(part2(testInput) == 12)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
