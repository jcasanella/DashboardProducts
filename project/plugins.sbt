// Akka Http
//addSbtPlugin("org.scalariform" % "sbt-scalariform" % "1.6.0")
addSbtPlugin("io.spray" % "sbt-revolver" % "0.9.1")
addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.14.5")

// The Play plugin
addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.6.20")
addSbtPlugin("com.geirsson" % "sbt-scalafmt" % "1.5.1")
//addSbtPlugin("com.lucidchart" % "sbt-scalafmt-coursier" % "1.12")

// Creates a class to read properties from build.sbt
addSbtPlugin("com.eed3si9n" % "sbt-buildinfo" % "0.9.0")

// Docker test
addSbtPlugin("com.tapad" % "sbt-docker-compose" % "1.0.34")
addSbtPlugin("se.marcuslonnberg" % "sbt-docker" % "1.5.0")

// Fat jars
addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "1.1.0")



