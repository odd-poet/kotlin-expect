package net.oddpoet.expect

import net.oddpoet.expect.extension.be
import net.oddpoet.expect.extension.startWith
import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.time.Instant
import java.time.LocalDateTime
import java.util.*

class LiteralizerTest {
    private val log: Logger = LoggerFactory.getLogger(this.javaClass)

    @Test
    fun `test for string`() {
        Literalizer.literal("hello")
            .should.be("\"hello\"")

        Literalizer.literal("hello\tworld")
            .should.be("\"hello\\tworld\"")
    }

    @Test
    fun `test for char`() {
        Literalizer.literal('c')
            .should.be("'c'")
    }

    @Test
    fun `test for list`() {
        Literalizer.literal(arrayListOf(1, 2, 3))
            .should.be("ArrayList(1,2,3)")
        Literalizer.literal(LinkedList<Int>(listOf(1, 2, 3)))
            .should.be("LinkedList(1,2,3)")
    }

    @Test
    fun `test for numeric`() {
        Literalizer.literal(1).should.be("1")
        Literalizer.literal(2L).should.be("2L")
        Literalizer.literal(3.1).should.be("3.1")
        Literalizer.literal(4.12f).should.be("4.12f")
    }

    @Test
    fun `test for array`() {
        Literalizer.literal(arrayOf(1L, 2L, 3L))
            .should.be("[1L,2L,3L]")
    }

    @Test
    fun `test for regex`() {
        Literalizer.literal(Regex("^hello.*!$"))
            .should.be("/^hello.*!$/")
    }

    @Test
    fun `test for map`() {
        Literalizer.literal(linkedMapOf("KOREA" to "SEOUL", "SPAIN" to "MADRID"))
            .should.be("LinkedHashMap{\"KOREA\":\"SEOUL\",\"SPAIN\":\"MADRID\"}")
    }

    @Test
    fun `test for throwable`() {
        val aException = NoSuchElementException("it's just test")

        Literalizer.literal(aException)
            .should.be("java.util.NoSuchElementException(message=\"it's just test\")")
    }

    @Test
    fun `test for times`() {
        Literalizer.literal(Instant.now()).should.startWith("Instant")
        Literalizer.literal(Date()).should.startWith("Date")
        Literalizer.literal(LocalDateTime.now()).should.startWith("LocalDateTime")
    }
}
