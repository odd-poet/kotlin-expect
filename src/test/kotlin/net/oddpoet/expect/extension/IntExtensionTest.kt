package net.oddpoet.expect.extension

import net.oddpoet.expect.should
import org.junit.jupiter.api.Test

class IntExtensionTest {

    @Test
    fun `test beGreaterThan`() {
        2.should.beGreaterThan(1)
        2.should.not.beGreaterThan(2)
        1.should.not.beGreaterThan(2)
    }

    @Test
    fun `test beGreaterThanOrEqualTo`() {
        1.should.beGreaterThanOrEqualTo(1)
        1.should.beGreaterThanOrEqualTo(0)
    }

    @Test
    fun `test beLessThan`() {
        1.should.beLessThan(2)
        1.should.not.beLessThan(1)
        1.should.not.beLessThan(0)
    }

    @Test
    fun `test beLessThanOrEqualTo`() {
        2.should.beLessThanOrEqualTo(2)
        2.should.beLessThanOrEqualTo(3)
        2.should.not.beLessThanOrEqualTo(1)
    }

    @Test
    fun `test beBetween`() {
        2.should.beBetween(1, 3)
        2.should.beBetween(1, 2)
        2.should.beBetween(2, 3)
        2.should.not.beBetween(3, 4)
    }

    @Test
    fun `test beIn the range`() {
        3.should.beIn(1..3)
        3.should.beIn(3..5)
        3.should.not.beIn(1 until 3)
    }
}
