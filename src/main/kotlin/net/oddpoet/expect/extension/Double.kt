package net.oddpoet.expect.extension

import net.oddpoet.expect.Expect

/**
 * Extension: Double
 *
 * @author Yunsang Choi
 */
fun Expect<Double>.beGreaterThan(other: Double) =
        satisfyThat("be greater than ${other.literal}") {
            it > other
        }

fun Expect<Double>.beGreaterThanOrEqualTo(other: Double) =
        satisfyThat("be greater than or equal to ${other.literal}") {
            it >= other
        }


fun Expect<Double>.beLessThan(other: Double) =
        satisfyThat("be less than ${other.literal}") {
            it < other
        }

fun Expect<Double>.beLessThanOrEqualTo(other: Double) =
        satisfyThat("be less than or equal to ${other.literal}") {
            it <= other
        }


fun Expect<Double>.beBetween(lower: Double, upper: Double) =
        satisfyThat("be between ${lower.literal} and ${upper.literal}") {
            it in lower..upper
        }

fun Expect<Double>.beIn(range: ClosedRange<Double>) =
        satisfyThat("be in the range of ${range.literal}") {
            it in range
        }


fun Expect<Double>.equalToWithin(value: Double, error: Double) =
        satisfyThat("equal to ${value.literal} within ±${error.literal}") {
            Math.abs(it - value) <= error
        }


