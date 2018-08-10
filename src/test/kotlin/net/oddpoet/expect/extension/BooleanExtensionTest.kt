package net.oddpoet.expect.extension

import net.oddpoet.expect.expect
import net.oddpoet.expect.should
import org.junit.Test

/**
 *
 * @author Yunsang Choi
 */

class BooleanExtensionTest {
    @Test
    fun `test true`() {
        expect(true) {
            it.should.beTrue()
            it.should.not.beFalse()
        }
    }

    @Test
    fun `test false`() {
        expect(false) {
            it.should.beFalse()
            it.should.not.beTrue()
        }
        expect {
            true.should.beFalse()
        }.throws(AssertionError::class)
    }
}