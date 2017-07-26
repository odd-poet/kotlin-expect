package net.oddpoet.expect.extension

import net.oddpoet.expect.Expect
import kotlin.reflect.KClass

/**
 * Extension : Any
 *
 * @author Yunsang Choi
 */

infix fun <T : Any> Expect<T>.beSameInstance(value: Any?) =
        satisfyThatForNullable("be same instance of <${value.literal}>") {
            it === value
        }

infix fun <T : Any> Expect<T>.be(value: Any?) =
        satisfyThatForNullable("be <${value.literal}>") {
            it == value
        }

infix fun <T : Any> Expect<T>.equal(value: Any?) =
        satisfyThatForNullable("equal <${value.literal}>") {
            it == value
        }

fun <T : Any> Expect<T>.beNull() =
        satisfyThatForNullable("be null") {
            it == null
        }

infix fun <T : Any> Expect<T>.beInstanceOf(type: KClass<*>) =
        satisfyThatForNullable("be instance of <${type.literal}>") {
            type.isInstance(it)
        }
