package com.parser.cmd
import com.sbt.properties.BuildInfo
import scopt.OptionParser

case class LineArgs(name: String = null, key: String = null)

class ParserCmdLine {

  val builder = new OptionParser[LineArgs]("DashboardHttp") {
    // BuildInfo generated using a plugin, these properties are from sbt
    head(BuildInfo.name, BuildInfo.version)

    opt[String]('f', "file") required () action { (x, c) =>
      c.copy(name = x)
    } text ("csv to parse is required")

    opt[String]('k', "key") required () action { (x, c) =>
      c.copy(key = x)
    } text ("key to connect to httpServer is required")

    help("help") text ("The valid parameters are f|file csvFile k|key keyToConnectServer")
  }

  def getParams(args: Seq[String]): Option[LineArgs] =
    builder.parse(args, LineArgs())
}

object ParserCmdLine {

  def apply(args: Seq[String]): Option[LineArgs] = {
    new ParserCmdLine().getParams(args)
  }
}
