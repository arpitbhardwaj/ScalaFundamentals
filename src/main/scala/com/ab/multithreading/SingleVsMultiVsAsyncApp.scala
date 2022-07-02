package com.ab.multithreading

import scala.concurrent.Future



object SingleVsMultiVsAsyncApp extends App{

  case class User(id:Int,name:String,email:String)
  case class Contest(id:Int,title:String)

  def fetchContestInfo(contestId:Int):Contest = {
    println(s"[${Thread.currentThread()}] fetchContestInfo...");
    Thread.sleep(3000L)
    Contest(contestId,"Weekly Contest")
  }

  def fetchUserInfo(userId: Int):User = {
    println(s"[${Thread.currentThread()}] fetchUserInfo...");
    Thread.sleep(3000L)
    User(userId,"Arpit","arpit@gmail.com")
  }

  object SingleThreadApp {
    def registerParticipant(userId: Int, contestId:Int):Boolean = {
      println(s"[${Thread.currentThread()}] register participant...");
      val contestInfo = fetchContestInfo(contestId)
      val userInfo = fetchUserInfo(userId)
      println(s"[${Thread.currentThread()}] contest info $contestInfo user info $userInfo");
      true
    }
  }

  object MultiThreadApp {
    def registerParticipant(userId: Int, contestId:Int):Boolean = {
      println(s"[${Thread.currentThread()}] register participant...");
      var contestInfo:Contest = null
      var userInfo:User = null
      val t1 = threaded{
        contestInfo = fetchContestInfo(contestId)
      }
      val t2 = threaded {
        userInfo = fetchUserInfo(userId)
      }
      t1.join()
      t2.join()
      println(s"[${Thread.currentThread()}] contest info $contestInfo user info $userInfo");
      true
    }

    def threaded(exec: => Any):Thread = {
      val child = new Thread {
        override def run(): Unit = exec
      }
      child.start()
      child
    }
  }

  object AsyncApp {
    import scala.concurrent.ExecutionContext.Implicits.global

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
  }

  //client side
  //SingleThreadApp.registerParticipant(1,2)
  //MultiThreadApp.registerParticipant(1,2)
  AsyncApp.registerParticipant(1,2)
  println(s"[${Thread.currentThread()}] main method, performing other operations...");
  Thread.sleep(4000L)
}