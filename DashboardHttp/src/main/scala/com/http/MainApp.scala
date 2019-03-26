package com.http
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{HttpMethods, HttpRequest, HttpResponse}
import akka.stream.ActorMaterializer

import scala.concurrent.Future
import scala.util.{Failure, Success}

object MainApp extends App {

//  ParserCmdLine(args) match {
//    case Some(cmd) => println(s"Params are working: ${cmd.name} ${cmd.key}")
//    case None      => println("Error params are not working")
//  }

  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer()
  implicit val executionContext = system.dispatcher

  val baseUrl = "https://api.tomtom.com"
  val versionNumber = "2"
  val keyApp = "NhDiMjpG7VGMF11YBsCVIfexRtXraOgj"
  val callApi = "nearbySearch"

  val response: Future[HttpResponse] =
    Http().singleRequest(
      HttpRequest(
        method = HttpMethods.GET,
        uri = s"${baseUrl}/search/${versionNumber}/search/${callApi}/.json?" +
        s"key=${keyApp}" +
        s"&lat=37.8085" +
        s"&lon=-122.4239" +
        s"&radius=100" +
        "&limit=1000" +
        "&language=en-us"
      )
    )

  response.onComplete {
    case Success(res) => println(res)
    case Failure(_)   => sys.error("Something wrong!!!")
  }

//  system.terminate()
}