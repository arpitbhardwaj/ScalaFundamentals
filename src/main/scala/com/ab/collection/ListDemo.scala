package com.ab.collection

import scala.::

/**
 * List is immutable
 * cons (::) operator constructs an object from a pair of values
 * cons appends (prepends internally in O(1)) a value to a list
 * Nil is the empty list
 * Nil extends list
 */
object ListDemo extends App {

  val aList = List(1,2,3)
  val prepended = 42 +: aList :+ 45
  println(prepended)

  val apple5 = List.fill(5)("apple")
  println(apple5)
  println(aList.mkString("-|-"))
  //declaration and initialization
  List()//empty list of type nothing
  List(1,2,3,4)
  List("A","B")
  val colors1 = List("Red","Green")
  val colors2 = "red" :: ("green" :: Nil)
  val colors3 = "red" :: ("green":: ("yellow"::Nil))
  val colors4 = "red" :: "green":: "yellow"::Nil
  var colors5:List[String] = List()

  //traversing lists
  val numbers = List(2,9,4)

  //using foreach
  numbers.foreach(print(_))
  println()

  //using iterator
  var iter = numbers.iterator
  while (iter.hasNext) print(iter.next())
  println()

  //for loops in scala as generator based for loops
  //converted to iterator based foreach under the hood
  for (element <- numbers.iterator) print(element)
  println()

  //more concise
  for (element <- numbers) print(element)
  println()

  //recursive processing of element in list
  def sum(list: List[Int]): Int = {
    if (list.isEmpty) 0
    else list.head + sum(list.tail)
  }

  //finding elements using pattern match
  val names1 = List("Dale","Susan","Bob","Jen")
  names1 match {
    case List("Dale",_,_) => println("Dale")
    case List(_,"Susan",_*) => println("Susan")
    case _ => println("Not found")
  }

  val names2 = List(List("Dale","Susan"),List("Bob","Jen"))
  names2 match {
    case List(List("Dale",_,_),_*) => println("Dale")
    case List(_,List("Bob",_*),_*) => println("Bob")
  }

  //finding elements using head and tale (recursion)
  def find[A](target:A, list: List[A]): Option[A] = {
    if (list.isEmpty) None
    else if (list.head == target) Some(list.head)
    else find(target,list.tail) //list.tail because internally the elements prepends in the list and the tail will be the first element while head will be last
  }

  //infinite list
  //def infiniteList: List[Int] = 1::2::3::infiniteList
  //println(sum(infiniteList))

  val names = List("Bob", "James", "Angeli", "Mukes", "Rames")
  println(names.groupBy(name => name.charAt(0)))
}
