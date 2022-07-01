package com.ab.multithreading

import java.util.concurrent.Executors

object MultiThreadDemo extends App {

  //manual threading
  val aThread1 = new Thread(new Runnable {
    override def run(): Unit = (1 to 5).foreach(_ => println("Hello I'm running in " + Thread.currentThread().getName))
  })

  val aThread2 = new Thread(() => (1 to 5).foreach(_ => println("Goodbye I'm running in " + Thread.currentThread().getName)))

  aThread1.start()
  aThread2.start()
  aThread1.join()
  aThread2.join()

  //executors
  val pool = Executors.newFixedThreadPool(10)
  pool.execute(() => println("Hello from thread pool"))

  pool.execute(() => {
    Thread.sleep(1000)
    println("Goodbye done after 1 sec")
  })

  pool.execute(() => {
    Thread.sleep(1000)
    println("almost done")
    Thread.sleep(1000)
    println("Goodbye done after 2 sec")
  })

  pool.shutdown() //pool stops accepting new task
  //pool.execute(() => println("should not appear")) //throws RejectedExecutionException in calling thread
  //pool.shutdownNow() //throws InterruptedException for waiting task
  println(pool.isShutdown)
}
