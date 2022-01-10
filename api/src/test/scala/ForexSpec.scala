import org.scalatest.{FlatSpec, Matchers}

class ForexSpec extends FlatSpec with Matchers{
  "The forex api" should "fetch 32 currencies" in {
    val exchangeRates = Forex.getExchangeRates
    exchangeRates.size should be (32)
  }
}
