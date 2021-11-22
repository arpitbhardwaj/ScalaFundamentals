package com.ab.oo

import java.time.LocalDateTime

sealed trait Kind
case object Debit extends Kind
case object Credit extends Kind
case object Refund extends Kind

case class CaseTransaction (name:String, amount:Double, kind:Kind, when:LocalDateTime)

object CaseTransaction extends App{
  val now:LocalDateTime = LocalDateTime.now()
  val t1 = CaseTransaction("T1",100.22,Debit,now)
  println(t1)
  //t1.amount = 34.32

  val t2 = CaseTransaction("T2",100.22,Debit,now)
  println(t1==t2)

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
    case t:Transaction => t.amount
  }
  println(amount)
  def getMessage(kind: Kind):String = kind match {
    case Debit => "Debit Transaction"
    case Credit => "Credit Transaction"
    case Refund => "Refund Transaction"
  }
  println(getMessage(t3.kind))
}
