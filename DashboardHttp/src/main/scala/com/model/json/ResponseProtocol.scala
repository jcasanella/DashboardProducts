package com.model.json
import spray.json.DefaultJsonProtocol

object ResponseProtocol extends DefaultJsonProtocol {

  implicit val geoBiasFormat = jsonFormat2(GeoBias)
  implicit val summaryFormat = jsonFormat7(Summary)
  implicit val namesFormat = jsonFormat2(Name)
  implicit val classificationFormat = jsonFormat2(Classification)
  implicit val poiFormat = jsonFormat3(Poi)
  implicit val addressFormat = jsonFormat8(Address)
}

