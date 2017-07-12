package net.oddpoet.expect.extension

import net.oddpoet.expect.Expect

fun <T : Float> Expect<T>.beGreaterThan(other: Float) =
        satisfyThat("be greater than ${other.literal}") {
            it?.let { it > other } ?: false
        }

fun <T : Float> Expect<T>.beGreaterThanOrEqualTo(other: Float) =
        satisfyThat("be greater than or equal to ${other.literal}") {
            it?.let { it >= other } ?: false
        }


fun <T : Float> Expect<T>.beLessThan(other: Float) =
        satisfyThat("be less than ${other.literal}") {
            it?.let { it < other } ?: false
        }

fun <T : Float> Expect<T>.beLessThanOrEqualTo(other: Float) =
        satisfyThat("be less than or equal to ${other.literal}") {
            it?.let { it <= other } ?: false
        }


fun <T : Float> Expect<T>.beBetween(lower: Float, upper: Float) =
        satisfyThat("be between ${lower.literal} and ${upper.literal}") {
            it?.let { it >= lower && it <= upper } ?: false
        }

fun <T : Float> Expect<T>.beBetweenExclusive(lower: Float, upper: Float) =
        satisfyThat("be between ${lower.literal} and ${upper.literal} exclusive") {
            it?.let { it > lower && it < upper } ?: false
        }

fun <T : Float> Expect<T>.equalToWithin(value: Float, delta: Double) =
        satisfyThat("equal to ${value.literal} within ±${delta.literal}") {
            it?.let { Math.abs(it - value) <= delta } ?: false
        }
