fun main() {
    fun getElfCaloriesList(input: List<String>): List<Int> {
        var currentSumCalories = 0
        val calorieList = mutableListOf<Int>()
        input.forEach {
            if (it.isNotBlank()) {
                currentSumCalories += it.toInt()
            } else {
                calorieList.add(currentSumCalories)
                currentSumCalories = 0
            }
        }
        return calorieList
    }

    fun part1(input: List<String>): Int =
        getElfCaloriesList(input).max()


    fun part2(input: List<String>): Int =
        getElfCaloriesList(input).sortedDescending().take(3).sum()

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 24000)
    check(part2(testInput) == 45000)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
