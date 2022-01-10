object NetWorthMultiCurrency {
  def calculate(assets:Double, liabilities:Double):Double = assets-liabilities

  def main(args: Array[String]): Unit = {
    val assets = args(0)
    val liabilities = args(1)
    val assetInEuros = assets.split(",").map(Currency(_))
    val liabilitiesInEuros = liabilities.split(",").map(Currency(_))

    val totalAssetsInEuros = assetInEuros.map(_.valueInEuros).sum
    val totalLiabilitiesInEuros = liabilitiesInEuros.map(_.valueInEuros).sum

    println(s"Your net worth is ${calculate(totalAssetsInEuros,totalLiabilitiesInEuros)}")
  }
}

object Currency{
  val currencyConverter: Map[String,Double] = Forex.getExchangeRates

  def apply(value:String):Currency = {
    val Array(amountString:String, currencyCode:String) = value.split("\\s")
    val amount:Long = amountString.toLong
    if (currencyCode != "EUR"){
      val multiplier:Double = 1 / currencyConverter(currencyCode)
      println(s"$currencyCode -> EUR : $multiplier")
      new Currency(amount,currencyCode,amount*multiplier)
    }
    new Currency(amount, currencyCode, amount)
  }
}
case class Currency(amount:Long,code:String,valueInEuros: Double)