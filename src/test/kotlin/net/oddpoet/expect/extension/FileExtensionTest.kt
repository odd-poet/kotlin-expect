package net.oddpoet.expect.extension

import net.oddpoet.expect.should
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.File
import java.nio.file.Files

/**
 *
 * @author Yunsang Choi
 */

class FileExtensionTest {
    private val log : Logger = LoggerFactory.getLogger(this.javaClass)
    lateinit var dir: File
    lateinit var file: File

    @BeforeEach
    fun setUp() {
        dir = Files.createTempDirectory("test").toFile()
        file = File(dir, "dummy.txt")
    }

    @Test
    fun `test beDirectory`() {
        dir.should.beDirectory()
        file.should.not.beDirectory()
    }

    @Test
    fun `test beFile`() {
        dir.should.not.beFile()

        file.createNewFile()
        file.should.beFile()
    }

    @Test
    fun `test exist`() {
        dir.should.exist()
        file.should.not.exist()
    }

    @Test
    fun `test beNameOf`() {
        file.should.haveNameOf("dummy.txt")
        file.should.haveExtensionOf("txt")
    }

    @Test
    fun `test beAbsolute`() {
        file.should.beAbsolute()
        File("./dummy").should.not.beAbsolute()
    }

    @Test
    fun `test hidden`() {
        file.should.not.beHidden()
        File("./dummy").should.not.beHidden()
        File("./.dummy").should.beHidden()
    }

    @Test
    fun `test beReadable`() {
        file.createNewFile()
        file.setReadOnly()
        file.should.beReadable()
        file.should.not.beWritable()
    }

    @Test
    fun `test beWritable`() {
        file.createNewFile()
        file.setWritable(true)
        file.should.beWritable()
        file.setWritable(false)
        file.should.not.beWritable()
    }

    @Test
    fun `test beExecutable`() {
        file.createNewFile()

        file.setExecutable(false)
        file.should.not.beExecutable()
        file.setExecutable(true)
        file.should.beExecutable()
    }

    @Test
    fun `test beRooted`() {
        file.should.beRooted()
        File("./hello.txt").should.not.beRooted()
    }

    @Test
    fun `test bePathOf`() {
        file.should.not.bePathOf("/dummy.txt")
        File("/path/to/file").should.bePathOf("/path/to/file")
        File("../path/to/file").should.bePathOf("../path/to/file")
    }

    @Test
    fun `test haveLengthOf`() {
        file.createNewFile()
        file.writeText("hello world")
        file.should.haveLengthOf(11)
    }

    @Test
    fun `test startWith`() {
        file.should.startWith(dir.absolutePath)
        File(dir, "world.txt").should.startWith(dir.path)
    }

    @Test
    fun `test startWith file`() {
        file.should.startWith(dir.absolutePath)
        File(dir, "world.txt").should.startWith(dir)
    }

    @Test
    fun `test endWith()`() {
        file.should.endWith("dummy.txt")
        File(dir, "demo.txt").should.endWith("demo.txt")
        File(dir, "demo2.txt").should.not.endWith("2.txt")
    }

    @Test
    fun `test endWith file()`() {
        file.should.endWith(File("dummy.txt"))
        File(dir, "demo.txt").should.endWith(File("demo.txt"))
        File(dir, "demo2.txt").should.not.endWith(File("mo2.txt"))
    }
}
