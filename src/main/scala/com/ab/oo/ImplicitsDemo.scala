package com.ab.oo

import scala.concurrent.Future

/**
 * implicit tells scala compiler to find the opportunities where this can be used behind the scene
 */
object ImplicitsDemo extends App {
  implicit val timeout = 3000
  def setTimeout(f:()=>Unit)(implicit timeout:Int)=f()

  setTimeout(()=>println("timeout"))

  //implicit conversions
  //1.implicit def
  case class Person(name:String){
    def greet = s"Hi my name is $name"
  }

  implicit def fromStrToPerson(str:String):Person = Person(str)
  "Peter".greet

  //2.implicit classes
  implicit class Dog(name:String){
    def bark = println("bark")
  }
  "Bruno".bark

  //organize implicits
  //local scope
  implicit val reverseOrder:Ordering[Int] = Ordering.fromLessThan(_ > _)
  println(List(1,2,3).sorted)

  //imported scope
  import scala.concurrent.ExecutionContext.Implicits.global
  val future = Future{
    println("hello, Future")
  }

  //companion objects of the type included in the call
  object Person{
    implicit val personOrder:Ordering[Person] = Ordering.fromLessThan((a,b) => a.name.compareTo(b.name) < 0)
  }
  println(List(Person("bob"),Person("alice")).sorted)
}
