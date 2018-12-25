package controllers

import javax.inject.{Inject, Singleton}
import models.Product
import play.api.Logger
import play.api.libs.json._
import play.api.mvc.{AbstractController, ControllerComponents}
import play.api.routing.JavaScriptReverseRouter

import scala.concurrent.Future

@Singleton
class ProductController @Inject()(cc: ControllerComponents)(implicit assetsFinder: AssetsFinder)
  extends AbstractController(cc) {

  val products = List(Product(1, "Prod1"), Product(2, "Prod2"))
  val logger = Logger(getClass)

  def show = Action {
    logger.debug("show - send products")
    Ok(views.html.product(products))
  }

 /* def showJson = Action { implicit request =>
    logger.debug("showJson - parsing")
    val msg = Json.stringify(Json.toJson(Product(1, "Prod")))
    Ok(views.html.viewsJson(msg))
  }*/

  def showJson = Action.async { implicit request =>
    logger.debug("showJson - parsing")
    val msg = Json.toJson(Product(1, "Prod"))
    Future.successful(Ok(msg))
  }

  def jsRoutes = Action { implicit request =>
    Ok(
      JavaScriptReverseRouter("jsRoutes")(
        routes.javascript.ProductController.showJson
      )
    ).as("text/javascript")
  }
}
