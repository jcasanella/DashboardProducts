package com.model.json

case class GeoBias(lat: Double, lon: Double)
case class Summary(queryType: String, queryTime: Int, numResults: Int, offset: Int, totalResults: Int,
                   fuzzyLevel: Int, geoBias: GeoBias)

case class Name(nameLocale: String, name: String)
case class Classification(code: String, names: Array[Name])
case class Poi(name: String, categories: Array[String], classifications: Array[Classification])
case class Address(countrySecondarySubdivision: String, countryTertiarySubdivision: String,
                   countrySubdivision: String, countryCode: String, country: String, countryCodeISO3: String,
                   freeformAddress: String, countrySubdivisionName: String)
case class Position(lat: Double, lon: Double)
case class TopLeftPoint(lat: Double, lon: Double)
case class BtmRightPoint(lat: Double, lon: Double)
case class Viewport(topLeftPoint: TopLeftPoint, btmRightPoint: BtmRightPoint)
case class Result(typeRes: String, id: String, score: Int, dist: Int, info: String, poi: Poi, address: Address,
                  position: Position, viewport: Viewport)



