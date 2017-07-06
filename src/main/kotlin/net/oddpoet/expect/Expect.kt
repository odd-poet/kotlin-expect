package net.oddpoet.expect

import net.oddpoet.expect.policy.Stability

/**
 * Expect.
 *
 * It's extension point to define vocabulary for own your class.
 *
 * @author Yunsang Choi
 */
class Expect<T : Any>
internal constructor(private val subject: T?,
            private val negative: Boolean = false,
            private val describe: String = "It should") {
    val not: Expect<T> by lazy { Expect(subject, !negative, describe + " not") }

    /**
     * test given predicate.
     *
     * if subject is null, it will throw assertError.
     */
    fun satisfy(predicate: T.() -> Boolean) {
        satisfyThat("satisfy given predicate <$predicate>") {
            it?.predicate() ?: false
        }
    }

    /**
     * assert with assertion message and predicate.
     *
     * use it to define your expect vocabulary.
     */
    fun satisfyThat(description: String, predicate: (T?) -> Boolean) {
        if (predicate(subject) == negative) {
            throw AssertionError(errorMessage(description))
        }
    }


    @Stability.Unstable
    fun <P : Any> propertyExpectation(name: String, extractor: (T) -> P?): Expectation<P> {
        if (subject == null) {
            throw RuntimeException("You try to access '$name' property for null object")
        }
        return Expectation(extractor(subject))
    }

    private fun errorMessage(description: String): String {
        return "$describe $description, but the actual was <$subject>."
    }
}