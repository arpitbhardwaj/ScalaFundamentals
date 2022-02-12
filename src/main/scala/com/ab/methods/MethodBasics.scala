package com.ab.methods

/**
 * Functions and Methods are the terms used interchangeable but there are subtle differences
 *    Functions are objects; methods are not
 *    Methods can be stored in function objects quite easily using method coercing
 *
 * A method
 *    is a reusable code and is defined using def keyword.
 *    is a part of a class, it has name, signature some annotations and bytecode
 *
 * In case you need a method that mutates the data but does not return anything, you can opt for procedures
 *
 * A Procedure
 *    is defined using just curly braces and no '=' sign
 *    does not return anything, its result type is always unit
 *
 * Mentioning return type is optional as it will be inferred by compiler
 * Curly braces are optional is single statement methods
 * return keyword is also optional. the last expression is evaluated to return by compiler
 */
object MethodBasics extends App {

  def volume: Int = 1000 //method takes no input argument and returns integer 1000
  def stockPrice: Int = 79
  def finalVal: Int = stockPrice * volume
  println("Final value is: " + finalVal)
  //println(s"Final value is: $finalVal")

  def volume1: Int = {
    println("Returning volume")
    100
  }
  def stockPrice1: Int = {
    println("Returning stock price")
    79
  }
  def finalVal1: Int = stockPrice1 * volume1
  println("Final value is: " + finalVal1)
  println("---------------------")

  def finalVal1Again: Int = stockPrice1 * volume1
  println(s"Final value is: $finalVal1Again")

  def data: Array[String] = {
    println("Array is initialized")
    Array("FB", "AMA", "GOOG")
  }
  println("Before accessing data")
  data.foreach(println)
  println("\n------------------")
  data.foreach(println)


  def computeSum(a:Int,b:Int): Int = {
    a+b
    //return a+b
  }

  /*def printSum(a:Int,b:Int):Unit = {
    println("Sum of a and b is: " + (a+b))
  }*/

  def printSum(a:Int,b:Int) {
    println("Sum of a and b is: " + (a+b))
  }

  val result = MethodBasics.computeSum(5,4)
  println("Result is: " + result)
  MethodBasics.printSum(2,3)

  //convert method to a function; this uses partially applied functions and process is called method coercing

  val tickers = Array("FB", "APP", "GOOG")

  def existsMethod(ticker: String): Boolean = tickers.contains(ticker)
  val existsFunction = (ticker: String) => tickers.contains(ticker)
  val existsFunction2 = (ticker: String) => existsMethod(ticker)
  //syntactic sugar
  val existsFunction3 = existsMethod _

  println("Does FB exists (method): " + existsMethod("FB"))
  println("Does TM exists (function): " + existsFunction("TM"))
  println("Does APP exists (function): " + existsFunction2("APP"))
  println("Does GOOG exists (function): " + existsFunction3("GOOG"))
}
