package net.oddpoet.expect

/**
 * Object to literal.
 *
 * @author Yunsang Choi
 */
object LiteralUtil {

    fun literal(value: Any?): String {
        return when (value) {
            null -> "null"
            is Int -> "$value"
            is Long -> "${value}L"
            is Double -> "$value"
            is Float -> "${value}f"
            is Char -> "'${unescape(value.toString())}'"
            is String -> "\"${unescape(value)}\""
            is Regex -> "/$value/"
            is Array<*> -> value.map { literal(it) }
                    .joinToString(separator = ",", prefix = "[", postfix = "]")
            is Collection<*> -> {
                value.map { literal(it) }
                        .joinToString(separator = ",", prefix = "${value::class.simpleName}(", postfix = ")")
            }
            is Map<*, *> -> value.map { "${literal(it.key)}:${literal(it.value)}" }
                    .joinToString(separator = ",", prefix = "${value::class.simpleName}{", postfix = "}")
            else -> value.toString()
        }
    }

    private fun unescape(string: String) =
            string.replace("\\", "\\\\")
                    .replace("\n", "\\n")
                    .replace("\r", "\\r")
                    .replace("\t", "\\t")
}

