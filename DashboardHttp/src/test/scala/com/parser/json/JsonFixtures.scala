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

  val viewportMsg = """  {
                      |    "topLeftPoint": {
                      |      "lat": 37.80888,
                      |      "lon": -122.42528
                      |    },
                      |    "btmRightPoint": {
                      |      "lat": 37.80708,
                      |      "lon": -122.423
                      |    }
                      |  } """.stripMargin

  val resultMsg = """    {
                    |      "type": "POI",
                    |      "id": "US/POI/p1/2276408",
                    |      "score": -0.062,
                    |      "dist": 61.54567482552456,
                    |      "info": "search:ta:840069002463939-US",
                    |      "poi": {
                    |        "name": "Aquatic Cove",
                    |        "categories": [
                    |          "bay",
                    |          "geographic feature"
                    |        ],
                    |        "classifications": [
                    |          {
                    |            "code": "GEOGRAPHIC_FEATURE",
                    |            "names": [
                    |              {
                    |                "nameLocale": "en-US",
                    |                "name": "bay"
                    |              },
                    |              {
                    |                "nameLocale": "en-US",
                    |                "name": "geographic feature"
                    |              }
                    |            ]
                    |          }
                    |        ]
                    |      },
                    |      "address": {
                    |        "countrySecondarySubdivision": "San Francisco",
                    |        "countryTertiarySubdivision": "San Francisco",
                    |        "countrySubdivision": "CA",
                    |        "countryCode": "US",
                    |        "country": "United States Of America",
                    |        "countryCodeISO3": "USA",
                    |        "freeformAddress": "San Francisco, CA",
                    |        "countrySubdivisionName": "California"
                    |      },
                    |      "position": {
                    |        "lat": 37.80798,
                    |        "lon": -122.42414
                    |      },
                    |      "viewport": {
                    |        "topLeftPoint": {
                    |          "lat": 37.80888,
                    |          "lon": -122.42528
                    |        },
                    |        "btmRightPoint": {
                    |          "lat": 37.80708,
                    |          "lon": -122.423
                    |        }
                    |      }
                    |    } """.stripMargin
}
