package com.ab.oo

import java.util.UUID
import java.time._

class Account (id:UUID, name: String, dateOpened: LocalDateTime){
  private var _id: UUID = id
  private var _name:String = name //private
  private var _dateOpened:LocalDateTime = dateOpened

  def this(name:String){
    this(UUID.randomUUID(),name,LocalDateTime.now())
  }

  def getId: UUID = _id
  def getName: String = _name
  def getDateOpened: LocalDateTime = _dateOpened

  def setName(newName:String):Unit = _name = newName
}

class CreditAccount(name:String) extends Account(name:String){
  private val _accountType = "Credit"
  def getAccountType:String = _accountType
}

class DepositsAccount(name:String) extends Account(name:String){
  private val _accountType = "Deposits"
  def getAccountType:String = _accountType
}

object AccountRunner extends App{
  var a1 = new Account(UUID.randomUUID(),"Account01",LocalDateTime.now())
  var a2 = new Account(UUID.randomUUID(),"Account01",LocalDateTime.now().plusHours(6))
  var a3 = new Account("Account03")

  /*a1._name = "Account04"
  println(a1._name)
  println(a2._name)
  println(a3._name)*/

  println(a1.getId)
  a2.setName("Account04")
  println(a2.getName)
  println(a3.getDateOpened)

  val ca1:CreditAccount = new CreditAccount("Travel Mastercard")
  println(ca1.getId,ca1.getName,ca1.getDateOpened,ca1.getAccountType)

  val da1:DepositsAccount = new DepositsAccount("Super Savings")
  println(da1.getId,da1.getName,da1.getDateOpened,da1.getAccountType)
}
