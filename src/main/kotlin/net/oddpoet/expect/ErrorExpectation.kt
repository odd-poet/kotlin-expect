package net.oddpoet.expect

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import kotlin.reflect.KClass

/**
 * Expectation of error.
 *
 * it's catch given block and test the exception.
 *
 * @author Yunsang Choi
 */
class ErrorExpectation
internal constructor(block: () -> Unit) {
    // execute and catch
    private val thrown: Throwable? = try {
        block()
        null
    } catch (e: Throwable) {
        e
    }
    private val log: Logger = LoggerFactory.getLogger(this.javaClass)

    /**
     * Test type of exception.
     *
     * Caught exception should be instance of given expection class.
     * if not, it will throw AssertionError.
     *
     */
    fun <T : Throwable> throws(
        exceptionClass: KClass<out T>,
        clause: (T) -> Unit = {},
    ) {
        if (thrown == null) {
            log.debug("No exception had been thrown : FAIL")
            throw AssertionError("expected to occur a exception<$exceptionClass> but no exception was thrown.")
        }
        if (!exceptionClass.isInstance(thrown)) {
            log.debug("${thrown.literal} has been thrown, but expected <$exceptionClass> : FAIL")
            throw AssertionError("expected <$exceptionClass> to be thrown, but <${thrown::class}> was thrown.", thrown)
        }
        log.debug("${thrown.literal} has been thrown (expected:<$exceptionClass>) : OK")
        @Suppress("UNCHECKED_CAST")
        clause(thrown as T)
    }


    /**
     * reified version.
     */
    @JvmName("reifiedThrows")
    inline fun <reified T : Throwable> throws(noinline clause: (T) -> Unit = {}) {
        throws(T::class, clause)
    }

    /**
     * short-cut method.
     *
     */
    fun throws(clause: (Exception) -> Unit = {}) {
        throws(Exception::class, clause)
    }

    // Expect class scoped extension (for print object in assertion message)
    internal val <X : Any?> X.literal: String
        get() = Literalizer.literal(this)
}
