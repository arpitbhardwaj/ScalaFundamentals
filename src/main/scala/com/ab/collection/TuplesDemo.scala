package com.ab.collection

/**
 * Tuples are often referred to nTuples
 *
 * 1        Single
 * 2,3      Pair
 * 4,5,6    Triple
 * 7,8,9,10 Quadruple
 * 1,2,..22 Duovigintuple
 */
object TuplesDemo extends App{
  (1,2)
  ("BEL","GOG")
  (1,"GOG")

  val t1: Tuple2[Int,String] = (12,"GOG")
  val t2: (Int,String) = (12,"GOG") //syntactic sugar

  //arrow opeartor can be used only for pair
  val t3: (Int,String) = 12 -> "GOG"

  //extracting elements
  val x = t3._1
  val y = t3._2

  //using unapply
  val (a,b) = "A" -> 23
  val (c,d) = t3

  //using pattern match
  def example(tuple:Any): String = {
    tuple match {
      case (_,_,_)              => "3 Elements"
      case ("A",_)              => "A"
      case (_, n:Int) if n>10   => s"$n is >10"
      case (a,b)                => s"$a and $b"
    }
  }

  println("match was " + example(("a",23)))
}
