package com.ab.functions

/**
 *
 * Function literal in the source code get compiled into a class that is instantiated at runtime
 *
 * A function expression block which holds multiple statements with in curly braces
 */
object FunctionLiteralsNPlaceholders extends App {

  //function literals
  (x: Int) => x * 100

  //function literals assigned to variable
  var multiplyBy = (x: Int) => x * 100

  println(multiplyBy(3))
  //variable can be reassigned to another function value but that func value should be of same type
  multiplyBy = (x: Int) => x * 100 * 20
  //multiplyBy = (x:String) => x.toUpperCase() //not allowed

  var calResult = (x: Int, y: Int) => {
    val sum = x + y
    val diff = x - y
    val pro = x * y
    val quo = x / y

    (sum, diff, pro, quo)
  }

  println(calResult(10, 5))

  val stockPrices = List(13.34, 23.45, 56.34, 12.67, 20.00)
  stockPrices.foreach((price: Double) => println(price))

  val stockPricesGreaterThan20 = stockPrices.filter((price: Double) => price > 20)
  println(stockPricesGreaterThan20)

  //function literals can be written in a shorter form if the type of input can be inferred
  //this is called target typing
  val stockPricesLessThan20 = stockPrices.filter(price => price < 20)
  println(stockPricesLessThan20)

  //placeholders is represented using '_' and it holds one or more parameters passed to a function
  val stockPricesEqualTo20 = stockPrices.filter(_ == 20)
  println(stockPricesEqualTo20)

  val stockTickers = List("GOOG", "PS", "TSLA")
  println(stockTickers.map(_.toLowerCase()))


  val sayHello = () => println("Hello function literal!")

  val multiplyBy100 = (x: Int) => x * 100

  val addTwoNumbers = (x: Int, y: Int) => x + y

  val addThreeNumbers = (x: Int, y: Int, z: Int) => x + y + z

  sayHello.apply()
  println(multiplyBy100.apply(7))
  println(addTwoNumbers.apply(7, 10))
  println(addThreeNumbers.apply(7, 10, 3))

  println("---------------------------------------")

  println("sayHello.isInstanceOf[Function0[_]]: " +
    sayHello.isInstanceOf[Function0[_]])
  println("multiplyBy100.isInstanceOf[Function1[_,_]]: " +
    multiplyBy100.isInstanceOf[Function1[_, _]])
  println("addTwoNumbers.isInstanceOf[Function2[_,_,_]]: " +
    addTwoNumbers.isInstanceOf[Function2[_, _, _]])
  println("addThreeNumbers.isInstanceOf[Function3[_,_,_,_]]: " +
    addThreeNumbers.isInstanceOf[Function3[_, _, _, _]])
}
