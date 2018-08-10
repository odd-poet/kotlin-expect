package net.oddpoet.expect

/**
 *
 * @author Yunsang Choi
 */
class InvalidExpectationError(message: String, cause: Throwable? = null) : Exception(message, cause)