package com.ab.oo

import java.time.LocalDateTime

/**
 * Case classes are used to model immutable data and can be constructed without the new keyword
 *
 *
 */
//to limit the possibility in pattern matching
sealed trait Kind //marking sealed tells compiler to look for different kind only in this file
case object Debit extends Kind
case object Credit extends Kind
case object Refund extends Kind

case class CaseTransaction (name:String, amount:Double, kind:Kind, when:LocalDateTime)

object CaseTransactionRunner extends App{
  println(Debit.toString)
  val now:LocalDateTime = LocalDateTime.now()
  //below is achieved using apply in companion object
  //but case class add out of the box factory method with name of class
  val t1 = CaseTransaction("T1",100.22,Debit,now)
  //add implemented toString as well
  println(t1)
  //implicitly add val prefix in parameter list
  //t1.amount = 34.32 //not allowed

  //add implemented equals as well
  val t2 = CaseTransaction("T2",100.22,Debit,now)
  println(t1==t2)

  //case class instances are immutable
  val t3 = t2.copy(name = "T1")
  println(t1==t3)
  println(t3.hashCode())

  /*val amount = t3 match {
    case CaseTransaction(name, amount, kind, when) => amount
  }*/

  /*val amount = t3 match {
    case CaseTransaction(_, amount, _, _) => amount
  }*/

  val amount = t3 match {
    case CaseTransaction(_, amount, kind, _) if kind == Debit && amount > 20 => amount-20
    case t:Transaction => t.amount //serves the default match scenario
  }
  println(amount)

  def getMessage(kind: Kind):String = kind match {
    case Debit => "Debit Transaction"
    case Credit => "Credit Transaction"
    case Refund => "Refund Transaction"
  }
  println(getMessage(t3.kind))
}
