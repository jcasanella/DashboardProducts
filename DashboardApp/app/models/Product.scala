package models

import play.api.libs.json.{Json, Writes}

class Model
case class Product(id: Int, name: String) extends Model

object Product {
  implicit val ProductWrites = new Writes[Product] {

    def writes(product: Product) = Json.obj(
      "id"   -> product.id,
      "name" -> product.name
    )
  }
}
