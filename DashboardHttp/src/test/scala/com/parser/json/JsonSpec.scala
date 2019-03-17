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

    val geoBiasObj = geoBiasAst.convertTo[GeoBias]
    geoBiasObj.isInstanceOf[GeoBias] shouldBe true
    geoBiasObj should equal (GeoBias(37.8085, -122.4239))
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
      GeoBias(lat = 37.8085, lon = -122.4239)
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
}
