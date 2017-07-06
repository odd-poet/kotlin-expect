package net.oddpoet.expect.extension

import net.oddpoet.expect.Expect

/**
 * Extension : String
 *
 * @author Yunsang Choi
 */


fun Expect<String>.startWith(prefix: String) =
        satisfyThat("start with") {
            it?.startsWith(prefix) ?: false
        }

fun Expect<String>.endWith(suffix: String) =
        satisfyThat("end with") {
            it?.endsWith(suffix) ?: false
        }

fun Expect<String>.beEmpty() =
        satisfyThat("be empty") {
            it?.isEmpty() ?: false
        }

fun Expect<String>.beEmptyOrNull() =
        satisfyThat("be empty") {
            it == null || it.isEmpty()
        }

fun Expect<String>.beBlank() =
        satisfyThat("be empty") {
            it?.isBlank() ?: false
        }

fun Expect<String>.beBlankOrNull() =
        satisfyThat("be empty") {
            it == null || it.isBlank()
        }



