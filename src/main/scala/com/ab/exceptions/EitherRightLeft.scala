package com.ab.exceptions

/**
 * @Arpit Bhardwaj
 *
 * Either-Right-Left is exactly analogous to Option-Some-None except
 *    that with this you can pass back info on why a certain computation failed
 */
object EitherRightLeft extends App {
  def convertToFloat1(str:String):Either[String,Float] = {
    try {
      Right(str.toFloat)
    }catch {
      case e:NumberFormatException => Left("There was an error parsing the float: " + str)
    }
  }
  val stockPrices = List("12.32","32.32","ee", "87.32")
  println(stockPrices.map(convertToFloat1))
}
