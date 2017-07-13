package net.oddpoet.expect.extension

import net.oddpoet.expect.Expect

fun Expect<Float>.beGreaterThan(other: Float) =
        satisfyThat("be greater than ${other.literal}") {
            it?.let { it > other } ?: false
        }

fun Expect<Float>.beGreaterThanOrEqualTo(other: Float) =
        satisfyThat("be greater than or equal to ${other.literal}") {
            it?.let { it >= other } ?: false
        }


fun Expect<Float>.beLessThan(other: Float) =
        satisfyThat("be less than ${other.literal}") {
            it?.let { it < other } ?: false
        }

fun Expect<Float>.beLessThanOrEqualTo(other: Float) =
        satisfyThat("be less than or equal to ${other.literal}") {
            it?.let { it <= other } ?: false
        }


fun Expect<Float>.beBetween(lower: Float, upper: Float) =
        satisfyThat("be between ${lower.literal} and ${upper.literal}") {
            it?.let { it >= lower && it <= upper } ?: false
        }

fun Expect<Float>.beBetweenExclusive(lower: Float, upper: Float) =
        satisfyThat("be between ${lower.literal} and ${upper.literal} exclusive") {
            it?.let { it > lower && it < upper } ?: false
        }

fun Expect<Float>.equalToWithin(value: Float, delta: Double) =
        satisfyThat("equal to ${value.literal} within ±${delta.literal}") {
            it?.let { Math.abs(it - value) <= delta } ?: false
        }
