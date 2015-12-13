package day4

import support.Resource
import java.security.MessageDigest

/**
 * Created by Jian Yuan on 13/12/15.
 */
fun main(args: Array<String>) {
    val input = Resource("day4.txt").readText()

    val candidates = IntRange(0, Int.MAX_VALUE).asSequence()

    println("Answer (5 zeros): ${candidates.first { md5(input + it.toString()).startsWith("00000") }}")
    println("Answer (6 zeros): ${candidates.first { md5(input + it.toString()).startsWith("000000") }}")
}

private val md5Instance = MessageDigest.getInstance("MD5")

private fun md5(input: String): String =
        md5Instance.digest(input.toByteArray()).map { "%02x".format(it) }.joinToString("")
