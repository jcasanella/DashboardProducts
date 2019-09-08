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
  val callApi = "nearbySearch"

  val response: Future[HttpResponse] =
    Http().singleRequest(
      HttpRequest(
        method = HttpMethods.GET,
        uri = s"${baseUrl}/search/${versionNumber}/${callApi}/.json?" +
        s"key=${KeyApp.keyApp}" +
        s"&lat=40.769" +
        s"&lon=-73.9549" +
        s"&radius=50" +
        "&limit=100" +
        "&language=en-us"
      )
    )

  response.onComplete {
    case Success(res) => println(res)
    case Failure(_)   => sys.error("Something wrong!!!")
  }

//  system.terminate()
}
