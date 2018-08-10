package net.oddpoet.expect.extension

import net.oddpoet.expect.should
import org.junit.Test

class DoubleExtensionTest {

    @Test
    fun `test beGreaterThan`() {
        2.3.should.beGreaterThan(1.1)
        2.1.should.not.beGreaterThan(2.1)
        1.0.should.not.beGreaterThan(2.0)
    }

    @Test
    fun `test beGreaterThanOrEqualTo`() {
        1.1.should.beGreaterThanOrEqualTo(1.1)
        1.1.should.beGreaterThanOrEqualTo(0.0)
    }

    @Test
    fun `test beLessThan`() {
        1.999999.should.beLessThan(2.0)
        1.00001.should.not.beLessThan(1.00001)
        1.1.should.not.beLessThan(0.0)
    }

    @Test
    fun `test beLessThanOrEqualTo`() {
        2.1.should.beLessThanOrEqualTo(2.1)
        2.2.should.beLessThanOrEqualTo(3.0)
        2.2.should.not.beLessThanOrEqualTo(1.0)
    }

    @Test
    fun `test beBetween`() {
        2.1111.should.beBetween(1.0, 2.2)
        2.1.should.beBetween(1.9, 2.2)
        2.0.should.beBetween(2.0, 2.1)
        2.0.should.not.beBetween(2.1, 2.3)
    }

    @Test
    fun `test beIn the range`() {
        2.2.should.beIn(2.1..2.3)
        2.2.should.not.beIn(2.20000001..2.3)
        2.2.should.not.beIn(2.1..2.19999999999)
    }


    @Test
    fun `test equalToWithin`() {
        2.31231.should.equalToWithin(2.3, error = 0.1)
        3.14159.should.not.equalToWithin(3.15, error = 0.001)
    }

}