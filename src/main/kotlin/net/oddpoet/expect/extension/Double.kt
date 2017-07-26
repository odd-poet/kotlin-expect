package net.oddpoet.expect.extension

import net.oddpoet.expect.Expect

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

fun Expect<Double>.beBetweenExclusive(lower: Double, upper: Double) =
        satisfyThat("be between ${lower.literal} and ${upper.literal} exclusive") {
            it > lower && it < upper
        }

fun Expect<Double>.equalToWithin(value: Double, delta: Double) =
        satisfyThat("equal to ${value.literal} within ±${delta.literal}") {
            Math.abs(it - value) <= delta
        }


