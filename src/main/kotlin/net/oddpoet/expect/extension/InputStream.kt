package net.oddpoet.expect.extension

import net.oddpoet.expect.Expect
import java.io.InputStream
import java.nio.charset.Charset

/**
 * Extension : InputStream
 *
 * @author mitchell.geek
 */

fun <T : InputStream> Expect<T>.equalAsText(text: String, charset: Charset = Charsets.UTF_8) =
        satisfyThat("equal to $text as text") {
            it.reader(charset).readText() == text
        }
