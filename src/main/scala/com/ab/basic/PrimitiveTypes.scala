package com.ab.basic

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
}
