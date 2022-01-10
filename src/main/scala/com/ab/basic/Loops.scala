package com.ab.basic

object Loops extends App {
  val amounts = Array(10,20,30)
  val currencies = Array("USD","NZD")
  var sum = 0

  //generator go in for loop braces
  //amounts is called generator exp
  //amount <- amounts os called generator
  for (amount <- amounts){
    sum += amount
  }
  println(sum)

  //in order to use for loop as an expression you need to use an yield keyword
  val result1:Array[Int] = for (amount <- amounts) yield{
    amount
  }
  println(result1.toList)

  //more than one generator in single for loop
  val result2:Array[String] = for{
    amount <- amounts
    currency <- currencies
    if amount > 10 && amount < 30 //conditional known as guards (purpose is to filter)
  } yield amount + " " + currency
  println(result2.toList)

  //foreach doesn't yield a value, means its an statement
  amounts.foreach(amount => println(amount*1000))
  List(1,2,3).foreach(num => println(num*20))

  //
  var i1 =1
  while (i1<10){
    print(i1)
    i1+=1
  }
  println()

  var i2 = 10
  do {
    print(i2)
    i2-=1
  }while(i2>0)
}
