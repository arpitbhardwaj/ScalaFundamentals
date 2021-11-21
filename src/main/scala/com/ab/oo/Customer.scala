package com.ab.oo

import java.util.UUID

final class Customer (first:String, last:String, email:String){
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

object CustomerRunner extends App{
  val c1:Customer = new Customer("Tony", "Stark", "tonystark@com")
  println(c1.getFirst,c1.getLast,c1.getEmail)
}