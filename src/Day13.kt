import java.math.BigInteger

fun main() {
    val testInput = readInput("Day13_example")
    check(part1(testInput, BigInteger.ZERO) == BigInteger("480"))
    check(part1(testInput, BigInteger("10000000000000")) == BigInteger("875318608908"))

    val input = readInput("Day13")
    part1(input, BigInteger.ZERO).println()
    part1(input, BigInteger("10000000000000")).println()
}

fun part1(input: List<String>, offset: BigInteger): Any {
    var tokensNeeded = BigInteger.ZERO

        var i = 0
        while (i < input.size) {
            val tokenCost: BigInteger = findWinningMovesPart2(input.subList(i, i + 3), offset)
            tokensNeeded = tokensNeeded.add(tokenCost)
            i += 4
        }

    return tokensNeeded
}

fun findWinningMovesPart2(input: List<String>, offset: BigInteger): BigInteger {
    val aValues: Array<String> =
        input.get(0).replace("Button A:", "").trim { it <= ' ' }.split(",".toRegex()).dropLastWhile { it.isEmpty() }
            .toTypedArray() // a deltas
    val bValues: Array<String> =
        input.get(1).replace("Button B:", "").trim { it <= ' ' }.split(",".toRegex()).dropLastWhile { it.isEmpty() }
            .toTypedArray() // a deltas
    val targets: Array<String> =
        input.get(2).replace("Prize:", "").trim { it <= ' ' }.split(",".toRegex()).dropLastWhile { it.isEmpty() }
            .toTypedArray() // a deltas

    val aXmovement = BigInteger(aValues[0].split("\\+".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[1])
    val aYmovement = BigInteger(aValues[1].split("\\+".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[1])

    val bXmovement = BigInteger(bValues[0].split("\\+".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[1])
    val bYmovement = BigInteger(bValues[1].split("\\+".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[1])

    val longX = targets[0].split("=".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[1].toLong()
    val longY = targets[1].split("=".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[1].toLong()

    val prize0 = offset.add(BigInteger.valueOf(longX))
    val prize1 = offset.add(BigInteger.valueOf(longY))

    val det = aXmovement.multiply(bYmovement)
        .subtract(aYmovement.multiply(bXmovement))

    val a = prize0.multiply(bYmovement)
        .subtract(prize1.multiply(bXmovement))
        .divide(det)

    val b = prize1.multiply(aXmovement)
        .subtract(prize0.multiply(aYmovement))
        .divide(det)

    val checkX = a.multiply(aXmovement)
        .add(b.multiply(bXmovement))
    val checkY = a.multiply(aYmovement)
        .add(b.multiply(bYmovement))
    if (prize0 == checkX && prize1 == checkY) {
        return a.multiply(BigInteger.valueOf(3)).add(b)
    }

    return BigInteger.ZERO
}

