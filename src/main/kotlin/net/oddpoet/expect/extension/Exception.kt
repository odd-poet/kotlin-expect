package net.oddpoet.expect.extension

import net.oddpoet.expect.Expect

/**
 *
 * @author Yunsang Choi
 */

fun <T : Throwable> Expect<T>.haveMessage(expectMessage: String?) =
        satisfyThat("has message of ${expectMessage.literal}") {
            it?.let { it.message == expectMessage } ?: false
        }

fun <T : Throwable> Expect<T>.haveNoMessage() =
        satisfyThat("has no message") {
            it?.let { it.message == null } ?: false
        }

