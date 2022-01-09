package com.ab.basic

/**
 *
 * Statements
 *    may contain side effects
 * Expression
 *    Anything that evaluates to value is an expression
 *    Do not contain side effects
 *    Expressions are ubiquitous
 * Expression Blocks
 *    Expressions enclosed in {}
 *    the last expression in block is the return value
 * Methods
 *    A named reusable expression block
 */
object Expressions extends App {
  //expressions
  val a = 100
  val b = 10

  val sum = a+b
  val diff = a-b

  val str = "Hello"
  val upper = str.toUpperCase()

  //statement returns Unit
  println(str)
}
