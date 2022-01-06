package com.ab.basic

object Loops extends App {
  val amounts = Array(10,20,30)
  val currencies = Array("USD","NZD")
  var sum = 0
  for (amount <- amounts){
    sum += amount
  }
  println(sum)
  sum = 0

  val result1:Array[Int] = for (amount <- amounts) yield{
    amount
  }

  val result2:Array[String] = for{
    amount <- amounts
    currency <- currencies
    if amount >10
  } yield currency+" " + amount

  amounts.foreach(amount => println(amount*1000))
  List(1,2,3).foreach(num=>println(num*20))

  var i =1;
  while (i<10){
    println(i)
    i+=1
  }

  var i2 = 10;
  do {
    println(i2)
    i2-=1
  }while(i2>0)
}
