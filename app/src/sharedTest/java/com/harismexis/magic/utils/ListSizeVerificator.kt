package com.harismexis.magic.utils

import com.harismexis.magic.parser.MockHerosParser
import org.junit.Assert

fun <T, P> verifyListsHaveSameSize(
    list0: List<T>,
    list1: List<P>
) {
    Assert.assertEquals(list0.size, list1.size)
}

fun <T> verifyListSize(
    expectedSize: Int,
    items: List<T>
) {
    Assert.assertEquals(expectedSize, items.size)
}

fun <T> verifyListSizeWhenAllIdsValid(items: List<T>) {
    verifyListSize(MockHerosParser.EXPECTED_NUM_HEROS_WHEN_ALL_IDS_VALID, items)
}

fun <T> verifyListSizeWhenSomeItemsEmpty(items: List<T>) {
    verifyListSize(MockHerosParser.EXPECTED_NUM_HEROS_WHEN_SOME_EMPTY, items)
}

fun <T> verifyListSizeForNoData(items: List<T>) {
    verifyListSize(MockHerosParser.EXPECTED_NUM_HEROS_WHEN_NO_DATA, items)
}