lazy val resolverSettings = Seq(
  resolvers ++= Seq(
    Resolver.sonatypeRepo("snapshots"),
    Resolver.sonatypeRepo("releases")
  )
)

lazy val root = (project in file("."))
  .aggregate(dashboardHttp, dashboardApp)

lazy val dashboardHttp = (project in file("DashboardHttp"))
  .enablePlugins(BuildInfoPlugin)
  .settings(
    name := "DashboardHttp",
    settings,
    version := "1.0-SNAPSHOT",
    buildInfoKeys := Seq[BuildInfoKey](name, version, scalaVersion),
    buildInfoPackage := "com.sbt.properties",
    libraryDependencies ++= httpDependencies
  )

lazy val dashboardApp = (project in file("DashboardApp"))
  .enablePlugins(PlayScala)
  .settings(
    name := """DashboardApp""",
    settings,
    version := "1.0-SNAPSHOT",
    libraryDependencies ++= appDependencies ++
    Seq(
      guice,
      specs2 % Test
    )
  )

// Dependencies
lazy val dependencies =
  new {
    val playSlickVersion = "3.0.0"
    val playJsonVersion = "2.6.13"
    val h2DatabaseVersion = "1.4.197"
    val scalaTestPlusPlay = "3.1.2"
    val akkaHttpVersion = "10.0.11"
    val akkaVersion = "2.5.11"
    val slickVersion = "3.2.0"
    val scoptVersion = "3.7.1"
    val sflj4Version = "1.6.4"
    val scalatestVersion = "3.0.1"
    val postgresqlVersion = "42.2.5"
    val scalazVersion = "7.2.27"
    val reflectVersion = "2.12.7"
    val dockerTestKitVersion = "0.9.8"

    val testPlusPlay        = "org.scalatestplus.play" %% "scalatestplus-play"              % scalaTestPlusPlay    % Test
    val playJson            = "com.typesafe.play"      %% "play-json"                       % playJsonVersion
    val playSlick           = "com.typesafe.play"      %% "play-slick"                      % playSlickVersion
    val playSlickEvolutions = "com.typesafe.play"      %% "play-slick-evolutions"           % playSlickVersion
    val h2Database          = "com.h2database"         %  "h2"                              % h2DatabaseVersion
    val postgresSql         = "org.postgresql"         %  "postgresql"                      % "42.2.5"

    val akkaHttp            = "com.typesafe.akka"      %% "akka-http"                       % akkaHttpVersion
    val akkaHttpSprayJson   = "com.typesafe.akka"      %% "akka-http-spray-json"            % akkaHttpVersion
    val akkaHttpXml         = "com.typesafe.akka"      %% "akka-http-xml"                   % akkaHttpVersion
    val akkaStream          = "com.typesafe.akka"      %% "akka-stream"                     % akkaVersion
    val scopt               = "com.github.scopt"       %% "scopt"                           % scoptVersion
    val slick               = "com.typesafe.slick"     %% "slick"                           % slickVersion
    val slf4jNop            = "org.slf4j"              %  "slf4j-nop"                       % sflj4Version
    val slickHirakicp       = "com.typesafe.slick"     %% "slick-hikaricp"                  % slickVersion
    val scalaz              = "org.scalaz"             %% "scalaz-core"                     % scalazVersion
    val scalaReflect        = "org.scala-lang"         %  "scala-reflect"                   % reflectVersion

    val akkaHttpTestKit     = "com.typesafe.akka"      %% "akka-http-testkit"               % akkaHttpVersion      % Test
    val akkaTestKit         = "com.typesafe.akka"      %% "akka-testkit"                    % akkaVersion          % Test
    val akkaStreamTestKit   = "com.typesafe.akka"      %% "akka-stream-testkit"             % akkaVersion          % Test
    val scalaTest           = "org.scalatest"          %% "scalatest"                       % scalatestVersion     % Test
    val dockerTestKit       = "com.whisk"              %% "docker-testkit-scalatest"        % dockerTestKitVersion % Test
    val dockerTestKitJava   = "com.whisk"              %% "docker-testkit-impl-docker-java" % dockerTestKitVersion % Test
  }

lazy val httpDependencies = Seq(
  dependencies.akkaHttp,
  dependencies.akkaHttpSprayJson,
  dependencies.akkaHttpXml,
  dependencies.akkaStream,
  dependencies.scopt,
  dependencies.slick,
  dependencies.slf4jNop,
  dependencies.slickHirakicp,
  dependencies.scalaz,
  dependencies.scalaReflect,
  dependencies.akkaHttpTestKit,
  dependencies.akkaTestKit,
  dependencies.akkaStreamTestKit,
  dependencies.scalaTest,
  dependencies.dockerTestKit,
  dependencies.dockerTestKitJava
)

lazy val appDependencies = Seq(
  dependencies.testPlusPlay,
  dependencies.playJson,
  dependencies.playSlick,
  dependencies.playSlickEvolutions,
  dependencies.h2Database,
  dependencies.postgresSql
)

lazy val settings =
scalafmtSettings ++
resolverSettings ++
Seq(
  scalaVersion := "2.12.7",
  organization := "com.dashboard.jordi",
  crossScalaVersions := Seq("2.11.12", "2.12.7")
)

lazy val scalafmtSettings =
  Seq(
    scalafmtOnCompile := true
  )

lazy val assemblySettings = Seq(
  assemblyJarName in assembly := name.value + ".jar",
  assemblyMergeStrategy in assembly := {
    case PathList("META-INF", xs @ _*) => MergeStrategy.discard
    case _                             => MergeStrategy.first
  }
)
