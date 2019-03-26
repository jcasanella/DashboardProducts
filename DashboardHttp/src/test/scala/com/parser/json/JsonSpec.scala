package com.parser.json

import com.model.json
import com.model.json._
import org.scalatest.{FlatSpec, Matchers}
import com.model.json.ResponseProtocol._
import spray.json._

class JsonSpec extends FlatSpec with Matchers with JsonFixtures {

  "GeoBias message" should "be parsed" in {

    val geoBiasAst = geoBiasMsg.parseJson
    println(geoBiasAst.prettyPrint)

    val geoBiasObj = geoBiasAst.convertTo[Position]
    geoBiasObj.isInstanceOf[Position] shouldBe true
    geoBiasObj should equal (Position(37.8085, -122.4239))
  }

  "Summary message" should "be parsed" in {

    val summaryAst = summaryMsg.parseJson
    println(summaryAst.prettyPrint)

    val summaryObj = summaryAst.convertTo[Summary]
    summaryObj.isInstanceOf[Summary] shouldBe true
    summaryObj should equal (Summary(queryType = "NEARBY",
      queryTime = 17,
      numResults = 1,
      offset = 0,
      totalResults = 1,
      fuzzyLevel = 1,
      geoBias = Position(lat = 37.8085, lon = -122.4239)
    ))
  }

  "Category message" should "be parsed" in {

    val classificationAst = classificationMsg.parseJson
    println(classificationAst.prettyPrint)

    val classificationObj = classificationAst.convertTo[Classification]
    classificationObj.isInstanceOf[Classification] shouldBe true

    classificationObj.code shouldBe "GEOGRAPHIC_FEATURE"

    classificationObj.names.isInstanceOf[Array[Name]] shouldBe true
    classificationObj.names.length shouldBe 2
    classificationObj.names should equal (Array(Name("en-US", "bay"), Name("en-US", "geographic feature")))
  }

  "Poi message" should "be parsed" in {

    val poiAst = poiMsg.parseJson
    println(poiAst.prettyPrint)

    val poiObj = poiAst.convertTo[Poi]
    poiObj.isInstanceOf[Poi] shouldBe true

    poiObj.name shouldBe "Aquatic Cove"

    poiObj.categories.isInstanceOf[Array[String]] shouldBe true
    poiObj.categories should equal (Array("bay", "geographic feature"))

    poiObj.classifications.isInstanceOf[Array[Classification]] shouldBe true
    poiObj.classifications.length shouldBe 1
    poiObj.classifications(0).code shouldBe "GEOGRAPHIC_FEATURE"

    poiObj.classifications(0).names.isInstanceOf[Array[Name]] shouldBe true
    poiObj.classifications(0).names.length shouldBe 2
    poiObj.classifications(0).names should equal (Array(Name("en-US", "bay"), Name("en-US", "geographic feature")))
  }

  "Address Msg" should "be parsed" in {

    val addressAst = addressMsg.parseJson
    println(addressAst.prettyPrint)

    val addressObj = addressAst.convertTo[Address]
    addressObj should equal (Address(
      countrySecondarySubdivision = "San Francisco",
      countryTertiarySubdivision = "San Francisco",
      countrySubdivision = "CA",
      countryCode = "US",
      country = "United States Of America",
      countryCodeISO3 = "USA",
      freeformAddress = "San Francisco, CA",
      countrySubdivisionName = "California"
    ))
  }

  "Viewport Msg" should "be parsed" in {

    val viewportAst = viewportMsg.parseJson
    println(viewportAst.prettyPrint)

    val viewportObj = viewportAst.convertTo[Viewport]
    viewportObj should equal (Viewport(Position(37.80888, -122.42528), Position(37.80708,-122.423)))
  }

  "Result Msg" should "be parsed" in {

    val resultAst = resultMsg.parseJson
    println(resultAst.prettyPrint)

    val resultObj = resultAst.convertTo[Result]
    val resCase = Result(
      typeRes = "POI", id = "US/POI/p1/2276408", score = -0.062, dist = 61.54567482552456, info = "search:ta:840069002463939-US",
      poi = Poi(
        name = "Aquatic Cove",
        categories = Array[String]("bay", "geographic feature"),
        classifications = Array[Classification](
          Classification(
            code = "GEOGRAPHIC_FEATURE",
            names = Array[Name](
              Name(nameLocale = "en-US", name = "bay"),
              Name(nameLocale = "en-US", name = "geographic feature")
            )
          )
        )
      ),
      address = Address(
        countrySecondarySubdivision = "San Francisco",
        countryTertiarySubdivision = "San Francisco",
        countrySubdivision = "CA",
        countryCode = "US",
        country = "United States Of America",
        countryCodeISO3 = "USA",
        freeformAddress = "San Francisco, CA",
        countrySubdivisionName = "California"
      ),
      position = Position(lat = 37.80798, lon = -122.42414),
      viewport = Viewport(Position(lat = 37.80888, lon = -122.42528), Position(lat = 37.80708, lon = -122.423))
    )

    // Simple attributes Equal
    resultObj.typeRes shouldBe (resCase.typeRes)
    resultObj.id shouldBe (resCase.id)
    resultObj.score shouldBe (resCase.score)
    resultObj.dist shouldBe (resCase.dist)
    resultObj.info shouldBe (resCase.info)

    resultObj.address should equal (resCase.address)
    resultObj.position should equal (resCase.position)
    resultObj.viewport should equal (resCase.viewport)

    resultObj.poi.name shouldBe (resCase.poi.name)
    resultObj.poi.categories shouldBe (resCase.poi.categories)

    (0 until resultObj.poi.classifications.length by 1).foreach(idx => {
      resultObj.poi.classifications(idx).code should equal (resCase.poi.classifications(idx).code)
      resultObj.poi.classifications(idx).names should equal (resCase.poi.classifications(idx).names)
    })
  }
}
