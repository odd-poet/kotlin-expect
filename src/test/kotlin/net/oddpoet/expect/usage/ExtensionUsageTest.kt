package net.oddpoet.expect.usage

import net.oddpoet.expect.Expect
import net.oddpoet.expect.expect
import net.oddpoet.expect.should
import org.junit.Test
import java.time.LocalDate

class ExtensionUsageTest {

    @Test
    fun `you can define own your assertion syntax`() {
        // then you can use your assertion.
        val emp: Employee = Employee(
                "yunsang.choi",
                LocalDate.of(1976, 4, 2),
                "X00000",
                "DevTeam")
        expect(emp) {
            it.should.beValidData()
            it.should.not.beUnderage()
        }
        emp.should.not.beAssignedTo("DesignTeam")
    }
}

// for your classes
abstract class Person(
        val name: String,
        val birthdate: LocalDate)

class Employee(
        name: String, birthdate: LocalDate,
        val empNo: String?,
        val dept: String?) : Person(name, birthdate)

// you can write your own assertion
fun <T : Person> Expect<T>.beUnderage() =
        satisfyThat("be underage") {
            it.birthdate.plusYears(19) > LocalDate.now()
        }

fun Expect<Employee>.beValidData() =
        satisfyThat("be valid") {
            it.empNo != null && it.dept != null
        }

fun Expect<Employee>.beAssignedTo(dept: String) =
        satisfyThat("be assigned to ${dept.literal}") {
            it.dept == dept
        }
