package net.oddpoet.expect.extension

import net.oddpoet.expect.Expect
import net.oddpoet.expect.PropertyExpectation

/**
 *
 * @author Yunsang Choi
 */

fun <T : Throwable> Expect<T>.haveMessage(expectMessage: String) =
        satisfyThat("has message <'$expectMessage'>") {
            it?.let { it.message == expectMessage } ?: false
        }
