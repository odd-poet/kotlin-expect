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
            log.debug("${subject.literal} $verb $description : FAIL")
            throw AssertionError(errorMessage(description))
        } else {
            log.debug("${subject.literal} $verb $description : OK")
        }

    }

    private fun errorMessage(description: String): String {
        return "It $verb $description, but it was <${subject.literal}>."
    }

    val <X : Any?> X.literal: String
        get() = when (this) {
            null -> "null"
            is Char -> "'${invisibleToString(this.toString())}'"
            is String -> "\"${invisibleToString(this)}\""
            is Regex -> "/$this/"
            is Array<*> -> this.map { it.literal }
                    .joinToString(separator = ",", prefix = "[", postfix = "]")
            is Collection<*> -> {
                this.map { it.literal }
                        .joinToString(separator = ",", prefix = "${className(this@literal)}(", postfix = ")")
            }
            is Map<*, *> -> this.map { "${it.key.literal}:${it.value.literal}" }
                    .joinToString(separator = ",", prefix = "${className(this@literal)}{", postfix = "}")
            else -> toString()
        }

    private fun className(value: Any) = value::class.simpleName

    private fun invisibleToString(string: String) =
            string.replace("\\", "\\\\")
                    .replace("\n", "\\n")
                    .replace("\r", "\\r")
                    .replace("\t", "\\t")

}