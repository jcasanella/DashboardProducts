package com.model.json
import spray.json.{DefaultJsonProtocol, JsArray, JsNumber, JsObject, JsString, JsValue, RootJsonFormat}
import spray.json._

object ResponseProtocol extends DefaultJsonProtocol {

  implicit val positionFormat = jsonFormat2(Position)
  implicit val summaryFormat = jsonFormat7(Summary)
  implicit val namesFormat = jsonFormat2(Name)
  implicit val classificationFormat = jsonFormat2(Classification)
  implicit val poiFormat = jsonFormat3(Poi)
  implicit val addressFormat = jsonFormat8(Address)
  implicit val viewportFormat = jsonFormat2(Viewport)

  implicit val resultFormamt = lazyFormat(jsonFormat(Result, "type", "id", "score", "dist", "info", "poi", "address",
    "position", "viewport"))

  /*implicit object ResultFormat extends RootJsonFormat[Result] {
    override def write(obj: Result): JsValue = {
      JsArray(JsString(obj.typeRes), JsString(obj.id), JsNumber(obj.score), JsNumber(obj.dist), JsString(obj.info),
        JsObject("poi" -> obj.poi.toJson),
        JsObject("address" -> obj.address.toJson),
        JsObject("position" -> obj.position.toJson),
        JsObject("viewport" -> obj.viewport.toJson)
      )
    }

    override def read(json: JsValue): Result = json match {
      case JsArray(Vector(JsString(typeRes), JsString(id), JsNumber(score), JsNumber(dist), JsString(info),
        poi, address, position, viewport)) =>
        Result(typeRes, id, score.toDouble, dist.toDouble, info,
          poi.convertTo[Poi],
          address.convertTo[Address],
          position.convertTo[Position],
          viewport.convertTo[Viewport]
      )

      case _ => throw new DeserializationException("Exception deserialization Result")
    }
  }*/
}
