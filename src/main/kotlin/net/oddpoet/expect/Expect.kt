package net.oddpoet.expect

/**
 * Expect.
 *
 * It's extension point to define vocabulary for own your class.
 *
 * @author Yunsang Choi
 */
class Expect<T : Any>(internal val subject: T?, internal val negative: Boolean = false) {
    val not: Expect<T> by lazy { Expect(subject, !negative) }

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

    fun satisfyThat(description: String, predicate: (T?) -> Boolean) {
        if (predicate(subject) == negative) {
            throw AssertionError(errorMessage(description))
        }
    }

    private fun errorMessage(description: String): String {
        val should = when (negative) {true -> "should not"; else -> "should"
        }
        return "It $should $description, but it's <$subject>."
    }
}