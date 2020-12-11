package net.oddpoet.expect.extension

import net.oddpoet.expect.should
import org.junit.jupiter.api.Test

class LongExtensionTest {

    @Test
    fun `test beGreaterThan`() {
        2L.should.beGreaterThan(1)
        2L.should.not.beGreaterThan(2)
        1L.should.not.beGreaterThan(2)
    }

    @Test
    fun `test beGreaterThanOrEqualTo`() {
        1L.should.beGreaterThanOrEqualTo(1)
        1L.should.beGreaterThanOrEqualTo(0)
    }

    @Test
    fun `test beLessThan`() {
        1L.should.beLessThan(2)
        1L.should.not.beLessThan(1)
        1L.should.not.beLessThan(0)
    }

    @Test
    fun `test beLessThanOrEqualTo`() {
        2L.should.beLessThanOrEqualTo(2)
        2L.should.beLessThanOrEqualTo(3)
        2L.should.not.beLessThanOrEqualTo(1)
    }

    @Test
    fun `test beBetween`() {
        2L.should.beBetween(1, 3)
        2L.should.beBetween(1, 2)
        2L.should.beBetween(2, 3)
        2L.should.not.beBetween(3, 4)
    }

    @Test
    fun `test beIn the range`() {
        3L.should.beIn(1..3L)
        3L.should.beIn(3..4L)
        3L.should.not.beIn(1 until 3L)
    }
}
