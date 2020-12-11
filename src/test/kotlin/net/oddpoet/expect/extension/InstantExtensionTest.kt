package net.oddpoet.expect.extension

import net.oddpoet.expect.should
import org.junit.jupiter.api.Test
import java.time.Instant
import java.util.*

/**
 *
 * @author Yunsang Choi
 */

class InstantExtensionTest {

    @Test
    fun `test beBefore other instant`() {
        // Given
        val time1 = Instant.ofEpochSecond(1000)
        val time2 = Instant.ofEpochSecond(2000)

        // When
        // Then
        time1.should.beBefore(time2)
        time2.should.not.beBefore(time1)
        time1.should.not.beBefore(time1)
    }

    @Test
    fun `test beBefore date`() {
        // Given
        val instant = Instant.ofEpochMilli(3000)
        val date1 = Date(2000)
        val date2 = Date(4000)

        // When
        // Then
        instant.should.not.beBefore(date1)
        instant.should.beBefore(date2)
    }

    @Test
    fun `test beAfter other instant`() {
        // Given
        val time1 = Instant.ofEpochSecond(1000)
        val time2 = Instant.ofEpochSecond(2000)

        // When
        // Then
        time1.should.not.beAfter(time2)
        time2.should.beAfter(time1)
        time1.should.not.beAfter(time1)
    }

    @Test
    fun `test beAfter other date`() {
        // Given
        val instant = Instant.ofEpochMilli(3000)
        val date1 = Date(2000)
        val date2 = Date(4000)

        // When
        // Then
        instant.should.beAfter(date1)
        instant.should.not.beAfter(date2)
    }

    @Test
    fun `test beIn the range`() {
        // Given
        val instant = Instant.ofEpochMilli(3000)
        // When
        // Then
        instant.should.beIn(Instant.ofEpochMilli(2000)..Instant.ofEpochMilli(3000))
        instant.should.not.beIn(Instant.ofEpochMilli(2000)..Instant.ofEpochMilli(2999))
    }
}
