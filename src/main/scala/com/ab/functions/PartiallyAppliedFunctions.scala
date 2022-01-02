package com.ab.functions

/**
 *
 */
object PartiallyAppliedFunctions extends App {
  val stockPrices = List(13.34, 23.45, 56.34, 12.67, 20.00, 35.43)

  def checkStockPricesGreaterThan20(price: Double): Boolean = price > 20

  //val stockPricesGreaterThan20 = stockPrices.filter(price => checkStockPricesGreaterThan20(price))
  //The below is a partially applied function where you do not supply all the arguments the function needs,
  //you specify either some or none of the arguments
  //val stockPricesGreaterThan20 = stockPrices.filter(checkStockPricesGreaterThan20 _)
  //simplifying the above
  //val stockPricesGreaterThan20 = stockPrices.filter(checkStockPricesGreaterThan20)

  val checkStockPricesGreaterThan20Func = checkStockPricesGreaterThan20 _
  val stockPricesGreaterThan20 = stockPrices.filter(checkStockPricesGreaterThan20Func)
  println(stockPricesGreaterThan20)

  def checkPriceInRange(price: Double, lower: Double, upper: Double): Boolean =
    price >= lower && price <= upper

  val checkStockPricesInRangeFunc = checkPriceInRange _
  val stockPricesInRange = stockPrices.filter(price => checkStockPricesInRangeFunc(price, 20, 40))
  println(stockPricesInRange)

  val checkStockPricesInRangeFunc2 = checkPriceInRange(_: Double, _: Double, 50)
  val stockPricesInRange2 = stockPrices.filter(price => checkStockPricesInRangeFunc2(price, 20))
  println(stockPricesInRange2)

  val checkStockPricesInRangeFunc3 = checkPriceInRange(_: Double, 30, 50)
  val stockPricesInRange3 = stockPrices.filter(checkStockPricesInRangeFunc3)
  println(stockPricesInRange3)
}
