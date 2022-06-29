package com.ab.circe

import cats.Traverse
import cats.implicits.catsSyntaxTuple2Semigroupal
import io.circe
import io.circe.Decoder.Result
import io.circe.{ACursor, Decoder, Encoder, HCursor, Json, KeyDecoder, jawn, parser}
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}
import io.circe.jawn.decode

import scala.util.{Failure, Success}

//Decoding Objects
case class Product(
                    productId: Long,
                    price: Double,
                    countryCurrency: String,
                    inStock: Boolean
                  )

object Product {
  implicit val decoder: Decoder[Product] = deriveDecoder[Product]
  implicit val encoder: Encoder[Product] = deriveEncoder[Product]

  def main(args: Array[String]): Unit = {
    val inputString: String =
      """
        |{
        |   "productId": 111112222222,
        |   "price": 23.45,
        |   "countryCurrency": "USD",
        |   "inStock": true
        |}
        |""".stripMargin

    decode[Product](inputString) match {
      case Right(productObject) => println(productObject)
      case Left(ex) => println(s"Oops some error here ${ex}")
    }
  }
}
/*******************************************Decoding Map***********************************************/

//Decoding Objects

case class ConfigMap(
                      inputLanguage: String,
                      outputVoice: String,
                      botParameters: Map[String,String]
                    )

object ConfigMap {

  def main(args: Array[String]): Unit = {
    val inputString: String =
      """
        |{
        |	"inputLanguage": "en-US",
        |	"outputVoice": "Automatic",
        |	"botParameters": {
        |		"accountNO": "123",
        |		"Name": "Deep",
        |		"customerANI": "654"
        |	}
        |}
        |""".stripMargin

    import io.circe.generic.auto._

    decode[ConfigMap](inputString) match {
      case Right(productObject) => println(productObject)
      case Left(ex) => println(s"Oops some error here ${ex}")
    }
  }
}

/*******************************************Decoding Map***********************************************/

object ConfigMap2 {

  def main(args: Array[String]): Unit = {
    val inputString: String =
      """
        |{
        |	"inputLanguage": "en-US",
        |	"outputVoice": "Automatic"
        |}
        |""".stripMargin

    /*val decoder = Decoder.decodeMap(KeyDecoder.decodeKeyString, Decoder.decodeString)
    val result = for {
      json <- jawn.parse(inputString).toTry
      map <- decoder.decodeJson(json).toTry
    } yield map

    val map = result.get
    println(map)*/

    parser.decode[Map[String,String]](inputString) match {
      case Right(books) => println(s"Here are the books ${books}")
      case Left(ex) => println(s"Ooops something error ${ex}")
    }
  }
}

/*******************************************Decoding Map***********************************************/

//Decoding Objects

case class BotParam (fields:Map[String,String])

object ConfigMap3 {

  def main(args: Array[String]): Unit = {
    val inputString: String =
      """
        |{
        |	"inputLanguage": "en-US",
        |	"outputVoice": "Automatic",
        | "botParameters": {
        |		"accountNO": "123",
        |		"Name": "Deep",
        |		"customerANI": "654"
        |	}
        |}
        |""".stripMargin

    implicit val botParamDecoder:Decoder[BotParam] = deriveDecoder[BotParam]

   /* parser.decode[Map[String,Either[String,BotParam]]](inputString) match {
      case Right(books) => println(s"Here are the books ${books}")
      case Left(ex) => println(s"Ooops something error ${ex}")
    }*/
  }
}

/*******************************************Decoding Arrays***********************************************/

case class Book(book: String)

object Book {
  implicit val decoder: Decoder[Book] = deriveDecoder[Book]

  def main(args: Array[String]): Unit = {
    val inputString =
      """
        |[
        | {"book": "Programming in Scala"},
        | {"book": "How to Win Friends and Influence People"},
        | {"book": "HomoSapiens"},
        | {"book": "Scala OOP"}
        |]
        |""".stripMargin

    parser.decode[List[Book]](inputString) match {
      case Right(books) => println(s"Here are the books ${books}")
      case Left(ex) => println(s"Ooops something error ${ex}")
    }
  }
}

/*******************************************Decoding Automatically***********************************************/

case class Form(firstName: String, lastName: String, age: Int, email: Option[String])

object Form {

  def main(args: Array[String]): Unit = {
    val inputString =
      """
        |[
        |    {"firstName": "Rose", "lastName":"Jane", "age":20, "email":"roseJane@gmail.com"},
        |    {"firstName": "John", "lastName":"Doe" , "age": 45}
        |]
        |""".stripMargin

    import io.circe.generic.auto._
    parser.decode[List[Form]](inputString) match {
      case Right(form) => println(form)
      case Left(ex) => println(s"Ooops something happened ${ex}")
    }
  }
}

