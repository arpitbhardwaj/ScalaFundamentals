package com.ab.collection

/**
 * A (very) general interface for data structures that
 *    have a well defined order
 *    can be indexed
 *
 * Supports various operations
 *    apply, iterator, length, reverse for indexing and iterating
 *    concatenation, appending and prepending
 *    grouping, sorting zipping, searching, slicing
 */
object SeqDemo extends App {
  val aSequence = Seq(1,3,2,4)
  println(aSequence)
  println(aSequence.reverse)
  println(aSequence(2))
  println(aSequence ++ Seq(7,5,6))
  println(aSequence.sorted)


  //Ranges
  val aRange: Seq[Int] = 1 until 10
  aRange.foreach(println)
  (1 to 10).foreach(_ => println("Hello"))
}
