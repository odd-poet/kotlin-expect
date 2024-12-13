package net.oddpoet.expect.extension

import net.oddpoet.expect.Expect
import java.time.Instant
import java.time.temporal.TemporalAmount
import java.util.*

/**
 * Extension : Instant
 *
 * @author Yunsang Choi
 */

fun Expect<Instant>.beBefore(other: Instant) =
    satisfyThat("be before ${other.literal}") {
        it.isBefore(other)
    }

fun Expect<Instant>.beBefore(other: Date) =
    satisfyThat("be before ${other.literal}") {
        it.isBefore(other.toInstant())
    }

fun Expect<Instant>.beAfter(other: Instant) =
    satisfyThat("be after ${other.literal}") {
        it.isAfter(other)
    }

fun Expect<Instant>.beAfter(other: Date) =
    satisfyThat("be after ${other.literal}") {
        it.isAfter(other.toInstant())
    }

fun Expect<Instant>.beIn(range: ClosedRange<Instant>) =
    satisfyThat("be in the range of ${range.literal}") {
        it in range
    }

fun Expect<Instant>.beApproximatedTo(other: Instant, marginOfError: TemporalAmount) =
    satisfyThat("be approximated to ${other.literal} within ${marginOfError.literal}") {
        it in (other.minus(marginOfError)..other.plus(marginOfError))
    }

