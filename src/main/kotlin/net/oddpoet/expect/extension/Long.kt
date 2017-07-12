package net.oddpoet.expect.extension

import net.oddpoet.expect.Expect

fun <T : Long> Expect<T>.beGreaterThan(other: Long) =
        satisfyThat("be greater than ${other.literal}") {
            it?.let { it > other } ?: false
        }

fun <T : Long> Expect<T>.beGreaterThanOrEqualTo(other: Long) =
        satisfyThat("be greater than or equal to ${other.literal}") {
            it?.let { it >= other } ?: false
        }


fun <T : Long> Expect<T>.beLessThan(other: Long) =
        satisfyThat("be less than ${other.literal}") {
            it?.let { it < other } ?: false
        }

fun <T : Long> Expect<T>.beLessThanOrEqualTo(other: Long) =
        satisfyThat("be less than or equal to ${other.literal}") {
            it?.let { it <= other } ?: false
        }


fun <T : Long> Expect<T>.beBetween(lower: Long, upper: Long) =
        satisfyThat("be between ${lower.literal} and ${upper.literal}") {
            it?.let { it >= lower && it <= upper } ?: false
        }

fun <T : Long> Expect<T>.beBetweenExclusive(lower: Long, upper: Long) =
        satisfyThat("be between ${lower.literal} and ${upper.literal} exclusive") {
            it?.let { it > lower && it < upper } ?: false
        }


