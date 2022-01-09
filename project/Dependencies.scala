import sbt._

object Dependencies {
  val scalaRequest = "com.lihaoyi" %% "requests" % "0.1.7"
  val scalaXml = "org.scala-lang.modules" %% "scala-xml" % "1.1.1"
  val scalaTest = "org.scalatest" %% "scalatest" % "3.0.5"
  val akkaHttp = "com.typesafe.akka" %% "akka-http" % "10.2.6"
  val akkaStream = "com.typesafe.akka" %% "akka-stream" % "2.6.18"
  val json4s = "org.json4s" %% "json4s-native" % "3.6.5"

  val commonDependencies: Seq[ModuleID] = Seq(scalaTest % Test)

  val apiDependencies:Seq[ModuleID] = Seq(scalaRequest,scalaXml,akkaHttp,akkaStream,json4s) ++ commonDependencies
  val calculatorDependencies:Seq[ModuleID] = commonDependencies
}
