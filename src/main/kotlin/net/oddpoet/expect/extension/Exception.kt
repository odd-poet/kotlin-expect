package net.oddpoet.expect.extension

import net.oddpoet.expect.Expect

/**
 *
 * @author Yunsang Choi
 */

fun <T : Throwable> Expect<T>.haveMessage(expectMessage: String?) =
        satisfyThat("has message <$expectMessage>") {
            it?.let { it.message == expectMessage } ?: false
        }

fun <T : Throwable> Expect<T>.haveNoMessage() =
        satisfyThat("has no describe") {
            it?.let { it.message == null } ?: false
        }

