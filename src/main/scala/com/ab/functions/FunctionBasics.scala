package com.ab.functions

/**
 * Functions are
 *    first class citizens
 *    is a instance of a class that implements traits based on number of arguments i.e Function0, Function1...Function22
 *    has its own methods apply() toString() etc.
 *    can be anonymous
 *    can be stored to val or var type which is then used to invoke function
 *    return value or parameter of a function can be a function
 *    slightly slower and higher overhead
 *    Do not accept type parameters or parameters default values
 */
object  FunctionBasics extends App {
  val data = Array("FB", "APP", "GOOG")

  val getNumRowsFunction0: Function0[Int] = new Function0[Int]{
    override def apply():Int= data.length
  }

  val getNumRowsFunction1:(()=> Int) = new Function0[Int]{
    override def apply():Int= data.length
  }

  val getNumRowsFunction2 = new Function0[Int]{
    override def apply():Int = data.length
  }

  //syntactic sugar
  val getNumRowsFunction = () => data.length //anonymous method or lambda // a function with no name assigned to a val type

  println("function object: " + getNumRowsFunction)

  //invoking a function invokes the apply method of a function object
  println("Total num of rows (function): " + getNumRowsFunction.apply())
  //syntactic sugar
  println("Total num of rows (function): " + getNumRowsFunction())

  println(getNumRowsFunction().getClass)

  //getNumRowsFunction implements the trait Function0
  println(getNumRowsFunction.isInstanceOf[Function0[_]])
}
