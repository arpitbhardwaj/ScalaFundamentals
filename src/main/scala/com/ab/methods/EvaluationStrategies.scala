package com.ab.methods

import com.ab.oo.StockTickerRecord

/**
 *@Arpit Bhardwaj
 *
 * Call by value
 *    is the strict evaluation strategy that scala uses by default
 *    the expressions corresponding to input arguments are evaluated before the function body is executed
 *
 * Call by name
 *    is the non strict evaluation strategy that scala
 *    the expressions corresponding to input arguments are evaluated only when the program needs it
 */
object EvaluationStrategies extends App {

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

  //call by value
  def momentumStrategy1(record: StockTickerRecord,percentDelta:Float):Unit = {
    println("Calculating percentage move")

    val percentMove = ((record.close - record.open) / record.open) * 100

    if (percentMove > percentDelta){
      println(s"Recommend Buy: ${record.ticker}")
    }else if (percentMove < percentDelta){
      println(s"Recommend Sell: ${record.ticker}")
    }else{
      println(s"No call on: ${record.ticker}")
    }
  }

  //call by name
  //useful when computing the input argument is expensive and you may not reference that input argument to invoke a function
  //wasteful when you have several references to the same input argument
  def momentumStrategy2(record: => StockTickerRecord,percentDelta:Float):Unit = {
    println("Calculating percentage move")

    //to avoid call by name below aprroach can also be used
    lazy val stockRecord = record

    val percentMove = ((stockRecord.close - stockRecord.open) / stockRecord.open) * 100

    if (percentMove > percentDelta){
      println(s"Recommend Buy: ${stockRecord.ticker}")
    }else if (percentMove < percentDelta){
      println(s"Recommend Sell: ${stockRecord.ticker}")
    }else{
      println(s"No call on: ${stockRecord.ticker}")
    }
  }

  def getRecord(ticker:String):StockTickerRecord = {
    println(s"Accesing records for ${ticker}")
    val filterRecord = data.filter(_.ticker == ticker)
    assert(filterRecord.length >= 1)
    filterRecord(0)
  }

  println("-------------------")
  momentumStrategy1(getRecord("MSFT"),1)

  println("-------------------")
  momentumStrategy2(getRecord("TM"),1)

}
