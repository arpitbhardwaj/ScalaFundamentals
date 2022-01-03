package com.ab.methods

/**
 * @Arpit Bhardwaj
 *
 * Generic Methods using Type Parameters
 */
object TypeParameters extends App {
  def pickRandomStock(tickers:Seq[String]):String = {
    val randomNum = util.Random.nextInt(tickers.length)
    tickers(randomNum)
  }

  def pickRandomQty(quantities:Seq[Int]):Int = {
    val randomNum = util.Random.nextInt(quantities.length)
    quantities(randomNum)
  }

  def pickRandomVal(values:Seq[Double]):Double = {
    val randomNum = util.Random.nextInt(values.length)
    values(randomNum)
  }

  //single generic method for above ones
  def pickRandom[A](list: Seq[A]):A = {
    val random = util.Random.nextInt(list.length)
    list(random)
  }

  /*val stock = pickRandomStock(List("PS","TSLA","AAPL","FB"))
  val qty = pickRandomQty(List(10,100,1000,10000))
  val value = pickRandomVal(List(10.0,20.0,50.0))*/

  val stock = pickRandom(List("PS","TSLA","AAPL","FB"))
  val qty = pickRandom(List(10,100,1000,10000))
  val value = pickRandom(List(10.0,20.0,50.0))

  println(s"The pick for the day is ${qty} shares of ${stock} " +
        s"if the price is greater than ${value}")

}
