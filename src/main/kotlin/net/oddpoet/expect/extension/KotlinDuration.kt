package net.oddpoet.expect.extension

import net.oddpoet.expect.Expect
import kotlin.math.abs
import kotlin.time.Duration


/**
 * Extension: Double
 *
 * @author Yunsang Choi
 */


@OptIn(kotlin.time.ExperimentalTime::class)
fun Expect<Duration>.beGreaterThan(other: Duration) =
    satisfyThat("be greater than ${other.literal}") {
        it > other
    }

@OptIn(kotlin.time.ExperimentalTime::class)
fun Expect<Duration>.beGreaterThanOrEqualTo(other: Duration) =
    satisfyThat("be greater than or equal to ${other.literal}") {
        it >= other
    }

@OptIn(kotlin.time.ExperimentalTime::class)
fun Expect<Duration>.beLessThan(other: Duration) =
    satisfyThat("be less than ${other.literal}") {
        it < other
    }

@OptIn(kotlin.time.ExperimentalTime::class)
fun Expect<Duration>.beLessThanOrEqualTo(other: Duration) =
    satisfyThat("be less than or equal to ${other.literal}") {
        it <= other
    }

@OptIn(kotlin.time.ExperimentalTime::class)
fun Expect<Duration>.beBetween(lower: Duration, upper: Duration) =
    satisfyThat("be between ${lower.literal} and ${upper.literal}") {
        it in lower..upper
    }

@OptIn(kotlin.time.ExperimentalTime::class)
fun Expect<Duration>.beIn(range: ClosedRange<Duration>) =
    satisfyThat("be in the range of ${range.literal}") {
        it in range
    }

@OptIn(kotlin.time.ExperimentalTime::class)
fun Expect<Duration>.equalToWithin(value: Duration, error: Duration) =
    satisfyThat("equal to ${value.literal} within ±${error.literal}") {
        abs(it.inWholeNanoseconds - value.inWholeNanoseconds) <= error.inWholeNanoseconds
    }