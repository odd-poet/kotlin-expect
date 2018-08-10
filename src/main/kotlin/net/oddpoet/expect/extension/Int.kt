package net.oddpoet.expect.extension

import net.oddpoet.expect.Expect

fun Expect<Int>.beGreaterThan(other: Int) =
        satisfyThat("be greater than ${other.literal}") {
            it > other
        }

fun Expect<Int>.beGreaterThanOrEqualTo(other: Int) =
        satisfyThat("be greater than or equal to ${other.literal}") {
            it >= other
        }


fun Expect<Int>.beLessThan(other: Int) =
        satisfyThat("be less than ${other.literal}") {
            it < other
        }

fun Expect<Int>.beLessThanOrEqualTo(other: Int) =
        satisfyThat("be less than or equal to ${other.literal}") {
            it <= other
        }


fun Expect<Int>.beBetween(lower: Int, upper: Int) =
        satisfyThat("be between ${lower.literal} and ${upper.literal}") {
            it in lower..upper
        }

fun Expect<Int>.beIn(range: ClosedRange<Int>) =
        satisfyThat("be in the range of ${range.literal}") {
            it in range
        }


