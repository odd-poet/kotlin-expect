package net.oddpoet.expect.extension

import net.oddpoet.expect.Expect
import java.io.Reader

/**
 * Extension: Reader
 *
 * @author Yunsang Choi
 */
fun <T : Reader> Expect<T>.equalAsText(text: String, ignoreCase: Boolean = false) =
        satisfyThat("equal to ${text.literal} as text") {
            it.readText().asTestProp("content").equals(text, ignoreCase)
        }

fun <T : Reader> Expect<T>.contain(text: String, ignoreCase: Boolean = false) =
        satisfyThat("contain ${text.literal}") {
            it.readText().asTestProp("content").contains(text, ignoreCase)
        }