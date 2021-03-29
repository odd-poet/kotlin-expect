package net.oddpoet.expect.extension

import net.oddpoet.expect.should
import org.junit.jupiter.api.Test

/**
 *
 * @author Yunsang Choi
 */
class ReaderExtensionTest {
    @Test
    fun `test equal as text`() {
        "hello".reader().should.equalAsText("hello")
        "hello".reader().should.not.equalAsText("heLLo")
        "hello".reader().should.equalAsText("heLLo", ignoreCase = true)
    }

    @Test
    fun `test reader contain text`() {
        "hello world".reader().should.contain("hello")
        "hello world".reader().should.contain("world")
        "hello world".reader().should.not.contain("HELLo")
    }
}
