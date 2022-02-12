package com.ab.oo

object Basics extends App {
  val person = new Person //or new Person() calling an empty constructor
  println(person)

  val people = new People("John", 30)
  println(people)

  //class parameters are not fields
  //println(people.name) //not compile
  //in order to make it field we need to mark it with val or var
  println(people.age)


}

class Person
//constructor or a class with parameters
class People(name:String, val age:Int)
//classes with body
class Guy(name:String, val age:Int){
  //val/var definition
  //function definitions
}

