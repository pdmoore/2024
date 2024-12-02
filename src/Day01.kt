import java.util.*
import java.util.Collections.*
import java.util.stream.IntStream
import kotlin.math.abs

fun main() {
    fun convertStringListToListsOfIntegers(input: List<String>): Pair<List<Int>, List<Int>> {
        var firstList = ArrayList<Int>()
        var secondList = ArrayList<Int>()

        input.forEach { s ->
            val s1 = s.split(" {3,}".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            firstList += s1[0].toInt()
            secondList += s1[1].toInt()
        }
        return Pair(firstList, secondList)
    }

    fun part1(input: List<String>): Int {
        var (firstList: List<Int>, secondList: List<Int>) = convertStringListToListsOfIntegers(input)
        sort(firstList)
        sort(secondList)

        return IntStream
            .range(0, firstList.size)
            .map { i: Int ->
                abs((firstList[i] - secondList[i]).toDouble())
                    .toInt()
            }
            .sum()
    }

    fun part2(input: List<String>): Int {
        var (firstList: List<Int>, secondList: List<Int>) = convertStringListToListsOfIntegers(input)

        val secondListAppearances = HashMap<Int, Int>()
        secondList.forEach { integer ->
            secondListAppearances[integer] = secondListAppearances.getOrDefault(integer, 0) + 1
        }

        return firstList
            .stream()
            .mapToInt { integer: Int -> integer * secondListAppearances.getOrDefault(integer, 0) }
            .sum()
    }

    // Confirm solutions work on example data provided
    val testInput = readInput("Day01_example")
    check(part1(testInput) == 11)
    check(part2(testInput) == 31)

    // Now solve the day's puzzle input
    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
