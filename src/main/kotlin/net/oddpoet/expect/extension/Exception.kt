package net.oddpoet.expect.extension

import net.oddpoet.expect.Expect
import kotlin.reflect.KClass

/**
 *  Extension: Throwable
 *
 * @author Yunsang Choi
 */

fun <T : Throwable> Expect<T>.haveMessage(expectMessage: String?) =
    satisfyThat("has message of ${expectMessage.literal}") {
        it.message.asTestProp("message") == expectMessage
    }

fun <T : Throwable> Expect<T>.haveNoMessage() =
    satisfyThat("has no message") {
        it.message.asTestProp("message") == null
    }

fun <T : Throwable, C : Throwable> Expect<T>.haveCause(cause: KClass<C>) =
    satisfyThat("has cause of ${cause.literal}") {
        it.cause.asTestProp("cause")
        sequence {
            yield(it)
            var current = it.cause
            while (current != null) {
                yield(current)
                current = current.cause
            }
        }.any { cause.java.isAssignableFrom(it::class.java) }
    }
