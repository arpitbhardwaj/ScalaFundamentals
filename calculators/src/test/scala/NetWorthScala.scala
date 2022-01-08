object NetWorthScala extends App {

  def runTests():Unit = {
    assert(NetWorth.calculate(100,20) == 80, "The resut should be 80")
    assert(NetWorth.calculate(1000,2000) == -1000, "The resut should be -1000")
  }

  override def main(args:Array[String]):Unit = {
    println("Running Test")
    runTests()
  }
}
