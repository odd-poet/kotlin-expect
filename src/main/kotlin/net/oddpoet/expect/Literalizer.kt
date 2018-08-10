package net.oddpoet.expect

import kotlin.reflect.KClass

/**
 * Object to literal.
 *
 * it will be used to print an object as part of assertion message.
 *
 * @author Yunsang Choi
 */
interface Literalizer<T> {
    fun literal(value: T): String

    companion object Registry {
        private val list = mutableListOf<TypedLiteralizer<*>>()

        init {
            // register built-in literalizers.
            // the order of those is important!
            register(Int::class) { "$it" }
            register(Long::class) { "${it}L" }
            register(Double::class) { "$it" }
            register(Float::class) { "${it}f" }
            register(Char::class) { "'${unescape(it.toString())}'" }
            register(String::class) { "\"${unescape(it)}\"" }
            register(Regex::class) { "/$it/" }
            register(Boolean::class) { "$it" }
            register(Throwable::class) { "${it::class.qualifiedName}(message=\"${it.message}\")" }
            register(Array<Any?>::class) {
                it.map { literal(it) }
                        .joinToString(separator = ",", prefix = "[", postfix = "]")
            }
            register(Collection::class) {
                it.map { literal(it) }
                        .joinToString(separator = ",", prefix = "${it::class.simpleName}(", postfix = ")")
            }
            register(Map::class) {
                it.map { "${literal(it.key)}:${literal(it.value)}" }
                        .joinToString(separator = ",", prefix = "${it::class.simpleName}{", postfix = "}")
            }
            register(ClosedRange::class) { "(${it.start}, ${it.endInclusive})" }
        }

        fun literal(value: Any?): String {
            return value?.let {
                list.firstOrNull { it.type.isInstance(value) }
                        ?.literal(value)
                        ?: it.toString() // no literalizer for given value.
            } ?: "null"
        }

        fun <T : Any> register(type: KClass<T>, literalizer: Literalizer<T>) {
            list.add(TypedLiteralizer(type, literalizer))
        }

        fun <T : Any> register(type: KClass<T>, block: (T) -> String) {
            list.add(TypedLiteralizer(type, object : Literalizer<T> {
                override fun literal(value: T): String = block(value)
            }))
        }

        private fun unescape(string: String) =
                string.replace("\\", "\\\\")
                        .replace("\n", "\\n")
                        .replace("\r", "\\r")
                        .replace("\t", "\\t")


        internal class TypedLiteralizer<T : Any>
        constructor(val type: KClass<T>,
                    private val literalizer: Literalizer<T>) : Literalizer<Any> {
            override fun literal(value: Any): String {
                if (!type.isInstance(value)) {
                    throw IllegalArgumentException("wrong type! : $value")
                }
                @Suppress("UNCHECKED_CAST")
                return literalizer.literal(value as T)
            }
        }
    }
}

