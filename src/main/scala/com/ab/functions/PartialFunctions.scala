package com.ab.functions

/**
 * A partial function
 *    caters to only a subset of possible data for which it has been defined
 *    works on pattern matching
 *    can have only one parameter type
 */
object PartialFunctions extends App{

  val aFunction = (x:Int) => x+1

  val aFussyFunction = (x:Int) =>
    if (x==1) 42
    else if (x==2) 56
    else if(x==5) 999
    else throw new Exception

  val aNicerFussyFunction = (x:Int) => x match {
    case 1 => 42
    case 2 => 56
    case 5 => 999
  } //total function

  val aPartialFunction: PartialFunction[Int, Int] = {
    case 1 => 42
    case 2 => 56
    case 5 => 999
  }//partial function

  println(aPartialFunction(2))
  println(aPartialFunction(788)) //throws match error

  //Partial function utilities

  //isDefinedAt
  println(aPartialFunction.isDefinedAt(78))

  //lifting
  val lifted = aPartialFunction.lift //Int => Option[Int] //convert from partial func to total func
  println(lifted(2))
  println(lifted(4))

  //orElse
  val partialFunctionChain = aPartialFunction.orElse[Int,Int]{
    case 60 => 9000
  } //another partial function

  println(aPartialFunction(5))
  println(aPartialFunction(60))

  //PF extends normal/total function
  //PF are subtype of total function
  val aTotalFunction: Int => Int = {
    case 1 => 99
  }

  //HOF accepts partial function
  val aMappedList = List(1,2,3).map({
    case 1 => 42
    case 2 => 56
    case 3 => 1000
  })

  println(aMappedList)

  val aMappedList2 = List(1,2,3).map({
    case 1 => 42
    case 2 => 56
    case 5 => 1000
  })

  println(aMappedList2) //match error


  /*
  Write a custom manual partial function
   */
  val aManualPartialFunction = new PartialFunction[Int, Int] {
    override def apply(x: Int): Int = x match {
      case 1 => 42
      case 2 => 56
      case 5 => 1000
    }

    override def isDefinedAt(x: Int): Boolean =
      x == 1 | x == 2 | x == 5
  }


  //type aliases
  type ReceiveFunction = PartialFunction[Any,Unit]

  def receive:ReceiveFunction = {
    case 1 => println("hello1")
    case _ =>
  }
}
