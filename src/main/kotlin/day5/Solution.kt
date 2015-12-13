package day5

import support.Resource

/**
 * Created by Jian Yuan on 13/12/15.
 */
fun main(args: Array<String>) {
    val input = Resource("day5.txt").readText().lines()

    println("There are ${input.count { isNice(it) }} nice string(s)")
    println("There are ${input.count { isNice2(it) }} nice string(s) under new rules")
}

private val threeVowels = """[aeiou].*[aeiou].*[aeiou]""".toRegex()
private val twiceInARow = """(\w)\1""".toRegex()
private val naughtyStrings = """(ab|cd|pq|xy)""".toRegex()

private fun isNice(input: String): Boolean =
        input.contains(threeVowels)
                && input.contains(twiceInARow)
                && !input.contains(naughtyStrings)


private val pairAtLeastTwice = """(\w)(\w).*\1\2""".toRegex()
private val sandwiched = """(\w)\w\1""".toRegex()

private fun isNice2(input: String): Boolean =
        input.contains(pairAtLeastTwice)
                && input.contains(sandwiched)
