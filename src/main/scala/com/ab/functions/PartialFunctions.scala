package com.ab.functions

/**
 * A partial function caters to only a subset of possible data for which it has been defined
 */
object PartialFunctions extends App{
  //val divide64By = (x:Int) => 64/x

  //println(divide64By(8))
  //println(divide64By(0))

  /*val anotherDivide64By = new PartialFunction[Int,Int] {
    override def isDefinedAt(x: Int): Boolean = x!=0
    override def apply(x: Int): Int = 64/x
  }*/

  //using pattern matching
  val anotherDivide64By: PartialFunction[Int,Int] ={
    case x:Int if x != 0 => 64/x
  }

  //partial function assignment
  val aFunction: (Int => Int) = anotherDivide64By

  println("Defined for 8: " + anotherDivide64By.isDefinedAt(8))
  println(if(anotherDivide64By.isDefinedAt(0)) anotherDivide64By(0))

  val partialFunction: PartialFunction[Int,Int] = {
    case 1 => 42
    case 2 => 67
  }

  //orElse
  val pfChain = partialFunction.orElse[Int,Int]{
    case 60 => 9000
  }

  val modifiedList1 = List(1,2,3).map({
    case 1 => 42
    case _ => 0
  })

  val modifiedList2 = List(1,2,3).map{
    case 1 => 42
    case _ => 0
  }

  //lifting
  val lifted = partialFunction.lift
  println(lifted(2))
  println(lifted(4))
  println(pfChain(60))
  pfChain(89) //throws match error

  //type aliases
  type ReceiveFunction = PartialFunction[Any,Unit]

  def receive:ReceiveFunction = {
    case 1 => println("hello1")
    case _ =>
  }
}
