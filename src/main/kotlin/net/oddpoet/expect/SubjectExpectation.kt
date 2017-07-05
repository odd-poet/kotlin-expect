package net.oddpoet.expect

/**
 *
 * @author Yunsang Choi
 */
class SubjectExpectation<T : Any>
internal constructor(subject: T?) {
    val to: Expect<T> by lazy { Expect(subject) }
}


class PropertyExpectation<T : Any>
internal constructor(name: String, property: T?) {
    val that: Expect<T> by lazy { Expect(property) }
}