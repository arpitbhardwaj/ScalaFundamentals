package com.ab.basic

object Conditional extends App {
  val num = 10

  val sym1: Unit = if (num > 10){
    println(">10")
  }else{
    println("<=10")
  }

  val sym2: String = if (num > 10){
    ">10"
  }else if(num == 10){
    "=10"
  }else{
    "<=10"
  }

  val sym3: String = if (num > 10) ">10" else if(num == 10)"=10"else "<=10"
}
