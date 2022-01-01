package com.ab.basic

/**
 *
 * A val modifier is used to define variables that are immutable
 * A var modifier is used to define variables that are mutable
 * A def keyword is used to define methods which are evaluated each time they are invoked
 *
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

  var aAnyVal: AnyVal = 5;
  //var aAnyRef: AnyRef = 5;

  var arr1: Array[Int] = Array(1,2);
  var arr2: AnyRef = Array(1,2);
  //var arr3: AnyVal = Array(1,2);

  var aNull1: Null = null;
  //var aNull2: Null = 1;


  var num_1: Int = 10;
  var num_2 = 20;

  num_1 = -30;

  val num_3: Int = 10;
  val num_4 = 20;

  //num_3 = 40; //not allowed

  var str1: String = "said";
}
