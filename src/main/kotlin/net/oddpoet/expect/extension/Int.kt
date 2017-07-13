package net.oddpoet.expect.extension

import net.oddpoet.expect.Expect

fun Expect<Int>.beGreaterThan(other: Int) =
        satisfyThat("be greater than ${other.literal}") {
            it?.let { it > other } ?: false
        }

fun Expect<Int>.beGreaterThanOrEqualTo(other: Int) =
        satisfyThat("be greater than or equal to ${other.literal}") {
            it?.let { it >= other } ?: false
        }


fun Expect<Int>.beLessThan(other: Int) =
        satisfyThat("be less than ${other.literal}") {
            it?.let { it < other } ?: false
        }

fun Expect<Int>.beLessThanOrEqualTo(other: Int) =
        satisfyThat("be less than or equal to ${other.literal}") {
            it?.let { it <= other } ?: false
        }


fun Expect<Int>.beBetween(lower: Int, upper: Int) =
        satisfyThat("be between ${lower.literal} and ${upper.literal}") {
            it?.let { it >= lower && it <= upper } ?: false
        }

fun Expect<Int>.beBetweenExclusive(lower: Int, upper: Int) =
        satisfyThat("be between ${lower.literal} and ${upper.literal} exclusive") {
            it?.let { it > lower && it < upper } ?: false
        }


