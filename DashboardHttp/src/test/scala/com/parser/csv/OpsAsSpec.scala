package com.parser.csv

import com.parser.csv
import org.scalatest.{FlatSpec, Inside, Matchers}

class OpsAsSpec extends FlatSpec with Matchers with Inside {

  "The line cadena1,cadena2" should "be casted case class Test1(field1: String, field2: String)" in {

    val line = Array("cadena1", "cadena2")
    val t1 = Test1("cadena1", "cadena2")
    val testFactory = new csv.ReflectionHelpers.CaseClassFactory[Test1]
    val tf = testFactory.buildWith(line)

    tf should equal(t1)
    inside(tf)  {
      case Test1(c1, c2) =>
        c1 shouldBe("cadena1")
        c2 shouldBe("cadena2")
    }
  }
}
