package com.ab.basic

/**
 * Arpit Bhardwaj
 *
 *
 * Features of Pattern Matching (PM)
 *  cases are matched in order
 *  what if no case match? MatchError
 *  type of PM expression? unified type of all the types in all the cases
 *  PM works well with case classes
 */
object PatternMatch extends App {
  var amount = 101
  //pattern matching against data

  //when a match happens any further case will not be evaluated
  //If there is no default then match error (scala.MatchError) comes
  amount match {
    case 50   => println("$50")
    case 100  => println("$100")
    case _    => println("Not a $50 or $100 bill")//default case
  }

  amount match {
    case a   => println(a) //this match everything
  }

  //need guards to avoid above case
  amount match {
    case a if a >=50    =>println("bill is >50, it's " + a)
    case a if a == 100  =>println("bill is $100")
    case a              =>println("The amount is " + a)
  }

  //pattern matching is also an expression
  val result: String = amount match {
    case a if a >=50    =>"bill is >50, it's " + a
    case a if a == 100  =>"bill is $100"
    case a              =>"The amount is " + a
  }

  //pattern matching against type
  val currency: Currency = NZD()

  currency match {
    case u:USD => println("USD " + amount)
    case c:CAD => println("CAD " + amount * (1/1.30))
    case n:NZD => println("NZD " + amount * (1/1.50))
  }

  //extracting data from pattern match
  val anArr = Array(100,200,300)

  //extracted second element from array by pattern match
  anArr match {
    case Array(f,s,t) => println("second " + s)
  }

  //or
  anArr match {
    case Array(_,_,t) => println("second " + t)
  }

  //1 - Constants
  val x:Any = "Scala"

  val constants = x match {
    case 1 => "a number"
    case "Scala" => "The Scala"
    case true => "The truth"
    case PatternMatch => "a Singleton object"
  }

  //a tuple is a collection like an array but doesn't require the element of the same type
  val aTuple = ("NZD",100)
  println(aTuple._1)

  aTuple match {
    case (c,a) if c == "NZD" => println("NZD " + amount * (1/1.50))
  }

  //finding elements using pattern match
  //extractor pattern
  val names1 = List("Dale","Susan","Bob","Jen")
  val names2 = List("Honey","Yoo","Bob","Jen")
  val names3 = List("Dale","Yoo","Bob","Hey")
  names3 match {
    case List("Dale",_,_) => println("Dale") //throws match error if there is no default case
    case List(_,"Susan",_*) => println("Susan")
    case "Honey" :: List(_*) => println("Honey") //infix pattern
    case List("Dale","Yoo","Bob") :+ "Hey" => println("Hey")
    case _ => println("Not found")
  }
  //constructor pattern match
  //allows to match against the arguments that were used to construct the case class
  //uses unapply method under the hood
  //so basically constructor uses apply of case class and constructor match use unapply
  //apply(a,b) -> object(a,b)
  //unapply(object(a,b)) -> a,b

  //val person  = Person("Arpit",32)
  val person  = Person("Mino",33)
  person match {
    case Person("Dale",_) => println("found Dale")
    case Person("Arpit",_) => println("found Arpit")
    case Person(n,a) => println(s"found $n having age $a")
  }

  //name bindings
  names3 match {
    case aNonEmptyList @ List("Dale",_,_,_) => println(aNonEmptyList)
    //case List(_,rest @ List("Susan",_*)) => println(rest)
    case _ => println("Not found")
  }

}

sealed class Currency
case class USD() extends Currency
case class CAD() extends Currency
case class NZD() extends Currency

case class Person(name:String, age:Int)

