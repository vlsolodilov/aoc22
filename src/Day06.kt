fun main() {

    fun getStartMarker(data: String, markerLength: Int): Int {
        data.forEachIndexed { index, _ ->
            if (data.toCharArray(index, index + markerLength).distinct().size == markerLength)
                return index + markerLength
        }
        return 0
    }

    fun part1(input: List<String>): Int =
        getStartMarker(input.first(), 4)


    fun part2(input: List<String>): Int =
        getStartMarker(input.first(), 14)

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test")
    check(part1(testInput) == 7)
    check(part2(testInput) == 19)

    val input = readInput("Day06")
    println(part1(input))
    println(part2(input))
}
