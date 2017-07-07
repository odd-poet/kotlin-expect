package net.oddpoet.expect.extension

import net.oddpoet.expect.AssertionPrintingTest
import net.oddpoet.expect.expect
import net.oddpoet.expect.should
import org.junit.Test

/**
 *
 * @author Yunsang Choi
 */
class ExceptionExtensionTest : AssertionPrintingTest() {

    @Test
    fun `test exception message`() {
        expect {
            throw RuntimeException("forced")
        }.throws {
            it.message.should.equal("forced")
        }
    }

    @Test
    fun `test exception null message`() {
        expect {
            throw RuntimeException()
        }.throws {
            it.should.haveNoMessage()
            it.should.haveMessage(null) // same above
        }
    }
}