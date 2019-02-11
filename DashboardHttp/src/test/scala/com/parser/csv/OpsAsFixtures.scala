package com.parser.csv

case class Test1(field1: String, field2: String)
case class Person(name: String, age: Int, address: String)
case class Animal(isAlive: Boolean, ages: Long, name: String, description: String)

trait OpsAsFixtures {
  val lineTest1 = "cadena1,cadena2"
  val lineTest2 = "anna,56,london"
  val lineTest3 = "true,123455678,trex,big animal"
}

