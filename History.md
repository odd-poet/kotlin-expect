## Change History

## 1.3.2

date: 2024.12.14

- add `expectThrows<T> {...}` expression
- add: Throwable.should.haveCause(type)
- add: Expect<Duration> (java/kotlin)

## 1.3.1

date: 2022.02.01

- upgrade kotlin version to 1.3.30
- gradle version up to 7.3.1
- migrate junit 4 to junit 5
- add assertion for `ByteArray`, `Instant`
- fix: literalizer error for `LocalDateTime`
- refactor: make literalizer internal class

## 1.3.0

date: 2021.09.29

- kotlin version up : 1.4.31
- use junit 5
- some extensions added

## 1.2.2

date: 2019.04.18

- improve: literal for throwable object will show it's own message.

## 1.2.1

date: 2018.09.04

- fix: long collection would be printed with multiline in log
- add: `Map.should.haveEntries(size)`

## 1.2.0

date: 2018.09.04

- feature: could describe prop of subject in message log and exception message

## 1.1.2

date: 2018.09.04

- fix: scope of extension `Any.literal`

## 1.1.1

date: 2018.08.19

- change `should.be()` to compare with same type object.

## 1.1.0

date: 2018.08.19

- add assertions for `Array`
- add assertions for `File`

## 1.0.1

date: 2018.08.19

- modify logging for `InputStream.equalAsText()`

## 1.0.0

date: 2018.08.11

- add assertion for `Instant`

## 0.9.0

date: 2018.08.10

- upgrade kotlin : 1.2.60
- upgrade gradle : 4.9
- bintray plugin : 1.8.4
- add extension for 'Boolean'
- logging for 'expect { }.throws()'
- add assertions(`haveLengthWith`, `haveLenghIn`) for `String`
- add assertion(`beIn`) for `Number`
- add assertion(`equalAsText`)  for `InputStream`
- fix: unchecked cast warning

## 0.8.4

date: 2017.07.26

- separate literal generator
- rename `satisfyThat` to `satisfyThatForNullable`
- add new method `satisfyThat` for non-nullable type
- add assertion `containString`, `containChar` to `String` type

## 0.8.2

date: 2017.07.13

- add javadoc Jar as artifact

## 0.8.1

date: 2017.07.13

- remove generic from final class extension: `Double`, `Float`, `Int`, `Long`

## 0.8.0

date: 2017.07.12

- initial release
