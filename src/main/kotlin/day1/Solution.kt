package day1

import support.Resource
import support.scanLeft

/**
 * Created by Jian Yuan on 13/12/15.
 */
fun main(args: Array<String>) {
    val input = Resource("day1.txt").readText()
    val steps = input.map { translate(it) }

    val positions = steps.scanLeft(0, { sum, step -> sum + step })

    println("Final floor: ${positions.last()}")
    println("First enter basement at: ${positions.indexOf(-1)}")
}

private fun translate(input: Char): Int = when (input) {
    '(' -> 1
    ')' -> -1
    else -> throw IllegalArgumentException()
}
