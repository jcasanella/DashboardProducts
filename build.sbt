lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    name := """WebFeeds""",
    scalaVersion := "2.12.7",
    version := "1.0-SNAPSHOT"
  )

resolvers += Resolver.sonatypeRepo("snapshots")

crossScalaVersions := Seq("2.11.12", "2.12.7")

scalafmtOnCompile := false

val playSlickVersion = "3.0.0"
val playJsonVersion = "2.6.13"
val h2DatabaseVersion = "1.4.197"
val scalaTestPlusPlay = "3.1.2"
libraryDependencies ++= Seq(
  guice,
  "org.scalatestplus.play" %% "scalatestplus-play" % scalaTestPlusPlay % Test,
  "com.typesafe.play" %% "play-json" % playJsonVersion,
  specs2 % Test,
  "com.typesafe.play" %% "play-slick" % playSlickVersion,
  "com.typesafe.play" %% "play-slick-evolutions" % playSlickVersion,
  "com.h2database" % "h2" % h2DatabaseVersion,
  "org.postgresql" % "postgresql" % "42.2.5"

)