/*******************************************Decoding Manually***********************************************/

case class Applicant(name: String, age: Int, phoneNumber: String)

object Applicant {
  // here we are actually casting the return value to Decode
  implicit val decoder: Decoder[Applicant] = (hCursor: HCursor) =>
    for {
      name <- hCursor.get[String]("name")
      //.get[A](key) with .downfield("key").as[A] is the same thing.
      age <- hCursor.downField("age").as[Int]
      phoneNumber <- hCursor.get[String]("phoneNumber")
    } yield Applicant(name, age, phoneNumber)

  def main(args: Array[String]): Unit = {
    val inputString =
      """
        |[
        | {"name": "Jane Doe", "age":26, "phoneNumber":"512222222"},
        | {"name": "Petter Pan", "age":55, "phoneNumber":"214553356"},
        | {"name": "Jason Mamoa", "age":33, "phoneNumber":"2111112234", "email":"jasonMamoa@gmail.com"}
        |]
        |""".stripMargin

    parser.decode[List[Applicant]](inputString) match {
      case Right(applicants) => println(applicants)
      case Left(ex) => println(s"Oops something is wrong with decoding value ${ex}")
    }
  }
}

/*******************************************Decoding an Arrays of Objects of Arrays***********************************************/

case class ProductResource(name: String, campaignResources: List[Int], discountPrice: List[Int])

object voucher {
  implicit val decoder: Decoder[ProductResource] = new Decoder[ProductResource] {
    override def apply(hCursor: HCursor): Result[ProductResource] =
      for {
        name <- hCursor.downField("name").as[String]
        orderItemsJson <- hCursor.downField("orderItems").as[List[Json]]
        campaignResource <- Traverse[List].traverse(orderItemsJson)(
          orderItemsJson => orderItemsJson.hcursor.downField("voucher").downField("campaignNumber").as[Int]
        )
        discountPrice <- Traverse[List].traverse(orderItemsJson)(
          orderItemsJson => orderItemsJson.hcursor.downField("voucher").downField("discount").as[Int]
        )
      } yield {
        ProductResource(name, campaignResource, discountPrice)
      }
  }

  def main(args: Array[String]): Unit = {
    val inputString =
      """
        |[
        |   {
        |      "name":"productResource",
        |      "orderItems":[
        |         {
        |            "voucher":{
        |               "campaignNumber":12,
        |               "discount":20,
        |               "subscriptionPeriod":"June"
        |            }
        |         },
        |         {
        |            "voucher":{
        |               "campaignNumber":13,
        |               "discount":24
        |            }
        |         }
        |      ]
        |   },
        |   {
        |      "name":"productResource2",
        |      "orderItems":[
        |         {
        |            "voucher":{
        |               "campaignNumber":13,
        |               "discount":24
        |            }
        |         }
        |      ]
        |   },
        |   {
        |      "name":"productResource3",
        |      "orderItems":[
        |         {
        |            "voucher":{
        |               "campaignNumber":15,
        |               "discount":28
        |            }
        |         }
        |      ]
        |   }
        |]
        |""".stripMargin

    parser.decode[List[ProductResource]](inputString) match {
      case Right(vouchers) => vouchers.map(println)
      case Left(ex) => println(s"Something wrong ${ex}")
    }
  }
}

/********************************Handling Class with Default on Non-Optional Field************************************/

case class Company(industry: String, year: Int, name: String, public: Boolean)

object Company {
  implicit val decoder: Decoder[Company] = deriveDecoder[Company].prepare { (aCursor: ACursor) =>
  {
    aCursor.withFocus(json => {
      json.mapObject(jsonObject => {
        if (jsonObject.contains("public")) {
          jsonObject
        } else {
          jsonObject.add("public", Json.fromBoolean(false))
        }
      })
    })
  }
  }

  def main(args: Array[String]): Unit = {
    val inputString =
      """
        |[
        | {"industry":"tech", "year":1990, "name":"Intel", "public": true},
        | {"industry":"tech", "year":2006, "name":"Netflix"},
        | {"industry":"Consumer Goods", "year":1860, "name":"Pepsodent", "public": true}
        |]
        |""".stripMargin

    parser.decode[List[Company]](inputString) match {
      case Right(companies) => companies.map(println)
      case Left(ex) => println(s"ooops something wrong ${ex}")
    }
  }
}

case class Company2(industry: List[String], public: Boolean)

