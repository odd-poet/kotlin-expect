package net.oddpoet.expect.extension

import net.oddpoet.expect.Expect

fun <T : Int> Expect<T>.beGreaterThan(other: Int) =
        satisfyThat("be greater than ${other.literal}") {
            it?.let { it > other } ?: false
        }

fun <T : Int> Expect<T>.beGreaterThanOrEqualTo(other: Int) =
        satisfyThat("be greater than or equal to ${other.literal}") {
            it?.let { it >= other } ?: false
        }


fun <T : Int> Expect<T>.beLessThan(other: Int) =
        satisfyThat("be less than ${other.literal}") {
            it?.let { it < other } ?: false
        }

fun <T : Int> Expect<T>.beLessThanOrEqualTo(other: Int) =
        satisfyThat("be less than or equal to ${other.literal}") {
            it?.let { it <= other } ?: false
        }


fun <T : Int> Expect<T>.beBetween(lower: Int, upper: Int) =
        satisfyThat("be between ${lower.literal} and ${upper.literal}") {
            it?.let { it >= lower && it <= upper } ?: false
        }

fun <T : Int> Expect<T>.beBetweenExclusive(lower: Int, upper: Int) =
        satisfyThat("be between ${lower.literal} and ${upper.literal} exclusive") {
            it?.let { it > lower && it < upper } ?: false
        }


