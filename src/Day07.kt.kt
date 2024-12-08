import java.math.BigInteger

fun main() {
    // Confirm solutions work on example data provided
    val testInput = readInput("Day07_example")
    check(part1(testInput) == BigInteger("3749"))
    check(part2(testInput) == BigInteger("11387"))







}

fun part1(input: List<String>): Any {
    var result = BigInteger.ZERO

    for (line in input) {
        val split = line.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val lhs = split[0]
        val rhs = split[1].trim { it <= ' ' }.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

        if (canBeCalibrated(lhs, rhs, false)) {
            result = result.add(BigInteger(lhs))
        }
    }

    return result
}

fun part2(input: List<String>): Any {
    var result = BigInteger.ZERO

    for (line in input) {
        val split = line.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val lhs = split[0]
        val rhs = split[1].trim { it <= ' ' }.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

        if (canBeCalibrated(lhs, rhs, true)) {
            result = result.add(BigInteger(lhs))
        }
    }

    return result
}

fun canBeCalibrated(lhs: String, rhs: Array<String>, tryConcatenation: Boolean): Boolean {
    val target = BigInteger(lhs)

    val bigIntegersRemaining: MutableList<BigInteger> = ArrayList()
    for (rh in rhs) {
        bigIntegersRemaining.add(BigInteger(rh))
    }
    return canBeCalibrated(target, bigIntegersRemaining, tryConcatenation)
}

private fun canBeCalibrated(
    target: BigInteger,
    bigIntegersRemaining: MutableList<BigInteger>,
    tryConcatenation: Boolean
): Boolean {
    val x1 = bigIntegersRemaining.removeFirst()
    val x2 = bigIntegersRemaining.removeFirst()

    val concatenation = BigInteger(x1.toString() + x2.toString())
    if (bigIntegersRemaining.isEmpty()) {
        val isConcatenated = tryConcatenation && target == concatenation
        val isAdded = target == x1.add(x2)
        val isMultiplied = target == x1.multiply(x2)
        return isConcatenated ||
                isAdded ||
                isMultiplied
    } else {
        val addedList: MutableList<BigInteger> = java.util.ArrayList(bigIntegersRemaining)
        addedList.addFirst(x1.add(x2))
        if (canBeCalibrated(target, addedList, tryConcatenation)) {
            return true
        }

        val multipliedList: MutableList<BigInteger> = java.util.ArrayList(bigIntegersRemaining)
        multipliedList.addFirst(x1.multiply(x2))
        if (canBeCalibrated(target, multipliedList, tryConcatenation)) {
            return true
        }

        if (tryConcatenation) {
            val concatenationList: MutableList<BigInteger> = java.util.ArrayList(bigIntegersRemaining)
            concatenationList.addFirst(concatenation)
            return canBeCalibrated(target, concatenationList, tryConcatenation)
        }

        return false
    }
}
