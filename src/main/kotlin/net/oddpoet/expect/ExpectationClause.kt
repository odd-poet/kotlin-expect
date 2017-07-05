package net.oddpoet.expect

class ExpectationClause<T : Any>(subject: T?) {
    val should: Expect<T> by lazy { Expect(subject) }
}