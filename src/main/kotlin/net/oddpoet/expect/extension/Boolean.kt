package net.oddpoet.expect.extension

import net.oddpoet.expect.Expect

/**
 * Extension : Boolean
 * @author Yunsang Choi
 */

fun Expect<Boolean>.beTrue() =
        satisfyThat("be true") {
            it
        }

fun Expect<Boolean>.beFalse() =
        satisfyThat("be false") {
            !it
        }
