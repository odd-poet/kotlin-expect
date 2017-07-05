package net.oddpoet.expect.extension

import net.oddpoet.expect.Expect

/**
 * @author Yunsang Choi
 */

fun <T : Any> Expect<T>.be(value: Any?) =
        satisfyThat("be <$value>") {
            it == value
        }

fun <T : Any> Expect<T>.beNull(value: Any?) = be(null)
