import java.util.regex.Pattern

fun main() {
    // Confirm solutions work on example data provided
    val testInput = "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))"
    check(part1(testInput) == 161)
    check(part2(testInput) == 48)

    // Now solve the day's puzzle input
    val input = readInput("Day03")
    part1(input.toString()).println()
    part2(input.toString()).println()
}

fun part1(input: String): Any {
    val pattern = Pattern.compile("mul\\(\\d{1,3},\\d{1,3}\\)")
    val matcher = pattern.matcher(input)

    var result = 0
    while (matcher.find()) {
        val match = matcher.group().replace("mul(", "").replace(")", "")
        val split = match.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        result += split[0].toInt() * split[1].toInt()
    }
    return result
}

fun part2(input: String): Any {
    val pattern = Pattern.compile("don't\\(\\).*?do\\(\\)", Pattern.DOTALL)
    return part1(java.lang.String.join("", *pattern.split(input)))
}


