package net.oddpoet.expect

/**
 * Expectation.
 *
 * @author Yunsang Choi
 */
class Expectation<T : Any>
internal constructor(subject: T?) {
    val to: Expect<T> by lazy { Expect(subject) }
}

