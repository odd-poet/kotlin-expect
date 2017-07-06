package net.oddpoet.expect.extension

import net.oddpoet.expect.expect
import org.junit.Test

/**
 *
 * @author Yunsang Choi
 */
class ExceptionExtensionTest {

    @Test
    fun `test exception message`() {
        expect {
            throw RuntimeException("forced")
        }.throws {
            it.should.haveMessage("forced")
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