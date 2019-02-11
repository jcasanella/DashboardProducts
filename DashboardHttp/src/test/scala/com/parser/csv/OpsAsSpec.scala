package com.parser.csv

import com.parser.csv
import org.scalatest.{FlatSpec, Inside, Matchers}

class OpsAsSpec extends FlatSpec with Matchers with Inside with OpsAsFixtures {

  "The line cadena1,cadena2" should "be casted to a case class Test1(field1: String, field2: String)" in {
    val line = lineTest1.split(",")
    val t1 = Test1("cadena1", "cadena2")
    val testFactory = new ReflectionHelpers.CaseClassFactory[Test1]
    val tf = testFactory.buildWith(line)

    tf should equal(t1)
    inside(tf)  {
      case Test1(c1, c2) =>
        c1 shouldBe("cadena1")
        c2 shouldBe("cadena2")
    }
  }

  "The line anna,56,london" should "be casted to a case class Person(name: String, age: Int, address: String)" in {
    val line = lineTest2.split(",")
    val person1 = Person("anna", 56, "london")
    val factory = new ReflectionHelpers.CaseClassFactory[Person]
    val person2 = factory.buildWith(line)

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
    val line = lineTest3.split(",")
    val animal1 = Animal(true, 123455678, "trex", "big animal")
    val factory = new ReflectionHelpers.CaseClassFactory[Animal]
    val animal2 = factory.buildWith(line)

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
