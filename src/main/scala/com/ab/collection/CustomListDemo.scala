package com.ab.collection

//generic list
//+ represents covariant
trait CustomList[+A]{
  def head:A
  def tail: CustomList[A]
  def ::[B >: A](element: B):CustomList[B] = MyList(element,this)
}

case object Nil extends CustomList[Nothing] {
  override def head: Nothing = ???
  override def tail: CustomList[Nothing] = ???
}

case class MyList[+A](head:A,tail:CustomList[A]) extends CustomList[A]

//companion object
object CustomList{
  def mkString[A](list: CustomList[A]):String = {
    if (list==Nil)""
    else list.head.toString + " " + mkString(list.tail)
  }
}
object CustomListDemo extends App{
  val list1 = MyList("a", MyList("b",MyList("c",Nil)))
  var list2 = "a"::"b"::"c"::Nil
  println(CustomList.mkString(list1))
  println(CustomList.mkString(list2))
}
