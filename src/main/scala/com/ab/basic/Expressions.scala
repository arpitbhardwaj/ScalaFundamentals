package com.ab.basic

/**
 * Expression
 *    Anything that evaluates to value is an expression
 *    Expressions are ubiquitous
 * Expression Blocks
 *    Expressions enclosed in {}
 *    the last expression in block is the return value
 * Methods
 *    A named reusable expression block
 */
object Expressions extends App {
  val a = 100
  val b = 10

  val sum = a+b
  val diff = a-b

  val str = "Hello"
  val upper = str.toUpperCase()

  println(str)
}
