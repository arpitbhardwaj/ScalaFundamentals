package com.ab.multithreading

/*
this.amount -= money is not ATOMIC

either use
    this.synchronized
    @volatile
 */
object RaceCondition extends App {

  class BankAccount(/*@volatile*/ var amount:Int){

    /*
    -= Involves 3 operation
      read old value
      compute result
      write new value
     */
    def withdraw(money:Int) = this.amount -= money

    def safeWithdraw(money:Int) = this.synchronized(
      this.amount -= money
    )
  }

  /*
  t1
    read amount 50000
    compute result 50000 - 3000 = 47000
  t2
    read amount 50000
    compute result 50000 - 4000 = 46000
  t1
    write amount 47000
  t2
    write amount 46000
   */
  def demoBankingProblem(): Unit = {
    (1 to 10000).foreach { _ =>
      val account = new BankAccount(50000)
      val t1 = new Thread(() => account.withdraw(3000))
      val t2 = new Thread(() => account.withdraw(4000))
      t1.start()
      t2.start()
      t1.join()
      t2.join()
      if (account.amount != 43000) {
        println(s"AHA! i just broken the bank: ${account.amount}")
      }
    }
  }

  demoBankingProblem()
}
