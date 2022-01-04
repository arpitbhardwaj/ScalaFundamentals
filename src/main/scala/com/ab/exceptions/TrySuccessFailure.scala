package com.ab.exceptions

import com.ab.exceptions.TryCatchFinally.getLinesFromFile
import com.ab.oo.DividendRecord

import java.io.{BufferedReader, FileReader}
import scala.annotation.tailrec
import scala.io.{BufferedSource, Source, StdIn}
import scala.util.{Failure, Success, Try, Using}

/**
 * @Arpit Bhardwaj
 *
 * Using:
 *    utility for performing automatic resource management
 *    after the operation is complete it releases resource in reverse order of creation
 */
object TrySuccessFailure extends App {
  def getLinesFromFile(fileName:String):Try[BufferedSource] = {
    Try(Source.fromFile(fileName))
  }

  val trySourcePath:Try[BufferedSource] = getLinesFromFile("src/main/resources/dividendStockss.csv")

  trySourcePath match {
    case Success(sourecpath) => sourecpath.getLines().toList.foreach(println)
    case Failure(exception) => println(exception.getMessage)
  }

  var lines: Try[List[String]] =
    Using(new BufferedReader(new FileReader("src/main/resources/dividendStocks.csv"))){
      reader => Iterator.continually(reader.readLine()).takeWhile(_ != null).toList
    }

  lines match {
    case Failure(exception) => println(exception.getMessage)
    case Success(value) => value.foreach(println)
  }

  def readFinanceData(filePath:String):Seq[DividendRecord] = {
    val source = io.Source.fromFile(filePath)
    //for expression
    for{
      line <- source.getLines().drop(1).toVector
      cols = line.split(",").map(_.trim)
    }yield DividendRecord(
      cols(0),
      cols(1),
      cols(2).toFloat,
      cols(3).toFloat,
    )
  }

  var data = readFinanceData("src/main/resources/dividendStocks.csv")

  @tailrec
  def calculateDividendYield():Try[Double] = {
    val ticker = StdIn.readLine("Enter Ticker: ")
    val currPrice = Try(StdIn.readLine("Enter current price: ").toDouble)

    val faceValue:Try[Double] = Try(data.filter(_.ticker == ticker).map(_.face_value).head.toDouble)
    val dividendRate:Try[Double] = Try(data.filter(_.ticker == ticker).map(_.dividend).head * 0.01)

    val dividendPerShare:Try[Double] = faceValue.flatMap(fv => dividendRate.map(divRate => fv * divRate))
    val dividendYield:Try[Double] = dividendPerShare.flatMap(dps => currPrice.map(cp => dps/cp *100))

    dividendYield match {
      case Failure(exception) =>
        println(exception.getMessage)
        println("An error occured along the way, Try again!")
        calculateDividendYield()
      case Success(divYield) =>
        println(s"Dividen yield of $ticker is: $divYield")
        Success(divYield)
    }
  }

  calculateDividendYield()
}
