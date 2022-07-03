package com.ab.advanced

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
/**
 * Implicits
 *  - val/var
 *  - object
 *  - accessor methods - defs with no parameters
 *
 */
object OrganizeImplicits extends App {

  /*
  scope of implicits
    - local scope
    - imported scope
    - companion objects of all types involved in the method signature
       For in below method def sorted[B >: A](implicits ord: Ordering[B]): List[B]
        List
        Ordering
        all the types involved = A or any supertype

   */

  //default ordering implicits comes from scala.Predef

  //local scope
  implicit val reverseOrder: Ordering[Int] = Ordering.fromLessThan(_ > _)
  //implicit def reverseOrder: Ordering[Int] = Ordering.fromLessThan(_ > _)
  //implicit def reverseOrder(): Ordering[Int] = Ordering.fromLessThan(_ > _) //will not work
  //implicit val normalOrder: Ordering[Int] = Ordering.fromLessThan(_ < _) //if you uncomment this the compiler will confuse which to use and throws error

  println(List(1,4, 2, 5, 3).sorted)

  //imported scope
  val future = Future {
    println("hello, Future")
  }

  case class Person(name:String, age:Int)
  val persons = List(
    Person("Steve", 56),
    Person("Robert", 62),
    Person("Bob", 43)
  )

  //implicit val alphabeticReverseOrdering: Ordering[Person] = Ordering.fromLessThan((a, b) => a.name.compareTo(b.name) > 0)
  import AgeOrdering._
  println(persons.sorted)

  //companion objects of the type included in the call scope
  object AlphabeticOrdering {
    implicit val alphabeticOrdering: Ordering[Person] = Ordering.fromLessThan((a, b) => a.name.compareTo(b.name) < 0)
  }

  object AgeOrdering {
    implicit val ageOrdering: Ordering[Person] = Ordering.fromLessThan((a,b) => a.age < b.age)
  }


  println(persons.sorted)


}
