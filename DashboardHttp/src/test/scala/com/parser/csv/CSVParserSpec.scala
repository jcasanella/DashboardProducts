package com.parser.csv
import org.scalatest.{Matchers, WordSpec}

class CSVParserSpec extends WordSpec with Matchers {

  "the file1 should be parsed" in {
    val recPath = getClass.getResource("/file1.csv")
//    println(recPath.getPath)
//    CSVParser(recPath.getPath).readFile

    println()
    implicit val propsDelim = CSVProperties(",")
    CSVParser(recPath.getPath).readFile

//    println()
//    implicit val p
//    CSVParser(recPath.getPath, false).readFile

//    println()
//    CSVParser(recPath.getPath, ",", false).readFile


//    println()
//    CSVParser(recPath.getPath, ",", true).readFile
  }
}
