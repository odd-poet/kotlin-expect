package net.oddpoet.expect.extension

import net.oddpoet.expect.Expect
import java.time.Duration
import kotlin.math.abs

/**
 * Extension: Double
 *
 * @author Yunsang Choi
 */


fun Expect<Duration>.beGreaterThan(other: Duration) =
    satisfyThat("be greater than ${other.literal}") {
        it > other
    }

fun Expect<Duration>.beGreaterThanOrEqualTo(other: Duration) =
    satisfyThat("be greater than or equal to ${other.literal}") {
        it >= other
    }

fun Expect<Duration>.beLessThan(other: Duration) =
    satisfyThat("be less than ${other.literal}") {
        it < other
    }

fun Expect<Duration>.beLessThanOrEqualTo(other: Duration) =
    satisfyThat("be less than or equal to ${other.literal}") {
        it <= other
    }

fun Expect<Duration>.beBetween(lower: Duration, upper: Duration) =
    satisfyThat("be between ${lower.literal} and ${upper.literal}") {
        it in lower..upper
    }

fun Expect<Duration>.beIn(range: ClosedRange<Duration>) =
    satisfyThat("be in the range of ${range.literal}") {
        it in range
    }


fun Expect<Duration>.equalToWithin(value: Duration, error: Duration) =
    satisfyThat("equal to ${value.literal} within ±${error.literal}") {
        abs(it.toNanos() - value.toNanos()) <= error.toNanos()
    }


