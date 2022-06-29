package com.ab.circe

import io.circe

object FilterJson extends App {
  import io.circe._
  val jsonString: String =
  """
    |[{
    |		"id": "01111",
    |		"clientId": "something",
    |		"name": "something",
    |		"enabled": true,
    |		"alwaysDisplayInConsole": false,
    |		"redirectUris": [
    |			"/something/"
    |		],
    |		"directAccessGrantsEnabled": false,
    |		"publicClient": true,
    |		"access": {
    |			"view": true,
    |			"configure": true,
    |			"manage": true
    |		},
    |		"claimValue": ""
    |	},
    |	{
    |		"id": "0022",
    |		"clientId": "something",
    |		"name": "dd",
    |		"enabled": true,
    |		"alwaysDisplayInConsole": false,
    |		"redirectUris": [
    |			"/something/*"
    |		],
    |		"directAccessGrantsEnabled": false,
    |		"publicClient": true,
    |		"access": {
    |			"view": true,
    |			"configure": true,
    |			"manage": true
    |		},
    |		"claimValue": "need"
    |	},
    |	{
    |		"id": "00223",
    |		"clientId": "something",
    |		"name": "dd",
    |		"enabled": true,
    |		"alwaysDisplayInConsole": false,
    |		"redirectUris": [
    |			"/something/*"
    |		],
    |		"directAccessGrantsEnabled": false,
    |		"publicClient": true,
    |		"access": {
    |			"view": true,
    |			"configure": true,
    |			"manage": true
    |		},
    |		"claimValue": "need"
    |	}
    |]
    |""".stripMargin

  def claimValueFilter(j:Json): Json = j.withArray { x =>
    Json.fromValues(x.filter(_.hcursor.downField("claimValue").as[String].map(_.equals("need")).getOrElse(false)))
  }
  val y: Either[ParsingFailure, Json] = circe.parser.parse(jsonString).map( _.hcursor.withFocus(claimValueFilter).top.get)
  y.foreach(x => println(x))
}
