package com.ab.basic

/**
 * Functions and Methods are the terms used interchangeable but there are subtle differences
 *    Functions are objects; methods are not
 *    Methods can be stored in objects quite easily
 *    Methods are associated with a class; functions are not
 *
 * A method is a reusable code and is defined using def keyword.
 * It is a part of a class, it has name, signature some annotations and bytecode
 */
object Methods extends App {

  def volume:Int = 1000 //method takes no input argument and returns integer 1000
  def stockPrice: Int = 79
  def finalVal:Int = stockPrice*volume
  println("Final value is: " + finalVal)
  //println(s"Final value is: $finalVal")

  def volume1:Int = {
    println("Returning volume")
    100
  }
  def stockPrice1: Int = {
    println("Returning stock price")
    79
  }

  def finalVal1:Int = stockPrice1*volume1
  println("Final value is: " + finalVal1)

  println("---------------------")

  def finalVal1Again:Int = stockPrice1*volume1
  println(s"Final value is: $finalVal1Again")


  def data: Array[String] = {
    println("Array is initialized")
    Array("F","A","G")
  }
  println("Before accessing data")
  data.foreach(println)

  println("\n------------------")
  data.foreach(println)
}
