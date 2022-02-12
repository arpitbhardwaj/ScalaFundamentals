package com.ab.multithreading

object MultiThreadDemo extends App {

  val aThread1 = new Thread(new Runnable {
    override def run(): Unit = println("I'm running in " + Thread.currentThread().getName)
  })

  val aThread2 = new Thread(() => println("I'm running in " + Thread.currentThread().getName))

  aThread1.start()
  aThread2.start()
  aThread1.join()
  aThread2.join()

  //different runs produce different result

  class BankAccount(@volatile private var amount:Int){
    def withdraw(money:Int) = this.amount -= money
    def safeWithdraw(money:Int) = this.synchronized(
      this.amount -= money
    )
  }

  /*
  BA(10000)

  T1 -> Withdraw 1000
  T2 -> Withdraw 2000

  T1 -> this.amount = 10000 - ... //preempted by the OS
  T2 -> this.amount = 10000 - 2000 = 8000
  T1 -> this.amount = 10000 - 1000 = 9000

  result = 1000

  this.amount -= money is not ATOMIC

  either use this.synchronized
  or @volatile
   */
}
