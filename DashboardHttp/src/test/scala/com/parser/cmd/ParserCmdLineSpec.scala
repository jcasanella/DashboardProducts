package com.parser.cmd
import org.scalatest.{Matchers, OptionValues, WordSpec}

class ParserCmdLineSpec extends WordSpec with Matchers with OptionValues {

  "command line with short params should be parsed" in {
    val args = Seq("-f", "test.csv", "-k", "key1")

    val parsed = ParserCmdLine(args)
    parsed should be ('defined)

    parsed.value should equal(LineArgs("test.csv", "key1"))
  }

  "command line with long params should be parsed" in {
    val args = Seq("--file", "test2.csv", "--key", "key2")

    val parsed = ParserCmdLine(args)
    parsed should be ('defined)

    parsed.value should equal(LineArgs("test2.csv", "key2"))
  }

  "command line mixing long and short params should be parsed" in {
    val args = Seq("--file", "test3.csv", "-k", "key3")

    val parsed = ParserCmdLine(args)
    parsed should be ('defined)

    parsed.value should equal(LineArgs("test3.csv", "key3"))
  }

  "command line with invalid short params should not be parsed" in {
    val args = Seq("-t", "test4.csv", "-k", "key4")
    ParserCmdLine(args) should not be ('defined)
  }

  "command line with invalid long params should not be parsed" in {
    val args = Seq("--test", "test5.csv", "--mnth", "key5")
    ParserCmdLine(args) should not be ('defined)
  }

  "command line with invalid mixed params should not be parsed" in {
    val args = Seq("--test", "test6.csv", "-m", "key6")
    ParserCmdLine(args) should not be ('defined)
  }
}
