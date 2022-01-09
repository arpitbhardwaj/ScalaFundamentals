package com.ab.functions

/**
 * HOF or methods either takes other function as input or returns other function
 */
object HigherOrderFunctions extends App{

  def square(n:Int):Int = n*n         //Int => Int
  def cube(n:Int):Int = n*n*n         //Int => Int
  def sum(a:Int,b:Int):Int = a+b      //(Int, Int) => Int
  def length(s:String):Int = s.length

  def transform(f:Int =>Int,numbers:Int*) = numbers.map(f)
  transform(square,1,2,3,4)
  transform(cube, 1,2,3,4)

  //passing function literal
  transform((n:Int) => n*n, 1,2,3)
  transform(n => n*n*n, 1,2,3)
}

