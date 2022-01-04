package com.ab.methods
import com.ab.oo.StockTickerRecord

/**
 *@Arpit Bhardwaj
 *
 * Positional argument values are assigned based on position in which arguments are specified
 * Named argument values are assigned based on names
 * For every parameter with default argument scala generates a synthetic method which computes the default
 * missing parameters are added to the argument list as calls to these synthetic method
 *
 *
 */
object Arguments extends App {
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

  def getStockDetails(date:String, ticker:String, priceType:String): (String,String,Float) ={
    var filterRecord = data.filter(_.date == date)
    filterRecord = filterRecord.filter(_.ticker == ticker)
    val record = filterRecord(0)
    val price = priceType match {
      case "open" => record.open
      case "close" => record.close
      case "high" => record.high
      case "low" => record.low
    }
    (record.date,record.ticker,price)
  }

  //positional arguments
  println(getStockDetails("12-06-2020","BNS","open"))
  //changing position may lead to exception
  //println(getStockDetails("BNS","open","12-06-2020"))
  //named arguments
  println(getStockDetails(ticker="BNS",priceType="open",date="12-06-2020"))
  //mix position arhuments with named arguments
  //rule is that postion argument should always come first
  println(getStockDetails("12-06-2020",priceType="open",ticker="BNS"))

  def getStockDetails2(date:String="12-06-2020", ticker:String, priceType:String): (String,String,Float) ={
    var filterRecord = data.filter(_.date == date)
    filterRecord = filterRecord.filter(_.ticker == ticker)
    val record = filterRecord(0)
    val price = priceType match {
      case "open" => record.open
      case "close" => record.close
      case "high" => record.high
      case "low" => record.low
    }
    (record.date,record.ticker,price)
  }
  //values for arguments after the one which has the default value has to be specified as the names arguments
  //println(getStockDetails2("BNS","open"))
  println(getStockDetails2(priceType="open",ticker="BNS"))
  println(getStockDetails2("11-06-2020",priceType="open",ticker="BNS"))

  //curried function
  def getStockDetails3(date:String="12-06-2020")(ticker:String="GOOG")(priceType:String): (String,String,Float) ={
    var filterRecord = data.filter(_.date == date)
    filterRecord = filterRecord.filter(_.ticker == ticker)
    val record = filterRecord(0)
    val price = priceType match {
      case "open" => record.open
      case "close" => record.close
      case "high" => record.high
      case "low" => record.low
    }
    (record.date,record.ticker,price)
  }

  println(getStockDetails3()()(priceType="open"))
  println(getStockDetails3()(ticker = "MSFT")(priceType="open"))

  //varargs (should not be the first arguments in list if there are others)
  def getStockDetails4(tickers:String*): Vector[(String,String,Float)] ={
    val filterRecord = data.filter(
      record => tickers.contains(record.ticker)
    )
    filterRecord.map(
      record => (record.date,record.ticker,record.close)
    )
  }
  val tickers = List("DB","TTM")
  //val records = getStockDetails4("DB","TTM")
  val records = getStockDetails4(tickers: _*)
  records.foreach(println)

  //nested methods
  def printStockData(ticker:String):Unit = {
    println("Date   | Ticker | Close")
    printRecords()
    def printRecords():Unit={
      val filterRecords = data.filter(_.ticker == ticker)
      for (row <- filterRecords){
        println(s"${row.date}   | ${row.ticker} | ${row.close}")
      }
      printSummary()
      def printSummary():Unit={
        printAverage()
        def printAverage():Unit={
          val avgClose = filterRecords.map(_.close).sum / filterRecords.size
          println("Avg Close: " + avgClose)
          printStandardDeviation()
          def printStandardDeviation():Unit={
            var x =0.0
            for (row <- filterRecords){
              x = x + Math.pow(row.close - avgClose, 2)
              val variance =  x / filterRecords.size
              println("Standard deviation close: " + Math.sqrt(variance))
            }
          }
        }
      }
    }
  }

  printStockData("BNS")
}
