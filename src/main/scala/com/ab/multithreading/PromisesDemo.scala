package com.ab.multithreading

import scala.concurrent.Promise
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}
/**
 * Promises are like
 *    controller over future
 *    they have internal future which they controls
 */
object PromisesDemo extends App {
  val aPromise = Promise[Int]()
  val aFuture = aPromise.future

  //thread 1: [consumer]
  aFuture.onComplete {
    case Failure(exception) => exception.printStackTrace()
    case Success(value) => println(s"[consumer] I have received $value")
  }

  //thread 2: [producer]
  val producer = new Thread(() => {
    println("[producer] crunching numbers...")
    Thread.sleep(1000)
    aPromise.success(42)
    //aPromise.failure(ex:Throwable => )
    println("[producer] done")
  })

  producer.start()
  Thread.sleep(2000)
}
