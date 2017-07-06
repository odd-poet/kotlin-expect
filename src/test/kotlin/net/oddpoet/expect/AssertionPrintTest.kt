package net.oddpoet.expect

import org.junit.After
import org.junit.Before

abstract class AssertionPrintTest {
    @Before
    fun setUp() {
        Expect.printAssertion = true
    }

    @After
    fun tearDown() {
        Expect.printAssertion = false
    }
}