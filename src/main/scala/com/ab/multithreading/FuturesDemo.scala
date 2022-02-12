package com.ab.multithreading

import scala.concurrent.Future
import scala.util.{Failure, Success}

object FuturesDemo extends App {

  import scala.concurrent.ExecutionContext.Implicits.global
  val future = Future{
    //long computation
    42
  }

  //callbacks
  future.onComplete{
    case Success(42) => println("i found the answer")
    case Failure(_) => println("Something bad happened")
  }

  val aProcessedFuture = future.map(_ + 1) //future with 43
  val aFlatFuture = future.flatMap{
    value => Future(value+2)
  } //future with 44
  val aFilteredFuture = future.filter(_ % 2 == 0) //future with 42

  //for comprehensions
  val aggregateResult = for {
    r1 <- future
    r2 <- aFilteredFuture
  } yield println(r1 + r2) //42+42 = 84

  //andThen
  //recover,recoverWith
  //promises
}
