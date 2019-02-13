package com.parser.csv

object implicits {
  implicit def converts[A <: Product](line: String): BuildCaseClass[A] = new BuildCaseClass[A](line)
}
