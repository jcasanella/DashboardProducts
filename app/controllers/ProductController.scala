package controllers

import javax.inject.{Inject, Singleton}
import models.Product
import play.api.libs.json._
import play.api.mvc.{AbstractController, ControllerComponents}

@Singleton
class ProductController @Inject()(cc: ControllerComponents)(implicit assetsFinder: AssetsFinder)
  extends AbstractController(cc) {

  val products = List(Product(1, "Prod1"), Product(2, "Prod2"))

  def show = Action {
    Ok(views.html.product(products))
  }

  def showJson = Action { implicit request =>
    Ok(Json.toJson(Product(1, "Prod")))
  }
}
