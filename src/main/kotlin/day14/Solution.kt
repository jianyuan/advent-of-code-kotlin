package day14

import support.Resource

/**
 * Created by Jian Yuan on 16/12/15.
 */
fun main(args: Array<String>) {
    val input = Resource("day14.txt").readText().lines()

    val reindeer = input.map { translate(it) }.filterNotNull().toMap()

    val distance = 2503

    val distanceTraveled = reindeer.mapValues { it.value.finalDistance(distance) }.maxBy { it.value }
    println("Distance traveled: $distanceTraveled")

    val raceTimeline = (1..distance).map { second -> reindeer.mapValues { it.value.finalDistance(second) } }
    val winningPoints = raceTimeline
            .flatMap { distances ->
                val maxDistance = distances.maxBy { it.value }?.value ?: return Unit
                distances.filterValues { it == maxDistance }.keys.toList()
            }
            .groupBy { it }
            .mapValues { it.value.size }
            .maxBy { it.value }
    println("Points: $winningPoints")
}

private val pattern = """(\w+) can fly (\d+) km/s for (\d+) seconds, but then must rest for (\d+) seconds.""".toRegex()

private fun translate(input: String): Pair<String, Stats>? {
    val groups = pattern.find(input)?.groups ?: return null
    return Pair(
            groups[1]?.value.orEmpty(),
            Stats(
                    speed = groups[2]?.value?.toInt() ?: 0,
                    flyTime = groups[3]?.value?.toInt() ?: 0,
                    restTime = groups[4]?.value?.toInt() ?: 0
            )
    )
}

private data class Stats(val speed: Int, val flyTime: Int, val restTime: Int) {
    val totalTime: Int by lazy { flyTime + restTime }

    fun finalDistance(duration: Int): Int {
        val distance = (duration / totalTime) * flyTime
        val remainingDistance = Math.min(duration % totalTime, flyTime)
        return (distance + remainingDistance) * speed
    }
}
