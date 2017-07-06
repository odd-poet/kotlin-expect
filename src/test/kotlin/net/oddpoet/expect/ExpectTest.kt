package net.oddpoet.expect

import org.junit.Test

/**
 * @author Yunsang Choi
 */
class ExpectTest : AssertionPrintTest() {
    @Test
    fun `it should test subject by given predicate`() {
        expect("hello").to.satisfy { length == 5 }
    }

    @Test
    fun `it should negative test for subject`() {
        expect(3).to.not.satisfy { this < 1 }
    }

    @Test
    fun `it should make you define own your vocabulary`() {
        fun <T : Int> Expect<T>.beOddNumber() =
                satisfyThat("be odd number") {
                    it?.let { it % 2 == 1 } ?: false
                }

        expect(3).to.beOddNumber()
        expect {
            expect(22).to.beOddNumber()
        }.throws(AssertionError::class)
    }


}