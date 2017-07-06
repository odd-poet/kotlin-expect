package net.oddpoet.expect.extension

import net.oddpoet.expect.Expect
import kotlin.reflect.KClass

/**
 * Extension : Any
 *
 * @author Yunsang Choi
 */

infix fun <T : Any> Expect<T>.beSameInstance(value: Any?) =
        satisfyThat("be same instance of <$value>") {
            it === value
        }

infix fun <T : Any> Expect<T>.be(value: Any?) =
        satisfyThat("be <$value>") {
            it == value
        }

infix fun <T : Any> Expect<T>.equal(value: Any?) =
        satisfyThat("equal <$value>") {
            it == value
        }

fun <T : Any> Expect<T>.beNull() =
        satisfyThat("be null") {
            it == null
        }

infix fun <T : Any> Expect<T>.beInstanceOf(type: KClass<*>) =
        satisfyThat("be instance of <$type>") {
            type.isInstance(it)
        }
