package com.ab.methods

import com.ab.oo.StockTickerRecord

/**
 * @Arpit Bhardwaj
 *
 * A Curried function has multiple argument lists - this is equivalent to multiple function invocatio
 * back to back.
 *
 * We call it curried function though it is applied on methods
 * The placeholder notation allowed us to use the curried function as o partially applied function
 */
object Currying extends App {
  val readFinanceData = () => {
    val source = io.Source.fromFile("src/main/resources/stockMarketData.csv")
    //for expression
    for{
      line <- source.getLines().drop(1).toVector
      cols = line.split(",").map(_.trim)
    }yield StockTickerRecord(
      cols(0),
      cols(1).toFloat,
      cols(2).toFloat,
      cols(3).toFloat,
      cols(4).toFloat,
      cols(5)
    )
  }

  var data = readFinanceData()

  def getPrice1(date:String, ticker:String, priceType:String): Float ={
    val recordByDate = data.filter(_.date == date)
    val recordByTicker = recordByDate.filter(_.ticker == ticker)
    val price = priceType match {
      case "open" => recordByTicker(0).open
      case "close" => recordByTicker(0).close
      case "high" => recordByTicker(0).high
      case "low" => recordByTicker(0).low
    }
    price
  }
  //curried function
  def getPrice2(date:String, ticker:String)(priceType:String): Float ={
    val recordByDate = data.filter(_.date == date)
    val recordByTicker = recordByDate.filter(_.ticker == ticker)
    val price = priceType match {
      case "open" => recordByTicker(0).open
      case "close" => recordByTicker(0).close
      case "high" => recordByTicker(0).high
      case "low" => recordByTicker(0).low
    }
    price
  }

  println(getPrice1("12-06-2020","GOOG", "open"))
  println(getPrice2("12-06-2020","GOOG")("open"))

  //placeholder notation
  val getPriceForStock = getPrice2("12-06-2020","GOOG") _

  println("Open: " + getPriceForStock("open"))
  println("Close: " + getPriceForStock("close"))

}
