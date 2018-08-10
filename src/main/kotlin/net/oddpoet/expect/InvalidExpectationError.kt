package net.oddpoet.expect

/**
 * 유효하지 않은 값으로 expectation을 기술하는 경우 발생하는 예외.
 *
 * @author Yunsang Choi
 */
class InvalidExpectationError(message: String, cause: Throwable? = null) : Exception(message, cause)