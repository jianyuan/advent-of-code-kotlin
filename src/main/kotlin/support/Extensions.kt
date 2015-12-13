package support

/**
 * Created by Jian Yuan on 13/12/15.
 */
inline fun <T, R> Iterable<T>.scanLeft(initial: R, operation: (R, T) -> R): List<R> {
    val result = arrayListOf(initial)
    forEach { result.add(operation(result.last(), it)) }
    return result
}
