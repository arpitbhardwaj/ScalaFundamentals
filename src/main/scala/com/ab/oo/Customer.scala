package com.ab.oo

import java.util.UUID

/*final*/ class Customer (first:String, last:String, email:String){
  private val _id:UUID = UUID.randomUUID()
  private val _first:String = first
  private val _last:String = last
  private val _email:String = email

  def getId:UUID = _id
  def getFirst:String = _first
  def getLast:String = _last
  def getEmail:String = _email
}

//class HackCustomer (first:String = "**",last:String="**",email:String="**") extends Customer{}

trait Address{
  protected var _zip:Int
  protected var _street:String
  protected var _state:String

  def setZip(zip:Int):Unit
  def setStreet(street:String):Unit
  def setState(state:String):Unit
  def printAddress():Unit = println(s"CustomerWithAddress(${_zip}, ${_street}, ${_state})")
}
trait SecretCodeGenerator{
  def getOTP:String = UUID.randomUUID().toString
}

class DepositBoxContainer extends SecretCodeGenerator

class CustomerWithAddress(first:String, last:String, email:String)
  extends Customer(first,last,email)
    with Address
    with SecretCodeGenerator {
  override protected var _zip: Int = -1
  override protected var _street: String = ""
  override protected var _state: String = ""

  override def setZip(zip: Int): Unit = _zip = zip

  override def setStreet(street: String): Unit = _street = street

  override def setState(state: String): Unit = _state = state
}
object CustomerRunner extends App{
  val c1:Customer = new Customer("Tony", "Stark", "tonystark@com")
  println(c1.getFirst,c1.getLast,c1.getEmail)

  var c2:CustomerWithAddress = new CustomerWithAddress("Tony", "Stark", "tonystark@com")
  c2.setZip(560048)
  c2.setStreet("45 Fermont")
  c2.setState("Karnataka")
  c2.printAddress()
  println(c2.getOTP)

  val depositBoxContainer = new DepositBoxContainer
  println("DepositBoxContainer OTP: " + depositBoxContainer.getOTP)
}