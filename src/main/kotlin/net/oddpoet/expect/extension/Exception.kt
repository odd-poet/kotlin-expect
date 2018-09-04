package net.oddpoet.expect.extension

import net.oddpoet.expect.Expect

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

