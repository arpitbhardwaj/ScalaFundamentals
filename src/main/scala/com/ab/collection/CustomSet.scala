package com.ab.collection

import scala.annotation.tailrec

trait CustomSet[A] extends (A => Boolean){
  def apply(elem: A):Boolean = contains(elem)

  def contains(elem: A): Boolean
  def +(elem: A):CustomSet[A]
  def ++(anotherSet: CustomSet[A]): CustomSet[A]

  def map[B](f: A => B): CustomSet[B]
  def flatMap[B](f: A => CustomSet[B]): CustomSet[B]
  def filter(predicate: A => Boolean): CustomSet[A]
  def forEach(f: A => Unit): Unit
}

class EmptyCustomSet[A] extends CustomSet[A]{
  override def contains(elem: A): Boolean = false

  override def +(elem: A): CustomSet[A] = new NonEmptyCustomSet[A](elem, this)

  override def ++(anotherSet: CustomSet[A]): CustomSet[A] = anotherSet

  override def map[B](f: A => B): CustomSet[B] = new EmptyCustomSet[B]

  override def flatMap[B](f: A => CustomSet[B]): CustomSet[B] = new EmptyCustomSet[B]

  override def filter(predicate: A => Boolean): CustomSet[A] = this

  override def forEach(f: A => Unit): Unit = ()
}

class NonEmptyCustomSet[A](head: A, tail: CustomSet[A]) extends CustomSet[A]{
  override def contains(elem: A): Boolean =
    elem == head | tail.contains(elem)

  override def +(elem: A): CustomSet[A] =
    if (this contains elem) this
    else new NonEmptyCustomSet[A](elem, this)

  /*

   */
  override def ++(anotherSet: CustomSet[A]): CustomSet[A] =
    tail ++ anotherSet + head

  override def map[B](f: A => B): CustomSet[B] =
    (tail map f) + f(head)

  override def flatMap[B](f: A => CustomSet[B]): CustomSet[B] =
    (tail flatMap f) ++ f(head)

  override def filter(predicate: A => Boolean): CustomSet[A] = {
    val filteredTail = tail filter predicate
    if (predicate(head)) filteredTail + head
    else filteredTail
  }

  override def forEach(f: A => Unit): Unit = {
    f(head)
    tail forEach f
  }
}

object CustomSet{
  /*def apply[A](values: A): CustomSet[A] = {
    @tailrec
    def buildSet(valueSeq: Seq[A], acc: CustomSet[A]): CustomSet[A] =
      if (valueSeq.isEmpty) acc
      else buildSet(valueSeq.tail, acc + values.head)

    buildSet(values.toSeq, new EmptyCustomSet[A])
  }*/
}
