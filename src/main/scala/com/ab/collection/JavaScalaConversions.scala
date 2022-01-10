package com.ab.collection
import java.util
import scala.jdk.CollectionConverters._
/**
 *
 * JavaConversions - deprecated(2.12)
 * JavaConverters - deprecated(2.13)
 * CollectionConverters
 *
 */
object JavaScalaConversions extends App {
  val elementsJava = new util.ArrayList[Int]()
  val elementsScala = elementsJava.asScala

  for(element <- elementsScala){
    println(element)
  }

  val elementsJava2 = elementsScala.asJava
}
