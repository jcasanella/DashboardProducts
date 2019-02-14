package com.parser.csv
import org.scalatest.{Matchers, WordSpec}

class CSVParserSpec extends WordSpec with Matchers {

  "the file1 should be parsed" in {
    val recPath = getClass.getResource("/file1.csv")

    println()
    implicit val propsDelim = CSVProperties(",")
    CSVParser(recPath.getPath).readFile
  }
}
