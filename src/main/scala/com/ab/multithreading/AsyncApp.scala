package com.ab.multithreading

import scala.concurrent.Future

object AsyncApp {
  import scala.concurrent.ExecutionContext.Implicits.global
  def main(args : Array[String]):Unit = {
    registerParticipant(1,2)
    println(s"[${Thread.currentThread()}] main method, performing other operations...");
    Thread.sleep(4000L)
  }

  def registerParticipant(userId: Int, contestId:Int):Future[Boolean] = Future{
    println(s"[${Thread.currentThread()}] register participant...");
    var contestInfo = fetchContestInfo(contestId)
    var userInfo = fetchUserInfo(userId)

    for {
      cInfo <- contestInfo
      uInfo <- userInfo
    }yield {
      println(s"[${Thread.currentThread()}] contest info $cInfo user info $uInfo");
    }
    true
  }

  def fetchContestInfo(contestId:Int):Future[Contest] = Future{
    println(s"[${Thread.currentThread()}] fetchContestInfo...");
    Thread.sleep(3000L)
    Contest(contestId,"Weekly Contest")
  }

  def fetchUserInfo(userId: Int):Future[User] = Future{
    println(s"[${Thread.currentThread()}] fetchUserInfo...");
    Thread.sleep(3000L)
    User(userId,"Arpit","arpit@gmail.com")
  }

  case class User(id:Int,name:String,email:String)
  case class Contest(id:Int,title:String)
}
