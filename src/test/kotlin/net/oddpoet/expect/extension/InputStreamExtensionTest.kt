package net.oddpoet.expect.extension

import net.oddpoet.expect.should
import org.junit.jupiter.api.Test
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

    @Test
    fun `test contain text`() {
        "hello world".byteInputStream().should.contain("hello")
        "hello world".byteInputStream().should.contain("world")
        "hello world".byteInputStream().should.not.contain("HELLo")
        "hello world".byteInputStream().should.contain("HELLo", ignoreCase = true)
    }


}
