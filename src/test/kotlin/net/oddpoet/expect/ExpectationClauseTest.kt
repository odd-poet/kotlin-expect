package net.oddpoet.expect

import net.oddpoet.expect.extension.beBlank
import net.oddpoet.expect.extension.endWith
import net.oddpoet.expect.extension.startWith
import org.junit.Test

/**
 * @author Yunsang Choi
 */
class ExpectationClauseTest : AssertionPrintTest() {

    @Test
    fun `it should make you write multiple assertion for a subject`() {
        expect("hello") {
            it.should.not.beBlank()
            it.should.startWith("h")
            it.should.not.endWith("x")
        }
    }
}