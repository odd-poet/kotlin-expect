package net.oddpoet.expect.extension

import net.oddpoet.expect.should
import org.junit.Test

/**
 *
 * @author Yunsang Choi
 */
class InputStreamExtensionTest {
    @Test
    fun `test equal as text`() {
        val inputStream = "hello".byteInputStream()
        inputStream.should.equalAsText("hello")
    }
}