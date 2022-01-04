package com.ab.multithreading

object SingleThreadApp {
  def main(args : Array[String]):Unit = {
    registerParticipant(1,2)
    println(s"[${Thread.currentThread()}] main method, performing other operations...");
  }

  def registerParticipant(userId: Int, contestId:Int):Boolean = {
    println(s"[${Thread.currentThread()}] register participant...");
    val contestInfo = fetchContestInfo(contestId)
    val userInfo = fetchUserInfo(userId)
    println(s"[${Thread.currentThread()}] contest info $contestInfo user info $userInfo");
    true
  }

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

  case class User(id:Int,name:String,email:String)
  case class Contest(id:Int,title:String)
}
