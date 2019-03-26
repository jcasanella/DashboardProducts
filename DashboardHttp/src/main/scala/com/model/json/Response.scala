package com.model.json

case class Summary(queryType: String, queryTime: Int, numResults: Int, offset: Int, totalResults: Int,
                   fuzzyLevel: Int, geoBias: Position)

case class Name(nameLocale: String, name: String)
case class Classification(code: String, names: Array[Name])
case class Poi(name: String, categories: Array[String], classifications: Array[Classification])
case class Address(countrySecondarySubdivision: String, countryTertiarySubdivision: String,
                   countrySubdivision: String, countryCode: String, country: String, countryCodeISO3: String,
                   freeformAddress: String, countrySubdivisionName: String)
case class Position(lat: Double, lon: Double)
case class Viewport(topLeftPoint: Position, btmRightPoint: Position)
case class Result(typeRes: String, id: String, score: Double, dist: Double, info: String, poi: Poi, address: Address,
                  position: Position, viewport: Viewport)

case class Response(summary: Summary, results: Array[Result])



