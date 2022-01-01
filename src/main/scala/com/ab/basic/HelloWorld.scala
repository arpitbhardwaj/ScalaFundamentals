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
 * Ease of Parallelism
 *    pure functions acting on immutable data
 */
object HelloWorld /*extends App */{

  def main(args : Array[String]):Unit = {
    println("Hello World");
  }
  //println("Hello World");
}
