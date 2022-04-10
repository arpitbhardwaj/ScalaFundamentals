package com.ab.circe

import io.circe.{ACursor, Decoder, Encoder, HCursor, Json, DecodingFailure, JsonObject}
import io.circe.generic.extras._
import io.circe.jawn.{JawnParser, decode}
import cats.implicits.catsSyntaxTuple2Semigroupal

import java.util.UUID
import scala.util.{Failure, Success, Try}


/***************************************************************/
case class Prompt(`type`: String, value: String)

object MediaChannel {
  val Broadcloud = "broadcloud"
  val Telnyx     = "telnyx"
}

sealed trait ResponseConfig {
  def mediaChannel: String
}

case class PlayMessageConfig(//promptsTts: List[Prompt],
                             prompts: List[Prompt],
                             bargeIn: String,
                             connector: String,
                             inputLanguage: Option[String] = Some("en-US"),
                             voiceLanguage: String,
                             speakingRate: Double,
                             volumeGainDb: Double,
                             mediaChannel: String = MediaChannel.Broadcloud)
  extends ResponseConfig

object ResponseConfig {

  import io.circe.generic.extras.semiauto._

  implicit val config: Configuration = Configuration.default.withDefaults

  implicit val responseConfigDecoder: Decoder[ResponseConfig] = deriveDecoder[ResponseConfig]
  //implicit val PromptDecoder: Decoder[Prompt] = deriveDecoder[Prompt]

  implicit final val FileDecoder: Decoder[Prompt] =
    Decoder.instance { cursor =>
      (
        cursor.get[String](k = "type"),
        cursor.get[String](k = "value")
        ).mapN(Prompt.apply)
    }.or(
      Decoder[String].map(value => Prompt(`type` = "audioFile", value))
    )

  /*implicit val PromptDecoder: Decoder[Prompt] = deriveDecoder[Prompt].prepare { (aCursor: ACursor) =>
  {
    aCursor.withFocus(json => {
      json.mapArray(jsonArr => {
        if (!jsonArr.contains("type")){
          Json.arr(
            Json.fromFields(
              Seq(
                ("type", Json.fromString("audio")),
                ("value", Json.fromString("")
              )
            )
          )
          )
        } else {
          jsonArr
        }
      })
    })
  }
  }*/

  //implicit val playMessageConfigDecoder: Decoder[PlayMessageConfig] = deriveDecoder[PlayMessageConfig]

  implicit val playMessageConfigDecoder: Decoder[PlayMessageConfig] = (h:HCursor) =>
    for {
      prompts <- h.get[List[Prompt]]("prompts").orElse(h.get[List[Prompt]]("promptsTts"))
      bargeIn <- h.downField("bargeIn").as[String]
      connector <- h.downField("connector").as[String]
      inputLanguage <- h.downField("inputLanguage").as[Option[String]]
      voiceLanguage <- h.downField("voiceLanguage").as[String]
      speakingRate <- h.downField("speakingRate").as[Double]
      volumeGainDb <- h.downField("volumeGainDb").as[Double]
      mediaChannel <- h.downField("mediaChannel").as[String]
    }yield{
        PlayMessageConfig(prompts, bargeIn, connector, inputLanguage, voiceLanguage, speakingRate, volumeGainDb, mediaChannel)
    }
}
/***************************************************************/

case class ResponseMeta(remoteEntity: String)

sealed trait Response {
  def interactionId: UUID
  def config: ResponseConfig
  def getName: String = this.getClass.getSimpleName
}

case class PlayMessageResponse(interactionId: UUID, config: PlayMessageConfig, meta: ResponseMeta) extends Response {

  override def toString(): String = {
    val sb = new StringBuilder()
    sb.append(s"PlayMessageResponse\n")
      .append(s" interactionId:${this.interactionId}\n")
      .append(s" prompts:${this.config.prompts}\n")
      //.append(s" promptsTts:${this.config.promptsTts}\n")
      .append(s" bargeIn:${this.config.bargeIn}\n")
      .append(s" connector:${this.config.connector}\n")
      .append(s" inputLanguage:${this.config.inputLanguage}\n")
      .append(s" voiceLanguage:${this.config.voiceLanguage}\n")
      .append(s" speakingRate:${this.config.speakingRate}\n")
      .append(s" volumeGainDb:${this.config.volumeGainDb}\n")
      .append(s" mediaChannel:${this.config.mediaChannel}\n")
      .append(s" meta:${this.meta}\n")
      .toString()
  }
}

