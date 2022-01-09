import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.{HttpApp, Route}

object WebServer extends HttpApp{
  override protected def routes: Route = path("rates"){
    get{
      complete(HttpEntity(ContentTypes.`application/json`,Forex.getRatesAsJson()))
    }
  }

  def main(args:Array[String]):Unit = {
    val port:Int = sys.env.getOrElse("PORT","8080").toInt
    WebServer.startServer("0.0.0.0",port)
  }
}
