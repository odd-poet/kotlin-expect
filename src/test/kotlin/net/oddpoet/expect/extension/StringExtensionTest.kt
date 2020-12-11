package net.oddpoet.expect.extension

import net.oddpoet.expect.expect
import net.oddpoet.expect.should
import org.junit.jupiter.api.Test

class StringExtensionTest {

    @Test
    fun `test startWith`() {
        expect("hello") {
            it.should.startWith("he")
            it.should.not.startWith("x")
            it.should.startWith('h')
        }
    }

    @Test
    fun `test startWith ignoreCase`() {
        expect("Hello") {
            it.should.startWith("h", ignoreCase = true)
            it.should.startWith('h', ignoreCase = true)
        }
    }

    @Test
    fun `test endWith`() {
        expect("hello world") {
            it.should.endWith("world")
            it.should.endWith('d')
            it.should.not.endWith("o")
        }
    }

    @Test
    fun `test endWith ignore case`() {
        expect("hello world") {
            it.should.endWith("WORLD", ignoreCase = true)
            it.should.endWith('D', ignoreCase = true)
            it.should.not.endWith("o", ignoreCase = true)
        }
    }

    @Test
    fun `test beEmpty`() {
        "".should.beEmpty()
        "hello".should.not.beEmpty()
        " ".should.not.beEmpty()
        (null as String?).should.not.beEmpty()
    }

    @Test
    fun `test beEmptyOrNull`() {
        (null as String?).should.beEmptyOrNull()
    }

    @Test
    fun `test beBlank`() {
        " ".should.beBlank()
        "\t".should.beBlank()
        "\n".should.beBlank()
        "\\n".should.not.beBlank()
    }

    @Test
    fun `test beBlankOrNull`() {
        val nil: String? = null
        nil.should.not.beBlank()
        nil.should.beBlankOrNull()
    }

    @Test
    fun `test match regex`() {
        expect("hello").to.match("^h.*o$")
        "\\".should.match("^\\\\")
    }

    @Test
    fun `test contain`() {
        "hello".should.containString("ll")
        "hello".should.containString("EL", ignoreCase = true)
        "hello".should.containChar('l')
        "hello".should.containChar('O', ignoreCase = true)
    }

    @Test
    fun `test haveLengthOf`() {
        "abc".should.haveLengthOf(3)
        "hello".should.haveLengthOf(5)
    }

    @Test
    fun `test haveLengthBetween`() {
        "abc".should.haveLengthIn(3..6)
        "hello".should.not.haveLengthIn(1 until 5)
    }
}
