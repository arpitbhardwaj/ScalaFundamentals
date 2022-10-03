package com.ab.basic

import scala.util.Random

object OptionsDemo extends App {
  val myFirstOption:Option[Int] = Some(4)
  val noOption:Option[Int] = None

  //Work with unsafe apis
  def unsafeMethod():String = null
  //val result = Some(unsafeMethod()) //WRONG
  val result = Option(unsafeMethod())
  println(result)

  //chained method
  def backupMethod(): String = "A Valid Result"
  val chainedResult = Option(unsafeMethod()).orElse(Option(backupMethod()))

  //Design unsafe apis
  def betterUnsafeMethod(): Option[String] = None
  def betterBackupMethod(): Option[String] = Some("A Valid Result")
  val betterChainedResult = betterUnsafeMethod() orElse betterBackupMethod()

  println(myFirstOption.isEmpty)
  println(myFirstOption.get) //DO NOT USE

  println(myFirstOption.map(_ * 2))
  println(myFirstOption.filter(x => x > 10))
  println(myFirstOption.flatMap(x => Option(x * 10)))


  val config:Map[String,String] = Map(
    "host" -> "122:23:43:2",
    "port" -> "80"
  )

  class Connection{
    def  connect = "Connected"
  }

  object Connection {
    val random = new Random(System.nanoTime())
    def  apply(host:String, port:String): Option[Connection] =
      if (random.nextBoolean()) Some(new Connection)
      else None
  }

  val host = config.get("host")
  val port = config.get("port")
  val connection = host.flatMap(h => port.flatMap(p => Connection.apply(h,p)))
  val connectionStatus = connection.map(c => c.connect)
  println(connectionStatus)
  connectionStatus.foreach(println)

  //chained method
  config.get("host")
    .flatMap(h => config.get("port")
    .flatMap(p => Connection.apply(h,p))
      .map(c => c.connect))
      .foreach(println)

  val forConnectionStatus = for {
    h <- config.get("host")
    p <- config.get("port")
    c <- Connection(h,p)
  } yield c.connect

  forConnectionStatus.foreach(println)

}
