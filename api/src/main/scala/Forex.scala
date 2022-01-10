import org.json4s.native.JsonMethods._
import org.json4s.JsonDSL._
import scala.collection.immutable
import scala.xml.XML

object Forex {

  def getExchangeRates:Map[String,Double] = {
    val apiUrl = "https://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml"
    val response = requests.get(apiUrl)
    val xmlRespose = XML.loadString(response.text)
    val currCodes: immutable.Seq[String] = (xmlRespose \\ "@currency").map(node => node.text)
    val euroToCurrMultiplier: immutable.Seq[Double] = (xmlRespose \\ "@rate").map(node => node.text.toDouble)

    val currencyCodeMultiplier = (currCodes zip euroToCurrMultiplier).toMap
    currencyCodeMultiplier
  }

  def getRatesAsJson:String = compact(render(getExchangeRates))

  def main(args: Array[String]):Unit = {
    println(getExchangeRates)
  }
}
