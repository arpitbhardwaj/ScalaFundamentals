package com.ab.enums

import com.ab.enums.Enums.MessageType

object Enums extends Enumeration {
  case object MessageType extends Enumeration {
    type MessageType = Value
    val PlayMessageResponse, PlayMenuResponse, CollectDigitsResponse, MusicInQueueResponse, FeedbackResponse = Value
    //def withNameOpt(s: String): Option[Value] = values.find(_.toString == s)
  }


}

object EnumsDemo extends App{
  val value = MessageType.withName("PlayMessageResponse")
  println(value == MessageType.PlayMessageResponse)
}
