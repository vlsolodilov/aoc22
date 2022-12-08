fun main() {

    fun fillMatrix(input: List<String>): List<List<Int>> =
        input.map { line ->
            line.map { it.digitToInt() }
        }

    fun checkLeft(y: Int, x: Int, matrix: List<List<Int>>): Boolean {
        val currentTree = matrix[y][x]
        matrix[y].forEachIndexed { index, tree ->
           if ((index < x) && ( tree - currentTree >= 0 )) return false
        }
        return true
    }

    fun checkRight(y: Int, x: Int, matrix: List<List<Int>>): Boolean {
        val currentTree = matrix[y][x]
        matrix[y].forEachIndexed { index, tree ->
            if ((index > x) && ( tree - currentTree >= 0 )) return false
        }
        return true
    }

    fun checkTop(y: Int, x: Int, matrix: List<List<Int>>): Boolean {
        val currentTree = matrix[y][x]
        for (i in 0 until y) {
            if (matrix[i][x] - currentTree >= 0) return false
        }
        return true
    }

    fun checkBottom(y: Int, x: Int, matrix: List<List<Int>>): Boolean {
        val currentTree = matrix[y][x]
        for (i in y+1 until matrix.size) {
            if (matrix[i][x] - currentTree >= 0) return false
        }
        return true
    }

    fun part1(input: List<String>): Int {
        var count = 0
        val matrix = fillMatrix(input)
        for (i in 1..matrix.size-2) {
            for (j in 1..matrix[0].size-2) {
                if (checkLeft(i,j,matrix)
                    || checkRight(i,j,matrix)
                    || checkTop(i,j,matrix)
                    || checkBottom(i,j,matrix)) {
                    count ++
                }
            }
        }
        return count + (matrix.size + matrix[0].size - 2) * 2
    }

    fun sumLeft(y: Int, x: Int, matrix: List<List<Int>>): Int {
        val currentTree = matrix[y][x]
        var count = 0
        for (i in x - 1 downTo 0) {
            if (matrix[y][i] < currentTree) {
                count++
            } else if (matrix[y][i] >= currentTree){
                count++
                break
            } else {
                break
            }
        }
        return count
    }

    fun sumRight(y: Int, x: Int, matrix: List<List<Int>>): Int {
        val currentTree = matrix[y][x]
        var count = 0
        for (i in x + 1 until matrix[y].size) {
            if (matrix[y][i] < currentTree) {
                count++
            } else if (matrix[y][i] >= currentTree){
                count++
                break
            } else {
                break
            }
        }
        return count
    }

    fun sumTop(y: Int, x: Int, matrix: List<List<Int>>): Int {
        val currentTree = matrix[y][x]
        var count = 0
        for (i in y - 1 downTo 0) {
            if (matrix[i][x] < currentTree) {
                count++
            } else if (matrix[i][x] >= currentTree){
                count++
                break
            } else {
                break
            }
        }
        return count
    }

    fun sumBottom(y: Int, x: Int, matrix: List<List<Int>>): Int {
        val currentTree = matrix[y][x]
        var count = 0
        for (i in y + 1 until matrix.size) {
            if (matrix[i][x] < currentTree) {
                count++
            } else if (matrix[i][x] >= currentTree){
                count++
                break
            } else {
                break
            }
        }
        return count
    }

    fun part2(input: List<String>): Int {
        var score = 0
        val matrix = fillMatrix(input)
        for (i in 1..matrix.size-2) {
            for (j in 1..matrix[0].size-2) {
                val currentScore = sumLeft(i,j,matrix) * sumRight(i,j,matrix) * sumTop(i,j,matrix) * sumBottom(i,j,matrix)
                if (currentScore > score) score = currentScore
            }
        }
        return score
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day08_test")
    check(part1(testInput) == 21)
    check(part2(testInput) == 8)

    val input = readInput("Day08")
    println(part1(input))
    println(part2(input))
}
