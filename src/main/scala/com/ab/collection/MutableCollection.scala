package com.ab.collection

import scala.collection.{immutable, mutable}

//import scala.collection.mutable.Set

/**
 * When you create a collection
 *    by default scala always pics immutable collection from a immutable package
 *    to use mutable one, you need to specifically import the mutable package
 */
object MutableCollection extends App {

  var s = Set(1,2,3)
  println(s.hashCode())
  //for mutable set
  //s += 4
  //immutable set doesn't offer += instead its has +
  s + 4
  println(s)
  println(s.hashCode())

  //convert mutable <-> immutable
  val a = immutable.Set(1,2,3)
  val b = a.to(mutable.Set)
  val c = a to mutable.Set //infix notation
  val d = c to immutable.Set
}
