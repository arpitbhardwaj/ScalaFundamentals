package com.ab.advanced

/**
 * constants
 * wild cards
 * case classes
 * tuples
 * special magic
 */
object PatternMatching extends App {
  val numbers = List(1)
  val description = numbers match {
    case head:: Nil => println(s"the only element is $head")
    case _ => //np op
  }


  class Person(val name:String, val age:Int)

  object PersonPattern{
    def unapply(person: Person):Option[(String,Int)] =
      Some(person.name, person.age)

    def unapply(age: Int):Option[String] =
      Some(if(age < 21) "minor" else "major")
  }

  val bob = new Person("bob", 23)
  val greeting = bob match {
    case PersonPattern(n,a) => s"My name is $n and i'm $a years old"
  }

  println(greeting)

  val legalStatus = bob.age match {
    case PersonPattern(status) => s"My legal status is $status"
  }

  //infix patterns
  case class Or[A, B](a:A, b:B)
  val either = Or(2,"Two")
  val desc = either match {
    case n Or s => s"$n can be written as $s"
  }

  //decomposing sequences

}
