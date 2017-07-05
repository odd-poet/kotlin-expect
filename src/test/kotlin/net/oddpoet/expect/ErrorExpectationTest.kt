package net.oddpoet.expect

import net.oddpoet.expect.extension.haveMessage
import org.junit.Test
import java.io.IOException
import java.nio.file.NoSuchFileException

/**
 * @author Yunsang Choi
 */
class ErrorExpectationTest {
    @Test
    fun `코드 블럭에 발생하는 예외클래스를 체크할 수 있다`() {
        expect {
            throw IOException()
        }.throws(IOException::class)
    }

    @Test
    fun `실제 발생하는 예외의 조상클래스로도 체크할 수 있다`() {
        expect {
            throw NoSuchFileException("/dummy")
        }.throws(IOException::class)
    }

    @Test(expected = AssertionError::class)
    fun `실제 발생하는 예외의 자손클래스는 테스트에 실패한다`() {
        expect {
            throw IOException()
        }.throws(NoSuchFileException::class)
    }

    @Test
    fun `발생한 예외의 객체를 테스트할 수 있다`() {
        expect {
            throw IOException("fake exception")
        }.throws(IOException::class) {
            it.should.haveMessage("fake exception")
        }
    }

    @Test
    fun `예외 클래스가 주어지지 않으면 Exception 클래스로 가정한다`() {
        expect {
            throw Exception("hello")
        }.throws()
    }
}