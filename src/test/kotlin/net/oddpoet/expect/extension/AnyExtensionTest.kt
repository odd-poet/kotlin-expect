package net.oddpoet.expect.extension

import net.oddpoet.expect.AssertionPrintTest
import net.oddpoet.expect.expect
import org.junit.Test
import org.slf4j.LoggerFactory

/**
 *
 * @author Yunsang Choi
 */
class AnyExtensionTest : AssertionPrintTest() {
    val log = LoggerFactory.getLogger(this.javaClass)

    @Test
    fun `test value equality`() {
        expect("hello").to.be("hello")
        expect(123).to.not.be(122)
    }

    @Test
    fun `test value equality negative`() {
        expect {
            expect("hello").to.be("world")
        }.throws(AssertionError::class)

        expect {
            expect(1).to.not.be(1)
        }.throws(AssertionError::class)

    }

    @Test
    fun `test nullable`() {
        var subj: String? = null
        expect(subj).to.beNull()

        expect {
            expect("not null").to.beNull()
        }.throws(AssertionError::class)

    }

    @Test
    fun `test not null`() {
        expect("hello").to.not.beNull()

        expect {
            expect(null).to.not.beNull()
        }.throws(AssertionError::class)
    }

    @Test
    fun `test beInstanceOf`() {
        val subj = listOf(1, 2, 3)
        expect(subj).to.beInstanceOf(List::class)
        expect(subj).to.beInstanceOf(Collection::class)
        expect(subj).to.beInstanceOf(Iterable::class)
    }

    @Test
    fun `test not beInstanceOf`() {
        val subj = mapOf(
                "john doe" to "US",
                "yunsang" to "Korea")

        log.debug("class : {}", subj.javaClass)
        expect(subj).to.not.beInstanceOf(List::class)
        expect(subj).to.not.beInstanceOf(String::class)
    }
}