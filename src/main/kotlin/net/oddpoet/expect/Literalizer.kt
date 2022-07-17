package net.oddpoet.expect

import java.time.temporal.Temporal
import java.util.*
import kotlin.reflect.KClass

/**
 * Object to literal.
 *
 * it will be used to print an object as part of assertion message.
 *
 * IMPORTANT:
 *  Literalizer is only used internally(by companion object).
 *  So user cannot register own custom literalizer.
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
            register<Int> { "$it" }
            register<Long> { "${it}L" }
            register<Double> { "$it" }
            register<Float> { "${it}f" }
            register<Char> { "'${unescape(it.toString())}'" }
            register<String> { "\"${unescape(it)}\"" }
            register<Regex> { "/$it/" }
            register<Boolean> { "$it" }
            register<Throwable> { "${it::class.qualifiedName}(message=${literal(it.message)})" }
            register<Array<Any?>> { array ->
                array.map { literal(it) }
                    .joinToStringAutoWrap(separator = ",", prefix = "[", postfix = "]")
            }
            register<ByteArray> { bytes ->
                bytes.joinToString(separator = " ", prefix = "[", postfix = "]") { byte ->
                    String.format("0x%02X", byte)
                }
            }
            register<Collection<Any?>> { collection ->
                collection.map { literal(it) }
                    .joinToStringAutoWrap(separator = ",", prefix = "${collection::class.simpleName}(", postfix = ")")
            }
            register<Map<Any?, Any?>> { map ->
                map.map { "${literal(it.key)}:${literal(it.value)}" }
                    .joinToStringAutoWrap(separator = ",", prefix = "${map::class.simpleName}{", postfix = "}")
            }
            register<ClosedRange<Comparable<Any?>>> { "(${literal(it.start)}, ${literal(it.endInclusive)})" }
            // time
            register<Temporal> { "${it.javaClass.simpleName}<$it>" }
            register<Date> { "Date<$it>" }
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

        inline fun <reified T : Any> register(crossinline block: (T) -> String) {
            register(T::class, object : Literalizer<T> {
                override fun literal(value: T): String = block(value)
            })
        }


        private fun List<String>.joinToStringAutoWrap(separator: String, prefix: String, postfix: String): String {
            return if (sumOf { it.length } > 80) {
                joinToString(separator + "\n\t", prefix + "\n\t", "\n" + postfix)
            } else {
                joinToString(separator, prefix, postfix)
            }
        }

        private fun unescape(string: String) =
            string.replace("\\", "\\\\")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t")

        internal class TypedLiteralizer<T : Any>(
            val type: KClass<T>,
            private val literalizer: Literalizer<T>,
        ) : Literalizer<Any> {
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

