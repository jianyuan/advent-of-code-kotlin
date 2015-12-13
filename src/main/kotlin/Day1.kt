/**
 * Created by Jian Yuan on 13/12/15.
 */
class Day1(val inputFileName: String) {
    fun translate(input: Char): Int = when (input) {
        '(' -> 1
        ')' -> -1
        else -> throw IllegalArgumentException()
    }

    fun printAnswer() {
        val rawInput = javaClass.classLoader.getResource(inputFileName).readText()

        val steps = rawInput.map { translate(it) }

        val positions = steps.scanLeft(0, { sum, step -> sum + step })

        println("Final floor: ${positions.last()}")
        println("First enter basement at: ${positions.indexOf(-1)}")
    }
}

fun main(args: Array<String>) {
    Day1("day1.txt").printAnswer()
}
