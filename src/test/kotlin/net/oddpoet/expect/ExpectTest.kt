package net.oddpoet.expect

import org.junit.Test

/**
 * @author Yunsang Choi
 */
class ExpectTest {
    @Test
    fun `satisfy로 주어진 객체 상태를 검증할 수 있다`() {
        expect("hello").to.satisfy { length == 5 }
    }

    @Test
    fun `not으로 검증조건의 부정형을 사용할 수 있다`() {
        expect(3).to.not.satisfy { this < 1 }
    }

    @Test
    fun `새로운 vocabulary를 정의할 수 있다`() {
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