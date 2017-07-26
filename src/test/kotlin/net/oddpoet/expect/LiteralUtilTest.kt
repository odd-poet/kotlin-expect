package net.oddpoet.expect

import jdk.nashorn.internal.runtime.regexp.RegExp
import net.oddpoet.expect.extension.be
import org.junit.Test
import java.util.*

class LiteralUtilTest {

    @Test
    fun `test for string`() {
        LiteralUtil.literal("hello")
                .should.be("\"hello\"")

        LiteralUtil.literal("hello\tworld")
                .should.be("\"hello\\tworld\"")
    }

    @Test
    fun `test for char`() {
        LiteralUtil.literal('c')
                .should.be("'c'")
    }

    @Test
    fun `test for list`() {
        LiteralUtil.literal(arrayListOf(1, 2, 3))
                .should.be("ArrayList(1,2,3)")
        LiteralUtil.literal(LinkedList<Int>(listOf(1, 2, 3)))
                .should.be("LinkedList(1,2,3)")
    }

    @Test
    fun `test for numeric`() {
        LiteralUtil.literal(1).should.be("1")
        LiteralUtil.literal(2L).should.be("2L")
        LiteralUtil.literal(3.1).should.be("3.1")
        LiteralUtil.literal(4.12f).should.be("4.12f")
    }

    @Test
    fun `test for array`() {
        LiteralUtil.literal(arrayOf(1L,2L,3L))
                .should.be("[1L,2L,3L]")
    }

    @Test
    fun `test for regex`() {
        LiteralUtil.literal(Regex("^hello.*!$"))
                .should.be("/^hello.*!$/")
    }

    @Test
    fun `test for map`() {
        LiteralUtil.literal(linkedMapOf("KOREA" to "SEOUL", "SPAIN" to "MADRID"))
                .should.be("LinkedHashMap{\"KOREA\":\"SEOUL\",\"SPAIN\":\"MADRID\"}")
    }
}