package com.ab.exceptions

/**
 * @Arpit Bhardwaj
 *
 * An option can be an instance of the some class or a none class
 */
object OptionSomeNone extends App {
  def convertToFloat1(str:String):Any = {
    try {
      str.toFloat
    }catch {
      case e:NumberFormatException => null
    }
  }
  val stockPrices = List("12.32","32.32","ee", "87.32")
  println(stockPrices.map(convertToFloat1))

  def convertToFloat2(str:String):Option[Float] = {
    try {
      Some(str.toFloat)
    }catch {
      case e:NumberFormatException => None
    }
  }
  val optionPrices = stockPrices.map(convertToFloat2)
  for (optionPrice <- optionPrices){
    optionPrice match {
      case Some(price) => println(price)
      case None => println("Skipping")
    }
  }

  for (optionPrice <- optionPrices){
    println(optionPrice.getOrElse(-1))
  }

  println(optionPrices.map(_.getOrElse(0)))

  //the flatmap combinator automatically just maps the valid values so the result can be used directly in mathematical expressions

  println(stockPrices.flatMap(convertToFloat2))
}
