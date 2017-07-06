package net.oddpoet.expect.extension

import net.oddpoet.expect.Expect


fun <E : Any?, T : Iterable<E>> Expect<T>.contain(item: E?) =
        satisfyThat("contain <$item>") {
            it?.contains(item) ?: false
        }

fun <E : Any?, T : Iterable<E>> Expect<T>.containAny(predicate: (E) -> Boolean) =
        satisfyThat("contain any satisfying given predicate") {
            it?.any(predicate) ?: false
        }

fun <E : Any?, T : Iterable<E>> Expect<T>.containAll(predicate: (E) -> Boolean) =
        satisfyThat("contain all satisfying  given predicate") {
            it?.all(predicate) ?: false
        }

fun <E : Any?, T : Iterable<E>> Expect<T>.containNone(predicate: (E) -> Boolean) =
        satisfyThat("contain none satisfying given predicate") {
            it?.none(predicate) ?: false
        }


fun <E : Any?, T : Collection<E>> Expect<T>.containAll(vararg items: E) =
        satisfyThat("contain all of <${items.toList()}>") {
            it?.containsAll(items.toList()) ?: false
        }

fun <T : Collection<*>> Expect<T>.haveSizeOf(size: Int) =
        satisfyThat("have size of <$size>") {
            it?.size == size
        }

fun <T : Collection<*>> Expect<T>.beEmpty() =
        satisfyThat("be empty") {
            it?.isEmpty() ?: false
        }


fun <E : Any?, T : List<E>> Expect<T>.containAllInSameOrder(vararg items: E) =
        satisfyThat("contain all <${items.toList()}> in same order ") {
            if (it == null || !it.containsAll(items.toList())) false
            else {
                val indice = items.map { i -> it.indexOf(i) }
                indice.sorted() == indice
            }
        }

fun <E : Comparable<E>, T : List<E>> Expect<T>.beSorted() =
        satisfyThat("be sorted") {
            it?.let { it.sorted() == it }
                    ?: false
        }

fun <E : Comparable<E>, T : List<E>> Expect<T>.beReverseSorted() =
        satisfyThat("be sorted in reverse order") {
            it?.let { it.sorted().reversed() == it }
                    ?: false
        }


fun <E : Any?, T : List<E>> Expect<T>.beSortedWith(comparator: Comparator<E>) =
        satisfyThat("be sorted with given comparator") {
            it?.let { it.sortedWith(comparator) == it }
                    ?: false
        }

fun <E : Any?, T : List<E>> Expect<T>.beReverseSortedWith(comparator: Comparator<E>) =
        satisfyThat("be sorted in reverse order with given comparator") {
            it?.let { it.sortedWith(comparator).reversed() == it }
                    ?: false
        }

fun <K : Any?, V : Any, T : Map<K, V>> Expect<T>.containKey(key: K?) =
        satisfyThat("contain key <$key>") {
            it?.containsKey(key) ?: false
        }

fun <K : Any?, V : Any, T : Map<K, V>> Expect<T>.containKeys(vararg keys: K) =
        satisfyThat("contain keys <${keys.toList()}>") {
            it?.let { map ->
                keys.all { map.containsKey(it) }
            } ?: false
        }

fun <K : Any?, V : Any, T : Map<K, V>> Expect<T>.containValue(value: V?) =
        satisfyThat("contain value <$value>") {
            it?.containsValue(value) ?: false
        }

fun <K : Any?, V : Any, T : Map<K, V>> Expect<T>.containValues(vararg values: V?) =
        satisfyThat("contain values <${values.toList()}>") {
            it?.let { map ->
                values.all { map.containsValue(it) }
            } ?: false
        }


fun <K : Any?, V : Any, T : Map<K, V>> Expect<T>.containEntry(key: K?, value: V?) =
        satisfyThat("contain entry <$key:$value>") {
            it?.let {
                it.containsKey(key) && it[key] == value
            } ?: false
        }

fun <K : Any?, V : Any, T : Map<K, V>> Expect<T>.containEntries(vararg pairs: Pair<K?, V?>) =
        satisfyThat("contain entries <${pairs.toList()}>") {
            it?.let { map ->
                pairs.all { map.containsKey(it.first) && map[it.first] == it.second }
            } ?: false
        }

