package net.oddpoet.expect

import net.oddpoet.expect.policy.Stability

/**
 * DSL entry points.
 *
 * @author Yunsang Choi
 */

/**
 * expect exception while executing code block
 */
@Stability.Stable
fun expect(block: () -> Unit) = ErrorExpectation(block)

/**
 * expect subject to...
 */
@Stability.Stable
fun <T : Any> expect(subject: T?) = Expectation(subject)

/**
 * expect subject that ...
 */
@Stability.Stable
fun <T : Any> expect(subject: T?, clause: (T) -> Unit) {
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

