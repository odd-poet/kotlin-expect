package net.oddpoet.expect.extension

import net.oddpoet.expect.Expect

/**
 * Extension: Collection
 *
 * @author Yunsang Choi
 */
fun <E : Any?, T : Iterable<E>> Expect<T>.contain(item: E?) =
        satisfyThat("contain <${item.literal}>") {
            it.contains(item)
        }

fun <E : Any?, T : Iterable<E>> Expect<T>.containAny(predicate: (E) -> Boolean) =
        satisfyThat("contain any satisfying given predicate") {
            it.any(predicate)
        }

fun <E : Any?, T : Iterable<E>> Expect<T>.containAll(predicate: (E) -> Boolean) =
        satisfyThat("contain all satisfying  given predicate") {
            it.all(predicate)
        }

fun <E : Any?, T : Iterable<E>> Expect<T>.containNone(predicate: (E) -> Boolean) =
        satisfyThat("contain none satisfying given predicate") {
            it.none(predicate)
        }


fun <E : Any?, T : Collection<E>> Expect<T>.containAll(vararg items: E) =
        satisfyThat("contain all of <${items.literal}>") {
            it.containsAll(items.toList())
        }

fun <T : Collection<*>> Expect<T>.haveSizeOf(size: Int) =
        satisfyThat("have size of <${size.literal}>") {
            it.size == size
        }

fun <T : Collection<*>> Expect<T>.beEmpty() =
        satisfyThat("be empty") {
            it.isEmpty()
        }

fun <E : Any?, T : List<E>> Expect<T>.containAllInSameOrder(vararg items: E) =
        satisfyThat("contain all <${items.literal}> in same order ") {
            if (!it.containsAll(items.toList())) false
            else {
                val indice = items.map { i -> it.indexOf(i) }
                indice.sorted() == indice
            }
        }

fun <E : Comparable<E>, T : List<E>> Expect<T>.beSorted() =
        satisfyThat("be sorted") {
            it.sorted() == it
        }

fun <E : Comparable<E>, T : List<E>> Expect<T>.beReverseSorted() =
        satisfyThat("be sorted in reverse order") {
            it.sorted().reversed() == it
        }


fun <E : Any?, T : List<E>> Expect<T>.beSortedWith(comparator: Comparator<E>) =
        satisfyThat("be sorted according to the given comparator") {
            it.sortedWith(comparator) == it
        }

fun <E : Any?, T : List<E>> Expect<T>.beReverseSortedWith(comparator: Comparator<E>) =
        satisfyThat("be sorted in reverse order according to the given comparator") {
            it.sortedWith(comparator).reversed() == it
        }

fun <K : Any?, V : Any, T : Map<K, V>> Expect<T>.containKey(key: K?) =
        satisfyThat("contain key <${key.literal}>") {
            it.containsKey(key)
        }

fun <K : Any?, V : Any, T : Map<K, V>> Expect<T>.containKeys(vararg keys: K) =
        satisfyThat("contain keys <${keys.literal}>") {
            keys.all { key -> it.containsKey(key) }
        }

fun <K : Any?, V : Any, T : Map<K, V>> Expect<T>.containValue(value: V?) =
        satisfyThat("contain value <${value.literal}>") {
            it.containsValue(value)
        }

fun <K : Any?, V : Any, T : Map<K, V>> Expect<T>.containValues(vararg values: V?) =
        satisfyThat("contain values <${values.literal}>") {
            values.all { value -> it.containsValue(value) }
        }


fun <K : Any?, V : Any, T : Map<K, V>> Expect<T>.containEntry(key: K?, value: V?) =
        satisfyThat("contain entry <${key.literal}:${value.literal}>") {
            it.containsKey(key) && it[key] == value
        }

fun <K : Any?, V : Any, T : Map<K, V>> Expect<T>.containEntries(vararg pairs: Pair<K?, V?>) =
        satisfyThat("contain entries <${pairs.literal}>") {
            pairs.all { pair -> it.containsKey(pair.first) && it[pair.first] == pair.second }
        }

