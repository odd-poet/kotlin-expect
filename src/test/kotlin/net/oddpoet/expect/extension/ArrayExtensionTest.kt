package net.oddpoet.expect.extension

import net.oddpoet.expect.should
import org.junit.Test

/**
 *
 * @author Yunsang Choi
 */
class ArrayExtensionTest {

    @Test
    fun `test contain`() {
        arrayOf("hello", "world").should.contain("hello")
        arrayOf("hello", "world").should.not.contain("kotlin")
    }

    @Test
    fun `test haveSizeOf`() {
        arrayOf(1, 3, 5, 7).should.haveSizeOf(4)
        arrayOf(1, 3).should.haveSizeOf(2)
        arrayOf(1, 3, null).should.not.haveSizeOf(2)
    }

    @Test
    fun `test beEmpty`() {
        arrayOf(1, 2).should.not.beEmpty()
        arrayOf<Int>().should.beEmpty()
    }

    @Test
    fun `test containAll`() {
        arrayOf(1, 2, 3).should.containAll(2, 3)
        arrayOf(1, 2, 3).should.not.containAll(1, 2, 3, 4)
        arrayOf(1, 2, 3).should.not.containAll(4)
    }

    @Test
    fun `test containAllInSameOrder`() {
        arrayOf(3, 1, 7, 9).should.containAllInSameOrder(3, 1, 7, 9)
        arrayOf(3, 1, 7, 9).should.containAllInSameOrder(3, 7, 9)
        arrayOf(3, 1, 7, 9).should.not.containAllInSameOrder(1, 3, 7, 9)
        arrayOf(3, 1, 7, 9).should.containAllInSameOrder(1, 9)
    }

    @Test
    fun `test containAll with predicate`() {
        arrayOf("hello", "world").should.containAll { it.contains('l') }
        arrayOf(2, 8, 6).should.containAll { it % 2 == 0 }
        arrayOf(2, 3, 4).should.not.containAll { it % 2 == 0 }
    }

    @Test
    fun `test containAny with predicate`() {
        arrayOf("hello", "world").should.containAny { it.startsWith("h") }
        arrayOf("hello", "world").should.containAny { it.endsWith("d") }
        arrayOf("hello", "world").should.not.containAny { it.length == 2 }
    }

    @Test
    fun `test containNone with predicate`() {
        arrayOf(1, 3, 5).should.containNone { it % 2 == 0 }
        arrayOf(1, 3, 6).should.not.containNone { it % 2 == 0 }
    }

    @Test
    fun `test beSorted`() {
        arrayOf(1, 3, 4, 9).should.beSorted()
        arrayOf(7, 3, 4, 9).should.not.beSorted()
    }

    @Test
    fun `test beReverseSorted`() {
        arrayOf(7, 5, 4, 2, 1).should.beReverseSorted()
        arrayOf(7, 5, 2, 4, 1).should.not.beReverseSorted()
    }

    @Test
    fun `test beSortedWith`() {
        arrayOf("hello", "HI", "world").should.beSortedWith(String.CASE_INSENSITIVE_ORDER)
        arrayOf("world", "HI", "hello").should.not.beSortedWith(String.CASE_INSENSITIVE_ORDER)
    }

    @Test
    fun `test beReverseSortedWith`() {
        arrayOf("world", "HI", "hello").should.beReverseSortedWith(String.CASE_INSENSITIVE_ORDER)
        arrayOf("hello", "HI", "world").should.not.beReverseSortedWith(String.CASE_INSENSITIVE_ORDER)
    }
}