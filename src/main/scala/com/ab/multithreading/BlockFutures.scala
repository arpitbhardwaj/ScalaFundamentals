package com.ab.multithreading

import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.DurationInt

object BlockFutures extends App {

  case class User(name:String)
  case class Transaction(sender:String, receiver:String, amount:Double, status:String)

  object BankingApp{

    def fetchUser(name:String): Future[User] = Future{
      Thread.sleep(500)
      User(name)
    }

    def createTransaction(user:User, merchant:String, amount:Double): Future[Transaction] =Future {
      Thread.sleep(1000)
      Transaction(user.name, merchant, amount, "SUCCESS")
    }

    def purchase(buyerName: String, item:String, sellerName:String, cost:Double): String ={
      val transactionStatusFuture = for {
        user <- fetchUser(buyerName)
        transaction <- createTransaction(user, sellerName, cost)
      } yield transaction.status

      Await.result(transactionStatusFuture, 2.seconds) //. works via implicits conversion -> pimp my library
     //Await.ready(transactionStatusFuture, 2.seconds) //returns the same future as ready
    }
  }

  //client side
  println(BankingApp.purchase("Arpit", "mac", "apple store", 50000))
}
