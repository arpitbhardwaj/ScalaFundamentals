package com.ab.basic

/**
 * Statements/Instruction (Instruction to computer to do something)
 *    do not evaluate to any value / or we can say an expression returning unit value
 *    contain side effects
 *    println(), while, reassigning
 *
 * Expression
 *    Anything that evaluates to value is an expression
 *    Do not contain side effects
 *    Expressions are ubiquitous
 *
 * Code Blocks
 *    is an expression
 *    the last expression in block is the return value
 *    enclosed in {}
 *
 * Everything in scala is expression and except few and also it enforces the programmer to write expressions
 */
object Expressions extends App {
  val a = 100
  val b = 10

  //expressions
  val sum = a+b
  val diff = a-b

  //1==2 is an expression returns boolean
  println(1 == 2)
  println(1 + 3)
  //if expression
  val aCondition = true
  val aConditionVal = if(aCondition) 5 else 3
  println(aConditionVal)
  println(if(aCondition) 5 else 3)

  //NEVER WRITE THIS AGAIN
  var i = 0
  while(i<10){
    println(i)
    i+=1
  }
  //or it is similar to (on hover you can see whi is of type Unit)
  val whi = while(i<10){
    println(i)
    i+=1
  }

  //code block
  val aCodeBlock = {
    val y =2
    val z = y+1
    if(z >2) "hello" else "goodbye"
  }

}
