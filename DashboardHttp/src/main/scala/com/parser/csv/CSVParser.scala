package com.parser.csv

import scala.io._

class CSVParser(val fileName: String)(implicit props: CSVProperties) {

  def readFile = {
    using(Source.fromFile(fileName)) { buffer =>
      val getLines = props.header match {
        case true  => buffer.getLines().drop(1)
        case false => buffer.getLines()
      }

      for (line <- getLines) {
        val cols = line.split(props.delimiter).map(_.trim)
        cols.foreach(value => print(" " + value))
        println()
      }
    }
  }

  private def using[A <: { def close(): Unit }, B](
      resource: A
  )(f: A => B): B = {
    try {
      f(resource)
    } finally {
      resource.close()
    }
  }
}

object CSVParser {

  def apply(fileName: String)(implicit props: CSVProperties) = {
    new CSVParser(fileName)
  }
}
