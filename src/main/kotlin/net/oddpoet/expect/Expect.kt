package net.oddpoet.expect

import org.slf4j.LoggerFactory

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
                     private val verb: String = "should") {
    val not: Expect<T> by lazy { Expect(subject, !negative, verb + " not") }
    private val log = LoggerFactory.getLogger(this.javaClass)

    /**
     * test given predicate.
     *
     * if subject is null, it will throw assertError.
     */
    fun satisfy(predicate: T.() -> Boolean) {
        satisfyThatForNullable("satisfy given predicate <$predicate>") {
            it?.predicate() ?: false
        }
    }

    /**
     * assert with assertion message and predicate.
     *
     * use it to define your expect vocabulary.
     */
    fun satisfyThat(description: String, predicate: (T) -> Boolean) {
        satisfyThatForNullable(description) {
            it?.let { predicate(it) } ?: false
        }
    }

    /**
     * same as `satisfyThat`.
     * but it could be test nullable.
     */
    fun satisfyThatForNullable(description: String, predicate: (T?) -> Boolean) {
        if (predicate(subject) == negative) {
            log.debug("${subject.literal} $verb $description : FAIL")
            throw AssertionError(errorMessage(description))
        } else {
            log.debug("${subject.literal} $verb $description : OK")
        }
    }

    private fun errorMessage(description: String): String {
        return "It $verb $description, but it was <${subject.literal}>."
    }


    // Expect class scoped extension (for print object in assertion message)
    internal val <X : Any?> X.literal: String
        get() = Literalizer.literal(this)

    @Deprecated("DO NOT USE")
    override fun equals(other: Any?): Boolean {
        throw RuntimeException("DO NOT USE THIS METHOD")
    }

    @Deprecated("DO NOT USE")
    override fun hashCode(): Int {
        throw RuntimeException("DO NOT USE THIS METHOD")
    }

    @Deprecated("DO NOT USE")
    override fun toString(): String {
        throw RuntimeException("DO NOT USE THIS METHOD")
    }
}