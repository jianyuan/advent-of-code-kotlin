package support

import java.net.URL

/**
 * Created by Jian Yuan on 13/12/15.
 */
class Resource(val name: String) {
    val resourceURL: URL = javaClass.classLoader.getResource(name)

    fun readText(): String = resourceURL.readText()
}
