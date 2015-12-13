package support

/**
 * Created by Jian Yuan on 13/12/15.
 */
inline fun <T : Any> Iterable<T>.scanLeft(initial: T, operation: (T, T) -> T): List<T> {
    val result = arrayListOf(initial)
    forEach { result.add(operation(result.last(), it)) }
    return result
}
