package net.oddpoet.expect.extension

import net.oddpoet.expect.Expect
import java.io.File

/**
 * Extension: File
 * @author Yunsang Choi
 */

fun Expect<File>.beDirectory() =
        satisfyThat("be directory") {
            it.isDirectory
        }

fun Expect<File>.beFile() =
        satisfyThat("be file") {
            it.isFile
        }

fun Expect<File>.exist() =
        satisfyThat("exist") {
            it.exists()
        }

fun Expect<File>.beAbsolute() =
        satisfyThat("be absolute") {
            it.isAbsolute
        }

fun Expect<File>.beHidden() =
        satisfyThat("be hidden") {
            it.isHidden
        }

fun Expect<File>.beExecutable() =
        satisfyThat("be executable") {
            it.canExecute()
        }


fun Expect<File>.beReadable() =
        satisfyThat("be readable") {
            it.canRead()
        }

fun Expect<File>.beWritable() =
        satisfyThat("be writable") {
            it.canWrite()
        }

fun Expect<File>.beRooted() =
        satisfyThat("be rooted") {
            it.isRooted
        }

fun Expect<File>.haveNameOf(name: String) =
        satisfyThat("have name of <${name.literal}>") {
            it.name == name
        }

fun Expect<File>.haveExtensionOf(extension: String) =
        satisfyThat("have extension of <${extension.literal}>") {
            it.extension.asTestProp("extension") == extension
        }

fun Expect<File>.bePathOf(path: String) =
        satisfyThat("be path of <${path.literal}>") {
            it.path.asTestProp("path") == path
        }

fun Expect<File>.haveLengthOf(length: Long) =
        satisfyThat("have length of <${length.literal}>") {
            it.length().asTestProp("length") == length
        }

fun Expect<File>.startWith(other: File) =
        satisfyThat("start with <${other.literal}>") {
            it.startsWith(other)
        }

fun Expect<File>.startWith(other: String) =
        satisfyThat("start with <${other.literal}>") {
            it.startsWith(other)
        }

fun Expect<File>.endWith(other: File) =
        satisfyThat("end with <${other.literal}>") {
            it.endsWith(other)
        }

fun Expect<File>.endWith(other: String) =
        satisfyThat("end with <${other.literal}>") {
            it.endsWith(other)
        }






