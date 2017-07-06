package net.oddpoet.expect.extension

import net.oddpoet.expect.AssertionPrintTest
import net.oddpoet.expect.expect
import net.oddpoet.expect.should
import org.junit.Test

class CollectionExtensionTest : AssertionPrintTest() {

    @Test
    fun `test contain`() {

        val sut = listOf("hello", "world")

        expect(sut).to.contain("hello")
        expect(sut).to.contain("world")
        expect(sut).to.not.contain("aloha")
    }

    @Test
    fun `test contain with predicate`() {
        val sut = listOf(2, 4, 8)

        expect(sut).to.containAny { it % 4 == 0 }
        expect(sut).to.not.containAny { it % 3 == 0 }
        expect(sut).to.containAll { it % 2 == 0 }
        expect(sut).to.not.containAll { it % 3 == 0 }
        expect(sut).to.containNone { it % 3 == 0 }
    }

    @Test
    fun `test containAll`() {
        val sut = setOf("hello", "world")

        sut.should.containAll("hello", "world")
        sut.should.containAll("hello")
        sut.should.not.containAll("hi", "man")
    }

    @Test
    fun `test size of collection`() {

        expect(listOf(1, 2, 3)).to.haveSizeOf(3)
        expect(listOf<Any>()).to.haveSizeOf(0)
        expect(listOf(1)).to.not.haveSizeOf(0)
    }

    @Test
    fun `test beEmpty`() {
        expect(listOf(1, 2)).to.not.beEmpty()
        expect(listOf<Any>()).to.beEmpty()
    }

    @Test
    fun `test containInSameOrder`() {
        val sut = listOf("John", "Bob", "Jack", "Alice", "Bob")

        sut.should.containAllInSameOrder("Bob", "Jack")
        sut.should.containAllInSameOrder("John", "Alice")
        // i will consider only first occurrence.
        sut.should.not.containAllInSameOrder("Alice", "Bob") // right? or wrong?
    }

    @Test
    fun `test sorted`() {
        listOf(1, 5, 9, 20).should.beSorted()
        listOf(5, 3, 11, 0).should.not.beSorted()
        listOf(2, 1, 0).should.beReverseSorted()
        listOf("Alice", "John", "Xavier").should.beSorted()
    }

    @Test
    fun `test sorted with comparator`() {
        listOf("Alice", "john").should.beSortedWith(String.CASE_INSENSITIVE_ORDER)

        listOf("xab", "Cda", "bun").should.beReverseSortedWith(String.CASE_INSENSITIVE_ORDER)
    }

    @Test
    fun `test if map contains key`() {
        val sut = mapOf("john" to 30, "jane" to 25)

        expect(sut) {
            it.should.containKey("john")
            it.should.containKeys("john", "jane")
            it.should.not.containKey("bart")
        }
    }

    @Test
    fun `test if map contains a value`() {
        val sut = mapOf("john" to 30, "jane" to 25)
        expect(sut) {
            it.should.containValue(30)
            it.should.containValues(25, 30)
            it.should.not.containValue(31)
        }
    }

    @Test
    fun `test if map contains an entry`() {
        val sut = mapOf("john" to 30, "jane" to 25)
        expect(sut) {
            it.should.containEntry("jane", 25)
            it.should.containEntries("jane" to 25, "john" to 30)
            it.should.not.containEntry("bart", 28)
        }
    }
}