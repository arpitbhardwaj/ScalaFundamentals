package com.ab.basic

/**
 *
 * Scala is both object oriented and functional
 *
 * Functional Languages and Distributed Systems Connections
 * Functions as First Class Citizens
 *    Functions can be passed and returned from functions
 * Function Composition
 *    Chains of Function calling each other
 * Immutable Data
 *    Prevent inadvertently introduced side effects
 * Pure Functions
 *    no side effects: same output for same input
 *    Do not read values from the external world
 *    Do not modify values in the external world
 * Ease of Parallelism
 *    pure functions acting on immutable data
 *
 * Scala Constructs for functional programming
 *    Immutable Data and Pure Functions
 *    Favors Expressions over statements
 *      Expressions are units of code that return a value
 *      Statements are units of code that do not return value
 *    Functions as reusable code blocks
 */
object HelloWorld /*extends App */{

  def main(args : Array[String]):Unit = {
    println("Hello World");
  }
  //println("Hello World");
}
