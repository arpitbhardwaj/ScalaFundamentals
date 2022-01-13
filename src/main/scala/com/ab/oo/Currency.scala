package com.ab.oo


/**
 * @Arpit Bhardwaj
 *
 * Companion Object
 *  same name as the class
 *  should be in the same file where the class exists
 *  contain methods and variables which does not depend on the state of object
 *  apply method in companion object help you to create instances without using new keyword
 */

//companion object (singleton object)
object Currency{
  private val conversionMap: Map[String,Double] = Map("CAD"->1/1.30,"NZD"->1/1.50)
  /*def apply(code:String, amount:Double) = new Currency(code,amount)
  def createUSD(amount:Double) = new Currency("USD",amount)
  def createNZD(amount:Double) = new Currency("NZD",amount)
  def createCAD(amount:Double) = new Currency("CAD",amount)*/

  private def getCostInUSD(code:String,amount:Double):Double = {
    code match {
      case "USD" => amount
      case "NZD" => amount * conversionMap("NZD")
      case "CAD" => amount * conversionMap("CAD")
      case _ => throw new IllegalArgumentException(s"No conversion available for ${code}")
    }
  }

  def apply(code:String, amount:Double) = new Currency(code,amount,getCostInUSD(code,amount))

  def createUSD(amount:Double):Currency = Currency("USD",amount) //new keyword not required as it uses apply
  def createNZD(amount:Double):Currency = Currency("NZD",amount)
  def createCAD(amount:Double):Currency = Currency("CAD",amount)

  //implicit tells scala compiler to find the opportunities where string is provided and currency is expected
  //and use this method behind the scenes
  implicit def string2Currency(money:String):Currency = {
    val Array(code:String,value:String) =  money.split("\\s")
    val requestedAmount:Double = value.toDouble
    Currency(code,requestedAmount)
  }
}

class Currency (code:String, amount:Double,inUSD:Double){
  //conversionMap does not depend on any internal state of the object hence this can live somewhere else as well
  //in java static methods/variables serve the same purpose and scala uses companion object for the same
  //private val conversionMap: Map[String,Double] = Map("CAD"->1/1.30,"NZD"->1/1.50)

  private val _code:String = code
  private val _amount:Double = amount
  private val _inUSD:Double = inUSD

  /*private def getCostInUSD:Double = {
    _code match {
      case "USD" => _amount
      case "NZD" => _amount * conversionMap("NZD")
      case "CAD" => _amount * conversionMap("CAD")
      case _ => throw new IllegalArgumentException(s"No conversion available for ${_code}")
    }
  }*/
  override def toString = s"Currency(${_code}, ${_amount}, ${_inUSD})"
}

object CurrencyRunner extends App{
  val usd1 = Currency("USD",100.12)
  println(usd1)

  val nzd1 = Currency("NZD",100.12)
  println(nzd1)

  val cad1 = Currency("CAD",100.12)
  println(cad1)

  import Currency._ //_helps you import everything in companion object
  val usd2 = createUSD(26)
  println(usd2)

  val nzd2:Currency = "NZD 25" //compiler calls string2Currency under the hood
  println(nzd2)
}