object Company2 {
  //implicit val decoder: Decoder[Company2] = deriveDecoder[Company2]
  implicit val decoder: Decoder[Company2] = deriveDecoder[Company2].prepare { (aCursor: ACursor) =>
  {
    aCursor.withFocus(json => {
      json.mapObject(jsonObject => {
        if (jsonObject.contains("public")) {
          jsonObject
        } else {
          jsonObject.add("public", Json.fromBoolean(false))
        }
      })
    })
  }
  }

  def main(args: Array[String]): Unit = {
    val inputString2 =
      """
        |{
        |	"industry": ["welcome"]
        |}
        |""".stripMargin

    parser.decode[Company2](inputString2) match {
      case Right(company) => println(company)
      case Left(ex) => println(s"ooops something wrong ${ex}")
    }
  }
}

/********************************************************************/

case class File(`type`: String, value: String)

case class Config(files: List[File],
                  channel: String = "BC")

object Config{
  implicit val FileDecoder: Decoder[File] = deriveDecoder[File]

  implicit val ConfigDecoder: Decoder[Config] = (h:HCursor) =>
    for {
      oldFiles <- h.get[List[File]]("oldFiles")
      files <- if (oldFiles.isEmpty) h.get[List[File]]("newFiles") else h.get[List[File]]("oldFiles")
      channel <- h.downField("channel").as[String]
    }yield{
      Config(files, channel)
    }
}

case class Inventory(config: Config)

object Inventory {
  implicit val InventoryDecoder: Decoder[Inventory] = deriveDecoder[Inventory]
}

object RealTime {
  def main(args: Array[String]): Unit = {
    val inputString1 =
      """
        |{
        |	"config": {
        |		"newFiles": [{
        |			"type": "audio",
        |			"value": "welcome1.mp3"
        |		}],
        |		"oldFiles": [],
        |		"channel": "BC"
        |	}
        |}
        |""".stripMargin

    val inputString2 =
      """
        |{
        |	"config": {
        |		"newFiles": [],
        |		"oldFiles": [{
        |			"type": "audio",
        |			"value": "welcome2.mp3"
        |		}],
        |		"channel": "BC"
        |	}
        |}
        |""".stripMargin

    parser.decode[Inventory](inputString2) match {
      case Right(company) => println(company)
      case Left(ex) => println(s"ooops something wrong ${ex}")
    }
  }
}

/********************************************************************/
case class File3(`type`: String, value: String)

case class Config3(files: List[File3],
                  channel: String = "BC")

object Config3{
  implicit final val FileDecoder: Decoder[File3] =
    Decoder.instance { cursor =>
      for {
        key <- cursor.get[String]("type")
        value <- cursor.get[String]("value")
      }yield{
        File3(key, value)
      }
    }.or(
      Decoder[String].map(value => File3(`type` = "audioFile", value))
    )
  implicit val ConfigDecoder: Decoder[Config3] = deriveDecoder[Config3]
}

case class Inventory3(config: Config3)

object Inventory3 {
  implicit val InventoryDecoder: Decoder[Inventory3] = deriveDecoder[Inventory3]
}

object RealTime3 {
  def main(args: Array[String]): Unit = {
    val inputString1 =
      """
        |{
        |	"config": {
        |		"files": ["welcome1","welcome2"],
        |		"channel": "media"
        |	}
        |}
        |""".stripMargin

    val inputString2 =
      """
        |{
        |	"config": {
        |		"files": [{
        |				"type": "audio",
        |				"value": "welcome1.mp3"
        |			},
        |			{
        |				"type": "tts",
        |				"value": "welcome2.mp3"
        |			}
        |		],
        |		"channel": "BC"
        |	}
        |}
        |""".stripMargin

    parser.decode[Inventory3](inputString1) match {
      case Right(company) => println(company)
      case Left(ex) => println(s"ooops something wrong ${ex}")
    }
  }
}

/********************************************************************/

case class File2(`type`: String, value: String)

object File2{
  //implicit val FileDecoder: Decoder[File] = deriveDecoder[File]
  implicit final val FileDecoder: Decoder[File2] =
    Decoder.instance { cursor =>
      (
        cursor.get[String](k = "type"),
        cursor.get[String](k = "value")
        ).mapN(File2.apply)
    }.or(
      Decoder[String].map(value => File2(`type` = "audio", value))
    )
}

object RealTime2 {
  def main(args: Array[String]): Unit = {
    val inputString =
      """
        |[{
        |		"type": "audioFile",
        |		"value": "welcome.mp3"
        |	},
        |	{
        |   "type": "tts",
        |		"value": "Hey Hello"
        |	},
        | "bar.mp3"
        |]
        |""".stripMargin

    parser.decode[List[File2]](inputString) match {
      case Right(company) => println(company)
      case Left(ex) => println(s"ooops something wrong ${ex}")
    }
  }
}