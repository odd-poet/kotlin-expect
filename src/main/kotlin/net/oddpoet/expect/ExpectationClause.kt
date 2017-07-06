package net.oddpoet.expect

/**
 * it for using in lambda.
 *
 * ```
 * expect(subj) {
 *      it.should.not.beNull()
 *      it.should.be(other)
 * }
 * ```
 *
 */
class ExpectationClause<T : Any>(subject: T?) {
    val should: Expect<T> by lazy { Expect(subject) }
}