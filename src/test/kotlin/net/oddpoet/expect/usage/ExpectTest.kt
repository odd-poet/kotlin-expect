package net.oddpoet.expect.usage

import net.oddpoet.expect.Expect
import net.oddpoet.expect.expect
import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 *
 * @author Yunsang Choi
 */
class ExpectTest {
    private val log: Logger = LoggerFactory.getLogger(this.javaClass)
    @Test
    fun `cannot use equals`() {
        expect {
            Expect("hello") == Expect("hello")
        }.throws()
    }

    @Test
    fun `cannot use hashcode`() {
        expect {
            log.debug("hashcode: {}", Expect("hello").hashCode())
        }.throws()
    }

    @Test
    fun `cannot use toString`() {
        expect {
            log.debug("toString: {}", Expect("hello").toString())
        }.throws()
    }
}
