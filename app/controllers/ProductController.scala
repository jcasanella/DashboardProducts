package controllers

import dao.ProductDAO
import javax.inject.{Inject, Singleton}
import models.Product
import play.api.Logger
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import play.api.libs.json._
import play.api.mvc.{AbstractController, ControllerComponents}
import play.api.routing.JavaScriptReverseRouter
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success}

@Singleton
class ProductController @Inject()(productDao: ProductDAO, cc: ControllerComponents)
                                 (implicit ec: ExecutionContext, assetsFinder: AssetsFinder)
  extends AbstractController(cc) {

  val products = List(Product(1, "Prod1"), Product(2, "Prod2"), Product(3, "Prod3"), Product(4, "Prod4"))
  val logger = Logger(getClass)

  def show = Action {
    logger.debug("show - send products")
    Ok(views.html.product(products))
  }

//  def showJson = Action.async { implicit request =>
//    logger.debug("showJson - parsing")
//    val msg = Json.toJson(products)
//    Future.successful(Ok(msg))
//  }

  def jsRoutes = Action { implicit request =>
    Ok(
      JavaScriptReverseRouter("jsRoutes")(
        routes.javascript.ProductController.showJson
      )
    ).as("text/javascript")
  }

  def showJson = Action.async { implicit request =>
    val prods = productDao.all()
    prods.map(cs => Ok(Json.toJson(cs)))
  }
}
