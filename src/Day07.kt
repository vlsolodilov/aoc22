fun main() {

    fun getDirectoryList(input: List<String>): List<Directory> {
        val dirs = mutableListOf<Directory>()
        var currentDirectory: Directory? = Directory()
        input.forEach { line ->
            if (line.startsWith("$ cd")) {
                if (line.split(" ").last() == "..") {
                   currentDirectory = currentDirectory?.parent
                } else {
                    currentDirectory = Directory(
                        parent = currentDirectory,
                        name = line.split(" ").last()
                    )
                    dirs.add(currentDirectory!!)
                }
            } else if (line[0].isDigit()) {
                val fileSize = line.split(" ")[0].toInt()
                var directory: Directory? = currentDirectory
                while (directory != null) {
                    directory.increaseSize(fileSize)
                    directory = directory.parent
                }
            }
        }
        return dirs
    }

    fun part1(input: List<String>): Int =
        getDirectoryList(input)
            .filter { directory -> directory.totalSize <= 100_000 }
            .sumOf { it.totalSize }


    fun part2(input: List<String>): Int {
        val dirSizeList = getDirectoryList(input).map { it.totalSize }
        val unusedSpace = 70_000_000 - dirSizeList.max()
        return dirSizeList.sorted().first { (unusedSpace + it) >= 30_000_000 }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day07_test")
    check(part1(testInput) == 95_437)
    check(part2(testInput) == 24_933_642)

    val input = readInput("Day07")
    println(part1(input))
    println(part2(input))
}

data class Directory(
    val parent: Directory? = null,
    val name: String = "",
    var totalSize: Int = 0
) {
    fun increaseSize(size: Int) {
        totalSize +=size
    }
}
