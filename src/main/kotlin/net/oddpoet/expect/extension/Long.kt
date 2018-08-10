package net.oddpoet.expect.extension

import net.oddpoet.expect.Expect

fun Expect<Long>.beGreaterThan(other: Long) =
        satisfyThat("be greater than ${other.literal}") {
            it > other
        }

fun Expect<Long>.beGreaterThanOrEqualTo(other: Long) =
        satisfyThat("be greater than or equal to ${other.literal}") {
            it >= other
        }


fun Expect<Long>.beLessThan(other: Long) =
        satisfyThat("be less than ${other.literal}") {
            it < other
        }

fun Expect<Long>.beLessThanOrEqualTo(other: Long) =
        satisfyThat("be less than or equal to ${other.literal}") {
            it <= other
        }


fun Expect<Long>.beBetween(lower: Long, upper: Long) =
        satisfyThat("be between ${lower.literal} and ${upper.literal}") {
            it in lower..upper
        }

fun Expect<Long>.beIn(range: ClosedRange<Long>) =
        satisfyThat("be in the range of ${range.literal}") {
            it in range
        }