object Response {

  import io.circe.generic.extras.semiauto._

  implicit val config: Configuration =
    Configuration.default.withDefaults.withDiscriminator("messageType")

  implicit val responseDecoder: Decoder[Response] = deriveDecoder[Response]
  implicit val responseMetaDecoder: Decoder[ResponseMeta] = deriveDecoder[ResponseMeta]
}

object Exercise{
  def main(args: Array[String]): Unit = {
    val inputString1: String =
      """
        |{
        |	"messageType": "PlayMessageResponse",
        |	"interactionId": "d9d1d025-e376-4f9d-bee4-72b594c2509b",
        |	"config": {
        |		"prompts": ["welcome.wav"],
        |		"promptsTts": [],
        |		"bargeIn": "false",
        |		"connector": "2887e88c-1fbf-424c-9e53-0c11f630b870",
        |		"inputLanguage": "",
        |		"voiceLanguage": "en-US-Wavenet-E",
        |		"speakingRate": 1,
        |		"volumeGainDb": 0,
        |		"mediaChannel": "broadcloud"
        |	},
        |	"meta": {
        |		"remoteEntity": ""
        |	}
        |}
        |""".stripMargin

    val inputString2: String =
      """
        |{
        |	"messageType": "PlayMessageResponse",
        |	"interactionId": "d9d1d025-e376-4f9d-bee4-72b594c2509b",
        |	"config": {
        |		"prompts": [{
        |			"type": "audioFile",
        |			"value": "book_a_room.wav"
        |		}, {
        |			"type": "audioPromptVariable",
        |			"value": "book_a_room.wav"
        |		}],
        |		"promptsTts": [],
        |		"bargeIn": "false",
        |		"connector": "2887e88c-1fbf-424c-9e53-0c11f630b870",
        |		"inputLanguage": "",
        |		"voiceLanguage": "en-US-Wavenet-E",
        |		"speakingRate": 1,
        |		"volumeGainDb": 0,
        |		"mediaChannel": "broadcloud"
        |	},
        |	"meta": {
        |		"remoteEntity": ""
        |	}
        |}
        |""".stripMargin

    val inputString3: String =
      """
        |{
        |	"messageType": "PlayMessageResponse",
        |	"interactionId": "d9d1d025-e376-4f9d-bee4-72b594c2509b",
        |	"config": {
        |		"prompts": [],
        |		"promptsTts": [{
        |			"type": "tts",
        |			"value": "This is old play message case 3"
        |		}, {
        |			"type": "audioFile",
        |			"value": "book_a_room.wav"
        |		}],
        |		"bargeIn": "false",
        |		"connector": "2887e88c-1fbf-424c-9e53-0c11f630b870",
        |		"inputLanguage": "",
        |		"voiceLanguage": "en-US-Wavenet-E",
        |		"speakingRate": 1,
        |		"volumeGainDb": 0,
        |		"mediaChannel": "broadcloud"
        |	},
        |	"meta": {
        |		"remoteEntity": ""
        |	}
        |}
        |""".stripMargin


    (new JawnParser).parse(inputString3) match {
       case Right(jsonObj) =>

         for{
           jObj <- jsonObj.asObject
           config <- jObj("config")
           configObj <- config.asObject
           prompts <- configObj("newFiles")
           promptsArr <- prompts.asArray
           promptsTts <- configObj("oldFiles")
           promptsTtsArr <- promptsTts.asArray
         } yield {
           if(promptsArr.size == 0){
             configObj.remove("newFiles")
           }else if(promptsTtsArr.size == 0){
             configObj.remove("oldFiles")
           }
         }

         Try(decode[Response](jsonObj)) match {
           case Success(response) =>
             println("Successfully decoded")
             println(response)
           case Failure(exception) =>
             println("Unable to decode the json to an object", exception)
         }
       case Left(exception) =>
         println("Unable to parse the string to json", exception)
     }

    def decode[T](json: Json)(implicit decoder: Decoder[T]): T =
      json.as[T] match {
        case Right(decodedObj) => decodedObj
        case Left(err) =>
          println("Unable to decode the json to an object", err)
          throw err
      }
  }


}

