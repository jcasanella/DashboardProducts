package com.parser.csv

import org.scalatest.{FlatSpec, Inside, Matchers}

class OpsAsSpec extends FlatSpec with Matchers with Inside with OpsAsFixtures {

  "The line cadena1,cadena2" should "be casted to a case class Test1(field1: String, field2: String)" in {
    import ReflectionHelpers._
    val t1 = Test1("cadena1", "cadena2")
    val tf = lineTest1.As[Test1]()

    tf should equal(t1)
    inside(tf)  {
      case Test1(c1, c2) =>
        c1 shouldBe("cadena1")
        c2 shouldBe("cadena2")
    }
  }

  "The line anna,56,london" should "be casted to a case class Person(name: String, age: Int, address: String)" in {
    val person1 = Person("anna", 56, "london")
    import ReflectionHelpers._
    val person2 = lineTest2.As[Person]()

    person2 should equal(person1)
    inside(person2) {
      case Person(n, a, ad) =>
        n shouldBe("anna")
        a shouldBe(56)
        ad shouldBe("london")
    }
  }

  "The line true,123455678,trex,big animal" should "be casted to a case class Animal(isAlive: Boolean, ages: Long, " +
    "name: String, description: String" in {
    val animal1 = Animal(true, 123455678, "trex", "big animal")

    import ReflectionHelpers._
    val animal2 = lineTest3.As[Animal]()

    animal2 should equal(animal1)
    inside(animal2) {
      case Animal(i, a, n, d) =>
        i shouldBe(true)
        a shouldBe(123455678)
        n shouldBe("trex")
        d shouldBe("big animal")
    }
  }
}
