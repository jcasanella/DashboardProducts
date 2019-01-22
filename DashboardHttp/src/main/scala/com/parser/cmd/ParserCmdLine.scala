package com.parser.cmd
import scopt.OptionParser

case class LineArgs(name: String = null, key: String = null)

class ParserCmdLine {
  val builder = new OptionParser[LineArgs]("DashboardHttp") {
    // TODO: check how to pick from build SBT
    head("DashboardHttp", "1.0.0")

    opt[String]('f', "file") required() action { (x, c) =>
      c.copy(name = x)
    } text("csv to parse is required")

    opt[String]('k', "key") required() action { (x, c) =>
      c.copy(key = x)
    } text("key to connect to httpServer is required")

    help("help") text("The valid parameters are f|file csvFile k|key keyToConnectServer")
  }

  def getParams(args: Seq[String]): Option[LineArgs] = builder.parse(args, LineArgs())
}

object ParserCmdLine {
  def apply(args: Seq[String]): Option[LineArgs] = {
    new ParserCmdLine().getParams(args)
  }
}
