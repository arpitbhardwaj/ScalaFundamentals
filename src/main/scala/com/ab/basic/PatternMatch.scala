package com.ab.basic

/**
 * Arpit Bhardwaj
 *
 *
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

  //a tuple is a collection like an array but doesn't require the element of the same type

  val aTuple = ("NZD",100)
  println(aTuple._1)

  aTuple match {
    case (c,a) if c == "NZD" => println("NZD " + amount * (1/1.50))
  }
}

abstract class Currency
case class USD() extends Currency
case class CAD() extends Currency
case class NZD() extends Currency

