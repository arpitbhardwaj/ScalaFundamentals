package com.ab.multithreading

import scala.concurrent.Future
import scala.util.{Failure, Random, Success}
import scala.concurrent.ExecutionContext.Implicits.global

object FuturesComposition extends App {

  case class Profile(id:String, name:String) {
    def poke(anotherProfile:Profile) = {
      println(s"${this.name} poking ${anotherProfile.name}")
    }
  }

  object SocialNetworkApp{
    val names = Map (
      "id_zuck" -> "zuck",
      "id_bill" -> "bill",
      "id_dummy" -> "dummy"
    )

    val friends = Map(
      "id_zuck" -> "id_bill",
      "id_bill" -> "id_rita"
    )

    val random = new Random()

    def fetchProfile(id: String):Future[Profile] = Future {
      Thread.sleep(random.nextInt(300))
      Profile(id, names(id))
    }

    def fetchBestFriendProfile(profile: Profile):Future[Profile] = Future {
      Thread.sleep(random.nextInt(500))
      val bfId = friends(profile.id)
      Profile(bfId, names(bfId))
    }
  }

  //client side
  val zuck = SocialNetworkApp.fetchProfile("id_zuck")

  zuck.onComplete{
    case Success(zuckProfile) => {
      val bill = SocialNetworkApp.fetchBestFriendProfile(zuckProfile)
      bill.onComplete{
        case Success(billProfile) => zuckProfile.poke(billProfile)
        case Failure(exception) => exception.printStackTrace()
      }
    }
    case Failure(exception) => exception.printStackTrace()
  }

  //refactor above spaghetti code using functional composition of futures
  //map,flatMap,filter
  val name = zuck.map(profile => profile.name)
  val zuckBestFriend = zuck.flatMap(profile => SocialNetworkApp.fetchBestFriendProfile(profile))
  val zuckBestFriendRestricted = zuck.filter(profile => profile.name.startsWith("z"))

  for {
    zuck <- SocialNetworkApp.fetchProfile("id_zuck")
    bill <- SocialNetworkApp.fetchBestFriendProfile(zuck)
  } zuck poke bill

  //fallbacks
  //recover, recoverWith,
  val aProfileNoMatterWhat = SocialNetworkApp.fetchProfile("unknown id").recover{
    case e:Throwable => Profile("id_dummy", "Dummy")
  }

  val aFetchedProfileNoMatterWhat = SocialNetworkApp.fetchProfile("unknown id").recoverWith{
    case e:Throwable => SocialNetworkApp.fetchProfile("id_dummy")
  }

  val fallBackResult = SocialNetworkApp.fetchProfile("unknown id").fallbackTo(SocialNetworkApp.fetchProfile("id_dummy"))

  Thread.sleep(2000)
}
