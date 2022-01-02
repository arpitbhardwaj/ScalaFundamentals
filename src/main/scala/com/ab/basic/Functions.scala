package com.ab.basic

/**
 *
 * Functions are complete standalone object which is a instance of a class that implements traits (Traits are equivalent to interface in java)
 * A Function object has its own method
 * Function object has its own methods
 * Invoking a function invokes the apply method of a function
 * can be anonymous
 * Assigned to val or var type which is then used to invoke function
 * Slightly slower and higher overhead
 * First class entities on par with classes
 * Do not accept type paramaters or parameters default values
 */
object Functions extends App {
  val data = Array("F","A","G")
  //method
  //def getNumRowsMethod(): Int = data.length
  def getNumRowsMethod: Int = data.length //emmty param methods
  val getNumRowsFunction = () => data.length //anonymous method or lambda // a method with no name assigned to a val type

  //println("Total num of rows (methods): " + getNumRowsMethod())
  println("Total num of rows (methods): " + getNumRowsMethod)
  println("Total num of rows (function): " + getNumRowsFunction)

  println(getNumRowsMethod.getClass)//invoked a method that return int and calling getClass on int class
  println(getNumRowsFunction.getClass)

  println(getNumRowsFunction.apply())
  //getNumRowsFunction implements the trait Function0
  println(getNumRowsFunction.isInstanceOf[Function0[_]])
}
