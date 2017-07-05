package net.oddpoet.expect

import org.junit.Test
import org.slf4j.LoggerFactory

/**
 * @author Yunsang Choi
 */
class SubjectExpectationTest {
    val log = LoggerFactory.getLogger(this.javaClass)

    @Test
    fun `expect to 형태로 주어진 값에 대해 검증할 수 있다`() {
        expect("hello").to.satisfy { length == 5 }
    }

    @Test
    fun `expect 한 결과와 다른 경우 assertionError가 발생한다`() {
        expect {
            expect(3).to.satisfy { this < 0 }
        }.throws(AssertionError::class)
    }

    @Test
    fun `검증 실패시 발생하는 메시지를 제어할 수 있다`() {
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