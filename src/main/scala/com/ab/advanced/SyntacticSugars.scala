package com.ab.advanced

import scala.util.Try

object SyntacticSugars extends App {

  //syntax sugar #1: Single argument method
  def singleArgMethod(arg:Int): String = s"$arg arguments"

  val str1 = singleArgMethod(2)
  //or
  val str2 = singleArgMethod {
    //write some code
    6
  }

  val aTryInstance = Try {
    throw new Exception
  }

  List(1,2,3).map{ x =>
    x+1
  }

  //syntax sugar #2: Single abstract method (lambdas in java)
  trait Action1{
    def act(x:Int):Int
  }
  val aTraitInstance:Action1 = x => x+1

  abstract class Action2{
    def implemented: Int = 23
    def act(x:Int):Int
  }
  val aAbstractInstance:Action2 = x => x+1

  //syntax sugar #3: the :: and #:: methods are special

  val prependedList = 2 :: List(3,4)
  //as per scala spec the last char decides associativity of method
  //List(3,4).::(2)

  class MyStream[T] {
    def -->:(value: T): MyStream[T] = this
  }

  val myStream = 1 -->: 2 -->: 3 -->: new MyStream[Int]

  //syntax sugar #4: multi word method naming


  //syntax sugar #5:

  //syntax sugar #1:

  //syntax sugar #1:

  //syntax sugar #1:
}
