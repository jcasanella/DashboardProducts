package com.parser.json

trait JsonFixtures {

  val geoBiasMsg = """  { "lat": 37.8085, "lon": -122.4239 } """

  val summaryMsg = """ {
                     |   "queryType": "NEARBY",
                     |   "queryTime": 17,
                     |   "numResults": 1,
                     |   "offset": 0,
                     |   "totalResults": 1,
                     |   "fuzzyLevel": 1,
                     |   "geoBias": {
                     |     "lat": 37.8085,
                     |     "lon": -122.4239
                     |   }
                     | } """.stripMargin

  val classificationMsg = """ {
                            |    "code": "GEOGRAPHIC_FEATURE",
                            |    "names": [ {
                            |      "nameLocale": "en-US",
                            |      "name": "bay"
                            |    },
                            |    {
                            |      "nameLocale": "en-US",
                            |      "name": "geographic feature"
                            |    }]
                            |  }""".stripMargin

  val poiMsg =
    """ {
      |   "name": "Aquatic Cove",
      |   "categories": [
      |     "bay",
      |     "geographic feature"
      |   ],
      |   "classifications": [ {
      |      "code": "GEOGRAPHIC_FEATURE",
      |      "names": [ {
      |        "nameLocale": "en-US",
      |        "name": "bay"
      |      },
      |      {
      |        "nameLocale": "en-US",
      |        "name": "geographic feature"
      |      } ]
      |   } ]
      | } """.stripMargin

  val addressMsg = """  {
                     |    "countrySecondarySubdivision": "San Francisco",
                     |    "countryTertiarySubdivision": "San Francisco",
                     |    "countrySubdivision": "CA",
                     |    "countryCode": "US",
                     |    "country": "United States Of America",
                     |    "countryCodeISO3": "USA",
                     |    "freeformAddress": "San Francisco, CA",
                     |    "countrySubdivisionName": "California"
                     |  }""".stripMargin
}
