package com.ab.basic

object PatternMatch extends App {
  var amount = 101
  amount match {
    case 50=>println("$50")
    case 100=>println("$100")
    case _ => println("Not a $50 or $100 bill")
  }

  amount match {
    case a if a >=50 =>println("bill is >50, it's " + a)
    case a if a == 100 =>println("bill is $100")
    case a=>println("The amount is " + a)
  }

  val result: String = amount match {
    case a if a >=50 =>"bill is >50, it's " + a
    case a if a == 100 =>"bill is $100"
    case a=>"The amount is " + a
  }
}
