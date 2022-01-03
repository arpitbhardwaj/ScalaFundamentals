package com.ab.oo

/**
 *
 */

import java.util.UUID
import java.time._

abstract class Account (id:UUID, name: String, dateOpened: LocalDateTime){
  //member variables
  private val _id: UUID = id
  private var _name:String = name //private
  private val _dateOpened:LocalDateTime = dateOpened
  val _accountType:String //abstract member because it does not have any value

  //auxilliary constructor
  def this(name:String){
    this(UUID.randomUUID(),name,LocalDateTime.now())
  }

  //member methods
  def getId: UUID = _id
  def getName: String = _name
  def getDateOpened: LocalDateTime = _dateOpened
  def setName(newName:String):Unit = _name = newName
  def getAccountType:String = _accountType

  //default implementation which prints the hashcode of object
  //override def toString: String = super.toString
  override def toString = s"Account(${_id}, ${_name}, ${_dateOpened})"//string interpolation
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

trait Balance{
  private var _balance:Double=0
  def getBalance:Double = _balance
  def setBalance(newBalance:Double):Unit = _balance = newBalance
  override def toString: String = s"Balance=${getBalance}"
}

trait AnnualFees extends Balance{
  override def setBalance(newBalance: Double): Unit = super.setBalance(newBalance-100)
}

trait HighSavings extends Balance{
  override def setBalance(newBalance: Double): Unit = super.setBalance(( newBalance + 500 ) * ( 1 + 0.50 ))
}

class PremiumSavingsAccount(name:String)extends DepositsAccount(name:String) with AnnualFees

//stackable modification
//from from farthest right to left
//first setBalance called from HighSavings then AnnualFees then DepositAccount
//class PremiumPromoSavingsAccount(name:String)extends DepositsAccount(name:String) with AnnualFees with HighSavings

class PremiumPromoSavingsAccount(name:String)extends DepositsAccount(name:String) with HighSavings with AnnualFees

object AccountRunner extends App{
  val ca1:Account = new CreditAccount("Travel Mastercard")
  println(ca1)

  val da1:Account = new DepositsAccount("Super Savings")
  println(da1)

  var psa = new PremiumSavingsAccount("Premium Saving Account")
  psa.setBalance(999)
  println(psa.getBalance)

  var ppsa = new PremiumPromoSavingsAccount("Premium Promotional Saving Account")
  ppsa.setBalance(999)
  println(ppsa.getBalance)
}
