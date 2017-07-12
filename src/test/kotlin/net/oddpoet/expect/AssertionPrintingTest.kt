package net.oddpoet.expect

import org.junit.After
import org.junit.Before
import org.slf4j.LoggerFactory

abstract class AssertionPrintingTest {
    protected val log = LoggerFactory.getLogger(this.javaClass)

    @Before
    fun setUp() {
        Expect.debug = true
    }

    @After
    fun tearDown() {
        Expect.debug = false
    }
}