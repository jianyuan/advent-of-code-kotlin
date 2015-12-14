package day6

import support.Resource

/**
 * Created by Jian Yuan on 14/12/15.
 */
fun main(args: Array<String>) {
    val input = Resource("day6.txt").readText().lines()
    val instructions = input.map { translate(it) }

    val map = Array(1000, { BooleanArray(1000) })
}

private val pattern = """(turn on|toggle|turn off) (\d+),(\d+) through (\d+),(\d+)""".toRegex()

private fun translate(input: String): Triple<Pair<Int, Int>, Pair<Int, Int>, Action>? {
    val result = pattern.find(input) ?: return null
    val groups = result.groups
    return Triple(
            Pair(groups[2]!!.value.toInt(), groups[3]!!.value.toInt()),
            Pair(groups[4]!!.value.toInt(), groups[5]!!.value.toInt()),
            when (groups[1]!!.value) {
                "turn on" -> Action.TURN_ON
                "toggle" -> Action.TOGGLE
                "turn off" -> Action.TURN_OFF
                else -> throw IllegalArgumentException()
            }
    )
}

private enum class Action {
    TURN_ON, TOGGLE, TURN_OFF
}