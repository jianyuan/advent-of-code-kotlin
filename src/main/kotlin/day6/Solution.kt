package day6

import support.Resource

/**
 * Created by Jian Yuan on 14/12/15.
 */
fun main(args: Array<String>) {
    val input = Resource("day6.txt").readText().lines()
    val instructions = input.flatMap { listOfNotNull(translate(it)) }

    val map = Array(1000, { Array(1000, { State() }) })

    val result = instructions.fold(map, { map, instruction ->
        for (i in instruction.from.first..instruction.to.first)
            for (j in instruction.from.second..instruction.to.second)
                map[i][j] = instruction.action.apply(map[i][j])
        map
    })

    println("Number of lights lit: ${result.sumBy { it.count { it.on } }}")
    println("Total brightness: ${result.sumBy { it.sumBy { it.brightness } }}")
}

private val pattern = """(turn on|toggle|turn off) (\d+),(\d+) through (\d+),(\d+)""".toRegex()

private fun translate(input: String): Instruction? {
    val groups = pattern.find(input)?.groups ?: return null
    return Instruction(
            action = when (groups[1]?.value.orEmpty()) {
                "turn on" -> Action.ON
                "toggle" -> Action.TOGGLE
                "turn off" -> Action.OFF
                else -> throw IllegalArgumentException()
            },
            from = Pair(groups[2]?.value?.toInt() ?: 0, groups[3]?.value?.toInt() ?: 0),
            to = Pair(groups[4]?.value?.toInt() ?: 0, groups[5]?.value?.toInt() ?: 0)
    )
}

private enum class Action {
    ON {
        override fun apply(current: State): State =
                State(on = true, brightness = current.brightness + 1)
    },
    TOGGLE {
        override fun apply(current: State): State =
                State(on = !current.on, brightness = current.brightness + 2)
    },
    OFF {
        override fun apply(current: State): State =
                State(on = false, brightness = (current.brightness - 1).coerceAtLeast(0))
    };

    abstract fun apply(current: State): State
}

private data class State(val on: Boolean = false, val brightness: Int = 0)

private data class Instruction(val action: Action, val from: Pair<Int, Int>, val to: Pair<Int, Int>)
