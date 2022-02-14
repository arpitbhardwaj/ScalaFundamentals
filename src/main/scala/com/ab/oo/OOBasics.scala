package com.ab.oo

/**
 *
 */
object Basics extends App {
  val person = new Person //or new Person() calling an empty constructor
  println(person)

  val people = new People("John", 30)
  println(people)

  //class parameters are not fields
  //println(people.name) //not compile
  //in order to make it field we need to mark it with val or var
  println(people.age)

  val guy = new Guy("Daniel", 40)
  println(guy.x)
  guy.greet("Bob")
  guy.greet()
}

//a class with no arg-constructor
class Person
//a class with 2 arg constructor
class People(name:String, val age:Int)
//classes with body
class Guy(name:String, val age:Int){

  //multiple constructors
  def this(name:String) = this(name,0) //bit of irrelevant as can be solved with default parameters
  def this() = this("John Doe")

  //val/var definition
  val x = 2

  //expressions
  println(1+4)

  //function definitions (methods)
  def greet(name:String):Unit = println(s"${this.name} says : Hi $name")

  def greet():Unit = println(s"Hi, I am $name")
}

