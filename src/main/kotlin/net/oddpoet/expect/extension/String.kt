package net.oddpoet.expect.extension

import net.oddpoet.expect.Expect

/**
 * Extension : String
 *
 * @author Yunsang Choi
 */


fun Expect<String>.startWith(prefix: CharSequence, ignoreCase: Boolean = false) =
        satisfyThat("start with ${prefix.literal}") {
            it?.startsWith(prefix, ignoreCase) ?: false
        }

fun Expect<String>.startWith(prefix: Char, ignoreCase: Boolean = false) =
        satisfyThat("start with ${prefix.literal}") {
            it?.startsWith(prefix, ignoreCase) ?: false
        }

fun Expect<String>.endWith(suffix: CharSequence, ignoreCase: Boolean = false) =
        satisfyThat("end with ${suffix.literal}") {
            it?.endsWith(suffix, ignoreCase) ?: false
        }

fun Expect<String>.endWith(suffix: Char, ignoreCase: Boolean = false) =
        satisfyThat("end with ${suffix.literal}") {
            it?.endsWith(suffix, ignoreCase) ?: false
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

fun Expect<String>.match(regex: Regex) =
        satisfyThat("match ${regex.literal}") {
            it?.matches(regex) ?: false
        }

fun Expect<String>.match(regex: String) = match(Regex(regex))
