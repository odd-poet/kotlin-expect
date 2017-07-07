package net.oddpoet.expect

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
            if (printAssertion)
                System.err.println(assertionMessage(description) + " : <${subject.literal}>")
            throw AssertionError(errorMessage(description))
        } else {
            if (printAssertion)
                System.out.println(assertionMessage(description) + " : <${subject.literal}>")
        }

    }

    private fun errorMessage(description: String): String {
        return "${assertionMessage(description)}, but it was <${subject.literal}>."
    }

    private fun assertionMessage(description: String): String {
        return "$describe $description"
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

    companion object {
        internal var printAssertion = false
    }
}