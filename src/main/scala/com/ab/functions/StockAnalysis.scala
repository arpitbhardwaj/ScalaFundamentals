package com.ab.functions

import com.ab.oo.{StockRecord, StockTickerRecord}

object StockAnalysis extends App {
  val readFinanceData = () => {
    val source = io.Source.fromFile("src/main/resources/GOOG.csv")
    //for expression
    for{
      line <- source.getLines().drop(1).toVector
      cols = line.split(",").map(_.trim)
    }yield StockRecord(
      cols(0),
      cols(1).toFloat,
      cols(2).toFloat,
      cols(3).toFloat,
      cols(4).toFloat,
      cols(5).toFloat,
      cols(6).toDouble,
    )
  }

  var data = readFinanceData()

  val getTotalNoOfRows = () => data.size
  val getAvgCloseVal = () => data.map(_.close).sum / data.size
  val getMinCloseVal = () => data.map(_.close).min
  val getMaxCloseVal = () => data.map(_.close).max
  val getCloseValOnADate = (date:String) => {
    val filteredClose = data.filter(_.date == date)
    filteredClose.map(_.close).head
  }

  println(s"Dataset size: ${getTotalNoOfRows()}")
  println(s"Average close: ${getAvgCloseVal()}")
  println(s"Min close: ${getMinCloseVal()}")
  println(s"Max close: ${getMaxCloseVal()}")

  val date = "2020-01-03"
  println(s"Close value on $date: ${getCloseValOnADate(date)}")


  val priceDelta = (_:Double) - (_:Double)
  //higher order function
  val getDailyDelta = (
    open:Double,
    close:Double,
    delta:(Double,Double) => Double
  ) => delta(open,close)
  val record = data.filter(_.date == "2020-01-03")
  //println(getDailyDelta(record(0).open,record(0).close,priceDelta))
  //concise way
  println(getDailyDelta(record(0).open,record(0).close,_-_))

  val readFinanceData2 = () => {
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

  var data2 = readFinanceData2()

  //partial functions
  val printStockRecords: PartialFunction[String,Unit] = new PartialFunction[String,Unit] {
    var allowedtickers = List("MSFT","TTM")
    def apply(ticker:String):Unit={
      for (lines <- data2.filter(_.ticker==ticker)){
        println(s"Date: ${lines.date} Ticker: ${lines.ticker} Close: ${lines.close}")
      }
    }
    override def isDefinedAt(ticker: String): Boolean = allowedtickers.contains(ticker)
  }

  if (printStockRecords.isDefinedAt("MSFT")) printStockRecords("MSFT")

  //advantages of using partial function is that
  //there are few libraries in scala that invokes isDefined at automatically before invoking the function
  //if the library doesn't invoke isDefinedAt then it may run into exception

  //List("DB","TESLA") map{printStockRecords}
  List("DB","TESLA") collect {printStockRecords}

  val printTechStockRecords: PartialFunction[String,Unit] = new PartialFunction[String,Unit] {

    def apply(ticker:String):Unit={
      for (lines <- data2.filter(_.ticker==ticker)){
        println(s"Date: ${lines.date} Ticker: ${lines.ticker} Close: ${lines.close}")
      }
    }
    override def isDefinedAt(ticker: String): Boolean = ticker == "GOOG" || ticker == "MSFT"
  }

  val printAutoStockRecords: PartialFunction[String,Unit] = new PartialFunction[String,Unit] {
    var allowedtickers = List("MSFT","TTM")
    def apply(ticker:String):Unit={
      for (lines <- data2.filter(_.ticker==ticker)){
        println(s"Date: ${lines.date} Ticker: ${lines.ticker} Close: ${lines.close}")
      }
    }
    override def isDefinedAt(ticker: String): Boolean = ticker == "TM" || ticker == "TTM"
  }

  val printTechOrAutoStocks = printTechStockRecords orElse printAutoStockRecords
  printTechOrAutoStocks("TM")
  printTechOrAutoStocks("MSFT")
}
