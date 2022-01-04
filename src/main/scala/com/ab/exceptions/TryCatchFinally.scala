package com.ab.exceptions

import java.io.{FileNotFoundException, IOException}
import scala.io.{BufferedSource, Source}

/**
 * @Arpit Bhardwaj
 *
 * Even though finally is executed the actual result value that we get when we run this method is from the try block
 * which is confusing. hence avoid returning results from finally block
 */
object TryCatchFinally extends App {

  def getLinesFromFile(fileName:String):List[String] = {
    var sourcePath:BufferedSource = null
    try {
      sourcePath = Source.fromFile(fileName)
      val lines = sourcePath.getLines().toList
      lines
    }
    catch {
      case e:FileNotFoundException => {
        println("File not found")
        List()
      }
      case e:IOException => {
        println("Input / Output Exception")
        List()
      }
    }finally {
      println("Running finally...")
      if (sourcePath != null){
        println("Closing file")
        sourcePath.close()
      }
      List("A","B")
    }
  }

  val lines= getLinesFromFile("src/main/resources/dividendStocks.csv")
  lines.foreach(println)
}
