name := "ScalaFundamentals"
version := "0.1"
scalaVersion := "2.13.7"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.10" % "test"

/*
SeetingKey[T] Computes once per project
TaskKey[T] Recomputed everytime called
InputKey[T] Acceprs command line arguments
*/

//custom setting keys
lazy val emotion =  settingKey[String]("How are you feeling") //description is for help command
emotion := "Fantastic"

//execute them with show command
val randomInt = taskKey[Int]("Give me random number")
randomInt := scala.util.Random.nextInt

/*
  setting may depend upon 1 or more setting
 task may depend upon 1 or more task or settings
 */

val status = settingKey[String]("What is your current status")

status := {
  val e = emotion.value
  s"Grateful and $e"
}

randomInt := {
  println(emotion.value)
  scala.util.Random.nextInt
}

//sbt creates the aggreagate relationship for you implicitly
/*lazy val root = project.in(file("."))
  .aggregate(calculators)*/

lazy val calculators = project.settings(
  libraryDependencies ++= Dependencies.calculatorDependencies
)

lazy val api = project.settings(
  libraryDependencies ++= Dependencies.apiDependencies

)