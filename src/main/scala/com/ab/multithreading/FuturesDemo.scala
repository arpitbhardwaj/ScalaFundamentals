package com.ab.multithreading

import scala.concurrent.Future
import scala.util.{Failure, Success}
import scala.concurrent.ExecutionContext.Implicits.global
object FuturesDemo extends App {

  val aFuture = Future{ //Future apply method
    Thread.sleep(2000)
    42
  }

  println(aFuture.value) //Option[Try[Int]]

  println("Waiting for the future to complete")
  /*aFuture.onComplete(t => t match {
    case Success(42) => println("i found the answer")
    case Failure(_) => println("Something bad happened")
  })*/

  //shorthand of above match as its a partial function
  aFuture.onComplete{
    case Success(i) => println(s"i found the answer: $i")
    case Failure(exception) => println(s"Something bad happened: $exception")
  }

  Thread.sleep(3000) //to wait for future thread to complete //this can be avoid by using blocks(await,ready) on futures
}
