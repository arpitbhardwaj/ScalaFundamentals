package com.ab.methods
import com.ab.oo.StockRecord

import scala.annotation.tailrec

/**
 * @Arpit Bhardwaj
 *
 * Parameterless Method
 *    takes in no input arguments so we eliminate the parenthesis
 *    used when an attribute could have been implemented either as a field or a method.
 *    based on the uniform access principle client code should not be affected by this decision
 *
 * Empty-parem method
 *    is recommended when there are no parameters and method accesses but does not change mutable state
 *    i.e by reading fields of the contained object
 *
 * Tail recursion:
 *    A new stack frame will not be built for each recursive call - all calls will executed in a single stack frame
 *    by simply updating the input arguments to the recursive call
 *    In order to apply the tail recursion optimization the recursive has to be the last call in method
 */
object Parameters extends App {
  //empty parem method
  def readFinanceData():Vector[StockRecord] = {
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

  //parameter less methods
  def getTotalNoOfRows:Int = data.size
  def getAvgCloseVal:Float = data.map(_.close).sum / data.size
  //parameterless methods can be changed to fields without any side effects
  val getMinCloseVal:Float = data.map(_.close).min
  val getMaxCloseVal:Float = data.map(_.close).max

  println(s"Dataset size: ${getTotalNoOfRows}")
  println(s"Average close: ${getAvgCloseVal}")
  println(s"Min close: ${getMinCloseVal}")
  println(s"Max close: ${getMaxCloseVal}")

  //iterative
  def rollingAvgIterative(numDays:Int):Unit = {
    var records = readFinanceData();
    while (records.length >= numDays){
      val avgClose = records.map(_.close).take(numDays).sum / numDays
      println(s"Rolling avg close for $numDays days date ${records.head.date}: $avgClose")
      records = records.drop(1)
    }
    println("Execution completed")
  }

  rollingAvgIterative(7)

  //tail recursion
  @tailrec
  def rollingAvgRecursive(records: Vector[StockRecord], numDays:Int):Unit = {
    if (records.length < numDays){
      //throw new Exception("Error so we can see the exception trace")
      println("Execution completed")
    }else{
      val avgClose = records.map(_.close).take(numDays).sum / numDays
      println(s"Rolling avg close for $numDays days date ${records.head.date}: $avgClose")
      val updatedRec = records.drop(1)
      rollingAvgRecursive(updatedRec,numDays)
      //println("random print to disturb tail recursion")
    }
  }

  rollingAvgRecursive(readFinanceData(),30)
}
