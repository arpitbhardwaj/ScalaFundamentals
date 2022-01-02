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
  println("Defined at 8: " + anotherDivide64By.isDefinedAt(8))
  println(if(anotherDivide64By.isDefinedAt(0)) anotherDivide64By(0))




}
