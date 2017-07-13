# kotlin-expect 
[![Build Status](https://travis-ci.org/odd-poet/kotlin-expect.svg?branch=master)](https://travis-ci.org/odd-poet/kotlin-expect)[![codecov](https://codecov.io/gh/odd-poet/kotlin-expect/branch/master/graph/badge.svg)](https://codecov.io/gh/odd-poet/kotlin-expect)

`kotlin-expect` is a assertion library for kotlin test. it's inspried by [Rspec Expectation].


## Setup 

```gradle
dependencies {
    testCompile("net.oddpoet:kotlin-expect:0.8.2")
}
```

## Basic Usage 

### `expect(s).to`

You can write an assertion for a subject in the form `expect(subject).to`.  

```kotlin
val list = listOf(1, 2, 3)
expect(list).to.haveSizeOf(3)
expect(list).to.satisfy { size == 3}
expect(list).to.not.contain(5)
expect(list).to.containAll { it < 10 }
expect(result).to.not.beInstanceOf(Set::class)
```

### `should`

Alternatively, you can write assertions in the form `subject.should` more simply.

```kotlin
"hello".should.startWith("h")
"hello".should.not.endWith("x", ignoreCase = true)
"believe".should.match("lie")
"hello".length.should.be(5)
```

### `expect(s) { ... }`

You can also create multiple assertions for an object in the form `expect(s) {...}`
```kotlin
expect(aList) {
    it.should.haveSizeOf(10)
    it.should.not.contain("hello")
    it.should.containAny { it.lenngth < 2 }
}
```
### `expect { }.throws()`

An assertion for an exception can be written in the form `expect { ... }.throws()`.

```kotlin
expect {
    throw IOExpection()
}.throws()

expect {
    throw NoSuchFileException("file.txt")
}.throws(IOExcpetion::class) {
    it.message.should.be("file.txt")
}

```

## Write own your assertion
 
`Kotlin-expect` has built-in assertions 
for java base types(`String`, `Collection`, `Map`, `Number` and so on).
You can define new assertions for your class.
An assertion for a class is defined as an extension of the `Expect` class.

```kotlin
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
            it?.let {
                it.birthdate.plusYears(19) > LocalDate.now()
            } ?: false
        }

fun Expect<Employee>.beValidData() =
        satisfyThat("be valid") {
            it?.let { it.empNo != null && it.dept != null } ?: false
        }

fun Expect<Employee>.beAssignedTo(dept: String) =
        satisfyThat("be assigned to $dept") {
            it?.let { it.dept == dept } ?: false
        }

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

```

[Rspec Expectation]:https://github.com/rspec/rspec-expectations