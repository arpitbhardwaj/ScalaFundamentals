import org.scalatest.{FlatSpec, Matchers}

class NetWorthSpec extends FlatSpec with Matchers {
  "A net worth calculator" should "return 500" in {
    NetWorth.calculate(1000,500) should be (500)
  }

  "A net worth calculator" should "return 5000" in {
    NetWorth.calculate(10000,5000) should be (5000)
  }
}
