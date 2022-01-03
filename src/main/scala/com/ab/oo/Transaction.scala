package com.ab.oo

/**
 * @Arpit Bhardwaj
 *
 *
 */
trait User{
  def getFirst:String
  def getLast:String
}

/*trait Transaction extends User {
  def printAmountWithTransaction(amount:Double):Unit = {
    val fullCustomerName = getFirst +" " + getLast
    val tax = amount*0.10
    println(s"$fullCustomerName paid $tax on amount $amount")
  }
}*/

//other way to get the information from other traits without directly extending them by declaring the self type
//reassigning the self reference this to use trait
//this is the reference to the current object in java and here we can make use of it to point to the trait we need
trait Transaction {
  //declaring a self type
  this: User=>
  def printAmountWithTransaction(amount:Double):Unit = {
    val fullCustomerName = this.getFirst +" " + this.getLast
    val tax = amount*0.10
    println(s"$fullCustomerName paid $tax on amount $amount")
  }
}

//declaring parameters using val keyword will let the compiler to generate getter methods
//hence these are instance variables instead of class parameters
class DebitTransaction(val first:String, val last:String) extends User with Transaction {
  override def getFirst: String = first
  override def getLast: String = last
}

object TransactionRunner extends App{
  val transaction = new DebitTransaction("Tony","Stark")
  transaction.printAmountWithTransaction(999)
}




