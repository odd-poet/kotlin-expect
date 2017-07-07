package net.oddpoet.expect.usage

import net.oddpoet.expect.AssertionPrintingTest
import net.oddpoet.expect.expect
import net.oddpoet.expect.extension.be
import net.oddpoet.expect.extension.beBlankOrNull
import net.oddpoet.expect.extension.endWith
import net.oddpoet.expect.extension.startWith
import net.oddpoet.expect.should
import org.junit.Test
import java.io.IOException
import java.nio.file.NoSuchFileException


class BasicUsageTest : AssertionPrintingTest() {

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
            it.message.should.be("fake exception")
        }
    }

    @Test
    fun `it should use Exception class as default when given type`() {
        expect {
            throw Exception("hello")
        }.throws()
    }

    @Test
    fun `it should verify given predicate using 'satisfy'`() {
        expect("hello").to.satisfy { length == 5 }
    }

    @Test
    fun `it should throw AssertionError when fail to test`() {
        expect {
            expect(3).to.satisfy { this < 0 }
        }.throws(AssertionError::class)
    }

    @Test
    fun `it should make you describe assertion message`() {
        expect {
            expect("thanks").to.satisfyThat("be a greeting") {
                arrayOf("hello", "aloha", "안녕")
                        .contains(it)
            }
        }.throws(AssertionError::class) {
            it.should.satisfy {
                log.debug("error: {}", this.message, this)
                message?.contains("should be a greeting") ?: false
            }
        }
    }

    @Test
    fun `it should test subject by given predicate`() {
        expect("hello").to.satisfy { length == 5 }
    }

    @Test
    fun `it should negative test for subject`() {
        expect(3).to.not.satisfy { this < 1 }
    }

    @Test
    fun `it should support block for multiple assertion`() {
        expect("hello") {
            it.should.not.beBlankOrNull()
            it.should.startWith("he")
            it.should.endWith("o")
        }
    }
}