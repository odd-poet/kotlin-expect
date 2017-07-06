package net.oddpoet.expect

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

    /**
     * Test type of exception.
     *
     * Caught exception should be instance of given expection class.
     * if not, it will throw AssertionError.
     *
     */
    fun <T : Throwable> throws(exceptionClass: KClass<out T>,
                               clause: (T) -> Unit = {}) {
        if (thrown == null) {
            throw AssertionError("expected to occur a exception<$exceptionClass> bu no exception was thrown.")
        }
        if (!exceptionClass.isInstance(thrown)) {
            throw AssertionError("expected <$exceptionClass> to be thrown, but <${thrown::class}> was thrown.", thrown)
        }
        clause(thrown as T)
    }

    /**
     * short-cut method.
     *
     */
    fun throws(clause: (Exception) -> Unit = {}) {
        throws(Exception::class, clause)
    }
}