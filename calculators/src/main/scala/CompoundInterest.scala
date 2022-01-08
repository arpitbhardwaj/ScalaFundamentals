object CompoundInterest {

  def calculate(m:Double, r:Double, t:Int, n:Int=1):Double = m *(math.pow(1+ r/n, n*t)-1)*(n/r)
  def main(args: Array[String]): Unit = {
    if(args.length < 3){
      throw new IllegalArgumentException("Arguments passed are not enough")
    }
    val principal = args(0).toDouble
    val rate = args(1).toDouble
    val years = args(2).toInt

    println(f"Your investment of $principal%f will worth ${calculate(principal,rate/100,years)}%f in $years")
  }
}
