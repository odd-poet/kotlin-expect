package net.oddpoet.expect

import org.junit.Test
import org.slf4j.LoggerFactory

/**
 * @author Yunsang Choi
 */
class ExpectationTest {
    val log = LoggerFactory.getLogger(this.javaClass)

    @Test
    fun `it should used 'expect subj to satisfy' style`() {
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
            expect("하이루").to.satisfyThat("be a greeting") {
                arrayOf("hello", "aloha", "안녕")
                        .contains(it)
            }
        }.throws(AssertionError::class) {
            it.should.satisfy {
                log.debug("error: {}", this)
                message?.contains("should be a greeting") ?: false
            }
        }
    }
}