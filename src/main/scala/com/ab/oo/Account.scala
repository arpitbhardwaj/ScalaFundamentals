package com.ab.oo

import java.util.UUID
import java.time._

abstract class Account (id:UUID, name: String, dateOpened: LocalDateTime){
  private var _id: UUID = id
  private var _name:String = name //private
  private var _dateOpened:LocalDateTime = dateOpened
  val _accountType:String

  def this(name:String){
    this(UUID.randomUUID(),name,LocalDateTime.now())
  }

  def getId: UUID = _id
  def getName: String = _name
  def getDateOpened: LocalDateTime = _dateOpened
  def setName(newName:String):Unit = _name = newName
  def getAccountType:String = _accountType


  override def toString = s"Account(${_id}, ${_name}, ${_dateOpened})"
}

class CreditAccount(name:String) extends Account(name:String){
  /*private val _accountType = "Credit"
  def getAccountType:String = _accountType*/
  override val _accountType: String = "Credit"

  override def toString = s"CreditAccount(${getId}, ${getName}, ${getDateOpened},${_accountType})"
}

class DepositsAccount(name:String) extends Account(name:String){
  /*private val _accountType = "Deposits"
  def getAccountType:String = _accountType*/
  override val _accountType: String = "Deposits"

  override def toString = s"DepositsAccount(${getId}, ${getName}, ${getDateOpened},${_accountType})"
}

object AccountRunner extends App{
  /*var a1 = new Account(UUID.randomUUID(),"Account01",LocalDateTime.now())
  var a2 = new Account(UUID.randomUUID(),"Account01",LocalDateTime.now().plusHours(6))
  var a3 = new Account("Account03")

  a1._name = "Account04"
  println(a1._name)
  println(a2._name)
  println(a3._name)

  println(a1.getId)
  a2.setName("Account04")
  println(a2.getName)
  println(a3.getDateOpened)*/

  val ca1:Account = new CreditAccount("Travel Mastercard")
  println(ca1)

  val da1:Account = new DepositsAccount("Super Savings")
  println(da1)
}
