package net.oddpoet.expect

import net.oddpoet.expect.extension.haveMessage
import org.junit.Test
import java.io.IOException
import java.nio.file.NoSuchFileException

/**
 * @author Yunsang Choi
 */
class ErrorExpectationTest {
    @Test
    fun `it should verify exception type thrown in code block`() {
        expect {
            throw IOException()
        }.throws(IOException::class)
    }

    @Test
    fun `it should verify exception type thrown in code block by super class`() {
        expect {
            throw NoSuchFileException("/dummy")
        }.throws(IOException::class)
    }

    @Test(expected = AssertionError::class)
    fun `it should verify wrong exception type thrown in code block`() {
        expect {
            throw IOException()
        }.throws(NoSuchFileException::class)
    }

    @Test
    fun `it should verify exception object`() {
        expect {
            throw IOException("fake exception")
        }.throws(IOException::class) {
            it.should.haveMessage("fake exception")
        }
    }

    @Test
    fun `it should use Exception class as default when given type`() {
        expect {
            throw Exception("hello")
        }.throws()
    }
}