package com.parser.csv

sealed trait CSVProps {
  def delimiter: String
  def header: Boolean
  def quote: String
}

case class CSVProperties(delimiter: String = ",", header: Boolean = false, quote: String = "") extends CSVProps
