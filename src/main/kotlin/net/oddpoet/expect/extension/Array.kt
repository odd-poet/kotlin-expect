package net.oddpoet.expect.extension

import net.oddpoet.expect.Expect

/**
 * Extension: Array
 *
 * @author Yunsang Choi
 */

fun <E : Any?> Expect<Array<E>>.contain(item: E?) =
        satisfyThat("contain <${item.literal}>") {
            it.contains(item)
        }

fun <E : Any?> Expect<Array<E>>.haveSizeOf(size: Int) =
        satisfyThat("have size of <${size.literal}>") {
            it.size == size
        }

fun <E : Any?> Expect<Array<E>>.containAllInSameOrder(vararg items: E) =
        satisfyThat("contain all <${items.literal}> in same order ") {
            if (!it.toList().containsAll(items.toList())) false
            else {
                val indice = items.map { i -> it.indexOf(i) }
                indice.sorted() == indice
            }
        }

fun <E : Any?> Expect<Array<E>>.beEmpty() =
        satisfyThat("be empty") {
            it.isEmpty()
        }

fun <E : Any?> Expect<Array<E>>.containAll(vararg items: E) =
        satisfyThat("contain all of <${items.literal}>") {
            it.toList().containsAll(items.toList())
        }


fun <E : Any?> Expect<Array<E>>.containAny(predicate: (E) -> Boolean) =
        satisfyThat("contain any satisfying given predicate") {
            it.any(predicate)
        }

fun <E : Any?> Expect<Array<E>>.containAll(predicate: (E) -> Boolean) =
        satisfyThat("contain all satisfying  given predicate") {
            it.all(predicate)
        }

fun <E : Any?> Expect<Array<E>>.containNone(predicate: (E) -> Boolean) =
        satisfyThat("contain none satisfying given predicate") {
            it.none(predicate)
        }

fun <E : Comparable<E>> Expect<Array<E>>.beSorted() =
        satisfyThat("be sorted") {
            it.sorted() == it.toList()
        }

fun <E : Comparable<E>> Expect<Array<E>>.beReverseSorted() =
        satisfyThat("be sorted in reverse order") {
            it.sorted().reversed() == it.toList()
        }


fun <E : Any?> Expect<Array<E>>.beSortedWith(comparator: Comparator<E>) =
        satisfyThat("be sorted according to the given comparator") {
            it.sortedWith(comparator) == it.toList()
        }

fun <E : Any?> Expect<Array<E>>.beReverseSortedWith(comparator: Comparator<E>) =
        satisfyThat("be sorted in reverse order according to the given comparator") {
            it.sortedWith(comparator).reversed() == it.toList()
        }