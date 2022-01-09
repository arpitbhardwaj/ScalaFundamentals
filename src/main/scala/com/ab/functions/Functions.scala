package com.ab.functions

/**
 * Functions are parameterized expression blocks
 * or
 * Functions are complete standalone object
 * which is a instance of a class that implements traits based on number of arguments i.e Function0, Function1
 * Function object has its own methods
 * Invoking a function invokes the apply method of a function object
 * Function can be anonymous
 * Function can be stored to val or var type which is then used to invoke function
 * The return value of a function can be a function
 * A parameter of a function can be a function
 * Slightly slower and higher overhead
 * First class entities on par with classes
 * Do not accept type parameters or parameters default values
 */
object  Functions extends App {
  val data = Array("FB", "APP", "GOOG")

  //method
  //def getNumRowsMethod(): Int = data.length
  def getNumRowsMethod: Int = data.length //emmty param methods

  val getNumRowsFunction = () => data.length //anonymous method or lambda // a method with no name assigned to a val type

  //println("Total num of rows (methods): " + getNumRowsMethod())
  println("Total num of rows (methods): " + getNumRowsMethod)
  println("Total num of rows (function): " + getNumRowsFunction)

  println(getNumRowsMethod.getClass) //invoked a method that return int and calling getClass on int class
  println(getNumRowsFunction.getClass)

  println(getNumRowsFunction.apply())
  //getNumRowsFunction implements the trait Function0
  println(getNumRowsFunction.isInstanceOf[Function0[_]])

  def existsMethod(ticker: String): Boolean = data.contains(ticker)

  val existsFunction = (ticker: String) => data.contains(ticker)

  //create a function object from a method; this uses partially applied functions
  val existsFunction2 = existsMethod _
  val existsFunction3 = (ticker: String) => existsMethod(ticker)

  println("Does FB exists (method): " + existsMethod("FB"))
  println("Does TM exists (function): " + existsFunction("TM"))
  println("Does GOOG exists (function): " + existsFunction2("GOOG"))
  println("Does APP exists (function): " + existsFunction3("APP"))
}
