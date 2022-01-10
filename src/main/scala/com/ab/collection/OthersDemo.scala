package com.ab.collection

import scala.collection.SortedSet

/**
 * Iterable
 *    Seq
 *      Ordered sequences of elements starting from position 0
 *      duplicates are allowed
 *        LinearSeq (Search O(n) Insert/Delete O(1))
 *            List
 *            LazyList (lot of elements and processing is expensive)
 *            Queue
 *        IndexedSeq (Search O(1) Insert/Delete O(n))
 *            ArraySeq
 *            Vector (Search ~O(1) Insert/Delete ~O(n))
 *            ArrayDeque
 *            Queue
 *            Stack
 *            Range
 *        Buffer (Search O(n) Insert/Delete O(1))
 *    Set
 *        SortedSet
 *        HashSet
 *        LinkedHashSet (not available as immutable)
 *    Map
 *        SortedMap
 *        HashMap
 *        LinkedHashMap
 *        ListMap
 *        VectorHashMap
 */
object OthersDemo extends App {
  Iterable("x","y","z")
  Vector("a","b","c")
  Array(("a",1),("b",2))
  Seq(Map("A"->1, "B"->2),Map("C"->3, "D"->4))
  Set(1,2,3)
  Map("A"->1, "B"->2)


  println(Set(2,4,6) == Set(6,4,2))
  println(Set(2,2,4,6) == Set(6,4,2))
  println(SortedSet(2,4,6) == Set(6,4,2))
}
