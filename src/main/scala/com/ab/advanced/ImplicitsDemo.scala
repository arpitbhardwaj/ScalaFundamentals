package com.ab.advanced

import scala.concurrent.Future

/**
 * implicit tells scala compiler to find the opportunities where this can be used behind the scene
 */
object ImplicitsDemo extends App {
  //implicit conversions

  //1.implicit def
  case class Person(name: String) {
    def greet = println(s"Hi my name is $name")
  }
  implicit def fromStrToPerson(str: String): Person = Person(str)
  "Peter".greet //compiler rewrites to fromStrToPerson("Peter").greet

  //2.implicit classes
  implicit class Dog(name: String) {
    def bark = println("bark")
  }
  "Bruno".bark

  //3. implicits parameters (not same as default args)
  implicit val timeout = 3000
  def setTimeout(f: () => Unit)(implicit timeout: Int) = f()
  setTimeout(() => println(s"timeout: $timeout"))
}
