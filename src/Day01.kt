import java.util.*
import java.util.stream.IntStream
import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
        var firstList: List<Int> = ArrayList()
        var secondList: List<Int> = ArrayList()

        for (s in input) {
            val s1 = s.split(" {3,}".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            firstList += s1[0].toInt()
            secondList += s1[1].toInt()
        }
        Collections.sort(firstList)
        Collections.sort(secondList)

        return IntStream
            .range(0, firstList.size)
            .map { i: Int ->
                abs((firstList[i] - secondList[i]).toDouble())
                    .toInt()
            }
            .sum()
    }

    fun part2(input: List<String>): Int {
        var firstList: List<Int> = ArrayList()
        var secondList: List<Int> = ArrayList()

        for (s in input) {
            val s1 = s.split(" {3,}".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            firstList += s1[0].toInt()
            secondList += s1[1].toInt()
        }

        val secondListAppearance = HashMap<Int, Int>()
        for (integer in secondList) {
            secondListAppearance[integer] = secondListAppearance.getOrDefault(integer, 0) + 1
        }

        return firstList
            .stream()
            .mapToInt { integer: Int -> integer * secondListAppearance.getOrDefault(integer, 0) }
            .sum()
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day01_example")
    check(part1(testInput) == 11)
    check(part2(testInput) == 31)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
