package models

import play.api.libs.json.{Json, Writes}

case class Product(id: Int, name: String)

object Product {
  implicit val ProductWrites = new Writes[Product] {
    def writes(product: Product) = Json.obj(
      "id" -> product.id,
      "name" -> product.name
    )
  }
}
