package net.oddpoet.expect

import net.oddpoet.expect.policy.Stability
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

/**
 * DSL entry points.
 *
 * @author Yunsang Choi
 */

/**
 * expect exception while executing code block
 */
@Stability.Stable
fun expect(block: () -> Unit): ErrorExpectation {
    return ErrorExpectation(block)
}

/**
 * expect exception while executing code block (another version)
 */
@Stability.Stable
@OptIn(ExperimentalContracts::class)
inline fun <reified T : Throwable> expectThrows(noinline block: () -> Unit) {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    expect(block).throws<T>()
}

/**
 * expect subject to...
 */
@Stability.Stable
fun <T : Any> expect(subject: T?) = Expectation(subject)

/**
 * expect subject that ...
 */
@Stability.Stable
@OptIn(ExperimentalContracts::class)
fun <T : Any> expect(subject: T?, clause: (T) -> Unit) {
    contract {
        returns() implies (subject != null)
        callsInPlace(clause, InvocationKind.EXACTLY_ONCE)
    }
    if (subject == null) {
        throw RuntimeException("Cannot execute expect clause for null.")
    }
    clause(subject)
}

/**
 * rspec old-style : `should`
 *
 */
@Stability.Stable
val <T : Any> T?.should: Expect<T>
    get() = Expect(this)

