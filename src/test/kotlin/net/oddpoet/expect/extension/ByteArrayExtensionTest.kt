package net.oddpoet.expect.extension

import net.oddpoet.expect.should
import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.*

/**
 *
 * @author mitchell.geek
 */
internal class ByteArrayExtensionTest {
    private val log: Logger = LoggerFactory.getLogger(this.javaClass)

    @Test
    internal fun `내용으로 equal check를 할 수 있다`() {
        // Given
        val bytes1 = "hello world".toByteArray()
        val bytes2 = "i love kotlin".toByteArray()

        // When
        // Then
        bytes1.should.be("hello world".toByteArray())
        bytes1.should.not.be(bytes2)
    }

    @Test
    internal fun `bytes 내용을 base64 인코딩 값으로 체크할 수 있다`() {
        // Given
        val bytes = "hello world".toByteArray()

        // When
        val base64encoded = Base64.getEncoder().encodeToString(bytes)
        // Then
        bytes.should.beEncodedBase64(base64encoded)
    }

    @Test
    internal fun `bytes 내용을 hex 값으로 체크할 수 있다`() {
        // Given
        val bytes = byteArrayOf(0xC0.toByte(), 0xDE.toByte(), 0xBE.toByte())

        // When
        // Then
        bytes.should.beAsHex("c0debe")
        bytes.should.beAsHex("C0DEBE")

    }
}
