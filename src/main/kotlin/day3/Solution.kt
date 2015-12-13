package day3

import support.Resource
import support.scanLeft

/**
 * Created by Jian Yuan on 13/12/15.
 */
fun main(args: Array<String>) {
    val input = Resource("day3.txt").readText()

    val moves = input.map { translate(it) }

    println("Number of houses receiving at least one present: ${enumerateMoves(moves).distinct().size}")

    val movePairs = moves.withIndex().partition { it.index % 2 == 0 }
    val santaMoves = movePairs.first.map { it.value }
    val roboSantaMoves = movePairs.second.map { it.value }
    val totalMoves: Set<Pair<Int, Int>> = enumerateMoves(santaMoves).plus(enumerateMoves(roboSantaMoves))
    println("With Robo-Santa: ${totalMoves.size}")
}

private enum class Direction {
    UP {
        override fun apply(current: Pair<Int, Int>): Pair<Int, Int> {
            return current.copy(second = current.second - 1)
        }
    },
    DOWN {
        override fun apply(current: Pair<Int, Int>): Pair<Int, Int> {
            return current.copy(second = current.second + 1)
        }
    },
    LEFT {
        override fun apply(current: Pair<Int, Int>): Pair<Int, Int> {
            return current.copy(first = current.first - 1)
        }
    },
    RIGHT {
        override fun apply(current: Pair<Int, Int>): Pair<Int, Int> {
            return current.copy(first = current.first + 1)
        }
    };

    abstract fun apply(current: Pair<Int, Int>): Pair<Int, Int>
}

private fun translate(input: Char): Direction = when (input) {
    '^' -> Direction.UP
    'v' -> Direction.DOWN
    '<' -> Direction.LEFT
    '>' -> Direction.RIGHT
    else -> throw IllegalArgumentException()
}

private fun enumerateMoves(input: List<Direction>): Set<Pair<Int, Int>> = input
        .scanLeft(Pair(0, 0), { last, direction -> direction.apply(last) })
        .toSet()
