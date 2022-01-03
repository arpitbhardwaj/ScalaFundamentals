package com.ab.collection

import scala.::

/**
 * List is immutable
 * cons (::) operator constructs an object from a pair of values
 * cons appends (prepends internally in O(1)) a value to a list
 * Nil is the empty list
 * Nil extends list
 * a::Nil     Infix form
 * Nil.::(a)  Long hand form
 */
object ListDemo extends App {
  List()//empty list of type nothing
  List(1,2,3,4)
  List("A","B")
  val colors = List("Red","Green")
  var names:List[String] = List()

  val colors2 = "red" :: ("green" :: Nil)
  val colors3 = "red" :: ("green":: ("yellow"::Nil))
  val colors4 = "red" :: "green":: "yellow"::Nil

  //traversing lists
  val numbers = List(2,9,4)
  numbers.foreach(print(_))

  var iter = numbers.iterator
  while (iter.hasNext) print(iter.next())

  //for loops in scala as generator based for loops
  //converted to iterator based for each under the hood
  for (element <- numbers.iterator) print(element)
  for (element <- numbers) print(element)
}
