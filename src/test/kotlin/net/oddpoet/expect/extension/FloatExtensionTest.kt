package net.oddpoet.expect.extension

import net.oddpoet.expect.should
import org.junit.Test

class FloatExtensionTest {

    @Test
    fun `test beGreaterThan`() {
        2.3f.should.beGreaterThan(1.1f)
        2.1f.should.not.beGreaterThan(2.1f)
        1f.should.not.beGreaterThan(2f)
    }

    @Test
    fun `test beGreaterThanOrEqualTo`() {
        1f.should.beGreaterThanOrEqualTo(1f)
        1f.should.beGreaterThanOrEqualTo(0f)
    }

    @Test
    fun `test beLessThan`() {
        1f.should.beLessThan(2f)
        1f.should.not.beLessThan(1f)
        1f.should.not.beLessThan(0f)
    }

    @Test
    fun `test beLessThanOrEqualTo`() {
        2f.should.beLessThanOrEqualTo(2f)
        2f.should.beLessThanOrEqualTo(3f)
        2f.should.not.beLessThanOrEqualTo(1f)
    }

    @Test
    fun `test beBetween`() {
        2f.should.beBetween(1f, 3f)
        2f.should.beBetween(1f, 2f)
        2f.should.beBetween(2f, 3f)
        2f.should.not.beBetween(3f, 4f)
    }

    @Test
    fun `test beIn the range`() {
        3f.should.beIn(3f..4f)
        3f.should.beIn(2.9f..3f)
        3f.should.not.beIn(3.00001f..3.1f)
        3f.should.not.beIn(2f..2.999999f)
    }

    @Test
    fun `test equalToWithin`() {
        2.31231f.should.equalToWithin(2.3f, error = 0.1)
        3.14159f.should.not.equalToWithin(3.15f, error = 0.001)
    }
}