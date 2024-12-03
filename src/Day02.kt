fun main() {


    fun isSafe(line: String?): Boolean {
        val levels = line!!.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val decreasing = levels[0].toInt() > levels[1].toInt()
        for (i in 1..<levels.size) {
            val next = levels[i].toInt()
            val delta = if (decreasing) {
                levels[i - 1].toInt() - next
            } else {
                next - levels[i - 1].toInt()
            }
            if (delta <= 0 || delta > 3) {
                return false
            }
        }

        return true
    }

    fun part1(input: List<String>): Int {
        return input
            .stream()
            .filter { line: String? -> isSafe(line) }
            .count().toInt()
    }

    fun isTolerantSafe(line: String?): Boolean {
        // for each line, slice out one level and test if the resulting report is safe or not
        val levels = line!!.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

        for (i in levels.indices) {
            val reportWithOneSkippedLevel = StringBuilder()
            for (j in levels.indices) {
                if (i != j) {
                    reportWithOneSkippedLevel.append(levels[j]).append(" ")
                }
            }
            if (isSafe(reportWithOneSkippedLevel.toString())) {
                return true
            }
        }

        return false
    }

    fun part2(input: List<String>): Int {
        return input
            .stream()
            .filter { line: String? -> isTolerantSafe(line) }
            .count().toInt()
    }



    // Confirm solutions work on example data provided
    val testInput = readInput("Day02_example")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)



    // Now solve the day's puzzle input
    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}

