package net.oddpoet.expect.extension

import net.oddpoet.expect.Expect
import java.util.*

/**
 * Extension: ByteArray.
 *
 * @author Yunsang Choi
 */


fun Expect<ByteArray>.contain(item: Byte) =
    satisfyThat("contain <${item.literal}>") {
        it.contains(item)
    }

fun Expect<ByteArray>.be(other: ByteArray) =
    satisfyThat("be <${other.literal}>") {
        it.contentEquals(other)
    }

// alias for `be`
fun Expect<ByteArray>.beSameContentOf(other: ByteArray) = this.be(other)

fun Expect<ByteArray>.haveSizeOf(size: Int) =
    satisfyThat("have size of <${size.literal}>") {
        it.size == size
    }

fun Expect<ByteArray>.beEmpty() =
    satisfyThat("be empty") {
        it.isEmpty()
    }

fun Expect<ByteArray>.beEncodedBase64(base64: String) =
    satisfyThat("be as base64") {
        Base64.getEncoder().encodeToString(it) == base64
    }

fun Expect<ByteArray>.beAsHex(hexBin: String) =
    satisfyThat("be as HEX '${hexBin.literal}'") {
        val hex = it.joinToString("") { String.format("%02X", it) }
        hex.equals(hexBin, ignoreCase = true)
    }


