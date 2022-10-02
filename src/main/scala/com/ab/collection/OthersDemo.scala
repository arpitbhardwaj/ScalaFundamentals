package com.ab.collection

import scala.collection.SortedSet
import scala.util.Random

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

  //Vector
  Vector("a","b","c")
  val aVector = Vector(1,3,2,4)
  println(aVector)

  //vector vs list
  val maxRuns = 1000
  val maxCapacity = 100000

  def getWriteTime(collection: Seq[Int]): Double = {
    val r = new Random
    val times = for {
      it <- 1 to maxRuns
    } yield {
      val currentTime = System.nanoTime()
      collection.updated(r.nextInt(maxCapacity), r.nextInt())
      System.nanoTime() - currentTime
    }

    times.sum * 1.0 / maxRuns
  }

  val numbersList = (1 to maxCapacity).toList
  val numbersVector = (1 to maxCapacity).toVector

  println(getWriteTime(numbersList))
  println(getWriteTime(numbersVector))

  //Arrays
  Array(("a",1),("b",2))
  val aArray = Array(1,3,2,4)
  val threeEle = Array.ofDim[String](3)
  threeEle.foreach(println)

  //mutation
  aArray(2) = 0 //syntactic sugar for aArray.update(2,0)
  println(aArray.mkString(" "))

  //arrays and seq
  val aSeq: Seq[Int] = aArray
  println(aSeq)

  Seq(Map("A"->1, "B"->2),Map("C"->3, "D"->4))
  Set(1,2,3)

  //Maps
  val aMap: Map[String,Int] = Map("A"->1, "B"->2)
  val phoneBook = Map(("Jim",555), "Daniel" -> 345)
  println(phoneBook)
  println(phoneBook.contains("Jim"))
  println(phoneBook("Jim"))
  //println(phoneBook("Arpit")) //NoSuchElementException
  val pairing = "Marry" -> 897
  val newPhoneBook = phoneBook + pairing
  println(newPhoneBook)

  println(phoneBook.map(p => p._1.toLowerCase -> p._2))
  println(phoneBook.filterKeys(x => x.startsWith("J")))
  println(phoneBook.mapValues(n => n * 10))

  println(Set(2,4,6) == Set(6,4,2))
  println(Set(2,2,4,6) == Set(6,4,2))
  println(SortedSet(2,4,6) == Set(6,4,2))
}
