package net.oddpoet.expect


/**
 *
 * @author Yunsang Choi
 */
fun expect(block: () -> Unit) = ErrorExpectation(block)

fun <T : Any> expect(subject: T?) = SubjectExpectation(subject)

fun <T : Any> expect(subject: T?, clause: (ExpectationClause<T>) -> Unit) {
    clause(ExpectationClause(subject))
}
val <T : Any> T?.should: Expect<T>
    get() = Expect(this)
