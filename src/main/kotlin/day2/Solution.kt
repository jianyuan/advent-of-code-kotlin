package day2

import support.Resource

/**
 * Created by Jian Yuan on 13/12/15.
 */
fun main(args: Array<String>) {
    val input = Resource("day2.txt").readText().lines()

    val pattern = """(\d+)x(\d+)x(\d+)""".toRegex()

    val boxes = input
            .flatMap {
                listOfNotNull(pattern.find(it)?.groups)
            }
            .map {
                Box(
                        length = it[1]?.value?.toInt() ?: 0,
                        width = it[2]?.value?.toInt() ?: 0,
                        height = it[3]?.value?.toInt() ?: 0
                )
            }

    println("Square feet of wrapping paper needed: ${boxes.sumBy { it.surfaceArea }}")
    println("Feet of ribbon needed: ${boxes.sumBy { it.ribbonLength }}")
}

data class Box(val length: Int, val width: Int, val height: Int) {
    val surfaceAreas: List<Int> = listOf(length * width, width * height, height * length)
    val smallestSurfaceArea: Int = surfaceAreas.min()!!
    val surfaceArea: Int = 2 * surfaceAreas.sum() + smallestSurfaceArea
    val perimeterLengths: List<Int> = listOf(length + width, width + height, height + length).map { it * 2 }
    val volume: Int = length * width * height
    val ribbonLength: Int = perimeterLengths.min()!! + volume
}