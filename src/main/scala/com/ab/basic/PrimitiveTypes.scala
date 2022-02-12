package com.ab.basic

/**
 * A val modifier is used to define values that are immutable
 * A var modifier is used to define variables that are mutable
 * A def keyword is used to define methods which are evaluated each time they are invoked
 * A lazy val is immutable but is evaluated lazily i.e it is evaluated the first time it is referenced in code.
 *
 * Semicolons are necessary if you are writing multiple expression in same line else not which is bad style.
 *
 * prefers vals over vars
 * compiler auto infer types when omitted
 */
object PrimitiveTypes extends App{
  var aByte: Byte = 10
  var aShort: Short = 10
  var aChar: Char = 'c'
  var aInt: Int = 10
  var aFloat: Float = 10F
  var aLong: Long = 10L
  var aDouble: Double = 10
  var aUnit: Unit = ()
  var aBoolean: Boolean = false
  var str1: String = "said"

  var aAnyVal: AnyVal = 5
  //var aAnyRef: AnyRef = 5

  var arr1: Array[Int] = Array(1,2)
  var arr2: AnyRef = Array(1,2)
  println(arr1(1))
  //var arr3: AnyVal = Array(1,2)

  var aNull1: Null = null
  //var aNull2: Null = 1

  //Nothing is the return type for methods which never return normally.
  
  var num_1: Int = 10 //var stands for variable
  var num_2 = 20
  num_1 = -30

  var data = {
    println("Array is initialized")
    Array("F","A","G")
  }
  println("Before accessing data")
  data.foreach(println)
  println("------------------")
  data.foreach(println)
  data = {
    println("Array is initialized")
    Array("F","N","G")
  }
  println("------------------")
  data.foreach(println)

  val num_3: Int = 10 //val stands for values
  val num_4 = 20
  //num_3 = 40 //not allowed

  val data2 = {
    println("Array is initialized")
    Array("F","A","G")
  }
  println("Before accessing data")
  data2.foreach(println)
  println("\n------------------")
  data2.foreach(println)
  /*data2 = {
    println("Array is initialized")
    Array("F","N","G")
  }     //not allowed
  println("------------------")
  data2.foreach(print)*/
  data2(1) = "N"
  println("------------------")
  data2.foreach(println)

  lazy val num_5:Int = 40
  lazy val num_6 = 50

  lazy val data3 = {
    println("Array is initialized")
    Array("F","A","G")
  }
  println("Before accessing data")
  data3.foreach(println)

  println("------------------")
  data3.foreach(println)
}
