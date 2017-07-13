package net.oddpoet.expect.extension

import net.oddpoet.expect.Expect

fun Expect<Double>.beGreaterThan(other: Double) =
        satisfyThat("be greater than ${other.literal}") {
            it?.let { it > other } ?: false
        }

fun Expect<Double>.beGreaterThanOrEqualTo(other: Double) =
        satisfyThat("be greater than or equal to ${other.literal}") {
            it?.let { it >= other } ?: false
        }


fun Expect<Double>.beLessThan(other: Double) =
        satisfyThat("be less than ${other.literal}") {
            it?.let { it < other } ?: false
        }

fun Expect<Double>.beLessThanOrEqualTo(other: Double) =
        satisfyThat("be less than or equal to ${other.literal}") {
            it?.let { it <= other } ?: false
        }


fun Expect<Double>.beBetween(lower: Double, upper: Double) =
        satisfyThat("be between ${lower.literal} and ${upper.literal}") {
            it?.let { it >= lower && it <= upper } ?: false
        }

fun Expect<Double>.beBetweenExclusive(lower: Double, upper: Double) =
        satisfyThat("be between ${lower.literal} and ${upper.literal} exclusive") {
            it?.let { it > lower && it < upper } ?: false
        }

fun Expect<Double>.equalToWithin(value: Double, delta: Double) =
        satisfyThat("equal to ${value.literal} within ±${delta.literal}") {
            it?.let { Math.abs(it - value) <= delta } ?: false
        }


